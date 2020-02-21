package tests;

import org.junit.*;
import static org.junit.Assert.*;

import production.*;

/**
 * @author tommens
 *
 */
public class WorkstationTest {

	private Workstation w,
						w2;
	private Printserver s;
	private Packet p;

	@Before
	public void before() {
		w = new Workstation("Workstation 1");
		w2 = new Workstation("Workstation 2");
		w.setNextNode(w2);
		s = new Printserver("Printserver",w);
		w2.setNextNode(s);
		p = new Packet("some text",new Printserver("Printserver 2")); // create a packet with an addressee that is not part of the LAN
	}

	@Test
	public void testOriginator() {
		assertNull(p.originator); // initially, packet has no origin yet
		w.originate(p); // this results in output "Packet has unknown destination"
		assertSame(p.originator,w);
	}

	@Test
	public void testOriginator2() {
		p.setDestination(s);
		w.originate(p); // this results in output "Printing packet with contents 'some text'
		assertSame(p.originator,w);
	}

	@Test
	public void testAddressee() {
		p.setDestination(w2);
		w.originate(p);
		// after cycling through the network, the package should reach its destination workstation
		assertSame(w2,p.current);
	}


	@Test(expected = UnknownDestinationException.class)
	public void testCyclingWithException() throws UnknownDestinationException {
		  p.originator = w;
		  w.send(p); // this should throw an production.UnknownDestinationException
		}

	// Same test as previous one, except that it does not raise an exception, since the originate method catches the exception...
	@Test
	public void testCyclingWithoutException() {
		w.originate(p);
	}

}

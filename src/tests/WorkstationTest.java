package tests;

import org.junit.*;
import static org.junit.Assert.*;

import production.*;

/**
 * @author tommens
 *
 */
public class WorkstationTest {

	private Workstation w;
	private Printserver s;
	private Packet p;

	@Before
	public void before() {
		w = new Workstation("production.Workstation");
		s = new Printserver("production.Printserver",w);
		w.nextNode = s;
		p = new Packet("'some text'",new Node("production.Node")); // create a packet with an addressee that is not part of the production.LAN
	}

	@Test
	public void testOriginator() {
		assertNull(p.originator);
		w.originate(p); // this results in output "production.Packet has unknown destination"
		assertEquals(p.originator,w);
		p.addressee = s;
		w.originate(p); // this results in output "Printing packet with contents 'some text'
		assertEquals(p.originator,w);
	}

	@Test(expected = UnknownDestinationException.class)
	public void testCycling() throws UnknownDestinationException {
		  p.originator = w;
		  w.send(p); // this should throw an production.UnknownDestinationException
		}

}

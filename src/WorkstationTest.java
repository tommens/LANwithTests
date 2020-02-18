import org.junit.*;

import static org.junit.Assert.*;

/**
 * @author tommens
 *
 */
public class WorkstationTest {

	Workstation w;
	Printserver s;
	Packet p;
	

	@Before
	public void before() throws Exception {
		w = new Workstation("Workstation");
		s = new Printserver("Printserver",w);
		w.nextNode = s;
		p = new Packet("'some text'",new Node("Node")); // create a packet with an addressee that is not part of the LAN
	}

	@Test
	public void testOriginator() {
		assertNull(p.originator);
		w.originate(p); // this results in output "Packet has unknown destination"
		assertEquals(p.originator,w);
		p.addressee = s;
		w.originate(p); // this results in output "Printing packet with contents 'some text'
		assertEquals(p.originator,w);
	}

	@Test
	public void testCycling() {
		try {
		  p.originator = w;
		  w.send(p); // this should throw an UnknownDestinationException
		}
		catch (UnknownDestinationException e) {
		  return;
		}
		fail("I expected an UnknownDestinationException here");
	}

}

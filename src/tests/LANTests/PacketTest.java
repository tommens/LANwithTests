package tests.LANTests;

import org.junit.*;
import static org.junit.Assert.*;

import production.LAN.Packet;
import production.LAN.Workstation;

/**
 * @author tommens
 *

 */
public class PacketTest {

	private Packet p1,
			       p2,
			       p3;
	@SuppressWarnings("FieldCanBeLocal")
	private Workstation n1, n2;

	@Before
	public void before() {
		n1 = new Workstation("W1");
		n2 = new Workstation("W2");
		p1 = new Packet("Packet 1", n1);
		p2 = new Packet("Packet 2", n1); // same addressee as p1
		p3 = new Packet("Packet 2", n2); // same contents as p2
	}

	@Test
	public void testPacketContents() {
		assertEquals("Packet 1", p1.toString());
		// p1 and p2 have a different contents
		assertNotSame(p1,p2);
		// p2 and p3 have the same contents
		assertEquals(p2.toString(),p3.toString());
	}

	@Test
	public void testPacketDestination() {
		assertSame(n1,p1.getDestination());
		// p1 and p3 have a different destination
		assertNotSame(p1,p3);
		// p1 and p2 have the same destination
		assertSame(p1.getDestination(), p2.getDestination());
	}

	@Test
	public void testPacketOriginator() {
		// upon creation, a packet has no originator
		assertNull(p1.getOriginator());
		// The originator of a packet will be set by a Workstation when it receives a visitor
	}

}

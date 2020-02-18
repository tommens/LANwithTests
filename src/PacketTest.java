import org.junit.*;

import static org.junit.Assert.*;

/**
 * @author tommens
 *

 */
public class PacketTest {

	private Packet p1, p2, p3;

	@Before
	public void before() throws Exception {
		Node n1 = new Node("Node 1");
		Node n2 = new Node("Node 2");
		p1 = new Packet("Packet 1", n1);
		p2 = new Packet("Packet 2", n1); // same addressee as p1
		p3 = new Packet("Packet 2", n2); // same contents as p2
	}

	@Test
	public void testPacketContents() {
		assertNotSame(p1,p2); // p1 and p2 have a different contents
		assertEquals(p2.contents,p3.contents); // to be done: p2 and p3 have the same contents
	}

	@Test
	public void testPacketAddressee() {
		// p1 and p3 have a different addressee
		assertNotSame(p1,p3);
		
		// p1 and p2 have the same addressee
		assertEquals(p1.addressee,p2.addressee);
		assertEquals(p1.addressee.name,p2.addressee.name);
	}

}

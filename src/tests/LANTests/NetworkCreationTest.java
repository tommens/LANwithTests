package tests.LANTests;

import org.junit.*;
import static org.junit.Assert.*;

import production.LAN.Network;
import production.LAN.Printserver;
import production.LAN.Workstation;

import java.util.Arrays;

/**
 * @author tommens
 *
 */
public class NetworkCreationTest {

	private Network lan;

	@Before
	public void before() {
		lan = new Network(Arrays.asList("workstation1", "workstation2", "printserver1"));
	}

	@Test
	public void testUnkwownNodesInNetwork() {
		assertNull(lan.findNode("workstation3")); // name does not correspond to a known node name in network
		assertNull(lan.findNode("printserver2")); // name does not correspond to a known node name in network
	}

	@Test
	public void testIfNodesHaveCorrectType() {
		assertSame(lan.findNode(Workstation.class).getClass(),Workstation.class);
		assertSame(lan.findNode("workstation1").getClass(),Workstation.class);
		assertSame(lan.findNode("workstation2").getClass(),Workstation.class);
		assertSame(lan.findNode(Printserver.class).getClass(),Printserver.class);
		assertSame(lan.findNode("printserver1").getClass(),Printserver.class);
	}

	@Test
	public void testNodeNames() {
		assertEquals("printserver1",lan.findNode("printserver1").toString());
		assertEquals("workstation2",lan.findNode("workstation2").toString());
	}

	@Test
	public void testCircularity() {
		assertSame(lan.findNode("workstation1").getNextNode(),lan.findNode("workstation2"));
		assertSame(lan.findNode("workstation2").getNextNode(),lan.findNode("printserver1"));
		assertSame(lan.findNode("printserver1").getNextNode(),lan.findNode("workstation1"));
	}

}

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
		assertNull(lan.findWorkstation("printserver1")); // printserver1 is not a Workstation so cannot be found
		assertNull(lan.findPrintserver("workstation1")); // workstation1 is not a Printserver so cannot be found
		assertNull(lan.findWorkstation("workstation3")); // name does not correspond to a known node name in network
		assertNull(lan.findWorkstation("printserver2")); // name does not correspond to a known node name in network
	}

	@Test
	public void testIfNodesHaveCorrectType() {
		assertSame(lan.findWorkstation("workstation1").getClass(),Workstation.class);
		assertSame(lan.findWorkstation("workstation2").getClass(),Workstation.class);
		assertSame(lan.findPrintserver("printserver1").getClass(),Printserver.class);
	}

	@Test
	public void testNodeNames() {
		assertEquals("printserver1",lan.findPrintserver("printserver1").toString());
		assertEquals("workstation2",lan.findWorkstation("workstation2").toString());
	}

	@Test
	public void testCircularity() {
		assertSame(lan.findWorkstation("workstation1").getNextNode(),lan.findWorkstation("workstation2"));
		assertSame(lan.findWorkstation("workstation2").getNextNode(),lan.findPrintserver("printserver1"));
		assertSame(lan.findPrintserver("printserver1").getNextNode(),lan.findWorkstation("workstation1"));
	}

}

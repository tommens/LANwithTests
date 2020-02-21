package tests;

import org.junit.*;
import production.*;

import static org.junit.Assert.*;

/**
 * @author tommens
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */

public class NodeTest {

	private Node n1,
			     n2;

	@Before
	public void before() {
		n1 = new Workstation("w1");
		n2 = new Printserver("ps1",n1);
		n1.setNextNode(n2);
	}

	@Test
	public void testNextnode() {
		assertEquals(n1.getNextNode(),n2);
		assertEquals(n2.getNextNode(),n1);
	}

}

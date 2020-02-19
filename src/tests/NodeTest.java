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

	private Node n,
				 n1,
			     n2;

	@Before
	public void before() {
		n = new Node("Node 1");
		n1 = new Node("Node 1"); // same name as n
		n2 = new Node("Node 2",n1);
		n1.setNextNode(n2);
	}

	@Test
	public void testNextnode() {
		assertEquals(n1.getNextNode(),n2);
		assertEquals(n2.getNextNode(),n1);
	}

}

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

	private Node n, n1, n2;

	@Before
	public void before() throws Exception {
		n = new Node("production.Node 1");
		n1 = new Node("production.Node 1"); // same name as n
		n2 = new Node("production.Node 2",n1);
		n1.nextNode = n2;
	}

	@Test
	public void testNextnode() {
		Assert.assertEquals(n1.nextNode,n2);
		Assert.assertEquals(n2.nextNode,n1);
	}

	@Test
	public void testName() {
		Assert.assertEquals(n.name,n1.name);
		assertNotSame(n1.name,n2.name);
	}
}
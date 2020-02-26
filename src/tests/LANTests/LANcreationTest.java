package tests.LANTests;

import org.junit.*;
import static org.junit.Assert.*;

import production.LAN.Printserver;
import production.LAN.Workstation;

/**
 * @author tommens
 *
 */
public class LANcreationTest {

	private Workstation w,
						w2;
	private Printserver s;

	@Before
	public void before() {
		w2 = new Workstation("workstation 2");
		w = new Workstation("workstation 1",w2);
		s = new Printserver("printserver",w);
		w2.setNextNode(s);
	}

	@Test
	public void test() {
		assertSame(w2,w.getNextNode());
		assertSame(s,w2.getNextNode());
		assertSame(w,s.getNextNode());
		assertEquals("workstation 2",w2.toString());
		assertEquals("printserver",s.toString());
	}

}

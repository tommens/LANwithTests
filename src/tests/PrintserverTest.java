package tests;

import org.junit.*;
import static org.junit.Assert.*;

import production.*;

/**
 * @author tommens
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class PrintserverTest {

	private Workstation w;
	private Printserver s;
	private Packet p;

	@Before
	public void before() {
		w = new Workstation("Workstation");
		s = new Printserver("Printserver",w);
		w.setNextNode(s);
		p = new Packet("text to be printed on Printserver",s);
	}


	@Test
	public void testPrintserver() {
		w.originate(p);
		assertSame(s,p.current);
		//after iterating over the network, the package p should have travelled to its destination printserver s, that will have received the packet and printed its contents...
	}

}

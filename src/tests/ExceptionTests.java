package tests;

import org.junit.*;
import production.*;

/**
 * @author tommens
 *
 */
public class ExceptionTests {

	Workstation w;
	Printserver s;
	Packet p;

	@Before
	public void before() {
		//super.setUp();
		w = new Workstation("production.Workstation");
		s = new Printserver("production.Printserver",w);
		w.nextNode = s;
		p = new Packet("'some text'",new Node("production.Node"));
	}

	@Test(expected = UnknownDestinationException.class)
	public void testCycling() throws UnknownDestinationException {
		p.originator = w;
		w.send(p); // this should throw an production.UnknownDestinationException
	}

}

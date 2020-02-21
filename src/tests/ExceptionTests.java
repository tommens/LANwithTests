package tests;

import org.junit.*;
import production.*;

/**
 * @author tommens
 *
 */
public class ExceptionTests {

	private Workstation w1,
						w2;
	private Printserver s1,
						s2;
	private Packet p;

	@Before
	public void before() {
		w2 = new Workstation("My second workstation");
		w1 = new Workstation("My workstation",w2);
		s1 = new Printserver("My printserver");
		s2 = new Printserver("My second printserver",w1);
		s1.setNextNode(s2);
		w2.setNextNode(s1);
		p = new Packet("some text",new Workstation("Unreacable workstation"));
		p.setTracking(true);
	}

	//Testing a package that is sent to an unknown destination
	@Test(expected = UnknownDestinationException.class)
	public void testCycling() throws UnknownDestinationException {
		p.originator = w1;
		w1.send(p); // this should throw an production.UnknownDestinationException
	}

}

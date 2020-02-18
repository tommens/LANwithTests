import org.junit.*;


/**
 * @author tommens
 *
 */
public class ExceptionTests {

	Workstation w;
	Printserver s;
	Packet p;

	@Before
	public void before() throws Exception {
		//super.setUp();
		w = new Workstation("Workstation");
		s = new Printserver("Printserver",w);
		w.nextNode = s;
		p = new Packet("'some text'",new Node("Node"));
	}

	@Test(expected = UnknownDestinationException.class)
	public void testCycling() throws UnknownDestinationException {
		p.originator = w;
		w.send(p); // this should throw an UnknownDestinationException
	}

}

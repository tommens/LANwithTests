package tests;

import org.junit.*;
import production.*;

import static org.junit.Assert.*;

public class CollectingVisitorTests {

    private Workstation w1, w2, w3;
    private Printserver ps1, ps2;

    @Before
    public void before() {
        // create a small token ring network to be used by each test
        w1 = new Workstation("Workstation1");
        w2 = new Workstation("Workstation2");
        w3 = new Workstation("Workstation3");
        ps1 = new Printserver("Printer1");
        ps2 = new Printserver("Printer2");

        w1.setNextNode(w2);
        w2.setNextNode(ps1);
        ps1.setNextNode(w3);
        w3.setNextNode(ps2);
        ps2.setNextNode(w1);
    }

    @After
    public void after() {
        // set all nodes of the network to null to avoid side effects in tests because of earlier tests
        w1 = null;
        w2 = null;
        w3 = null;
        ps1 = null;
        ps2 = null;
    }

    @Test
    public void testWorkstations() {
        // create a tracking visitor with tracking packet with destination address w1
        CollectingVisitor v = new CollectingVisitor(new Packet("tracking packet", w1));
        w1.accept(v); // send this visitor over the network, starting from the destination node w1, the packet should do one full cycle over the network to return to this node
        assertTrue(v.contains(w1));
        assertTrue(v.contains(w2));
        assertTrue(v.contains(w3));
        assertTrue(v.contains(ps1));
        assertTrue(v.contains(ps2));
    }

    @Test
    public void testPrintservers() {
        // create a tracking visitor with tracking packet with destination address w1
        CollectingVisitor v = new CollectingVisitor(new Packet("tracking packet", w1));
        w1.accept(v); // send this visitor over the network, starting from the destination node w1, the packet should do one full cycle over the network to return to this node
        assertTrue(v.contains(ps1));
        assertTrue(v.contains(ps2));
    }

}

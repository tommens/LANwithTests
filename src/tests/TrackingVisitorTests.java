package tests;

import org.junit.*;
import production.*;

import static org.junit.Assert.*;

public class TrackingVisitorTests {

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
    public void testOriginator() {
        w1.setNextNode(w1); // create a network of cycle length 1
        TrackingVisitor v = new TrackingVisitor(new Packet("contents",ps1));
        assertSame(null,v.visitingPacket.originator);
        w1.accept(v);
        assertSame(w1,v.visitingPacket.originator);
    }

    @Test
    public void test1() {
        // create a tracking visitor with tracking packet with destination address w1
        TrackingVisitor tv = new TrackingVisitor(new Packet("tracking packet", w1));
        w1.accept(tv); // send this visitor over the network, starting from the destination node w1, the packet should do one full cycle over the network to return to this node
        assertSame(w1,tv.current); // after visiting, destination node must have been reached
     }

    @Test
    public void test2() {
        // create a tracking visitor with tracking packet with destination address ps2
        TrackingVisitor tv = new TrackingVisitor(new Packet("tracking packet", ps2));
        w1.accept(tv); // visitor is given to the workstation to start iterating
        assertSame(ps2,tv.current); // after visiting, destination node must have been reached
    }

    @Test
    public void test3() {
        // create a tracking visitor with tracking packet with destination address not in network
        TrackingVisitor tv = new TrackingVisitor(new Packet("tracking packet", new Printserver("Printer3")));
        w1.accept(tv); // visitor is given to the workstation to start iterating
        assertSame(w1,tv.current); // after visiting, origin node of traversal, i.e., w1, must have been reached since the destination node did not exist
    }
}

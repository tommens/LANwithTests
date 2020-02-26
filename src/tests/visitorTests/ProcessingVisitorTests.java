package tests.visitorTests;

import org.junit.*;
import production.LAN.Packet;
import production.LAN.Printserver;
import production.LAN.Workstation;
import production.visitors.ProcessingVisitor;

import static org.junit.Assert.*;

public class ProcessingVisitorTests {

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
        ProcessingVisitor v = new ProcessingVisitor(new Packet("contents",ps1));
        assertSame(null,v.getSource());
        w1.accept(v);
        assertSame(w1,v.getSource());
    }

    @Test
    public void test1() {
        // create a tracking visitor with tracking packet with destination address w1
        ProcessingVisitor v = new ProcessingVisitor(new Packet("tracking packet", w1));
        w1.accept(v); // send this visitor over the network, starting from the destination node w1, the packet should do one full cycle over the network to return to this node
        assertSame(w1,v.getCurrent()); // after visiting, destination node must have been reached
    }

    @Test
    public void test2() {
        // create a tracking visitor with tracking packet with as destination a printserver ps2
        ProcessingVisitor v = new ProcessingVisitor(new Packet("tracking packet", ps2));
        w1.accept(v); // visitor is given to the workstation to start iterating
        assertSame(ps2,v.getCurrent()); // after visiting, destination node must have been reached
    }

    @Test
    public void test3() {
        // create a tracking visitor with tracking packet with destination address not in network
        ProcessingVisitor v = new ProcessingVisitor(new Packet("tracking packet", new Printserver("Printer3")));
        w1.accept(v); // visitor is given to the workstation to start iterating
        assertSame(w1,v.getCurrent()); // after visiting, origin node of traversal, i.e., w1, must have been reached since the destination node did not exist
    }
}

package tests.visitorTests;

import org.junit.*;
import production.LAN.*;
import production.visitors.ProcessingVisitor;

import java.util.Arrays;

import static org.junit.Assert.*;

public class ProcessingVisitorTests {

    private Network lan;
    Node originator;

    @Before
    public void before() {
        // create a small token ring network to be used by each test
        lan = new Network(Arrays.asList("workstation1","workstation2","printserver1","workstation3","printserver2"));
        originator = lan.findNode("workstation1");
    }

    @After
    public void after() {
        // set all nodes of the network to null to avoid side effects in tests because of earlier tests
        lan = null;
        originator = null;
    }

    @Test
    public void testOriginator() {
        originator.setNextNode(originator); // create a network of cycle length 1
        ProcessingVisitor v = new ProcessingVisitor(new Packet("contents",lan.findNode("printserver1")));
        assertSame(null,v.getSource());
        originator.accept(v);
        assertSame(originator,v.getSource());
    }

    @Test
    public void test1() {
        // create a processing visitor with packet with destination address == originator
        ProcessingVisitor v = new ProcessingVisitor(new Packet("tracking packet", originator));
        originator.accept(v); // send this visitor over the network, starting from the destination node w1, the packet should do one full cycle over the network to return to this node
        assertSame(originator,v.getCurrent()); // after visiting, destination node must have been reached
    }

    @Test
    public void test2() {
        // create a processing visitor with packet with as destination a printserver ps2
        Node ps2 = lan.findNode("printserver2");
        ProcessingVisitor v = new ProcessingVisitor(new Packet("tracking packet", ps2));
        originator.accept(v); // visitor is given to the workstation to start iterating
        assertSame(ps2,v.getCurrent()); // after visiting, destination node must have been reached
    }

    @Test
    public void test3() {
        // create a processing visitor with packet with destination address not in network
        ProcessingVisitor v = new ProcessingVisitor(new Packet("tracking packet", new Printserver("Printer3")));
        originator.accept(v); // visitor is given to the workstation to start iterating
        assertSame(originator,v.getCurrent()); // after visiting, origin node of traversal, i.e., w1, must have been reached since the destination node did not exist
    }
}

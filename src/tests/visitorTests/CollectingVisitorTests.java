package tests.visitorTests;

import org.junit.*;
import production.LAN.*;
import production.visitors.CollectingVisitor;

import java.util.Arrays;

import static org.junit.Assert.*;

public class CollectingVisitorTests {

    private Network lan;

    @Before
    public void before() {
        // create a small token ring network to be used by each test
        lan = new Network(Arrays.asList("workstation1","workstation2","printserver1","workstation3","printserver2"));
     }

    @After
    public void after() {
        // set all nodes of the network to null to avoid side effects in tests because of earlier tests
        lan = null;
     }

    @Test
    public void testCollectedWorkstations() {
        // create a collecting visitor with packet with destination address w1
        Workstation w1 = lan.findWorkstation("workstation1");
        CollectingVisitor v = new CollectingVisitor(new Packet("tracking packet", w1));
        w1.accept(v); // send this visitor over the network, starting from the destination node w1, the packet should do one full cycle over the network to return to this node
        assertTrue(v.contains(w1));
        assertTrue(v.contains(lan.findWorkstation("workstation2")));
        assertTrue(v.contains(lan.findWorkstation("workstation3")));
     }

    @Test
    public void testCollectedPrintservers() {
        // create a collecting visitor with packet with destination address w1
        Workstation w1 = lan.findWorkstation("workstation1");
        CollectingVisitor v = new CollectingVisitor(new Packet("tracking packet", w1));
        w1.accept(v); // send this visitor over the network, starting from the destination node w1, the packet should do one full cycle over the network to return to this node
        assertTrue(v.contains(lan.findPrintserver("printserver1")));
        assertTrue(v.contains(lan.findPrintserver("printserver2")));
    }

}

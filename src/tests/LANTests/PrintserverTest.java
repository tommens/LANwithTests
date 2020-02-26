package tests.LANTests;

import org.junit.*;
import static org.junit.Assert.*;

import production.LAN.Printserver;
import production.LAN.Workstation;

public class PrintserverTest {
    private Workstation w;

    @Test
    public void test() {
        Printserver w = new Printserver("printer");
        assertEquals("printer",w.toString());
    }

}

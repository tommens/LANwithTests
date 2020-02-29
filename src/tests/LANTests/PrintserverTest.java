package tests.LANTests;

import org.junit.*;
import static org.junit.Assert.*;

import production.LAN.Printserver;

public class PrintserverTest {

    @Test
    public void test() {
        Printserver w = new Printserver("printer");
        assertEquals("printer",w.toString());
    }

}

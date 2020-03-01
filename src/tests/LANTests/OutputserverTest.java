package tests.LANTests;

import org.junit.*;
import static org.junit.Assert.*;

import production.LAN.Printserver;
import production.LAN.Fileserver;

public class OutputserverTest {

    //more complex behaviour is tests by the visitorTests ...

    @Test
    public void testPrintserver() {
        Printserver p = new Printserver("printer");
        assertEquals("printer",p.toString());
    }

    @Test
    public void testFileserver() {
        Fileserver f = new Fileserver("file server");
        assertEquals("file server",f.toString());
    }
}

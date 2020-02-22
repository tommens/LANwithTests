package tests;

import org.junit.*;
import static org.junit.Assert.*;

import production.*;

public class PrintserverTest {
    private Workstation w;

    @Test
    public void test() {
        Printserver w = new Printserver("printer");
        assertEquals("printer",w.toString());
    }

}

package tests;

import org.junit.*;
import static org.junit.Assert.*;

import production.*;

public class WorkstationTest {
    private Workstation w;

    @Test
    public void test() {
        Workstation w = new Workstation("workstation");
        assertEquals("workstation",w.toString());
    }

}

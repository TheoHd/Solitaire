package app;

import org.testng.annotations.Test;
import static org.testng.AssertJUnit.assertEquals;

public class TestsJUnit {
    @Test
    public void test() {
        assertEquals("test fonction", 2, 2);
    }
}

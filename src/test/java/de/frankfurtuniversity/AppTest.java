package de.frankfurtuniversity;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class AppTest {
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @Test
    public void test() {
        byte b = (byte)0xfe;
        int s = (b & b);
        System.out.println(s);
    }
}

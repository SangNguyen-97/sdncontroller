package de.frankfurtuniversity;

import static org.junit.Assert.assertTrue;

import java.util.HashMap;

import org.junit.Test;

public class AppTest {
    class testClass {
        HashMap<Integer, String> hm = new HashMap<Integer, String>();

        public testClass(HashMap<Integer, String> hm) throws IllegalArgumentException {
            if (hm instanceof HashMap<?, ?>){
                this.hm = (HashMap<Integer, String>) hm.clone();
            }
            else {
                throw new IllegalArgumentException();
            }
        }
    }

    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @Test
    public void test() {
    //     byte[] data = { 1, 2, 3, 4 };
    //     testClass obj = new testClass();
    //     if (obj != null) {
    //         System.out.println("checked");
    //     }
    }
}

package de.frankfurtuniversity.utils;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.frankfurtuniversity.protocol.of.Utility;

public class UtilityTest {
    @Test
    public void bytesToLongTest(){
        long l = 12345678910111213L;
        byte[] b = {
            (byte) 0x00, (byte) 0x2b, (byte) 0xdc, (byte) 0x54, (byte) 0x5d, (byte) 0xf2, (byte) 0xbd, (byte) 0xed
        };
        assertEquals("bytes to long test", l, Utility.bytesToLong(b,0));
    }

    @Test
    public void bytesToIntTest(){
        int i = 651354857;
        byte[] b = {
            (byte) 0x26, (byte) 0xd2, (byte) 0xe2, (byte) 0xe9
        };
        assertEquals("bytes to int test", i, Utility.bytesToInt(b,0));
    }

    @Test
    public void bytesToShortTest(){
        short s = (short) 65535;
        byte[] b = {
            (byte) 0xff, (byte) 0xff
        };
        assertEquals("bytes to short test", s, Utility.bytesToShort(b, 0));
    }
}

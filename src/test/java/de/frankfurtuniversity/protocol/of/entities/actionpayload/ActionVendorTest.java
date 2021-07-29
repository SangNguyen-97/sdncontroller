package de.frankfurtuniversity.protocol.of.entities.actionpayload;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

public class ActionVendorTest {
    byte[] raw = {
        (byte) 0x00, (byte) 0xae, (byte) 0x8f, (byte) 0x9b
    };
    long vendor = 11440027;
    
    @Test
    public void testConstructor(){
        ActionVendor a = new ActionVendor(raw);
        assertEquals("constructor", vendor, a.getVendor());
    }

    @Test
    public void testToHeader(){
        ActionVendor a = new ActionVendor(vendor);
        assertTrue("to header", Arrays.equals(raw, a.toHeader()));
    }
}

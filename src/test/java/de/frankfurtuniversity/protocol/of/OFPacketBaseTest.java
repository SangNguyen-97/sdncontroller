package de.frankfurtuniversity.protocol.of;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class OFPacketBaseTest {
    @Test
    public void testConstructor(){

        byte[] raw = {
            (byte) 0x01, (byte) 0x00, (byte) 0x00, (byte) 0x08, // version + type
            (byte) 0x00, (byte) 0x12, (byte) 0xd6, (byte) 0x87 // length + xid
        };
        
        OFPacketBase p = new OFPacketBase(raw);
        System.out.println(p.toString());
        assertEquals("Checking version",(short) 1, p.version);
        assertEquals((short) 0, p.type);
        assertEquals((int) 8, p.length);
        assertEquals((long) 1234567, p.xid);
    }
    
}

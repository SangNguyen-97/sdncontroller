package de.frankfurtuniversity.protocol.of;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class OFPacketFeatResTest {
    @Test
    public void testConstructorWithoutPorts(){
        
        byte[] raw = {
            // OF Header
            (byte) 0x01, (byte) 0x00, (byte) 0x00, (byte) 0x32,
            (byte) 0x00, (byte) 0x12, (byte) 0xd6, (byte) 0x87,
            // OF feature response
            (byte) 0x00, (byte) 0x2b, (byte) 0xdc, (byte) 0x54, // datapath_id
            (byte) 0x5d, (byte) 0xf2, (byte) 0xbd, (byte) 0xed,
            (byte) 0x5d, (byte) 0x2a, (byte) 0x4c, (byte) 0xcc, // n_buffers
            (byte) 0xfa, (byte) 0x00, (byte) 0x00, (byte) 0x00, // n_tbls + padding
            (byte) 0x3a, (byte) 0xde, (byte) 0x68, (byte) 0xb1, // capabilities
            (byte) 0x21, (byte) 0xa5, (byte) 0x6c, (byte) 0xd8, // actions
        };

        OFPacketFeatRes p = new OFPacketFeatRes(raw);
        assertEquals("datapath_id",12345678910111213L, p.datapath_id);
        assertEquals("n_buffers",(long) 1563053260, p.n_buffers);
        assertEquals("n_tbls",(short) 250, p.n_tbls);
        assertEquals("capabilities",987654321, p.capabilities);
        assertEquals("actions",564489432, p.actions);
    }
}

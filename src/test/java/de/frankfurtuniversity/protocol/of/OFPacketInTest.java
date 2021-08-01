package de.frankfurtuniversity.protocol.of;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

public class OFPacketInTest {
    @Test
    public void testConstructor(){
        byte[] sraw = {
            (byte) 0x01, (byte) 0x0a, (byte) 0x00, (byte) 0x18, 
            (byte) 0x7c, (byte) 0x5a, (byte) 0xdf, (byte) 0xae
        };
        byte[] praw = {
            (byte) 0xae, (byte) 0x5c, (byte) 0x3d, (byte) 0x6f, 
            (byte) 0x00, (byte) 0x64, (byte) 0xab, (byte) 0x12, 
            (byte) 0x00, (byte) 0x00, 

                                      (byte) 0x12, (byte) 0x00, 
            (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0xab,
            (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00
        };
        
        short version = OFPacketBase.VERSION;
        short type = OFPacketBase.OFPACKETTYPE_PACKET_IN;
        int length = 24;
        long xid = 0x7c5adfaeL;

        long buffer_id = 0xae5c3d6fL;
        int total_len = 100;
        int inport = 0xab12;
        byte reason = OFPacketIn.REASON_NOMATCH;

        byte[] data = {
                                      (byte) 0x12, (byte) 0x00, 
            (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0xab,
            (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00
        };

        OFPacketBase s = new OFPacketBase(sraw);
        OFPacketIn p = new OFPacketIn(s,praw);

        assertEquals("version", version, p.getVersion());
        assertEquals("type", type, p.getType());
        assertEquals("length", length, p.getLength());
        assertEquals("xid", xid, p.getXid());

        assertEquals("buffer_id", buffer_id, p.getBufferId());
        assertEquals("total_length", total_len, p.getTotalLength());
        assertEquals("inport", inport, p.getInport());
        assertEquals("reason", reason, p.getReason());
        assertTrue("data", Arrays.equals(data, p.getData()));
    }
}

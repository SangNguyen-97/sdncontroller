package de.frankfurtuniversity.protocol.of;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

public class OFPacketErrorTest {
    short version = OFPacketBase.VERSION;
    short type = OFPacketBase.OFPACKETTYPE_ERROR;
    int length = 24;
    long xid = 0xabcdefabL;

    int etype = OFPacketError.ETYPE_FLOWMODFAILED;
    int code = 5;
    byte[] data = {
        (byte) 0x48, (byte) 0x65, (byte) 0x6c, (byte) 0x6c, 
        (byte) 0x6f, (byte) 0x20, (byte) 0x57, (byte) 0x6f, 
        (byte) 0x72, (byte) 0x6c, (byte) 0x64, (byte) 0x21
    };
    
    @Test
    public void testConstructor(){
        byte[] sraw = {
            (byte) 0x01, (byte) 0x01, (byte) 0x00, (byte) 0x18, 
            (byte) 0xab, (byte) 0xcd, (byte) 0xef, (byte) 0xab
        };
        byte[] praw = {
            (byte) 0x00, (byte) 0x03, (byte) 0x00, (byte) 0x05, 
            (byte) 0x48, (byte) 0x65, (byte) 0x6c, (byte) 0x6c, 
            (byte) 0x6f, (byte) 0x20, (byte) 0x57, (byte) 0x6f, 
            (byte) 0x72, (byte) 0x6c, (byte) 0x64, (byte) 0x21
        };
        OFPacketBase s = new OFPacketBase(sraw);
        OFPacketError p = new OFPacketError(s, praw);

        assertEquals("version", version, p.getVersion());
        assertEquals("type", type, p.getType());
        assertEquals("length", length, p.getLength());
        assertEquals("xid", xid, p.getXid());

        assertEquals("etype", etype, p.getEType());
        assertEquals("code", code, p.getCode());
        assertTrue("data", Arrays.equals(data, p.getData()));
    }

    @Test
    public void testToHeader(){
        byte[] raw = {
            (byte) 0x01, (byte) 0x01, (byte) 0x00, (byte) 0x18, 
            (byte) 0xab, (byte) 0xcd, (byte) 0xef, (byte) 0xab,
    
            (byte) 0x00, (byte) 0x03, (byte) 0x00, (byte) 0x05, 
    
            (byte) 0x48, (byte) 0x65, (byte) 0x6c, (byte) 0x6c, 
            (byte) 0x6f, (byte) 0x20, (byte) 0x57, (byte) 0x6f, 
            (byte) 0x72, (byte) 0x6c, (byte) 0x64, (byte) 0x21
        };

        OFPacketError p = new OFPacketError(version, type, length, xid, etype, code, data);

        assertTrue("to header", Arrays.equals(raw, p.toHeader()));
    }
}

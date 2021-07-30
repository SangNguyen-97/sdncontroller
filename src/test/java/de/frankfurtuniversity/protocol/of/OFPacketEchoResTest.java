package de.frankfurtuniversity.protocol.of;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

public class OFPacketEchoResTest {
    short version = OFPacketBase.VERSION;
    short type = OFPacketBase.OFPACKETTYPE_ECHO_RES;
    int length = 12;
    long xid = 0xababcdcdL;

    byte[] data = {
        (byte) 0x00, (byte) 0x0a, (byte) 0x00, (byte) 0x00
    };

    @Test
    public void testConstructor(){
        byte[] sraw = {
            (byte) 0x01, (byte) 0x03, (byte) 0x00, (byte) 0x0c,
            (byte) 0xab, (byte) 0xab, (byte) 0xcd, (byte) 0xcd
        };
        byte[] praw = {
            (byte) 0x00, (byte) 0x0a, (byte) 0x00, (byte) 0x00
        };
        OFPacketBase s = new OFPacketBase(sraw);
        OFPacketEchoRes p = new OFPacketEchoRes(s, praw);

        assertEquals("version", version, p.getVersion());
        assertEquals("type", type, p.getType());
        assertEquals("length", length, p.getLength());
        assertEquals("xid", xid, p.getXid());

        assertTrue("data", Arrays.equals(data, p.getData()));
    }

    @Test
    public void testToHeader(){
        byte[] raw = {
            (byte) 0x01, (byte) 0x03, (byte) 0x00, (byte) 0x0c,
            (byte) 0xab, (byte) 0xab, (byte) 0xcd, (byte) 0xcd,
            (byte) 0x00, (byte) 0x0a, (byte) 0x00, (byte) 0x00
        };

        OFPacketEchoRes p = new OFPacketEchoRes(version, type, length, xid, data);

        assertTrue("to header", Arrays.equals(raw, p.toHeader()));
    }
}

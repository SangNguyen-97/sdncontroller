package de.frankfurtuniversity.protocol.of;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

public class OFPacketFeatReqTest {
    public byte[] raw = {
        (byte) 0x01, (byte) 0x05, (byte) 0x00, (byte) 0x08, 
        (byte) 0xfa, (byte) 0x56, (byte) 0xef, (byte) 0x95
    };

    short version = OFPacketBase.VERSION;
    short type = OFPacketBase.OFPACKETTYPE_FEATURE_REQ;
    int length = 8;
    long xid = 0xfa56ef95L;

    @Test
    public void testConstructor(){
        OFPacketBase s = new OFPacketBase(raw);
        OFPacketFeatReq p = new OFPacketFeatReq(s);

        assertEquals("version", version, p.getVersion());
        assertEquals("type", type, p.getType());
        assertEquals("length", length, p.getLength());
        assertEquals("xid", xid, p.getXid());
    }

    @Test
    public void testConstructorNoArgs(){
        OFPacketFeatReq p = new OFPacketFeatReq();

        assertEquals("version", version, p.getVersion());
        assertEquals("type", type, p.getType());
        assertEquals("length", length, p.getLength());
    }

    @Test
    public void testToHeader(){
        OFPacketFeatReq p = new OFPacketFeatReq(version, type, length, xid);

        assertTrue("to header", Arrays.equals(raw, p.toHeader()));
    }
}

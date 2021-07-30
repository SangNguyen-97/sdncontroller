package de.frankfurtuniversity.protocol.of;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

import de.frankfurtuniversity.utils.exception.RawBytesTooFewException;

public class OFPacketBaseTest {
    byte[] raw = { 
        (byte) 0x01, (byte) 0x00, (byte) 0x00, (byte) 0x08, // version + type + length
        (byte) 0x00, (byte) 0x12, (byte) 0xd6, (byte) 0x87 // xid
    };
    short version = OFPacketBase.VERSION;
    short type = 0;
    int length = 8;
    long xid = 0x0012d687L;

    @Test
    public void testConstructorRaw() throws RawBytesTooFewException {
        OFPacketBase p = new OFPacketBase(raw);

        assertEquals("version", version, p.getVersion());
        assertEquals("type", type, p.getType());
        assertEquals("length", length, p.getLength());
        assertEquals("xid", xid, p.getXid());
    }

    @Test
    public void testToHeader() {
        OFPacketBase p = new OFPacketBase(version, type, length, xid);

        assertTrue("to header", Arrays.equals(raw, p.toHeader()));
    }
}

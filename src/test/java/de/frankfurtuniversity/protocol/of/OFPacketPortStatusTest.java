package de.frankfurtuniversity.protocol.of;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.frankfurtuniversity.protocol.mac.MACAddress;
import de.frankfurtuniversity.protocol.of.entities.OFPort;

public class OFPacketPortStatusTest {
    @Test
    public void testConstructor(){
        byte[] sraw = { 
            (byte) 0x01, (byte) 0x0c, (byte) 0x00, (byte) 0x40, // version + type + length
            (byte) 0x00, (byte) 0x12, (byte) 0xd6, (byte) 0x87 // xid
        };

        byte[] praw = {
            (byte) 0x02, (byte) 0x00, (byte) 0x00, (byte) 0x00, 
            (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, 

            (byte) 0xff, (byte) 0xf8, (byte) 0x11, (byte) 0x33, 
            (byte) 0x88, (byte) 0x7a, (byte) 0xf5, (byte) 0xe1, 
            (byte) 0x48, (byte) 0x65, (byte) 0x6c, (byte) 0x6c, 
            (byte) 0x6f, (byte) 0x20, (byte) 0x57, (byte) 0x6f, 
            (byte) 0x72, (byte) 0x6c, (byte) 0x64, (byte) 0x21, 
            (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, 
            (byte) 0x00, (byte) 0x02, (byte) 0x57, (byte) 0x6d, 
            (byte) 0x00, (byte) 0x0a, (byte) 0x01, (byte) 0x20, 
            (byte) 0x00, (byte) 0x0d, (byte) 0xa6, (byte) 0x4f, 
            (byte) 0x00, (byte) 0x63, (byte) 0xe5, (byte) 0x9e, 
            (byte) 0x00, (byte) 0x07, (byte) 0x17, (byte) 0x0e, 
            (byte) 0x00, (byte) 0x07, (byte) 0x1a, (byte) 0x39
        };

        short version = OFPacketBase.VERSION;
        short type = OFPacketBase.OFPACKETTYPE_PORT_STATUS;
        int length = 64;
        long xid = 0x0012d687L;

        byte reason = OFPacketPortStatus.REASON_MODIFY; // 8 bits and few possible value => byte

        int portId = 0xfff8; // 16 bits
        MACAddress hw_addr = new MACAddress("11:33:88:7A:f5:e1"); // 6 * 8 bits
        String name = "Hello World!"; // 16 * 8 bits
        int config = 153453; // 32 bits
        int state = 655648; // 32 bits;
        int curr = 894543; // 32 bits
        int advertised = 6546846; // 32 bits
        int supported = 464654; // 32 bits
        int peer = 465465; // 32 bits

        OFPacketBase s = new OFPacketBase(sraw);
        OFPacketPortStatus p = new OFPacketPortStatus(s,praw);

        assertEquals("version", version, p.getVersion());
        assertEquals("type", type, p.getType());
        assertEquals("length", length, p.getLength());
        assertEquals("xid", xid, p.getXid());

        assertEquals("reason", reason, p.getReason());
        assertTrue("port", p.getPort().equals(new OFPort(portId, hw_addr, name, config, state, curr, advertised, supported, peer)));
    }
}

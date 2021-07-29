package de.frankfurtuniversity.protocol.of.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

import de.frankfurtuniversity.protocol.mac.MACAddress;

public class OFPortTest {
    byte[] raw = {
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
    int portId = 0xfff8; // 16 bits
    MACAddress hw_addr = new MACAddress("11:33:88:7A:f5:e1"); // 6 * 8 bits
    String name = "Hello World!"; // 16 * 8 bits
    int config = 153453; // 32 bits
    int state = 655648; // 32 bits;
    int curr = 894543; // 32 bits
    int advertised = 6546846; // 32 bits
    int supported = 464654; // 32 bits
    int peer = 465465; // 32 bits

    @Test
    public void testConstructor(){
        OFPort p = new OFPort(raw);

        assertEquals("port_id", portId, p.getPortId());
        assertTrue("hw_addr", hw_addr.equals(p.getHWAddr()));
        assertTrue("name", name.equals(p.getName()));
        assertEquals("config", config, p.getConfig());
        assertEquals("state", state, p.getState());
        assertEquals("curr", curr, p.getCurr());
        assertEquals("advertised", advertised, p.getAdvertised());
        assertEquals("supported", supported, p.getSupported());
        assertEquals("peer", peer, p.getPeer());
    }

    @Test
    public void testToHeader(){
        OFPort p = new OFPort(portId, hw_addr, name, config, state, curr, advertised, supported, peer);

        assertTrue("to header", Arrays.equals(raw, p.toHeader()));
    }
}

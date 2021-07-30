package de.frankfurtuniversity.protocol.of;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.frankfurtuniversity.protocol.mac.MACAddress;
import de.frankfurtuniversity.protocol.of.entities.OFPort;

public class OFPacketFeatResTest {
    long datapath_id = 12345678910111213L;
    long n_buffers = (long)1563053260;
    short n_tbls = (short) 250;
    int capabilities = 987654321;
    int actions = 564489432;
    @Test
    public void testConstructorWithoutPorts(){
        
        byte[] sraw = {
            // OF Header
            (byte) 0x01, (byte) 0x00, (byte) 0x00, (byte) 0x32,
            (byte) 0x00, (byte) 0x12, (byte) 0xd6, (byte) 0x87
        };
        byte[] praw = {
            // OF feature response
            (byte) 0x00, (byte) 0x2b, (byte) 0xdc, (byte) 0x54, // datapath_id
            (byte) 0x5d, (byte) 0xf2, (byte) 0xbd, (byte) 0xed,
            (byte) 0x5d, (byte) 0x2a, (byte) 0x4c, (byte) 0xcc, // n_buffers
            (byte) 0xfa, (byte) 0x00, (byte) 0x00, (byte) 0x00, // n_tbls + padding
            (byte) 0x3a, (byte) 0xde, (byte) 0x68, (byte) 0xb1, // capabilities
            (byte) 0x21, (byte) 0xa5, (byte) 0x6c, (byte) 0xd8, // actions
        };
        OFPacketBase s = new OFPacketBase(sraw);
        OFPacketFeatRes p = new OFPacketFeatRes(s,praw);

        assertEquals("datapath_id",datapath_id, p.getDatapathId());
        assertEquals("n_buffers", n_buffers, p.n_buffers);
        assertEquals("n_tbls", n_tbls, p.n_tbls);
        assertEquals("capabilities", capabilities, p.capabilities);
        assertEquals("actions", actions, p.actions);
    }

    @Test
    public void testConstructorWithPorts(){
        byte[] sraw = {
            // OF Header
            (byte) 0x01, (byte) 0x00, (byte) 0x00, (byte) 0x80, // version + type + length
            (byte) 0x00, (byte) 0x12, (byte) 0xd6, (byte) 0x87 // xid
        };
        byte[] praw = {
            // OF feature response
            (byte) 0x00, (byte) 0x2b, (byte) 0xdc, (byte) 0x54, // datapath_id
            (byte) 0x5d, (byte) 0xf2, (byte) 0xbd, (byte) 0xed,
            (byte) 0x5d, (byte) 0x2a, (byte) 0x4c, (byte) 0xcc, // n_buffers
            (byte) 0xfa, (byte) 0x00, (byte) 0x00, (byte) 0x00, // n_tbls + padding
            (byte) 0x3a, (byte) 0xde, (byte) 0x68, (byte) 0xb1, // capabilities
            (byte) 0x21, (byte) 0xa5, (byte) 0x6c, (byte) 0xd8, // actions
            // OF ports
            // port 1
            (byte) 0x19, (byte) 0xec, (byte) 0xaa, (byte) 0xbb, // port_id + hw_addr
            (byte) 0xcc, (byte) 0xdd, (byte) 0xee, (byte) 0xff,
            (byte) 0x48, (byte) 0x65, (byte) 0x6c, (byte) 0x6c, // Name in ASCII
            (byte) 0x6f, (byte) 0x20, (byte) 0x57, (byte) 0x6f,
            (byte) 0x72, (byte) 0x6c, (byte) 0x64, (byte) 0x21,
            (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
            (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x02, // config
            (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x01, // state
            (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x01, // curr
            (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x10, // advertised
            (byte) 0x00, (byte) 0x00, (byte) 0x04, (byte) 0x00, // supported
            (byte) 0x00, (byte) 0x00, (byte) 0x08, (byte) 0x00, // peer
            // port 2
            (byte) 0xab, (byte) 0xcd, (byte) 0xaa, (byte) 0x11, // port_id + hw_addr
            (byte) 0xcc, (byte) 0x22, (byte) 0xee, (byte) 0x33,
            (byte) 0x48, (byte) 0x65, (byte) 0x6c, (byte) 0x6c, // Name in ASCII
            (byte) 0x6f, (byte) 0x20, (byte) 0x57, (byte) 0x6f,
            (byte) 0x72, (byte) 0x6c, (byte) 0x64, (byte) 0x00,
            (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
            (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x02, // config
            (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x01, // state
            (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x01, // curr
            (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x10, // advertised
            (byte) 0x00, (byte) 0x00, (byte) 0x04, (byte) 0x00, // supported
            (byte) 0x00, (byte) 0x00, (byte) 0x08, (byte) 0x00, // peer
        };
        OFPacketBase s = new OFPacketBase(sraw);
        OFPacketFeatRes p = new OFPacketFeatRes(s,praw);

        int port_id_1 = 6636;
        MACAddress hw_addr_1 = new MACAddress("AA:BB:CC:DD:EE:FF");
        String name_1 = "Hello World!";
        int config_1 = 2;
        int state_1 = 1;
        int curr_1 = 1;
        int advertised_1 = 16;
        int supported_1 = 1024;
        int peer_1 = 2048;

        int port_id_2 = 0xabcd;
        MACAddress hw_addr_2 = new MACAddress("AA:11:CC:22:EE:33");
        String name_2 = "Hello World";
        int config_2 = 2;
        int state_2 = 1;
        int curr_2 = 1;
        int advertised_2 = 16;
        int supported_2 = 1024;
        int peer_2 = 2048;

        
        assertEquals("datapath_id",datapath_id, p.datapath_id);
        assertEquals("n_buffers", n_buffers, p.n_buffers);
        assertEquals("n_tbls", n_tbls, p.n_tbls);
        assertEquals("capabilities", capabilities, p.capabilities);
        assertEquals("actions", actions, p.actions);

        OFPort port = p.getPorts().get(6636);

        assertEquals("port_id",port_id_1, port.getPortId());
        assertTrue("hw_addr",hw_addr_1.equals(port.getHWAddr()));
        assertTrue("name", name_1.equals(port.getName()));
        assertEquals("config",config_1, port.getConfig());
        assertEquals("state",state_1, port.getState());
        assertEquals("curr",curr_1, port.getCurr());
        assertEquals("advertised",advertised_1, port.getAdvertised());
        assertEquals("supported",supported_1, port.getSupported());
        assertEquals("peer",peer_1, port.getPeer());

        OFPort port2 = p.getPorts().get(0xabcd);

        assertEquals("port_id",port_id_2, port2.getPortId());
        assertTrue("hw_addr",hw_addr_2.equals(port2.getHWAddr()));
        assertTrue("name", name_2.equals(port2.getName()));
        assertEquals("config",config_2, port2.getConfig());
        assertEquals("state",state_2, port2.getState());
        assertEquals("curr",curr_2, port2.getCurr());
        assertEquals("advertised",advertised_2, port2.getAdvertised());
        assertEquals("supported",supported_2, port2.getSupported());
        assertEquals("peer",peer_2, port2.getPeer());

    }
}

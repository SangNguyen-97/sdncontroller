package de.frankfurtuniversity.protocol.of;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.frankfurtuniversity.protocol.of.entities.OFPort;

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

    @Test
    public void testConstructorWithPorts(){
        byte[] raw = {
            // OF Header
            (byte) 0x01, (byte) 0x00, (byte) 0x00, (byte) 0x50, // version + type + length
            (byte) 0x00, (byte) 0x12, (byte) 0xd6, (byte) 0x87, // xid
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
        };

        OFPacketFeatRes p = new OFPacketFeatRes(raw);

        
        assertEquals("datapath_id",12345678910111213L, p.datapath_id);
        assertEquals("n_buffers",(long) 1563053260, p.n_buffers);
        assertEquals("n_tbls",(short) 250, p.n_tbls);
        assertEquals("capabilities",987654321, p.capabilities);
        assertEquals("actions",564489432, p.actions);

        OFPort port = p.ports.get(6636);

        assertEquals("port_id",6636, port.getPortId());
        assertTrue("hw_addr","AA:BB:CC:DD:EE:FF".equals(port.getHWAddr().toString()));
        assertTrue("name", "Hello World!".equals(port.getName()));
        // System.out.println(port.getName() + "-----" +"Hello World!");
        assertEquals("config",2, port.getConfig());
        assertEquals("state",1, port.getState());
        assertEquals("curr",1, port.getCurr());
        assertEquals("advertised",16, port.getAdvertised());
        assertEquals("supported",1024, port.getSupported());
        assertEquals("peer",2048, port.getPeer());
    }
}

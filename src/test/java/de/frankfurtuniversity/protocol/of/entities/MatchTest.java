package de.frankfurtuniversity.protocol.of.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.junit.Test;

public class MatchTest {
    @Test
    public void testConstructorByte() throws UnknownHostException{
        byte[] raw = {
            (byte) 0x00, (byte) 0x0c, (byte) 0x11, (byte) 0x22, // wildcards
            (byte) 0x00, (byte) 0x0c, (byte) 0x11, (byte) 0x22, // in_port + dl_src
            (byte) 0x33, (byte) 0x44, (byte) 0x55, (byte) 0x66, 
            (byte) 0x77, (byte) 0x77, (byte) 0x88, (byte) 0x99, // dl_dst
            (byte) 0x00, (byte) 0x11, (byte) 0x0e, (byte) 0x10, // dl_dst + dl_vlan
            (byte) 0xae, (byte) 0x00, (byte) 0xaa, (byte) 0xaa, // dl_pcp + dl_type
            (byte) 0xaa, (byte) 0xaa, (byte) 0x00, (byte) 0x00, // nw_tos + nw_prot
            (byte) 0xc0, (byte) 0xa8, (byte) 0x00, (byte) 0x01, // nw_src
            (byte) 0xc0, (byte) 0xa8, (byte) 0x00, (byte) 0x02, // nw_dst
            (byte) 0x19, (byte) 0xec, (byte) 0x19, (byte) 0xec, // tp_src + tp_dst
        };

        Match m = new Match(raw);

        assertEquals("wildcards", 760818L, m.getWildcards());
        assertEquals("in_port", 12,m.getIn_port());
        assertTrue("dl_src", m.getDl_src().toString().equals("11:22:33:44:55:66"));
        assertTrue("dl_dst", m.getDl_dst().toString().equals("77:77:88:99:00:11"));
        assertEquals("dl_vlan", 3600, m.getDl_vlan());
        assertEquals("dl_pcp", 174, m.getDl_pcp());
        assertEquals("dl_type", 43690, m.getDl_type());
        assertEquals("nw_tos", 170, m.getNw_tos());
        assertEquals("nw_prot", 170, m.getNw_proto());
        assertTrue("nw_src", m.getNw_src().equals(InetAddress.getByName("192.168.0.1")));
        assertTrue("nw_dst", m.getNw_dst().equals(InetAddress.getByName("192.168.0.2")));
        assertEquals("tp_src", 6636, m.getTp_src());
        assertEquals("tp_dst", 6636, m.getTp_dst());
    }
}

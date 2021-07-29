package de.frankfurtuniversity.protocol.of.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import de.frankfurtuniversity.protocol.mac.MACAddress;

public class MatchTest {
    byte[] raw = { (byte) 0x00, (byte) 0x0b, (byte) 0x9b, (byte) 0xf2, // wildcards
            (byte) 0x00, (byte) 0x0c, (byte) 0x11, (byte) 0x22, // in_port + dl_src
            (byte) 0x33, (byte) 0x44, (byte) 0x55, (byte) 0x66, // dl_src
            (byte) 0x77, (byte) 0x77, (byte) 0x88, (byte) 0x99, // dl_dst
            (byte) 0x00, (byte) 0x11, (byte) 0x0e, (byte) 0x10, // dl_dst + dl_vlan
            (byte) 0xae, (byte) 0x00, (byte) 0xaa, (byte) 0xaa, // dl_pcp + dl_type
            (byte) 0xaa, (byte) 0xaa, (byte) 0x00, (byte) 0x00, // nw_tos + nw_prot
            (byte) 0xc0, (byte) 0xa8, (byte) 0x00, (byte) 0x01, // nw_src
            (byte) 0xc0, (byte) 0xa8, (byte) 0x00, (byte) 0x02, // nw_dst
            (byte) 0x19, (byte) 0xec, (byte) 0x19, (byte) 0xec, // tp_src + tp_dst
    };
    long wildcards = 760818L;
    int in_port = 12;
    MACAddress dl_src = new MACAddress("11:22:33:44:55:66");
    MACAddress dl_dst = new MACAddress("77:77:88:99:00:11");
    int dl_vlan = 3600;
    short dl_pcp = 174;
    int dl_type = 43690;
    short nw_tos = 170;
    short nw_proto = 170;
    InetAddress nw_src;
    InetAddress nw_dst;
    int tp_src = 6636;
    int tp_dst = 6636;

    @Before
    public void init() throws UnknownHostException {
        nw_src = InetAddress.getByName("192.168.0.1");
        nw_dst = InetAddress.getByName("192.168.0.2");
    }

    @Test
    public void testConstructorByte() throws UnknownHostException {
        Match m = new Match(raw);

        assertEquals("wildcards", wildcards, m.getWildcards());
        assertEquals("in_port", in_port, m.getIn_port());
        assertTrue("dl_src", m.getDl_src().equals(dl_src));
        assertTrue("dl_dst", m.getDl_dst().equals(dl_dst));
        assertEquals("dl_vlan", dl_vlan, m.getDl_vlan());
        assertEquals("dl_pcp", dl_pcp, m.getDl_pcp());
        assertEquals("dl_type", dl_type, m.getDl_type());
        assertEquals("nw_tos", nw_tos, m.getNw_tos());
        assertEquals("nw_prot", nw_proto, m.getNw_proto());
        assertTrue("nw_src", m.getNw_src().equals(nw_src));
        assertTrue("nw_dst", m.getNw_dst().equals(nw_dst));
        assertEquals("tp_src", tp_src, m.getTp_src());
        assertEquals("tp_dst", tp_dst, m.getTp_dst());
    }

    @Test
    public void testToHeader() {
        Match m = new Match(wildcards, in_port, dl_src, dl_dst, dl_vlan, dl_pcp, dl_type, nw_tos, nw_proto, nw_src,
                nw_dst, tp_src, tp_dst);

        assertTrue("to header", Arrays.equals(m.toHeader(), raw));
    }
}

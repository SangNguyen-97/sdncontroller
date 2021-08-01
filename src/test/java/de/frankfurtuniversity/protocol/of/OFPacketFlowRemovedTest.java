package de.frankfurtuniversity.protocol.of;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.Arrays;

import org.junit.Test;

import de.frankfurtuniversity.protocol.mac.MACAddress;

public class OFPacketFlowRemovedTest {
    @Test
    public void testConstructor() throws UnknownHostException{
        byte[] sraw = { 
            (byte) 0x01, (byte) 0x0b, (byte) 0x00, (byte) 0x58, // version + type + length
            (byte) 0x00, (byte) 0x12, (byte) 0xd6, (byte) 0x87 // xid
        };

        byte[] praw = {
            (byte) 0x00, (byte) 0x0b, (byte) 0x9b, (byte) 0xf2, // wildcards
            (byte) 0x00, (byte) 0x0c, (byte) 0x11, (byte) 0x22, // in_port + dl_src
            (byte) 0x33, (byte) 0x44, (byte) 0x55, (byte) 0x66, // dl_src
            (byte) 0x77, (byte) 0x77, (byte) 0x88, (byte) 0x99, // dl_dst
            (byte) 0x00, (byte) 0x11, (byte) 0x0e, (byte) 0x10, // dl_dst + dl_vlan
            (byte) 0xae, (byte) 0x00, (byte) 0xaa, (byte) 0xaa, // dl_pcp + dl_type
            (byte) 0xaa, (byte) 0xaa, (byte) 0x00, (byte) 0x00, // nw_tos + nw_prot
            (byte) 0xc0, (byte) 0xa8, (byte) 0x00, (byte) 0x01, // nw_src
            (byte) 0xc0, (byte) 0xa8, (byte) 0x00, (byte) 0x02, // nw_dst
            (byte) 0x19, (byte) 0xec, (byte) 0x19, (byte) 0xec, // tp_src + tp_dst

            (byte) 0x19, (byte) 0xec, (byte) 0x19, (byte) 0xec, // cookie
            (byte) 0x19, (byte) 0xec, (byte) 0x19, (byte) 0xec,

            (byte) 0x03, (byte) 0xe8, (byte) 0x02, (byte) 0x00, // priority + reason
            (byte) 0x19, (byte) 0xec, (byte) 0x19, (byte) 0xec, // duration_sec
            (byte) 0x19, (byte) 0xec, (byte) 0x19, (byte) 0xec, // duration_nsec
            (byte) 0x3a, (byte) 0x93, (byte) 0x00, (byte) 0x00, // idle_timeout

            (byte) 0x19, (byte) 0xec, (byte) 0x19, (byte) 0xec, // packet_count
            (byte) 0x36, (byte) 0xef, (byte) 0xa9, (byte) 0xed,

            (byte) 0x19, (byte) 0xec, (byte) 0x19, (byte) 0xec, // byte_count
            (byte) 0x51, (byte) 0xa3, (byte) 0x4d, (byte) 0xec,
        };

        short version = OFPacketBase.VERSION;
        short type = OFPacketBase.OFPACKETTYPE_FLOW_RMD;
        int length = OFPacketFlowRemoved.SIZE;
        long xid = 0x0012d687L;

        long wildcards = 760818L;
        int in_port = 12;
        MACAddress dl_src = new MACAddress("11:22:33:44:55:66");
        MACAddress dl_dst = new MACAddress("77:77:88:99:00:11");
        int dl_vlan = 3600;
        short dl_pcp = 174;
        int dl_type = 43690;
        short nw_tos = 170;
        short nw_proto = 170;
        InetAddress nw_src = InetAddress.getByName("192.168.0.1");
        InetAddress nw_dst = InetAddress.getByName("192.168.0.2");
        int tp_src = 6636;
        int tp_dst = 6636;

        long cookie = 0x19ec19ec19ec19ecL; // 64 bits
        int priority = 0x03e8; // 16 bits
        short reason = OFPacketFlowRemoved.REASON_DELETE; // 8 bits
        // padding 8 bits
        long duration_sec = 0x19ec19ecL; // 32 bits
        long duration_nsec = 0x19ec19ecL; // 32 bits
    
        int idle_timeout = 0x3a93; // 16 bits
        // padding 16 bits
        
        int offset = 64;
        ByteBuffer buff = ByteBuffer.allocate(8 + 1);
        buff.put((byte) 0);
        buff.put(Arrays.copyOfRange(praw, offset, offset + 8));
        buff.flip();
        BigInteger packet_count = new BigInteger(Arrays.copyOf(buff.array(), buff.array().length));
        offset = offset + 8;
        buff.clear();

        buff.put((byte) 0);
        buff.put(Arrays.copyOfRange(praw, offset, offset + 8));
        buff.flip();
        BigInteger byte_count = new BigInteger(Arrays.copyOf(buff.array(), buff.array().length));

        OFPacketBase s = new OFPacketBase(sraw);
        OFPacketFlowRemoved p = new OFPacketFlowRemoved(s, praw);

        assertEquals("version", version, p.getVersion());
        assertEquals("type", type, p.getType());
        assertEquals("length", length, p.getLength());
        assertEquals("xid", xid, p.getXid());


        assertEquals("wildcards", wildcards, p.getMatch().getWildcards());
        assertEquals("in_port", in_port, p.getMatch().getIn_port());
        assertTrue("dl_src", p.getMatch().getDl_src().equals(dl_src));
        assertTrue("dl_dst", p.getMatch().getDl_dst().equals(dl_dst));
        assertEquals("dl_vlan", dl_vlan, p.getMatch().getDl_vlan());
        assertEquals("dl_pcp", dl_pcp, p.getMatch().getDl_pcp());
        assertEquals("dl_type", dl_type, p.getMatch().getDl_type());
        assertEquals("nw_tos", nw_tos, p.getMatch().getNw_tos());
        assertEquals("nw_prot", nw_proto, p.getMatch().getNw_proto());
        assertTrue("nw_src", p.getMatch().getNw_src().equals(nw_src));
        assertTrue("nw_dst", p.getMatch().getNw_dst().equals(nw_dst));
        assertEquals("tp_src", tp_src, p.getMatch().getTp_src());
        assertEquals("tp_dst", tp_dst, p.getMatch().getTp_dst());

        assertEquals("cookie", cookie, p.getCookie());
        assertEquals("priority", priority, p.getPriority());
        assertEquals("reason", reason, p.getReason());
        assertEquals("duration_sec", duration_sec, p.getDurationSec());
        assertEquals("duration_nsec", duration_nsec, p.getDurationNsec());
        assertEquals("idle_timeout", idle_timeout, p.getIdleTimeout());

        assertTrue("packet_count", packet_count.equals(p.getPacketCount()));
        assertTrue("byte_count", byte_count.equals(p.getByteCount()));
    }
}

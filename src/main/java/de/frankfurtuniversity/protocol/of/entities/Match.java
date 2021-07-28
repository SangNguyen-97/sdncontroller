package de.frankfurtuniversity.protocol.of.entities;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

import de.frankfurtuniversity.protocol.mac.MACAddress;
import de.frankfurtuniversity.protocol.of.Utility;

public class Match {
    public static int SIZE = 40; // in bytes

    long wildcards; // 32 bits
    int in_port; // 16 bits
    MACAddress dl_src; // 48 bits
    MACAddress dl_dst; // 48 bits
    int dl_vlan; // 16 bits
    short dl_pcp; // 8 bits
    // padding 8 bits
    int dl_type; // 16 bits
    short nw_tos; // 8 bits
    short nw_proto; // 8 bits
    // padding 16 bits
    InetAddress nw_src; // 32 bits
    InetAddress nw_dst; // 32 bits
    int tp_src; // 16 bits
    int tp_dst; // 16 bits

    public Match(byte[] raw) throws UnknownHostException{
        int offset = 0;

        this.wildcards = Utility.bytesToLong(raw, offset);
        offset = offset + Long.BYTES;
        this.in_port = Utility.bytesToInt(raw, offset);
        offset = offset + Integer.BYTES;
        this.dl_src = new MACAddress(Arrays.copyOfRange(raw, offset, offset + MACAddress.SIZE));
        offset = offset + MACAddress.SIZE;
        this.dl_dst = new MACAddress(Arrays.copyOfRange(raw, offset, offset + MACAddress.SIZE));
        offset = offset + MACAddress.SIZE;
        this.dl_vlan = Utility.bytesToInt(raw, offset);
        offset = offset + Integer.BYTES;
        this.dl_pcp = Utility.bytesToShort(raw, offset);
        offset = offset + Short.BYTES;

        offset = offset + 1; // padding 8 bits

        this.dl_type = Utility.bytesToInt(raw, offset);
        offset = offset + Integer.BYTES;
        this.nw_tos = Utility.bytesToShort(raw, offset);
        offset = offset + Short.BYTES;
        this.nw_proto = Utility.bytesToShort(raw, offset);
        offset = offset + Short.BYTES;
        
        offset = offset + 2; // padding 16 bits

        this.nw_src = InetAddress.getByAddress(Arrays.copyOfRange(raw, offset, offset + 4)); // IPv4 address = 4 bytes
        offset = offset + 4;
        this.nw_dst = InetAddress.getByAddress(Arrays.copyOfRange(raw, offset, offset + 4)); // IPv4 address = 4 bytes
        offset = offset + 4;
        this.tp_src = Utility.bytesToInt(raw, offset);
        offset = offset + Integer.BYTES;
        this.tp_dst = Utility.bytesToInt(raw, offset);
        offset = offset + Integer.BYTES;

    }

    public long getWildcards() {
        return wildcards;
    }

    public int getIn_port() {
        return in_port;
    }

    public MACAddress getDl_src() {
        return dl_src;
    }

    public MACAddress getDl_dst() {
        return dl_dst;
    }

    public int getDl_vlan() {
        return dl_vlan;
    }

    public short getDl_pcp() {
        return dl_pcp;
    }

    public int getDl_type() {
        return dl_type;
    }

    public short getNw_tos() {
        return nw_tos;
    }

    public short getNw_proto() {
        return nw_proto;
    }

    public InetAddress getNw_src() {
        return nw_src;
    }

    public InetAddress getNw_dst() {
        return nw_dst;
    }

    public int getTp_src() {
        return tp_src;
    }

    public int getTp_dst() {
        return tp_dst;
    }
}

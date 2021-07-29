package de.frankfurtuniversity.protocol.of.entities;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.Arrays;

import de.frankfurtuniversity.protocol.mac.MACAddress;
import de.frankfurtuniversity.utils.exception.RawBytesTooFewException;

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

    public Match(byte[] raw) throws UnknownHostException, RawBytesTooFewException {
        if (raw.length < SIZE)
            throw new RawBytesTooFewException();
        else {
            int offset = 0;

            this.wildcards = (((long) raw[offset++] & 0xff) << 24) + (((long) raw[offset++] & 0xff) << 16)
                    + (((long) raw[offset++] & 0xff) << 8) + ((long) raw[offset++] & 0xff);
            this.in_port = (((int) raw[offset++] & 0xff) << 8) + ((int) raw[offset++] & 0xff);
            this.dl_src = new MACAddress(Arrays.copyOfRange(raw, offset, offset + MACAddress.SIZE));
            offset = offset + MACAddress.SIZE;
            this.dl_dst = new MACAddress(Arrays.copyOfRange(raw, offset, offset + MACAddress.SIZE));
            offset = offset + MACAddress.SIZE;
            this.dl_vlan = (((int) raw[offset++] & 0xff) << 8) + ((int) raw[offset++] & 0xff);
            this.dl_pcp = (short) ((int) raw[offset++] & 0xff);

            offset = offset + 1; // padding 8 bits

            this.dl_type = (((int) raw[offset++] & 0xff) << 8) + ((int) raw[offset++] & 0xff);
            this.nw_tos = (short) ((int) raw[offset++] & 0xff);
            this.nw_proto = (short) ((int) raw[offset++] & 0xff);

            offset = offset + 2; // padding 16 bits

            this.nw_src = InetAddress.getByAddress(Arrays.copyOfRange(raw, offset, offset + 4)); // IPv4 address = 4
                                                                                                 // bytes
            offset = offset + 4;
            this.nw_dst = InetAddress.getByAddress(Arrays.copyOfRange(raw, offset, offset + 4)); // IPv4 address = 4
                                                                                                 // bytes
            offset = offset + 4;
            this.tp_src = (((int) raw[offset++] & 0xff) << 8) + ((int) raw[offset++] & 0xff);
            this.tp_dst = (((int) raw[offset++] & 0xff) << 8) + ((int) raw[offset++] & 0xff);
        }
    }

    public Match(long wildcards, int in_port, MACAddress dl_src, MACAddress dl_dst, int dl_vlan, short dl_pcp,
            int dl_type, short nw_tos, short nw_proto, InetAddress nw_src, InetAddress nw_dst, int tp_src, int tp_dst) {
        this.wildcards = wildcards;
        this.in_port = in_port;
        this.dl_src = dl_src;
        this.dl_dst = dl_dst;
        this.dl_vlan = dl_vlan;
        this.dl_pcp = dl_pcp;
        this.dl_type = dl_type;
        this.nw_tos = nw_tos;
        this.nw_proto = nw_proto;
        this.nw_src = nw_src;
        this.nw_dst = nw_dst;
        this.tp_src = tp_src;
        this.tp_dst = tp_dst;
    }

    public String toString() {
        return "match wildcards: " + this.wildcards + "  in_port: " + this.in_port + "  dl_src: " + this.dl_src
                + "  dl_dst: " + this.dl_dst + "  dl_vlan: " + this.dl_vlan + "  dl_pcp: " + this.dl_pcp + "  dl_type: "
                + this.dl_type + "  nw_tos: " + this.nw_tos + "  nw_proto: " + this.nw_proto + "  nw_src: "
                + this.nw_proto + "  nw_src: " + this.nw_src + "  nw_dst: " + this.nw_dst + "  tp_src: " + this.tp_src
                + "  tp_dst: " + this.tp_dst;
    }

    public byte[] toHeader() {
        ByteBuffer buff = ByteBuffer.allocate(SIZE);
        buff.put((byte) (this.wildcards >> 24));
        buff.put((byte) (this.wildcards >> 16));
        buff.put((byte) (this.wildcards >> 8));
        buff.put((byte) this.wildcards);
        buff.put((byte) (this.in_port >> 8));
        buff.put((byte) this.in_port);
        buff.put(this.dl_src.getAddressBytes());
        buff.put(this.dl_dst.getAddressBytes());
        buff.put((byte) (this.dl_vlan >> 8));
        buff.put((byte) this.dl_vlan);
        buff.put((byte) this.dl_pcp);
        buff.put((byte) 0);
        buff.put((byte) (this.dl_type >> 8));
        buff.put((byte) this.dl_type);
        buff.put((byte) this.nw_tos);
        buff.put((byte) this.nw_proto);
        buff.put((byte) 0);
        buff.put((byte) 0);
        buff.put(this.nw_src.getAddress());
        buff.put(this.nw_dst.getAddress());
        buff.put((byte) (this.tp_src >> 8));
        buff.put((byte) this.tp_src);
        buff.put((byte) (this.tp_dst >> 8));
        buff.put((byte) this.tp_dst);
        buff.flip();
        return buff.array();
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

package de.frankfurtuniversity.protocol.of;

import de.frankfurtuniversity.utils.exception.RawBytesTooFewException;

public class OFPacketBase {
    public static final int SIZE = 8; // in bytes

    public static final short VERSION = 1;

    public static final short OFPACKETTYPE_HELLO = 0;
    public static final short OFPACKETTYPE_ERROR = 1;
    public static final short OFPACKETTYPE_ECHO_REQ = 2;
    public static final short OFPACKETTYPE_ECHO_RES = 3;
    public static final short OFPACKETTYPE_FEATURE_REQ = 5;
    public static final short OFPACKETTYPE_FEATURE_RES = 6;
    public static final short OFPACKETTYPE_PACKET_IN = 10;
    public static final short OFPACKETTYPE_FLOW_RMD = 11;
    public static final short OFPACKETTYPE_PORT_STATUS = 12;
    public static final short OFPACKETTYPE_PACKET_OUT = 13;
    public static final short OFPACKETTYPE_FLOW_MOD = 14;

    short version = VERSION; // 8 bits
    short type; // 8 bits
    int length; // 16 bits
    long xid; // 32 bits

    public OFPacketBase(byte[] raw) throws RawBytesTooFewException {
        if (raw.length < SIZE) {
            throw new RawBytesTooFewException();
        } else {
            this.version = (short) raw[0];
            this.type = (short) raw[1];
            this.length = (((int) raw[2] & 0xFF) << 8) + ((int) raw[3] & 0xFF);
            this.xid = (((long) raw[4] & 0xFF) << 24) + (((long) raw[5] & 0xFF) << 16) + (((long) raw[6] & 0xFF) << 8)
                    + ((long) raw[7] & 0xFF);
        }
    }

    // Needed for initiation of subclasses
    public OFPacketBase(short version, short type, int length, long xid) {
        this.version = version;
        this.type = type;
        this.length = length;
        this.xid = xid;
    }

    public byte[] toHeader() {
        byte[] ret = new byte[SIZE];

        ret[0] = (byte) version;
        ret[1] = (byte) type;
        ret[2] = (byte) (length >> 8);
        ret[3] = (byte) length;
        ret[4] = (byte) (xid >> 24);
        ret[5] = (byte) (xid >> 16);
        ret[6] = (byte) (xid >> 8);
        ret[7] = (byte) xid;

        return ret;
    }

    public String toString() {
        return "OFPacketBase version: " + this.version + "   type: " + this.type + "   length: " + this.length
                + "   xid: " + this.xid;
    }

    public short getVersion() {
        return this.version;
    }

    public short getType() {
        return this.type;
    }

    public int getLength() {
        return this.length;
    }

    public long getXid() {
        return this.xid;
    }

    public boolean equals(OFPacketBase p) {
        if (p.version != this.version)
            return false;
        else if (p.type != this.type)
            return false;
        else if (p.length != this.length)
            return false;
        else if (p.xid != this.xid)
            return false;
        return true;
    }
}

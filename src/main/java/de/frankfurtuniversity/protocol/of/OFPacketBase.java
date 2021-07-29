package de.frankfurtuniversity.protocol.of;

import de.frankfurtuniversity.utils.exception.RawBytesTooFewException;

public class OFPacketBase {
    public static final int SIZE = 8; // in bytes

    short version; // 8 bits
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

    public OFPacketBase(short version, short type, int length, long xid) {
        this.version = version;
        this.type = type;
        this.length = length;
        this.xid = xid;
    }

    public byte[] toHeader() {
        byte[] ret = new byte[SIZE];

        ret[0] = (byte) (version & 0xFF);
        ret[1] = (byte) (type & 0xFF);
        ret[2] = (byte) ((length >> 8) & 0xFF);
        ret[3] = (byte) (length & 0xFF);
        ret[4] = (byte) ((xid >> 24) & 0xFF);
        ret[5] = (byte) ((xid >> 16) & 0xFF);
        ret[6] = (byte) ((xid >> 8) & 0xFF);
        ret[7] = (byte) (xid & 0xFF);

        return ret;
    }

    public String toString() {
        return "version: " + this.version + "   type: " + this.type + "   length: " + this.length + "   xid: "
        + this.xid;
    }

    public short getVersion(){
        return this.version;
    }

    public short getType(){
        return this.type;
    }

    public int getLength(){
        return this.length;
    }

    public long getXid(){
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

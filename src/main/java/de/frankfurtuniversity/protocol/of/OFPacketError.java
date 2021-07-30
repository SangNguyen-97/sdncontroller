package de.frankfurtuniversity.protocol.of;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import de.frankfurtuniversity.utils.exception.RawBytesTooFewException;

public class OFPacketError extends OFPacketBase {
    public static final int MIN_SIZE = OFPacketBase.SIZE + 4; // in bytes

    public static final int ETYPE_HELLOFAILED = 0;
    public static final int ETYPE_BADREQUEST = 1;
    public static final int ETYPE_BADACTION = 2;
    public static final int ETYPE_FLOWMODFAILED = 3;
    public static final int ETYPE_PORTMODFAILED = 4;
    public static final int ETYPE_QUEUEOPFAILED = 5;

    int etype; // 16 bits
    int code; // 16 bits
    byte[] data; // variable

    public OFPacketError(OFPacketBase s, byte[] raw) throws RawBytesTooFewException {
        super(s.getVersion(), s.getType(), s.getLength(), s.getXid());
        if (raw.length < (MIN_SIZE - OFPacketBase.SIZE))
            throw new RawBytesTooFewException();
        else {
            int offset = 0;
            this.etype = (((int) raw[offset++] & 0xff) << 8) + ((int) raw[offset++] & 0xff);
            this.code = (((int) raw[offset++] & 0xff) << 8) + ((int) raw[offset++] & 0xff);
            int datalen = super.getLength() - MIN_SIZE;
            ByteBuffer buff = ByteBuffer.allocate(datalen);
            for (int i = 0; i < datalen; i++) {
                buff.put(raw[offset++]);
            }
            buff.flip();
            this.data = buff.array();
        }
    }

    public OFPacketError(short version, short type, int length, long xid, int etype, int code, byte[] data) {
        super(version, type, length, xid);
        this.etype = etype;
        this.code = code;
        this.data = Arrays.copyOf(data, data.length);
    }

    public byte[] toHeader() {
        ByteBuffer buff = ByteBuffer.allocate(this.length);
        buff.put(super.toHeader());
        buff.put((byte) (etype >> 8));
        buff.put((byte) etype);
        buff.put((byte) (code >> 8));
        buff.put((byte) code);
        buff.put(data);
        buff.flip();
        return buff.array();
    }

    public String toString() {
        return super.toString() + "    OFPacketError etype: " + this.etype + "   code: " + this.code + "   data: "
                + new String(this.data, StandardCharsets.US_ASCII);
    }

    public int getEType(){
        return this.etype;
    }

    public int getCode(){
        return this.code;
    }

    public byte[] getData(){
        return this.data;
    }
}

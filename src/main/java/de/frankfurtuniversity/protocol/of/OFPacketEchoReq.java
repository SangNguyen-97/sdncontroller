package de.frankfurtuniversity.protocol.of;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class OFPacketEchoReq extends OFPacketBase {

    public static final int MIN_SIZE = OFPacketBase.SIZE + 0; // in bytes

    byte[] data; // variable

    public OFPacketEchoReq(OFPacketBase s, byte[] raw) {
        super(s.getVersion(), s.getType(), s.getLength(), s.getXid());
        if (raw.length > 0) {
            this.data = Arrays.copyOf(raw, raw.length);
        }
    }

    public OFPacketEchoReq(short version, short type, int length, long xid, byte[] data) {
        super(version, type, length, xid);
        this.data = Arrays.copyOf(data, data.length);
    }

    public byte[] toHeader(){
        ByteBuffer buff = ByteBuffer.allocate(super.length);
        buff.put(super.toHeader());
        buff.put(this.data);
        buff.flip();
        return buff.array();
    }

    public String toString(){
        return super.toString() + "   OFPacketEchoReq data: " + new String(this.data,StandardCharsets.US_ASCII);
    }

    public byte[] getData(){
        return Arrays.copyOf(this.data, this.data.length);
    }
}

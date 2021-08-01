package de.frankfurtuniversity.protocol.of;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import de.frankfurtuniversity.utils.exception.RawBytesTooFewException;

public class OFPacketIn extends OFPacketBase {
    public static final int MIN_SIZE = OFPacketBase.SIZE + 10; // in bytes

    public static final byte REASON_NOMATCH = 0x00;
    public static final byte REASON_ACTION = 0x01;

    long buffer_id; // 32 bits
    int total_len; // 16 bits
    int inport; // 16 bits
    byte reason; // 8 bits (only 0x00 or 0x01 => type byte is used)
    byte[] data;

    public OFPacketIn(OFPacketBase s, byte[] raw) throws RawBytesTooFewException {
        super(s.getVersion(),s.getType(),s.getLength(),s.getXid());
        if (raw.length < (MIN_SIZE - OFPacketBase.SIZE))
            throw new RawBytesTooFewException();
        else {
            int offset = 0;
            this.buffer_id = (((long) raw[offset++] & 0xFF) << 24) + (((long) raw[offset++] & 0xFF) << 16)
                    + (((long) raw[offset++] & 0xFF) << 8) + ((long) raw[offset++] & 0xFF);
            this.total_len = (((int) raw[offset++] & 0xFF) << 8) + ((int) raw[offset++] & 0xFF);
            this.inport = (((int) raw[offset++] & 0xFF) << 8) + ((int) raw[offset++] & 0xFF);
            this.reason = raw[offset++];
            offset = offset + 1; // padding 1 byte
            this.data = Arrays.copyOfRange(raw, offset, raw.length);
        }
    }

    public String toString() {
        return super.toString() + "   OFPacketIn buffer_id: " + this.buffer_id + "   total_length: " + this.total_len
                + "   inport: " + this.inport + "   reason: " + this.reason + "   data: "
                + new String(this.data, StandardCharsets.US_ASCII);
    }

    public long getBufferId(){
        return this.buffer_id;
    }

    public int getTotalLength(){
        return this.total_len;
    }

    public int getInport(){
        return this.inport;
    }

    public byte getReason(){
        return this.reason;
    }

    public byte[] getData(){
        return Arrays.copyOf(this.data, this.data.length);
    }
}

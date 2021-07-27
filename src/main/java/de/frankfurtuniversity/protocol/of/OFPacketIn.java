package de.frankfurtuniversity.protocol.of;

import java.util.Arrays;

import de.frankfurtuniversity.utils.exception.RawBytesTooFewException;

public class OFPacketIn extends OFPacketBase {
    public static int MIN_SIZE = 8 + 10; // in bytes

    long buffer_id; // 32 bits
    int total_len; // 16 bits
    int inport; // 16 bits
    byte reason; // 8 bits (only 0x00 or 0x01 => type byte is used)
    byte[] data;

    public OFPacketIn(byte[] raw) throws RawBytesTooFewException {
        super(raw);
        if (raw.length < MIN_SIZE)
            throw new RawBytesTooFewException();
        else {
            int offset = OFPacketBase.SIZE;
            this.buffer_id = (((long) raw[offset++] & 0xFF) << 24) + (((long) raw[offset++] & 0xFF) << 16)
                    + (((long) raw[offset++] & 0xFF) << 8) + ((long) raw[offset++] & 0xFF);
            this.total_len = (((int) raw[offset++] & 0xFF) << 8) + ((int) raw[offset++] & 0xFF);
            this.inport = (((int) raw[offset++] & 0xFF) << 8) + ((int) raw[offset++] & 0xFF);
            this.reason = raw[offset++];
            offset = offset + 1; // padding 1 byte
            this.data = Arrays.copyOfRange(raw, offset, raw.length);
        }
    }
}

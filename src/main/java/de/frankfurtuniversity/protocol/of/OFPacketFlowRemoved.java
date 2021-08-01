package de.frankfurtuniversity.protocol.of;

import java.math.BigInteger;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.Arrays;

import de.frankfurtuniversity.protocol.of.entities.Match;
import de.frankfurtuniversity.utils.exception.RawBytesTooFewException;

public class OFPacketFlowRemoved extends OFPacketBase {
    public static final int SIZE = OFPacketBase.SIZE + Match.SIZE + 40; // in bytes

    public static final short REASON_IDLETIMEOUT = 0x00;
    public static final short REASON_HARDTIMEOUT = 0x01;
    public static final short REASON_DELETE = 0x02;

    Match match; // 40 * 32 bits
    long cookie; // 64 bits
    int priority; // 16 bits
    short reason; // 8 bits
    // padding 8 bits
    long duration_sec; // 32 bits
    long duration_nsec; // 32 bits

    int idle_timeout; // 16 bits
    // padding 16 bits
    BigInteger packet_count; // 64 bits
    BigInteger byte_count; // 64 bits

    public OFPacketFlowRemoved(OFPacketBase s, byte[] raw) throws RawBytesTooFewException, UnknownHostException {
        super(s.getVersion(), s.getType(), s.getLength(), s.getXid());
        if (raw.length < (SIZE - OFPacketBase.SIZE)) {
            throw new RawBytesTooFewException();
        } else {
            int offset = 0;
            this.match = new Match(Arrays.copyOfRange(raw, offset, offset + Match.SIZE));
            offset = offset + Match.SIZE;
            this.cookie = (((long) raw[offset++] & 0xff) << 56) + (((long) raw[offset++] & 0xff) << 48)
                    + (((long) raw[offset++] & 0xff) << 40) + (((long) raw[offset++] & 0xff) << 32)
                    + (((long) raw[offset++] & 0xff) << 24) + (((long) raw[offset++] & 0xff) << 16)
                    + (((long) raw[offset++] & 0xff) << 8) + ((long) raw[offset++] & 0xff);
            this.priority = (((int) raw[offset++] & 0xff) << 8) + ((int) raw[offset++] & 0xff);
            this.reason = (short) ((int) raw[offset++] & 0xff);
            offset = offset + 1; // padding 1 bytes
            this.duration_sec = (((long) raw[offset++] & 0xff) << 24) + (((long) raw[offset++] & 0xff) << 16)
                    + (((long) raw[offset++] & 0xff) << 8) + ((long) raw[offset++] & 0xff);
            this.duration_nsec = (((long) raw[offset++] & 0xff) << 24) + (((long) raw[offset++] & 0xff) << 16)
                    + (((long) raw[offset++] & 0xff) << 8) + ((long) raw[offset++] & 0xff);
            this.idle_timeout = (((int) raw[offset++] & 0xff) << 8) + ((int) raw[offset++] & 0xff);
            offset = offset + 2; // padding 2 bytes

            ByteBuffer buff = ByteBuffer.allocate(8 + 1);
            buff.put((byte) 0);
            buff.put(Arrays.copyOfRange(raw, offset, offset + 8));
            buff.flip();
            this.packet_count = new BigInteger(Arrays.copyOf(buff.array(), buff.array().length));
            offset = offset + 8;
            buff.clear();

            buff.put((byte) 0);
            buff.put(Arrays.copyOfRange(raw, offset, offset + 8));
            buff.flip();
            this.byte_count = new BigInteger(Arrays.copyOf(buff.array(), buff.array().length));
        }
    }

    public Match getMatch() {
        return this.match;
    }

    public long getCookie() {
        return this.cookie;
    }

    public int getPriority() {
        return this.priority;
    }

    public short getReason() {
        return this.reason;
    }

    public long getDurationSec() {
        return this.duration_sec;
    }

    public long getDurationNsec() {
        return this.duration_nsec;
    }

    public int getIdleTimeout() {
        return this.idle_timeout;
    }

    public BigInteger getPacketCount() {
        return this.packet_count;
    }

    public BigInteger getByteCount() {
        return this.byte_count;
    }
}

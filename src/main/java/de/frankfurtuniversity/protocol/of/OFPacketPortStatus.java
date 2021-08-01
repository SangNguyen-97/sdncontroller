package de.frankfurtuniversity.protocol.of;

import java.util.Arrays;

import de.frankfurtuniversity.protocol.of.entities.OFPort;
import de.frankfurtuniversity.utils.exception.RawBytesTooFewException;

public class OFPacketPortStatus extends OFPacketBase {
    public static final int MIN_SIZE = OFPacketBase.SIZE + 8 + OFPort.SIZE;

    public static final byte REASON_ADD = 0x00;
    public static final byte REASON_DELETE = 0x01;
    public static final byte REASON_MODIFY = 0x02;

    byte reason; // 8 bits and few possible value => byte
    // padding 7 * 8 bits
    OFPort port; // 48 * 8 bits

    public OFPacketPortStatus(OFPacketBase s, byte[] raw) throws RawBytesTooFewException {
        super(s.version, s.type, s.length, s.xid);
        if (raw.length < (MIN_SIZE - OFPacketBase.SIZE))
            throw new RawBytesTooFewException();
        else {
            int offset = 0;
            this.reason = (byte) raw[offset++];
            offset = offset + 7; // padding 7 bytes

            port = new OFPort(Arrays.copyOfRange(raw, offset, offset + OFPort.SIZE));
        }
    }

    public String toString(){
        return super.toString() + "   OFPacketPortStatus reason: " + this.reason + "   port: " + this.port.toString();
    }

    public byte getReason(){
        return this.reason;
    }

    /**
     * 
     * @return reference to port object of this object
     */
    public OFPort getPort(){
        return this.port;
    }
}

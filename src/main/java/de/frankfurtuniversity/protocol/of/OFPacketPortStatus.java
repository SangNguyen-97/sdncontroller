package de.frankfurtuniversity.protocol.of;

import java.util.Arrays;
import java.util.HashMap;

import de.frankfurtuniversity.protocol.of.entities.OFPort;
import de.frankfurtuniversity.utils.exception.RawBytesTooFewException;

public class OFPacketPortStatus extends OFPacketBase {
    public static int MIN_SIZE = OFPacketBase.SIZE + 8;

    short reason; // 8 bits
    // padding 56 bits
    HashMap<Integer, OFPort> ports;

    public OFPacketPortStatus(byte[] raw) throws RawBytesTooFewException {
        super(raw);
        if (raw.length < MIN_SIZE)
            throw new RawBytesTooFewException();
        else {
            int offset = OFPacketBase.SIZE;
            this.reason = (short) raw[offset++];
            offset = offset + 7; // padding 7 bytes

            int portCount = (raw.length - offset) / OFPort.SIZE;
            if (portCount > 0) {
                OFPort p = new OFPort(Arrays.copyOfRange(raw, offset, offset + OFPort.SIZE));
                offset = offset + OFPort.SIZE;
                ports.put(p.getPortId(), p);
            }
        }
    }
}

package de.frankfurtuniversity.protocol.of;

import java.util.Arrays;
import java.util.HashMap;

import de.frankfurtuniversity.protocol.of.entities.OFPort;
import de.frankfurtuniversity.utils.exception.RawBytesTooFewException;

public class OFPacketFeatRes extends OFPacketBase {
    public static final int MIN_SIZE = OFPacketBase.SIZE + 24; // in bytes

    long datapath_id; // 64 bits
    long n_buffers; // 32 bits
    short n_tbls; // 8 bits
    // padding 24 bits
    int capabilities; // 32 bits
    int actions; // 32 bits
    HashMap<Integer, OFPort> ports; // N * 48 * 8 bits

    public OFPacketFeatRes(byte[] raw) throws RawBytesTooFewException {
        super(raw);
        if (raw.length < MIN_SIZE) {
            throw new RawBytesTooFewException();
        } else {
            int offset = OFPacketBase.SIZE;
            this.datapath_id = (((long) raw[offset++] & 0xFF) << 56) + (((long) raw[offset++] & 0xFF) << 48)
                    + (((long) raw[offset++] & 0xFF) << 40) + (((long) raw[offset++] & 0xFF) << 32)
                    + (((long) raw[offset++] & 0xFF) << 24) + (((long) raw[offset++] & 0xFF) << 16)
                    + (((long) raw[offset++] & 0xFF) << 8) + ((long) raw[offset++] & 0xFF);
            this.n_buffers = (((long) raw[offset++] & 0xFF) << 24) + (((long) raw[offset++] & 0xFF) << 16)
                    + (((long) raw[offset++] & 0xFF) << 8) + ((long) raw[offset++] & 0xFF);
            this.n_tbls = (short) ((int) raw[offset++] & 0xFF);
            offset = offset + 3; // padding of 3 bytes
            this.capabilities = (((int) raw[offset++] & 0xFF) << 8) + ((int) raw[offset++] & 0xFF);
            this.actions = (((int) raw[offset++] & 0xFF) << 8) + ((int) raw[offset++] & 0xFF);

            int portCount = (this.length - MIN_SIZE) / OFPort.SIZE;
            if (portCount > 0) {
                this.ports = new HashMap<Integer, OFPort>();
                for (int i = 0; i < portCount; i++) {
                    OFPort p = new OFPort(Arrays.copyOfRange(raw, offset, offset + OFPort.SIZE));
                    offset = offset + OFPort.SIZE;
                    ports.put(p.getPortId(), p);
                }
            }
        }
    }

}

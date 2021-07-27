package de.frankfurtuniversity.protocol.of.entities;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import de.frankfurtuniversity.protocol.mac.MACAddress;
import de.frankfurtuniversity.utils.exception.RawBytesTooFewException;

public class OFPort {
        public static int SIZE = 48; // in bytes

        int portId; // 16 bits
        MACAddress hw_addr; // 6 * 8 bits
        String name; // 16 * 8 bits
        int config; // 32 bits
        int state; // 32 bits;
        int curr; // 32 bits
        int advertised; // 32 bits

        public int getPortId(){
                return this.portId;
        }

        public OFPort(byte[] raw) throws RawBytesTooFewException {
                if (raw.length < SIZE)
                        throw new RawBytesTooFewException();
                else {
                        int offset = 0;
                        this.portId = (((int) raw[offset++] & 0xFF) << 8) + ((int) raw[offset++] & 0xFF);
                        this.hw_addr = new MACAddress(Arrays.copyOfRange(raw, offset, 6));
                        offset = offset + 6;
                        this.name = new String(Arrays.copyOfRange(raw, offset, offset + 16), StandardCharsets.US_ASCII);
                        offset = offset + 16;
                        this.config = (((int) raw[offset++] & 0xFF) << 24) + (((int) raw[offset++] & 0xFF) << 16)
                                        + (((int) raw[offset++] & 0xFF) << 8) + ((int) raw[offset++] & 0xFF);

                        this.config = (((int) raw[offset++] & 0xFF) << 24) + (((int) raw[offset++] & 0xFF) << 16)
                                        + (((int) raw[offset++] & 0xFF) << 8) + ((int) raw[offset++] & 0xFF);
                        this.state = (((int) raw[offset++] & 0xFF) << 24) + (((int) raw[offset++] & 0xFF) << 16)
                                        + (((int) raw[offset++] & 0xFF) << 8) + ((int) raw[offset++] & 0xFF);
                        this.curr = (((int) raw[offset++] & 0xFF) << 24) + (((int) raw[offset++] & 0xFF) << 16)
                                        + (((int) raw[offset++] & 0xFF) << 8) + ((int) raw[offset++] & 0xFF);
                        this.advertised = (((int) raw[offset++] & 0xFF) << 24) + (((int) raw[offset++] & 0xFF) << 16)
                                        + (((int) raw[offset++] & 0xFF) << 8) + ((int) raw[offset++] & 0xFF);
                }
        }

}

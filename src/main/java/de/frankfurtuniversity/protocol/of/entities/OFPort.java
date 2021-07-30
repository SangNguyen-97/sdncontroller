package de.frankfurtuniversity.protocol.of.entities;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import de.frankfurtuniversity.protocol.mac.MACAddress;
import de.frankfurtuniversity.utils.exception.RawBytesTooFewException;

public class OFPort {
        public static final int SIZE = 48; // in bytes

        public static final int PORTID_MAX = 0xff00;
        public static final int PORTID_INPORT = 0xfff8;
        public static final int PORTID_TABLE = 0xfff9;
        public static final int PORTID_NORMAL = 0xfffa;
        public static final int PORTID_FLOOD = 0xfffb;
        public static final int PORTID_ALL = 0xfffc;
        public static final int PORTID_CONTROLLER = 0xfffd;
        public static final int PORTID_LOCAL = 0xfffe;
        public static final int PORTID_NONE = 0xffff;


        int portId; // 16 bits
        MACAddress hw_addr; // 6 * 8 bits
        String name; // 16 * 8 bits
        int config; // 32 bits
        int state; // 32 bits;
        int curr; // 32 bits
        int advertised; // 32 bits
        int supported; // 32 bits
        int peer; // 32 bits

        public OFPort(byte[] raw) throws RawBytesTooFewException {
                if (raw.length < SIZE)
                        throw new RawBytesTooFewException();
                else {
                        int offset = 0;
                        this.portId = (((int) raw[offset++] & 0xFF) << 8) + ((int) raw[offset++] & 0xFF);
                        this.hw_addr = new MACAddress(Arrays.copyOfRange(raw, offset, offset + 6));
                        offset = offset + 6;
                        this.name = new String(Arrays.copyOfRange(raw, offset, offset + 16), StandardCharsets.US_ASCII)
                                        .replace("\u0000", " ").trim();
                        offset = offset + 16;
                        this.config = (((int) raw[offset++] & 0xFF) << 24) + (((int) raw[offset++] & 0xFF) << 16)
                                        + (((int) raw[offset++] & 0xFF) << 8) + ((int) raw[offset++] & 0xFF);
                        this.state = (((int) raw[offset++] & 0xFF) << 24) + (((int) raw[offset++] & 0xFF) << 16)
                                        + (((int) raw[offset++] & 0xFF) << 8) + ((int) raw[offset++] & 0xFF);
                        this.curr = (((int) raw[offset++] & 0xFF) << 24) + (((int) raw[offset++] & 0xFF) << 16)
                                        + (((int) raw[offset++] & 0xFF) << 8) + ((int) raw[offset++] & 0xFF);
                        this.advertised = (((int) raw[offset++] & 0xFF) << 24) + (((int) raw[offset++] & 0xFF) << 16)
                                        + (((int) raw[offset++] & 0xFF) << 8) + ((int) raw[offset++] & 0xFF);
                        this.supported = (((int) raw[offset++] & 0xFF) << 24) + (((int) raw[offset++] & 0xFF) << 16)
                                        + (((int) raw[offset++] & 0xFF) << 8) + ((int) raw[offset++] & 0xFF);
                        this.peer = (((int) raw[offset++] & 0xFF) << 24) + (((int) raw[offset++] & 0xFF) << 16)
                                        + (((int) raw[offset++] & 0xFF) << 8) + ((int) raw[offset++] & 0xFF);
                }
        }

        public OFPort(int portId, MACAddress hw_addr, String name, int config, int state, int curr, int advertised,
                        int supported, int peer) {
                this.portId = portId;
                this.hw_addr = hw_addr;
                this.name = name;
                this.config = config;
                this.state = state;
                this.curr = curr;
                this.advertised = advertised;
                this.supported = supported;
                this.peer = peer;
        }

        public String toString() {
                return "OFPort portID: " + this.portId + "  hw_addr: " + this.hw_addr.toString() + "  name: "
                                + this.name + "  config: " + this.config + "  state: " + this.state + "  curr: "
                                + this.curr + "  advertised: " + this.advertised + "  supported: " + this.supported
                                + "  peer: " + this.peer;
        }

        public byte[] toHeader() {
                ByteBuffer buff = ByteBuffer.allocate(SIZE);
                buff.put((byte) (this.portId >> 8));
                buff.put((byte) this.portId);
                buff.put(this.hw_addr.getAddressBytes());
                buff.put(this.name.getBytes());
                for (int i = 0; i < (16 - name.getBytes().length); i++) {
                        buff.put((byte) 0);
                }
                buff.put((byte) (this.config >> 24));
                buff.put((byte) (this.config >> 16));
                buff.put((byte) (this.config >> 8));
                buff.put((byte) this.config);
                buff.put((byte) (this.state >> 24));
                buff.put((byte) (this.state >> 16));
                buff.put((byte) (this.state >> 8));
                buff.put((byte) this.state);
                buff.put((byte) (this.curr >> 24));
                buff.put((byte) (this.curr >> 16));
                buff.put((byte) (this.curr >> 8));
                buff.put((byte) this.curr);
                buff.put((byte) (this.advertised >> 24));
                buff.put((byte) (this.advertised >> 16));
                buff.put((byte) (this.advertised >> 8));
                buff.put((byte) this.advertised);
                buff.put((byte) (this.supported >> 24));
                buff.put((byte) (this.supported >> 16));
                buff.put((byte) (this.supported >> 8));
                buff.put((byte) this.supported);
                buff.put((byte) (this.peer >> 24));
                buff.put((byte) (this.peer >> 16));
                buff.put((byte) (this.peer >> 8));
                buff.put((byte) this.peer);
                buff.flip();
                return buff.array();
        }

        public int getPortId() {
                return this.portId;
        }

        public MACAddress getHWAddr() {
                return this.hw_addr;
        }

        public String getName() {
                return this.name;
        }

        public int getConfig() {
                return this.config;
        }

        public int getState() {
                return this.state;
        }

        public int getCurr() {
                return this.curr;
        }

        public int getAdvertised() {
                return this.advertised;
        }

        public int getSupported() {
                return this.supported;
        }

        public int getPeer() {
                return this.peer;
        }

}

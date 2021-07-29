package de.frankfurtuniversity.protocol.mac;

import java.nio.ByteBuffer;

import de.frankfurtuniversity.utils.exception.RawBytesTooFewException;

public class MACAddress {
    public static int SIZE = 6; // in bytes

    byte[] mac = new byte[6];

    public MACAddress(String s) throws NumberFormatException {
        String[] sa = s.split(":");
        if (sa.length >= 6) {
            ByteBuffer buff = ByteBuffer.allocate(SIZE);
            for (int i = 0; i < SIZE; i++) {
                if (sa[i].length() > 2)
                    throw new NumberFormatException();
                buff.put((byte) (Integer.parseInt(sa[i], 16)));
            }
            buff.flip();
            this.mac = buff.array();
        }
    }

    public MACAddress(byte[] raw) throws RawBytesTooFewException {
        if (raw.length == 6) {
            mac = raw;
        } else {
            throw new RawBytesTooFewException();
        }
    }

    public String toString() {
        String s = "";
        for (int i = 0; i < 6; i++) {
            int a = (mac[i] >> 4) & 0x0f;
            int b = mac[i] & 0x0f;
            s = s + Integer.toHexString(a).toUpperCase() + Integer.toHexString(b).toUpperCase();
            if (i < 5)
                s = s + ":";
        }
        return s;
    }

    public boolean equals(MACAddress macadd2) {
        if (mac.length != macadd2.mac.length) {
            return false;
        } else {
            for (int i = 0; i < mac.length; i++) {
                if (mac[i] != macadd2.mac[i])
                    return false;
            }
            return true;
        }
    }

    public byte[] getAddressBytes() {
        return mac;
    }
}

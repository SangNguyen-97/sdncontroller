package de.frankfurtuniversity.protocol.mac;

import de.frankfurtuniversity.utils.exception.RawBytesTooFewException;

public class MACAddress {
    byte[] mac = new byte[6];

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

    public byte[] getAddress(){
        return mac;
    }
}

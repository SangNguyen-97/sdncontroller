package de.frankfurtuniversity.protocol.of.entities.actionpayload;

import de.frankfurtuniversity.utils.exception.RawBytesTooFewException;

public class ActionVendor extends ActionPayload {
    public static int SIZE = 4; // in bytes

    long vendor; // 32 bits

    public ActionVendor(long vendor) {
        this.vendor = vendor;
    }

    public ActionVendor(byte[] raw) throws RawBytesTooFewException {
        if (raw.length < SIZE)
            throw new RawBytesTooFewException();
        else {
            int offset = 0;
            this.vendor = (((long) raw[offset++] & 0xff) << 24) + (((long) raw[offset++] & 0xff) << 16)
                    + (((long) raw[offset++] & 0xff) << 8) + ((long) raw[offset++] & 0xff);
        }
    }

    public byte[] toHeader(){
        byte[] b = new byte[SIZE];
        b[0] = (byte) (vendor >> 24);
        b[1] = (byte) (vendor >> 16);
        b[2] = (byte) (vendor >> 8);
        b[3] = (byte) vendor;
        return b;
    }

    public String toString(){
        return "setVendor vendor: " + this.vendor;
    }

    public long getVendor(){
        return this.vendor;
    }
}

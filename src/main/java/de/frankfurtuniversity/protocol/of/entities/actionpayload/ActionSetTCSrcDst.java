package de.frankfurtuniversity.protocol.of.entities.actionpayload;

import de.frankfurtuniversity.utils.exception.RawBytesTooFewException;

public class ActionSetTCSrcDst extends ActionPayload {

    public static int SIZE = 4; // in bytes

    int port; // 16 bits
    // padding 16 bits

    public ActionSetTCSrcDst(byte[] raw) throws RawBytesTooFewException {
        if (raw.length < SIZE)
            throw new RawBytesTooFewException();
        else {
            int offset = 0;
            this.port = (((int) raw[offset++] & 0xff) << 8) + ((int) raw[offset++] & 0xff);
        }
    }

    public ActionSetTCSrcDst(int port) {
        this.port = port;
    }

    public byte[] toHeader() {
        byte[] b = new byte[SIZE];
        b[0] = (byte) (port >> 8);
        b[1] = (byte) port;
        b[2] = (byte) 0;
        b[3] = (byte) 0;
        return b;
    }

    public int getPort() {
        return this.port;
    }

    public String toString() {
        return "setTP port: " + this.port;
    }
}

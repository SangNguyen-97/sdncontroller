package de.frankfurtuniversity.protocol.of.entities.actionpayload;

import de.frankfurtuniversity.utils.exception.RawBytesTooFewException;

public class ActionOutput extends ActionPayload {
    public static int SIZE = 4;

    int port; // 16 bits
    int max_len; // 16 bits

    public ActionOutput(int port, int max_len) {
        this.port = port;
        this.max_len = max_len;
    }

    public ActionOutput(byte[] raw) throws RawBytesTooFewException {
        if (raw.length < SIZE)
            throw new RawBytesTooFewException();
        else {
            int offset = 0;
            this.port = (((int) raw[offset++] & 0xff) << 8) + ((int) raw[offset++] & 0xff);
            this.max_len = (((int) raw[offset++] & 0xff) << 8) + ((int) raw[offset++] & 0xff);
        }
    }

    public byte[] toHeader() {
        byte[] b = new byte[SIZE];
        b[0] = (byte) (port >> 8);
        b[1] = (byte) port;
        b[2] = (byte) (max_len >> 8);
        b[3] = (byte) max_len;
        return b;
    }

    public int getPort() {
        return this.port;
    }

    public int getMaxLen() {
        return this.max_len;
    }

    public String toString() {
        return "output port: " + this.port + "  max_len" + this.max_len;
    }
}

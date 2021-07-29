package de.frankfurtuniversity.protocol.of.entities.actionpayload;

import de.frankfurtuniversity.utils.exception.RawBytesTooFewException;

public class ActionEnqueue extends ActionPayload {

    public static int SIZE = 12; // in bytes

    int port; // 16 bits
    // padding 48 bits
    long queue_id; // 32 bits

    public ActionEnqueue(byte[] raw) throws RawBytesTooFewException {
        if (raw.length < SIZE)
            throw new RawBytesTooFewException();
        else {
            int offset = 0;
            this.port = (((int) raw[offset++] & 0xff) << 8) + ((int) raw[offset++] & 0xff);
            offset = offset + 6; // padding 6 bytes
            this.queue_id = (((long) raw[offset++] & 0xff) << 24) + (((long) raw[offset++] & 0xff) << 16)
                    + (((long) raw[offset++] & 0xff) << 8) + ((long) raw[offset++] & 0xff);
        }
    }

    public ActionEnqueue(int port, long queue_id) {
        this.port = port;
        this.queue_id = queue_id;
    }

    public byte[] toHeader() {
        int offset = 0;
        byte[] b = new byte[SIZE];
        b[offset++] = (byte) (port >> 8);
        b[offset++] = (byte) port;
        for (int i = 0; i < 6; i++) {
            b[offset++] = 0;
        }
        b[offset++] = (byte) (queue_id >> 24);
        b[offset++] = (byte) (queue_id >> 16);
        b[offset++] = (byte) (queue_id >> 8);
        b[offset++] = (byte) queue_id;
        return b;
    }

    public int getPort() {
        return this.port;
    }

    public long getQueueID() {
        return this.queue_id;
    }

    public String toString() {
        return "enqueue port: " + this.port + "  " + "queue_id: " + this.queue_id;
    }
}

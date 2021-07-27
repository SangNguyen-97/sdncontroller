package de.frankfurtuniversity.protocol.of;

import de.frankfurtuniversity.utils.exception.RawBytesTooFewException;

public class OFPacketHello extends OFPacketBase {
    public static int SIZE = OFPacketBase.SIZE;

    public OFPacketHello(byte[] raw) throws RawBytesTooFewException {
        super(raw);
    }
}

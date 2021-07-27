package de.frankfurtuniversity.protocol.of;

import de.frankfurtuniversity.utils.exception.RawBytesTooFewException;

public class OFPacketFeatReq extends OFPacketBase {
    public static int SIZE = OFPacketBase.SIZE;

    public OFPacketFeatReq(byte[] raw) throws RawBytesTooFewException {
        super(raw);
        super.type = 5;
    }
}

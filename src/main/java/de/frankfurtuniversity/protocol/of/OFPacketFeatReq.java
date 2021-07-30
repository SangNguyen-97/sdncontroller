package de.frankfurtuniversity.protocol.of;

import java.util.concurrent.ThreadLocalRandom;

import de.frankfurtuniversity.utils.exception.RawBytesTooFewException;

public class OFPacketFeatReq extends OFPacketBase {
    public static int SIZE = OFPacketBase.SIZE + 0; // in bytes

    public OFPacketFeatReq(OFPacketBase s) throws RawBytesTooFewException {
        super(s.getVersion(),s.getType(),s.getLength(),s.getXid());
    }

    public OFPacketFeatReq() {
        super(OFPacketBase.VERSION, OFPacketBase.OFPACKETTYPE_FEATURE_REQ, OFPacketBase.SIZE,
                (long) ThreadLocalRandom.current().nextInt() & 0xffffffffL);
    }

    public OFPacketFeatReq(short version, short type, int length, long xid) {
        super(version, type, length, xid);
    }

    public String toString(){
        return super.toString() + "   OFPacketFeatureRequest";
    }

    public byte[] toHeader(){
        return super.toHeader();
    }
}

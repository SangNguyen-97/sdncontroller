package de.frankfurtuniversity.protocol.of;

import java.util.concurrent.ThreadLocalRandom;

import de.frankfurtuniversity.utils.exception.RawBytesTooFewException;

public class OFPacketHello extends OFPacketBase {

    public static int SIZE = OFPacketBase.SIZE + 0; // in bytes

    public OFPacketHello(OFPacketBase s) throws RawBytesTooFewException {
        super(s.getVersion(),s.getType(),s.getLength(),s.getXid());
    }

    public OFPacketHello() {
        super(OFPacketBase.VERSION, OFPacketBase.OFPACKETTYPE_HELLO, OFPacketBase.SIZE,
                (long) ThreadLocalRandom.current().nextInt() & 0xffffffffL);
    }

    public OFPacketHello(short version, short type, int length, long xid) {
        super(version, type, length, xid);
    }
    
    public String toString(){
        return super.toString() + "   OFPacketHello";
    }

    public byte[] toHeader(){
        return super.toHeader();
    }
}

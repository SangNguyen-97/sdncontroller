package de.frankfurtuniversity.protocol.of.entities.actionpayload;

import java.nio.ByteBuffer;

import de.frankfurtuniversity.protocol.mac.MACAddress;
import de.frankfurtuniversity.utils.exception.RawBytesTooFewException;

public class ActionSetDLSrcDst extends ActionPayload{

    public static int SIZE = 12; // in bytes

    MACAddress dl_addr; // 48 bits
    // padding 48 bits

    public ActionSetDLSrcDst(byte[] raw) throws RawBytesTooFewException {
        if (raw.length < SIZE)
            throw new RawBytesTooFewException();
        else {
            ByteBuffer buff = ByteBuffer.allocate(MACAddress.SIZE);
            buff.put(raw, 0, MACAddress.SIZE);
            buff.flip();
            dl_addr = new MACAddress(buff.array());
        }
    }

    public ActionSetDLSrcDst(MACAddress dl_addr) {
        this.dl_addr = dl_addr;
    }

    public byte[] toHeader() {
        ByteBuffer b = ByteBuffer.allocate(SIZE);
        b.put(dl_addr.getAddressBytes());
        for (int i = 0; i < SIZE - MACAddress.SIZE; i++)
            b.put((byte)0);
        b.flip();
        return b.array();
    }

    public MACAddress getDl_addr() {
        return this.dl_addr;
    }

    public String toString(){
        return "setDL mac: " + this.dl_addr.toString();
    }
}

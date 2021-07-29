package de.frankfurtuniversity.protocol.of.entities.actionpayload;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

import de.frankfurtuniversity.utils.exception.RawBytesTooFewException;

public class ActionSetNWSrcDst extends ActionPayload {

    public static int SIZE = 4; // in bytes

    InetAddress nw_addr; // 32 bits

    public ActionSetNWSrcDst(InetAddress nw_addr) {
        this.nw_addr = nw_addr;
    }

    public ActionSetNWSrcDst(byte[] raw) throws RawBytesTooFewException, UnknownHostException{
        if ( raw.length < SIZE) throw new RawBytesTooFewException();
        else {
            this.nw_addr = InetAddress.getByAddress(Arrays.copyOfRange(raw, 0, SIZE));
        }
    }

    public byte[] toHeader(){
        return this.nw_addr.getAddress();
    }

    public InetAddress getNwAddr(){
        return this.nw_addr;
    }

    public String toString(){
        return "setNW nw_addr: " + this.nw_addr.getHostAddress();
    }
}

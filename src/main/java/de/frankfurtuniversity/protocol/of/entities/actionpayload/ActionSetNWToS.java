package de.frankfurtuniversity.protocol.of.entities.actionpayload;

import de.frankfurtuniversity.utils.exception.RawBytesTooFewException;

public class ActionSetNWToS extends ActionPayload{

    public static int SIZE = 4; //in bytes

    short nw_tos; // 8bits
    // padding 24 bits

    public ActionSetNWToS(short nw_tos) {
        this.nw_tos = nw_tos;
    }

    public ActionSetNWToS(byte[] raw) throws RawBytesTooFewException{
        if (raw.length < SIZE) throw new RawBytesTooFewException();
        else{
            this.nw_tos = (short)((int)raw[0] & 0xff);
        }
    }

    public byte[] toHeader(){
        byte[] b = {
            (byte) nw_tos, (byte) 0, (byte) 0, (byte) 0
        };
        return b;
    }

    public short getNwTos(){
        return this.nw_tos;
    }

    public String toString(){
        return "setNWTos nw_tos: " + this.nw_tos;
    }
    
}

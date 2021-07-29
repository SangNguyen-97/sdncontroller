package de.frankfurtuniversity.protocol.of.entities.actionpayload;

import de.frankfurtuniversity.utils.exception.RawBytesTooFewException;

public class ActionSetVLANPCP extends ActionPayload{
   
    public static int SIZE = 4; //in bytes

    short vlan_pc; // 8bits
    // padding 24 bits

    public ActionSetVLANPCP(short vlan_pc) {
        this.vlan_pc = vlan_pc;
    }

    public ActionSetVLANPCP(byte[] raw) throws RawBytesTooFewException{
        if (raw.length < SIZE) throw new RawBytesTooFewException();
        else{
            this.vlan_pc = (short)((int)raw[0] & 0xff);
        }
    }

    public byte[] toHeader(){
        byte[] b = {
            (byte) vlan_pc, (byte) 0, (byte) 0, (byte) 0
        };
        return b;
    }

    public short getVlanPc(){
        return this.vlan_pc;
    }

    public String toString(){
        return "setVLANPCP vlan_pc: " + this.vlan_pc;
    }
    
}

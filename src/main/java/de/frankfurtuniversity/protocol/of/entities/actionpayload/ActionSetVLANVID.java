package de.frankfurtuniversity.protocol.of.entities.actionpayload;

import de.frankfurtuniversity.utils.exception.RawBytesTooFewException;

public class ActionSetVLANVID extends ActionPayload{
    public static int SIZE = 4; // in bytes

    int vlan_id; // 16 bits
    // padding 16 bits

    public ActionSetVLANVID(int vlan_id) {
        this.vlan_id = vlan_id;
    }

    public ActionSetVLANVID(byte[] raw) throws RawBytesTooFewException {
        if (raw.length < SIZE)
            throw new RawBytesTooFewException();
        else {
            int offset = 0;
            this.vlan_id = (((int)raw[offset++] & 0xff)<< 8) + ((int)raw[offset++] & 0xff);
        }
    }

    public byte[] toHeader(){
        byte[] b = new byte[SIZE];
        b[0] = (byte)(this.vlan_id >> 8);
        b[1] = (byte) this.vlan_id;
        b[2] = (byte) 0;
        b[3] = (byte) 0;
        return b;
    }

    public int getVlanId(){
        return this.vlan_id;
    }

    public String toString(){
        return "setVLANVID vlan_id: " + this.vlan_id;
    }
}

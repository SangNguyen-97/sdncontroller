package de.frankfurtuniversity.protocol.of.entities;

import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.Arrays;

import de.frankfurtuniversity.protocol.of.entities.actionpayload.ActionEnqueue;
import de.frankfurtuniversity.protocol.of.entities.actionpayload.ActionOutput;
import de.frankfurtuniversity.protocol.of.entities.actionpayload.ActionPayload;
import de.frankfurtuniversity.protocol.of.entities.actionpayload.ActionSetDLSrcDst;
import de.frankfurtuniversity.protocol.of.entities.actionpayload.ActionSetNWSrcDst;
import de.frankfurtuniversity.protocol.of.entities.actionpayload.ActionSetNWToS;
import de.frankfurtuniversity.protocol.of.entities.actionpayload.ActionSetTCSrcDst;
import de.frankfurtuniversity.protocol.of.entities.actionpayload.ActionSetVLANPCP;
import de.frankfurtuniversity.protocol.of.entities.actionpayload.ActionSetVLANVID;
import de.frankfurtuniversity.protocol.of.entities.actionpayload.ActionVendor;
import de.frankfurtuniversity.utils.exception.RawBytesTooFewException;

public class Action {
    public static final int PAYLOAD_OUTPUT = 0x00;
    public static final int PAYLOAD_SETVLANVID = 0x01;
    public static final int PAYLOAD_VLANPCP = 0x02;
    public static final int PAYLOAD_STRIPVLAN = 0x03;
    public static final int PAYLOAD_SETDLSRC = 0x04;
    public static final int PAYLOAD_SETDLDST = 0x05;
    public static final int PAYLOAD_SETNWSRC = 0x06;
    public static final int PAYLOAD_SETNWDST = 0x07;
    public static final int PAYLOAD_SETNWTOS = 0x08;
    public static final int PAYLOAD_SETTPSRC = 0x09;
    public static final int PAYLOAD_SETTPDST = 0x0a;
    public static final int PAYLOAD_ENQUEUE = 0x0b;
    public static final int PAYLOAD_VENDOR = 0xffff;

    public static int MIN_SIZE = 4; // in bytes

    int type; // 16 bits
    int length; // 16 bits

    ActionPayload p = null; // variable size

    public Action(int type, int length, ActionPayload p) {
        this.type = type;
        this.length = length;
        this.p = p;
    }

    public Action(byte[] raw) throws RawBytesTooFewException, UnknownHostException {
        if (raw.length < MIN_SIZE)
            throw new RawBytesTooFewException();
        else {
            int offset = 0;
            this.type = (((int) raw[offset++] & 0xff) << 8) + ((int) raw[offset++] & 0xff);
            this.length = (((int) raw[offset++] & 0xff) << 8) + ((int) raw[offset++] & 0xff);
            switch (this.type) {
                case PAYLOAD_OUTPUT:
                    this.p = new ActionOutput(Arrays.copyOfRange(raw, offset, offset + ActionOutput.SIZE));
                    break;
                case PAYLOAD_SETVLANVID:
                    this.p = new ActionSetVLANVID(Arrays.copyOfRange(raw, offset, offset + ActionSetVLANVID.SIZE));
                    break;
                case PAYLOAD_VLANPCP:
                    this.p = new ActionSetVLANPCP(Arrays.copyOfRange(raw, offset, offset + ActionSetVLANPCP.SIZE));
                    break;
                case PAYLOAD_STRIPVLAN:
                    break;
                case PAYLOAD_SETDLSRC:
                    this.p = new ActionSetDLSrcDst(Arrays.copyOfRange(raw, offset, offset + ActionSetDLSrcDst.SIZE));
                    break;
                case PAYLOAD_SETDLDST:
                    this.p = new ActionSetDLSrcDst(Arrays.copyOfRange(raw, offset, offset + ActionSetDLSrcDst.SIZE));
                    break;
                case PAYLOAD_SETNWSRC:
                    this.p = new ActionSetNWSrcDst(Arrays.copyOfRange(raw, offset, offset + ActionSetNWSrcDst.SIZE));
                    break;
                case PAYLOAD_SETNWDST:
                    this.p = new ActionSetNWSrcDst(Arrays.copyOfRange(raw, offset, offset + ActionSetNWSrcDst.SIZE));
                    break;
                case PAYLOAD_SETNWTOS:
                    this.p = new ActionSetNWToS(Arrays.copyOfRange(raw, offset, offset + ActionSetNWToS.SIZE));
                    break;
                case PAYLOAD_SETTPSRC:
                    this.p = new ActionSetTCSrcDst(Arrays.copyOfRange(raw, offset, offset + ActionSetTCSrcDst.SIZE));
                    break;
                case PAYLOAD_SETTPDST:
                    this.p = new ActionSetTCSrcDst(Arrays.copyOfRange(raw, offset, offset + ActionSetTCSrcDst.SIZE));
                    break;
                case PAYLOAD_ENQUEUE:
                    this.p = new ActionEnqueue(Arrays.copyOfRange(raw, offset, offset + ActionEnqueue.SIZE));
                    break;
                case PAYLOAD_VENDOR:
                    this.p = new ActionVendor(Arrays.copyOfRange(raw, offset, offset + ActionVendor.SIZE));
                    break;
                default:
                    break;

            }
        }
    }

    public String toString() {
        return "action type: " + this.type + "  length: " + this.length + "  \npayload: " + this.p.toString();
    }

    public byte[] toHeader() {
        ByteBuffer buff = ByteBuffer.allocate(this.length);
        buff.put((byte) (this.type >> 8));
        buff.put((byte) this.type);
        buff.put((byte) (this.length >> 8));
        buff.put((byte) this.length);
        if (this.p != null)
            buff.put(this.p.toHeader());
        buff.flip();
        return buff.array();
    }

    public int getType() {
        return this.type;
    }

    public int getLength() {
        return this.length;
    }

    public ActionPayload getPayload(){
        return this.p;
    }
}

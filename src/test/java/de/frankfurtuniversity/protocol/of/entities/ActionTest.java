package de.frankfurtuniversity.protocol.of.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

import org.junit.Test;

import de.frankfurtuniversity.protocol.mac.MACAddress;
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

public class ActionTest {
    byte[] raw = { (byte) 0x00, (byte) 0x03, (byte) 0x00, (byte) 0x04 };
    int mytype = 3;
    int mylength = 4;
    ActionPayload p = null;

    @Test
    public void testConstructor() throws UnknownHostException, RawBytesTooFewException {
        Action a = new Action(raw);

        assertEquals("type", mytype, a.getType());
        assertEquals("length", mylength, a.getLength());
    }

    @Test
    public void testToHeader(){
        Action a = new Action(mytype, mylength, p);

        assertTrue("to header", Arrays.equals(raw, a.toHeader()));
    }

    @Test
    public void testEnqueue() throws UnknownHostException, RawBytesTooFewException{
        byte[] raw = {
            (byte) 0x00, (byte) 0x0b, (byte) 0x00, (byte) 0x10,
            (byte) 0xae, (byte) 0xf4, (byte) 0x00, (byte) 0x00,
            (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
            (byte) 0x45, (byte) 0xd4, (byte) 0x5e, (byte) 0x6a
        };
        int type = Action.PAYLOAD_ENQUEUE;
        int length = 16;
        int port = 0xaef4; // 16 bits
        long queue_id = 0x45d45e6a; // 32 bits
        ActionEnqueue np = new ActionEnqueue(port, queue_id);
    
        Action a1 = new Action(raw);
        assertEquals("type", type, a1.getType());
        assertEquals("length", length, a1.getLength());
        assertTrue("enqueue port", a1.getPayload().toString().equals("enqueue port: " + port + "  " + "queue_id: " + queue_id));
        
        Action a2 = new Action(type, length, np);
        assertTrue("to header", Arrays.equals(raw, a2.toHeader()));
    }

    @Test
    public void testOutput() throws UnknownHostException, RawBytesTooFewException{
        byte[] raw = {
            (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x08, // type + length 
            (byte) 0xa6, (byte) 0xe5, (byte) 0xff, (byte) 0xff // port + max_len
        };

        int type = Action.PAYLOAD_OUTPUT;
        int length = 8;
        int port = 42725;
        int max_len = 0xffff;
        ActionOutput p = new ActionOutput(port, max_len);

        Action a1 = new Action(raw);
        assertEquals("type", type, a1.getType());
        assertEquals("length", length, a1.getLength());
        assertTrue("port", a1.getPayload().toString().equals("output port: " + port + "  max_len" + max_len));

        Action a2 = new Action(type, length, p);
        assertTrue("to header", Arrays.equals(raw, a2.toHeader()));
    }

    @Test
    public void testSetVLANVID() throws RawBytesTooFewException, UnknownHostException{
        byte[] raw = {
            (byte) 0x00, (byte) 0x01, (byte) 0x00, (byte) 0x08, 
            (byte) 0xae, (byte) 0xfd, (byte) 0x00, (byte) 0x00
        };

        int type = Action.PAYLOAD_SETVLANVID;
        int length = 8;
        int vlan_id = 0xaefd;
        ActionSetVLANVID p = new ActionSetVLANVID(vlan_id);

        Action a1 = new Action(raw);
        assertEquals("type", type, a1.getType());
        assertEquals("length", length, a1.getLength());
        assertTrue("port", a1.getPayload().toString().equals("setVLANVID vlan_id: " + vlan_id));

        Action a2 = new Action(type, length, p);
        assertTrue("to header", Arrays.equals(raw, a2.toHeader()));
    }

    @Test
    public void testSetNWSrcDst() throws RawBytesTooFewException, UnknownHostException{
        byte[] raw = {
            (byte) 0x00, (byte) 0x06, (byte) 0x00, (byte) 0x08, 
            (byte) 0xe0, (byte) 0x00, (byte) 0x00, (byte) 0x64
        };

        int type = Action.PAYLOAD_SETNWSRC;
        int length = 8;
        InetAddress nw_addr = InetAddress.getByName("224.0.0.100");
        ActionSetNWSrcDst p = new ActionSetNWSrcDst(nw_addr);

        Action a1 = new Action(raw);
        assertEquals("type", type, a1.getType());
        assertEquals("length", length, a1.getLength());
        assertTrue("nw_addr", a1.getPayload().toString().equals("setNW nw_addr: " + nw_addr.getHostAddress()));

        Action a2 = new Action(type, length, p);
        assertTrue("to header", Arrays.equals(raw, a2.toHeader()));
    }
   
    @Test
    public void testVendor() throws RawBytesTooFewException, UnknownHostException{
        byte[] raw = {
            (byte) 0xff, (byte) 0xff, (byte) 0x00, (byte) 0x08, 
            (byte) 0xab, (byte) 0xcd, (byte) 0xef, (byte) 0xab
        };

        int type = Action.PAYLOAD_VENDOR;
        int length = 8;
        long vendor = 0xabcdefab;
        ActionVendor p = new ActionVendor(vendor);

        Action a1 = new Action(raw);
        assertEquals("type", type, a1.getType());
        assertEquals("length", length, a1.getLength());
        assertTrue("vendor", a1.getPayload().toString().equals("setVendor vendor: " + (int)vendor));

        Action a2 = new Action(type, length, p);
        assertTrue("to header", Arrays.equals(raw, a2.toHeader()));
    }

    @Test
    public void testSetVLANPCP() throws RawBytesTooFewException, UnknownHostException{
        byte[] raw = {
            (byte) 0x00, (byte) 0x02, (byte) 0x00, (byte) 0x08, 
            (byte) 0xab, (byte) 0x00, (byte) 0x00, (byte) 0x00
        };

        int type = Action.PAYLOAD_SETVLANPCP;
        int length = 8;
        short vlan_pc = 0xab;
        ActionSetVLANPCP p = new ActionSetVLANPCP(vlan_pc);

        Action a1 = new Action(raw);
        assertEquals("type", type, a1.getType());
        assertEquals("length", length, a1.getLength());
        assertTrue("vlan_pc", a1.getPayload().toString().equals("setVLANPCP vlan_pc: " + vlan_pc));

        Action a2 = new Action(type, length, p);
        assertTrue("to header", Arrays.equals(raw, a2.toHeader()));
    }

    @Test
    public void testSetNWTos() throws RawBytesTooFewException, UnknownHostException{
        byte[] raw = {
            (byte) 0x00, (byte) 0x08, (byte) 0x00, (byte) 0x08, 
            (byte) 0xab, (byte) 0x00, (byte) 0x00, (byte) 0x00
        };

        int type = Action.PAYLOAD_SETNWTOS;
        int length = 8;
        short nw_tos = 0xab;
        ActionSetNWToS p = new ActionSetNWToS(nw_tos);

        Action a1 = new Action(raw);
        assertEquals("type", type, a1.getType());
        assertEquals("length", length, a1.getLength());
        assertTrue("nw_tos", a1.getPayload().toString().equals("setNWTos nw_tos: " + nw_tos));

        Action a2 = new Action(type, length, p);
        assertTrue("to header", Arrays.equals(raw, a2.toHeader()));
    }

    @Test
    public void testSetDLSrcDst() throws RawBytesTooFewException, UnknownHostException{
        byte[] raw = {
            (byte) 0x00, (byte) 0x04, (byte) 0x00, (byte) 0x10, 
            (byte) 0xab, (byte) 0xcd, (byte) 0xef, (byte) 0xfe,
            (byte) 0xdc, (byte) 0xba, (byte) 0x00, (byte) 0x00,
            (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00
        };

        int type = Action.PAYLOAD_SETDLSRC;
        int length = 16;
        MACAddress dl_addr = new MACAddress("ab:cd:ef:fe:dc:ba");
        ActionSetDLSrcDst p = new ActionSetDLSrcDst(dl_addr);

        Action a1 = new Action(raw);
        assertEquals("type", type, a1.getType());
        assertEquals("length", length, a1.getLength());
        assertTrue("nw_tos", a1.getPayload().toString().equals("setDL mac: " + dl_addr.toString()));

        Action a2 = new Action(type, length, p);
        assertTrue("to header", Arrays.equals(raw, a2.toHeader()));
    }

    @Test
    public void testSetTCSrcDst() throws RawBytesTooFewException, UnknownHostException{
        byte[] raw = {
            (byte) 0x00, (byte) 0x09, (byte) 0x00, (byte) 0x08,
            (byte) 0xff, (byte) 0xff, (byte) 0x00, (byte) 0x00
        };

        int type = Action.PAYLOAD_SETTPSRC;
        int length = 8;
        int port = 65535;
        ActionSetTCSrcDst p = new ActionSetTCSrcDst(port);

        Action a1 = new Action(raw);
        assertEquals("type", type, a1.getType());
        assertEquals("length", length, a1.getLength());
        assertTrue("nw_tos", a1.getPayload().toString().equals("setTP port: " + port));

        Action a2 = new Action(type, length, p);
        assertTrue("to header", Arrays.equals(raw, a2.toHeader()));
    }
}

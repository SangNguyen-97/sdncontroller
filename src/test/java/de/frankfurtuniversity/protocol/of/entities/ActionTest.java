package de.frankfurtuniversity.protocol.of.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.net.UnknownHostException;
import java.util.Arrays;

import org.junit.Test;

import de.frankfurtuniversity.protocol.of.entities.actionpayload.ActionEnqueue;
import de.frankfurtuniversity.protocol.of.entities.actionpayload.ActionPayload;
import de.frankfurtuniversity.utils.exception.RawBytesTooFewException;

public class ActionTest {
    byte[] raw = { (byte) 0x00, (byte) 0x03, (byte) 0x00, (byte) 0x04 };
    int type = 3;
    int length = 4;
    ActionPayload p = null;

    @Test
    public void testConstructor() throws UnknownHostException, RawBytesTooFewException {
        Action a = new Action(raw);

        assertEquals("type", type, a.getType());
        assertEquals("length", length, a.getLength());
    }

    @Test
    public void testToHeader(){
        Action a = new Action(type, length, p);

        assertTrue("to header", Arrays.equals(raw, a.toHeader()));
    }

    @Test
    public void testEnqueue() throws UnknownHostException, RawBytesTooFewException{
        byte[] raw = {
            (byte) 0x00, (byte) 0x0b, (byte) 0x00, (byte) 0x0f,
            (byte) 0xae, (byte) 0xf4, (byte) 0x00, (byte) 0x00,
            (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
            (byte) 0x45, (byte) 0xd4, (byte) 0x5e, (byte) 0x6a
        };
        int ntype = 0x0b;
        int nlength = 0x0f;
        int port = 0xaef4; // 16 bits
        long queue_id = 0x45d45e6a; // 32 bits
        ActionEnqueue np = new ActionEnqueue(port, queue_id);
    
        Action a1 = new Action(raw);
        assertEquals("type", ntype, a1.getType());
        assertEquals("length", nlength, a1.getLength());
        assertTrue("enqueue port", a1.getPayload().toString().equals("enqueue port: " + port + "  " + "queue_id: " + queue_id));
        
        Action a2 = new Action(ntype, nlength, np);
        assertTrue("to header", Arrays.equals(raw, a2.toHeader()));
    }
}

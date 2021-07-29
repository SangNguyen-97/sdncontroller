package de.frankfurtuniversity.protocol.of.entities.actionpayload;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

public class ActionSetNWTosTest {
    byte[] raw = {
        (byte) 0xfe, (byte) 0x00, (byte) 0x00, (byte) 0x00,
    };
    short nw_tos = 254;

    @Test
    public void testConstructor(){
        ActionSetNWToS a = new ActionSetNWToS(raw);
        assertEquals("constructor ", nw_tos, a.getNwTos());
    }
    
    @Test
    public void testToHeader(){
        ActionSetNWToS a = new ActionSetNWToS(nw_tos);
        assertTrue("to header", Arrays.equals(raw, a.toHeader()));
    }
}

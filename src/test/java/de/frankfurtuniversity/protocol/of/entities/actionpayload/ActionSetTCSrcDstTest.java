package de.frankfurtuniversity.protocol.of.entities.actionpayload;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

public class ActionSetTCSrcDstTest {
    byte[] raw = {
        (byte) 0x19, (byte) 0xec, (byte) 0x00, (byte) 0x00
    };
    int port = 6636;
    
    @Test
    public void testConstructor(){
        ActionSetTCSrcDst a = new ActionSetTCSrcDst(this.raw);
        assertEquals("ActionSetTCSrcDst", this.port, a.getPort());
    }

    @Test
    public void testToHeader(){
        ActionSetTCSrcDst a = new ActionSetTCSrcDst(this.port);

        assertTrue("ActionSetTCSrcDst", Arrays.equals(a.toHeader(), this.raw));
    }
}

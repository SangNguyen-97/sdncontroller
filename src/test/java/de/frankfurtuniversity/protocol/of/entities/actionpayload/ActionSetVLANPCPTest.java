package de.frankfurtuniversity.protocol.of.entities.actionpayload;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

public class ActionSetVLANPCPTest {
    byte[] raw = {
        (byte) 0xfa, (byte) 0x00, (byte) 0x00, (byte) 0x00,
    };
    short vlan_pc = 250;

    @Test
    public void testConstructor(){
        ActionSetVLANPCP a = new ActionSetVLANPCP(raw);
        assertEquals("constructor ", vlan_pc, a.getVlanPc());
    }
    
    @Test
    public void testToHeader(){
        ActionSetVLANPCP a = new ActionSetVLANPCP(vlan_pc);
        assertTrue("to header", Arrays.equals(raw, a.toHeader()));
    }
}

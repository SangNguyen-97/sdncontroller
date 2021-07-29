package de.frankfurtuniversity.protocol.of.entities.actionpayload;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

public class ActionSetVLANVIDTest {
    byte[] raw = {
        (byte) 0x12, (byte) 0x51, (byte) 0x00, (byte) 0x00
    };
    int vlan_id = 4689;
    
    @Test
    public void testConstructor(){
        ActionSetVLANVID a = new ActionSetVLANVID(raw);
        assertEquals("constructor", vlan_id, a.getVlanId());
    }

    @Test
    public void testToHeader(){
        ActionSetVLANVID a = new ActionSetVLANVID(vlan_id);
        assertTrue("to header", Arrays.equals(raw, a.toHeader()));
    }
}

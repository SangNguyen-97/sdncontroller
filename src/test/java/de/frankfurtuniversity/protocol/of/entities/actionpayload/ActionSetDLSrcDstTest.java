package de.frankfurtuniversity.protocol.of.entities.actionpayload;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

import de.frankfurtuniversity.protocol.mac.MACAddress;

public class ActionSetDLSrcDstTest {
    byte[] raw = {
        (byte) 0xaa, (byte) 0xbb, (byte) 0xcc, (byte) 0xdd, (byte) 0xee, (byte) 0xff, 
        (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, 
    };
    MACAddress mac = new MACAddress("AA:BB:CC:DD:EE:FF");
    
    @Test
    public void testConstructor(){
        ActionSetDLSrcDst a = new ActionSetDLSrcDst(raw);
        assertTrue("ActionSetDLSrcDst", Arrays.equals(a.getDl_addr().getAddressBytes(), mac.getAddressBytes()));
    }

    @Test
    public void testToHeader(){
        ActionSetDLSrcDst a = new ActionSetDLSrcDst(mac);
        assertTrue("ActionSetDLSrcDst to header", Arrays.equals(a.toHeader(), raw));
    }
}

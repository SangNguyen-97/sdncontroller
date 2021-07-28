package de.frankfurtuniversity.protocol.mac;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class MACAddressTest {
    @Test
    public void testConstructor(){
        byte[] raw = {
            (byte) 0xaa, (byte) 0xcc, (byte) 0xdd, (byte) 0xbb, (byte) 0xff, (byte) 0xee
        };

        MACAddress mac = new MACAddress(raw);

        assertTrue("MAC constructor", "AA:CC:DD:BB:FF:EE".equals(mac.toString()));
    }
}

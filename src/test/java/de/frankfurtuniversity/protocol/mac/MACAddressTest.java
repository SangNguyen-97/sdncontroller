package de.frankfurtuniversity.protocol.mac;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

public class MACAddressTest {
    byte[] raw = { (byte) 0xaa, (byte) 0xcc, (byte) 0xdd, (byte) 0xbb, (byte) 0xff, (byte) 0xee };

    @Test
    public void testConstructor() {

        MACAddress mac = new MACAddress(raw);

        assertTrue("MAC constructor", "AA:CC:DD:BB:FF:EE".equals(mac.toString()));
    }

    @Test
    public void testConstructorString() {
        String s = "AA:CC:DD:BB:FF:EE";
        MACAddress mac = new MACAddress(s);
        assertTrue("MAC constructor from string", Arrays.equals(raw, mac.getAddressBytes()));
    }
}

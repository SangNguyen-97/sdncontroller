package de.frankfurtuniversity.protocol.of;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class OFPacketBaseTest {
    @Test
    public void testConstructor(){
        byte[] raw = {1,0,58,-104,0,18,-42,-121};
        OFPacketBase p = new OFPacketBase(raw);
        System.out.println(p.toString());
        assertEquals((short) 1, p.version);
        assertEquals((short) 0, p.type);
        assertEquals((int) 15000, p.length);
        assertEquals((long) 1234567, p.xid);
    }


}

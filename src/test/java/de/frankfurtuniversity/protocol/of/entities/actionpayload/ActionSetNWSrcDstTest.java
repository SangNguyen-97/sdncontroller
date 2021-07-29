package de.frankfurtuniversity.protocol.of.entities.actionpayload;

import static org.junit.Assert.assertTrue;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class ActionSetNWSrcDstTest {
    byte[] raw = {
        (byte) 0xc0, (byte) 0xa8, (byte) 0x64, (byte) 0x78
    };
    InetAddress nw_addr;
    
    @Before
    public void init() throws UnknownHostException{
        nw_addr = InetAddress.getByName("192.168.100.120");
    }

    @Test
    public void testConstructor() throws UnknownHostException{
        ActionSetNWSrcDst a = new ActionSetNWSrcDst(raw);
        assertTrue("constructor", a.nw_addr.equals(nw_addr));
    }

    @Test
    public void testToHeader(){
        ActionSetNWSrcDst a = new ActionSetNWSrcDst(nw_addr);
        assertTrue("to header", Arrays.equals(raw, a.toHeader()));
    }
}

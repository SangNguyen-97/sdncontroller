package de.frankfurtuniversity.protocol.of.entities.actionpayload;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

public class ActionOutputTest {
    byte[] raw = {
        (byte) 0x24, (byte) 0xf0, (byte) 0x16, (byte) 0xe8
    };
    int port = 9456;
    int max_len = 5864;

    @Test
    public void testConstructor(){
        ActionOutput a = new ActionOutput(raw);
        assertEquals("port", port, a.getPort());
        assertEquals("max_len", max_len, a.getMaxLen());
    }

    @Test
    public void testToHeader(){
        ActionOutput a = new ActionOutput(port,max_len);
        assertTrue("to header", Arrays.equals(raw, a.toHeader()));
    }
}

package de.frankfurtuniversity.protocol.of.entities.actionpayload;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

public class ActionEnqueueTest {
    byte[] raw = {
        (byte) 0x19, (byte) 0xec, (byte) 0x00, (byte) 0x00,
        (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
        (byte) 0xaa, (byte) 0xbb, (byte) 0xcc, (byte) 0xdd,
    };
    int port = 6636;
    long queue_id = 2864434397L;
    
    @Test
    public void testConstructor(){
        ActionEnqueue a = new ActionEnqueue(this.raw);

        assertEquals("port", this.port, a.getPort());
        assertEquals("queue_id", this.queue_id, a.getQueueID());
    }

    @Test
    public void testToHeader(){
        ActionEnqueue a = new ActionEnqueue(this.port,this.queue_id);
        
        for (int i=0;i<ActionEnqueue.SIZE;i++){
            System.out.println(a.toHeader()[i] + "\t" + raw[i]);
        }
        assertTrue("to header", Arrays.equals(a.toHeader(),raw));
    }
}

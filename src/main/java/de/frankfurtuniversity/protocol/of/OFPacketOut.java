package de.frankfurtuniversity.protocol.of;

import de.frankfurtuniversity.protocol.of.entities.Action;

public class OFPacketOut {
    public static final int SIZE = OFPacketBase.SIZE + 8; // in bytes

    long buffer_id; // 32 bits
    int in_port; // 16 bits
    int actions_len; // 16 bits
    Action[] actions; // variable
    byte[] data; // variable

    
}

package de.frankfurtuniversity;

import java.util.HashMap;

import de.frankfurtuniversity.protocol.of.entities.OFPort;

public class SDNSwitch {

    public enum State {
        ESTABLISHED,
        HALFLIFE,
        HELLO_WAIT,
        FEATURE_WAIT,
        CLOSED
    }

    State s = State.CLOSED;

    long dpid; //64 bits
    long n_buffers; // 32 bits
    int n_tbls; // 8 bits
    int feat_capabilities; // 32 bits
    int feat_actions; // 32 bits
    HashMap<Integer, OFPort> ports; // N * 12 * 32 bits

    // HashMap<Integer, SDNSwitch> neighbors;
}

package de.frankfurtuniversity.protocol.of.entities;
public class Match {
    public static int SIZE = 40; // in bytes

    long wildcards; // 32 bits
    int in_port; // 16 bits
    long dl_src; // 48 bits
    long dl_dst; // 48 bits
    int dl_vlan; // 16 bits
    short dl_pcp; // 8 bits
    // padding 8 bits
    int dl_type; // 16 bits
    short nw_tos; //8 bits
    short nw_proto; // 8 bits
    // padding 16 bits
    long nw_src; // 32 bits
    long nw_dst; // 32 bits
    int tp_src; // 16 bits
    int tp_dst; // 16 bits

    
}

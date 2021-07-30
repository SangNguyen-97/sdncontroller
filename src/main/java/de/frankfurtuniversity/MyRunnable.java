package de.frankfurtuniversity;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;

public class MyRunnable implements Runnable {
    Socket sock;
    HashMap<Integer, SDNSwitch> switchList;
    BufferedInputStream input;
    BufferedOutputStream output;

    public MyRunnable(Socket sock, HashMap<Integer, SDNSwitch> sl) throws IOException {
        this.sock = sock;
        this.switchList = sl;
        try {
            input = new BufferedInputStream(sock.getInputStream());
            output = new BufferedOutputStream(sock.getOutputStream());
        } catch (IOException e) {
            throw e;
        }
    }

    // public static SDNSwitch handshake() {
        
    // }

    public void run() {
        // handshake();
    }

}
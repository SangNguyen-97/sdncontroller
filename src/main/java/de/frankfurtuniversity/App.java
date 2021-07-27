package de.frankfurtuniversity;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class App {

    public static void main(String[] args) {
        System.out.println("Starting SDN controller.");
        System.out.println("Listening for TCP connection from SDN switches...");
        try (ServerSocket ssock = new ServerSocket(6633)) {
            HashMap<Integer, SDNSwitch> switchList = new HashMap<Integer, SDNSwitch>();

            while (true) {
                Socket sock = ssock.accept();
                new Thread(new MyRunnable(sock, switchList));
            }
        } catch (IOException e) {
            System.err.println(e);
        } finally {
            System.out.println("Stopped SDN controller.");
        }
    }
}

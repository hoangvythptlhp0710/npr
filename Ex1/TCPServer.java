package Tut6.Ex1;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    public static void main(String args[]) throws Exception {
        try (ServerSocket welcomeSocket = new ServerSocket(6789)) {
            System.out.println("Server is running...");
            while (true){
                System.out.println("Connecting to client ...");
                CustomThread t1 = new CustomThread(welcomeSocket.accept());
                t1.start();
            }
        }
    }

}

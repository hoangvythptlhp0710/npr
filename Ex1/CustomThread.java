package Tut6.Ex1;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class CustomThread extends Thread {

    private Socket socket;

    public CustomThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (BufferedReader inFromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             DataOutputStream outToClient = new DataOutputStream(socket.getOutputStream());
        ) {
            while (true) {
                String clientSentence = inFromClient.readLine();
                String capitalizedSentence = clientSentence.toUpperCase() + '\n';
                outToClient.writeBytes(capitalizedSentence);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

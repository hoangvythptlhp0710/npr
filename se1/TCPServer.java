package learningTCP.se1;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.Buffer;

public class TCPServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(2231);
        System.out.println("Connecting to client...");
        try {
            Socket socket = serverSocket.accept();
            new CThread(socket).start();
            System.out.println("Connected to client: " + socket.getInetAddress());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}

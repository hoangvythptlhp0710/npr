package learningTCP.Ex1;

import java.io.*;
import java.net.Socket;

public class Server_Process extends Thread{
    Socket socket;
    BufferedReader netIn;
    PrintWriter netOut;
    public Server_Process(Socket socket) {
        this.socket = socket;
        try {
            netIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            netOut = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
        }
       catch (IOException e) {
            e.printStackTrace();
       }
    }

    @Override
    public void run() {
        netOut.println("Hello Client");
        netOut.flush();
        while (true) {
            String command;
            try {
                command = netIn.readLine();
                if (command.equalsIgnoreCase("Quit")) {
                    netOut.println("Bye Client");
                    netIn.close();
                    netOut.close();
                    break;
                }
                else {
                    netOut.println("Server response: " + command.toUpperCase());
                    netOut.flush();
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}

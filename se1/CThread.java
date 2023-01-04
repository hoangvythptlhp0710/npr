package learningTCP.se1;

import java.io.*;
import java.net.Socket;

public class CThread extends Thread{

    BufferedReader netIn;
    PrintWriter netOut;
    Socket socket;

    public CThread(Socket socket) throws IOException {
        this.socket = socket;
        netIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        netOut = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
    }

    @Override
    public void run() {
        netOut.println("Hello user!");
        netOut.flush();
        while (true) {
            String command;
            try {
                command = netIn.readLine();
                if (command.equalsIgnoreCase("Quit")){
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

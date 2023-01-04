package learningTCP.Ex1;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;


public class Client {

    BufferedReader netIn;
    PrintWriter netOut;
    Socket socket;
    BufferedReader userIn;

    public Client() throws UnknownHostException, IOException {
        socket = new Socket("localhost", 2222);
        netIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        netOut = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
    }

    public void main_process() throws IOException {
        String greeting = netIn.readLine();
        System.out.println(greeting);
        while (true) {
            userIn = new BufferedReader(new InputStreamReader(System.in));
            String command = userIn.readLine();
            netOut.println(command);
            netOut.flush();
            String response = netIn.readLine();
            System.out.println(response);
            if (response.equalsIgnoreCase("Bye Client")) {
                netIn.close();
                netOut.close();
                userIn.close();
                break;
            }
        }
    }

    public static void main(String[] args) throws UnknownHostException, IOException{
        new Client().main_process();
    }
}

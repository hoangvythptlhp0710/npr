package SSL;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import java.io.*;

public class LoginServer {

    private static final String username = "vy";
    private static final String password = "123";
    private SSLServerSocket serverSocket;

    public LoginServer() throws Exception {
        SSLServerSocketFactory socketFactory = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
        serverSocket = (SSLServerSocket) socketFactory.createServerSocket(7070);
    }

    private void runServer() {
        while (true) {
            try {
                System.err.println("Waiting for client...");
                SSLSocket socket = (SSLSocket) serverSocket.accept();
                BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter output = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
                String usernameIn = input.readLine();
                String passwordIn = input.readLine();
                if (usernameIn.equals(username) && passwordIn.equals(password))
                    output.println("Welcome back, " + username);
                else {
                    output.println("Login failed!");
                }
                output.close();
                input.close();
                socket.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) throws Exception {
        LoginServer server = new LoginServer();
        server.runServer();
    }
}

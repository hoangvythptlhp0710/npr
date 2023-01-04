package SSL;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.swing.*;
import java.io.*;
import java.nio.Buffer;

public class LoginClient {

    public LoginClient() {
        try {
            SSLSocketFactory socketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            SSLSocket socket = (SSLSocket) socketFactory.createSocket("localhost", 7070);
            PrintWriter output = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            String username = JOptionPane.showInputDialog(null, "Enter username: ");
            output.println(username);
            String password = JOptionPane.showInputDialog(null, "Enter password: ");
            output.println(password);
            output.flush();
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String response = input.readLine();
            JOptionPane.showMessageDialog(null, response);
            output.close();
            input.close();
            socket.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new LoginClient();
    }

}

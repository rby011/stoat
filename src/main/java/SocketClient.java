//
// Source code recreated from Action .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class SocketClient {
    public SocketClient() {
    }

    public static void main(String[] args) {
        String ipString = JOptionPane.showInputDialog("Enter IP Address of Action machine that is\nrunning the date service on Port 9090:");

        try {
            Socket socket = new Socket(ipString, 9090);
            System.out.println("the client start to read the date from the server...");
            String var1 = (new BufferedReader(new InputStreamReader(socket.getInputStream()))).readLine();
            JOptionPane.showMessageDialog(null, var1);
            System.exit(0);
            socket.close();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
}

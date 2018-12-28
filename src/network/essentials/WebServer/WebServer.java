package network.essentials.WebServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class WebServer {

    public static void main(String[] args) {

        try {
            ServerSocket server = new ServerSocket(8080);
            boolean isVallid = false;
            while(!isVallid) {
                //accepting client connections
                Socket socket = server.accept();
                WebServerThread wst = new WebServerThread(socket);
                wst.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

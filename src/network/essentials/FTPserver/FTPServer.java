package network.essentials.FTPserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketOption;

public class FTPServer {

    public static void main(String[] args) {

        try {
            // Initializing server socket
            ServerSocket serverSocket = new ServerSocket(9999);


            //boolean variable to stop the server
            boolean isStopped = false;
            while(!isStopped){
                //accepting a new client connection
                Socket socket = serverSocket.accept();
                ClientConnection clientConnection = new ClientConnection(socket);
                System.out.println("Received request from client to create a connection");
                //starting a new thread for each client
                clientConnection.start();
            }
        } catch (IOException e) {
            System.out.println("9999 port is already being used please use another port");
            e.printStackTrace();
        }
    }
}

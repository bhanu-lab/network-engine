package network.essentials.multithreadedserver;

import java.io.IOException;
import java.net.ServerSocket;

public class MultiThreadedServer {
    public static void main(String[] args){
        try {
            ServerSocket server = new ServerSocket(9090);
            System.out.println("Waiting for client to connect ...");
            boolean stop = false;
            while(!stop) {
                ClientThread client = new ClientThread(server.accept());
                System.out.println("Connecting to client");
                client.start();

                /*PrintWriter clientWriter = new PrintWriter(socket.getOutputStream(), true);
                clientWriter.println("Helloo Client !!!");
                BufferedReader clientReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String clientMessage = clientReader.readLine();
                System.out.println(clientMessage);
                clientWriter.close();
                clientReader.close();
                socket.close();*/
            }
            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

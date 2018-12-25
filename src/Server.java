import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args){
        try {
            ServerSocket server = new ServerSocket(9090);
            System.out.println("Waiting for client to connect ...");
            boolean stop = false;
            while(!stop) {
                Socket socket = server.accept();

                PrintWriter clientWriter = new PrintWriter(socket.getOutputStream(), true);
                clientWriter.println("Helloo Client !!!");
                BufferedReader clientReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String clientMessage = clientReader.readLine();
                System.out.println(clientMessage);
                clientWriter.close();
                clientReader.close();
                socket.close();
            }
            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

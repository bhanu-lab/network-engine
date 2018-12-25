package network.essentials.multithreadedserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread implements Runnable{

    private Socket socket;

    public ClientThread(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            PrintWriter serverWriter = new PrintWriter(socket.getOutputStream(), true);
            serverWriter.println("Hello Client!!!");
            BufferedReader serverReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println(serverReader.readLine());
            serverReader.close();
            serverWriter.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start(){
        Thread clientThread = new Thread(this);
        clientThread.start();
    }

}

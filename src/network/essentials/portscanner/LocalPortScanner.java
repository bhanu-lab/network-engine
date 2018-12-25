package network.essentials.portscanner;

import java.io.IOException;
import java.net.ServerSocket;

public class LocalPortScanner {

    public static void main(String[] args){
        int port = 1;
        while(port <= 65535) {
            try {
                ServerSocket socket = new ServerSocket(port);
                System.out.println("Port " + port + " is open");
            } catch (IOException e) {
                System.out.println("Port " + port + " is already in use");
            }
            port ++;
        }

    }
}

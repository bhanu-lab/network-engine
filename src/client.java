import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class client {

    public static void main(String[] args){
        try {
            InetAddress inetAddress = InetAddress.getByName("localhost");
            Socket client = new Socket(inetAddress.getHostAddress(), 9090);
            PrintWriter writeToServer = new PrintWriter(client.getOutputStream(), true);
            writeToServer.println("Hello dear server!!!");
            BufferedReader readFromServer = new BufferedReader(new InputStreamReader(client.getInputStream()));
            System.out.println(readFromServer.readLine());
            writeToServer.close();
            readFromServer.close();
            client.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

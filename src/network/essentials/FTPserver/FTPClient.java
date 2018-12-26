package network.essentials.FTPserver;

import network.essentials.util.NetworkUtility;
import org.apache.commons.validator.routines.InetAddressValidator;

import java.io.*;
import java.net.Socket;

public class FTPClient {

    public static void main(String[] args) {
        try {

            InputStreamReader in = new InputStreamReader(System.in);
            BufferedReader reader = new BufferedReader(in);

            String ipAddress = "";
            String fiName = "";

            boolean isValid = false;

            //accepting a valid ip address
            while (!isValid) {
                System.out.printf("Please enter a valid IP Address : ");

                ipAddress = reader.readLine();
                InetAddressValidator validator = new InetAddressValidator();
                isValid = validator.isValidInet4Address(ipAddress);

            }

            System.out.printf("Please enter file name to be downloaded : ");
            fiName = reader.readLine();

            //creating a socket to connect to server
            Socket socket = new Socket(ipAddress, 9999);

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println(fiName);

            System.out.println("written file name to output stream");

            BufferedInputStream input = new BufferedInputStream(socket.getInputStream());
            byte[] buffer = new byte[1024];
            int bytesRead = 0;

            int code = input.read();

            if(1 == code) {
                //reading download location from property file
                FileOutputStream fi = new FileOutputStream(NetworkUtility.getProperty("ftpDownloadFileLocation") + fiName);
                while ((bytesRead = input.read(buffer)) != -1) {
                    fi.write(buffer, 0, bytesRead);

                }
                System.out.println("File Downladed successfully");
            }else if(0 == code){
                System.out.print("Entered file name doesnt exist on server");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

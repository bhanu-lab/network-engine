package network.essentials.FTPserver;

import network.essentials.util.NetworkUtility;

import java.io.*;
import java.net.Socket;

public class ClientConnection implements Runnable{

    private Socket socket;
    private BufferedOutputStream out;
    private BufferedReader reader;
    private BufferedInputStream in;

    public ClientConnection(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        try {

            //reading file name to transfer contents
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("got the reader object");

            out = new BufferedOutputStream(socket.getOutputStream());

            //server will wait here to read client file name
            String fileName = reader.readLine();
            String location = NetworkUtility.getProperty("folderToSearch") +fileName;
            System.out.println("File Name "+location+" is requested by client with ipaddress "+socket.getInetAddress().getHostAddress());

            //creating a file object
            File file = new File(location);

            //if file exists transfer data to output stream
            if(file.exists()){
                out.write((byte)1);
                in = new BufferedInputStream(new FileInputStream(file));

                byte[] buffer = new byte[1024];

                int bytesRead = 0;

                while((bytesRead = in.read(buffer)) != -1){
                    out.write(buffer, 0, bytesRead);
                    out.flush();
                }

                closeConnection();
            }else{
                System.out.println("file doesn't exist on server");
                out.write((byte)0);

                closeConnection();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * closes all resources opened in this class
     * @throws IOException
     */
    private void closeConnection() throws IOException {
        if(null != out) {
            out.close();
        }
        if(null != socket) {
            socket.close();
        }
        if(null != reader) {
            reader.close();
        }
        if(null != in) {
            in.close();
        }
    }

    public void start(){
        Thread tr = new Thread(this);
        tr.start();
        System.out.println("Starting new thread");
    }
}

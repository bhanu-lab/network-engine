package network.essentials.WebServer;

import java.io.*;
import java.net.Socket;
import java.util.Date;

public class WebServerThread implements Runnable{
    private Socket socket;
    private BufferedReader reader;
    private BufferedOutputStream out;
    private String htmlFilesFolder = "/home/bhanureddy/Documents/";

    public WebServerThread(Socket socket){
        this.socket = socket;
    }


    @Override
    public void run() {
        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //out = new BufferedOutputStream(new FileOutputStream());
            String fileName = "";
            String httpHeader = "";

            boolean isValid = true;

            while(isValid){
                String line = reader.readLine();

                if(line.equals("\r\n")||(line.equals(""))){
                    break;
                }else{
                    httpHeader = httpHeader + line +"\n";
                    if(line.contains("GET")){
                        int begin = line.indexOf("/");
                        int end = line.lastIndexOf("HTTP");

                        fileName = line.substring(begin+1,end);
                    }
                }
            }
            // HTTP Request
            System.out.println(httpHeader);
            fileName = fileName.trim();
            //File name to be sent
            System.out.println("HTML File name received is : "+fileName);

            //validating file
            File file = new File(htmlFilesFolder+fileName);
            if(file.exists()){

                System.out.println("File is present to send as html");
                out = new BufferedOutputStream(socket.getOutputStream());

                //sending HTTP response constructed to client
                processHTTPResponse(out);
                System.out.println("HTTP Response is sent to the client");

                BufferedInputStream fileReader = new BufferedInputStream(new FileInputStream(file));
                while(fileReader.read() != -1){
                    byte[] data = fileReader.readAllBytes();
                    System.out.println("Data read from html file is : "+new String(data));
                    out.write(data);
                }
                out.flush();
                System.out.println("HTML file is sent to client");
            }
            closeConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void processHTTPResponse(BufferedOutputStream out) {
        try {
            out.write("HTTP/1.1 200 OK".getBytes());
            Date date = new Date();
            out.write(("Date: "+date.toString()).getBytes());
            out.write("Server: Apache/2.2.14 (Win32))".getBytes());
            out.write("Last-Modified: Wed, 22 Jul 2009 19:15:56 GMT".getBytes());
            out.write("Content-Length: 88".getBytes());
            out.write("Content-Type: text/html".getBytes());
            out.write("Connection: Closed".getBytes());
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start(){
        Thread thread = new Thread(this);

        thread.start();
        System.out.println("Started Client thread!!!");
    }

    /*
        Close all opened resources
     */
    public void closeConnection(){
        try {
            if(null != reader){
                reader.close();
            }
            if(null != out){
                out.close();
            }
            if(null != socket){
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

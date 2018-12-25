package network.essentials.streams;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class TestFileOutputStream {

    public static void main(String[] args){
        try {
            FileOutputStream outputStream = new FileOutputStream("/home/bhanureddy/IdeaProjects/JavaNetworking/resources/example2.txt");
            String test = "Learning about output stream";
            outputStream.write(test.getBytes());
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

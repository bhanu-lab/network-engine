package network.essentials.streams;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TestFileInputStream {

    public static void main(String[] args){
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream("/home/bhanureddy/IdeaProjects/JavaNetworking/resources/example1.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            int read = inputStream.read();
            while(read != -1){
                System.out.print((char)read);
                read = inputStream.read();
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
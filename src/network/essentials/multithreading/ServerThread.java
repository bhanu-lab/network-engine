package network.essentials.multithreading;

public class ServerThread extends Thread{


    ServerThread(String threadName){
        this.setName(threadName);
    }

    public void run(){
        int clientNum = 1;
        while(clientNum <= 100) {
            System.out.println("Client "+this.getName()+" to be sent is : " + clientNum);
            clientNum++;
        }
    }


}

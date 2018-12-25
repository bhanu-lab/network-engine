package network.essentials.multithreading;

public class ImplementsRunnable implements Runnable {

    private int threadIndex;
    public ImplementsRunnable(int threadIndex){
        this.threadIndex = threadIndex;
    }

    @Override
    public void run() {
        int clientNum = 1;
        while(clientNum <= 100) {
            System.out.println("Client"+threadIndex+"  to be sent is : " + clientNum);
            clientNum++;
        }
    }

    public void start(){
        Thread thread = new Thread(this);
        thread.start();
    }
}

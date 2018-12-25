package network.essentials.multithreading;

public class Main {

    public static void main(String[] args) {
        ServerThread thread = new ServerThread("Client 1");
        thread.start();
        thread.setPriority(Thread.MIN_PRIORITY);
        ServerThread thread2 = new ServerThread("Client 2");
        thread2.start();
        thread2.setPriority(Thread.MAX_PRIORITY);
        ServerThread thread3 = new ServerThread("Client 3");
        thread3.start();
        thread3.setPriority(Thread.NORM_PRIORITY);
    }
}

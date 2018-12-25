package network.essentials.multithreading;

public class RunRunnable {

    public static void main(String[]  args){
        ImplementsRunnable runnable = new ImplementsRunnable(1);
        Thread thread = new Thread(runnable, "Server 1");
        thread.start();
        Thread.yield();
        ImplementsRunnable runnable1 = new ImplementsRunnable(2);
        Thread thread2 = new Thread(runnable1, "Server 2");
        thread2.start();
        Thread.yield();

    }
}

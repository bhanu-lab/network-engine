package network.essentials.multithreading;

public class OddEvenPrint {

    private boolean odd =false;
    private int count = 1;
    private int MAX = 100;

    private void printEven(){

        synchronized (this) {
            while(count <= MAX){
                while(odd){
                    System.out.println("Even Number is : "+count);
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                count ++;
                odd = true;
                notify();
            }
        }
    }

    private void printOdd(){
        synchronized (this){
            while(count < MAX){
                while(!odd){
                    System.out.println("Odd Number is: "+count);
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                count ++;
                odd = false;
                notify();
            }
        }
    }

    public static void main(String[] args){
        OddEvenPrint oep = new OddEvenPrint();
        Thread t1 = new Thread(() -> oep.printOdd());

        Thread t2 = new Thread(() -> oep.printEven());

        t1.start();
        t2.start();


        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

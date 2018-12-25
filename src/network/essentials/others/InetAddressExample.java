package network.essentials.others;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressExample {
    public static void main(String[] args){
        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            System.out.println("host name is "+inetAddress.getHostName());
            System.out.println("host address is "+inetAddress.getHostAddress());

            byte[] addr = new byte[4];
            addr[0] = (byte)192;
            addr[1] = (byte)168;
            addr[2] = (byte)0;
            addr[3] = (byte)101;
            System.out.println("Byte ar is : "+addr.length);


            InetAddress inet2 = InetAddress.getByAddress(addr);
            System.out.println("host name is "+inet2.getCanonicalHostName());

            /*InetAddress[] inet3 = InetAddress.getAllByName("oneplus");
            System.out.println(inet3[0].getHostAddress());*/

            InetAddressExample example = new InetAddressExample();
            try {
                example.checkHosts("192.168.0");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public void checkHosts(String subnet) throws IOException {
        int timeout=1000;
        for (int i=1;i<255;i++){
            String host=subnet + "." + i;
            if (InetAddress.getByName(host).isReachable(timeout)){
                System.out.println(host + " is reachable");
            }
        }
    }
}

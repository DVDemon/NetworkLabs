import java.io.*;
import java.net.*;
import java.util.*;

public class Ex00_NetworkConfiguration {
     public static void main(String args[]) 
      throws SocketException , IOException
    {
        Enumeration<NetworkInterface> nets = 
            NetworkInterface.getNetworkInterfaces();
        for (NetworkInterface netint : Collections.list(nets))
            displayInterfaceInformation(netint);
       
        
        System.in.read();
    }

    static void displayInterfaceInformation(NetworkInterface netint) 
      throws SocketException 
    {
        System.out.println("Display name: " 
           + netint.getDisplayName());
        System.out.println("Hardware address: " 
           + Arrays.toString(netint.getHardwareAddress()));
        
        Enumeration<InetAddress> addresses = netint.getInetAddresses();
        
        for(InetAddress adr: Collections.list(addresses))
        System.out.println("address: " + adr.toString());
        
    }   
}

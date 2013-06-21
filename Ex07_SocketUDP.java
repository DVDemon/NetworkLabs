import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

public class Ex07_SocketUDP 
{
   public static void main(String[] args) throws IOException {
        new Ex07_SocketUDP();
    }

    public Ex07_SocketUDP() throws IOException {
        new Thread(new Runnable() {
            public void run() {
                server();
            }
        }).start();

        System.in.read();
        client();
        System.in.read();

    }

    public void server() {
        try {
              DatagramSocket ds = new DatagramSocket(8080, Inet4Address.getLocalHost());
              DatagramPacket packet_input = new DatagramPacket(new byte[4096], 4096);
              ds.receive(packet_input);
                
              System.out.println("Server: received");
              CharBuffer cb = Charset.defaultCharset().decode(ByteBuffer.wrap(packet_input.getData()));
              ByteBuffer buffer_out = Charset.defaultCharset().encode("Hello  "+new String(cb.array()));
              byte[] buffer_array = buffer_out.array();
                
              DatagramPacket packet = new DatagramPacket(buffer_array, buffer_array.length, Inet4Address.getLocalHost(),7070);
              ds.send(packet);
                
              System.out.println("Server: send");                

        } catch (java.io.IOException ex) {
            ex.printStackTrace();
        }

    }

    public void client() {
        try {
                DatagramSocket ds = new DatagramSocket(7070, Inet4Address.getLocalHost());
                ByteBuffer buffer_out = Charset.defaultCharset().encode("Little Datagram Client");
                byte[] buffer_array = buffer_out.array();
                
                DatagramPacket packet = new DatagramPacket(buffer_array, buffer_array.length, Inet4Address.getLocalHost(),8080);
                ds.send(packet);
                
                DatagramPacket packet_input = new DatagramPacket(new byte[4096], 4096);
                ds.receive(packet_input);
                CharBuffer cb = Charset.defaultCharset().decode(ByteBuffer.wrap(packet_input.getData()));
                
                System.out.println("Client:"+new String(cb.array()).trim());
                 
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

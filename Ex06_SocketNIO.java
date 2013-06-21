
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.io.*;
import java.net.*;

public class Ex06_SocketNIO {

    public static void main(String[] args) throws IOException {
        new Ex06_SocketNIO();
    }

    public Ex06_SocketNIO() throws IOException {
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
            ByteBuffer buffer = ByteBuffer.allocate(4096);
            

            ServerSocketChannel ssc = ServerSocketChannel.open();
            ssc.socket().bind(new InetSocketAddress(8080));
            ssc.configureBlocking(false);

            System.out.println("Server: waiting clients");

            while (true) {
                SocketChannel sc = ssc.accept();
                if (sc == null) {
                   // System.out.println("Dead slience, let's sleep for a while");
                    Thread.sleep(500);
                } else {
                    System.out.println("Server: connected to: " + sc.socket().getRemoteSocketAddress());
                    buffer.rewind();

                    StringBuilder sb = new StringBuilder(4096);

                    char cb = 0;
                    boolean cont;
                    do
                    {
                        cont = (sc.read(buffer) != -1);
                        if(cont)
                        {
                        buffer.flip();
                        CharBuffer array = Charset.defaultCharset().decode(buffer);

                        
                        do {
                            cb = array.get();
                            sb.append(cb);
                            if(cb=='\n') cont = false;
                        } while (array.hasRemaining()&&(cont));
                        buffer.clear();
                        }
                    }while(cont);
                    System.out.println("Server: message "+sb.toString());
                    
                    ByteBuffer buffer_out = Charset.defaultCharset().encode("Hello from NIO "+sb.toString()+"\r\n");
                    buffer_out.wrap(buffer_out.array());
                    sc.write(buffer_out);
                    sc.close();
                }
            }


            }   catch (java.io.IOException ex) {
            ex.printStackTrace();
        } catch (java.lang.InterruptedException ex) {
            ex.printStackTrace();
        }
    }

public void client() {
        try {
            InetAddress addr = Inet4Address.getLocalHost();
            Socket server = new Socket(addr, 8080);
            System.out.println("Client: connected");

            BufferedReader is = new BufferedReader(new InputStreamReader(server.getInputStream()));
            BufferedWriter os = new BufferedWriter(new OutputStreamWriter(server.getOutputStream()));

            os.write("Little Client\r\n");
            os.flush();

            String input = is.readLine();
            System.out.println("Client:" + input);

            server.shutdownOutput();
            server.shutdownInput();
            server.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

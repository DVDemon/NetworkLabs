
import java.io.*;
import java.net.*;

public class Ex05_SocketTCP {

    public static void main(String[] args) throws IOException {
        new Ex05_SocketTCP();
    }

    public Ex05_SocketTCP() throws IOException {
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
            ServerSocket server_socket = new ServerSocket(8080);

            System.out.println("Server: waiting clients");
            Socket client_socket = server_socket.accept();

            System.out.println("Server: connected to " + client_socket.getRemoteSocketAddress().toString());

            BufferedReader is = new BufferedReader(new InputStreamReader(client_socket.getInputStream()));
            BufferedWriter os = new BufferedWriter(new OutputStreamWriter(client_socket.getOutputStream()));

            // first part of protocol - read line
            String input = is.readLine();

            // seconf part of protocol - write line
            os.write("Hello world " + input + "!");
            os.flush();

            client_socket.shutdownOutput();
            client_socket.shutdownInput();
            client_socket.close();
            System.out.println("Server: disconnected");

        } catch (java.io.IOException ex) {
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

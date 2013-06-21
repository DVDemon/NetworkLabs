import java.io.IOException;
import java.net.*;
import java.io.*;

public class Ex03_HTTP {

    public static void main(String[] args) throws java.net.MalformedURLException, java.io.IOException {
        //Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("123.0.0.1", 8080));
        URL url = new URL("http://www.mai.ru");
        HttpURLConnection uc = (HttpURLConnection) url.openConnection();//(proxy);
        uc.connect();

        String line;
        StringBuffer page = new StringBuffer();
        BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
        while ((line = in.readLine()) != null) {
            page.append(line + "\n");
        }
        System.out.println(page);
    }
}

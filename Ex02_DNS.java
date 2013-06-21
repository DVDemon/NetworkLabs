import org.xbill.DNS.*;
import java.io.*;
import java.net.*;

/**
 *
 * @author Sony
 */
public class Ex02_DNS {
        public static String reverseDns(String hostIp) throws IOException {
             Record opt = null;
             Resolver res = new ExtendedResolver();

             Name name = ReverseMap.fromAddress(hostIp);
             int type = Type.PTR;
             int dclass = DClass.IN;
             Record rec = Record.newRecord(name, type, dclass);
             Message query = Message.newQuery(rec);
             Message response = res.send(query);

             Record[] answers = response.getSectionArray(Section.ANSWER);
             if (answers.length == 0)
                return hostIp;
             else
                return answers[0].rdataToString();
       }
    public static void main(String args[]) throws IOException {
        long now = System.currentTimeMillis();
        InetAddress addr = InetAddress.getByName("www.mai.ru");
        System.out.println(addr.getHostName());
        System.out.println(addr.toString());
        
        long after = System.currentTimeMillis();
        System.out.println((after - now) + " ms");
        now = System.currentTimeMillis();
       // System.out.println(reverseDns("192.222.1.13"));
        System.out.println(reverseDns("77.88.21.3"));
        after = System.currentTimeMillis();
        System.out.println((after - now) + " ms");
        
    } 
}

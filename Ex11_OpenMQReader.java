
import javax.jms.*;

public class Ex11_OpenMQReader {

    public static void main(String[] args) throws Exception {
        com.sun.messaging.ConnectionFactory myConnFactory;
        Queue myQueue;
        myConnFactory = new com.sun.messaging.ConnectionFactory();
        Connection myConn = myConnFactory.createConnection();
        Session mySess = myConn.createSession(false, Session.AUTO_ACKNOWLEDGE);
        myQueue = new com.sun.messaging.Queue("MyQueue");

        MessageConsumer myMsgConsumer = mySess.createConsumer(myQueue);
        myConn.start();


        try {
            while (true) {
                Message msg = myMsgConsumer.receive();
                if (msg instanceof TextMessage) {
                    TextMessage txtMsg = (TextMessage) msg;
                    System.out.println("Read Message: " + txtMsg.getText());
                }
            }
        } catch (Exception ex) {
        }
         mySess.close ();
         myConn.close ();
    }


}


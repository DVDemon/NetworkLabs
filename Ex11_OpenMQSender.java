import javax.jms.*;



public class Ex11_OpenMQSender {
    
    public static void main(String [] args) throws Exception
    {
            com.sun.messaging.ConnectionFactory myConnFactory;
            Queue myQueue;
            myConnFactory = new com.sun.messaging.ConnectionFactory();
            Connection myConn = myConnFactory.createConnection();
            Session mySess = myConn.createSession(false, Session.AUTO_ACKNOWLEDGE);
            myQueue = new com.sun.messaging.Queue("MyQueue");

            MessageProducer myMsgProducer = mySess.createProducer(myQueue);
            
            for(int i=0;i<100000;i++)
            {
            TextMessage myTextMsg = mySess.createTextMessage();
            myTextMsg.setText("This is message number: "+i);
            myMsgProducer.send(myTextMsg);
            }
            System.out.println("Done!");

            mySess.close();
            myConn.close();
        }
}

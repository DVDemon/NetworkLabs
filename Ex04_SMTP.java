import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class Ex04_SMTP {
 	public static void main(String[] args) {
		String host = "smtp.yandex.ru";
		int port = 25;
 
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
                props.put("mail.smtp.host", host);
                props.put("mail.smtp.port", port);
                 
 
		Session session = Session.getInstance(props,new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("net.mai","2d3443d5f346gd2");
			}
		});

 
		try {
 
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("net.mai@yandex.ru"));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse("netexample@yandex.ru"));
			message.setSubject("Go away!");
			message.setText("Dear Mail Crawler," +
					"\n\n No spam to my email, please!");
			Transport.send(message);
 
			System.out.println("Done");
 
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}   
}

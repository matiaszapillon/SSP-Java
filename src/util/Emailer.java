package util;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class Emailer {
	
	public static Emailer instance;
	
	private Properties props;
	
	public static Emailer getInstance(){
		if (instance==null){
			instance=new Emailer();
		}
		return instance;
	}
	
	private Emailer() {
		//TIRA ERROR INPUT STREAM NULL Y TAMBIEN OTRO ERROR DE CREDENCIALES INCORRECTAS


		props = new Properties();
//	if(inputStream != null) {
//			props.load(inputStream);	
//			}

		 props.put("mail.smtp.auth","true");
		 props.put("mail.smtp.starttls.enable","true");
		 props.put("mail.smtp.host","smtp.gmail.com");
		 props.put("mail.smtp.port","587");
		 props.put("mail.username","ortecnologiassp");
		 props.put("mail.password","OR123456789");
		

	}
	
	public void send(String to,String subject,String body){

		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("ortecnologiassp@gmail.com","OR123456789");
			//  return new PasswordAuthentication(props.getProperty("mail.username"), props.getProperty("mail.password"));
			}
		  });

		try {

			Message message = new MimeMessage(session);
			//message.setFrom(new InternetAddress(props.getProperty("mail.username")));
			message.setFrom(new InternetAddress("ortecnologiassp@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,InternetAddress.parse("matiaszapillon@gmail.com")); //"matiaszapillon@gmail.com"
			//message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to)); //"matiaszapillon@gmail.com"
			message.setSubject(subject); //"The project has finished
			message.setText(body); //"Dear *NombreCliente, your project *NombreProyecto has already finished, you can 
									// check the state in the Web www.http://localhost:8080/SSP/logIn.html , cheers."

			Transport.send(message);

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

}
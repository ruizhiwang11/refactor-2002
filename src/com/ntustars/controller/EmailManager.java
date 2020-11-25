package com.ntustars.controller;

import javax.mail.PasswordAuthentication;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 Controller to control all the student related operation.
 @author FENG HAOLIN
 @version 1.0
 @since 2020-11-10
 */

public class EmailManager extends SendNotificatoin {

	/**
     * The gmail address
     */
	private static String username = "mySTARSTAR@gmail.com";
	/**
     * The gmail password
     */
	private static String password = "newPassword"; 

	/**
     * Initialize the email receiver
     * @param usernameTo email address of the receiver
     */
	public EmailManager(String usernameTo) {
		super("adminSCSE@e.ntu.edu.sg",usernameTo+ "@e.ntu.edu.sg");

	}

	/**
    send email notification to student
     */
	@Override
	public void sendNotificationsToStudent() {
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(usernameFrom));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(usernameTo));
			message.setSubject("Notification");  
			message.setText("Hello, you have been registered to the course");  
 
			Transport.send(message);  
 
	 	}catch (MessagingException mex) {mex.printStackTrace();}  

	}
		
}
package com.example.WebBanVe.Utils;

import java.util.Properties;
import java.util.Random;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class Email {
	public static String sendPassword(String receiverEmail, String receiverPassword) {
	String toEmail = receiverEmail;
	String fromEmail = "vunam3002931@gmail.com";
	String password = "ydnh imuu kyox xjzt";

	try {
		Properties pr = configEmail(new Properties());

		Session session = Session.getInstance(pr, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
		});

		Message mess = new MimeMessage(session);
		mess.setHeader("Content-Type", "text/plain; charset=UTF-8");

		mess.setFrom(new InternetAddress(fromEmail));

		mess.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));

		mess.setSubject("Confirm Code");

		mess.setText(receiverPassword);

		Transport.send(mess);
		
		return "Success";
	} catch (Exception e) {
		e.printStackTrace();
		return null;
	}
}
	public static String sendOTP(String receiverEmail) {
		String otp = getRandom();
		
		String toEmail = receiverEmail;
		String fromEmail = "vunam3002931@gmail.com";
		String password = "ydnh imuu kyox xjzt";

		try {
			Properties pr = configEmail(new Properties());

			Session session = Session.getInstance(pr, new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(fromEmail, password);
				}
			});

			Message mess = new MimeMessage(session);
			mess.setHeader("Content-Type", "text/plain; charset=UTF-8");

			mess.setFrom(new InternetAddress(fromEmail));

			mess.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));

			mess.setSubject("Confirm Code");

			mess.setText("Your code is: " + otp);

			Transport.send(mess);
			
			return otp;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public static Properties configEmail(Properties pr) {
		pr.setProperty("mail.smtp.host", "smtp.gmail.com");
		pr.setProperty("mail.smtp.port", "587");
		pr.setProperty("mail.smtp.auth", "true");
		pr.setProperty("mail.smtp.starttls.enable", "true");
		pr.put("mail.smtp.socketFactory.port", "587");
		pr.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

		return pr;
	}
	public static String getRandom() {
		Random rnd = new Random();
		int number = rnd.nextInt(999999);
		return String.format("%06d", number);
	}
}

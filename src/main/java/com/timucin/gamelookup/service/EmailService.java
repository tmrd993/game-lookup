package com.timucin.gamelookup.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailService {
	
	private final JavaMailSender javaMailSender;
	
	@Value("${primary_contact_email}")
	private String contactEmail;
	
	public EmailService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;	
	}
	
	public void sendSimpleMessage(String name, String contactFormEmail, String message) {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setFrom(contactEmail);
		simpleMailMessage.setTo(contactEmail);
		
		simpleMailMessage.setSubject("Contact Form message from " + name);
		String messageText = "Message from: " + name + ", " + contactEmail + "\n\n" + message;
		simpleMailMessage.setText(messageText);
		javaMailSender.send(simpleMailMessage);
	}

}

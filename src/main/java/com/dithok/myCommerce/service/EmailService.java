package com.dithok.myCommerce.service;

import org.springframework.mail.MailException;

public interface EmailService {

	public void sendEmail(String email, String subject, String body) throws MailException;	
	
}
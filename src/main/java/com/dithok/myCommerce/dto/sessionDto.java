package com.dithok.myCommerce.dto;

import org.springframework.stereotype.Component;

//The data transfer object that is used for user login
@Component
public class sessionDto {
	private String email;
	private String sessionstatus;
	private int sessionInactiveInterval;
    
    
	
	public sessionDto() {}

	public sessionDto(String email, String sessionstatus) {
		super();
		this.email = email;
		this.sessionstatus = sessionstatus;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getsessionstatus() {
		return sessionstatus;
	}

	public void setStatus(String sessionstatus) {
		this.sessionstatus = sessionstatus;
	}

	public int getSessionInactiveInterval() {
		return sessionInactiveInterval;
	}

	public void setSessionInactiveInterval(int sessionInactiveInterval) {
		this.sessionInactiveInterval = sessionInactiveInterval;
	}

	

	
	
	
	
}

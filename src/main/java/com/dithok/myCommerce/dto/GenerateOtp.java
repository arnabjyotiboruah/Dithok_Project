package com.dithok.myCommerce.dto;

import org.springframework.stereotype.Component;

@Component
public class GenerateOtp {
    // private String email;
    private String otp;
    private long createdTime;
	public String email;
	

	public GenerateOtp() {}

	public GenerateOtp(String email) {
		super();
		this.email = email;
	}
    
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}
	
	public long getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(long createdTime) {
		this.createdTime = createdTime;
	}
}
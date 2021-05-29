package com.dithok.myCommerce.dto;

import org.springframework.stereotype.Component;

//The data transfer object that is used for user login
@Component
public class LoginDto {
	private String email;
	private String password;
	//private String role;
	//private String exceptionMsg;
	
	public LoginDto() {}

	public LoginDto(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}



//	public LoginDto(String exceptionMsg)
//	{
//		super();
//		this.exceptionMsg=exceptionMsg;
//	}
//
//	public String getExceptionMsg() {
//		return exceptionMsg;
//	}
//
//	public void setExceptionMsg(String exceptionMsg) {
//		this.exceptionMsg = exceptionMsg;
//	}
//
//	public String getRole() {
//		return role;
//	}
//
//	public void setRole(String role) {
//		this.role = role;
//	}
//	
	
	
}

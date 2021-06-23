package com.dithok.myCommerce.dto;

import org.springframework.stereotype.Component;

@Component
public class GroupUserDto {
	private long id;
	private String email;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public GroupUserDto(long id, String email) {
		this.id = id;
		this.email = email;
	}
	public GroupUserDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}

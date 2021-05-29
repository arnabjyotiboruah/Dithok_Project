package com.dithok.myCommerce.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.dithok.myCommerce.model.AddressEmbeddable;

//The data transfer object that is used for user registration
public class RegisterDto {
	private String firstName;
	private String middleName;
	private String lastName;
	private String email;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birthDate;
	private String phoneNumber;
	private String password;
	private String message;
	private AddressEmbeddable address;
	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public  RegisterDto() {}


	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getBirthDate() {
		return birthDate ;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	
	/**
	 * @return the address
	 */
	public AddressEmbeddable getAddress() {
		return address;
	}


	/**
	 * @param address the address to set
	 */
	public void setAddress(AddressEmbeddable address) {
		this.address = address;
	}


	public RegisterDto(String email, String phoneNumber) {
		super();
		this.email = email;
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @param firstName
	 * @param middleName
	 * @param lastName
	 * @param email
	 * @param birthDate
	 * @param phoneNumber
	 * @param password
	 * @param message
	 * @param address
	 */
	public RegisterDto(String firstName, String middleName, String lastName, String email, Date birthDate,
			String phoneNumber, String password, String message, AddressEmbeddable address) {
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.email = email;
		this.birthDate = birthDate;
		this.phoneNumber = phoneNumber;
		this.password = password;
		this.message = message;
		this.address = address;
	}

	public RegisterDto(String message) {
		super();
		this.message = message;
	}

}

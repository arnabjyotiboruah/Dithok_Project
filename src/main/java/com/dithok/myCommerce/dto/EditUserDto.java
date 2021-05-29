package com.dithok.myCommerce.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

//The data transfer object that is used for user registration
public class EditUserDto {
	private String firstName;
	private String middleName;
	private String lastName;
	private String email;
    private String house_number;
    private String street;
    private String city;
    private String state;
    private String country;
	private String zip_code;
	private String latitude;
	private String longitude;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birthDate;
    
    private String phoneNumber;
	private long id;

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @return the middleName
	 */
	public String getMiddleName() {
		return middleName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @return the house_number
	 */
	public String getHouse_number() {
		return house_number;
	}

	/**
	 * @return the street
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @return the zip_code
	 */
	public String getZip_code() {
		return zip_code;
	}

	/**
	 * @return the latitude
	 */
	public String getLatitude() {
		return latitude;
	}

	/**
	 * @return the longitude
	 */
	public String getLongitude() {
		return longitude;
	}

	/**
	 * @return the birthDate
	 */
	public Date getBirthDate() {
		return birthDate;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @param middleName the middleName to set
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @param house_number the house_number to set
	 */
	public void setHouse_number(String house_number) {
		this.house_number = house_number;
	}

	/**
	 * @param street the street to set
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @param zip_code the zip_code to set
	 */
	public void setZip_code(String zip_code) {
		this.zip_code = zip_code;
	}

	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	/**
	 * @param birthDate the birthDate to set
	 */
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @param firstName
	 * @param middleName
	 * @param lastName
	 * @param email
	 * @param house_number
	 * @param street
	 * @param city
	 * @param state
	 * @param country
	 * @param zip_code
	 * @param latitude
	 * @param longitude
	 * @param birthDate
	 * @param phoneNumber
	 * @param id
	 */
	public EditUserDto(String firstName, String middleName, String lastName, String email, String house_number,
			String street, String city, String state, String country, String zip_code, String latitude,
			String longitude, Date birthDate, String phoneNumber, long id) {
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.email = email;
		this.house_number = house_number;
		this.street = street;
		this.city = city;
		this.state = state;
		this.country = country;
		this.zip_code = zip_code;
		this.latitude = latitude;
		this.longitude = longitude;
		this.birthDate = birthDate;
		this.phoneNumber = phoneNumber;
		this.id = id;
	}

	/**
	 * 
	 */
	public EditUserDto() {
		super();
		// TODO Auto-generated constructor stub
	}

    
}

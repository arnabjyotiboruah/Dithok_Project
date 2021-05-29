package com.dithok.myCommerce.dto;

public class UserAddressDto{
    
    private String house_number;
    private String street;
    private String city;
    private String state;
    private String country;
	private String zip_code;
	private String latitude;
	private String longitude;
    private String email;

    
    public String getHouse_number(){
        return house_number;
    }

    public void setHouse_number(String house_number){
        this.house_number = house_number;
    }

    public String getStreet(){
        return street;
    }

    public void setStreet(String street){
        this.street = street;
    }

    public String getCity(){
        return city;
    }

    public void setCity(String city){
        this.city = city;
    }
    
    public String getCountry(){
        return country;
    }

    public void setCountry(String country){
        this.country = country;
    }

    public String getState(){
        return state;
    }

    public void setState(String state){
        this.state = state;
    }

    public String getZip_code(){
        return zip_code;
    }

    public void setZip_code(String zip_code){
        this.zip_code = zip_code;
    }

    public String getLatitude(){
        return latitude;
    }

    public void setLatitude(String latitude){
        this.latitude = latitude;
    }
    
    public String getLongitude(){
        return longitude;
    }

    public void setLongitude(String longitude){
        this.longitude = longitude;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public UserAddressDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserAddressDto(
        String house_number, 
        String street, 
        String city, 
        String state,
        String country, 
        String zip_code, 
        String latitude, 
        String longitude, 
        String email
    ){
        this.house_number = house_number;
        this.street = street;
        this.city = city;
        this.state = state;
        this.country = country;
        this.zip_code = zip_code;
        this.latitude = latitude;
        this.longitude = longitude;
        this.email = email;

    }

}
	

package com.dithok.myCommerce.model;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Embeddable
public class AddressEmbeddable {
    @NotBlank
    @Size(max = 1024)
    private String addressLine1;

   // @NotBlank
    @Size(max = 1024)
    private String addressLine2;

    @NotBlank
    @Size(max = 128)
    private String city;

    @NotBlank
    @Size(max = 128)
    private String state;

	@NotBlank
    @Size(max = 128)
    private String country;

    @NotBlank
    @Size(max = 6)
    private String zipCode;

	//@NotBlank
    private String longitude;
	
	//@NotBlank
    private String  latitude;

   public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public AddressEmbeddable() {

    }

	/**
	 * @param addressLine1
	 * @param addressLine2
	 * @param city
	 * @param state
	 * @param country
	 * @param zipCode
	 * @param longitude
	 * @param latitude
	 */
	public AddressEmbeddable(@NotBlank @Size(max = 1024) String addressLine1, @Size(max = 1024) String addressLine2,
			@NotBlank @Size(max = 128) String city, @NotBlank @Size(max = 128) String state,
			@NotBlank @Size(max = 128) String country, @NotBlank @Size(max = 6) String zipCode, String longitude,
			String latitude) {
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.city = city;
		this.state = state;
		this.country = country;
		this.zipCode = zipCode;
		this.longitude = longitude;
		this.latitude = latitude;
	}

 

}



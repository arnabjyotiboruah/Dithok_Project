package com.dithok.myCommerce.model;
import javax.persistence.Embeddable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Embeddable
public class NameEmbeddable {
	@NotBlank
	@Size(min = 1, max = 128)
	private String firstName;

	@Size(max = 128)
	private String middleName;

	@NotBlank
	@Size(min = 1, max = 128)
	private String lastName;

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

	public NameEmbeddable() {

	}

	/**
	 * @param firstName
	 * @param middleName
	 * @param lastName
	 */
	public NameEmbeddable(@NotBlank @Size(min = 1, max = 128) String firstName, @Size(max = 128) String middleName,
			@NotBlank @Size(min = 1, max = 128) String lastName) {
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
	}

}

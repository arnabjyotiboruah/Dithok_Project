package com.dithok.myCommerce.model;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@Table(name="otp")
@EntityListeners(AuditingEntityListener.class)
public class OtpModel {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long Id;
	
	@NotBlank
	@Length(min=6,max=6)
	private String oneTP;

	public String getOneTP() {
		return oneTP;
	}

	public void setOneTP(String oneTP) {
		this.oneTP = oneTP;
	}

	public long getId() {
		return Id;
	}
	
	
	
	

}

package com.dithok.myCommerce.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name="Roles")
@EntityListeners(AuditingEntityListener.class)
public class RoleModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotBlank
	@Length(min = 5, max=10)
	private String role;
	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	@LastModifiedDate
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;
	public long getId() {
		return id;
	}
	
//	public void setId(long id) {
//		this.id = id;
//	}
	
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	
	public Date getCreatedAt() {
		return createdAt;
	}
	
//	public void setCreatedAt(Date createdAt) {
//		this.createdAt = createdAt;
//	}
	
	public Date getUpdatedAt() {
		return updatedAt;
	}
	
//	public void setUpdatedAt(Date updatedAt) {
//		this.updatedAt = updatedAt;
//	}
	
	public RoleModel(@NotBlank @Length(min = 5, max = 10) String role) {
		super();
		this.role = role;
	}
	
	public RoleModel() {
		super();
	}
}

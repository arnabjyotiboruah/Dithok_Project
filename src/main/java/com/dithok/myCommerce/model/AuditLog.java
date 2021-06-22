package com.dithok.myCommerce.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="audit_log")
public class AuditLog {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name="User_name")
	private String userName;
	@Column(name="Details")
	private String details;
	@Column(name="Timestamp")
	private Date timestamp;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public AuditLog(int id, String userName, String details, Date timestamp) {
		super();
		this.id = id;
		this.userName = userName;
		this.details = details;
		this.timestamp = timestamp;
	}
	public AuditLog(String userName, String details, Date timestamp) {
		super();
		this.userName = userName;
		this.details = details;
		this.timestamp = timestamp;
	}
	public AuditLog() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}

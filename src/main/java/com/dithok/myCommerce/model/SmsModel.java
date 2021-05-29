package com.dithok.myCommerce.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import com.dithok.myCommerce.model.UserModel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "sms", uniqueConstraints = { @UniqueConstraint(name = "UK_sms", columnNames = { "smsId" }) })
@EntityListeners(AuditingEntityListener.class)
@org.hibernate.annotations.Table(comment = "SMS information", appliesTo = "sms")
public class SmsModel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;


	@NotNull
	@Column
	private long smsId;

	
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id", referencedColumnName = "id")
	private UserModel user;
	
//	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
//	@JoinColumn(name = "user_id", referencedColumnName = "id")
//	private UserModel user;
//	
//	@NotNull
	@Column
	@Length(min = 10)
	private String phoneNumber;

	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	private Date sentAt;

//	@Temporal(TemporalType.TIMESTAMP)
//	@NotBlank
	private String deliveredAt;

//	@NotBlank
	@Column
	private String content;

//	@NotBlank
	@Column
	private String sender;

//	@NotBlank
	@Column(columnDefinition = "VARCHAR (5) NOT NULL COMMENT 'status of the sms'")
	private String status;

//	@NotBlank
	@Column
	private String customId;

//	@NotBlank
	@Column
	private String links;

	public SmsModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getSmsId() {
		return smsId;
	}

	public void setSmsId(long smsId) {
		this.smsId = smsId;
	}

	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Date getSentAt() {
		return sentAt;
	}

	public void setSentAt(Date sentAt) {
		this.sentAt = sentAt;
	}

	public String getDeliveredAt() {
		return deliveredAt;
	}

	public void setDeliveredAt(String deliveredAt) {
		this.deliveredAt = deliveredAt;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCustomId() {
		return customId;
	}

	public void setCustomId(String customId) {
		this.customId = customId;
	}

	public String getLinks() {
		return links;
	}

	public void setLinks(String links) {
		this.links = links;
	}

}

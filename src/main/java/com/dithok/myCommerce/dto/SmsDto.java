package com.dithok.myCommerce.dto;

public class SmsDto {
	private String content;
	private long smsId;
	private String phoneNumber;
	
//    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
	private String deliveredAt;
    
	private String sender;
	private String status;
	private String customId;
	private String links;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public long getSmsId() {
		return smsId;
	}

	public void setSmsId(long smsId) {
		this.smsId = smsId;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getDeliveredAt() {
		return deliveredAt;
	}

	public void setDeliveredAt(String deliveredAt) {
		this.deliveredAt = deliveredAt;
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

	public SmsDto(long smsId, String phoneNumber, String content, String deliveredAt, String sender, String status,
			String customId, String links) {
		super();
		this.content = content;
		this.smsId = smsId;
		this.phoneNumber = phoneNumber;
		this.deliveredAt = deliveredAt;
		this.sender = sender;
		this.status = status;
		this.customId = customId;
		this.links = links;
	}

}

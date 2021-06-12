package com.dithok.myCommerce.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "pdf")
public class PdfModel {
	
	
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	private Date date;
	private String pay_type;
	private String pay_details;
	private Long paid_out;
	private Long paid_in;
	private Long balance;
	
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getPay_type() {
		return pay_type;
	}
	public void setPay_type(String pay_type) {
		this.pay_type = pay_type;
	}
	public String getPay_details() {
		return pay_details;
	}
	public void setPay_details(String pay_details) {
		this.pay_details = pay_details;
	}
	public Long getPaid_out() {
		return paid_out;
	}
	public void setPaid_out(Long paid_out) {
		this.paid_out = paid_out;
	}
	public Long getPaid_in() {
		return paid_in;
	}
	public void setPaid_in(Long paid_in) {
		this.paid_in = paid_in;
	}
	public Long getBalance() {
		return balance;
	}
	public void setBalance(Long balance) {
		this.balance = balance;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	public PdfModel(Long id, Date date, String pay_type, String pay_details, Long paid_out, Long paid_in,
			Long balance) {
		super();
		this.id = id;
		this.date = date;
		this.pay_type = pay_type;
		this.pay_details = pay_details;
		this.paid_out = paid_out;
		this.paid_in = paid_in;
		this.balance = balance;
	}
	
	
	public PdfModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "PdfModel [id=" + id + ", date=" + date + ", pay_type=" + pay_type + ", pay_details=" + pay_details
				+ ", paid_out=" + paid_out + ", paid_in=" + paid_in + ", balance=" + balance + "]";
	}
	
	
}
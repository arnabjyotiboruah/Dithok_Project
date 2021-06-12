package com.dithok.myCommerce.model;

import java.util.Date;

public class Details {
	
	private Date date;
	
	private String headerImage;
	
	private String footer;
	
	

	public String getHeaderImage() {
		return headerImage;
	}

	public void setHeaderImage(String headerImage) {
		this.headerImage = headerImage;
	}
	

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getFooter() {
		return footer;
	}

	public void setFooter(String footer) {
		this.footer = footer;
	}

	@Override
	public String toString() {
		return "Details [date=" + date + ", headerImage=" + headerImage + ", footer=" + footer + "]";
	}

	public Details() {
		super();
		// TODO Auto-generated constructor stub
		this.date = null;
		this.headerImage = null;
		this.footer = null;
	}

	
	
}

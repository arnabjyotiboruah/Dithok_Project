package com.dithok.myCommerce.model;

public class HeaderInfoPdf {
	private String tel1;
	private String telinfo;
	private String tel2;
	private String tel2info;
	private String url;
	private String name;
	private String address;
	private String openingbalance;
	private String paymentin;
	private String paymentout;
	private String closingbalance;
	private String accountno;
	private String sortcode;
	private String sheetno;
	

	public String getTel1() {
		return tel1;
	}

	public void setTel1(String tel1) {
		this.tel1 = tel1;
	}
	public String getTelinfo() {
		return telinfo;
	}
	public void setTelinfo(String telinfo) {
		this.telinfo = telinfo;
	}
	public String getTel2() {
		return tel2;
	}
	public void setTel2(String tel2) {
		this.tel2 = tel2;
	}
	public String getTel2info() {
		return tel2info;
	}
	public void setTel2info(String tel2info) {
		this.tel2info = tel2info;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	public String getOpeningbalance() {
		return openingbalance;
	}
	public void setOpeningbalance(String openingbalance) {
		this.openingbalance = openingbalance;
	}
	public String getPaymentin() {
		return paymentin;
	}
	public void setPaymentin(String paymentin) {
		this.paymentin = paymentin;
	}
	public String getPaymentout() {
		return paymentout;
	}
	public void setPaymentout(String paymentout) {
		this.paymentout = paymentout;
	}
	public String getClosingbalance() {
		return closingbalance;
	}
	public void setClosingbalance(String closingbalance) {
		this.closingbalance = closingbalance;
	}
	public String getAccountno() {
		return accountno;
	}
	public void setAccountno(String accountno) {
		this.accountno = accountno;
	}
	public String getSortcode() {
		return sortcode;
	}

	public void setSortcode(String sortcode) {
		this.sortcode = sortcode;
	}

	public String getSheetno() {
		return sheetno;
	}

	public void setSheetno(String sheetno) {
		this.sheetno = sheetno;
	}

	public HeaderInfoPdf(String tel1, String telinfo, String tel2, String tel2info, String url, String name,
			String address, String openingbalance, String paymentin, String paymentout, String closingbalance,
			String accountno, String sortcode, String sheetno) {
		super();
		this.tel1 = tel1;
		this.telinfo = telinfo;
		this.tel2 = tel2;
		this.tel2info = tel2info;
		this.url = url;
		this.name = name;
		this.address = address;
		this.openingbalance = openingbalance;
		this.paymentin = paymentin;
		this.paymentout = paymentout;
		this.closingbalance = closingbalance;
		this.accountno = accountno;
		this.sortcode = sortcode;
		this.sheetno = sheetno;
	}

	public HeaderInfoPdf() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

}

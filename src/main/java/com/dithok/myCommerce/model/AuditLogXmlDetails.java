package com.dithok.myCommerce.model;


public class AuditLogXmlDetails {
	
	private String login;
	

	private String logout;
	
	private String datasyncdownload;
	
	private String debuglogs;
	
	private String errorlogs;

	public String getDebuglogs() {
		return debuglogs;
	}

	public void setDebuglogs(String debuglogs) {
		this.debuglogs = debuglogs;
	}

	public String getErrorlogs() {
		return errorlogs;
	}

	public void setErrorlogs(String errorlogs) {
		this.errorlogs = errorlogs;
	}

	public String getDatasyncdownload() {
		return datasyncdownload;
	}

	public void setDatasyncdownload(String datasyncdownload) {
		this.datasyncdownload = datasyncdownload;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getLogout() {
		return logout;
	}

	public void setLogout(String logout) {
		this.logout = logout;
	}

	public AuditLogXmlDetails(String login, String logout, String datasyncdownload,String debuglogs, String errorlogs) {
		super();
		this.login = login;
		this.logout = logout;
		this.datasyncdownload = datasyncdownload;
		this.debuglogs = debuglogs;
		this.errorlogs = errorlogs;
	}

	public AuditLogXmlDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}

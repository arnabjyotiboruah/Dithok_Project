package com.dithok.myCommerce.exception;

import java.util.Date;


public class DBException extends BaseException
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5702563329727613914L;

	public DBException(Class<?> entityClass, Object id, DbErrorStatus status, String message) {
		this.setTimestamp(new Date());
		this.setErrorCode(status.value());
		this.setMessage(status.getReasonPhrase());
		String strTechDetails = String.format(" %s has SQL Database error in %s.", entityClass.getSimpleName(), id);
		String strDetails = message + strTechDetails;
		this.setDetails( strDetails );            
    }

}

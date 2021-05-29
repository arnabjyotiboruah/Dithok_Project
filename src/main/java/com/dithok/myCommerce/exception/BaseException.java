package com.dithok.myCommerce.exception;
import java.util.Date;

//Make this class abstract so that developers are forced to create
//suitable exception types only
public abstract class BaseException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9055683290381192286L;
	
	 private Date timestamp;     
     private int errorCode; 
	//Each exception message will be held here
     private String message;
     private String details;

 /**
	 * @param timestamp
	 * @param errorCode
	 * @param message
	 * @param details
	 */
	public BaseException(Date timestamp, int errorCode, String message, String details) {
		this.timestamp = timestamp;
		this.errorCode = errorCode;
		this.message = message;
		this.details = details;
	}

	
 /**
 * 
 */
public BaseException() {
	super();
	// TODO Auto-generated constructor stub
}


/**
 * @param message
 * @param cause
 * @param enableSuppression
 * @param writableStackTrace
 */
public BaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
	super(message, cause, enableSuppression, writableStackTrace);
	// TODO Auto-generated constructor stub
}


/**
 * @param message
 * @param cause
 */
public BaseException(String message, Throwable cause) {
	super(message, cause);
	// TODO Auto-generated constructor stub
}


/**
 * @param message
 */
public BaseException(String message) {
	super(message);
	// TODO Auto-generated constructor stub
}


/**
 * @param cause
 */
public BaseException(Throwable cause) {
	super(cause);
	// TODO Auto-generated constructor stub
}


//Message can be retrieved using this accessor method
 public String getMessage() {
     return message;
 }
/**
 * @return the errorCode
 */
public int getErrorCode() {
	return errorCode;
}

/**
 * @return the timestamp
 */
public Date getTimestamp() {
	return timestamp;
}

/**
 * @return the details
 */
public String getDetails() {
	return details;
}


/**
 * @param timestamp the timestamp to set
 */
public void setTimestamp(Date timestamp) {
	this.timestamp = timestamp;
}


/**
 * @param errorCode the errorCode to set
 */
public void setErrorCode(int errorCode) {
	this.errorCode = errorCode;
}


/**
 * @param message the message to set
 */
public void setMessage(String message) {
	this.message = message;
}


/**
 * @param details the details to set
 */
public void setDetails(String details) {
	this.details = details;
}
 
}
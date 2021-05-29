package com.dithok.myCommerce.exception;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.dithok.myCommerce.response.ErrorResponse;
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice()
public class RestExceptionHandler extends ResponseEntityExceptionHandler{
	
	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers,
	    HttpStatus status, WebRequest request) {
		String error = ex.getParameterName() + " parameter is missing.";
		ErrorResponse errorResponse = new ErrorResponse(new Date(),HttpStatus.BAD_REQUEST.value(), error, null);
		return new ResponseEntity<Object>(errorResponse, HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * Exception thrown when {@link org.springframework.http.converter.HttpMessageNotReadableException} is used in controller.
	 * @param ex
	 * @param request
	 * @return
	 */
//	@ExceptionHandler(HttpMessageNotReadableException.class)
//	protected ResponseEntity<?> handleMessageNotReadableException(HttpMessageNotReadableException ex) {
//		try {
//			String messages = ex.getMessage();
//			ErrorResponse errorResponse = new ErrorResponse(new Date(),HttpStatus.BAD_REQUEST.value(), messages.toString(), null);
//			return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
//		} catch (Exception e) {
//			ErrorResponse errorResponse = new ErrorResponse(new Date(),HttpStatus.BAD_REQUEST.value(), ex.getMessage(), ex.getLocalizedMessage());
//			return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
//		}
//	}
	
	/**
	 * Exception thrown when {@link org.springframework.validation.annotation.Validated} is used in controller.
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(ConstraintViolationException.class)
	protected ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException ex, HttpServletRequest request) {
		try {
			List<String> messages = ex.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
			ErrorResponse errorResponse = new ErrorResponse(new Date(),HttpStatus.BAD_REQUEST.value(), messages.toString(), null);
			return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			ErrorResponse errorResponse = new ErrorResponse(new Date(),HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(), ex.getLocalizedMessage());
			return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	//This works
	@ExceptionHandler(DataIntegrityViolationException.class)
	protected ResponseEntity<?> handleConstraintViolationExceptionException(DataIntegrityViolationException ex, HttpServletRequest request) {
		try {
			
			String messages = ex.getMessage() + " " + ex.getMostSpecificCause().getMessage() ;
			ErrorResponse errorResponse = new ErrorResponse(new Date(),HttpStatus.BAD_REQUEST.value(), messages.toString(), null);
			return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			ErrorResponse errorResponse = new ErrorResponse(new Date(),HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(), ex.getLocalizedMessage());
			return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	
	/**
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(EntityNotFoundException.class)
	protected ResponseEntity<?> handleEntityNotFoundException(EntityNotFoundException ex, HttpServletRequest request) {
		try {
			ErrorResponse errorResponse = new ErrorResponse(new Date(),HttpStatus.BAD_REQUEST.value(), ex.getMessage(), null);
			return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			ErrorResponse errorResponse = new ErrorResponse(new Date(),HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null);
			return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(DBException.class)
	protected ResponseEntity<?> handleDbException(DBException ex, HttpServletRequest request) {
			ErrorResponse errorResponse = new ErrorResponse(ex.getTimestamp(),ex.getErrorCode(),ex.getMessage(),ex.getDetails());
			return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}
	
}

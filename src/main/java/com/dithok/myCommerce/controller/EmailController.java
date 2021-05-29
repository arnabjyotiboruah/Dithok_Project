package com.dithok.myCommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dithok.myCommerce.MyCommerceException;
import com.dithok.myCommerce.service.EmailService;

import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
public class EmailController {

	@Autowired
	private EmailService emailService;

	@RequestMapping(value = "/api/sendemail", method = RequestMethod.POST, produces = {
			MimeTypeUtils.APPLICATION_JSON_VALUE }, headers = "Accept=application/json")

	public ResponseEntity<MyCommerceException> send(@RequestBody ObjectNode objectNode) {

		// JSONObject resObject = new JSONObject();
		String email = String.valueOf(objectNode.get("email").asText());
		String subject = String.valueOf(objectNode.get("subject").asText());
		String body = String.valueOf(objectNode.get("body").asText());

		

		try {
			emailService.sendEmail(email, subject, body);
			return new ResponseEntity<MyCommerceException>(new MyCommerceException(1, "Email Sent!"), HttpStatus.OK);

		} catch (Exception E) {
			return new ResponseEntity<MyCommerceException>(new MyCommerceException(0, "Error: " + E),
					HttpStatus.BAD_REQUEST);
		}
	}

	// public String send( @RequestBody ObjectNode objectNode) {

	// JSONObject resObject = new JSONObject();
	// String email = String.valueOf(objectNode.get("email").asText());
	// String subject = String.valueOf(objectNode.get("subject").asText());
	// String body = String.valueOf(objectNode.get("body").asText());

	// try{

	// emailService.sendEmail(email,subject,body);
	// resObject.put("status", 1);
	// resObject.put("message", "Email Sent");
	// return resObject.toString();

	// }catch(MailException E){
	// resObject.put("error", E);
	// return resObject.toString();
	// }
	// }
}
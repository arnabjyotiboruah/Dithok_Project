package com.dithok.myCommerce.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.dithok.myCommerce.Repo.SmsRepository;
import com.dithok.myCommerce.dto.SmsDto;
import com.dithok.myCommerce.service.SmsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.dithok.myCommerce.exception.MyCommerceException;

//import com.sun.mail.iap.ProtocolException;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SmsController {

	@Autowired
	SmsRepository smsRepo;

	@Autowired
	SmsService smsService;


	@RequestMapping(method = RequestMethod.POST, value = "/api/sendsms", headers = "Accept=application/json")
	public String SendSms(@RequestBody String number) {
		String apiKey = "&apikey=" + "Aiy2rWAyOxo-JlC1W43KJHVzZMsJRUr6AoOOl2oBMN";
		String message = "&message=" + "Hello User! Your Profile information has been updated.";
		String sender = "&sender=" + "TXTLCL";
		String numbers = "&numbers=" + number;
		// send data
		try {
			HttpURLConnection conn = (HttpURLConnection) new URL("https://api.textlocal.in/send/?").openConnection();
			String data = apiKey + numbers + message + sender;
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
			conn.getOutputStream().write(data.getBytes("UTF-8"));
			final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			final StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = rd.readLine()) != null) {
				stringBuffer.append(line);
			}
			rd.close();

			return stringBuffer.toString();
		} catch (Exception e) {
			System.out.println("Error SMS " + e);
			return "Error " + e;
		}
	}

	@RequestMapping(method = RequestMethod.POST,
					value = "/api/smshistory",
					// consumes = "application/x-www-form-urlencoded",
					headers = "Accept=application/json"

				)
	public String SmsHistory() {
		try {
			String apiKey = "apikey=" + "bq50FQGGez8-EXo36dhtpzMRbLG9jfqmR8zPU5xcdh";

			// Send data
			HttpURLConnection conn = (HttpURLConnection) new URL("https://api.textlocal.in/get_history_api/?")
					.openConnection();
			String data = apiKey;
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
			conn.getOutputStream().write(data.getBytes("UTF-8"));
			final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			final StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = rd.readLine()) != null) {
				stringBuffer.append(line);
			}
			rd.close();

			return stringBuffer.toString();
			// return new ResponseEntity<MyCommerceException>(new MyCommerceException(1, stringBuffer.toString()), HttpStatus.OK);
		} catch (Exception e) {
			System.out.println("Error SMS " + e);
			return "Error " + e;
			// return new ResponseEntity<MyCommerceException>(new MyCommerceException(1, "Error " + e), HttpStatus.OK);

		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "/api/savesmshistory", consumes = "application/x-www-form-urlencoded", headers = "Accept=application/json")
	public ResponseEntity<MyCommerceException> saveSmsDetails(SmsDto smsDetails) {
		System.out.println(smsDetails.toString());
		int ret = smsService.saveSmsDetails(smsDetails);
		if (ret == 1)
			return new ResponseEntity<MyCommerceException>(new MyCommerceException(1, "SMS Details added"),
					HttpStatus.OK);

		return new ResponseEntity<MyCommerceException>(new MyCommerceException(0, "Failed to add the sms detials"),
				HttpStatus.OK);
	}

}

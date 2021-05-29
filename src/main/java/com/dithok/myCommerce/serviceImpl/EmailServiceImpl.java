package com.dithok.myCommerce.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.dithok.myCommerce.Repo.EmailRepository;
import com.dithok.myCommerce.Repo.UserRepository;
//import com.dithok.myCommerce.dto.GetOrder;
import com.dithok.myCommerce.model.EmailModel;
//import com.dithok.myCommerce.model.OrderModel;
import com.dithok.myCommerce.model.UserModel;
import com.dithok.myCommerce.service.EmailService;

@Service
public class EmailServiceImpl implements EmailService {

    
	private JavaMailSender javaMailSender;
	private RestTemplate restTemplate = new RestTemplate();

	@Autowired
	UserRepository userRepo;

	@Autowired
	EmailRepository emailRepo;

	// @Autowired
	public EmailServiceImpl(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	@Override
	public void sendEmail(String email, String subject, String body) throws MailException {

		UserModel userEntity = userRepo.findByEmail(email);
		Optional<UserModel> users = userRepo.findById(userEntity.getId());
		UserModel user = users.get();
		EmailModel emailModel = new EmailModel(user);
		emailModel.setEmail(email);
		//System.out.println("+++++++++++" + getOrderID(userEntity.getId()));

		try {
			SimpleMailMessage mail = new SimpleMailMessage();
			mail.setTo(email);
			mail.setSubject(subject);
			mail.setText(body);
			javaMailSender.send(mail);
			emailModel.setEmail_Status("sent");

		} catch (MailException e) {
			emailModel.setEmail_Status("error");
			e.printStackTrace();
		}

		emailModel.setBody(body);
		emailModel.setSubject(subject);
		emailRepo.save(emailModel);

	}

	public int getOrderID(Long long1) {

		String url = "http://localhost:8080/api/customerorder/GetAllOrderDetails";

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

		Map<String, Object> map = new HashMap<>();
		map.put("user_id", long1);

		HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, headers);

		//ResponseEntity<GetOrder> res = this.restTemplate.postForEntity(url, entity, GetOrder.class);

		//System.out.println("-------------" + res);
		System.out.println("--------Test Email sent -----");
		return 1;
	}
}
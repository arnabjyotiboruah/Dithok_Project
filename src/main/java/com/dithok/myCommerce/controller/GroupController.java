package com.dithok.myCommerce.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;

import com.dithok.myCommerce.Repo.PdfRepository;
import com.dithok.myCommerce.model.GroupUserModel;
import com.dithok.myCommerce.model.GroupsModel;
import com.dithok.myCommerce.model.PdfModel;
import com.dithok.myCommerce.model.UserModel;
import com.dithok.myCommerce.service.GroupUserService;
import com.dithok.myCommerce.service.GroupsService;
import com.dithok.myCommerce.service.PdfService;
import com.dithok.myCommerce.service.UserServiceInterface;


@RestController
public class GroupController {
	@Autowired
	private GroupsService groupService;
	
	@Autowired
	private GroupUserService groupUserService;
	
	@Autowired
	private UserServiceInterface userService;
	
	@Autowired
	private PdfRepository pdfRepo;
	
	@Autowired
	private PdfService pdfService;
	
	@RequestMapping(value="/group/add/{ids}",method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public void add(@RequestBody GroupsModel group) {
			groupService.addGroup(group);
	}
	
	@RequestMapping(value="/group/delete/{id}",method=RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_VALUE)
	public GroupsModel delete(@PathVariable("id") long id) {
		return groupService.remove(id);
		
	}

	@RequestMapping(value="/group/update",method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public GroupsModel update(@RequestBody GroupsModel group) {
		return groupService.updateGroup(group);
	}
	
	@RequestMapping(value="/group/listAll", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<GroupsModel> getAll() {
		return groupService.listAll();
	}
	
	@RequestMapping(value="/group/listAll/{id}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public GroupsModel getGroup(@PathVariable("id") long id) {
		return groupService.getById(id);
	}
	@RequestMapping(value="/group/addUser/{id}", method = RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public GroupUserModel addUsertoGroup(@RequestBody String email,@PathVariable("id") long id) {
		UserModel user = userService.findUserByEmail(email);
		GroupsModel group = groupService.getById(id);
		GroupUserModel userGroup = new GroupUserModel();
		userGroup.setUser(user);
		userGroup.setGroup(group);
		userGroup.setUser_id(user.getId());
		userGroup.setGroup_id(group.getGroup_id());
		return groupUserService.addGroupUser(userGroup);
		
		
	}
	
	@RequestMapping(value="/group/print", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity pdfReport() throws IOException, ParserConfigurationException, SAXException{
		List<PdfModel> tr = pdfRepo.findAll();
		
//		ReadXml.readData();
		
//		ByteArrayInputStream bis = pdfService.sqlPDFReport(tr);
//		
//		
//		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(MediaType.parseMediaType("application/pdf"));
//		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
//		headers.add("Content-Disposition", "inline; filename=PdfOutline.pdf");
//		
//		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bis));
		
		ByteArrayInputStream bis = pdfService.sqlPDFReport(tr);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/pdf"));
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=TransactionPdfReport.pdf");

		ResponseEntity<Resource> response = new ResponseEntity<Resource>(new InputStreamResource(bis), headers,
				HttpStatus.OK);

		return response;
		
		
		
	}
}

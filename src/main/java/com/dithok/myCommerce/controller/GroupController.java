package com.dithok.myCommerce.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;

import com.dithok.myCommerce.Repo.PdfRepository;
import com.dithok.myCommerce.dto.GroupUserDto;
import com.dithok.myCommerce.dto.LoginDto;
import com.dithok.myCommerce.dto.sessionDto;
import com.dithok.myCommerce.model.GroupUserModel;
import com.dithok.myCommerce.model.GroupsModel;
import com.dithok.myCommerce.model.PdfModel;
import com.dithok.myCommerce.model.PoliciesModel;
import com.dithok.myCommerce.model.RulesModel;
import com.dithok.myCommerce.model.UserModel;
import com.dithok.myCommerce.service.GroupUserService;
import com.dithok.myCommerce.service.GroupsService;

import com.dithok.myCommerce.service.PdfService;
import com.dithok.myCommerce.service.PoliciesService;
import com.dithok.myCommerce.service.RulesService;
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
	
	@Autowired
	private PoliciesService policyService;
	
	@Autowired
	private RulesService ruleService;
	
	@Autowired
	private HttpServletResponse response;
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private HttpSession session;

	
//	private LoginDto login;

	
	@RequestMapping(value="/group/add",method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public GroupsModel add(@RequestBody GroupsModel group) {
			return groupService.addGroup(group);
			
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
	
	@RequestMapping(value="/group/list/{id}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public GroupsModel getGroup(@PathVariable("id") long id) {
		return groupService.getById(id);
	}
	@RequestMapping(value="/group/addUser", method = RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public GroupUserModel addUsertoGroup(@RequestBody GroupUserDto groupUser) {
		UserModel user = userService.findUserByEmail(groupUser.getEmail());
		GroupsModel group = groupService.getById(groupUser.getId());
		GroupUserModel userGroup = new GroupUserModel();
		userGroup.setUser(user);
		userGroup.setGroup(group);
		userGroup.setUser_id(user.getId());
		userGroup.setGroup_id(group.getGroup_id());
		return groupUserService.addGroupUser(userGroup);
		
		
	}
	
	@RequestMapping(value="/group/reset/{id}", method = RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public GroupsModel addResetGroup(@PathVariable("id") long id) {
		return groupService.resetGroup(id);
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
	
	
	@RequestMapping(value="/group/applyPolicy", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public void applyPolicy() {

		String email = session.getAttribute("loggedInUser").toString();
		UserModel user = userService.findUserByEmail(email);
		long userNewId = user.getId();
		GroupUserModel groupUser = groupUserService.findByUserId(userNewId);
		long groupId = groupUser.getGroup_id();
		List<PoliciesModel> policy = policyService.getByGroupId(groupId);
			for(int i=0; i<policy.size();i++) {
				PoliciesModel eachPolicy = policy.get(i);
				List<RulesModel> ruleList = ruleService.findByPolicyId(eachPolicy.getPolicy_id());
					for(int j=0; j<ruleList.size();j++) {
					RulesModel rule = ruleList.get(j);
						if(rule.getType() == "Javascript") {
							
			}
		System.out.println("Empty rule list");
			}
		}
	}
	
//	@RequestMapping(value="/group/cookie", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
//	public String getCookie(Authentication auth) {
//		 auth.
//	}
}

package com.dithok.myCommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dithok.myCommerce.model.GroupsModel;
import com.dithok.myCommerce.model.PoliciesModel;
import com.dithok.myCommerce.service.GroupsService;
import com.dithok.myCommerce.service.PoliciesService;

@RestController
public class PolicyController {
	@Autowired
	private PoliciesService policyService;
	
	@Autowired
	private GroupsService groupService;
	
	@RequestMapping(value="/policy/add/{id}",method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public void add(@RequestBody PoliciesModel policy, @PathVariable("id") long id) {
		GroupsModel group = groupService.getById(id);
		policy.setGroup(group);
		policyService.addPolicy(policy);
	}
	
	@RequestMapping(value="/policy/delete/{id}",method=RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_VALUE)
	public PoliciesModel delete(@PathVariable("id") long id) {
		return policyService.remove(id);
		
	}

	@RequestMapping(value="/policy/update",method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public PoliciesModel update(@RequestBody PoliciesModel policy) {
		return policyService.updatePolicy(policy);
	}
	
	@RequestMapping(value="/policy/listAll", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<PoliciesModel> getAll() {
		return policyService.listAll();
	}
	
	@RequestMapping(value="/policy/listAll/{id}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public PoliciesModel getRule(@PathVariable("id") long id) {
		return policyService.getById(id);
	}

}

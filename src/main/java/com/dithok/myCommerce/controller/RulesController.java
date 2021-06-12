package com.dithok.myCommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dithok.myCommerce.model.RulesModel;
import com.dithok.myCommerce.service.RulesService;

@RestController
public class RulesController {
	
	@Autowired
	private RulesService ruleService;
	
	@RequestMapping(value="/rule/add",method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public void add(@RequestBody RulesModel rule) {
		ruleService.addRule(rule);
	}
	
	@RequestMapping(value="/rule/delete/{id}",method=RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_VALUE)
	public RulesModel delete(@PathVariable("id") long id) {
		return ruleService.remove(id);
		
	}

	@RequestMapping(value="/rule/update",method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public RulesModel update(@RequestBody RulesModel rule) {
		return ruleService.updateRule(rule);
	}
	
	@RequestMapping(value="/rule/listAll", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<RulesModel> getAll() {
		return ruleService.listAll();
	}
	
	@RequestMapping(value="/rule/listAll/{id}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public RulesModel getRule(@PathVariable("id") long id) {
		return ruleService.getById(id);
	}
}

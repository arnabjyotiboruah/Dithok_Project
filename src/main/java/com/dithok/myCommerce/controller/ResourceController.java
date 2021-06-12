package com.dithok.myCommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dithok.myCommerce.model.ResourcesModel;
import com.dithok.myCommerce.model.RulesModel;
import com.dithok.myCommerce.service.ResourcesService;

@RestController
public class ResourceController {

	@Autowired
	private ResourcesService resourceService;
	
	@RequestMapping(value="/resource/add",method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public void add(@RequestBody ResourcesModel resource) {
		resourceService.addResource(resource);
	}
	
	@RequestMapping(value="/resource/delete/{id}",method=RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResourcesModel delete(@PathVariable("id") long id) {
		return resourceService.remove(id);
		
	}

	@RequestMapping(value="/resource/update",method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResourcesModel update(@RequestBody ResourcesModel resource) {
		return resourceService.updateResource(resource);
	}
	
	@RequestMapping(value="/resource/listAll", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<ResourcesModel> getAll() {
		return resourceService.listAll();
	}
	
	@RequestMapping(value="/resource/listAll/{id}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResourcesModel getRule(@PathVariable("id") long id) {
		return resourceService.getById(id);
	}
}

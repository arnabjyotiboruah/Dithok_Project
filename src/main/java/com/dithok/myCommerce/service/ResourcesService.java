package com.dithok.myCommerce.service;

import java.util.List;

import com.dithok.myCommerce.model.ResourcesModel;

public interface ResourcesService {
	public ResourcesModel addResource(ResourcesModel resource);
	public ResourcesModel remove(long id);
	public ResourcesModel updateResource(ResourcesModel resource);
	public List<ResourcesModel> listAll();
	public ResourcesModel getById(long id);

}

package com.dithok.myCommerce.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dithok.myCommerce.Repo.ResourcesRepository;
import com.dithok.myCommerce.model.ResourcesModel;
import com.dithok.myCommerce.service.ResourcesService;

@Service
public class ResourceServiceImpl implements ResourcesService {
	@Autowired
	private ResourcesRepository repo;

	@Override
	public ResourcesModel addResource(ResourcesModel resource) {
		return repo.save(resource);
	}

	@Override
	public ResourcesModel remove(long id) {
		ResourcesModel resource = repo.findById(id).get();
		if(resource.isStatus() == true) {
			resource.setStatus(false);
			repo.save(resource);
			return resource;
		}
		return resource;
	}

	@Override
	public ResourcesModel updateResource(ResourcesModel resource) {
		ResourcesModel updateResource = repo.findById(resource.getRes_id()).get();
		updateResource.setCompanyName(resource.getCompanyName());
		updateResource.setRange_Resource(resource.getRange_Resource());
		updateResource.setType(resource.getType());
		return repo.save(updateResource);
	}

	@Override
	public List<ResourcesModel> listAll() {
		return repo.findAll();
	}

	@Override
	public ResourcesModel getById(long id) {
		return repo.findById(id).get();
	}

}

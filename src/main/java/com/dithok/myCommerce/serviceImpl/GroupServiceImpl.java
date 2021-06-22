package com.dithok.myCommerce.serviceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dithok.myCommerce.Repo.GroupRepository;
import com.dithok.myCommerce.model.GroupsModel;
import com.dithok.myCommerce.service.GroupsService;

@Service
public class GroupServiceImpl implements GroupsService {
	
	@Autowired
	private GroupRepository repo;

	@Override
	public GroupsModel addGroup(GroupsModel group) {
		return repo.save(group);
	}

	@Override
	public GroupsModel remove(long id) {
		GroupsModel group = repo.findById(id).get();
		if(group.isStatus() == true) {
			group.setStatus(false);
			repo.save(group);
			return group;
		}
		return group;
	}
	
	

	@Override
	public List<GroupsModel> listAll() {
		return repo.findAll();
	}

	@Override
	public GroupsModel getById(long id) {
		return repo.findById(id).get();
		}

	@Override
	public GroupsModel updateGroup(GroupsModel group) {
		GroupsModel updateGroup = repo.findById(group.getGroup_id()).get();
		updateGroup.setDescription(group.getDescription());
		updateGroup.setName(group.getName());
		return repo.save(updateGroup);
	}

	@Override
	public GroupsModel resetGroup(long id) {
		GroupsModel group = repo.findById(id).get();
		if(group.isStatus()==false) {
			group.setStatus(true);
			return group;
		}
		return group;
		
	}

}

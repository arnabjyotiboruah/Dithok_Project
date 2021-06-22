package com.dithok.myCommerce.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dithok.myCommerce.Repo.GroupUserRepository;
import com.dithok.myCommerce.model.GroupUserModel;
import com.dithok.myCommerce.service.GroupUserService;

@Service
public class GroupUserServiceImpl implements GroupUserService {
	
	@Autowired
	private GroupUserRepository repo;

	@Override
	public GroupUserModel addGroupUser(GroupUserModel groupUser) {
		return repo.save(groupUser);
		
	}

}

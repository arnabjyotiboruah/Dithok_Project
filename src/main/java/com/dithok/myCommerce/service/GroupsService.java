package com.dithok.myCommerce.service;

import java.util.List;

import com.dithok.myCommerce.model.GroupsModel;

public interface GroupsService {
	public GroupsModel addGroup(GroupsModel group);
	public GroupsModel remove(long id);
	public GroupsModel updateGroup(GroupsModel group);
	public List<GroupsModel> listAll();
	public GroupsModel getById(long id);
	public GroupsModel resetGroup(long id);

}

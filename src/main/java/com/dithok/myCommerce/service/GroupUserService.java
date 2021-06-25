package com.dithok.myCommerce.service;

import java.util.List;

import com.dithok.myCommerce.model.GroupUserModel;
import com.dithok.myCommerce.model.UserModel;

public interface GroupUserService {
	public GroupUserModel addGroupUser(GroupUserModel groupUser);
	public List<GroupUserModel> findByUserId(long id);
	public List<GroupUserModel> findByGroupId(long id);
	public String findUserByGroupId(long id);
	public String findGroupByUserId(long id);

}
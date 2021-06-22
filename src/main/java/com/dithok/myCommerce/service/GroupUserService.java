package com.dithok.myCommerce.service;

import com.dithok.myCommerce.model.GroupUserModel;

public interface GroupUserService {
	public GroupUserModel addGroupUser(GroupUserModel groupUser);
	public GroupUserModel findByUserId(long id);
	public GroupUserModel findByGroupId(long id);

}

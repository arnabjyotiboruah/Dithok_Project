package com.dithok.myCommerce.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dithok.myCommerce.model.GroupsModel;
import com.dithok.myCommerce.model.UserModel;

public interface GroupRepository extends JpaRepository<GroupsModel, Long> {
	
}

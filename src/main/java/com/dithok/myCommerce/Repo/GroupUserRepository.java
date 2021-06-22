package com.dithok.myCommerce.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dithok.myCommerce.model.GroupUserModel;


public interface GroupUserRepository extends JpaRepository<GroupUserModel, Integer> {
	
	@Query(value="select s.* from group_user s where s.user_id = :userId", nativeQuery = true)
    public GroupUserModel findByUserId(@Param("userId") long userId);
	
	@Query(value="select s.* from group_user s where s.group_id = :groupId", nativeQuery = true)
    public GroupUserModel findByGroupId(@Param("groupId") long groupId);
}

package com.dithok.myCommerce.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dithok.myCommerce.model.PoliciesModel;

public interface PoliciesRepository extends JpaRepository<PoliciesModel, Long> {
	@Query(value="select s.* from policies_model s where s.group_model_id = :groupId", nativeQuery = true)
    public List<PoliciesModel> findByGroupId(@Param("groupId") long groupId);

}

package com.dithok.myCommerce.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.dithok.myCommerce.model.RulesModel;

public interface RulesRepository extends JpaRepository<RulesModel, Long> {
	@Query(value="select s.* from rules s where s.policy_id = :policyId", nativeQuery = true)
    public List<RulesModel> findByPolicyId(@Param("policyId") long policyId);

}

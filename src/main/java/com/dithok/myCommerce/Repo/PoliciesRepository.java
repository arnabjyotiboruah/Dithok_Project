package com.dithok.myCommerce.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dithok.myCommerce.model.PoliciesModel;

public interface PoliciesRepository extends JpaRepository<PoliciesModel, Long> {

}

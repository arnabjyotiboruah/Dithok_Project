package com.dithok.myCommerce.Repo;
import org.springframework.data.jpa.repository.JpaRepository;

import com.dithok.myCommerce.model.RoleModel;

public interface RoleRepository extends JpaRepository<RoleModel, Long>{
 public RoleModel findByRole(String role);
}

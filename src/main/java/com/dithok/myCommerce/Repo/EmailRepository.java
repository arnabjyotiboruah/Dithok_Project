package com.dithok.myCommerce.Repo;

import com.dithok.myCommerce.model.EmailModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository extends JpaRepository<EmailModel, Long>{

}

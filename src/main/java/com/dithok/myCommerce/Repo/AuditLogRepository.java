package com.dithok.myCommerce.Repo;

import com.dithok.myCommerce.model.AuditLog;
import com.dithok.myCommerce.model.UserModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;



@Repository
public interface AuditLogRepository extends JpaRepository<AuditLog, Integer> {
	
	@Query(value="select s.* from audit_log s where s.timestamp >= :searchDate", nativeQuery = true)
    public List<AuditLog> findByDate(@Param("searchDate")@DateTimeFormat(pattern = "dd-MM-yyyy") Date searchDate);
} 


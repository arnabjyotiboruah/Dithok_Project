package com.dithok.myCommerce.serviceImpl;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dithok.myCommerce.Repo.AuditLogRepository;
import com.dithok.myCommerce.model.AuditLog;
import com.dithok.myCommerce.model.UserModel;
import com.dithok.myCommerce.service.AuditLogService;

@Service
public class AuditLogServiceImpl implements AuditLogService {
	@Autowired
	AuditLogRepository repo;
	
	
	@Override
	public AuditLog saveauditLog(AuditLog log) {
		return repo.save(log);
		
	}


	@Override
	public List<AuditLog> findAll() {
		try {
			return repo.findAll();
		} catch (Exception e) {
			throw e;
		}

	}
	@Override
	public List<AuditLog> findByDate(Date searchDate) {
		return repo.findByDate(searchDate);
	}


	@Override
	public ByteArrayInputStream sqlPDFReport(List<AuditLog> pdf) {
		return null;
	}

	
}

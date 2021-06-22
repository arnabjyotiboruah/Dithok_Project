package com.dithok.myCommerce.service;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.dithok.myCommerce.model.AuditLog;
import com.dithok.myCommerce.model.PdfModel;
import com.dithok.myCommerce.model.UserModel;

public interface AuditLogService {
	public AuditLog saveauditLog(AuditLog log);
	public List<AuditLog> findAll();
	public List<AuditLog> findByDate(Date searchDate);
	public ByteArrayInputStream sqlPDFReport(List<AuditLog> pdf);
}

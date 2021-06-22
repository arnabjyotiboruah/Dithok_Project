package com.dithok.myCommerce.singleton;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import com.dithok.myCommerce.model.AuditLog;
import com.dithok.myCommerce.model.AuditLogXmlDetails;
import com.dithok.myCommerce.service.AuditLogService;
import com.dithok.myCommerce.xmlImpl.XmlImplementation;

public class Singleton {
//	   @Autowired
//      AuditLogService auditlogService;
	   //create an object of SingleObject
	   private static Singleton instance;
	    
	   
	   private static AuditLogXmlDetails auditxml = new AuditLogXmlDetails();
	   static {
	   try {
		    auditxml= XmlImplementation.readData();
	   }
	   catch (ParserConfigurationException | SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
	   } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   }
	   //make the constructor private so that this class cannot be
	   //instantiated
	   private Singleton(){}

	   //Get the only object available
	   public static Singleton getInstance(){
		   if (instance == null) {
			   instance = new Singleton();
		   }
	       return instance;
	   }
	   @PostConstruct
	   public void logInMessage(AuditLogService auditlogService,String email){
		   InetAddress IP = null;
			try {
				IP = InetAddress.getLocalHost();
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    AuditLog auditlog = new AuditLog(email,auditxml.getLogin() + IP.toString(),new Date());
		    auditlogService.saveauditLog(auditlog);
		   }
	   public void logOutMessage(AuditLogService auditlogService,String email){
		   InetAddress IP = null;
			try {
				IP = InetAddress.getLocalHost();
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   AuditLog auditlog = new AuditLog(email,auditxml.getLogout() + IP.toString(),new Date());
		   auditlogService.saveauditLog(auditlog);
		   }
	   public void debugLogDownloadMessage(AuditLogService auditlogService,String email){
		   InetAddress IP = null;
			try {
				IP = InetAddress.getLocalHost();
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   AuditLog auditlog = new AuditLog(email,auditxml.getDebuglogs() + IP.toString(),new Date());
		   auditlogService.saveauditLog(auditlog);
		   }
	   public void errorLogDownloadMessage(AuditLogService auditlogService,String email){
		   InetAddress IP = null;
			try {
				IP = InetAddress.getLocalHost();
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   AuditLog auditlog = new AuditLog(email,auditxml.getErrorlogs() + IP.toString(),new Date());
		   auditlogService.saveauditLog(auditlog);
		   }
	   public void syncDownloadMessage(AuditLogService auditlogService,String email){
		   InetAddress IP = null;
		try {
			IP = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   AuditLog auditlog = new AuditLog(email,auditxml.getDatasyncdownload() + IP.toString(),new Date());
		   auditlogService.saveauditLog(auditlog);
		   }
	   }
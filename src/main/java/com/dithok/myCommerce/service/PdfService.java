package com.dithok.myCommerce.service;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.w3c.dom.Element;

import com.dithok.myCommerce.model.Details;
import com.dithok.myCommerce.model.HeaderInfoPdf;
import com.dithok.myCommerce.model.PdfModel;

public interface PdfService {
	public List<PdfModel> findAllData();
	public ByteArrayInputStream sqlPDFReport(List<PdfModel> pdf);
//	public Details readData();
//	public HeaderInfoPdf readInfo();
//	public String getTagValue(String tag, Element element);
//	public String getHeaderInfoValue(String tag, Element element); 


}

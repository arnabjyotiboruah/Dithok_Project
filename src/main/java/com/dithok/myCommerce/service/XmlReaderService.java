package com.dithok.myCommerce.service;

import java.io.File;


import org.w3c.dom.Element;

import com.dithok.myCommerce.model.Details;


public interface XmlReaderService {
	public Details readData(File file);
	public String getTagValue(String tag, Element element);

}

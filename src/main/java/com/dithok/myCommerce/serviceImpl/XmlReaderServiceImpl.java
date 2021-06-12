package com.dithok.myCommerce.serviceImpl;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.dithok.myCommerce.model.Details;
import com.dithok.myCommerce.service.XmlReaderService;

@Service
public class XmlReaderServiceImpl implements XmlReaderService {

	@Override
	public Details readData(File file){

		//Create a new document builder for the configuration.xml file
		
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = null;
		try {
			db = dbf.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Document doc = null;
		try {
			doc = db.parse(file);
		} catch (SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		doc.getDocumentElement().normalize();
		System.out.println("Root Element: " + doc.getDocumentElement().getNodeName());
		NodeList nodeList = doc.getElementsByTagName("info");
		

//		Get the details from xml tag to the details class variables.
		
		Details details = new Details();
		if(nodeList.item(0).getNodeType() == Node.ELEMENT_NODE) {
			Element element = (Element) nodeList.item(0);
			details.setDate(new Date());
			details.setHeaderImage(getTagValue("headerImage",element));
			details.setFooter(getTagValue("footer",element));
		}
		
		return details;
	}

	@Override
	public String getTagValue(String tag, Element element) {
		NodeList nodelist = element.getElementsByTagName(tag).item(0).getChildNodes();
		Node node = (Node) nodelist.item(0);
		return node.getNodeValue();
	}

}

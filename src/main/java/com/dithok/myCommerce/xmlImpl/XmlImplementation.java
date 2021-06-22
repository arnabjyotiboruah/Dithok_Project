package com.dithok.myCommerce.xmlImpl;
 
	import java.io.File;
	import java.io.IOException;
	import java.util.ArrayList;
	import java.util.Date;
	import java.util.List;

	import javax.xml.parsers.DocumentBuilder;
	import javax.xml.parsers.DocumentBuilderFactory;
	import javax.xml.parsers.ParserConfigurationException;

	import org.springframework.context.annotation.Bean;
	import org.w3c.dom.Document;
	import org.w3c.dom.Element;
	import org.w3c.dom.Node;
	import org.w3c.dom.NodeList;
	import org.xml.sax.SAXException;

import com.dithok.myCommerce.model.AuditLogXmlDetails;



	public class XmlImplementation {
		
		
		public static AuditLogXmlDetails readData() throws ParserConfigurationException, SAXException, IOException {

		    
			//Create a new document builder for the configuration.xml file
			
			String path = new File("src/main/resources/AuditLogMessage.xml").getAbsolutePath();                                                        
			File file = new File(path);
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(file);
			doc.getDocumentElement().normalize();
			System.out.println("Root Element: " + doc.getDocumentElement().getNodeName());
			NodeList nodeList = doc.getElementsByTagName("info");
			
//			Get the details from xml tag to the details class variables.
			
			AuditLogXmlDetails details = new AuditLogXmlDetails();
			if(nodeList.item(0).getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) nodeList.item(0);
				details.setLogin(getTagValue("login",element));
				details.setLogout(getTagValue("logout",element));
				details.setDatasyncdownload(getTagValue("datasyncdownload",element));
				details.setDebuglogs(getTagValue("debuglogs",element));
				details.setErrorlogs(getTagValue("errorlogs",element));
				}
			
			return details;
			
			
		}
//	      Creating nodelist to insert elements from xml tag.
		private static String getTagValue(String tag, Element element) {
			NodeList nodelist = element.getElementsByTagName(tag).item(0).getChildNodes();
			Node node = (Node) nodelist.item(0);
			return node.getNodeValue();
		}
	}

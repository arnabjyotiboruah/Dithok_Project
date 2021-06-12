package com.dithok.myCommerce.serviceImpl;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dithok.myCommerce.model.Details;
import com.dithok.myCommerce.service.XmlReaderService;
import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Document;


@Service
public class CreateFooterServiceImpl implements IEventHandler {

	@Autowired
	private XmlReaderService xmlReader;
	
	
	private Document doc;
	private Details info;
	
//	float tableHeight;
//	private Table table;
//	
//	@Override
//	public IEventHandler CreateFooterService(Document doc) {
//		this.doc=doc;
//		return null;
//	}
	
	
	public CreateFooterServiceImpl(Document doc, Details info) {
		this.doc = doc;
		this.info = info;

	}



	

	@Override
	public void handleEvent(Event event) {
		PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
		PdfCanvas canvas = new PdfCanvas(docEvent.getPage());
		Rectangle rect = docEvent.getPage().getPageSize();
		canvas.beginText();
		try {
			canvas.setFontAndSize(PdfFontFactory.createFont(FontConstants.HELVETICA_OBLIQUE), 5);
			
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		float coordX = ((rect.getLeft() + doc.getLeftMargin()) + (rect.getRight() - doc.getRightMargin())) / 2;
		float footerY = doc.getBottomMargin();
//		String pathXml = new File("src/main/resources/configuration.xml").getAbsolutePath();                                                        
//		File fileXml = new File(pathXml);
//		info = xmlReader.readData(fileXml);
		String footer = info.getFooter().toString();
		if( footer != null) {
			canvas.moveText(coordX, footerY).showText(info.getDate().toString()).showText("   ").showText(footer).endText().release();

		}
		else {
			canvas.moveText(coordX, footerY).showText(new Date().toString()).showText(footer).endText().release();
		}
		
	}


	public CreateFooterServiceImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	

}

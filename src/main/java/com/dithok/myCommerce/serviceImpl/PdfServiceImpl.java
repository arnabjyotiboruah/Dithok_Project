package com.dithok.myCommerce.serviceImpl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Element;

import com.dithok.myCommerce.Repo.PdfRepository;
import com.dithok.myCommerce.model.Details;
import com.dithok.myCommerce.model.HeaderInfoPdf;
import com.dithok.myCommerce.model.PdfModel;
import com.dithok.myCommerce.service.PdfService;
import com.dithok.myCommerce.service.XmlReaderService;
import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.border.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;


@Service
public class PdfServiceImpl implements PdfService{
	@Autowired 
	private PdfRepository repo;
	
	@Autowired
	private XmlReaderService xmlReader;

	
	@Autowired
	private CreateHeaderServiceImpl createHeader;
	
	@Autowired
	private CreateFooterServiceImpl createFooter;
	
//	@SuppressWarnings("unused")
//	private static Logger logger = LoggerFactory.getLogger(PdfServiceImpl.class);

	@Override
	public List<PdfModel> findAllData() {
		return repo.findAll();
	}

	@Override
	public ByteArrayInputStream sqlPDFReport(List<PdfModel> pdf) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		String path = new File("src/main/resources/PdfOutline.pdf").getAbsolutePath();
		File file = new File(path);
		

		PdfDocument pdfDoc = null;
		try {
			pdfDoc = new PdfDocument( new PdfWriter(path));
		} catch (FileNotFoundException e6) {
			// TODO Auto-generated catch block
			e6.printStackTrace();
		}
		Document doc = new Document(pdfDoc);
		PdfFont font = null;
		try {
			font = PdfFontFactory.createFont(FontConstants.COURIER_BOLD);
		} catch (IOException e6) {
			// TODO Auto-generated catch block
			e6.printStackTrace();
		}
		Paragraph para = new Paragraph("Transaction Table");
		para.setFont(font).setFontSize(14).setTextAlignment(TextAlignment.CENTER);
		Paragraph lineBreak = new Paragraph(" ");
		doc.add(lineBreak);
		
	    String pathXml = new File("src/main/resources/configuration.xml").getAbsolutePath();                                                        
		File fileXml = new File(pathXml);
		
		Details info = new Details();
		info =  xmlReader.readData(fileXml);
		
		
		
		
		String imagePath = info.getHeaderImage();
		ImageData data = null;
		try {
			data = ImageDataFactory.create(imagePath);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		Image img = new Image(data);
		createHeader = new CreateHeaderServiceImpl(doc,img);	
		createFooter = new CreateFooterServiceImpl(doc,info);
	
		
		
		pdfDoc.addEventHandler(PdfDocumentEvent.END_PAGE, createHeader);
		pdfDoc.addEventHandler(PdfDocumentEvent.END_PAGE, createFooter);

		
		
		
//		HeaderInfoPdf headInfo = new HeaderInfoPdf();
//		headInfo = ReadXml.readInfo();
		
		
		float [] widths = {200f,200f, 200f};
		Table table_info = new Table(widths);
		Cell cInfo1 = new Cell();
		cInfo1.setBorder(Border.NO_BORDER);
		table_info.addCell(cInfo1);
		
		Cell gap1 = new Cell();
		gap1.setBorder(Border.NO_BORDER);
		table_info.addCell(gap1);
		
		Cell cInfo2 = new Cell();
//		String p1 = headInfo.getTel1();
//		String p2 = headInfo.getTelinfo();
//		String p3 = headInfo.getTel2();
//		String p4 = headInfo.getTel2info();
//		String p5 = headInfo.getUrl();
		cInfo2.add("Contact tel xxxx xxx xxx").add(lineBreak).add("Text Phone xxxx xxx xxx").add(lineBreak).add(new Paragraph("used by deaf or speech impaired customers").setTextAlignment(TextAlignment.RIGHT)).add(lineBreak).add("www.abc.co.in");
		cInfo2.setBorder(Border.NO_BORDER);
		table_info.addCell(cInfo2);
		
		Cell cInfo3 = new Cell();
		Paragraph info1 = new Paragraph("Mr. Abhishek Gogoi");
		Paragraph info2 = new Paragraph("Mitra Bora Lane");

		cInfo3.add(info1).add(lineBreak).add(info2);
		cInfo3.setBorder(Border.NO_BORDER);
		table_info.addCell(cInfo3);
		
		Cell gap2 = new Cell();
		gap2.setBorder(Border.NO_BORDER);
		table_info.addCell(gap2);
		
		Cell cInfo4 = new Cell();
		cInfo4.setBorder(Border.NO_BORDER);
		Paragraph infopara1 = new Paragraph("Your Statement").setTextAlignment(TextAlignment.RIGHT).setPaddingBottom(10).setFontSize(15);
		cInfo4.add(infopara1);
		
		//Nested Table
		Table nestedTable = new Table(new float[]{100f,100f});
		Cell nestedCell1 = new Cell();
		nestedCell1.add("Account Summary").setFontSize(10);
		nestedCell1.setBorder(Border.NO_BORDER);
		nestedTable.addCell(nestedCell1);
		
		Cell nestedCell2 = new Cell();
		nestedCell2.setBorder(Border.NO_BORDER);
		nestedTable.addCell(nestedCell2);
		
		Cell nestedCell3 = new Cell();
		nestedCell3.add("Opening Balance").setFontSize(10);
		nestedCell3.setBorder(Border.NO_BORDER);
		nestedTable.addCell(nestedCell3);
		
		Cell nestedCell4 = new Cell();
		nestedCell4.add("xxxxx").setTextAlignment(TextAlignment.RIGHT);
		nestedCell4.setBorder(Border.NO_BORDER);
		nestedTable.addCell(nestedCell4);
		
		Cell nestedCell5 = new Cell();
		nestedCell5.add("Payments In").setFontSize(10);
		nestedCell5.setBorder(Border.NO_BORDER);
		nestedTable.addCell(nestedCell5);
		
		Cell nestedCell6 = new Cell();
		nestedCell6.add("xxxxx").setTextAlignment(TextAlignment.RIGHT);
		nestedCell6.setBorder(Border.NO_BORDER);
		nestedTable.addCell(nestedCell6);
		
		Cell nestedCell7 = new Cell();
		nestedCell7.add("Payments Out").setFontSize(10);
		nestedCell7.setBorder(Border.NO_BORDER);
		nestedTable.addCell(nestedCell7);
		
		Cell nestedCell8 = new Cell();
		nestedCell8.add("xxxxx").setTextAlignment(TextAlignment.RIGHT);
		nestedCell8.setBorder(Border.NO_BORDER);
		nestedTable.addCell(nestedCell8);
		
		Cell nestedCell9 = new Cell();
		nestedCell9.add("Closing Balance").setFontSize(10);
		nestedCell9.setBorder(Border.NO_BORDER);
		nestedTable.addCell(nestedCell9);
		
		Cell nestedCell10 = new Cell();
		nestedCell10.add("xxxxx").setTextAlignment(TextAlignment.RIGHT);
		nestedCell10.setBorder(Border.NO_BORDER);
		nestedTable.addCell(nestedCell10);
		nestedTable.setBackgroundColor(Color.LIGHT_GRAY);
		nestedTable.setPaddingTop(20);
		cInfo4.add(nestedTable);
		table_info.addCell(cInfo4);
		
		Cell cInfo5 = new Cell();
		cInfo5.add("29 April To 01 May 2021");
		cInfo5.setBorder(Border.NO_BORDER);
		table_info.addCell(cInfo5);
		
		Cell gap3 = new Cell();
		gap3.setBorder(Border.NO_BORDER);
		table_info.addCell(gap3);
		
		Cell cInfo6 = new Cell();
		cInfo6.add("Account Number").add(lineBreak).add("xxxxxxxxxxxxxxxx").add(lineBreak).add("Branch Code").add(lineBreak).add("xxxxxxxxxxxxxx");
		cInfo6.setBorder(Border.NO_BORDER);
		table_info.addCell(cInfo6);
		
		Cell cInfo7 = new Cell();
		cInfo7.add("Account Name").add(lineBreak).add(info1);
		cInfo7.setBorder(Border.NO_BORDER);
		table_info.addCell(cInfo7);
		
		Cell gap4 = new Cell();
		gap4.setBorder(Border.NO_BORDER);
		table_info.addCell(gap4);
		
		Cell cInfo8 = new Cell();
		cInfo8.setBorder(Border.NO_BORDER);
		Table nestedTable2 = new Table(new float[]{66f,66f,66f});
		Cell nestcell1 = new Cell();
		nestcell1.add("Sort Code");
		nestcell1.setBorder(Border.NO_BORDER);
		nestedTable2.addCell(nestcell1);
		
		Cell nestcell2 = new Cell();
		nestcell2.add("Account Number");
		nestcell2.setBorder(Border.NO_BORDER);
		nestedTable2.addCell(nestcell2);
		
		Cell nestcell3 = new Cell();
		nestcell3.add("Sheet Number");
		nestcell3.setBorder(Border.NO_BORDER);
		nestedTable2.addCell(nestcell3);
		
		Cell nestcell4 = new Cell();
		nestcell4.add("xxxxx");
		nestcell4.setBorder(Border.NO_BORDER);
		nestedTable2.addCell(nestcell4);
		Cell nestcell5 = new Cell();
		nestcell5.add("xxxxx");
		nestcell5.setBorder(Border.NO_BORDER);
		nestedTable2.addCell(nestcell5);
		
		Cell nestcell6 = new Cell();
		nestcell6.add("xxxxx");
		nestcell6.setBorder(Border.NO_BORDER);
		nestedTable2.addCell(nestcell6);
		
		cInfo8.add(nestedTable2);
		table_info.addCell(cInfo8);
		//Creating Table
		
			
			
		 float [] columnWidths = {50f,50f,350f,50f,50f,50f};		
		 Table table = new Table(columnWidths);
		 //Adding cells
		 
		 Cell c1 = new Cell();
		 try {
			c1.add("Date").setFont(PdfFontFactory.createFont(FontConstants.HELVETICA)).setBorder(new SolidBorder(2f));
		} catch (IOException e5) {
			// TODO Auto-generated catch block
			e5.printStackTrace();
		}
		 table.addCell(c1);
		 
		 Cell c2 = new Cell();
		 try {
			c2.add("Payment Type").setFont(PdfFontFactory.createFont(FontConstants.HELVETICA)).setBorder(new SolidBorder(2f));
		} catch (IOException e4) {
			// TODO Auto-generated catch block
			e4.printStackTrace();
		}
		 table.addCell(c2);
		 
		 Cell c3 = new Cell();
		 try {
			c3.add("Payment Details").setFont(PdfFontFactory.createFont(FontConstants.HELVETICA)).setBorder(new SolidBorder(2f));
		} catch (IOException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		 table.addCell(c3);
		 
		 Cell c4 = new Cell();
		 try {
			c4.add("Paid Out").setFont(PdfFontFactory.createFont(FontConstants.HELVETICA)).setBorder(new SolidBorder(2f));
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		 table.addCell(c4);
		 
		 Cell c5 = new Cell();
		 try {
			c5.add("Paid In").setFont(PdfFontFactory.createFont(FontConstants.HELVETICA)).setBorder(new SolidBorder(2f));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 table.addCell(c5);
		 
		 Cell c6 = new Cell();
		 try {
			c6.add("Balance").setFont(PdfFontFactory.createFont(FontConstants.HELVETICA)).setBorder(new SolidBorder(2f));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 table.addCell(c6);
		 
		 

		
		for(PdfModel pdfNew : pdf) {
			
				
			
			Cell data1 = new Cell();
			data1.add(pdfNew.getDate().toString());
			table.addCell(data1);
			
			Cell data2 = new Cell();
			data2.add(pdfNew.getPay_type());
			table.addCell(data2);
			
			Cell data3 = new Cell();

			data3.add(pdfNew.getPay_details());
			
			table.addCell(data3);
			
			Cell data4 = new Cell();
			data4.add(pdfNew.getPaid_out().toString());
			table.addCell(data4);
			
			Cell data5 = new Cell();
			data5.add(pdfNew.getPaid_in().toString());
			table.addCell(data5);
			
			Cell data6 = new Cell();
			data6.add(pdfNew.getBalance().toString());
			table.addCell(data6);
			
			
		}
		
//		doc.setTopMargin(40);
		doc.add(table_info.setMarginTop(90));
		doc.add(table.setMarginTop(40));
		
		doc.close();
		
		return new ByteArrayInputStream(out.toByteArray());
	}

}

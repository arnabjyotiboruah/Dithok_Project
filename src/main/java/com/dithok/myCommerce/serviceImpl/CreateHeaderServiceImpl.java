package com.dithok.myCommerce.serviceImpl;

import org.springframework.stereotype.Service;

import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.layout.LayoutArea;
import com.itextpdf.layout.layout.LayoutContext;
import com.itextpdf.layout.layout.LayoutResult;
import com.itextpdf.layout.renderer.DocumentRenderer;
import com.itextpdf.layout.renderer.TableRenderer;

@Service
public class CreateHeaderServiceImpl implements IEventHandler{
	

	private Table table;
	private float tableHeight;
	private Document doc;
	
	@Override
	public void handleEvent(Event event) {
		PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
		PdfDocument pdfDoc = docEvent.getDocument();
		PdfPage page = docEvent.getPage();
		PdfCanvas canvas = new PdfCanvas(page.newContentStreamBefore(), page.getResources(), pdfDoc);
		PageSize pageSize = pdfDoc.getDefaultPageSize();
		float coordX = pageSize.getX() + doc.getLeftMargin();
		float coordY = pageSize.getTop() - doc.getTopMargin();
		float width = pageSize.getWidth() - doc.getRightMargin() - doc.getLeftMargin();
		float height = getTableHeight();
		Rectangle rect = new Rectangle(coordX, coordY, width, height);
		
		new Canvas(canvas, pdfDoc, rect).add(table).close();
		
	}

	public float getTableHeight() {
		return tableHeight;
	}

	public CreateHeaderServiceImpl(Document doc, Image img) {
		this.doc=doc;
		initTable(img);
		
		TableRenderer renderer = (TableRenderer) table.createRendererSubTree();
		renderer.setParent(new DocumentRenderer(doc));
		
		LayoutResult result = renderer.layout(new LayoutContext(new LayoutArea(0, PageSize.A4)));
		tableHeight = result.getOccupiedArea().getBBox().getHeight();
		
		float topMargin = 36 + getTableHeight();
		doc.setMargins(topMargin, 36, 36, 36);
	}

	public void initTable(Image img) {
		table = new Table(new float[]{200f, 320f});
		table.useAllAvailableWidth();
		img.setWidth(200);
		Cell logo = new Cell();
		logo.setBorder(Border.NO_BORDER);
		logo.add(img);
		table.addCell(logo);
		table.setMarginBottom(20);
		
		Cell blank = new Cell();
		blank.setBorder(Border.NO_BORDER);
		table.addCell(blank);
		
	}


	public CreateHeaderServiceImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

//	public void CreateHeaderServiceImpl(Document doc, Image img) {
//	this.doc=doc;
//	initTable(img);
//	
//	TableRenderer renderer = (TableRenderer) table.createRendererSubTree();
//	renderer.setParent(new DocumentRenderer(doc));
//	
//	LayoutResult result = renderer.layout(new LayoutContext(new LayoutArea(0, PageSize.A4)));
//	tableHeight = result.getOccupiedArea().getBBox().getHeight();
//	
//	float topMargin = 36 + getTableHeight();
//	doc.setMargins(topMargin, 36, 36, 36);
//}
	

}

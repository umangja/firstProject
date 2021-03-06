package com.umang.files;

import java.util.Date;
import java.util.List;
import java.util.Map;

import java.io.FileOutputStream;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.umang.dao.Billdao;
import com.umang.dao.MedicinePurchaseddao;
import com.umang.dao.Medicinedao;
import com.umang.model.Address;
import com.umang.model.Bill;
import com.umang.model.CONSTANTS;
import com.umang.model.Medicine;
import com.umang.model.Medicine_purchased;

/**
 * This view class generates a PDF document 'on the fly' based on the data
 * contained in the model.
 * 
 * @author www.codejava.net
 *
 */
public class PDFBuilder extends AbstractITextPdfView {

	@Autowired
	MedicinePurchaseddao medicinePurchasedao;

	@Autowired
	Medicinedao medicinedao;

	@Autowired
	Billdao billdao;

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document doc, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		int bill_id = (Integer) model.get("bill_id");
		
    	//System.out.println("bill is " + bill_id);
		
		
		@SuppressWarnings("unchecked")
		List<Medicine> medicines = (List<Medicine>) model.get("medicines");
		@SuppressWarnings("unchecked")
		List<Medicine_purchased> medicine_purchased =  (List<Medicine_purchased>)model.get("medicine_purchased");
		Bill bill = (Bill) model.get("bill");
		Address add = (Address) model.get("address");
		String name = (String) model.get("cust_name");

		 
		 if(bill==null || medicines==null)
		 {
			 System.out.println("bill or medicine is null");
			 return ;
		 }
		 
	   
	    Font catFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, (float)18.0,Font.NORMAL);
	    Font redFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12,Font.NORMAL,new CMYKColor(0, 255, 0, 0));
	    Font smallBold = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12,Font.BOLD);
	    
	    
	    
	    
        Paragraph preface = new Paragraph();
        addEmptyLine(preface, 2);
        
        
	    String imageUrl = "http://localhost:8080/MedicalCompany/resources/images/logo.png";
	    Image image2 = Image.getInstance(new URL(imageUrl));
	    image2.setAbsolutePosition(20f, 675f);
	    image2.scaleAbsolute(200, 200);
	    preface.add(image2);
	    addEmptyLine(preface, 3);
        
        preface.add(new Paragraph("INVOICE", catFont));
        
        addEmptyLine(preface, 1);
        preface.add(new Paragraph("INVOICE generated by: " + bill.getEmployee_issuing() + " on " + new Date(), smallBold));
        addEmptyLine(preface, 1);
        preface.add(new Paragraph("This is a computer generated bill/invoice",smallBold));
        addEmptyLine(preface, 3);
        
        
        preface.add(new Paragraph("Billed To", catFont));
        addEmptyLine(preface, 1);
        preface.add(new Paragraph(name, smallBold));
        if(add!=null)
        {
        	preface.add(new Paragraph("shop no"+add.getShop_no(), smallBold));
        	preface.add(new Paragraph(add.getColony_name(), smallBold));
        	preface.add(new Paragraph(""+add.getCity()+","+add.getState(), smallBold));
        	preface.add(new Paragraph(""+add.getPincode(), smallBold));
        }
        else
        {
        	preface.add(new Paragraph("XXXXXX", smallBold));
        	preface.add(new Paragraph("XXXXXX", smallBold));
        	preface.add(new Paragraph("XXXXXX", smallBold));
        	preface.add(new Paragraph("XXXXXX", smallBold));
        }
        
        addEmptyLine(preface, 3);
        
        
        
        PdfPTable table = new PdfPTable(5);

		table.setWidthPercentage(100.0f);
		table.setWidths(new float[] { 3.0f, 2.0f, 2.0f, 2.0f, 1.0f });
		table.setSpacingBefore(10);
	

        PdfPCell c1 = new PdfPCell(new Phrase("Medicine Name"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setPadding(4);
        c1.setBorderWidth(2);
        table.addCell(c1);
        
        c1 = new PdfPCell(new Phrase("Medicine Company"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setPadding(4);
        c1.setBorderWidth(2);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Unit Cost"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setPadding(4);
        c1.setBorderWidth(2);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Quantity"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setPadding(4);
        c1.setBorderWidth(2);
        table.addCell(c1);
        
        c1 = new PdfPCell(new Phrase("Total"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setPadding(4);
        c1.setBorderWidth(2);
        table.addCell(c1);
        
        
        table.setHeaderRows(1);
    	
        int total=0;
        for(int i=0;i<medicines.size();i++)
        {
        	Medicine med = medicines.get(i);
        	Medicine_purchased medp =medicine_purchased.get(i);
        	
        	table.addCell(med.getName());
        	table.addCell(""+med.getCompany());
        	table.addCell(""+med.getPrice());
        	table.addCell(""+medp.getQuantity());
        	table.addCell(""+med.getPrice()*medp.getQuantity());
        	total=total+med.getPrice()*medp.getQuantity();
        }
       
        preface.add(table);
         
        addEmptyLine(preface, 3);
        
      
        
        
        table = new PdfPTable(2);
        table.setHorizontalAlignment(Element.ALIGN_RIGHT);
		table.setWidthPercentage(25.0f);
	
		table.setWidths(new float[] { 3.0f, 1.0f});
		table.setSpacingBefore(10);
	

        c1 = new PdfPCell(new Phrase("Subtotal"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setPadding(4);
        c1.setBorderWidth(2);
        table.addCell(c1);
        
        table.addCell(""+total);
        
        c1 = new PdfPCell(new Phrase("Discount"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setPadding(4);
        c1.setBorderWidth(2);
        table.addCell(c1);
        
        table.addCell(""+bill.getDiscount_offered());
        
        
        c1 = new PdfPCell(new Phrase("CGST"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setPadding(4);
        c1.setBorderWidth(2);
        table.addCell(c1);
        
        CONSTANTS cons = new CONSTANTS(); 
        table.addCell(""+cons.getCgst());
        
        c1 = new PdfPCell(new Phrase("SGST"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setPadding(4);
        c1.setBorderWidth(2);
        table.addCell(c1);
        
        table.addCell(""+cons.getSgst());
        
        c1 = new PdfPCell(new Phrase("Total",redFont));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setPadding(4);
        c1.setBorderWidth(2);
        table.addCell(c1);
        
        
        c1 = new PdfPCell(new Phrase(""+bill.getTotal(),redFont));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setPadding(4);
        c1.setBorderWidth(2);
        table.addCell(c1);
        
        preface.add(table);
        
        
        
        addEmptyLine(preface, 3);
        
        
        if(bill.getTransaction_id()==0)
        {
        	preface.add(new Paragraph("Ammount Paid by Cash", smallBold));
        }
        else
        {
        	preface.add(new Paragraph("Ammount Paid Online with id : "+bill.getTransaction_id(), smallBold));
        }
        
        addEmptyLine(preface, 1);
        
        preface.add(new Paragraph("This is a computer generated invoice and Does not require Signature", smallBold));
        addEmptyLine(preface, 1);        
        
        doc.add(preface);
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document doc,HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int bill_id = (Integer) model.get("bill_id");
		
		
        PdfWriter writer = PdfWriter.getInstance(doc,new FileOutputStream(""+bill_id));
        writer.setViewerPreferences(getViewerPreferences());
		doc.open();
		
		
		

    	//System.out.println("bill is " + bill_id);
		
		
		@SuppressWarnings("unchecked")
		List<Medicine> medicines = (List<Medicine>) model.get("medicines");
		@SuppressWarnings("unchecked")
		List<Medicine_purchased> medicine_purchased =  (List<Medicine_purchased>)model.get("medicine_purchased");
		Bill bill = (Bill) model.get("bill");
		Address add = (Address) model.get("address");
		String name = (String) model.get("cust_name");

		 
		 if(bill==null || medicines==null)
		 {
			 System.out.println("bill or medicine is null");
			 return ;
		 }
		 
	   
	    Font catFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, (float)18.0,Font.NORMAL);
	    Font redFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12,Font.NORMAL,new CMYKColor(0, 255, 0, 0));
	    Font smallBold = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12,Font.BOLD);
	    
        Paragraph preface = new Paragraph();
        addEmptyLine(preface, 2);
        
	    String imageUrl = "http://umangmedical.herokuapp.com/resources/images/logo.png";
	    Image image2 = Image.getInstance(new URL(imageUrl));
	    image2.setAbsolutePosition(20f, 675f);
	    image2.scaleAbsolute(200, 200);
	    preface.add(image2);
	    addEmptyLine(preface, 3);
        
        
        preface.add(new Paragraph("INVOICE", catFont));
        
        addEmptyLine(preface, 1);
        preface.add(new Paragraph("INVOICE generated by: " + bill.getEmployee_issuing() + " on " + new Date(), smallBold));
        addEmptyLine(preface, 1);
        preface.add(new Paragraph("This is a computer generated bill/invoice",smallBold));
        addEmptyLine(preface, 3);
        
        
        preface.add(new Paragraph("Billed To", catFont));
        addEmptyLine(preface, 1);
        preface.add(new Paragraph(name, smallBold));
        if(add!=null)
        {
        	preface.add(new Paragraph("shop no"+add.getShop_no(), smallBold));
        	preface.add(new Paragraph(add.getColony_name(), smallBold));
        	preface.add(new Paragraph(""+add.getCity()+","+add.getState(), smallBold));
        	preface.add(new Paragraph(""+add.getPincode(), smallBold));
        }
        else
        {
        	preface.add(new Paragraph("XXXXXX", smallBold));
        	preface.add(new Paragraph("XXXXXX", smallBold));
        	preface.add(new Paragraph("XXXXXX", smallBold));
        	preface.add(new Paragraph("XXXXXX", smallBold));
        }
        
        addEmptyLine(preface, 3);
        
        
        
        PdfPTable table = new PdfPTable(5);

		table.setWidthPercentage(100.0f);
		table.setWidths(new float[] { 3.0f, 2.0f, 2.0f, 2.0f, 1.0f });
		table.setSpacingBefore(10);
	

        PdfPCell c1 = new PdfPCell(new Phrase("Medicine Name"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setPadding(4);
        c1.setBorderWidth(2);
        table.addCell(c1);
        
        c1 = new PdfPCell(new Phrase("Medicine Company"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setPadding(4);
        c1.setBorderWidth(2);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Unit Cost"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setPadding(4);
        c1.setBorderWidth(2);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Quantity"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setPadding(4);
        c1.setBorderWidth(2);
        table.addCell(c1);
        
        c1 = new PdfPCell(new Phrase("Total"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setPadding(4);
        c1.setBorderWidth(2);
        table.addCell(c1);
        
        
        table.setHeaderRows(1);
    	
        int total=0;
        for(int i=0;i<medicines.size();i++)
        {
        	Medicine med = medicines.get(i);
        	Medicine_purchased medp =medicine_purchased.get(i);
        	
        	table.addCell(med.getName());
        	table.addCell(""+med.getCompany());
        	table.addCell(""+med.getPrice());
        	table.addCell(""+medp.getQuantity());
        	table.addCell(""+med.getPrice()*medp.getQuantity());
        	total=total+med.getPrice()*medp.getQuantity();
        }
       
        preface.add(table);
         
        addEmptyLine(preface, 3);
        
      
        
        
        table = new PdfPTable(2);
        table.setHorizontalAlignment(Element.ALIGN_RIGHT);
		table.setWidthPercentage(25.0f);
	
		table.setWidths(new float[] { 3.0f, 1.0f});
		table.setSpacingBefore(10);
	

        c1 = new PdfPCell(new Phrase("Subtotal"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setPadding(4);
        c1.setBorderWidth(2);
        table.addCell(c1);
        
        table.addCell(""+total);
        
        c1 = new PdfPCell(new Phrase("Discount"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setPadding(4);
        c1.setBorderWidth(2);
        table.addCell(c1);
        
        table.addCell(""+bill.getDiscount_offered());
        
        
        c1 = new PdfPCell(new Phrase("CGST"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setPadding(4);
        c1.setBorderWidth(2);
        table.addCell(c1);
        
        CONSTANTS cons = new CONSTANTS(); 
        table.addCell(""+cons.getCgst());
        
        c1 = new PdfPCell(new Phrase("SGST"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setPadding(4);
        c1.setBorderWidth(2);
        table.addCell(c1);
        
        table.addCell(""+cons.getSgst());
        
        c1 = new PdfPCell(new Phrase("Total",redFont));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setPadding(4);
        c1.setBorderWidth(2);
        table.addCell(c1);
        
        
        c1 = new PdfPCell(new Phrase(""+bill.getTotal(),redFont));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setPadding(4);
        c1.setBorderWidth(2);
        table.addCell(c1);
        
        preface.add(table);
        
        
        
        addEmptyLine(preface, 3);
        
        
        if(bill.getTransaction_id()==0)
        {
        	preface.add(new Paragraph("Ammount Paid by Cash", smallBold));
        }
        else
        {
        	preface.add(new Paragraph("Ammount Paid Online with id : "+bill.getTransaction_id(), smallBold));
        }
        
        addEmptyLine(preface, 1);
        
        preface.add(new Paragraph("This is a computer generated invoice and Does not require Signature", smallBold));
        addEmptyLine(preface, 1);        
        
        doc.add(preface);
        doc.close();
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }

}

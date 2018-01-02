/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reportes;

import GUI.FrnProyecto;
import GUI.Impresion;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Home
 */
public class ReporteRequisito {
    public void reporte(){
    try {
                 Connection cn=null;
                Statement st=null;
                ResultSet rs=null;
                   FrnProyecto ventanaproy=new FrnProyecto();
     
                 Class.forName("com.mysql.jdbc.Driver");
  //cn=DriverManager.getConnection("jdbc:mysql://mysql22353-mariscal2424.j.facilcloud.com/relacion","root","QKElak40188");
          cn=DriverManager.getConnection("jdbc:mysql://localhost/relacion1","root","");
            st=cn.createStatement();
            rs=st.executeQuery("select * from requisito where numproy='"+ventanaproy.num+"'");
            
            if(cn!=null){
                Impresion ventanaimp=new Impresion();
                 
            try {
                
                Document documento=new Document();
                PdfWriter.getInstance(documento, new FileOutputStream(ventanaimp.ruta+".reporterequisito.pdf"));
                documento.open();
                
                Paragraph par1=new Paragraph();
                Font fontitulo=new Font(Font.FontFamily.HELVETICA,16,Font.BOLD,BaseColor.BLUE);
                par1.add(new Phrase("GESTION DE PROYECTOS DE LA EMPRESA ISAGEN",fontitulo));
                par1.setAlignment(Element.ALIGN_CENTER);
                par1.add(new Phrase(Chunk.NEWLINE));
                                par1.add(new Phrase(Chunk.NEWLINE));
                 documento.add(par1);
                
                 
                 
                 
                   Paragraph par2=new Paragraph();
                Font descripcion=new Font(Font.FontFamily.HELVETICA,13,Font.BOLD,BaseColor.BLACK);
                par2.add(new Phrase("REQUSITOS DEL PROYECTO ",descripcion));
                par2.setAlignment(Element.ALIGN_CENTER);
                par2.add(new Phrase(Chunk.NEWLINE));
                  par2.add(new Phrase(Chunk.NEWLINE));
                 documento.add(par2);
                 
                 
                 
                 PdfPTable tabla=new PdfPTable(6);
                   tabla.setWidthPercentage(100);
                 PdfPCell celda1=new PdfPCell(new Paragraph("NUM. REQUISITO",FontFactory.getFont("Arial",12,Font.BOLD,BaseColor.BLACK )));
                 PdfPCell celda2=new PdfPCell(new Paragraph("ALCANCE",FontFactory.getFont("Arial",12,Font.BOLD,BaseColor.BLACK )));
                 PdfPCell celda3=new PdfPCell(new Paragraph("PERSONAL",FontFactory.getFont("Arial",12,Font.BOLD,BaseColor.BLACK )));
                 PdfPCell celda4=new PdfPCell(new Paragraph("CANT. REUNIONES",FontFactory.getFont("Arial",12,Font.BOLD,BaseColor.BLACK )));
                 PdfPCell celda5=new PdfPCell(new Paragraph("DESCRPCION",FontFactory.getFont("Arial",12,Font.BOLD,BaseColor.BLACK )));
                 PdfPCell celda6=new PdfPCell(new Paragraph("NUM. PROYECTO",FontFactory.getFont("Arial",12,Font.BOLD,BaseColor.BLACK )));
               
                 tabla.addCell(celda1);               
                 tabla.addCell(celda2);
                 tabla.addCell(celda3);
                 tabla.addCell(celda4);
                 tabla.addCell(celda5);
                 tabla.addCell(celda6);
                 
                 while(rs.next()){
                     tabla.addCell(rs.getString(1));               
           tabla.addCell(rs.getString(2));               
         tabla.addCell(rs.getString(3));               
         tabla.addCell(rs.getString(4));               
        tabla.addCell(rs.getString(5));          
        tabla.addCell(rs.getString(6));                
                 }
                 documento.add(tabla);
                 
                 
               
                 
            documento.close();
              //abrir automaticamente pdf
                // Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + ""+ventanaimp.ruta+".reporterequisito.pdf");
              String rut=new String(ventanaimp.ruta+".reporterequisito.pdf");
                  File path = new File (rut);
             Desktop.getDesktop().open(path);
            
            } catch (Exception e) {
                e.getMessage();
            }
     }} catch (Exception e) {
            }
     
 }
}

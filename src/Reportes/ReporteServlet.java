
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
import javax.swing.JFileChooser;


public class ReporteServlet {


 
 public void impre(){
     
                 try {
                Connection cn=null;
                Statement st=null;
                ResultSet rs=null;
                     FrnProyecto ventanaproy=new FrnProyecto();
                     
     
                 Class.forName("com.mysql.jdbc.Driver");
  //cn=DriverManager.getConnection("jdbc:mysql://mysql22353-mariscal2424.j.facilcloud.com/relacion","root","QKElak40188");
         cn=DriverManager.getConnection("jdbc:mysql://localhost/relacion1","root","");
            st=cn.createStatement();
            rs=st.executeQuery("select * from proy where numero='"+ventanaproy.num+"'");
            
            if(cn!=null){
                Impresion ventanaimp=new Impresion();
            try {
              
                
                Document documento=new Document();
                PdfWriter.getInstance(documento, new FileOutputStream(ventanaimp.ruta+".reporteproyecto.pdf"));
                documento.open();
                
              //  Image imagenes=Image.getInstance("logo.jpg");
                //imagenes.setAlignment(Element.ALIGN_RIGHT);
                //imagenes.scaleToFit(100,100);
                //documento.add(imagenes);
                
                
                Paragraph par1=new Paragraph();
                Font fontitulo=new Font(Font.FontFamily.HELVETICA,16,Font.BOLD,BaseColor.BLUE);
                par1.add(new Phrase("GESTION DE PROYECTOS DE LA EMPRESA ISAGEN ",fontitulo));
                par1.setAlignment(Element.ALIGN_CENTER);
                par1.add(new Phrase(Chunk.NEWLINE));
                                par1.add(new Phrase(Chunk.NEWLINE));
                 documento.add(par1);
                
                 
                 
                 
                   Paragraph par2=new Paragraph();
                Font descripcion=new Font(Font.FontFamily.HELVETICA,13,Font.BOLD,BaseColor.BLACK);
                par2.add(new Phrase("INFORMACION DEL PROYECTO ",descripcion));
                par2.setAlignment(Element.ALIGN_CENTER);
                par2.add(new Phrase(Chunk.NEWLINE));
                  par2.add(new Phrase(Chunk.NEWLINE));
                 documento.add(par2);
                 
                 
                 
                 PdfPTable tabla=new PdfPTable(10);
                 tabla.setWidthPercentage(100);
                 
                 PdfPCell celda1=new PdfPCell(new Paragraph("NUM. NUMERO",FontFactory.getFont("Arial",12,Font.BOLD,BaseColor.BLACK )));
                 PdfPCell celda2=new PdfPCell(new Paragraph("TITULO",FontFactory.getFont("Arial",12,Font.BOLD,BaseColor.BLACK )));
                 PdfPCell celda3=new PdfPCell(new Paragraph("DURACION",FontFactory.getFont("Arial",12,Font.BOLD,BaseColor.BLACK )));
                 PdfPCell celda4=new PdfPCell(new Paragraph("DESCRIPCION",FontFactory.getFont("Arial",12,Font.BOLD,BaseColor.BLACK )));
                 PdfPCell celda5=new PdfPCell(new Paragraph("TIPO",FontFactory.getFont("Arial",12,Font.BOLD,BaseColor.BLACK )));
                 PdfPCell celda6=new PdfPCell(new Paragraph("FASES",FontFactory.getFont("Arial",12,Font.BOLD,BaseColor.BLACK )));
                 PdfPCell celda7=new PdfPCell(new Paragraph("INICIO",FontFactory.getFont("Arial",12,Font.BOLD,BaseColor.BLACK )));
                 PdfPCell celda8=new PdfPCell(new Paragraph("FIN",FontFactory.getFont("Arial",12,Font.BOLD,BaseColor.BLACK )));
                 PdfPCell celda9=new PdfPCell(new Paragraph("GASTOS",FontFactory.getFont("Arial",12,Font.BOLD,BaseColor.BLACK )));
                 PdfPCell celda10=new PdfPCell(new Paragraph("CODIGO JEFE",FontFactory.getFont("Arial",12,Font.BOLD,BaseColor.BLACK )));
 
                 tabla.addCell(celda1);               
                 tabla.addCell(celda2);
                 tabla.addCell(celda3);
                 tabla.addCell(celda4);
                 tabla.addCell(celda5);
                 tabla.addCell(celda6);
                 tabla.addCell(celda7);
                 tabla.addCell(celda8);
                 tabla.addCell(celda9);
                 tabla.addCell(celda10);
                 while(rs.next()){
                     tabla.addCell(rs.getString(1));               
           tabla.addCell(rs.getString(2));               
         tabla.addCell(rs.getString(3));               
         tabla.addCell(rs.getString(4));               
        tabla.addCell(rs.getString(5));          
        tabla.addCell(rs.getString(6));                
        tabla.addCell(rs.getString(7)); 
          tabla.addCell(rs.getString(8));               
            tabla.addCell(rs.getString(9));               
              tabla.addCell(rs.getString(10));
                 }
                 documento.add(tabla);
                 
                 
               
                 
            documento.close();
              //abrir automaticamente pdf
                 //Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + ""+ventanaimp.ruta+".reporteproyecto.pdf");
                 
                   String rut=new String(ventanaimp.ruta+".reporteproyecto.pdf");
                  File path = new File (rut);
             Desktop.getDesktop().open(path);
            } catch (Exception e) {
                e.getMessage();
            }
     }} catch (Exception e) {
            }
     
 }
 }
 


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BEAN.CambiosBean;
import BEAN.ProyectoBean;
import DAO.CambiosDAO;
import DAO.ProyectoDAO;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Home
 */
public class FrmCambio extends JFrame implements ActionListener,MouseListener{
    
     private TextField txtumero,txtproposito,txtimportacia;
     private JDateChooser calfecha;
    private JComboBox cboproyecto;
    private  JButton btneliminar,btngrabar,btnmodificar,btnalctualizar;
    private JTable tabla;
    private JScrollPane Panel;
    private JLabel lblcodigo,lbltitulo,lblduracion,lbldescripcion,lblfases,lblpresupuesto,lblcoordinador,lbltipo,lblinicio,lblfin;
    DefaultTableModel tablemodel;
    
    ArrayList<ProyectoBean> listaproy;
    ProyectoBean objproybean;
    ProyectoDAO objproydao;
    ArrayList<CambiosBean> listacam;
    CambiosBean objcambiosbean;
    CambiosDAO objcambiosdao;
   
    public FrmCambio(){
        GUI();
        objproybean=new ProyectoBean();
        objproydao=new ProyectoDAO();
        objcambiosbean=new CambiosBean();
        objcambiosdao=new CambiosDAO();
        mostrar();
        combo1();
    }
    
    public static void main(String[] args) {
        FrmCambio principal=new FrmCambio();
        principal.setVisible(true);
        principal.setLocationRelativeTo(null);
                
                
                
     }
    
    public void GUI(){
         String columnas[]={"NUMERO","PROYECTO ELEGIDO","FECHA","PROPOSITO","IMPORTACIA"};
         try {
             setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
             getContentPane().setLayout(null);
               getContentPane().setBackground(new Color(0, 153, 153));
             
        
             txtumero=new TextField();
             getContentPane().add(txtumero);
             txtumero.setBounds(240, 84, 157, 23);
             
             
             
             cboproyecto=new JComboBox();
             getContentPane().add(cboproyecto);
             cboproyecto.setBounds(240,124,157,23);
             
               calfecha=new JDateChooser();
             getContentPane().add(calfecha);
              calfecha.setLocale(new Locale("es"));
              calfecha.setDateFormatString("yyyy-MM-dd");
              //dato por defecto
              SimpleDateFormat parser1 = new SimpleDateFormat("yyyy-MM-dd");
              Date date1 = parser1.parse(" 2017-01-01");
            calfecha.setDate(date1);
             calfecha.setBounds(240,164,157,23);
             
             
             txtproposito=new TextField();
             getContentPane().add(txtproposito);
             txtproposito.setBounds(240,204,157,23);
             
             txtimportacia=new TextField();
             getContentPane().add(txtimportacia);
             txtimportacia.setBounds(240,244,157,23);

             
             btngrabar=new JButton();
             getContentPane().add(btngrabar);
             btngrabar.setText("GRABAR");
             btngrabar.setBounds(60,364,140,23);
             btngrabar.addActionListener(this);
             btngrabar.setBackground(new Color(0,102,153));
             
                 btnmodificar=new JButton();
             getContentPane().add(btnmodificar);
             btnmodificar.setText("MODIFICAR");
             btnmodificar.setBounds(270,364,140,23);
             btnmodificar.addActionListener(this);
             btnmodificar.setBackground(new Color(0,102,153));
             
                 btneliminar=new JButton();
             getContentPane().add(btneliminar);
             btneliminar.setText("ELIMINAR");
             btneliminar.setBounds(60,414,140,23);
             btneliminar.addActionListener(this);
             btneliminar.setBackground(new Color(0,102,153));
             
             btnalctualizar=new JButton();
             getContentPane().add(btnalctualizar);
             btnalctualizar.setText("ACTULIZAR");
             btnalctualizar.setBounds(270,414,140,23);
             btnalctualizar.addActionListener(this);
             btnalctualizar.setBackground(new Color(0,102,153));
             
             lbltitulo=new JLabel();
             getContentPane().add(lbltitulo);
             lbltitulo.setText("RELACION CAMBIOS");
             lbltitulo.setBounds(160,20,200,30);
             
              Panel=new JScrollPane();
            getContentPane().add(Panel);
            Panel.setBounds(20,450,450,300);
            
            tablemodel=new DefaultTableModel(null,columnas);
            tabla=new JTable();
            Panel.setViewportView(tabla);
            tabla.addMouseListener(this);
            tabla.setModel(tablemodel);
             
            this.setSize(500,800);
         } catch (Exception e) {
             e.printStackTrace();
         }
}
    
    public void combo1(){
        listaproy=objproydao.cargartablaproyecto();
        for(ProyectoBean obj:listaproy){
         cboproyecto.addItem(new  ProyectoBean(obj.getNumero(), obj.getTitulo()));
        }
         if(cboproyecto.getItemCount()<=0){
          cboproyecto.addItem("no hay coordinador");
        }
    }
    
    public void Seleccionar(){
         try{
            
           txtumero.setText(tabla.getValueAt(tabla.getSelectedRow(),0).toString());
            int cod=Integer.parseInt(txtumero.getText());
           
           objcambiosbean.setNUMERO(cod);
           objcambiosbean=objcambiosdao.CapturarCodigo(objcambiosbean);
           txtumero.setText(String.valueOf(objcambiosbean.getNUMERO()));
           
           cboproyecto.removeAllItems();
             cboproyecto.addItem(new ProyectoBean(objcambiosbean.getNUMPROY(),objcambiosbean.getNOMBPROY()));
          combo1();
           
            txtimportacia.setText(objcambiosbean.getIMPORTANCIA());            
            txtproposito.setText(objcambiosbean.getPROPOSITO());
            
             String fecha=(objcambiosbean.getFECHA());
           SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
           Date date1 = parser.parse(fecha);
           calfecha.setDate(date1);
            
           btngrabar.setEnabled(false);
           
        }catch(Exception e){
            
        }
        
    }
    public void mostrar(){
        int cod=objcambiosdao.generarCodigo();
        if(cod==0){
            cod=1;
        }else{
            cod=objcambiosdao.generarCodigo();
        }
        txtumero.setText(String.valueOf(cod));
        txtumero.setEnabled(false);
        calfecha.requestFocus();
        
        int i=0;
        listacam=objcambiosdao.listarCambios1();
        tablemodel.setNumRows(listacam.size());
        for(CambiosBean obj:listacam){
            tablemodel.setValueAt(obj.getNUMERO(), i, 0);
            tablemodel.setValueAt(obj.getNOMBPROY(), i, 1);
            tablemodel.setValueAt(obj.getFECHA(), i, 2);
            tablemodel.setValueAt(obj.getPROPOSITO(), i, 3);
            tablemodel.setValueAt(obj.getIMPORTANCIA(), i, 4);
            i++;
        }
        tabla.setModel(tablemodel);
    }
    
    public void insertar(){
        objcambiosbean.setNUMERO(Integer.parseInt(txtumero.getText()));
        ProyectoBean objcbo=(ProyectoBean)cboproyecto.getSelectedItem();  
        objcambiosbean.setNUMPROY(objcbo.getNumero());
         DateFormat fecha = new SimpleDateFormat("yyyy-MM-dd");
        String convertido = fecha.format(calfecha.getDate());
        objcambiosbean.setFECHA( convertido);
        objcambiosbean.setPROPOSITO(txtproposito.getText());
        objcambiosbean.setIMPORTANCIA(txtimportacia.getText());
        int i=objcambiosdao.grabarCambios(objcambiosbean);
        if(i==0){
            JOptionPane.showMessageDialog(null, "Registro no Insertado");
        }else{
            JOptionPane.showMessageDialog(null, "Registro Insertado Satisfactoriamente");
        }



    }
    
    public void modificar(){
      
       objcambiosbean.setNUMERO(Integer.parseInt(txtumero.getText()));
        ProyectoBean objcbo=(ProyectoBean)cboproyecto.getSelectedItem();  
        objcambiosbean.setNUMPROY(objcbo.getNumero());
         DateFormat fecha = new SimpleDateFormat("yyyy-MM-dd");
        String convertido = fecha.format(calfecha.getDate());
        objcambiosbean.setFECHA( convertido);
        objcambiosbean.setPROPOSITO(txtproposito.getText());
        objcambiosbean.setIMPORTANCIA(txtimportacia.getText());
        int i=objcambiosdao.modificarCambio(objcambiosbean);
        if(i==0){
            JOptionPane.showMessageDialog(null, "Registro no Modificado");
        }else{
            JOptionPane.showMessageDialog(null, "Registro Modiificado Satisfactoriamente");
        }
    }
    public void eliminar(){
        objcambiosbean.setNUMERO(Integer.parseInt(txtumero.getText()));
        int i=objcambiosdao.eliminarCambios(objcambiosbean);
        if(i==0){
            JOptionPane.showMessageDialog(null, "Registro no Eliminado");
        }else{
            JOptionPane.showMessageDialog(null, "Registro Eliminado Satisfactoriamente");
        }
        
    }
    public void Limpiar(){
    txtproposito.setText("");
    txtimportacia.setText("");
    cboproyecto.removeAllItems();
    combo1();
    calfecha.setDateFormatString("yyyy-MM-dd");
    btngrabar.setEnabled(true);
    }
    
    
    public void ValidacionInsertar(){
                                 if(cboproyecto.getItemCount() >0){
                                  if(calfecha.getDate()!=null){
                                      if(!txtproposito.getText().isEmpty()){
                              if(!txtimportacia.getText().isEmpty()){
                                        
                                                  
                                           insertar();
                                             mostrar();
                                             Limpiar();
                                              }else{
                                                         JOptionPane.showMessageDialog(null, "INGRESE LA IMPORTANCIA DEL CAMBIO");
                                                         txtimportacia.requestFocus();
                                                     }
                                                         
                                         }else{
                                                  JOptionPane.showMessageDialog(null, "INGRESE EL PROPOSITO DEL CAMBIO");
                                             txtproposito.requestFocus();
                                             }
                                     }else{
                                              JOptionPane.showMessageDialog(null, "INGRESE LA FECHA DEL CAMBIO");
                                              calfecha.requestFocus();
                                         }
                                 }else{
                                          JOptionPane.showMessageDialog(null, "REGISTRE PRIMERO UN PROYECTO");
                    }
                                    }

    
    public void ValidacionModificar(){
                                 if(cboproyecto.getItemCount() >0){
                                  if(calfecha.getDate()!=null){
                                      if(!txtproposito.getText().isEmpty()){
                              if(!txtimportacia.getText().isEmpty()){
                                        
                                                  
                                           modificar();
                                             mostrar();
                                             Limpiar();
                                              }else{
                                                         JOptionPane.showMessageDialog(null, "INGRESE LA IMPORTANCIA DEL CAMBIO");
                                                         txtimportacia.requestFocus();
                                                     }
                                                         
                                         }else{
                                                  JOptionPane.showMessageDialog(null, "INGRESE EL PROPOSITO DEL CAMBIO");
                                             txtproposito.requestFocus();
                                             }
                                     }else{
                                              JOptionPane.showMessageDialog(null, "INGRESE LA FECHA DEL CAMBIO");
                                              calfecha.requestFocus();
                                         }
                                 }else{
                                          JOptionPane.showMessageDialog(null, "REGISTRE PRIMERO UN PROYECTO");
                    }
                                    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
  if(e.getSource()==btngrabar){
           ValidacionInsertar();
        }
        if(e.getSource()==btnmodificar){
            ValidacionModificar();
        }
        if(e.getSource()==btneliminar){
            eliminar();
            mostrar();
            Limpiar();
            
        }
        if(e.getSource()==btnalctualizar){
             mostrar();
             Limpiar();
            
        }    }

    @Override
    public void mouseClicked(MouseEvent e) {
   if(e.getSource()==tabla){
             
            Seleccionar();

            
        }    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
   

  
    
    
}

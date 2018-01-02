/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BEAN.ProyectoBean;
import BEAN.ReunionesBean;
import DAO.ProyectoDAO;
import DAO.ReunionesDAO;
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
public class FrmReuniones extends  JFrame implements ActionListener,MouseListener{
    
       private TextField txtnumero,txtasis,txtacuerdo,txtduracion;
    private JComboBox cboproy;
     private  JDateChooser   calfecha,calfechaprox;
    private  JButton btneliminar,btngrabar,btnmodificar,btnalctualizar;
    private JTable tabla;
    private JScrollPane Panel;
    private JLabel lblcodigo,lbltitulo,lblduracion,lbldescripcion,lblfases,lblpresupuesto,lblcoordinador,lbltipo,lblinicio,lblfin;
    DefaultTableModel tablemodel;
    ArrayList<ProyectoBean> listaproy;
    ArrayList<ReunionesBean> listareu;
    ReunionesDAO objreudao;
    ReunionesBean objreubean;
    ProyectoBean objproybean;
    ProyectoDAO objproydao;
    
    
    public FrmReuniones(){
        GUI();
        
        objproybean=new ProyectoBean();
        objproydao=new ProyectoDAO();
        objreudao=new ReunionesDAO();
        objreubean=new ReunionesBean();
        mostrar();
       combo1();
    
}
    
    public static void main(String []args){
        FrmReuniones principal=new FrmReuniones();
        principal.setVisible(true);
        principal.setLocationRelativeTo(null);
        
    }
    
    
    
    public void GUI(){
         String columnas[]={"NUMERO","PROYECTO","PERSONAL ASISTENTE","FECHA","ACUERDOS","PROX. REUNION","DURACION"};
         try {
             setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
             getContentPane().setLayout(null);
                   getContentPane().setBackground(new Color(0, 153, 153));
        
             txtnumero=new TextField();
             getContentPane().add(txtnumero);
             txtnumero.setBounds(190, 84, 157, 23);
             
             txtasis=new TextField();
             getContentPane().add(txtasis);
             txtasis.setBounds(190,124,157,23);
             
            cboproy=new JComboBox();
             getContentPane().add(cboproy);
             cboproy.setBounds(190,164,157,23);
             
             
             
             
             
             calfecha=new JDateChooser();
             getContentPane().add(calfecha);
              calfecha.setLocale(new Locale("es"));
              calfecha.setDateFormatString("yyyy-MM-dd");
              //dato por defecto
              SimpleDateFormat parser1 = new SimpleDateFormat("yyyy-MM-dd");
              Date date1 = parser1.parse(" 2017-01-01");
            calfecha.setDate(date1);
             calfecha.setBounds(190,204,157,23);
             
            txtacuerdo=new TextField();
             getContentPane().add(txtacuerdo);
             txtacuerdo.setBounds(570,84,157,23);
             

             

             
             calfechaprox=new JDateChooser();
             getContentPane().add(calfechaprox);
             calfechaprox.setLocale(new Locale("es"));
              calfechaprox.setDateFormatString("yyyy-MM-dd");
              //dato por defecto
              SimpleDateFormat parser2 = new SimpleDateFormat("yyyy-MM-dd");
            Date date2 = parser2.parse("2017-01-01");
            calfechaprox.setDate(date2);
             calfechaprox.setBounds(570,124,157,23);
             
             txtduracion=new TextField();
             getContentPane().add(txtduracion);
             txtduracion.setBounds(570,164,157,23);
             
            
             btngrabar=new JButton();
             getContentPane().add(btngrabar);
             btngrabar.setText("GRABAR");
             btngrabar.setBounds(30,364,157,23);
             btngrabar.addActionListener(this);
             btngrabar.setBackground(new Color(0,102,153));

             
                 btnmodificar=new JButton();
             getContentPane().add(btnmodificar);
             btnmodificar.setText("MODIFICAR");
             btnmodificar.setBounds(210,364,157,23);
             btnmodificar.addActionListener(this);
             btnmodificar.setBackground(new Color(0,102,153));

             
                 btneliminar=new JButton();
             getContentPane().add(btneliminar);
             btneliminar.setText("ELIMINAR");
             btneliminar.setBounds(380,364,157,23);
             btneliminar.addActionListener(this);
             btneliminar.setBackground(new Color(0,102,153));
             
             btnalctualizar=new JButton();
             getContentPane().add(btnalctualizar);
             btnalctualizar.setText("ACTULIZAR");
             btnalctualizar.setBounds(550,364,157,23);
             btnalctualizar.addActionListener(this);
             btnalctualizar.setBackground(new Color(0,102,153));
             
             lbltitulo=new JLabel();
             getContentPane().add(lbltitulo);
             lbltitulo.setText("RELACION REUNIONES");
             lbltitulo.setBounds(320,20,200,30);
             
              Panel=new JScrollPane();
            getContentPane().add(Panel);
            Panel.setBounds(35,450,700,300);
            
            tablemodel=new DefaultTableModel(null,columnas);
            tabla=new JTable();
            Panel.setViewportView(tabla);
            tabla.addMouseListener(this);
            tabla.setModel(tablemodel);
             
            this.setSize(780,800);
         } catch (Exception e) {
             e.printStackTrace();
         }
         
         
         
     }
     
     public void mostrar(){
         
          int codigo=objreudao.generarCodigo();
        if(codigo==0){
            codigo=1;
        }else{
            codigo=objreudao.generarCodigo();
        }
        txtnumero.setText(String.valueOf(codigo));
        txtnumero.setEnabled(false);
        txtnumero.requestFocus();
        int i=0;
       listareu=objreudao.ListarReuniones1();
            tablemodel.setNumRows(listareu.size());
             for(ReunionesBean obj:listareu){
                 tablemodel.setValueAt(obj.getNUMERO(), i,0);
                tablemodel.setValueAt(obj.getNOMBPROY(),i,1);
                tablemodel.setValueAt(obj.getPERSONAL(), i,2);
              tablemodel.setValueAt(obj.getFECHA(),i,3);
                tablemodel.setValueAt(obj.getACUERDOS(),i,4);
                tablemodel.setValueAt(obj.getREUNION(),i,5);
                  tablemodel.setValueAt(obj.getDURACION(),i,6);

                i++;
            }
             tabla.setModel(tablemodel);
     }
public void combo1(){
     listaproy=objproydao.cargartablaproyecto();
        int a=0;
        for(ProyectoBean obj:listaproy){
            cboproy.addItem(new ProyectoBean(obj.getNumero(),obj.getTitulo()));

        }
       
    
}

      public void insertar(){

        objreubean.setNUMERO(Integer.parseInt( txtnumero.getText()));

        ProyectoBean objcbo=(ProyectoBean)cboproy.getSelectedItem();
          objreubean.setNUMPROY(objcbo.getNumero());
        objreubean.setPERSONAL(txtasis.getText());
         DateFormat fecha = new SimpleDateFormat("yyyy-MM-dd");
        String convertido = fecha.format(calfecha.getDate());
        String convertido2=fecha.format(calfechaprox.getDate());
        objreubean.setFECHA( convertido);
        objreubean.setACUERDOS(txtacuerdo.getText());
       objreubean.setREUNION(convertido2);
        objreubean.setDURACION(txtduracion.getText());
        int i=objreudao.InsertarReuniones(objreubean);
        if(i==0){
            JOptionPane.showMessageDialog(null, "Registro no insertado");
         
        }else{
            JOptionPane.showMessageDialog(null, "Registro Insertado Satisfactoriamente");
            
        }
         
     }
      
      
      public void modificar(){
        objreubean.setNUMERO(Integer.parseInt( txtnumero.getText()));

        ProyectoBean objcbo=(ProyectoBean)cboproy.getSelectedItem();
          objreubean.setNUMPROY(objcbo.getNumero());
        objreubean.setPERSONAL(txtasis.getText());
         DateFormat fecha = new SimpleDateFormat("yyyy-MM-dd");
        String convertido = fecha.format(calfecha.getDate());
        String convertido2=fecha.format(calfechaprox.getDate());
        objreubean.setFECHA( convertido);
        objreubean.setACUERDOS(txtacuerdo.getText());
       objreubean.setREUNION(convertido2);
        objreubean.setDURACION(txtduracion.getText());
        int i=objreudao.ModificarReuniones(objreubean);
        if(i==0){
            JOptionPane.showMessageDialog(null, "Registro no Modificado");
         
        }else{
            JOptionPane.showMessageDialog(null, "Registro Modificado Satisfactoriamente");
            
        }
         
     }
      
      public void eliminar(){
          String cod=txtnumero.getText();
          int codigo=Integer.parseInt(cod);
          objreubean.setNUMERO(codigo);
          int i=objreudao.EliminarReuniones(objreubean);
          if(i==0){
              JOptionPane.showMessageDialog(null, "Registro no eliminado");
              
          }else{
              JOptionPane.showMessageDialog(null, "Registro eliminado satisfactoriamente");
          }
      }
      
     public void Seleccionar(){
          
        try{
            
           txtnumero.setText(tabla.getValueAt(tabla.getSelectedRow(),0).toString());
            int cod=Integer.parseInt(txtnumero.getText());
         
           objreubean.setNUMERO(cod);
           objreubean=objreudao.CapturarCodigo(objreubean);
           txtnumero.setText(String.valueOf(objreubean.getNUMERO()));
           txtasis.setText(objreubean.getPERSONAL());
           
            cboproy.removeAllItems();
             cboproy.addItem(new ProyectoBean(objreubean.getNUMPROY(),objreubean.getNOMBPROY()));
          combo1();
          
          String fecha=(objreubean.getFECHA());
           String fechaprox=(objreubean.getREUNION());
           SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
           Date date1 = parser.parse(fecha);
            Date date2 = parser.parse(fechaprox);
          
          calfecha.setDate(date1);
            txtacuerdo.setText(objreubean.getACUERDOS());
            calfechaprox.setDate(date2);
            txtduracion.setText(objreubean.getDURACION());
        
           btngrabar.setEnabled(false);
           
        }catch(Exception e){
            
        }
        
    }
 
         public void Limpiar(){
       txtasis.setText("");
       //actualizar combobox
       cboproy.removeAllItems();
       combo1();
        txtacuerdo.setText("");
       txtduracion.setText("");
       calfecha.setDateFormatString("yyyy-MM-dd");
       calfechaprox.setDateFormatString("yyyy-MM-dd");
       txtasis.requestFocus();
       btngrabar.setEnabled(true);
        
        
    }
         
         
          public void ValidacionInsertar(){
                     if(!txtasis.getText().isEmpty()){
                                 if(cboproy.getItemCount() >0){
                                  if(calfecha.getDate()!=null){
                                      if(!txtacuerdo.getText().isEmpty()){
                       if(calfechaprox.getDate()!=null){
                              if(!txtduracion.getText().isEmpty()){
                                        
                                                  
                                           insertar();
                                             mostrar();
                                             Limpiar();
                                              }else{
                                                         JOptionPane.showMessageDialog(null, "INGRESE LA DURACION DE LA REUNION");
                                                         txtduracion.requestFocus();
                                                     }
                                                         
                                         }else{
                                                  JOptionPane.showMessageDialog(null, "INGRESE LA FECHA DE LA PROXIMA REUNION");
                                             calfechaprox.requestFocus();
                                             }
                                     }else{
                                              JOptionPane.showMessageDialog(null, "INGRESE EL ACUERDO DE LA REUNION");
                                              txtacuerdo.requestFocus();
                                         }
                                 }else{
                                          JOptionPane.showMessageDialog(null, "INGRESE LA FECHA DE LA REUNION");
                                    calfecha.requestFocus();
                    }
                                    }else{
                                                         JOptionPane.showMessageDialog(null, "REGISTRE PRIMERO UN PROYECTO");
                                                   
                                                     }
                                                         
                                         }else{
                                                  JOptionPane.showMessageDialog(null, "INGRESE LA CANTIDAD DE ASISTENTES");
                                             txtasis.requestFocus();
                                             }
                                     }
         public void ValidacionModificar(){
                     if(!txtasis.getText().isEmpty()){
                                 if(cboproy.getItemCount() >0){
                                  if(calfecha.getDate()!=null){
                                      if(!txtacuerdo.getText().isEmpty()){
                       if(calfechaprox.getDate()!=null){
                              if(!txtduracion.getText().isEmpty()){
                                        
                                                  
                                           modificar();
                                             mostrar();
                                             Limpiar();
                                              }else{
                                                         JOptionPane.showMessageDialog(null, "INGRESE LA DURACION DE LA REUNION");
                                                         txtduracion.requestFocus();
                                                     }
                                                         
                                         }else{
                                                  JOptionPane.showMessageDialog(null, "INGRESE LA FECHA DE LA PROXIMA REUNION");
                                             calfechaprox.requestFocus();
                                             }
                                     }else{
                                              JOptionPane.showMessageDialog(null, "INGRESE EL ACUERDO DE LA REUNION");
                                              txtacuerdo.requestFocus();
                                         }
                                 }else{
                                          JOptionPane.showMessageDialog(null, "INGRESE LA FECHA DE LA REUNION");
                                    calfecha.requestFocus();
                    }
                                    }else{
                                                         JOptionPane.showMessageDialog(null, "REGISTRE PRIMERO UN PROYECTO");
                                                   
                                                     }
                                                         
                                         }else{
                                                  JOptionPane.showMessageDialog(null, "INGRESE LA CANTIDAD DE ASISTENTES");
                                             txtasis.requestFocus();
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
            
        }


    }

    @Override
    public void mouseClicked(MouseEvent e) {

         if(e.getSource()==tabla){
             
            Seleccionar();

            
        }

    }

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

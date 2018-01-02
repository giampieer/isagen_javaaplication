/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BEAN.InteresadosBean;
import BEAN.ProyectoBean;
import DAO.InteresadosDAO;
import DAO.ProyectoDAO;
import java.awt.Color;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
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
public class FrmInteresados extends JFrame implements ActionListener,MouseListener{
    
     private TextField txtumero,txtnombre,txtimporte,txtnecesidad,txtinteres;
    private JComboBox cboproyecto;
    private  JButton btneliminar,btngrabar,btnmodificar,btnalctualizar;
    private JTable tabla;
    private JScrollPane Panel;
    private JLabel lblcodigo,lbltitulo,lblduracion,lbldescripcion,lblfases,lblpresupuesto,lblcoordinador,lbltipo,lblinicio,lblfin;
    DefaultTableModel tablemodel;
    ArrayList<InteresadosBean> listainteres;
    ArrayList<ProyectoBean> listaproy;
    ProyectoBean objproybean;
    ProyectoDAO objproydao;
    InteresadosBean objintbean;
    InteresadosDAO objintdao;

    public  FrmInteresados(){
        GUI();
        objproybean=new ProyectoBean();
        objproydao=new ProyectoDAO();
        objintbean=new InteresadosBean();
      objintdao=new InteresadosDAO();
        mostrar();
        combo1();
    }
    public static void main(String[]args){
        FrmInteresados principal=new FrmInteresados();
        principal.setVisible(true);
        principal.setLocationRelativeTo(null);
    }
    
    public void GUI(){
         String columnas[]={"NUMERO","PROYECTO ELEGIDO","NOMBRE","IMPORTE","NECESIDADES","INTERES"};
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
             

             
             txtnombre=new TextField();
             getContentPane().add(txtnombre);
             txtnombre.setBounds(240,164,157,23);
             
             
             
             
             txtimporte=new TextField();
             getContentPane().add(txtimporte);
             txtimporte.setBounds(240,204,157,23);
             
             txtnecesidad=new TextField();
             getContentPane().add(txtnecesidad);
             txtnecesidad.setBounds(240,244,157,23);
             
             
             txtinteres=new TextField();
             getContentPane().add(txtinteres);
             txtinteres.setBounds(240,284,157,23);

             
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
             lbltitulo.setText("RELACION INTERESADOS");
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
    
    
    public void mostrar(){
          int codigo=objintdao.generarCodigo();
        if(codigo==0){
            codigo=1;
        }else{
            codigo=objintdao.generarCodigo();
        }
        txtumero.setText(String.valueOf(codigo));
        txtumero.setEnabled(false);
        txtumero.requestFocus();
        int i=0;
       listainteres=objintdao.ListarInteresados1();
            tablemodel.setNumRows(listainteres.size());
             for(InteresadosBean obj:listainteres){
                 tablemodel.setValueAt(obj.getNUMERO(), i,0);
                tablemodel.setValueAt(obj.getNOMBPROY(),i,1);
                tablemodel.setValueAt(obj.getNOMBRE(), i,2);
              tablemodel.setValueAt(obj.getIMPORTE(),i,3);
                tablemodel.setValueAt(obj.getNECESIDADES(),i,4);
                tablemodel.setValueAt(obj.getINTERES(),i,5);
                i++;
            }
             tabla.setModel(tablemodel);
        
    }
    
    public void insertar(){
        objintbean.setNUMERO(Integer.parseInt(txtumero.getText()));
       
        ProyectoBean objcbo=(ProyectoBean)cboproyecto.getSelectedItem();  
        objintbean.setNUMPROY(objcbo.getNumero());
        objintbean.setNOMBRE(txtnombre.getText());
        objintbean.setIMPORTE(txtimporte.getText());
        objintbean.setNECESIDADES(txtnecesidad.getText());
        objintbean.setINTERES(txtinteres.getText());
        int i=objintdao.InsertarInteresado(objintbean);
        if(i==0){
            JOptionPane.showMessageDialog(null, "Registro no Insertado");
        }else{
            JOptionPane.showMessageDialog(null, "Registro Insertado Satisfactoriamente");
        }
        
    }
    public void eliminar(){
        String codigo=txtumero.getText();
        int cod=Integer.parseInt(codigo);
        objintbean.setNUMERO(cod);
        int i=objintdao.EliminarInteresados(objintbean);
        if(i==0){
            JOptionPane.showMessageDialog(null,"Registro no Eliminado");
        }else{
            JOptionPane.showMessageDialog(null, "Registro Eliminado Satisfactoriamente");
        }
    }
    public void  modificar(){
        objintbean.setNUMERO(Integer.parseInt(txtumero.getText()));
        ProyectoBean objcbo=(ProyectoBean)cboproyecto.getSelectedItem();  
        objintbean.setNUMPROY(objcbo.getNumero());
        objintbean.setNOMBRE(txtnombre.getText());
        objintbean.setIMPORTE(txtimporte.getText());
        objintbean.setNECESIDADES(txtnecesidad.getText());
        objintbean.setINTERES(txtinteres.getText());
        int i=objintdao.ModificarInteresados(objintbean);
        if(i==0){
            JOptionPane.showMessageDialog(null, "Registro no Modificado");
        }else{
            JOptionPane.showMessageDialog(null, "Registro Modificado Satisfactoriamente");
        }
    }
    public void Limpiar(){
        cboproyecto.removeAllItems();
        combo1();
        txtnombre.setText("");
        txtimporte.setText("");
        txtnecesidad.setText("");
        txtinteres.setText("");
        txtnombre.requestFocus();
        btngrabar.setEnabled(true);
    }
    public void combo1(){
          listaproy=objproydao.cargartablaproyecto();
        int a=0;
        for(ProyectoBean obj:listaproy){
            cboproyecto.addItem(new ProyectoBean(obj.getNumero(),obj.getTitulo()));

        }
       
    }
    public void Seleccionar(){
          try{
            
           txtumero.setText(tabla.getValueAt(tabla.getSelectedRow(),0).toString());
            int cod=Integer.parseInt(txtumero.getText());
           
           objintbean.setNUMERO(cod);
           objintbean=objintdao.CapturarCodigo(objintbean);
           txtumero.setText(String.valueOf(objintbean.getNUMERO()));
           txtnombre.setText(objintbean.getNOMBRE());
           
            cboproyecto.removeAllItems();
             cboproyecto.addItem(new ProyectoBean(objintbean.getNUMPROY(),objintbean.getNOMBPROY()));
          combo1();
          txtimporte.setText(objintbean.getIMPORTE());
            txtnecesidad.setText(objintbean.getINTERES());            
            txtinteres.setText(objintbean.getINTERES());
           btngrabar.setEnabled(false);
           
        }catch(Exception e){
            
        }
    }

     public void ValidacionInsertar(){
                    
                                 if(cboproyecto.getItemCount() >0){
                                     if(!txtnombre.getText().isEmpty()){
                                         if(!txtimporte.getText().isEmpty()){
                                             if(!txtnecesidad.getText().isEmpty()){
                                           if(!txtinteres.getText().isEmpty()){
                                                  
                                           insertar();
                                             mostrar();
                                             Limpiar();
                                              }else{
                                                         JOptionPane.showMessageDialog(null, "INGRESE EL INTERES DEL INTERESADO");
                                                         txtinteres.requestFocus();
                                                     }
                                                         
                                         }else{
                                                  JOptionPane.showMessageDialog(null, "INGRESE LA NECESIDAD DEL INTERESADO");
                                             txtnecesidad.requestFocus();
                                             }
                                     }else{
                                              JOptionPane.showMessageDialog(null, "INGRESE EL IMPORTE DEL INTERESADO");
                                              txtimporte.requestFocus();
                                         }
                                 }else{
                                          JOptionPane.showMessageDialog(null, "INGRESE EL NOMBRE DEL INTERESADO");
                                    txtnombre.requestFocus();
                    }
                                    }else{
                                                         JOptionPane.showMessageDialog(null, "REGISTRE PRIMERO UN PROYECTO");
                                                   
                                                     }
                                                         
                                         }
     
          public void ValidacionModificar(){
                    
                                 if(cboproyecto.getItemCount() >0){
                                     if(!txtnombre.getText().isEmpty()){
                                         if(!txtimporte.getText().isEmpty()){
                                             if(!txtnecesidad.getText().isEmpty()){
                                           if(!txtinteres.getText().isEmpty()){
                                                  
                                           modificar();
                                             mostrar();
                                             Limpiar();
                                              }else{
                                                         JOptionPane.showMessageDialog(null, "INGRESE EL INTERES DEL INTERESADO");
                                                         txtinteres.requestFocus();
                                                     }
                                                         
                                         }else{
                                                  JOptionPane.showMessageDialog(null, "INGRESE LA NECESIDAD DEL INTERESADO");
                                             txtnecesidad.requestFocus();
                                             }
                                     }else{
                                              JOptionPane.showMessageDialog(null, "INGRESE EL IMPORTE DEL INTERESADO");
                                              txtimporte.requestFocus();
                                         }
                                 }else{
                                          JOptionPane.showMessageDialog(null, "INGRESE EL NOMBRE DEL INTERESADO");
                                    txtnombre.requestFocus();
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
    
    
    


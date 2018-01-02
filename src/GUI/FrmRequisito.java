/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BEAN.ProyectoBean;
import BEAN.RequisitoBean;
import DAO.ProyectoDAO;
import DAO.RequisitoDAO;
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
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Home
 */
public class FrmRequisito extends JFrame implements ActionListener,MouseListener{
    
     private TextField txtumero,txtalcance,txtdescripcion,txtcantper,txtcantreu;
    private JComboBox cboproyecto;
    private  JButton btneliminar,btngrabar,btnmodificar,btnalctualizar;
    private JTable tabla;
    private JScrollPane Panel;
    private JLabel lblcodigo,lbltitulo,lblduracion,lbldescripcion,lblfases,lblpresupuesto,lblcoordinador,lbltipo,lblinicio,lblfin;
    DefaultTableModel tablemodel;
    ArrayList<RequisitoBean> listarequi;
    ArrayList<ProyectoBean> listaproy;
    ProyectoBean objproybean;
    ProyectoDAO objproydao;
    RequisitoBean objrequibean;
    RequisitoDAO objrequidao;

    public  FrmRequisito(){
        GUI();
        objproybean=new ProyectoBean();
        objproydao=new ProyectoDAO();
        objrequibean=new RequisitoBean();
      objrequidao=new RequisitoDAO();
        mostrar();
        combo1();
    }
    public static void main(String[]args){
        FrmRequisito principal=new FrmRequisito();
        principal.setVisible(true);
        principal.setLocationRelativeTo(null);
    }
    
    public void GUI(){
         String columnas[]={"NUMERO","PROYECTO ELEGIDO","ALCANCE","PERSONAL","REUNION","DESCRIPCION"};
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
             

             
             txtalcance=new TextField();
             getContentPane().add(txtalcance);
             txtalcance.setBounds(240,164,157,23);
             
             
             
             
             txtdescripcion=new TextField();
             getContentPane().add(txtdescripcion);
             txtdescripcion.setBounds(240,204,157,23);
             
             txtcantper=new TextField();
             getContentPane().add(txtcantper);
             txtcantper.setBounds(240,244,157,23);
             
             
             txtcantreu=new TextField();
             getContentPane().add(txtcantreu);
             txtcantreu.setBounds(240,284,157,23);

             
             btngrabar=new JButton();
             getContentPane().add(btngrabar);
             btngrabar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/grabar.png")));
             btngrabar.setText("GRABAR");
             btngrabar.setBounds(60,344,140,40);
             btngrabar.addActionListener(this);
             btngrabar.setBackground(new Color(0,102,153));
             
                 btnmodificar=new JButton();
             getContentPane().add(btnmodificar);
             btnmodificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/modificar.png")));
             btnmodificar.setText("MODIFICAR");
             btnmodificar.setBounds(270,344,140,40);
             btnmodificar.addActionListener(this);
             btnmodificar.setBackground(new Color(0,102,153));
             btnmodificar.setEnabled(false);
             
                 btneliminar=new JButton();
             getContentPane().add(btneliminar);
             btneliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/eliminar.png")));
             btneliminar.setText("ELIMINAR");
             btneliminar.setBounds(60,400,140,40);
             btneliminar.addActionListener(this);
             btneliminar.setBackground(new Color(0,102,153));
             
             btnalctualizar=new JButton();
             getContentPane().add(btnalctualizar);
             btnalctualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/actualizar.png")));
             btnalctualizar.setText("ACTULIAZAR");
             btnalctualizar.setBounds(270,400,140,40);
             btnalctualizar.addActionListener(this);
             btnalctualizar.setBackground(new Color(0,102,153));
             
             lbltitulo=new JLabel();
             getContentPane().add(lbltitulo);
             lbltitulo.setText("RELACION REQUISITOS");
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
          int codigo=objrequidao.generarCodigo();
        if(codigo==0){
            codigo=1;
        }else{
            codigo=objrequidao.generarCodigo();
        }
        txtumero.setText(String.valueOf(codigo));
        txtumero.setEnabled(false);
        txtumero.requestFocus();
        int i=0;
       listarequi=objrequidao.cargartablarequisito();
            tablemodel.setNumRows(listarequi.size());
             for(RequisitoBean obj:listarequi){
                 tablemodel.setValueAt(obj.getNumero(), i,0);
                tablemodel.setValueAt(obj.getNOMBPROY(),i,1);
                tablemodel.setValueAt(obj.getAlcance(), i,2);
              tablemodel.setValueAt(obj.getPersonal(),i,3);
                tablemodel.setValueAt(obj.getReunion(),i,4);
                tablemodel.setValueAt(obj.getDescripcion(),i,5);
                i++;
            }
             tabla.setModel(tablemodel);
        
    }
    
    public void insertar(){
        objrequibean.setNumero(Integer.parseInt(txtumero.getText()));
       
        ProyectoBean objcbo=(ProyectoBean)cboproyecto.getSelectedItem();  
        objrequibean.setNUMPROY(objcbo.getNumero());
        objrequibean.setAlcance(txtalcance.getText());
        objrequibean.setDescripcion(txtdescripcion.getText());
        objrequibean.setPersonal(Integer.parseInt(txtcantper.getText()));
        objrequibean.setReunion(Integer.parseInt(txtcantreu.getText()));
        int i=objrequidao.grabarRequisito(objrequibean);
        if(i==0){
            JOptionPane.showMessageDialog(null, "Registro no Insertado");
        }else{
            JOptionPane.showMessageDialog(null, "Registro Insertado Satisfactoriamente");
        }
        
    }
    public void eliminar(){
        String codigo=txtumero.getText();
        int cod=Integer.parseInt(codigo);
        objrequibean.setNumero(cod);
        int i=objrequidao.eliminarRrequisito(objrequibean);
        if(i==0){
            JOptionPane.showMessageDialog(null,"Registro no Eliminado");
        }else{
            JOptionPane.showMessageDialog(null, "Registro Eliminado Satisfactoriamente");
        }
    }
    public void  modificar(){
         objrequibean.setNumero(Integer.parseInt(txtumero.getText()));
       
        ProyectoBean objcbo=(ProyectoBean)cboproyecto.getSelectedItem();
          objrequibean.setNUMPROY(objcbo.getNumero());
        objrequibean.setAlcance(txtalcance.getText());
        objrequibean.setDescripcion(txtdescripcion.getText());
        objrequibean.setPersonal(Integer.parseInt(txtcantper.getText()));
        objrequibean.setReunion(Integer.parseInt(txtcantreu.getText()));
        int i=objrequidao.modificarRequisito(objrequibean);
        if(i==0){
            JOptionPane.showMessageDialog(null, "Registro no Modificado");
        }else{
            JOptionPane.showMessageDialog(null, "Registro Modificado Satisfactoriamente");
        }
    }
    public void Limpiar(){
        cboproyecto.removeAllItems();
        combo1();
        txtalcance.setText("");
        txtdescripcion.setText("");
        txtcantper.setText("");
        txtcantreu.setText("");
        txtalcance.requestFocus();
        btngrabar.setEnabled(true);
        btnmodificar.setEnabled(false);
    }
    public void combo1(){
          listaproy=objproydao.ProyectodeRequisito();
        int a=0;
        for(ProyectoBean obj:listaproy){
            cboproyecto.addItem(new ProyectoBean(obj.getNumero(),obj.getTitulo()));

        }
           if(cboproyecto.getItemCount()<=0){
          cboproyecto.addItem("no hay coordinador");
        }
       
    }
    public void Seleccionar(){
          try{
            
           txtumero.setText(tabla.getValueAt(tabla.getSelectedRow(),0).toString());
            int cod=Integer.parseInt(txtumero.getText());
           
           objrequibean.setNumero(cod);
           objrequibean=objrequidao.CapturarRequisito(objrequibean);
           txtumero.setText(String.valueOf(objrequibean.getNumero()));
           txtalcance.setText(objrequibean.getAlcance());
           
             cboproyecto.removeAllItems();
             cboproyecto.addItem(new ProyectoBean(objrequibean.getNUMPROY(),objrequibean.getNOMBPROY()));
          combo1();
          txtcantper.setText(String.valueOf(objrequibean.getPersonal()));
            txtdescripcion.setText(objrequibean.getDescripcion());            
            txtcantreu.setText(String.valueOf(objrequibean.getReunion()));
           btngrabar.setEnabled(false);
           btnmodificar.setEnabled(true);
           
        }catch(Exception e){
            
        }
    }
    
    
    public void ValidacionInsertar(){
             if(!txtalcance.getText().isEmpty()){
                    if(cboproyecto.getItemCount() >0){
                     if(!txtdescripcion.getText().isEmpty()){
                         if(!txtcantper.getText().isEmpty()){
                                             if(!txtcantreu.getText().isEmpty()){
                                                  
                                           insertar();
                                             mostrar();
                                             Limpiar();
                                              }else{
                                                         JOptionPane.showMessageDialog(null, "INGRESE LA CANTIDAD DE REUNIONES DEL PROYECTO ");
                                                         txtcantreu.requestFocus();
                                                     }
                                                         
                                         }else{
                                                  JOptionPane.showMessageDialog(null, "INGRESE LA CANTIDAD DE PERSONAS ENCARGADOS DEL PROYECTO");
                                             txtcantper.requestFocus();
                                             }
                                     }else{
                                              JOptionPane.showMessageDialog(null, "INGRESE LA DESCRIPCION DEL REQUISITO");

                                  txtdescripcion.requestFocus();
                                         }
                                 }else{
                                          JOptionPane.showMessageDialog(null, "REGISTRE PRIMERO UN PROYECTO ");
                                    
                    }
                             }else{
                                      JOptionPane.showMessageDialog(null, "INGRESE EL ALCANCE DEL REQUISITO");
                                 txtalcance.requestFocus();
                                 }
                             
                         }
             
         
        public void ValidacionModificar(){
             if(!txtalcance.getText().isEmpty()){
                    if(cboproyecto.getItemCount() >0){
                     if(!txtdescripcion.getText().isEmpty()){
                         if(!txtcantper.getText().isEmpty()){
                                             if(!txtcantreu.getText().isEmpty()){
                                                  
                                           modificar();
                                             mostrar();
                                             Limpiar();
                                              }else{
                                                         JOptionPane.showMessageDialog(null, "INGRESE LA CANTIDAD DE REUNIONES DEL PROYECTO ");
                                                         txtcantreu.requestFocus();
                                                     }
                                                         
                                         }else{
                                                  JOptionPane.showMessageDialog(null, "INGRESE LA CANTIDAD DE PERSONAS ENCARGADOS DEL PROYECTO");
                                             txtcantper.requestFocus();
                                             }
                                     }else{
                                              JOptionPane.showMessageDialog(null, "INGRESE LA DESCRIPCION DEL REQUISITO");

                                  txtdescripcion.requestFocus();
                                         }
                                 }else{
                                          JOptionPane.showMessageDialog(null, "REGISTRE PRIMERO UN PROYECTO ");
                                    
                    }
                             }else{
                                      JOptionPane.showMessageDialog(null, "INGRESE EL ALCANCE DEL  REQUISITO ");
                                 txtalcance.requestFocus();
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

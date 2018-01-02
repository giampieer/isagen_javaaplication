/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BEAN.ActividadesBean;
import BEAN.ProyectoBean;
import DAO.ActividadesDAO;
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
public class FrmActividad extends JFrame implements ActionListener,MouseListener{
     private TextField txtumero,txtactividad,txtduracion,txtresponsable;
    private JComboBox cboproyecto;
    private  JButton btneliminar,btngrabar,btnmodificar,btnalctualizar;
    private JTable tabla;
    private JScrollPane Panel;
    private JLabel lblcodigo,lbltitulo,lblduracion,lbldescripcion,lblfases,lblpresupuesto,lblcoordinador,lbltipo,lblinicio,lblfin;
    DefaultTableModel tablemodel;
    ArrayList<ActividadesBean> listaactividad;
    ArrayList<ProyectoBean> listaproy;
    ProyectoBean objproybean;
    ProyectoDAO objproydao;
    ActividadesBean objactbean;
    ActividadesDAO objactdao;

    public  FrmActividad(){
        GUI();
        objproybean=new ProyectoBean();
        objproydao=new ProyectoDAO();
        objactbean=new ActividadesBean();
      objactdao=new ActividadesDAO();
        mostrar();
        combo1();
    }
    public static void main(String[]args){
        FrmActividad principal=new FrmActividad();
        principal.setVisible(true);
        principal.setLocationRelativeTo(null);
    }
    
    public void GUI(){
         String columnas[]={"NUMERO","PROYECTO ELEGIDO","ACTIVIDAD","DURACION","RESPONSABLE"};
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
             

             
             txtactividad=new TextField();
             getContentPane().add(txtactividad);
             txtactividad.setBounds(240,164,157,23);
             
             
             
             
             txtduracion=new TextField();
             getContentPane().add(txtduracion);
             txtduracion.setBounds(240,204,157,23);
             
             txtresponsable=new TextField();
             getContentPane().add(txtresponsable);
             txtresponsable.setBounds(240,244,157,23);
             
             

             
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
             lbltitulo.setText("RELACION ACTIVIDADES");
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
          int codigo=objactdao.generarCodigo();
        if(codigo==0){
            codigo=1;
        }else{
            codigo=objactdao.generarCodigo();
        }
        txtumero.setText(String.valueOf(codigo));
        txtumero.setEnabled(false);
        txtactividad.requestFocus();
        int i=0;
       listaactividad=objactdao.cargartablaactividades1();
            tablemodel.setNumRows(listaactividad.size());
             for(ActividadesBean obj:listaactividad){
                 tablemodel.setValueAt(obj.getNumero(), i,0);
                tablemodel.setValueAt(obj.getNOMBPROY(),i,1);
                tablemodel.setValueAt(obj.getActividad(), i,2);
              tablemodel.setValueAt(obj.getDuracion(),i,3);
                tablemodel.setValueAt(obj.getResponsable(),i,4);
                i++;
            }
             tabla.setModel(tablemodel);
        
    }
    
    public void insertar(){
        objactbean.setNumero(Integer.parseInt(txtumero.getText()));
       
        ProyectoBean objcbo=(ProyectoBean)cboproyecto.getSelectedItem();  
        objactbean.setNUMPROY(objcbo.getNumero());
        objactbean.setActividad(txtactividad.getText());
        objactbean.setDuracion(txtduracion.getText());
        objactbean.setResponsable(txtresponsable.getText());
        int i=objactdao.grabaractividades(objactbean);
        if(i==0){
            JOptionPane.showMessageDialog(null, "Registro no Insertado");
        }else{
            JOptionPane.showMessageDialog(null, "Registro Insertado Satisfactoriamente");
        }
        
    }
    public void eliminar(){
        String codigo=txtumero.getText();
        int cod=Integer.parseInt(codigo);
        objactbean.setNumero(cod);
        int i=objactdao.eliminaractividades(objactbean);
        if(i==0){
            JOptionPane.showMessageDialog(null,"Registro no Eliminado");
        }else{
            JOptionPane.showMessageDialog(null, "Registro Eliminado Satisfactoriamente");
        }
    }
    public void  modificar(){
        objactbean.setNumero(Integer.parseInt(txtumero.getText()));
       
        ProyectoBean objcbo=(ProyectoBean)cboproyecto.getSelectedItem();  
        objactbean.setNUMPROY(objcbo.getNumero());
        objactbean.setActividad(txtactividad.getText());
        objactbean.setDuracion(txtduracion.getText());
        objactbean.setResponsable(txtresponsable.getText());
        int i=objactdao.modificaractividades(objactbean);
        if(i==0){
            JOptionPane.showMessageDialog(null, "Registro no Modificado");
        }else{
            JOptionPane.showMessageDialog(null, "Registro Modificado Satisfactoriamente");
        }
    }
    public void Limpiar(){
        cboproyecto.removeAllItems();
        combo1();
        txtactividad.setText("");
        txtresponsable.setText("");
        txtduracion.setText("");
        txtactividad.requestFocus();
        btngrabar.setEnabled(true);
    }
    public void combo1(){
          listaproy=objproydao.cargartablaproyecto();
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
           
           objactbean.setNumero(cod);
           objactbean=objactdao.CapturarActividades(objactbean);
           txtumero.setText(String.valueOf(objactbean.getNumero()));
           txtactividad.setText(objactbean.getActividad());
           
            cboproyecto.removeAllItems();
             cboproyecto.addItem(new ProyectoBean(objactbean.getNUMPROY(),objactbean.getNOMBPROY()));
          combo1();
           
          cboproyecto.setSelectedItem(objactbean.getNOMBPROY());
          txtduracion.setText(objactbean.getDuracion());
            txtresponsable.setText(objactbean.getResponsable());            
           btngrabar.setEnabled(false);
           
        }catch(Exception e){
            
        }
    }
      public void ValidacionInsertar(){
                    
                                 if(cboproyecto.getItemCount() >0){
                                     if(!txtactividad.getText().isEmpty()){
                                         if(!txtduracion.getText().isEmpty()){
                                             if(!txtresponsable.getText().isEmpty()){
                                                  
                                           insertar();
                                             mostrar();
                                             Limpiar();
                                              }else{
                                                         JOptionPane.showMessageDialog(null, "INGRESE EL RESPONSABLE DE LA ACTIVIDAD");
                                                         txtresponsable.requestFocus();
                                                     }
                                                         
                                         }else{
                                                  JOptionPane.showMessageDialog(null, "INGRESE LA DURACION DE LA ACTIVIDAD");
                                             txtduracion.requestFocus();
                                             }
                                     }else{
                                              JOptionPane.showMessageDialog(null, "INGRESE LA ACTIVIDAD");
                                              txtactividad.requestFocus();
                                         }
                                 }else{
                                          JOptionPane.showMessageDialog(null, "REGISTRE PRIMERO UN PROYECTO");
                                    
                    }
                                    }
      
      public void ValidacionModificar(){
                    
                                 if(cboproyecto.getItemCount() >0){
                                     if(!txtactividad.getText().isEmpty()){
                                         if(!txtduracion.getText().isEmpty()){
                                             if(!txtresponsable.getText().isEmpty()){
                                                  
                                           modificar();
                                             mostrar();
                                             Limpiar();
                                              }else{
                                                         JOptionPane.showMessageDialog(null, "INGRESE EL RESPONSABLE DE LA ACTIVIDAD");
                                                         txtresponsable.requestFocus();
                                                     }
                                                         
                                         }else{
                                                  JOptionPane.showMessageDialog(null, "INGRESE LA DURACION DE LA ACTIVIDAD");
                                             txtduracion.requestFocus();
                                             }
                                     }else{
                                              JOptionPane.showMessageDialog(null, "INGRESE LA ACTIVIDAD");
                                              txtactividad.requestFocus();
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
           
        }      }

    @Override
    public void mouseClicked(MouseEvent e) {
if(e.getSource()==tabla){
            Seleccionar();
        }     }

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

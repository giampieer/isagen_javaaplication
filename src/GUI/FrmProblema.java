/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BEAN.ProblemaBean;
import BEAN.ProyectoBean;
import DAO.ProblemaDAO;
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
public class FrmProblema extends JFrame implements ActionListener,MouseListener{
    
     private TextField txtumero,txtproyelegido,txtdescripcion,txtperjudicados;
    private JComboBox cboproyecto,cbonivel;
    private  JButton btneliminar,btngrabar,btnmodificar,btnalctualizar;
    private JTable tabla;
    private JScrollPane Panel;
    private JLabel lblcodigo,lbltitulo,lblduracion,lbldescripcion,lblfases,lblpresupuesto,lblcoordinador,lbltipo,lblinicio,lblfin;
    DefaultTableModel tablemodel;
    
    ArrayList<ProyectoBean> listaproy;
    ProyectoBean objproybean;
    ProyectoDAO objproydao;
    ArrayList<ProblemaBean> listaprob;
    ProblemaBean objproblemabean;
    ProblemaDAO objproblemadao;
   
    public FrmProblema(){
        GUI();
        objproybean=new ProyectoBean();
        objproydao=new ProyectoDAO();
        objproblemabean=new ProblemaBean();
        objproblemadao=new ProblemaDAO();
        mostrar();
        combo1();
    }
    
    public static void main(String[] args) {
        FrmProblema principal=new FrmProblema();
        principal.setVisible(true);
        principal.setLocationRelativeTo(null);
                
                
                
     }
    
    public void GUI(){
         String columnas[]={"NUMERO","PROYECTO ELEGIDO","NIVEL","DESCRIPCION","PERJUDICADOS"};
         try {
             setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
             getContentPane().setLayout(null);
             getContentPane().setBackground(new Color(0, 153, 153));

        
             txtumero=new TextField();
             getContentPane().add(txtumero);
             txtumero.setBounds(240, 84, 157, 23);
             
             cbonivel=new JComboBox();
             getContentPane().add(cbonivel);
              cbonivel.addItem("----Seleccionar----");
             cbonivel.addItem("Leve");
             cbonivel.addItem("Grave");
             cbonivel.addItem("Muy Grave");
             cbonivel.setBounds(240,124,157,23);
             
             cboproyecto=new JComboBox();
             getContentPane().add(cboproyecto);
             cboproyecto.setBounds(240,164,157,23);
             
             txtdescripcion=new TextField();
             getContentPane().add(txtdescripcion);
             txtdescripcion.setBounds(240,204,157,23);
             
             
             txtperjudicados=new TextField();
             getContentPane().add(txtperjudicados);
             txtperjudicados.setBounds(240,244,157,23);
             
             

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
             lbltitulo.setText("RELACION PROBLEMAS");
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
        listaproy=objproydao.ProyectodeProblema();
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
           
           objproblemabean.setNumero(cod);
           objproblemabean=objproblemadao.CapturarProblema(objproblemabean);
           txtumero.setText(String.valueOf(objproblemabean.getNumero()));
          cbonivel.setSelectedItem(objproblemabean.getNivel());
          
           cboproyecto.removeAllItems();
             cboproyecto.addItem(new ProyectoBean(objproblemabean.getNUMPROY(),objproblemabean.getNOMBPROY()));
          combo1();
         
            txtdescripcion.setText(objproblemabean.getDescripcion());            
            txtperjudicados.setText(objproblemabean.getPerjudicado());
           btngrabar.setEnabled(false);
           btnmodificar.setEnabled(true);
           
        }catch(Exception e){
            
        }
        
    }
    public void mostrar(){
        int cod=objproblemadao.generarCodigo();
        if(cod==0){
            cod=1;
        }else{
            cod=objproblemadao.generarCodigo();
        }
        txtumero.setText(String.valueOf(cod));
        txtumero.setEnabled(false);
        txtdescripcion.requestFocus();
        
        int i=0;
        listaprob=objproblemadao.cargartablaproblema1();
        tablemodel.setNumRows(listaprob.size());
        for(ProblemaBean obj:listaprob){
            tablemodel.setValueAt(obj.getNumero(), i, 0);
            tablemodel.setValueAt(obj.getNOMBPROY(), i, 1);
            tablemodel.setValueAt(obj.getNivel(), i, 2);
            tablemodel.setValueAt(obj.getDescripcion(), i, 3);
            tablemodel.setValueAt(obj.getPerjudicado(), i, 4);
            i++;
        }
        tabla.setModel(tablemodel);
    }
    
    public void insertar(){
        objproblemabean.setNumero(Integer.parseInt(txtumero.getText()));
        ProyectoBean objcbo=(ProyectoBean)cboproyecto.getSelectedItem();  
        objproblemabean.setNUMPROY(objcbo.getNumero());
        objproblemabean.setNivel(cbonivel.getSelectedItem().toString());
        objproblemabean.setDescripcion(txtdescripcion.getText());
        objproblemabean.setPerjudicado(txtperjudicados.getText());
        int i=objproblemadao.grabarproblema(objproblemabean);
        if(i==0){
            JOptionPane.showMessageDialog(null, "Registro no Insertado");
        }else{
            JOptionPane.showMessageDialog(null, "Registro Insertado Satisfactoriamente");
        }



    }
    
    public void modificar(){
      
        objproblemabean.setNumero(Integer.parseInt(txtumero.getText()));
        ProyectoBean objcbo=(ProyectoBean)cboproyecto.getSelectedItem();  
        objproblemabean.setNUMPROY(objcbo.getNumero());
        objproblemabean.setNivel(cbonivel.getSelectedItem().toString());
        objproblemabean.setDescripcion(txtdescripcion.getText());
        objproblemabean.setPerjudicado(txtperjudicados.getText());
        int i=objproblemadao.modificarproblema(objproblemabean);
        if(i==0){
            JOptionPane.showMessageDialog(null, "Registro no Modificado");
        }else{
            JOptionPane.showMessageDialog(null, "Registro Modificado Satisfactoriamente");
        }
    }
    public void eliminar(){
        objproblemabean.setNumero(Integer.parseInt(txtumero.getText()));
        int i=objproblemadao.eliminarproblema(objproblemabean);
        if(i==0){
            JOptionPane.showMessageDialog(null, "Registro no Eliminado");
        }else{
            JOptionPane.showMessageDialog(null, "Registro Eliminado Satisfactoriamente");
        }
        
    }
    public void Limpiar(){
    txtdescripcion.setText("");
    txtperjudicados.setText("");
    cbonivel.setSelectedIndex(0);
    cboproyecto.removeAllItems();
    combo1();
    txtdescripcion.requestFocus();
    btngrabar.setEnabled(true);
    btnmodificar.setEnabled(false);
    }
    public void ValidacionInsertar(){
             if(cbonivel.getSelectedIndex()!=0){
                    if(cboproyecto.getItemCount() >0){
                     if(!txtdescripcion.getText().isEmpty()){
                         if(!txtperjudicados.getText().isEmpty()){
                                        
                                                  
                                           insertar();
                                             mostrar();
                                             Limpiar();
                                              }else{
                                                         JOptionPane.showMessageDialog(null, "INGRESE LOS PERJUDICADOS DEL PROBLEMA ");
                                                         txtperjudicados.requestFocus();
                                                     }
                                                         
                                         }else{
                                                  JOptionPane.showMessageDialog(null, "INGRESE LA DESCRIPCION DEL PROBLEMA");
                                             txtdescripcion.requestFocus();
                                             }
                                     }else{
                                              JOptionPane.showMessageDialog(null, "REGISTRE PRIMERO UN PROYECTO");

                                         }
                                 }else{
                                          JOptionPane.showMessageDialog(null, "SELECCIONE EL NIVEL DEL PROBLEMA");
                                    
                    }
                             }
   
    public void ValidacionModificar(){
             if(cbonivel.getSelectedIndex()!=0){
                    if(cboproyecto.getItemCount() >0){
                     if(!txtdescripcion.getText().isEmpty()){
                         if(!txtperjudicados.getText().isEmpty()){
                                        
                                                  
                                           modificar();
                                             mostrar();
                                             Limpiar();
                                              }else{
                                                         JOptionPane.showMessageDialog(null, "INGRESE LOS PERJUDICADOS DEL PROBLEMA ");
                                                         txtperjudicados.requestFocus();
                                                     }
                                                         
                                         }else{
                                                  JOptionPane.showMessageDialog(null, "INGRESE LA DESCRIPCION DEL PROBLEMA ");
                                             txtdescripcion.requestFocus();
                                             }
                                     }else{
                                              JOptionPane.showMessageDialog(null, "REGISTRE PRIMERO UN  PROYECTO");

                                         }
                                 }else{
                                          JOptionPane.showMessageDialog(null, "SELECCIONE EL NIVEL DEL PROBLEMA");
                                    
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

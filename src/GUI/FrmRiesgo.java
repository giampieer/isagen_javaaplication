/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BEAN.ProyectoBean;
import BEAN.RiesgosBean;
import DAO.ProyectoDAO;
import DAO.RiesgosDAO;
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
public class FrmRiesgo extends JFrame implements ActionListener,MouseListener{
    
    
     private TextField txtumero,txtdescripcion;
    private JComboBox cboproyecto,cbonivel;
    private  JButton btneliminar,btngrabar,btnmodificar,btnalctualizar;
    private JTable tabla;
    private JScrollPane Panel;
    private JLabel lblcodigo,lbltitulo,lblduracion,lbldescripcion,lblfases,lblpresupuesto,lblcoordinador,lbltipo,lblinicio,lblfin;
    DefaultTableModel tablemodel;
    
    ArrayList<ProyectoBean> listaproy;
    ProyectoBean objproybean;
    ProyectoDAO objproydao;
    ArrayList<RiesgosBean> listariesgo;
    RiesgosBean objriesgobean;
    RiesgosDAO objriesgosdao;
   
    public FrmRiesgo(){
        GUI();
        objproybean=new ProyectoBean();
        objproydao=new ProyectoDAO();
        objriesgobean=new RiesgosBean();
        objriesgosdao=new RiesgosDAO();
        mostrar();
        combo1();
    }
    
    public static void main(String[] args) {
        FrmRiesgo principal=new FrmRiesgo();
        principal.setVisible(true);
        principal.setLocationRelativeTo(null);
                
                
                
     }
    
    public void GUI(){
         String columnas[]={"NUMERO","PROYECTO ELEGIDO","NIVEL","DESCRIPCION"};
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
             
               cbonivel=new JComboBox();
             getContentPane().add(cbonivel);
             cbonivel.addItem("----Seleccionar----");
             cbonivel.addItem("Leve");
             cbonivel.addItem("Grave");
             cbonivel.addItem("Muy Grave");
             cbonivel.setBounds(240,164,157,23);
             
             txtdescripcion=new TextField();
             getContentPane().add(txtdescripcion);
             txtdescripcion.setBounds(240,204,157,23);
             
             
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
             lbltitulo.setText("RELACION RIESGO");
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
           
           objriesgobean.setNumero(cod);
           objriesgobean=objriesgosdao.CapturarRiesgos(objriesgobean);
           txtumero.setText(String.valueOf(objriesgobean.getNumero()));
           
            cboproyecto.removeAllItems();
             cboproyecto.addItem(new ProyectoBean(objriesgobean.getNUMPROY(),objriesgobean.getNOMBPROY()));
          combo1();
           
            cbonivel.setSelectedItem(objriesgobean.getNivel());            
            txtdescripcion.setText(objriesgobean.getDescripción());
            
          
            
           btngrabar.setEnabled(false);
           
        }catch(Exception e){
            
        }
        
    }
    public void mostrar(){
        int cod=objriesgosdao.generarCodigo();
        if(cod==0){
            cod=1;
        }else{
            cod=objriesgosdao.generarCodigo();
        }
        txtumero.setText(String.valueOf(cod));
        txtumero.setEnabled(false);
        txtdescripcion.requestFocus();
        
        int i=0;
        listariesgo=objriesgosdao.cargartablariesgos1();
        tablemodel.setNumRows(listariesgo.size());
        for(RiesgosBean obj:listariesgo){
            tablemodel.setValueAt(obj.getNumero(), i, 0);
            tablemodel.setValueAt(obj.getNOMBPROY(), i, 1);
            tablemodel.setValueAt(obj.getNivel(), i, 2);
            tablemodel.setValueAt(obj.getDescripción(), i, 3);
            i++;
        }
        tabla.setModel(tablemodel);
    }
    
    public void insertar(){
        objriesgobean.setNumero(Integer.parseInt(txtumero.getText()));
        ProyectoBean objcbo=(ProyectoBean)cboproyecto.getSelectedItem();  
        objriesgobean.setNUMPROY(objcbo.getNumero());

        objriesgobean.setNivel(cbonivel.getSelectedItem().toString());
        objriesgobean.setDescripción(txtdescripcion.getText());
        int i=objriesgosdao.grabarriesgos(objriesgobean);
        if(i==0){
            JOptionPane.showMessageDialog(null, "Registro no Insertado");
        }else{
            JOptionPane.showMessageDialog(null, "Registro Insertado Satisfactoriamente");
        }



    }
    
    public void modificar(){
      
        objriesgobean.setNumero(Integer.parseInt(txtumero.getText()));
        ProyectoBean objcbo=(ProyectoBean)cboproyecto.getSelectedItem();  
        objriesgobean.setNUMPROY(objcbo.getNumero());

        objriesgobean.setNivel(cbonivel.getSelectedItem().toString());
        objriesgobean.setDescripción(txtdescripcion.getText());
        int i=objriesgosdao.modificarriesgos(objriesgobean);
        if(i==0){
            JOptionPane.showMessageDialog(null, "Registro no Modificado");
        }else{
            JOptionPane.showMessageDialog(null, "Registro Modiificado Satisfactoriamente");
        }
    }
    public void eliminar(){
        objriesgobean.setNumero(Integer.parseInt(txtumero.getText()));
        int i=objriesgosdao.eliminarriesgos(objriesgobean);
        if(i==0){
            JOptionPane.showMessageDialog(null, "Registro no Eliminado");
        }else{
            JOptionPane.showMessageDialog(null, "Registro Eliminado Satisfactoriamente");
        }
        
    }
    public void Limpiar(){
    txtdescripcion.setText("");
    cboproyecto.removeAllItems();
    combo1();
    cbonivel.setSelectedIndex(0);
    btngrabar.setEnabled(true);
    }
    
    
    public void ValidacionInsertar(){
                    
                                 if(cboproyecto.getItemCount() >0){
                                      if(cbonivel.getSelectedIndex()!=0){
                                     if(!txtdescripcion.getText().isEmpty()){
                                         
                                                  
                                           insertar();
                                             mostrar();
                                             Limpiar();
                                              }else{
                                                         JOptionPane.showMessageDialog(null, "INGRESE LA DESCRIPCION DEL RIESGO");
                                                         txtdescripcion.requestFocus();
                                                     }
                                                         
                                         }else{
                                                  JOptionPane.showMessageDialog(null, "SELECCIONE EL NIVEL DEL RIESGO");
                                             }
                                     }else{
                                              JOptionPane.showMessageDialog(null, "INGRESE EL IMPORTE DEL INTERESADO");
                                            
                                         }
                                 }
    
        public void ValidacionModificar(){
                    
                                 if(cboproyecto.getItemCount() >0){
                                      if(cbonivel.getSelectedIndex()!=0){
                                     if(!txtdescripcion.getText().isEmpty()){
                                         
                                                  
                                           modificar();
                                             mostrar();
                                             Limpiar();
                                              }else{
                                                         JOptionPane.showMessageDialog(null, "INGRESE LA DESCRIPCION DEL RIESGO");
                                                         txtdescripcion.requestFocus();
                                                     }
                                                         
                                         }else{
                                                  JOptionPane.showMessageDialog(null, "SELECCIONE EL NIVEL DEL RIESGO");
                                             }
                                     }else{
                                              JOptionPane.showMessageDialog(null, "INGRESE EL IMPORTE DEL INTERESADO");
                                            
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

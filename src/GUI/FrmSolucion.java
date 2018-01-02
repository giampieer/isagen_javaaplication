/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BEAN.ProyectoBean;
import BEAN.RiesgosBean;
import BEAN.SolucionBean;
import DAO.ProyectoDAO;
import DAO.RiesgosDAO;
import DAO.SolucionDAO;
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
public class FrmSolucion extends JFrame implements ActionListener,MouseListener{
    
      private TextField txtumero,txtdescripcion;
    private JComboBox cboriesgo,cbonivel;
    private  JButton btneliminar,btngrabar,btnmodificar,btnalctualizar;
    private JTable tabla;
    private JScrollPane Panel;
    private JLabel lblcodigo,lbltitulo,lblduracion,lbldescripcion,lblfases,lblpresupuesto,lblcoordinador,lbltipo,lblinicio,lblfin;
    DefaultTableModel tablemodel;
    
    ArrayList<RiesgosBean> listariesgo;
    RiesgosBean objriesgobean;
    RiesgosDAO objriesgodao;
    ArrayList<SolucionBean> listasolucion;
    SolucionBean objsolbean;
    SolucionDAO objsoldao;
   
    public FrmSolucion(){
         
        GUI();
        objriesgobean=new RiesgosBean();
        objriesgodao=new RiesgosDAO();
        objsolbean=new SolucionBean();
        objsoldao=new SolucionDAO();
        mostrar();
        combo1();
    }
    
    public static void main(String[] args) {
        FrmSolucion principal=new FrmSolucion();
        
        principal.setVisible(true);
        principal.setLocationRelativeTo(null);
     }
    
    public void GUI(){
         String columnas[]={"NUMERO","PROYECTO ELEGIDO","RIESGO","NIVEL","DESCRIPCION DE LA SOLUCION"};
         try {
             setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
             getContentPane().setLayout(null);
             getContentPane().setBackground(new Color(0, 153, 153));
        
             txtumero=new TextField();
             getContentPane().add(txtumero);
             txtumero.setBounds(240, 84, 157, 23);
             
             
             
             cboriesgo=new JComboBox();
             getContentPane().add(cboriesgo);
             cboriesgo.setBounds(240,124,157,23);
             
               cbonivel=new JComboBox();
             getContentPane().add(cbonivel);
             cbonivel.addItem("----Seleccionar----");
             cbonivel.addItem("Facil");
             cbonivel.addItem("Intermedio");
             cbonivel.addItem("Complejo");
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
             lbltitulo.setText("RELACION SOLUCION");
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
        listariesgo=objriesgodao.cargartablariesgos1();
        for(RiesgosBean obj:listariesgo){
         cboriesgo.addItem(new  RiesgosBean(obj.getNumero(), obj.getDescripción()));
        }
         if(cboriesgo.getItemCount()<=0){
          cboriesgo.addItem("no hay riesgo");
        }
    }

    public void Seleccionar(){
         try{
            
           txtumero.setText(tabla.getValueAt(tabla.getSelectedRow(),0).toString());
            int cod=Integer.parseInt(txtumero.getText());
           
           objsolbean.setNumero(cod);
           objsolbean=objsoldao.CapturarSolucion(objsolbean);
           txtumero.setText(String.valueOf(objsolbean.getNumero()));
           
           
            cboriesgo.removeAllItems();
             cboriesgo.addItem(new RiesgosBean(objsolbean.getNUMRIESGO(),objsolbean.getNOMBRIESGO()));
          combo1();
           
          //cboriesgo.setSelectedItem(objsolbean.getNOMBRIESGO());
            cbonivel.setSelectedItem(objsolbean.getNivel());            
            txtdescripcion.setText(objsolbean.getDescripción());
            
          
            
           btngrabar.setEnabled(false);
           
        }catch(Exception e){
            
        }
        
    }
    public void mostrar(){
        int cod=objsoldao.generarCodigo();
        if(cod==0){
            cod=1;
        }else{
            cod=objsoldao.generarCodigo();
        }
        txtumero.setText(String.valueOf(cod));
        txtumero.setEnabled(false);
        txtdescripcion.requestFocus();
        
        int i=0;
        listasolucion=objsoldao.cargartablasolucion1();
        tablemodel.setNumRows(listasolucion.size());
        for(SolucionBean obj:listasolucion){
            tablemodel.setValueAt(obj.getNumero(), i, 0);
             tablemodel.setValueAt(obj.getNOMBPROY(), i, 1);
            tablemodel.setValueAt(obj.getNOMBRIESGO(), i, 2);
            tablemodel.setValueAt(obj.getNivel(), i, 3);
            tablemodel.setValueAt(obj.getDescripción(), i, 4);
            i++;
        }
        tabla.setModel(tablemodel);
    }
    
    public void insertar(){
        objsolbean.setNumero(Integer.parseInt(txtumero.getText()));
        RiesgosBean objcbo=(RiesgosBean)cboriesgo.getSelectedItem();  
        objsolbean.setNUMRIESGO(objcbo.getNumero());

        objsolbean.setNivel(cbonivel.getSelectedItem().toString());
        objsolbean.setDescripción(txtdescripcion.getText());
        int i=objsoldao.grabarsolucion(objsolbean);
        if(i==0){
            JOptionPane.showMessageDialog(null, "Registro no Insertado");
        }else{
            JOptionPane.showMessageDialog(null, "Registro Insertado Satisfactoriamente");
        }



    }
    
    public void modificar(){
      
      objsolbean.setNumero(Integer.parseInt(txtumero.getText()));
        RiesgosBean objcbo=(RiesgosBean)cboriesgo.getSelectedItem();  
        objsolbean.setNUMRIESGO(objcbo.getNumero());

        objsolbean.setNivel(cbonivel.getSelectedItem().toString());
        objsolbean.setDescripción(txtdescripcion.getText());
        int i=objsoldao.modificarsolucion(objsolbean);
        if(i==0){
            JOptionPane.showMessageDialog(null, "Registro no Modificado");
        }else{
            JOptionPane.showMessageDialog(null, "Registro Modiificado Satisfactoriamente");
        }
    }
    public void eliminar(){
        objsolbean.setNumero(Integer.parseInt(txtumero.getText()));
        int i=objsoldao.eliminarsolucion(objsolbean);
        if(i==0){
            JOptionPane.showMessageDialog(null, "Registro no Eliminado");
        }else{
            JOptionPane.showMessageDialog(null, "Registro Eliminado Satisfactoriamente");
        }
        
    }
    public void Limpiar(){
    txtdescripcion.setText("");
    cboriesgo.removeAllItems();
    combo1();
    cbonivel.setSelectedIndex(0);
    btngrabar.setEnabled(true);
    }
    
    
    
     public void ValidacionInsertar(){
                    
                                 if(cboriesgo.getItemCount() >0){
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
                                              JOptionPane.showMessageDialog(null, "REGISTRE PRIMERO UN RIESGO PARA LA SOLUCION");
                                            
                                         }
                                 }
     public void ValidacionModificar(){
                    
                                 if(cboriesgo.getItemCount() >0){
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
                                              JOptionPane.showMessageDialog(null, "REGISTRE PRIMERO UN RIESGO PARA LA SOLUCION");
                                            
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
            
        }       }

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

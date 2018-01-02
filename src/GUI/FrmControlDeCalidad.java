/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BEAN.ControldeCalidadBean;
import BEAN.ProyectoBean;
import DAO.ControldeCalidadDAO;
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
public class FrmControlDeCalidad extends JFrame implements ActionListener,MouseListener{
    
     private TextField txtumero,txtgestion,txtmejora,txtactualizaciones;
    private JComboBox cboproyecto,cbometricas;
    private  JButton btneliminar,btngrabar,btnmodificar,btnalctualizar;
    private JTable tabla;
    private JScrollPane Panel;
    private JLabel lblcodigo,lbltitulo,lblduracion,lbldescripcion,lblfases,lblpresupuesto,lblcoordinador,lbltipo,lblinicio,lblfin;
    DefaultTableModel tablemodel;
    
    ArrayList<ProyectoBean> listaproy;
    ProyectoBean objproybean;
    ProyectoDAO objproydao;
    ArrayList<ControldeCalidadBean> listacontrol;
    ControldeCalidadBean objcontrolbean;
    ControldeCalidadDAO objcontroldao;
   
    public FrmControlDeCalidad(){
        GUI();
        objproybean=new ProyectoBean();
        objproydao=new ProyectoDAO();
        objcontrolbean=new ControldeCalidadBean();
        objcontroldao=new ControldeCalidadDAO();
        mostrar();
        combo1();
    }
    
    public static void main(String[] args) {
        FrmControlDeCalidad principal=new FrmControlDeCalidad();
        principal.setVisible(true);
        principal.setLocationRelativeTo(null);
                
                
                
     }
    
    public void GUI(){
         String columnas[]={"NUMERO","PROYECTO ELEGIDO","PLAN DE GESTION","PLAN DE MEJORA","METRICAS DE CALIDAD","ACTUALIZACIONES"};
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
             
             txtgestion=new TextField();
             getContentPane().add(txtgestion);
             txtgestion.setBounds(240,164,157,23);
             
             
             txtmejora=new TextField();
             getContentPane().add(txtmejora);
             txtmejora.setBounds(240,204,157,23);
             
             cbometricas=new JComboBox();
             getContentPane().add(cbometricas);
             cbometricas.addItem("Ninguna metrica Realizada");
             cbometricas.addItem("Operacion - Revision - Transicion");
             cbometricas.addItem("Correccion - Mantenimiento - Facilidad de uso - Integridad");
              cbometricas.addItem("Numero de Errores - Defectos Encontrados");
             cbometricas.setBounds(240,244,157,23);
             
               txtactualizaciones=new TextField();
             getContentPane().add(txtactualizaciones);
             txtactualizaciones.setBounds(240,284,157,23);

             
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
             lbltitulo.setText("RELACION CONTROL DE CALIDAD");
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
           
           objcontrolbean.setNumero(cod);
           objcontrolbean=objcontroldao.CapturarControlCalidad(objcontrolbean);
           txtumero.setText(String.valueOf(objcontrolbean.getNumero()));
          cbometricas.setSelectedItem(objcontrolbean.getMetricasdecalidad());
          
          cboproyecto.removeAllItems();
             cboproyecto.addItem(new ProyectoBean(objcontrolbean.getNUMPROY(),objcontrolbean.getNOMBPROY()));
          combo1();

            txtgestion.setText(objcontrolbean.getPlandegestion());            
            txtmejora.setText(objcontrolbean.getPlandemejoradecalidad());
               txtactualizaciones.setText(objcontrolbean.getActualizacionesdeladocumentacion());
           btngrabar.setEnabled(false);
           
        }catch(Exception e){
            
        }
        
    }
    public void mostrar(){
        int cod=objcontroldao.generarCodigo();
        if(cod==0){
            cod=1;
        }else{
            cod=objcontroldao.generarCodigo();
        }
        txtumero.setText(String.valueOf(cod));
        txtumero.setEnabled(false);
        txtgestion.requestFocus();
        
        int i=0;
        listacontrol=objcontroldao.cargartablacontrolcalidad1();
        tablemodel.setNumRows(listacontrol.size());
        for(ControldeCalidadBean obj:listacontrol){
            tablemodel.setValueAt(obj.getNumero(), i, 0);
            tablemodel.setValueAt(obj.getNOMBPROY(), i, 1);
            tablemodel.setValueAt(obj.getPlandegestion(), i, 2);
            tablemodel.setValueAt(obj.getPlandemejoradecalidad(), i, 3);
            tablemodel.setValueAt(obj.getMetricasdecalidad(), i, 4);
            tablemodel.setValueAt(obj.getActualizacionesdeladocumentacion(), i, 5);
            i++;
        }
        tabla.setModel(tablemodel);
    }
    
    public void insertar(){
        objcontrolbean.setNumero(Integer.parseInt(txtumero.getText()));
        ProyectoBean objcbo=(ProyectoBean)cboproyecto.getSelectedItem();  
        objcontrolbean.setNUMPROY(objcbo.getNumero());
        objcontrolbean.setPlandegestion(txtgestion.getText());
        objcontrolbean.setPlandemejoradecalidad(txtmejora.getText());
        objcontrolbean.setActualizacionesdeladocumentacion(txtactualizaciones.getText());
                objcontrolbean.setMetricasdecalidad(cbometricas.getSelectedItem().toString());

        int i=objcontroldao.grabarcontrolcalidad(objcontrolbean);
        if(i==0){
            JOptionPane.showMessageDialog(null, "Registro no Insertado");
        }else{
            JOptionPane.showMessageDialog(null, "Registro Insertado Satisfactoriamente");
        }



    }
    
    public void modificar(){
      
        objcontrolbean.setNumero(Integer.parseInt(txtumero.getText()));
        ProyectoBean objcbo=(ProyectoBean)cboproyecto.getSelectedItem();  
        objcontrolbean.setNUMPROY(objcbo.getNumero());
        objcontrolbean.setPlandegestion(txtgestion.getText());
        objcontrolbean.setPlandemejoradecalidad(txtmejora.getText());
        objcontrolbean.setActualizacionesdeladocumentacion(txtactualizaciones.getText());
                objcontrolbean.setMetricasdecalidad(cbometricas.getSelectedItem().toString());

        int i=objcontroldao.modificarcontrolcalidad(objcontrolbean);
        if(i==0){
            JOptionPane.showMessageDialog(null, "Registro no Modificado");
        }else{
            JOptionPane.showMessageDialog(null, "Registro Modificado Satisfactoriamente");
        }
    }
    public void eliminar(){
        objcontrolbean.setNumero(Integer.parseInt(txtumero.getText()));
        int i=objcontroldao.eliminarcontrolcalidad(objcontrolbean);
        if(i==0){
            JOptionPane.showMessageDialog(null, "Registro no Eliminado");
        }else{
            JOptionPane.showMessageDialog(null, "Registro Eliminado Satisfactoriamente");
        }
        
    }
    public void Limpiar(){
    txtgestion.setText("");
    txtmejora.setText("");
    txtactualizaciones.setText("");
    cbometricas.setSelectedIndex(0);
    cboproyecto.removeAllItems();
    combo1();
    txtgestion.requestFocus();
    btngrabar.setEnabled(true);
    }

    
         public void ValidacionInsertar(){
                    
                                 if(cboproyecto.getItemCount() >0){
                                     
                                     if(!txtgestion.getText().isEmpty()){
                                          if(!txtmejora.getText().isEmpty()){
                                               if(cbometricas.getSelectedIndex()!=0){
                                               if(!txtactualizaciones.getText().isEmpty()){
                                                   insertar();
                                             mostrar();
                                             Limpiar();
                                               }else{
                                                    JOptionPane.showMessageDialog(null, "INGRESE LA ACTUALIZACION PARA EL CONTROL DE CALIDAD");
                                                         txtactualizaciones.requestFocus();
                                               }
                                          }else{
                                                    JOptionPane.showMessageDialog(null, "SELECCIONE LAS METRICAS DE CALIDAD");
                                               }
                                         
                                                  
                                          
                                              }else{
                                                         JOptionPane.showMessageDialog(null, "INGRESE LA MEJORA PARA EL CONTROL DE CALIDAD");
                                                         txtmejora.requestFocus();
                                                     }
                                                         
                                         }else{
                                                  JOptionPane.showMessageDialog(null, "INGRESE EL PLAN DE GESTION PARA EL CONTROL DE CALIDAD");
                                                  txtgestion.requestFocus();       
                                     }
                                     }else{
                                              JOptionPane.showMessageDialog(null, "REGISTRE PRIMERO UN PROYECTO PARA EL CONTROL DE CALIDAD");
                                            
                                         }
                                 }
         
         
                 public void ValidacionModificar(){
                    
                                 if(cboproyecto.getItemCount() >0){
                                     
                                     if(!txtgestion.getText().isEmpty()){
                                          if(!txtmejora.getText().isEmpty()){
                                               if(cbometricas.getSelectedIndex()!=0){
                                               if(!txtactualizaciones.getText().isEmpty()){
                                                   modificar();
                                             mostrar();
                                             Limpiar();
                                               }else{
                                                    JOptionPane.showMessageDialog(null, "INGRESE LA ACTUALIZACION PARA EL CONTROL DE CALIDAD");
                                                         txtactualizaciones.requestFocus();
                                               }
                                          }else{
                                                    JOptionPane.showMessageDialog(null, "SELECCIONE LAS METRICAS DE CALIDAD");
                                               }
                                         
                                                  
                                          
                                              }else{
                                                         JOptionPane.showMessageDialog(null, "INGRESE LA MEJORA PARA EL CONTROL DE CALIDAD");
                                                         txtmejora.requestFocus();
                                                     }
                                                         
                                         }else{
                                                  JOptionPane.showMessageDialog(null, "INGRESE EL PLAN DE GESTION PARA EL CONTROL DE CALIDAD");
                                                  txtgestion.requestFocus();       
                                     }
                                     }else{
                                              JOptionPane.showMessageDialog(null, "REGISTRE PRIMERO UN PROYECTO PARA EL CONTROL DE CALIDAD");
                                            
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
        }      }

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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BEAN.JefeBean;
import DAO.JefeDao;
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
import javax.swing.WindowConstants;

/**
 *
 * @author Home
 */
public class FrmActualizarDatosJefe extends JFrame implements ActionListener{
     private TextField txtcodigo,txtnombre,txtcorreo,txttelefono,txtid,txtcontraseña;
    private JComboBox cboarea;
    private  JButton btnsalir,btnmodificar;
    public static JLabel lblcodigo,lblnombre,lblcorre,lbltelefono,lblid,lblcontraseña,lbltitulo;

    ArrayList<JefeBean> listajefe;
    JefeBean objbean;
    JefeDao objdao;
    
    
    public  FrmActualizarDatosJefe(){
        GUI();
    
        objbean=new JefeBean();
        objdao=new JefeDao();
      Seleccionar();
        
    }
     
    public static void main(String []args){
        FrmActualizarDatosJefe principal=new FrmActualizarDatosJefe();
        principal.setVisible(true);
        principal.setLocationRelativeTo(null);
        
    }
     public void GUI(){
        
         
         try {
             setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
             getContentPane().setLayout(null);
              getContentPane().setBackground(new Color(0, 153, 153));
        
              //CREAMOS UN OBJETO DEL LOGIN QUE TRAES LOS DATOS 
              Login ventanalogin=new Login();
              
               lblcodigo=new JLabel();
            getContentPane().add(lblcodigo);
            lblcodigo.setText("hola "+ventanalogin.obj.getCODJEFE());
            lblcodigo.setBounds(40,84,50,14);
              
              
             txtcodigo=new TextField();
             getContentPane().add(txtcodigo);
            // txtcodigo.setBounds(190, 84, 157, 23);
             
             txtnombre=new TextField();
             getContentPane().add(txtnombre);
             txtnombre.setBounds(190,124,157,23);
             
             txtcorreo=new TextField();
             getContentPane().add(txtcorreo);
             txtcorreo.setBounds(190,164,157,23);
             
             txttelefono=new TextField();
             getContentPane().add(txttelefono);
             txttelefono.setBounds(190,204,157,23);
             
             cboarea=new JComboBox();
             getContentPane().add(cboarea);
             cboarea.addItem("----Seleccionar----");
              cboarea.addItem("Administracion");
               cboarea.addItem("Direccion");
                cboarea.addItem("Finanzas");
                 cboarea.addItem("Informatica");
             cboarea.setBounds(190,244,157,23);
             
             txtid=new TextField();
             getContentPane().add(txtid);
           //  txtid.setBounds(190,284,157,23);
             
             txtcontraseña=new TextField();
             getContentPane().add(txtcontraseña);
             //txtcontraseña.setBounds(190,324,157,23);
             
             
                 btnmodificar=new JButton();
             getContentPane().add(btnmodificar);
             btnmodificar.setText("MODIFICAR");
             btnmodificar.setBounds(40,300,140,23);
             btnmodificar.addActionListener(this);
             btnmodificar.setBackground(new Color(0,102,153));
 
             
             btnsalir=new JButton();
             getContentPane().add(btnsalir);
             btnsalir.setText("SALIR");
             btnsalir.setBounds(190,300,140,23);
             btnsalir.addActionListener(this);
             btnsalir.setBackground(new Color(0,102,153));
             
             lbltitulo=new JLabel();
             getContentPane().add(lbltitulo);
             lbltitulo.setText("ACTUALIZAR DATOS");
             lbltitulo.setBounds(130,20,200,30);
             
           
             
            this.setSize(400,400);
         } catch (Exception e) {
             e.printStackTrace();
         }
         
         
         
     }

public void modificarjefe(){
        String codjefe,nombjefe,emailjefe,teljefe,areajefe,idjefe,contrajefe;
        
        objbean=new JefeBean();
        objdao=new JefeDao();

        objbean.setCODJEFE(Integer.parseInt(txtcodigo.getText()));
        objbean.setNOMBJEFE(txtnombre.getText());
        objbean.setEMAJEFE(txtcorreo.getText());
        objbean.setTELFJEFE(txttelefono.getText());
        objbean.setAREAJEFE(cboarea.getSelectedItem().toString());
        objbean.setID(txtid.getText());
        objbean.setPASS(txtcontraseña.getText());
        int i=objdao.modificarjefe(objbean);
        if(i==0){
            JOptionPane.showMessageDialog(null, "Registro no Modificado");
          
            
        }else{
            JOptionPane.showMessageDialog(null, "Registro Modificado Satisfactoriamente");
            
        }
        
            
    }

     
     public void Seleccionar(){
         Login ventana=new Login();
        objbean.setCODJEFE(ventana.obj.getCODJEFE());
         objbean=objdao.CapturarCodigo(objbean);
        try{
            txtcodigo.setText(String.valueOf(objbean.getCODJEFE()));
            txtnombre.setText(objbean.getNOMBJEFE());
            txtcorreo.setText(objbean.getEMAJEFE());
            txttelefono.setText(objbean.getTELFJEFE());
            cboarea.setSelectedItem(objbean.getAREAJEFE());
            txtid.setText(objbean.getID());
            txtcontraseña.setText(objbean.getPASS());
           

        }catch(Exception e){
            
        }
    }
 

           
            public void ValidacionModificar(){
                     if(!txtnombre.getText().isEmpty()){
                     if(!txtcorreo.getText().isEmpty()){
                         if(!txttelefono.getText().isEmpty()){
                           if(cboarea.getSelectedIndex()!=0){
                              if(!txtid.getText().isEmpty()){
                                  if(!txtcontraseña.getText().isEmpty()){
                                  
                                           modificarjefe();
                                           Seleccionar();
                                              }else{
                                                         JOptionPane.showMessageDialog(null, "INGRESE EL PASSWORD  DEL JEFE");
                                                         txtcontraseña.requestFocus();
                                                     }
                                                         
                                         }else{
                                                  JOptionPane.showMessageDialog(null, "INGRESE EL ID DEL JEFE");
                                             txtid.requestFocus();
                                             }
                                          }else{
                                              JOptionPane.showMessageDialog(null, "SELECCIONE EL AREA DEL JEFE");
                                              
                                         }
                                       }else{
                                          JOptionPane.showMessageDialog(null, "INGRESE EL TELEFONO DEL JEFE");
                                    txttelefono.requestFocus();
                                          }
                                        }else{
                                                         JOptionPane.showMessageDialog(null, "INGRESE EL CORREO DEL JEFE ");
                                                         txtcorreo.requestFocus();
                                                     }
                                                         
                                         }else{
                                                  JOptionPane.showMessageDialog(null, "SELECCIONE EL NOMBRE DEL JEFE ");
                                             txtnombre.requestFocus();
                                             }
                                     }

    @Override
    public void actionPerformed(ActionEvent e) {
if(e.getSource()==btnmodificar){
ValidacionModificar();
}
if(e.getSource()==btnsalir){
   this.dispose();
}
    }

   
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BEAN.JefeBean;
import DAO.JefeDao;
import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import java.awt.Color;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.WindowConstants;
import modelo.Email;

/**
 *
 * @author Home
 */
public class FrmContraJefe extends JFrame implements ActionListener{
    
       private TextField txtcodigo,txtnombre,txtcorreo,txttelefono,txtid,txtcontraseña;
    private JComboBox cboarea;
    private JPasswordField txtcontranueva,txtcontraactual;
    private  JButton btnsalir,btnmodificar,btnenviar;
    public static JLabel lblcodigo,lblnombre,lblcorre,lbltelefono,lblid,lblcontraseña,lbltitulo;

    ArrayList<JefeBean> listajefe;
    JefeBean objbean;
    JefeDao objdao;
    
    
    public  FrmContraJefe(){
        GUI();
    
        objbean=new JefeBean();
        objdao=new JefeDao();
      Seleccionar();
        
    }
     
    public static void main(String []args){
        FrmContraJefe principal=new FrmContraJefe();
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
             //txtnombre.setBounds(190,124,157,23);
             
             txtcorreo=new TextField();
             getContentPane().add(txtcorreo);
             //txtcorreo.setBounds(190,164,157,23);
             
             txttelefono=new TextField();
             getContentPane().add(txttelefono);
             //txttelefono.setBounds(190,204,157,23);
             
             cboarea=new JComboBox();
             getContentPane().add(cboarea);
             cboarea.addItem("----Seleccionar----");
              cboarea.addItem("Administracion");
               cboarea.addItem("Direccion");
                cboarea.addItem("Finanzas");
                 cboarea.addItem("Informatica");
             //cboarea.setBounds(190,244,157,23);
             
             txtid=new TextField();
             getContentPane().add(txtid);
           //  txtid.setBounds(190,284,157,23);
             
             txtcontraseña=new TextField();
             getContentPane().add(txtcontraseña);
            // txtcontraseña.setBounds(190,324,157,23);
             
             txtcontraactual=new JPasswordField();
             getContentPane().add(txtcontraactual);
            txtcontraactual.setBounds(190, 84, 157, 23);
             
              txtcontranueva=new JPasswordField();
             getContentPane().add(txtcontranueva);
            txtcontranueva.setBounds(190,124,157,23);
             
                 btnmodificar=new JButton();
             getContentPane().add(btnmodificar);
             btnmodificar.setText("MODIFICAR");
             btnmodificar.setBounds(40,200,140,23);
             btnmodificar.addActionListener(this);
             btnmodificar.setBackground(new Color(0,102,153));
 
             
             btnsalir=new JButton();
             getContentPane().add(btnsalir);
             btnsalir.setText("SALIR");
             btnsalir.setBounds(190,200,140,23);
             btnsalir.addActionListener(this);
             btnsalir.setBackground(new Color(0,102,153));
             
             
             btnenviar=new JButton();
             getContentPane().add(btnenviar);
             btnenviar.setText("ENVIAR CORREO");
             btnenviar.setBounds(120,240,140,23);
             btnenviar.addActionListener(this);
             btnenviar.setBackground(new Color(0,102,153));
             
             lbltitulo=new JLabel();
             getContentPane().add(lbltitulo);
             lbltitulo.setText("ACTUALIZAR DATOS");
             lbltitulo.setBounds(130,20,200,30);
             
           
             
            this.setSize(400,340);
         } catch (Exception e) {
             e.printStackTrace();
         }
         
         
         
     }
     public void limpiar(){
         txtcontraactual.setText("");
         txtcontranueva.setText("");
         txtcontraactual.requestFocus();
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
        objbean.setPASS(txtcontranueva.getText());
        if(txtcontraactual.getText().equals(txtcontraseña.getText())){
            int i=objdao.modificarjefe(objbean);
            if(i==0){
                 JOptionPane.showMessageDialog(null, "Contraseña no Modificado");
                 limpiar();
            }else{
                 JOptionPane.showMessageDialog(null, "Contraseña Modificado Satisfactoriamente");       
            limpiar();
            }
        }  else{
            JOptionPane.showMessageDialog(null, "Contraseña Actual Incorrecta");
        }
    }

public void correo(){
    
    Email email = new Email();
            String contraseña=txtcontraseña.getText();
            String de ="isagen24@gmail.com";
            String clave = "isagen2424";
            String para = txtcorreo.getText();
            String mensaje = "RECORDAR QUE SU CONTRASEÑA DE ADMINISTRADOR ES :"+contraseña;
            String asunto = "SEGURIDAD ISAGEN";
             boolean resultado = email.enviarCorreo(de, clave, para, mensaje, asunto);
             String bol=String.valueOf(resultado);
              if(bol.equals(false)){
                         
                 JOptionPane.showMessageDialog(null,"CORREO ELECTRONICO NO ENVIADO");
                


            }else{
               JOptionPane.showMessageDialog(null,"CORREO ELECTRONICO ENVIADO SATISFACTORIAMENTE"); 
              
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
                                   if(!txtcontraactual.getText().isEmpty()){
                                  if(!txtcontranueva.getText().isEmpty()){
                                           modificarjefe();
                                           Seleccionar();
                                            }else{
                                                         JOptionPane.showMessageDialog(null, "INGRESE LA NUEVA CONTRASEÑA  DEL JEFE");
                                                         txtcontranueva.requestFocus();
                                                     }
                                                         
                                         }else{
                                                  JOptionPane.showMessageDialog(null, "INGRESE LA CONTRASEÑA ACTUAL DEL JEFE");
                                             txtcontraactual.requestFocus();
                                             }
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
    
    if(e.getSource()==btnenviar){
correo();

}
    }
    
}

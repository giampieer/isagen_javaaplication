
package GUI;

import BEAN.JefeBean;
import DAO.JefeDao;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import org.w3c.dom.events.MouseEvent;


public class FrmJefe extends JFrame implements ActionListener,MouseListener{
    private TextField txtcodigo,txtnombre,txtcorreo,txttelefono,txtid,txtcontraseña;
    private JComboBox cboarea;
    private  JButton btneliminar,btngrabar,btnmodificar,btnalctualizar;
    private JTable tabla;
    private JScrollPane Panel;
    public static JLabel lblcodigo,lblnombre,lblcorre,lbltelefono,lblid,lblcontraseña,lbltitulo;
    DefaultTableModel tablemodel;
    ArrayList<JefeBean> listajefe;
    JefeBean objbean;
    JefeDao objdao;
    public  FrmJefe(){
        GUI();
    
        objbean=new JefeBean();
        objdao=new JefeDao();
        mostrar();
        
    }
     
    public static void main(String []args){
        FrmJefe principal=new FrmJefe();
        principal.setVisible(true);
        principal.setLocationRelativeTo(null);
        
    }
     public void GUI(){
        
         String columnas[]={"CODIGO","NOMBRE","CORREO","TELEFONO","AREA","ID","PASSWORD"};
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
             txtcodigo.setBounds(190, 84, 157, 23);
             
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
             txtid.setBounds(190,284,157,23);
             
             txtcontraseña=new TextField();
             getContentPane().add(txtcontraseña);
             txtcontraseña.setBounds(190,324,157,23);
             
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
             btnalctualizar.setText("ACTUALIZAR");
             btnalctualizar.setBounds(550,364,157,23);
             btnalctualizar.addActionListener(this);
             btnalctualizar.setBackground(new Color(0,102,153));
             
             lbltitulo=new JLabel();
             getContentPane().add(lbltitulo);
             lbltitulo.setText("RELACION JEFES");
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
          int codigo=objdao.generarCodigo();
        if(codigo==0){
            codigo=1;
        }else{
            codigo=objdao.generarCodigo();
        }
        txtcodigo.setText(String.valueOf(codigo));
        txtcodigo.setEnabled(false);
        txtnombre.requestFocus();
        
         int i=0;
       listajefe=objdao.Listarjefe();
            tablemodel.setNumRows(listajefe.size());
             for(JefeBean obj:listajefe){
                 tablemodel.setValueAt(obj.getCODJEFE(), i,0);
                tablemodel.setValueAt(obj.getNOMBJEFE(),i,1);
                tablemodel.setValueAt(obj.getEMAJEFE(), i,2);
              tablemodel.setValueAt(obj.getTELFJEFE(),i,3);
                tablemodel.setValueAt(obj.getAREAJEFE(),i,4);
                tablemodel.setValueAt(obj.getID(),i,5);
                  tablemodel.setValueAt(obj.getPASS(),i,6);
             
                 i++;
            }
            
          tabla.setModel(tablemodel);
     
            
        
        
    }
     public void insertarjefe(){
        String codjefe,nombjefe,emailjefe,teljefe,areajefe,idjefe,contrajefe;
        
       
        objbean.setCODJEFE(Integer.parseInt( txtcodigo.getText()));
        objbean.setNOMBJEFE(txtnombre.getText());
        objbean.setEMAJEFE(txtcorreo.getText());
        objbean.setTELFJEFE(txttelefono.getText());
        objbean.setAREAJEFE(cboarea.getSelectedItem().toString());//guardar el valor escrito en combobox y no los numeros
        objbean.setID(txtid.getText());
        objbean.setPASS(txtcontraseña.getText());
        int i=objdao.insertarjefe(objbean);
        if(i==0){
            JOptionPane.showMessageDialog(null, "Registro no insertado");
         
        }else{
            JOptionPane.showMessageDialog(null, "Registro Insertado Satisfactoriamente");
            
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

public void eliminarjefe(){
    int codigo=Integer.parseInt(txtcodigo.getText());
    objbean.setCODJEFE(codigo);
    int i=objdao.eliminarjefe(objbean);
    if(i==0){
        JOptionPane.showMessageDialog(null, "Registro no Eliminado");
    }else{
        JOptionPane.showMessageDialog(null, "Registro Eliminado Satisfactoriamnete");
        
    }
    
    
}
     
     public void Seleccionar(){
        try{
            txtcodigo.setText(tabla.getValueAt(tabla.getSelectedRow(),0).toString());
            txtnombre.setText(tabla.getValueAt(tabla.getSelectedRow(),1).toString());
            txtcorreo.setText(tabla.getValueAt(tabla.getSelectedRow(),2).toString());
            txttelefono.setText(tabla.getValueAt(tabla.getSelectedRow(),3).toString());
            cboarea.setSelectedItem(tabla.getValueAt(tabla.getSelectedRow(),4).toString());
            txtid.setText(tabla.getValueAt(tabla.getSelectedRow(),5).toString());
            txtcontraseña.setText(tabla.getValueAt(tabla.getSelectedRow(),6).toString());
           
            btngrabar.setEnabled(false);
        }catch(Exception e){
            
        }
    }
 
         public void Limpiar(){
        txtnombre.setText("");
       txtcorreo.setText("");
       txttelefono.setText("");
       cboarea.setSelectedIndex(0);
       txtid.setText("");
       txtcontraseña.setText("");
       txtnombre.requestFocus();
       btngrabar.setEnabled(true);
        
        
    }
         
           public void ValidacionInsertar(){
                     if(!txtnombre.getText().isEmpty()){
                     if(!txtcorreo.getText().isEmpty()){
                         if(!txttelefono.getText().isEmpty()){
                           if(cboarea.getSelectedIndex()!=0){
                              if(!txtid.getText().isEmpty()){
                                  if(!txtcontraseña.getText().isEmpty()){
                                  
                                           insertarjefe();
                                             mostrar();
                                             Limpiar();
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
           
            public void ValidacionModificar(){
                     if(!txtnombre.getText().isEmpty()){
                     if(!txtcorreo.getText().isEmpty()){
                         if(!txttelefono.getText().isEmpty()){
                           if(cboarea.getSelectedIndex()!=0){
                              if(!txtid.getText().isEmpty()){
                                  if(!txtcontraseña.getText().isEmpty()){
                                  
                                           modificarjefe();
                                             mostrar();
                                             Limpiar();
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
if(e.getSource()==btngrabar){
ValidacionInsertar();
}
if(e.getSource()==btnmodificar){
ValidacionModificar();
}
if(e.getSource()==btneliminar){
    eliminarjefe();
    mostrar();
    Limpiar();
}
if(e.getSource()==btnalctualizar){
    mostrar();
    Limpiar();
}
    
    
    
    }

    @Override
    public void mouseClicked(java.awt.event.MouseEvent e) {
 if(e.getSource()==tabla){
            Seleccionar();
        }
    }

    @Override
    public void mousePressed(java.awt.event.MouseEvent e) {
    }

    @Override
    public void mouseReleased(java.awt.event.MouseEvent e) {
    }

    @Override
    public void mouseEntered(java.awt.event.MouseEvent e) {
    }

    @Override
    public void mouseExited(java.awt.event.MouseEvent e) {
    }
            
            
            

    
    
    
}

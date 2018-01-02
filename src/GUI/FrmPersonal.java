
package GUI;

import BEAN.JefeBean;
import BEAN.PersonalBean;
import BEAN.ProyectoBean;
import DAO.PersonalDAO;
import DAO.ProyectoDAO;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
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

public class FrmPersonal extends JFrame implements ActionListener,MouseListener{
    
     private TextField txtcodigo,txtnombre,txtcorreo,txttelefono,txthoras,txtdias,txtid,txtpass;
    private JComboBox cboproy;
 
    private  JButton btneliminar,btngrabar,btnmodificar,btnalctualizar;
    private JTable tabla;
    private JScrollPane Panel;
    private JLabel lblcodigo,lbltitulo,lblduracion,lbldescripcion,lblfases,lblpresupuesto,lblcoordinador,lbltipo,lblinicio,lblfin;
    DefaultTableModel tablemodel;
    ArrayList<ProyectoBean> listaproy;
    ArrayList<PersonalBean> listaper;
    PersonalDAO objperdao;
    PersonalBean objperbean;
    ProyectoBean objproybean;
    ProyectoDAO objproydao;
    
    
    public FrmPersonal(){
        GUI();
        
        objproybean=new ProyectoBean();
        objproydao=new ProyectoDAO();
        objperbean=new PersonalBean();
        objperdao=new PersonalDAO();
        mostrar();
       combo1();
    
}
    
    public static void main(String []args){
        FrmPersonal principal=new FrmPersonal();
        principal.setVisible(true);
        principal.setLocationRelativeTo(null);
        
    }
    
    
    
    public void GUI(){
         String columnas[]={"CODIGO","PROYECTO","NOMBRE","CORREO","TELEFONO","H. LABORALES","D. LABORALES","ID","PASS"};
         try {
             setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
             getContentPane().setLayout(null);
             getContentPane().setBackground(new Color(0, 153, 153));
        
             txtcodigo=new TextField();
             getContentPane().add(txtcodigo);
             txtcodigo.setBounds(190, 84, 157, 23);
             
            cboproy=new JComboBox();
             getContentPane().add(cboproy);
             cboproy.setBounds(190,124,157,23);
             
             
             
             txtnombre=new TextField();
             getContentPane().add(txtnombre);
             txtnombre.setBounds(190,164,157,23);
             
             txtcorreo=new TextField();
             getContentPane().add(txtcorreo);
             txtcorreo.setBounds(190,204,157,23);
             
            txttelefono=new TextField();
             getContentPane().add(txttelefono);
             txttelefono.setBounds(570,84,157,23);
             

             

             
             txthoras=new TextField();
             getContentPane().add(txthoras);
             txthoras.setBounds(570,124,157,23);
             
             txtdias=new TextField();
             getContentPane().add(txtdias);
             txtdias.setBounds(570,164,157,23);
             
             txtid=new TextField();
             getContentPane().add(txtid);
             txtid.setBounds(570,204,157,23);
             
             txtpass=new TextField();
             getContentPane().add(txtpass);
             txtpass.setBounds(570,244,157,23);
             
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
             lbltitulo.setText("RELACION PERSONAL");
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
         
          int codigo=objperdao.generarCodigo();
        if(codigo==0){
            codigo=1;
        }else{
            codigo=objperdao.generarCodigo();
        }
        txtcodigo.setText(String.valueOf(codigo));
        txtcodigo.setEnabled(false);
        txtnombre.requestFocus();
        int i=0;
       listaper=objperdao.ListarPersonal1();
            tablemodel.setNumRows(listaper.size());
             for(PersonalBean obj:listaper){
                 tablemodel.setValueAt(obj.getCODPERSONAL(), i,0);
                tablemodel.setValueAt(obj.getNOMBPROY(),i,1);
                tablemodel.setValueAt(obj.getNOMBPERSONAL(), i,2);
              tablemodel.setValueAt(obj.getEMAPERSONAL(),i,3);
                tablemodel.setValueAt(obj.getTELFPERSONAL(),i,4);
                tablemodel.setValueAt(obj.getHORAS(),i,5);
                  tablemodel.setValueAt(obj.getDIAS(),i,6);
                  tablemodel.setValueAt(obj.getID(),i,7);
                tablemodel.setValueAt(obj.getPASS(),i,8);
                i++;
            }
             tabla.setModel(tablemodel);
     }
public void combo1(){
     listaproy=objproydao.cargartablaproyecto();
        int a=0;
        for(ProyectoBean obj:listaproy){
            cboproy.addItem(new ProyectoBean(obj.getNumero(),obj.getTitulo()));

        }
     
    
}

      public void insertar(){

        objperbean.setCODPERSONAL(Integer.parseInt( txtcodigo.getText()));

        ProyectoBean objcbo=(ProyectoBean)cboproy.getSelectedItem();
          objperbean.setNUMPROY(objcbo.getNumero());
        objperbean.setNOMBPERSONAL(txtnombre.getText());
        objperbean.setEMAPERSONAL(txtcorreo.getText());
        objperbean.setTELFPERSONAL(txttelefono.getText());
       objperbean.setHORAS(txthoras.getText());
        objperbean.setDIAS(txtdias.getText());
        objperbean.setID(txtid.getText());
        objperbean.setPASS(txtpass.getText());

        
        int i=objperdao.InsertarPersonal(objperbean);
        if(i==0){
            JOptionPane.showMessageDialog(null, "Registro no insertado");
         
        }else{
            JOptionPane.showMessageDialog(null, "Registro Insertado Satisfactoriamente");
            
        }
         
     }
      
      
      public void modificar(){

        objperbean.setCODPERSONAL(Integer.parseInt( txtcodigo.getText()));

        ProyectoBean objcbo=(ProyectoBean)cboproy.getSelectedItem();
          objperbean.setNUMPROY(objcbo.getNumero());
        objperbean.setNOMBPERSONAL(txtnombre.getText());
        objperbean.setEMAPERSONAL(txtcorreo.getText());
        objperbean.setTELFPERSONAL(txttelefono.getText());
       objperbean.setHORAS(txthoras.getText());
        objperbean.setDIAS(txtdias.getText());
        objperbean.setID(txtid.getText());
        objperbean.setPASS(txtpass.getText());

        
        int i=objperdao.ModificarPersonal(objperbean);
        if(i==0){
            JOptionPane.showMessageDialog(null, "Registro no Modificado");
         
        }else{
            JOptionPane.showMessageDialog(null, "Registro Modificado Satisfactoriamente");
            
        }
         
     }
      
      public void eliminar(){
          String cod=txtcodigo.getText();
          int codigo=Integer.parseInt(cod);
          objperbean.setCODPERSONAL(codigo);
          int i=objperdao.EliminarPersonal(objperbean);
          if(i==0){
              JOptionPane.showMessageDialog(null, "Registro no eliminado");
              
          }else{
              JOptionPane.showMessageDialog(null, "Registro eliminado satisfactoriamente");
          }
      }
      
     public void Seleccionar(){
          
        try{
            
           txtcodigo.setText(tabla.getValueAt(tabla.getSelectedRow(),0).toString());
            int cod=Integer.parseInt(txtcodigo.getText());
         
           objperbean.setCODPERSONAL(cod);
           objperbean=objperdao.CapturarCodigo(objperbean);
           txtcodigo.setText(String.valueOf(objperbean.getCODPERSONAL()));
           txtnombre.setText(objperbean.getNOMBPERSONAL());
           
           cboproy.removeAllItems();
           cboproy.addItem(new ProyectoBean(objperbean.getNUMPROY(),objperbean.getNOMBPROY()));
          combo1();
          txtcorreo.setText(objperbean.getEMAPERSONAL());
            txttelefono.setText(objperbean.getTELFPERSONAL());
            txthoras.setText(objperbean.getHORAS());
            txtdias.setText(objperbean.getDIAS());
            txtid.setText(objperbean.getID());
            txtpass.setText(objperbean.getPASS());
           btngrabar.setEnabled(false);
           
        }catch(Exception e){
            
        }
        
    }
 
         public void Limpiar(){
       txtnombre.setText("");
       //actualizar combobox
       cboproy.removeAllItems();
       combo1();
       txtcorreo.setText("");
       txttelefono.setText("");
       txthoras.setText("");
       txtdias.setText("");
       txtid.setText("");
       txtpass.setText("");
       txtnombre.requestFocus();
       btngrabar.setEnabled(true);
        
        
    }

         
         public void ValidacionInsertar(){
               if(cboproy.getItemCount() >0){
             if(!txtnombre.getText().isEmpty()){
                     if(!txtcorreo.getText().isEmpty()){
                         if(!txttelefono.getText().isEmpty()){
                             if(!txthoras.getText().isEmpty()){
                                         if(!txtdias.getText().isEmpty()){
                                             if(!txtid.getText().isEmpty()){
                                                     if(!txtpass.getText().isEmpty()){
                                           insertar();
                                             mostrar();
                                             Limpiar();
                                              }else{
                                                         JOptionPane.showMessageDialog(null, "INGRESE EL PASSWORD DEL PERSONAL");
                                                         txtpass.requestFocus();
                                                     }
                                                         
                                         }else{
                                                  JOptionPane.showMessageDialog(null, "INGRESE EL ID DEL PERSONAL");
                                             txtid.requestFocus();
                                             }
                                     }else{
                                              JOptionPane.showMessageDialog(null, "INGRESE LOS DIAS LABORALES DEL PERSONAL");

                                              txtdias.requestFocus();
                                         }
                                 }else{
                                          JOptionPane.showMessageDialog(null, "INGRESE LAS HORAS LABORALES DEL PERSONAL");
                                     txthoras.requestFocus();
                                     }
                             }else{
                                      JOptionPane.showMessageDialog(null, "INGRESE EL TELEFONO DEL PERSONAL");
                                 txttelefono.requestFocus();
                                 }
                             
                         }else{
                                  JOptionPane.showMessageDialog(null, "INGRESE EL CORREO DEL PERSONAL");
                             txtcorreo.requestFocus();
                             }
                     }else{
                              JOptionPane.showMessageDialog(null, "INGRESE EL NOMBRE DEL PERSONAL");
                         txtnombre.requestFocus();
                         }
                     }else{
                         JOptionPane.showMessageDialog(null, "REGISTRE PRIMERO UN PROYECTO");
                     }
                 
             }
         
           public void ValidacionModificar(){
               if(cboproy.getItemCount() >0){
             if(!txtnombre.getText().isEmpty()){
                     if(!txtcorreo.getText().isEmpty()){
                         if(!txttelefono.getText().isEmpty()){
                             if(!txthoras.getText().isEmpty()){
                                         if(!txtdias.getText().isEmpty()){
                                             if(!txtid.getText().isEmpty()){
                                                     if(!txtpass.getText().isEmpty()){
                                           modificar();
                                             mostrar();
                                             Limpiar();
                                              }else{
                                                         JOptionPane.showMessageDialog(null, "INGRESE EL PASSWORD DEL PERSONAL");
                                                         txtpass.requestFocus();
                                                     }
                                                         
                                         }else{
                                                  JOptionPane.showMessageDialog(null, "INGRESE EL ID DEL PERSONAL");
                                             txtid.requestFocus();
                                             }
                                     }else{
                                              JOptionPane.showMessageDialog(null, "INGRESE LOS DIAS LABORALES DEL PERSONAL");

                                              txtdias.requestFocus();
                                         }
                                 }else{
                                          JOptionPane.showMessageDialog(null, "INGRESE LAS HORAS LABORALES DEL PERSONAL");
                                     txthoras.requestFocus();
                                     }
                             }else{
                                      JOptionPane.showMessageDialog(null, "INGRESE EL TELEFONO DEL PERSONAL");
                                 txttelefono.requestFocus();
                                 }
                             
                         }else{
                                  JOptionPane.showMessageDialog(null, "INGRESE EL CORREO DEL PERSONAL");
                             txtcorreo.requestFocus();
                             }
                     }else{
                              JOptionPane.showMessageDialog(null, "INGRESE EL NOMBRE DEL PERSONAL");
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

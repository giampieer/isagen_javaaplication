/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BEAN.JefeBean;
import BEAN.ProyectoBean;
import DAO.JefeDao;
import DAO.ProyectoDAO;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

public class FrnProyecto extends JFrame implements ActionListener,MouseListener{
    private TextField txtcodigo,txttitulo,txtduracion,txtdescripcion,txtfases,txtpresupuesto;
    private JComboBox cbocoordgrab,cbotipo;
    private  JDateChooser   calinicio,calfin;
    private  JButton btneliminar,btngrabar,btnmodificar,btnalctualizar,btnimpresion;
    private JTable tabla;
    private JScrollPane Panel;
    private JLabel lblcodigo,lbltitulo,lblduracion,lbldescripcion,lblfases,lblpresupuesto,lblcoordinador,lbltipo,lblinicio,lblfin;
    DefaultTableModel tablemodel;
    ArrayList<ProyectoBean> listaproy;
    ArrayList<JefeBean> listajefe;
    JefeBean objbean;
    JefeDao objdao;
    ProyectoBean objproybean;
    ProyectoDAO objproydao;
    //enviar numero del proyecto para porder crear pdf
    public static int num;
    
    public FrnProyecto(){
        GUI();
        objbean=new JefeBean();
        objdao=new JefeDao();
        objproybean=new ProyectoBean();
        objproydao=new ProyectoDAO();
        mostrar();
       combo1();
       
     
}
    
    public static void main(String []args){
        try {
                
                  FrnProyecto principal=new FrnProyecto();
        principal.setVisible(true);
        principal.setLocationRelativeTo(null);
        } catch (Exception e) {
        }

        
    }
     public void GUI(){
         String columnas[]={"CODIGO","COORDINADOR","TITULO","DURACION","DESCRIPCION","TIPO","FASES","F.INICIO","F.FIN","GASTOS"};
         try {
             setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
             getContentPane().setLayout(null);
             getContentPane().setBackground(new Color(0, 153, 153));
        
             txtcodigo=new TextField();
             getContentPane().add(txtcodigo);
             txtcodigo.setBounds(190, 84, 157, 23);
             
             txttitulo=new TextField();
             getContentPane().add(txttitulo);
             txttitulo.setBounds(190,124,157,23);
             

             
             cbocoordgrab=new JComboBox();
             getContentPane().add(cbocoordgrab);
             cbocoordgrab.setBounds(190,164,157,23);
             
             
             
             
             txtduracion=new TextField();
             getContentPane().add(txtduracion);
             txtduracion.setBounds(190,204,157,23);
             
             txtdescripcion=new TextField();
             getContentPane().add(txtdescripcion);
             txtdescripcion.setBounds(190,244,157,23);
             
                calinicio=new JDateChooser();
             getContentPane().add(calinicio);
             calinicio.setLocale(new Locale("es"));
              calinicio.setDateFormatString("yyyy-MM-dd");
              //dato por defecto
              SimpleDateFormat parser1 = new SimpleDateFormat("yyyy-MM-dd");
              Date date1 = parser1.parse(" 2017-01-01");
            calinicio.setDate(date1);
             calinicio.setBounds(570, 84, 157, 23);
              
             
             calfin=new JDateChooser();
             getContentPane().add(calfin);
             calfin.setLocale(new Locale("es"));
              calfin.setDateFormatString("yyyy-MM-dd");
              //dato por defecto
              SimpleDateFormat parser2 = new SimpleDateFormat("yyyy-MM-dd");
            Date date2 = parser2.parse("2017-01-01");
            calfin.setDate(date2);
             calfin.setBounds(570,124,157,23);
             

             
             cbotipo=new JComboBox();
             getContentPane().add(cbotipo);
             cbotipo.addItem("----Seleccionar----");
             cbotipo.addItem("Publicos");
             cbotipo.addItem("Privados");
             cbotipo.addItem("Experimentales");
             cbotipo.addItem("Normalizados");
             cbotipo.addItem("Productivos");
             cbotipo.addItem("Sociales");
             cbotipo.addItem("Investigacion");
             cbotipo.setBounds(570,164,157,23);
             
             txtfases=new TextField();
             getContentPane().add(txtfases);
             txtfases.setBounds(570,204,157,23);
             
             txtpresupuesto=new TextField();
             getContentPane().add(txtpresupuesto);
             txtpresupuesto.setBounds(570,244,157,23);
             
             btngrabar=new JButton();
             getContentPane().add(btngrabar);
             btngrabar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/grabar.png")));
             btngrabar.setText("GRABAR");
             btngrabar.setBounds(45,345,157,40);
             btngrabar.addActionListener(this);
             btngrabar.setBackground(new Color(0,102,153));
             
                 btnmodificar=new JButton();
             getContentPane().add(btnmodificar);
             btnmodificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/modificar.png")));
             btnmodificar.setText("MODIFICAR");
             btnmodificar.setBounds(210,345,157,40);
             btnmodificar.addActionListener(this);
              btnmodificar.setBackground(new Color(0,102,153));
              btnmodificar.setEnabled(false);
             
                 btneliminar=new JButton();
             getContentPane().add(btneliminar);
             btneliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/eliminar.png")));
             btneliminar.setText("ELIMINAR");
             btneliminar.setBounds(380,345,157,40);
             btneliminar.addActionListener(this);
              btneliminar.setBackground(new Color(0,102,153));
              btneliminar.setEnabled(false);
             
             btnalctualizar=new JButton();
             getContentPane().add(btnalctualizar);
             btnalctualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/actualizar.png")));
             btnalctualizar.setText("ACTUALIZAR");
             btnalctualizar.setBounds(550,345,157,40);
             btnalctualizar.addActionListener(this);
              btnalctualizar.setBackground(new Color(0,102,153));
             
              
              btnimpresion=new JButton();
             getContentPane().add(btnimpresion);
             btnimpresion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/impresion.png")));
             btnimpresion.setText("IMPRESION");
             btnimpresion.setBounds(305,400,157,40);
             btnimpresion.addActionListener(this);
              btnimpresion.setBackground(new Color(0,102,153));
              btnimpresion.setEnabled(false);
              
             lbltitulo=new JLabel();
             getContentPane().add(lbltitulo);
             lbltitulo.setText("RELACION PROYECTO");
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
         
         JButton btn=new JButton();
         getContentPane().add(btn);
         //SINGLETON
          int codigo=objproydao.getInstancia().generarCodigo();
        if(codigo==0){
            codigo=1;
        }else{
            codigo=objproydao.getInstancia().generarCodigo();
        }
        txtcodigo.setText(String.valueOf(codigo));
        txtcodigo.setEnabled(false);
        txttitulo.requestFocus();
        int i=0;
       listaproy=objproydao.getInstancia().cargartablaproyecto();
            tablemodel.setNumRows(listaproy.size());
             for(ProyectoBean obj:listaproy){
                 tablemodel.setValueAt(obj.getNumero(), i,0);
                tablemodel.setValueAt(obj.getNOMBJEFE(),i,1);
                tablemodel.setValueAt(obj.getTitulo(), i,2);
              tablemodel.setValueAt(obj.getDuracion(),i,3);
                tablemodel.setValueAt(obj.getDescripcion(),i,4);
                tablemodel.setValueAt(obj.getTipo(),i,5);
                  tablemodel.setValueAt(obj.getFases(),i,6);
                  tablemodel.setValueAt(obj.getInicio(),i,7);
                tablemodel.setValueAt(obj.getFin(),i,8);
                tablemodel.setValueAt(obj.getGastos(),i,9);
                
                
                i++;
            }
             tabla.setModel(tablemodel);
     }
public void combo1(){
     listajefe=objdao.ListarJefedeProyecto();
        int a=0;
        for(JefeBean obj:listajefe){
            cbocoordgrab.addItem(new JefeBean(obj.getCODJEFE(),obj.getNOMBJEFE()));

        }
       
    
}

      public void insertar(){

        objproybean.setNumero(Integer.parseInt( txtcodigo.getText()));

        JefeBean objcbo=(JefeBean)cbocoordgrab.getSelectedItem();
          objproybean.setCODJEFE(objcbo.getCODJEFE());
        objproybean.setTitulo(txttitulo.getText());
        objproybean.setDuracion(txtduracion.getText());
        objproybean.setDescripcion(txtdescripcion.getText());
        objproybean.setTipo(cbotipo.getSelectedItem().toString());
        objproybean.setFases(txtfases.getText());
        DateFormat fecha = new SimpleDateFormat("yyyy-MM-dd");
        String convertido = fecha.format(calinicio.getDate());
        String convertido2=fecha.format(calfin.getDate());
        
        objproybean.setInicio(convertido);
        objproybean.setFin(convertido2);
        objproybean.setGastos(txtpresupuesto.getText());
        
        int i=objproydao.grabarproyecto(objproybean);
        if(i==0){
            JOptionPane.showMessageDialog(null, "Registro no insertado");
         
        }else{
            JOptionPane.showMessageDialog(null, "Registro Insertado Satisfactoriamente");
            
        }
         
     }
      
      
      public void modificar(){

        objproybean.setNumero(Integer.parseInt( txtcodigo.getText()));
        String cod=(cbocoordgrab.getSelectedItem().toString());
        JefeBean objcbo=(JefeBean)cbocoordgrab.getSelectedItem();
          objproybean.setCODJEFE(objcbo.getCODJEFE());
        objproybean.setTitulo(txttitulo.getText());
        objproybean.setDuracion(txtduracion.getText());
        objproybean.setDescripcion(txtdescripcion.getText());
        objproybean.setTipo(cbotipo.getSelectedItem().toString());
        objproybean.setFases(txtfases.getText());
        DateFormat fecha = new SimpleDateFormat("yyyy-MM-dd");
        String convertido = fecha.format(calinicio.getDate());
        String convertido2=fecha.format(calfin.getDate());
        
        objproybean.setInicio(convertido);
        objproybean.setFin(convertido2);
        objproybean.setGastos(txtpresupuesto.getText());
        
        int i=objproydao.modificarproyecto(objproybean);
        if(i==0){
            JOptionPane.showMessageDialog(null, "Registro no modificado");
         
        }else{
            JOptionPane.showMessageDialog(null, "Registro modificado satisfactoriamnete");
            
        }
         
     }
      
      public void eliminar(){
          String cod=txtcodigo.getText();
          int codigo=Integer.parseInt(cod);
          objproybean.setNumero(codigo);
          int i=objproydao.eliminarproyecto(objproybean);
          if(i==0){
              JOptionPane.showMessageDialog(null, "Registro no eliminado");
              
          }else{
              JOptionPane.showMessageDialog(null, "Registro eliminado satisfactoriamente");
          }
      }
      
      public void impresion(){
           num=(Integer.parseInt(txtcodigo.getText()));
          Impresion ventanaimp=new Impresion();
          ventanaimp.setVisible(true);
          ventanaimp.setLocationRelativeTo(null);
          
      }
      
     public void Seleccionar(){
          
        try{
            
           txtcodigo.setText(tabla.getValueAt(tabla.getSelectedRow(),0).toString());
            int cod=Integer.parseInt(txtcodigo.getText());
         
           objproybean.setNumero(cod);
           objproybean=objproydao.CapturarProyecto(objproybean);
           txtcodigo.setText(String.valueOf(objproybean.getNumero()));
           txttitulo.setText(objproybean.getTitulo());
           
           cbocoordgrab.removeAllItems();
       
          cbocoordgrab.addItem(new JefeBean(objproybean.getCODJEFE(),objproybean.getNOMBJEFE()));
          combo1();
          txtduracion.setText(objproybean.getDuracion());
            txtdescripcion.setText(objproybean.getDescripcion());
            cbotipo.setSelectedItem(objproybean.getTipo());
              txtfases.setText(objproybean.getFases());
              //convertir date a string
           String fechainicio=(objproybean.getInicio());
           String fechafin=(objproybean.getFin());
           SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
           Date date1 = parser.parse(fechainicio);
            Date date2 = parser.parse(fechafin);
           calinicio.setDate(date1);
            calfin.setDate(date2);
            txtpresupuesto.setText(objproybean.getGastos());
           btngrabar.setEnabled(false);
           btnimpresion.setEnabled(true);
           btnmodificar.setEnabled(true);
           btneliminar.setEnabled(true);

        }catch(Exception e){
            
        }
        
    }
 
         public void Limpiar(){
       txttitulo.setText("");
       //actualizar combobox
       cbocoordgrab.removeAllItems();
       combo1();
       txtduracion.setText("");
       txtdescripcion.setText("");
       calinicio.setDateFormatString("yyyy-MM-dd");
       calfin.setDateFormatString("yyyy-MM-dd");
       cbotipo.setSelectedIndex(0);
       txtfases.setText("");
       txtpresupuesto.setText("");
       txttitulo.requestFocus();
       btngrabar.setEnabled(true);
       btnimpresion.setEnabled(false);
        btnmodificar.setEnabled(false);
         btnimpresion.setEnabled(false);
         btneliminar.setEnabled(false);
        
        
    }
         public void ValidacionInsertar(){
             if(!txttitulo.getText().isEmpty()){
                     if(!txtduracion.getText().isEmpty()){
                         if(!txtdescripcion.getText().isEmpty()){
                             if(calinicio.getDate()!=null){
                                 if(calfin.getDate()!=null){
                                     if(cbotipo.getSelectedIndex()!=0){
                                         if(!txtfases.getText().isEmpty()){
                                             if(!txtpresupuesto.getText().isEmpty()){
                                                     if(cbocoordgrab.getItemCount() >0){
                                           insertar();
                                             mostrar();
                                             Limpiar();
                                             
                                              }else{
                                                         JOptionPane.showMessageDialog(null, "REGISTRE PRIMERO UN JEFE DEL PROYECTO ");
                                                     }
                                                         
                                         }else{
                                                  JOptionPane.showMessageDialog(null, "INGRESE EL PRESUPUESTO DEL PROYECTO");
                                             txtpresupuesto.requestFocus();
                                             }
                                     }else{
                                              JOptionPane.showMessageDialog(null, "INGRESE LAS FASES DEL PROYECTO");

                                              txtfases.requestFocus();
                                         }
                                 }else{
                                          JOptionPane.showMessageDialog(null, "SELECCIONE EL TIPO DEL PROYECTO");
                                     
                                     }
                             }else{
                                      JOptionPane.showMessageDialog(null, "INGRESE LA FECHA FINAL DEL PROYECTO");
                                 calfin.requestFocus();
                                 }
                             
                         }else{
                                  JOptionPane.showMessageDialog(null, "INGRESE LA FECHA DE INICIO DEL PROYECTO");
                             calinicio.requestFocus();
                             }
                     }else{
                              JOptionPane.showMessageDialog(null, "INGRESE LA DESCRIPCION DEL PROYECTO");
                         txtdescripcion.requestFocus();
                         }
                     }else{
                          JOptionPane.showMessageDialog(null, "INGRESE LA DURACION DEL PROYECTO");
                          txtduracion.requestFocus();
                     }
                 
             }else{
                 JOptionPane.showMessageDialog(null, "INGRESE EL TITULO DEL PROYECTO");
                 txttitulo.requestFocus();
             }
             
             
         }
         
           public void ValidacionModificar(){
             if(!txttitulo.getText().isEmpty()){
                     if(!txtduracion.getText().isEmpty()){
                         if(!txtdescripcion.getText().isEmpty()){
                             if(calinicio.getDate()!=null){
                                 if(calfin.getDate()!=null){
                                     if(cbotipo.getSelectedIndex()!=0){
                                         if(!txtfases.getText().isEmpty()){
                                             if(!txtpresupuesto.getText().isEmpty()){
                                                 if(cbocoordgrab.getItemCount() >0){
                                                modificar();
                                                mostrar();
                                                Limpiar();
                                                
                                                 }else{
                                                         JOptionPane.showMessageDialog(null, "REGISTRE PRIMERO UN JEFE DEL PROYECTO ");
                                                     }
                                         }else{
                                                  JOptionPane.showMessageDialog(null, "INGRESE EL PRESUPUESTO DEL PROYECTO");
                                             txtpresupuesto.requestFocus();
                                             }
                                     }else{
                                              JOptionPane.showMessageDialog(null, "INGRESE LAS FASES DEL PROYECTO");

                                              txtfases.requestFocus();
                                         }
                                 }else{
                                          JOptionPane.showMessageDialog(null, "SELECCIONE EL TIPO DEL PROYECTO");
                                     
                                     }
                             }else{
                                      JOptionPane.showMessageDialog(null, "INGRESE LA FECHA FINAL DEL PROYECTO");
                                 calfin.requestFocus();
                                 }
                             
                         }else{
                                  JOptionPane.showMessageDialog(null, "INGRESE LA FECHA DE INICIO DEL PROYECTO");
                             calinicio.requestFocus();
                             }
                     }else{
                              JOptionPane.showMessageDialog(null, "INGRESE LA DESCRIPCION DEL PROYECTO");
                         txtdescripcion.requestFocus();
                         }
                     }else{
                          JOptionPane.showMessageDialog(null, "INGRESE LA DURACION DEL PROYECTO");
                          txtduracion.requestFocus();
                     }
                 
             }else{
                 JOptionPane.showMessageDialog(null, "INGRESE EL TITULO DEL PROYECTO");
                 txttitulo.requestFocus();
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
        if(e.getSource()==btnimpresion){
             impresion();
            
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

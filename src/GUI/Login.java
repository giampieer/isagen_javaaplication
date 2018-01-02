
package GUI;

import BEAN.JefeBean;
import BEAN.PersonalBean;
import DAO.JefeDao;
import DAO.PersonalDAO;
import UTIL.ConexionBD;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.tree.DefaultTreeCellEditor;


public class Login extends javax.swing.JFrame implements ActionListener{
 JefeBean objjefebean;
JefeDao objjefedao;
    PersonalBean objpersobean;
    PersonalDAO objperdao;
/*
public =clase publica cualquier clase ajena a ventana login va a tener acceso
static =corresponde a ventana login 
*/
public static JefeBean obj=new JefeBean();
   

    public Login() {
        initComponents();
        //desabilitar boton maximizar
          this.setResizable(false);
          //titulo al jframe
          this.setTitle("Isagen");
          
       getContentPane().setBackground(new Color(0,153,153));
        this.setLocationRelativeTo(null);
        ConexionBD cn=new ConexionBD();
        cn.getConexionBD();
         objjefebean=new JefeBean();
         objjefedao=new JefeDao();
         objperdao=new PersonalDAO();
         objpersobean=new PersonalBean();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane1 = new javax.swing.JDesktopPane();
        btnloginjefe = new javax.swing.JButton();
        txtidjefe = new javax.swing.JTextField();
        cbotipo = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        txtpassjefe = new javax.swing.JPasswordField();
        jDesktopPane2 = new javax.swing.JDesktopPane();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jDesktopPane1.setBackground(new java.awt.Color(0, 102, 153));

        btnloginjefe.setBackground(new java.awt.Color(0, 153, 153));
        btnloginjefe.setText("LOGIN");
        btnloginjefe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnloginjefeActionPerformed(evt);
            }
        });

        txtidjefe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtidjefeActionPerformed(evt);
            }
        });

        cbotipo.setBackground(new java.awt.Color(0, 153, 153));
        cbotipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "----SELECCIONAR----", "ADMINISTRADOR", "PERSONAL" }));
        cbotipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbotipoActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/login.png"))); // NOI18N
        jLabel1.setText("jLabel1");

        txtpassjefe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtpassjefeActionPerformed(evt);
            }
        });

        jDesktopPane1.setLayer(btnloginjefe, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(txtidjefe, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(cbotipo, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(txtpassjefe, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane1Layout.createSequentialGroup()
                        .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbotipo, 0, 157, Short.MAX_VALUE)
                            .addComponent(txtidjefe, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtpassjefe))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane1Layout.createSequentialGroup()
                        .addComponent(btnloginjefe)
                        .addGap(49, 49, 49)))
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                        .addComponent(cbotipo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(txtidjefe, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtpassjefe, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnloginjefe)
                        .addGap(29, 29, 29))))
        );

        jDesktopPane2.setBackground(new java.awt.Color(0, 153, 153));

        jLabel2.setBackground(new java.awt.Color(0, 153, 153));
        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setText("LOGIN DE ACCESO");

        jDesktopPane2.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane2Layout = new javax.swing.GroupLayout(jDesktopPane2);
        jDesktopPane2.setLayout(jDesktopPane2Layout);
        jDesktopPane2Layout.setHorizontalGroup(
            jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane2Layout.createSequentialGroup()
                .addGap(99, 99, 99)
                .addComponent(jLabel2)
                .addContainerGap(101, Short.MAX_VALUE))
        );
        jDesktopPane2Layout.setVerticalGroup(
            jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1)
            .addComponent(jDesktopPane2, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jDesktopPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jDesktopPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtidjefeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtidjefeActionPerformed
  
    }//GEN-LAST:event_txtidjefeActionPerformed

    private void btnloginjefeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnloginjefeActionPerformed
        login();

    }//GEN-LAST:event_btnloginjefeActionPerformed

    private void cbotipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbotipoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbotipoActionPerformed

    private void txtpassjefeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtpassjefeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtpassjefeActionPerformed


    public static void main(String args[]) {

        try {
           /* for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }*/
             UIManager.setLookAndFeel("com.jtattoo.plaf.graphite.GraphiteLookAndFeel");   
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
                
            }
        });
    }
    
    
    public void limpiar(){
        txtidjefe.setText("");
        txtpassjefe.setText("");
        txtidjefe.requestFocus();
        
    }
    public void login(){
        int i=0;
          String idjefe=txtidjefe.getText();
         String passjefe=txtpassjefe.getText();
        //se almacena en jefe
         objjefebean.setID(idjefe);
        objjefebean.setPASS(passjefe);
        //se alamacena en personal
        objpersobean.setID(idjefe);
        objpersobean.setPASS(passjefe);
        
           if(cbotipo.getSelectedIndex()!=0){
        if(!txtidjefe.getText().isEmpty()){
                         if(!txtpassjefe.getText().isEmpty()){
                             
                             if(cbotipo.getSelectedIndex()==1){
            
            i=objjefedao.Login(objjefebean);
            if (i==1) {
             JOptionPane.showMessageDialog(null, "INGRESANDO COMO ADMINISTRADOR ........");
             MenuAdministrador ventana1=new MenuAdministrador();
             //obj es para enviar datos a cualquier frame
             obj=objjefedao.capturarDatosUsuario(objjefebean);
             
             objjefebean=objjefedao.capturarDatosUsuario(objjefebean);
             ventana1.lblusuario.setText(objjefebean.getID());
             ventana1.MENUUSUARIO.setText("BIENVENIDO:"+objjefebean.getID());
             ventana1.setVisible(true);
             ventana1.setLocationRelativeTo(null);
                          //cerrar ventana login
           this.dispose();
            }else{
                JOptionPane.showMessageDialog(null, "ERROR AL INICIAR SESION");
                limpiar();
            }
        
        }else{
            if(cbotipo.getSelectedIndex()==2){
                
                i=objperdao.ValidarAcceso(objpersobean);
                if (i==1) {
             JOptionPane.showMessageDialog(null, "INGRESANDO COMO PERSONAL ........");
             MenuPersonal ventana2=new MenuPersonal();
             /*obj es para enviar datos a cualquier frame
             obj=objjefedao.capturarDatosUsuario(objjefebean);
             
             objjefebean=objjefedao.capturarDatosUsuario(objjefebean);
             ventana2.lblusuario.setText(objjefebean.getID());
             ventana2.MENUUSUARIO.setText("BIENVENIDO:"+objjefebean.getID());*/
             ventana2.setVisible(true);
             ventana2.setLocationRelativeTo(null);
                          //cerrar ventana login
           this.dispose();
           
         
            }else{
                JOptionPane.showMessageDialog(null, "ERROR AL INICIAR SESION");
                limpiar();
                }
            
            }}
                             
                             
                             
                         }else{
                                JOptionPane.showMessageDialog(null,"INGRESE EL PASSWORD PARA INGRESAR");
                                txtpassjefe.requestFocus();
                         }}else{
               JOptionPane.showMessageDialog(null, "INGRESE EL ID PARA INGRESAR");
               txtidjefe.requestFocus();
        }}else{
                JOptionPane.showMessageDialog(null, "SELECCIONE CATEGORIA PARA INGRESAR");
           }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnloginjefe;
    private javax.swing.JComboBox<String> cbotipo;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JDesktopPane jDesktopPane2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField txtidjefe;
    private javax.swing.JPasswordField txtpassjefe;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent e) {


        if (e.getSource()==btnloginjefe) {
          login();
        }
    }
    
    //cambiar icno del jframe
    @Override
public Image getIconImage() {
   Image retValue = Toolkit.getDefaultToolkit().
         getImage(ClassLoader.getSystemResource("img/iconojframe.png"));


   return retValue;
}
    
}

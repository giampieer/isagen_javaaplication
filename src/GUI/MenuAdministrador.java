package GUI;

import BEAN.JefeBean;
import DAO.JefeDao;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Dimension2D;
import java.net.URI;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MenuAdministrador extends javax.swing.JFrame implements Runnable{

     JefeBean objjefebean1;
    JefeDao objjefedao;
    String hora, minutos, segundos, ampm;
    Calendar calendario;
    Thread h1;
 
    public MenuAdministrador() {
 
        initComponents();
          this.setResizable(false);
        h1 = new Thread(this);
        h1.start();
        this.setSize(1200,900);
        this.setLocationRelativeTo(null);
        //siempre ir al final
       cerrar();
        
    }
    

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane1 = new javax.swing.JDesktopPane();
        lbHora = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblusuario = new javax.swing.JLabel();
        barra = new javax.swing.JMenuBar();
        MENUUSUARIO = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        nuevo = new javax.swing.JMenuItem();
        requisito = new javax.swing.JMenuItem();
        problema = new javax.swing.JMenuItem();
        objetivo = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jefe = new javax.swing.JMenuItem();
        colaborador = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        reunion = new javax.swing.JMenuItem();
        cambios = new javax.swing.JMenuItem();
        interesados = new javax.swing.JMenuItem();
        riesgos = new javax.swing.JMenuItem();
        solucion = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        actividades = new javax.swing.JMenuItem();
        control_de_calidad = new javax.swing.JMenuItem();
        MENUAJUSTES = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(2000, 2000));

        jDesktopPane1.setBackground(new java.awt.Color(0, 102, 153));
        jDesktopPane1.setPreferredSize(new java.awt.Dimension(2000, 2000));

        lbHora.setFont(new java.awt.Font("Trebuchet MS", 0, 36)); // NOI18N

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/profile.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("EMPRESA ISAGEN");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel3.setText("ADMINISTRADOR:");

        lblusuario.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jDesktopPane1.setLayer(lbHora, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(lblusuario, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addGap(400, 400, 400)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1207, Short.MAX_VALUE))
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblusuario, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbHora, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
                            .addComponent(lblusuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(lbHora, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(68, 68, 68)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(2496, Short.MAX_VALUE))
        );

        barra.setBackground(new java.awt.Color(0, 102, 153));
        barra.setBorder(null);
        barra.setMaximumSize(new java.awt.Dimension(600, 20));
        barra.setPreferredSize(new java.awt.Dimension(310, 40));

        MENUUSUARIO.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/usuario.png"))); // NOI18N

        jMenuItem1.setText("ACTULIZAR DATOS");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        MENUUSUARIO.add(jMenuItem1);

        jMenuItem2.setText("CAMBIAR CONTRASEÑA");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        MENUUSUARIO.add(jMenuItem2);

        barra.add(MENUUSUARIO);

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/proyecto.png"))); // NOI18N
        jMenu1.setText("PROYECTO");

        nuevo.setText("NUEVO");
        nuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevoActionPerformed(evt);
            }
        });
        jMenu1.add(nuevo);

        requisito.setText("REQUISITO");
        requisito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                requisitoActionPerformed(evt);
            }
        });
        jMenu1.add(requisito);

        problema.setText("PROBLEMA");
        problema.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                problemaActionPerformed(evt);
            }
        });
        jMenu1.add(problema);

        objetivo.setText("OBJETIVOS");
        objetivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                objetivoActionPerformed(evt);
            }
        });
        jMenu1.add(objetivo);

        barra.add(jMenu1);

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/personal.png"))); // NOI18N
        jMenu2.setText("PERSONAL");

        jefe.setText("JEFE");
        jefe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jefeActionPerformed(evt);
            }
        });
        jMenu2.add(jefe);

        colaborador.setText("COLABORADOR");
        colaborador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                colaboradorActionPerformed(evt);
            }
        });
        jMenu2.add(colaborador);

        barra.add(jMenu2);

        jMenu3.setBackground(new java.awt.Color(0, 102, 153));
        jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/seguimiento.png"))); // NOI18N
        jMenu3.setText("SEGUIMIENTO");

        reunion.setText("REUNIONES");
        reunion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reunionActionPerformed(evt);
            }
        });
        jMenu3.add(reunion);

        cambios.setText("CAMBIOS");
        cambios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cambiosActionPerformed(evt);
            }
        });
        jMenu3.add(cambios);

        interesados.setText("INTERESADOS");
        interesados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                interesadosActionPerformed(evt);
            }
        });
        jMenu3.add(interesados);

        riesgos.setText("RIESGOS");
        riesgos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                riesgosActionPerformed(evt);
            }
        });
        jMenu3.add(riesgos);

        solucion.setText("SOLUCION");
        solucion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                solucionActionPerformed(evt);
            }
        });
        jMenu3.add(solucion);

        barra.add(jMenu3);

        jMenu4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cronograma.png"))); // NOI18N
        jMenu4.setText("CRONOGRAMA");

        actividades.setText("ACTIVIDADES");
        actividades.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actividadesActionPerformed(evt);
            }
        });
        jMenu4.add(actividades);

        control_de_calidad.setText("CONTROL DE CALIDAD");
        control_de_calidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                control_de_calidadActionPerformed(evt);
            }
        });
        jMenu4.add(control_de_calidad);

        barra.add(jMenu4);

        MENUAJUSTES.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ajustes.png"))); // NOI18N
        MENUAJUSTES.setText("AJUSTES");

        jMenuItem3.setText("CERRAR SESION");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        MENUAJUSTES.add(jMenuItem3);

        barra.add(MENUAJUSTES);

        setJMenuBar(barra);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 3011, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    
    private void nuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevoActionPerformed
       
        FrnProyecto ventanaproyecto=new FrnProyecto();
        ventanaproyecto.setVisible(true);
        ventanaproyecto.setLocationRelativeTo(null);
        
    }//GEN-LAST:event_nuevoActionPerformed

    private void jefeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jefeActionPerformed
        FrmJefe ventanajefe=new FrmJefe();
        ventanajefe.setVisible(true);
        ventanajefe.setLocationRelativeTo(null);
     
    }//GEN-LAST:event_jefeActionPerformed

    private void requisitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_requisitoActionPerformed

        FrmRequisito ventanarequisito=new FrmRequisito();
        ventanarequisito.setVisible(true);
        ventanarequisito.setLocationRelativeTo(null);

    }//GEN-LAST:event_requisitoActionPerformed

    private void problemaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_problemaActionPerformed

        FrmProblema ventanaproblema=new FrmProblema();
        ventanaproblema.setVisible(true);
        ventanaproblema.setLocationRelativeTo(null);


    }//GEN-LAST:event_problemaActionPerformed

    private void objetivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_objetivoActionPerformed
FrmObjetivo principal=new FrmObjetivo();
principal.setVisible(true);
principal.setLocationRelativeTo(null);

    }//GEN-LAST:event_objetivoActionPerformed

    private void colaboradorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_colaboradorActionPerformed
        FrmPersonal principal=new FrmPersonal();
        principal.setVisible(true);
        principal.setLocationRelativeTo(null);
        
        
        
    }//GEN-LAST:event_colaboradorActionPerformed

    private void cambiosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cambiosActionPerformed

         FrmCambio principal=new FrmCambio();
        principal.setVisible(true);
        principal.setLocationRelativeTo(null);

    }//GEN-LAST:event_cambiosActionPerformed

    private void reunionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reunionActionPerformed

        FrmReuniones principal=new FrmReuniones();
        principal.setVisible(true);
        principal.setLocationRelativeTo(null);

    }//GEN-LAST:event_reunionActionPerformed

    private void riesgosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_riesgosActionPerformed

        FrmRiesgo principal=new FrmRiesgo();
        principal.setVisible(true);
        principal.setLocationRelativeTo(null);


    }//GEN-LAST:event_riesgosActionPerformed

    private void solucionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_solucionActionPerformed

        FrmSolucion principal=new FrmSolucion();
        principal.setVisible(true);
        principal.setLocationRelativeTo(null);

    }//GEN-LAST:event_solucionActionPerformed

    private void interesadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_interesadosActionPerformed

        FrmInteresados principal=new FrmInteresados();
        principal.setVisible(true);
        principal.setLocationRelativeTo(null);


    }//GEN-LAST:event_interesadosActionPerformed

    private void actividadesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actividadesActionPerformed
 FrmActividad principal=new FrmActividad();
        principal.setVisible(true);
        principal.setLocationRelativeTo(null);    }//GEN-LAST:event_actividadesActionPerformed

    private void control_de_calidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_control_de_calidadActionPerformed
      FrmControlDeCalidad principal=new FrmControlDeCalidad();
        principal.setVisible(true);
        principal.setLocationRelativeTo(null);
    }//GEN-LAST:event_control_de_calidadActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed


        FrmActualizarDatosJefe principal=new FrmActualizarDatosJefe();
        principal.setVisible(true);
        principal.setLocationRelativeTo(null);


    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed

        FrmContraJefe principal=new FrmContraJefe();
        principal.setVisible(true);
        principal.setLocationRelativeTo(null);
        
        
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed

       confirmarsalida();
        
        
    }//GEN-LAST:event_jMenuItem3ActionPerformed

     public  void cerrar(){
               try {
                   this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                   addWindowListener(new WindowAdapter() {
                       public  void windowClosing(WindowEvent e){
                           confirmarsalida();
                            
                       }
});
                   this.setVisible(true);
               } catch (Exception e) {
                   e.printStackTrace();
               }
           }
         
           public void confirmarsalida(){
               int valor=JOptionPane.showConfirmDialog(this, "¿ESTAS SEGURO DE CERRAR EL PROGRAMA DE GESTION DEL PROYECTO?","Advertencia",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
        if(valor==JOptionPane.YES_OPTION){
            JOptionPane.showMessageDialog(null, "CERRANDO EL PROGRAMA","Mensaje",JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
          
            
        }
           }
    
   
    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MenuAdministrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuAdministrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuAdministrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuAdministrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
       

     
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuAdministrador().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu MENUAJUSTES;
    public javax.swing.JMenu MENUUSUARIO;
    private javax.swing.JMenuItem actividades;
    private javax.swing.JMenuBar barra;
    private javax.swing.JMenuItem cambios;
    private javax.swing.JMenuItem colaborador;
    private javax.swing.JMenuItem control_de_calidad;
    private javax.swing.JMenuItem interesados;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jefe;
    private javax.swing.JLabel lbHora;
    public static javax.swing.JLabel lblusuario;
    private javax.swing.JMenuItem nuevo;
    private javax.swing.JMenuItem objetivo;
    private javax.swing.JMenuItem problema;
    private javax.swing.JMenuItem requisito;
    private javax.swing.JMenuItem reunion;
    private javax.swing.JMenuItem riesgos;
    private javax.swing.JMenuItem solucion;
    // End of variables declaration//GEN-END:variables


    @Override
    public void run() {
        Thread ct = Thread.currentThread();
        while (ct == h1) {
            calcula();
            lbHora.setText(hora + ":" + minutos + ":" + segundos + " " + ampm);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        }
    }

    public void calcula() {
        Calendar calendario = new GregorianCalendar();
        Date fechaHoraActual = new Date();


        calendario.setTime(fechaHoraActual);
        ampm = calendario.get(Calendar.AM_PM) == Calendar.AM ? "AM" : "PM";

        if (ampm.equals("PM")) {
            int h = calendario.get(Calendar.HOUR_OF_DAY) - 12;
            hora = h > 9 ? "" + h : "0" + h;
        } else {
            hora = calendario.get(Calendar.HOUR_OF_DAY) > 9 ? "" + calendario.get(Calendar.HOUR_OF_DAY) : "0" + calendario.get(Calendar.HOUR_OF_DAY);
        }
        minutos = calendario.get(Calendar.MINUTE) > 9 ? "" + calendario.get(Calendar.MINUTE) : "0" + calendario.get(Calendar.MINUTE);
        segundos = calendario.get(Calendar.SECOND) > 9 ? "" + calendario.get(Calendar.SECOND) : "0" + calendario.get(Calendar.SECOND);
    }
//cambiar icno del jframe
    @Override
public Image getIconImage() {
   Image retValue = Toolkit.getDefaultToolkit().
         getImage(ClassLoader.getSystemResource("img/iconojframe.png"));


   return retValue;
}


}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author Home
 */
public class IMAGEN extends JPanel{
     
       @Override
       public void paintComponent(Graphics g){
        Dimension tam=getSize();
        ImageIcon imagen=new  ImageIcon(new ImageIcon(getClass().getResource("../imagenes/PRINCIPAL.jpg")).getImage());
        g.drawImage(imagen.getImage(), 0, 0, tam.width,tam.height,null);
    }
}

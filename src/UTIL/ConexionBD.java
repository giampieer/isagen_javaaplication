/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UTIL;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;

/**
 *
 * @author Home
 */
public class ConexionBD {
   public static Connection getConexionBD(){
       Connection cn=null;
       try {
                  Class.forName("com.mysql.jdbc.Driver");
  //cn=DriverManager.getConnection("jdbc:mysql://mysql128019-gestion.jelasticlw.com.br/relacion","root","EVVlts56638");
cn=DriverManager.getConnection("jdbc:mysql://localhost/relacion","root","");
           System.out.println("se conecto");
           
       } catch (Exception e) {
           
           System.out.println("no se conecto");
       }
       return cn;
       
   }
   
   
    public static void main(String[] args) {
        ConexionBD cn=new ConexionBD();
        cn.getConexionBD();
    }
}

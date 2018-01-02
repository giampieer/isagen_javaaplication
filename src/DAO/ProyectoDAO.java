/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import BEAN.ProyectoBean;
import UTIL.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Home
 */
public class ProyectoDAO {
    Connection cn=null;
         ProyectoBean objbean=null;
         ArrayList lista=null;
         PreparedStatement pt =null;
         ResultSet rs=null;
       //SINGLETON
          private static ProyectoDAO unicaInstancia;
    public static ProyectoDAO getInstancia(){
        if(unicaInstancia==null)
            unicaInstancia=new ProyectoDAO();
        return unicaInstancia;
    }
         //combobox para requisito
         public ArrayList<ProyectoBean> ProyectodeRequisito(){
        try {
         
            ConexionBD objc=new ConexionBD();
            cn=objc.getConexionBD();
            pt=cn.prepareStatement("select * from proy pr  left join requisito r on pr.numero=r.numproy where r.numproy is null");
            rs=pt.executeQuery();
            lista=new ArrayList<ProyectoBean>();
            while(rs.next()){
                objbean=new ProyectoBean();
                objbean.setNumero(rs.getInt(1));
                objbean.setTitulo(rs.getString(2));
                objbean.setDuracion(rs.getString(3));
                objbean.setDescripcion(rs.getString(4));
                objbean.setTipo(rs.getString(5));
                objbean.setFases(rs.getString(6));
                objbean.setInicio(rs.getString(7));
                objbean.setFin(rs.getString(8));
                objbean.setGastos(rs.getString(9));
                lista.add(objbean);
            }
            rs.close();
            pt.close();
            cn.close();
        } catch (Exception e) {
             
         }
         return lista;
         
         
         
     }
         public ArrayList<ProyectoBean> ProyectodeProblema(){
        try {
         
            ConexionBD objc=new ConexionBD();
            cn=objc.getConexionBD();
            pt=cn.prepareStatement("select * from proy pr  left join problema p on pr.numero=p.numproy where p.numproy is null");
            rs=pt.executeQuery();
            lista=new ArrayList<ProyectoBean>();
            while(rs.next()){
                objbean=new ProyectoBean();
                objbean.setNumero(rs.getInt(1));
                objbean.setTitulo(rs.getString(2));
                objbean.setDuracion(rs.getString(3));
                objbean.setDescripcion(rs.getString(4));
                objbean.setTipo(rs.getString(5));
                objbean.setFases(rs.getString(6));
                objbean.setInicio(rs.getString(7));
                objbean.setFin(rs.getString(8));
                objbean.setGastos(rs.getString(9));
                lista.add(objbean);
            }
            rs.close();
            pt.close();
            cn.close();
        } catch (Exception e) {
             
         }
         return lista;
         
         
         
     }
         public ArrayList<ProyectoBean> ProyectodeObjetivo(){
        try {
         
            ConexionBD objc=new ConexionBD();
            cn=objc.getConexionBD();
            pt=cn.prepareStatement("select * from proy pr  left join objetivo o on pr.numero=o.numproy where o.numproy is null");
            rs=pt.executeQuery();
            lista=new ArrayList<ProyectoBean>();
            while(rs.next()){
                objbean=new ProyectoBean();
                objbean.setNumero(rs.getInt(1));
                objbean.setTitulo(rs.getString(2));
                objbean.setDuracion(rs.getString(3));
                objbean.setDescripcion(rs.getString(4));
                objbean.setTipo(rs.getString(5));
                objbean.setFases(rs.getString(6));
                objbean.setInicio(rs.getString(7));
                objbean.setFin(rs.getString(8));
                objbean.setGastos(rs.getString(9));
                lista.add(objbean);
            }
            rs.close();
            pt.close();
            cn.close();
        } catch (Exception e) {
             
         }
         return lista;
         
         
         
     }
         
         
         
         
         
         
         
     
     
   
  
     public ArrayList<ProyectoBean> cargartablaproyecto(){
        try {
         
            ConexionBD objc=new ConexionBD();
            cn=objc.getConexionBD();
            pt=cn.prepareStatement("select p.numero,p.titulo,p.duracion,p.descripcion,p.tipo,p.fases,p.inicio,p.fin,p.gastos,j.nombjefe from proy p inner join jefe j on p.codjefe=j.codjefe");
            rs=pt.executeQuery();
            lista=new ArrayList<ProyectoBean>();
            while(rs.next()){
                objbean=new ProyectoBean();
                objbean.setNumero(rs.getInt(1));
                objbean.setTitulo(rs.getString(2));
                objbean.setDuracion(rs.getString(3));
                objbean.setDescripcion(rs.getString(4));
                objbean.setTipo(rs.getString(5));
                objbean.setFases(rs.getString(6));
                objbean.setInicio(rs.getString(7));
                objbean.setFin(rs.getString(8));
                objbean.setGastos(rs.getString(9));
                objbean.setNOMBJEFE(rs.getString(10));
                lista.add(objbean);
            }
            rs.close();
            pt.close();
            cn.close();
        } catch (Exception e) {
             
         }
         return lista;
     }
      public ArrayList<ProyectoBean> cargartablaproyecto1(){
        try {
         
            ConexionBD objc=new ConexionBD();
            cn=objc.getConexionBD();
            pt=cn.prepareStatement("select p.numero,p.titulo,p.duracion,p.descripcion,p.tipo,p.fases,p.inicio,p.fin,p.gastos,p.codjefe from proy p inner join jefe j on p.codjefe=j.codjefe");
            rs=pt.executeQuery();
            lista=new ArrayList<ProyectoBean>();
            while(rs.next()){
                objbean=new ProyectoBean();
                objbean.setNumero(rs.getInt(1));
                objbean.setTitulo(rs.getString(2));
                objbean.setDuracion(rs.getString(3));
                objbean.setDescripcion(rs.getString(4));
                objbean.setTipo(rs.getString(5));
                objbean.setFases(rs.getString(6));
                objbean.setInicio(rs.getString(7));
                objbean.setFin(rs.getString(8));
                objbean.setGastos(rs.getString(9));
                objbean.setCODJEFE(rs.getInt(10));
                lista.add(objbean);
            }
            rs.close();
            pt.close();
            cn.close();
        } catch (Exception e) {
             
         }
         return lista;
     }
     
     
     
     public int eliminarproyecto(ProyectoBean objbean){
        int i=0;
        try {
        
            cn=ConexionBD.getConexionBD();
            pt=cn.prepareStatement("delete from proy where numero=?");
            pt.setInt(1,objbean.getNumero());
            
            i=pt.executeUpdate();
            pt.close();
            cn.close();
        } catch (Exception e) {
             i=0;
         }
         return  i;
         
     }
     
     public int grabarproyecto(ProyectoBean objbean){
         int i=0;
         try {
             cn=ConexionBD.getConexionBD();
             pt=cn.prepareStatement("insert into proy values (?,?,?,?,?,?,?,?,?,?)");
             pt.setInt(1, objbean.getNumero());
             pt.setString(2, objbean.getTitulo());
             pt.setString(3, objbean.getDuracion());
             pt.setString(4, objbean.getDescripcion());
               pt.setString(5, objbean.getTipo());
             pt.setString(6, objbean.getFases());
             pt.setString(7, objbean.getInicio());
               pt.setString(8, objbean.getFin());
             pt.setString(9, objbean.getGastos());
             pt.setInt(10,objbean.getCODJEFE());
      i=pt.executeUpdate();
      pt.close();
      cn.close();
         } catch (Exception e) {
             i=0;
         }
         return i;
     }
     
     public int modificarproyecto(ProyectoBean objbean){
         int i=0;
         try {
             cn=ConexionBD.getConexionBD();
             pt=cn.prepareStatement("update proy set titulo=? ,duracion=?, descripcion=?,tipo=?, fases=?, inicio=?, fin=? ,gastos=?,codjefe=? where numero=?;");

             pt.setString(1,objbean.getTitulo());
                 pt.setString(2, objbean.getDuracion());
                pt.setString(3, objbean.getDescripcion());
            pt.setString(4, objbean.getTipo());
            pt.setString(5, objbean.getFases());
             pt.setString(6, objbean.getInicio());
              pt.setString(7, objbean.getFin());
              pt.setString(8, objbean.getGastos());
              pt.setInt(9, objbean.getCODJEFE());
               pt.setInt(10, objbean.getNumero());
             i=pt.executeUpdate();
             pt.close();
             cn.close();
        } catch (Exception e) {
             i=0;
         }
         return i;
         
     }
     
     public ProyectoBean CapturarProyecto(ProyectoBean obj)
    {
        ProyectoBean objeto=null;
        try 
        {
            cn  =  ConexionBD.getConexionBD();
            pt  = cn.prepareStatement("select p.numero,p.titulo,p.duracion,p.descripcion,p.tipo,p.fases,p.inicio,p.fin,p.gastos,j.nombjefe,p.codjefe from proy p inner join jefe j on p.codjefe=j.codjefe where p.numero=?");
            pt.setInt(1, obj.getNumero());
            rs=pt.executeQuery();
            while(rs.next())
            {
                objeto=new ProyectoBean();
                objeto.setNumero(rs.getInt(1));
                objeto.setTitulo(rs.getString(2));
                objeto.setDuracion(rs.getString(3));
                objeto.setDescripcion(rs.getString(4));
                objeto.setTipo(rs.getString(5));
                objeto.setFases(rs.getString(6));
                objeto.setInicio(rs.getString(7));
                objeto.setFin(rs.getString(8));
                objeto.setGastos(rs.getString(9));
                objeto.setNOMBJEFE(rs.getString(10));
                objeto.setCODJEFE(rs.getInt(11));
            }
            pt.close();
            rs.close();
            cn.close();
        } catch (Exception e) 
        {
        }
        return objeto;
    }
      public int generarCodigo() {
        int CODIGO = 0;
        try {
              cn  =  ConexionBD.getConexionBD();
              pt  = cn.prepareStatement("SELECT MAX(NUMERO) FROM proy ");  
              rs=pt.executeQuery();
              rs.next();
              int c=Integer.parseInt(rs.getString(1))+1;
              String id="";
              if(c<10000000){id=""+c;}
              CODIGO=Integer.parseInt(id);
        } catch (Exception e) {
        }
        return CODIGO;
    } 
    
    
    
    
    
}

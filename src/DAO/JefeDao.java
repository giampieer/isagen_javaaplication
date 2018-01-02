
package DAO;

import BEAN.JefeBean;
import UTIL.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.naming.spi.DirStateFactory;

public class JefeDao {

          
          Connection cn=null;
        
        PreparedStatement pt=null;
        ResultSet rs=null;
        ArrayList<JefeBean> lista=null;
        JefeBean objjefebean=null;
        
        
        
    public int Login (JefeBean obj){
  int estado=0;
        try {
            ConexionBD objc=new ConexionBD();
            cn=objc.getConexionBD();
            pt=cn.prepareStatement("select COUNT(*) from jefe where ID=? and PASS=?");
            pt.setString(1,obj.getID());
            pt.setString(2, obj.getPASS());
            rs=pt.executeQuery();
 
            while (rs.next()) {                
                estado=rs.getInt(1);
               
            }
            pt.close();
            rs.close();
            cn.close();
            
            
            
        } catch (Exception e) {
            estado=0;
        }
        return  estado;
        
    }
    
    public ArrayList<JefeBean>Listarjefe(){
        try {
            ConexionBD objc=new ConexionBD();
            cn=objc.getConexionBD();
            pt=cn.prepareStatement("select * from jefe");
            rs=pt.executeQuery();
            lista=new ArrayList<JefeBean>();
            while(rs.next()){
                objjefebean=new JefeBean();
                objjefebean.setCODJEFE(rs.getInt(1));
                objjefebean.setNOMBJEFE(rs.getString(2));
                objjefebean.setEMAJEFE(rs.getString(3));
                objjefebean.setTELFJEFE(rs.getString(4));
                objjefebean.setAREAJEFE(rs.getString(5));
                objjefebean.setID(rs.getString(6));
                objjefebean.setPASS(rs.getString(7));
                lista.add(objjefebean);
                
                
                
            }
            cn.close();
            pt.close();
            rs.close();
        } catch (Exception e) {
            
        }
        return lista;
        
    }
      //combobox en proyecto
   public ArrayList<JefeBean> ListarJefedeProyecto() {
        try {
             cn=ConexionBD.getConexionBD();// conexion BD
             pt=cn.prepareStatement("select * from jefe j  left join proy pr on pr.codjefe=j.codjefe where pr.codjefe is null");
             rs=pt.executeQuery();
             lista = new ArrayList<JefeBean>();
             while(rs.next()){
                 objjefebean = new JefeBean();
                 objjefebean.setCODJEFE(rs.getInt("codjefe"));
                 objjefebean.setNOMBJEFE(rs.getString("nombjefe"));
                 objjefebean.setEMAJEFE(rs.getString("emajefe"));
                 objjefebean.setTELFJEFE(rs.getString("telfjefe"));
                 objjefebean.setAREAJEFE(rs.getString("areajefe"));
                 objjefebean.setID(rs.getString("ID"));
                 objjefebean.setPASS(rs.getString("PASS"));
                 
                 lista.add(objjefebean);
             }
             pt.close();
             rs.close();
             cn.close();
        } catch (Exception e) {
             
        }
        return lista;
    }
    
    
    public int insertarjefe(JefeBean obj){
        int estado=0;
        try {
            ConexionBD objc=new ConexionBD();
            cn=objc.getConexionBD();
            pt=cn.prepareStatement("insert into jefe values(?,?,?,?,?,?,?)");
            pt.setInt(1, obj.getCODJEFE());
            pt.setString(2, obj.getNOMBJEFE());
            pt.setString(3, obj.getEMAJEFE());
            pt.setString(4, obj.getTELFJEFE());
            pt.setString(5, obj.getAREAJEFE());
            pt.setString(6, obj.getID());
            pt.setString(7, obj.getPASS());
            estado=pt.executeUpdate();
            pt.close();
            cn.close();
        } catch (Exception e) {
        estado=0;
        }
        return estado;
        
    }
    
    public int modificarjefe(JefeBean obj){
        int estado=0;
        
        try {
            ConexionBD objc=new ConexionBD();
        cn=objc.getConexionBD();
        pt=cn.prepareStatement("update jefe set nombjefe=?,emajefe=?,telfjefe=?,areajefe=?,id=?,pass=? where codjefe=?");
         
            pt.setString(1, obj.getNOMBJEFE());
            pt.setString(2, obj.getEMAJEFE());
            pt.setString(3, obj.getTELFJEFE());
            pt.setString(4, obj.getAREAJEFE());
            pt.setString(5, obj.getID());
            pt.setString(6, obj.getPASS());
            pt.setInt(7, obj.getCODJEFE());
            estado=pt.executeUpdate();
            pt.close();
            cn.close();
        
        
        } catch (Exception e) {
             estado=0;
        }
        return estado;
        
    }
    public int eliminarjefe(JefeBean obj){
        int estado=0;
                try {
            ConexionBD objc=new ConexionBD();
            cn=objc.getConexionBD();
            pt=cn.prepareStatement("delete from jefe where codjefe=?");
            pt.setInt(1, obj.getCODJEFE());
            estado=pt.executeUpdate();
                    cn.close();
                    pt.close();
        } catch (Exception e) {
            estado=0;
        }
                return estado;
        
    }
         public JefeBean CapturarCodigo(JefeBean obj)
    {
        JefeBean objeto=null;
        try 
        {
            cn  =  ConexionBD.getConexionBD();
            pt  = cn.prepareStatement("select * from jefe where codjefe=?;");
            pt.setInt(1, obj.getCODJEFE());
            rs=pt.executeQuery();
            while(rs.next())
            {
                objeto=new JefeBean();
                objeto.setCODJEFE(rs.getInt(1));
                objeto.setNOMBJEFE(rs.getString(2));
                objeto.setEMAJEFE(rs.getString(3));
                objeto.setTELFJEFE(rs.getString(4));
                objeto.setAREAJEFE(rs.getString(5));
                objeto.setID(rs.getString(6));
                objeto.setPASS(rs.getString(7));
                
                
            }
            pt.close();
            rs.close();
            cn.close();
        } catch (Exception e) 
        {
        }
        return objeto;
    }
     //seguridad
      public JefeBean capturarDatosUsuario(JefeBean objUsuBean){
        JefeBean  objeto=null;
        try {
            cn=ConexionBD.getConexionBD();
            pt=cn.prepareStatement("select * from jefe where ID=? and PASS=?;");
            pt.setString(1, objUsuBean.getID());
            pt.setString(2, objUsuBean.getPASS());
           
            rs=pt.executeQuery();
            while(rs.next()){
               
                objeto=new JefeBean();
                objeto.setCODJEFE(rs.getInt("codjefe"));
                objeto.setNOMBJEFE(rs.getString("nombjefe"));
                objeto.setEMAJEFE(rs.getString("emajefe"));
                objeto.setTELFJEFE(rs.getString("telfjefe"));
                objeto.setAREAJEFE(rs.getString("areajefe"));
                objeto.setID(rs.getString("id"));
                objeto.setPASS(rs.getString("pass"));
            }
            pt.close();
            rs.close();
            cn.close();
        } catch (Exception e) {
        }
        return objeto;
    } 
     public int generarCodigo() {
        int CODIGO = 0;
        try {
              cn  =  ConexionBD.getConexionBD();
              pt  = cn.prepareStatement("SELECT MAX(CODJEFE) FROM jefe ");  
              rs=pt.executeQuery();
              rs.next();
              int c=Integer.parseInt(rs.getString(1))+1;
              String id="";
              if(c<10000000){id=""+c;}
              CODIGO=Integer.parseInt(id);
              cn.close();
              pt.close();
        } catch (Exception e) {
        }
        return CODIGO;
    } 
    

}

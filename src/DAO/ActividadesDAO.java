
package DAO;

import BEAN.ActividadesBean;
import UTIL.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ActividadesDAO {
         Connection cn=null;
         ActividadesBean objbean=null;
         ArrayList lista=null;
         PreparedStatement pt =null;
         ResultSet rs=null;
         
         
         
         
         
         //personal
          public ArrayList<ActividadesBean> cargartablaactividadesPersonal(ActividadesBean obj){
        try {
         
            ConexionBD objc=new ConexionBD();
            cn=objc.getConexionBD();
            pt=cn.prepareStatement("select a.numero,a.actividad,a.duracion,a.reponsable,a.numproy,pr.titulo from actividades a inner join proy pr on a.numproy=pr.numero where pr.numero='"+obj.getNUMPROY()+"'");
            rs=pt.executeQuery();
            lista=new ArrayList<ActividadesBean>();
            while(rs.next()){
                objbean=new ActividadesBean();
                objbean.setNumero(rs.getInt(1));
                objbean.setActividad(rs.getString(2));
                objbean.setDuracion(rs.getString(3));
                objbean.setResponsable(rs.getString(4));
                objbean.setNUMPROY(rs.getInt(5));
                objbean.setNOMBPROY(rs.getString(6));
            
                lista.add(objbean);
            }
            rs.close();
            pt.close();
            cn.close();
        } catch (Exception e) {
             
         }
         return lista;
         
         
         
     }
         
         
     
     public ArrayList<ActividadesBean> cargartablaactividades1(){
        try {
         
            ConexionBD objc=new ConexionBD();
            cn=objc.getConexionBD();
            pt=cn.prepareStatement("select a.numero,a.actividad,a.duracion,a.reponsable,a.numproy,pr.titulo from actividades a inner join proy pr on pr.numero=a.numproy");
            rs=pt.executeQuery();
            lista=new ArrayList<ActividadesBean>();
            while(rs.next()){
                objbean=new ActividadesBean();
                objbean.setNumero(rs.getInt(1));
                objbean.setActividad(rs.getString(2));
                objbean.setDuracion(rs.getString(3));
                objbean.setResponsable(rs.getString(4));
                objbean.setNUMPROY(rs.getInt(5));
                objbean.setNOMBPROY(rs.getString(6));
            
                lista.add(objbean);
            }
            rs.close();
            pt.close();
            cn.close();
        } catch (Exception e) {
             
         }
         return lista;
         
         
         
     }
     
     public int eliminaractividades(ActividadesBean objbean){
        int i=0;
        try {
        
            ConexionBD objc=new ConexionBD();
            cn=objc.getConexionBD();
            pt=cn.prepareStatement("delete from actividades where numero=?");
            pt.setInt(1,objbean.getNumero());
            
            i=pt.executeUpdate();
            pt.close();
            cn.close();
        } catch (Exception e) {
             i=0;
         }
         return  i;
         
     }
     
     public int grabaractividades(ActividadesBean objbean){
         int i=0;
         try {
             ConexionBD objc=new ConexionBD();
             cn=objc.getConexionBD();
             pt=cn.prepareStatement("insert into actividades values (?,?,?,?,?)");
             pt.setInt(1, objbean.getNumero());
             pt.setString(2, objbean.getActividad());
             pt.setString(3, objbean.getDuracion());
             pt.setString(4, objbean.getResponsable());
             pt.setInt(5, objbean.getNUMPROY());
       
      i=pt.executeUpdate();
      pt.close();
      cn.close();
         } catch (Exception e) {
             i=0;
         }
         return i;
     }
     
     public int modificaractividades(ActividadesBean objbean){
         int i=0;
         try {
             ConexionBD objc=new ConexionBD();
             cn=objc.getConexionBD();
             pt=cn.prepareStatement("update actividades set actividad=? ,duracion=?, reponsable=?,numproy=?  where numero=?;");

             pt.setString(1,objbean.getActividad());
             pt.setString(2, objbean.getDuracion());
             pt.setString(3, objbean.getResponsable());
             pt.setInt(4, objbean.getNUMPROY());
             pt.setInt(5, objbean.getNumero());
     
             i=pt.executeUpdate();
             pt.close();
             cn.close();
        } catch (Exception e) {
             i=0;
         }
         return i;
         
     }
     
     public ActividadesBean CapturarActividades(ActividadesBean obj)
    {
        ActividadesBean objeto=null;
        try 
        {
            cn  =  ConexionBD.getConexionBD();
            pt  = cn.prepareStatement("select a.numero,a.actividad,a.duracion,a.reponsable,a.numproy,pr.titulo from actividades a inner join proy pr on a.numproy=pr.numero where a.numero=?");
            pt.setInt(1, obj.getNumero());
            rs=pt.executeQuery();
            while(rs.next())
            {
                objeto=new ActividadesBean();
                objeto.setNumero(rs.getInt(1));
                objeto.setActividad(rs.getString(2));
                objeto.setDuracion(rs.getString(3));
                objeto.setResponsable(rs.getString(4));
                objeto.setNUMPROY(rs.getInt(5));
                 objeto.setNOMBPROY(rs.getString(6));
          
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
              pt  = cn.prepareStatement("SELECT MAX(NUMERO) FROM actividades ;");  
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

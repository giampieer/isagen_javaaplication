
package DAO;

import BEAN.CambiosBean;
import UTIL.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CambiosDAO {
    
    
    Connection cn=null;
         CambiosBean objbean=null;
         ArrayList lista=null;
         PreparedStatement pt =null;
         ResultSet rs=null;
   //mostrar tablas de cambios del personal      
   public ArrayList<CambiosBean> listarCambiosPersonal(CambiosBean obj){
        try {
         
            cn=ConexionBD.getConexionBD();
            pt=cn.prepareStatement("select c.numero,c.fecha,c.proposito,c.importancia,c.numproy,pr.titulo from cambios c inner join proy pr on pr.numero=c.numproy where c.numproy='"+obj.getNUMPROY()+"'");
            rs=pt.executeQuery();
            lista=new ArrayList<CambiosBean>();
            while(rs.next()){
                objbean=new CambiosBean();
                objbean.setNUMERO(rs.getInt(1));
                objbean.setFECHA(rs.getString(2));
                objbean.setPROPOSITO(rs.getString(3));
                objbean.setIMPORTANCIA(rs.getString(4));
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
       public ArrayList<CambiosBean> listarCambios1(){
        try {
         
            cn=ConexionBD.getConexionBD();
            pt=cn.prepareStatement("select c.numero,c.fecha,c.proposito,c.importancia,c.numproy,pr.titulo from cambios c inner join proy pr on pr.numero=c.numproy");
            rs=pt.executeQuery();
            lista=new ArrayList<CambiosBean>();
            while(rs.next()){
                objbean=new CambiosBean();
                objbean.setNUMERO(rs.getInt(1));
                objbean.setFECHA(rs.getString(2));
                objbean.setPROPOSITO(rs.getString(3));
                objbean.setIMPORTANCIA(rs.getString(4));
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
     
     public int eliminarCambios(CambiosBean objbean){
        int i=0;
        try {
        
            cn=ConexionBD.getConexionBD();
            pt=cn.prepareStatement("delete from cambios where numero=?");
            pt.setInt(1,objbean.getNUMERO());
            
            i=pt.executeUpdate();
            pt.close();
            cn.close();
        } catch (Exception e) {
             i=0;
         }
         return  i;
         
     }
     
     public int grabarCambios(CambiosBean objbean){
         int i=0;
         try {
             cn=ConexionBD.getConexionBD();
             pt=cn.prepareStatement("insert into cambios values (?,?,?,?,?)");
             pt.setInt(1, objbean.getNUMERO());
             pt.setString(2, objbean.getFECHA());
             pt.setString(3, objbean.getPROPOSITO());
             pt.setString(4, objbean.getIMPORTANCIA());
             pt.setInt(5, objbean.getNUMPROY());
             
      i=pt.executeUpdate();
      pt.close();
      cn.close();
         } catch (Exception e) {
             i=0;
         }
         return i;
     }
     
     public int modificarCambio(CambiosBean objbean){
         int i=0;
         try {
             cn=ConexionBD.getConexionBD();
             pt=cn.prepareStatement("update cambios set fecha=?,proposito=?,importancia=?,numproy=? where numero=?");

             pt.setString(1,objbean.getFECHA());
             pt.setString(2, objbean.getPROPOSITO());
             pt.setString(3, objbean.getIMPORTANCIA());
             pt.setInt(4, objbean.getNUMPROY());
             pt.setInt(5, objbean.getNUMERO());
            
             i=pt.executeUpdate();
             pt.close();
             cn.close();
        } catch (Exception e) {
             i=0;
         }
         return i;
         
     }
     
     public CambiosBean CapturarCodigo(CambiosBean obj)
    {
        CambiosBean objeto=null;
        try 
        {
            cn  =  ConexionBD.getConexionBD();
            pt  = cn.prepareStatement("select c.numero,c.fecha,c.proposito,c.importancia,c.numproy,pr.titulo from cambios c inner join proy pr on pr.numero=c.numproy where c.numero=?;");
            pt.setInt(1, obj.getNUMERO());
            rs=pt.executeQuery();
            while(rs.next())
            {
                objeto=new CambiosBean();
                objeto.setNUMERO(rs.getInt(1));
                objeto.setFECHA(rs.getString(2));
                objeto.setPROPOSITO(rs.getString(3));
                objeto.setIMPORTANCIA(rs.getString(4));
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
              pt  = cn.prepareStatement("SELECT MAX(NUMERO) FROM cambios ;");  
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


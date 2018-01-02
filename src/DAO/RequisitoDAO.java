
package DAO;

import BEAN.ProyectoBean;
import BEAN.RequisitoBean;
import UTIL.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class RequisitoDAO {
     
    Connection cn=null;
         RequisitoBean objbean=null;
         ArrayList lista=null;
         PreparedStatement pt =null;
         ResultSet rs=null;
     
     public ArrayList<RequisitoBean> cargartablarequisito(){
        try {
         
            ConexionBD objc=new ConexionBD();
            cn=objc.getConexionBD();
            pt=cn.prepareStatement("select r.numero,r.alcance,r.personal,r.reuniones,r.descripcion,p.titulo,r.numproy from requisito r inner join proy p on p.numero=r.numproy");
            rs=pt.executeQuery();
            lista=new ArrayList<RequisitoDAO>();
            while(rs.next()){
                objbean=new RequisitoBean();
                objbean.setNumero(rs.getInt(1));
                objbean.setAlcance(rs.getString(2));
                objbean.setPersonal(rs.getInt(3));
                objbean.setReunion(rs.getInt(4));
                objbean.setDescripcion(rs.getString(5));
                objbean.setNOMBPROY(rs.getString(6));
                objbean.setNUMPROY(rs.getInt(7));
                lista.add(objbean);
            }
            rs.close();
            pt.close();
            cn.close();
        } catch (Exception e) {
             
         }
         return lista;
         
         
         
     }
     
     public int eliminarRrequisito(RequisitoBean objbean){
        int i=0;
        try {
        
            ConexionBD objc=new ConexionBD();
            cn=objc.getConexionBD();
            pt=cn.prepareStatement("delete from requisito where numero=?");
            pt.setInt(1,objbean.getNumero());
            
            i=pt.executeUpdate();
            pt.close();
            cn.close();
        } catch (Exception e) {
             i=0;
         }
         return  i;
         
     }
     
     public int grabarRequisito(RequisitoBean objbean){
         int i=0;
         try {
             ConexionBD objc=new ConexionBD();
             cn=objc.getConexionBD();
             pt=cn.prepareStatement("insert into requisito values (?,?,?,?,?,?)");
             pt.setInt(1, objbean.getNumero());
             pt.setString(2, objbean.getAlcance());
             pt.setInt(3, objbean.getPersonal());
             pt.setInt(4, objbean.getReunion());
               pt.setString(5, objbean.getDescripcion());
             pt.setInt(6, objbean.getNUMPROY());
      i=pt.executeUpdate();
      pt.close();
      cn.close();
         } catch (Exception e) {
             i=0;
         }
         return i;
     }
     
     public int modificarRequisito(RequisitoBean objbean){
         int i=0;
         try {
             ConexionBD objc=new ConexionBD();
             cn=objc.getConexionBD();
             pt=cn.prepareStatement("update requisito set alcance=? ,personal=?, reuniones=?,descripcion=?,numproy=?  where numero=?;");

             pt.setString(1,objbean.getAlcance());
                 pt.setInt(2, objbean.getPersonal());
                pt.setInt(3, objbean.getReunion());
            pt.setString(4, objbean.getDescripcion());
             pt.setInt(5, objbean.getNUMPROY());
             pt.setInt(6, objbean.getNumero());

             i=pt.executeUpdate();
             pt.close();
             cn.close();
        } catch (Exception e) {
             i=0;
         }
         return i;
         
     }
     
     public RequisitoBean CapturarRequisito(RequisitoBean obj)
    {
        RequisitoBean objeto=null;
        try 
        {
            cn  =  ConexionBD.getConexionBD();
            pt  = cn.prepareStatement("select r.numero,r.alcance,r.personal,r.reuniones,r.descripcion,r.numproy,p.titulo from requisito r inner join proy p on p.numero=r.numproy where r.numero=?;");
            pt.setInt(1, obj.getNumero());
            rs=pt.executeQuery();
            while(rs.next())
            {
                objeto=new RequisitoBean();
                objeto.setNumero(rs.getInt(1));
                objeto.setAlcance(rs.getString(2));
                objeto.setPersonal(rs.getInt(3));
                objeto.setReunion(rs.getInt(4));
                objeto.setDescripcion(rs.getString(5));
                objeto.setNUMPROY(rs.getInt(6));
                objeto.setNOMBPROY(rs.getString(7));
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
              pt  = cn.prepareStatement("SELECT MAX(NUMERO) FROM requisito ");  
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

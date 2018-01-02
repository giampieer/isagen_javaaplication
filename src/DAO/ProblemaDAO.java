
package DAO;

import BEAN.ProblemaBean;
import BEAN.ProyectoBean;
import UTIL.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ProblemaDAO {
    Connection cn=null;
         ProblemaBean objbean=null;
         ArrayList lista=null;
         PreparedStatement pt =null;
         ResultSet rs=null;
     
     public ArrayList<ProblemaBean> cargartablaproblema1(){
        try {
         
            ConexionBD objc=new ConexionBD();
            cn=objc.getConexionBD();
            pt=cn.prepareStatement("select p.numero,p.nivel,p.descripcion,p.perjudicado,p.numproy,pr.titulo from problema p inner join proy pr on p.numproy=pr.numero ");
            rs=pt.executeQuery();
            lista=new ArrayList<ProblemaBean>();
            while(rs.next()){
                objbean=new ProblemaBean();
                objbean.setNumero(rs.getInt(1));
                objbean.setNivel(rs.getString(2));
                objbean.setDescripcion(rs.getString(3));
                objbean.setPerjudicado(rs.getString(4));
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
     
     public int eliminarproblema(ProblemaBean objbean){
        int i=0;
        try {
        
            ConexionBD objc=new ConexionBD();
            cn=objc.getConexionBD();
            pt=cn.prepareStatement("delete from problema where numero=?");
            pt.setInt(1,objbean.getNumero());
            
            i=pt.executeUpdate();
            pt.close();
            cn.close();
        } catch (Exception e) {
             i=0;
         }
         return  i;
         
     }
     
     public int grabarproblema(ProblemaBean objbean){
         int i=0;
         try {
             ConexionBD objc=new ConexionBD();
             cn=objc.getConexionBD();
             pt=cn.prepareStatement("insert into problema values (?,?,?,?,?)");
             pt.setInt(1, objbean.getNumero());
             pt.setString(2, objbean.getNivel());
             pt.setString(3, objbean.getDescripcion());
             pt.setString(4, objbean.getPerjudicado());
             pt.setInt(5, objbean.getNUMPROY());
       
      i=pt.executeUpdate();
      pt.close();
      cn.close();
         } catch (Exception e) {
             i=0;
         }
         return i;
     }
     
     public int modificarproblema(ProblemaBean objbean){
         int i=0;
         try {
             ConexionBD objc=new ConexionBD();
             cn=objc.getConexionBD();
             pt=cn.prepareStatement("update problema set nivel=? ,descripcion=?, perjudicado=?,numproy=?  where numero=?;");

             pt.setString(1,objbean.getNivel());
                 pt.setString(2, objbean.getDescripcion());
                pt.setString(3, objbean.getPerjudicado());
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
     
     public ProblemaBean CapturarProblema(ProblemaBean obj)
    {
        ProblemaBean objeto=null;
        try 
        {
            cn  =  ConexionBD.getConexionBD();
            pt  = cn.prepareStatement("select p.numero,p.nivel,p.descripcion,p.perjudicado,p.numproy,pr.titulo from problema p inner join proy pr on p.numproy=pr.numero where p.numero=?");
            pt.setInt(1, obj.getNumero());
            rs=pt.executeQuery();
            while(rs.next())
            {
                objeto=new ProblemaBean();
                objeto.setNumero(rs.getInt(1));
                objeto.setNivel(rs.getString(2));
                objeto.setDescripcion(rs.getString(3));
                objeto.setPerjudicado(rs.getString(4));
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
              pt  = cn.prepareStatement("SELECT MAX(NUMERO) FROM problema ");  
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

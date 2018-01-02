
package DAO;

import BEAN.ObjetivoBean;
import BEAN.ProblemaBean;
import UTIL.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ObjetivoDAO {
    Connection cn=null;
         ObjetivoBean objbean=null;
         ArrayList lista=null;
         PreparedStatement pt =null;
         ResultSet rs=null;
         
     
           
     public ArrayList<ObjetivoBean> cargartablaobjetivo1(){
        try {
         
            ConexionBD objc=new ConexionBD();
            cn=objc.getConexionBD();
            pt=cn.prepareStatement("select o.numero,o.nivel,o.descripcion,o.finalidad ,o.numproy ,pr.titulo from objetivo o  inner join proy pr on o.numproy=pr.numero ");
            rs=pt.executeQuery();
            lista=new ArrayList<ObjetivoBean>();
            while(rs.next()){
                objbean=new ObjetivoBean();
                objbean.setNumero(rs.getInt(1));
                objbean.setNivel(rs.getString(2));
                objbean.setDescripcion(rs.getString(3));
                objbean.setFinalidad(rs.getString(4));
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
     
     public int eliminarobjetivo(ObjetivoBean objbean){
        int i=0;
        try {
        
            ConexionBD objc=new ConexionBD();
            cn=objc.getConexionBD();
            pt=cn.prepareStatement("delete from objetivo where numero=?");
            pt.setInt(1,objbean.getNumero());
            
            i=pt.executeUpdate();
            pt.close();
            cn.close();
        } catch (Exception e) {
             i=0;
         }
         return  i;
         
     }
     
     public int grabarobjetivo(ObjetivoBean objbean){
         int i=0;
         try {
             ConexionBD objc=new ConexionBD();
             cn=objc.getConexionBD();
             pt=cn.prepareStatement("insert into objetivo values (?,?,?,?,?)");
             pt.setInt(1, objbean.getNumero());
             pt.setString(2, objbean.getNivel());
             pt.setString(3, objbean.getDescripcion());
             pt.setString(4, objbean.getFinalidad());
             pt.setInt(5, objbean.getNUMPROY());
       
      i=pt.executeUpdate();
      pt.close();
      cn.close();
         } catch (Exception e) {
             i=0;
         }
         return i;
     }
     
     public int modificarObjetivo(ObjetivoBean objbean){
         int i=0;
         try {
             ConexionBD objc=new ConexionBD();
             cn=objc.getConexionBD();
             pt=cn.prepareStatement("update objetivo set nivel=? ,descripcion=?, finalidad=?,numproy=?  where numero=?;");

             pt.setString(1,objbean.getNivel());
                 pt.setString(2, objbean.getDescripcion());
                pt.setString(3, objbean.getFinalidad());
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
     
     public ObjetivoBean CapturarObjetivo(ObjetivoBean obj)
    {
        ObjetivoBean objeto=null;
        try 
        {
            cn  =  ConexionBD.getConexionBD();
            pt  = cn.prepareStatement("select o.numero,o.nivel,o.descripcion,o.finalidad ,o.numproy ,pr.titulo from objetivo o  inner join proy pr on o.numproy=pr.numero where o.numero=?");
            pt.setInt(1, obj.getNumero());
            rs=pt.executeQuery();
            while(rs.next())
            {
                objeto=new ObjetivoBean();
                objeto.setNumero(rs.getInt(1));
                objeto.setNivel(rs.getString(2));
                objeto.setDescripcion(rs.getString(3));
                objeto.setFinalidad(rs.getString(4));
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
              pt  = cn.prepareStatement("SELECT MAX(NUMERO) FROM objetivo ");  
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

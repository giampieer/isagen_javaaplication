package DAO;

import BEAN.RiesgosBean;
import UTIL.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class RiesgosDAO {
    
    
    Connection cn=null;
         RiesgosBean objbean=null;
         ArrayList lista=null;
         PreparedStatement pt =null;
         ResultSet rs=null;
         
         //PERSONAL
         
          // mostrar tabla en personal
         public ArrayList<RiesgosBean> cargartablariesgos1Personal(RiesgosBean obj){
        try {
         
            ConexionBD objc=new ConexionBD();
            cn=objc.getConexionBD();
            pt=cn.prepareStatement("select r.numero,r.nivel,r.descripcion,r.numproy,pr.titulo from riesgos r inner join proy pr on pr.numero=r.numproy where r.numproy='"+obj.getNUMPROY()+"' ");
            rs=pt.executeQuery();
            lista=new ArrayList<RiesgosBean>();
            while(rs.next()){
                objbean=new RiesgosBean();
                objbean.setNumero(rs.getInt(1));
                objbean.setNivel(rs.getString(2));
                objbean.setDescripción(rs.getString(3));
                objbean.setNUMPROY(rs.getInt(4));
                objbean.setNOMBPROY(rs.getString(5));
                lista.add(objbean);
            }
            rs.close();
            pt.close();
            cn.close();
        } catch (Exception e) {
             
         }
         return lista;
             
     }  
 
//mostrar tabla en riesgos
     public ArrayList<RiesgosBean> cargartablariesgos1(){
        try {
         
            ConexionBD objc=new ConexionBD();
            cn=objc.getConexionBD();
            pt=cn.prepareStatement("select r.numero,r.nivel,r.descripcion,r.numproy,pr.titulo from riesgos r inner join proy pr on pr.numero=r.numproy");
            rs=pt.executeQuery();
            lista=new ArrayList<RiesgosBean>();
            while(rs.next()){
                objbean=new RiesgosBean();
                objbean.setNumero(rs.getInt(1));
                objbean.setNivel(rs.getString(2));
                objbean.setDescripción(rs.getString(3));
                objbean.setNUMPROY(rs.getInt(4));
                objbean.setNOMBPROY(rs.getString(5));
                lista.add(objbean);
            }
            rs.close();
            pt.close();
            cn.close();
        } catch (Exception e) {
             
         }
         return lista;
             
     }
     
     public int eliminarriesgos(RiesgosBean objbean){
        int i=0;
        try {
        
            ConexionBD objc=new ConexionBD();
            cn=objc.getConexionBD();
            pt=cn.prepareStatement("delete from riesgos where numero=?");
            pt.setInt(1,objbean.getNumero());
            
            i=pt.executeUpdate();
            pt.close();
            cn.close();
        } catch (Exception e) {
             i=0;
         }
         return  i;
         
     }
     
     public int grabarriesgos(RiesgosBean objbean){
         int i=0;
         try {
             ConexionBD objc=new ConexionBD();
             cn=objc.getConexionBD();
             pt=cn.prepareStatement("insert into riesgos values (?,?,?,?)");
             pt.setInt(1, objbean.getNumero());
             pt.setString(2, objbean.getNivel());
             pt.setString(3, objbean.getDescripción());
             pt.setInt(4, objbean.getNUMPROY());
  
      i=pt.executeUpdate();
      pt.close();
      cn.close();
         } catch (Exception e) {
             i=0;
         }
         return i;
     }
     
     public int modificarriesgos(RiesgosBean objbean){
         int i=0;
         try {
             ConexionBD objc=new ConexionBD();
             cn=objc.getConexionBD();
             pt=cn.prepareStatement("update riesgos set nivel=? ,descripcion=?,numproy=? where numero=?;");

             pt.setString(1,objbean.getNivel());
             pt.setString(2, objbean.getDescripción());
             pt.setInt(3, objbean.getNUMPROY());
             pt.setInt(4, objbean.getNumero());
             
             i=pt.executeUpdate();
             pt.close();
             cn.close();
        } catch (Exception e) {
             i=0;
         }
         return i;
         
     }
     
     public RiesgosBean CapturarRiesgos(RiesgosBean obj)
    {
        RiesgosBean objeto=null;
        try 
        {
            cn  =  ConexionBD.getConexionBD();
            pt  = cn.prepareStatement("select r.numero,r.nivel,r.descripcion,r.numproy,pr.titulo from riesgos r inner join proy pr on pr.numero=r.numproy where r.numero=?");
            pt.setInt(1, obj.getNumero());
            rs=pt.executeQuery();
            while(rs.next())
            {
                objeto=new RiesgosBean();
                objeto.setNumero(rs.getInt(1));
                objeto.setNivel(rs.getString(2));
                objeto.setDescripción(rs.getString(3));
                objeto.setNUMPROY(rs.getInt(4));
                objeto.setNOMBPROY(rs.getString(5));
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
              pt  = cn.prepareStatement("SELECT MAX(NUMERO) FROM riesgos ");  
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

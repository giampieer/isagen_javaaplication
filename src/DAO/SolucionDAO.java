package DAO;

import BEAN.RiesgosBean;
import BEAN.SolucionBean;
import UTIL.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class SolucionDAO {
     
    Connection cn=null;
         SolucionBean objbean=null;
         ArrayList lista=null;
         PreparedStatement pt =null;
         ResultSet rs=null;
    
         
        
         
         //mostrar tabla de solucion relacionado con el problema
         
         public ArrayList<SolucionBean> cargartablasolucion1Personal(SolucionBean obj){
        try {
         
            ConexionBD objc=new ConexionBD();
            cn=objc.getConexionBD();
            pt=cn.prepareStatement("select s.numero,s.nivel,s.descripcion,s.numriesgo,r.descripcion,r.numproy,pr.titulo from solucion s inner join riesgos r on s.numriesgo=r.numero inner join proy pr on pr.numero=r.numproy where r.numproy='"+obj.getNUMPROY()+"'");
            rs=pt.executeQuery();
            lista=new ArrayList<SolucionBean>();
            while(rs.next()){
                objbean=new SolucionBean();
                objbean.setNumero(rs.getInt(1));
                objbean.setNivel(rs.getString(2));
                objbean.setDescripción(rs.getString(3));
                objbean.setNUMRIESGO(rs.getInt(4));
                objbean.setNOMBRIESGO(rs.getString(5));
                objbean.setNUMPROY(rs.getInt(6));
                objbean.setNOMBPROY(rs.getString(7));
                
                
                lista.add(objbean);
            }
            rs.close();
            pt.close();
            cn.close();
        } catch (Exception e) {
             
         }
         return lista;
 
     }
         
         
    
         public ArrayList<SolucionBean> cargartablasolucion1(){
        try {
         
            ConexionBD objc=new ConexionBD();
            cn=objc.getConexionBD();
            pt=cn.prepareStatement("select s.numero,s.nivel,s.descripcion,s.numriesgo,r.descripcion,r.numproy,pr.titulo from solucion s inner join riesgos r on s.numriesgo=r.numero inner join proy pr on pr.numero=r.numproy ");
            rs=pt.executeQuery();
            lista=new ArrayList<SolucionBean>();
            while(rs.next()){
                objbean=new SolucionBean();
                objbean.setNumero(rs.getInt(1));
                objbean.setNivel(rs.getString(2));
                objbean.setDescripción(rs.getString(3));
                objbean.setNUMRIESGO(rs.getInt(4));
                objbean.setNOMBRIESGO(rs.getString(5));
                objbean.setNUMPROY(rs.getInt(6));
                objbean.setNOMBPROY(rs.getString(7));
                
                lista.add(objbean);
            }
            rs.close();
            pt.close();
            cn.close();
        } catch (Exception e) {
             
         }
         return lista;
 
     }
     
     public int eliminarsolucion(SolucionBean objbean){
        int i=0;
        try {
        
            ConexionBD objc=new ConexionBD();
            cn=objc.getConexionBD();
            pt=cn.prepareStatement("delete from solucion where numero=?");
            pt.setInt(1,objbean.getNumero());
            i=pt.executeUpdate();
            pt.close();
            cn.close();
        } catch (Exception e) {
             i=0;
         }
         return  i;
         
     }
     
     public int grabarsolucion(SolucionBean objbean){
         int i=0;
         try {
             ConexionBD objc=new ConexionBD();
             cn=objc.getConexionBD();
             pt=cn.prepareStatement("insert into solucion values (?,?,?,?)");
             pt.setInt(1, objbean.getNumero());
             pt.setString(2, objbean.getNivel());
             pt.setString(3, objbean.getDescripción());
             pt.setInt(4, objbean.getNUMRIESGO());

      i=pt.executeUpdate();
      pt.close();
      cn.close();
         } catch (Exception e) {
             i=0;
         }
         return i;
     }
     
     public int modificarsolucion(SolucionBean objbean){
         int i=0;
         try {
             ConexionBD objc=new ConexionBD();
             cn=objc.getConexionBD();
             pt=cn.prepareStatement("update solucion set nivel=?,descripcion=?,numriesgo=? where numero=?");

             pt.setString(1, objbean.getNivel());
             pt.setString(2, objbean.getDescripción());
             pt.setInt(3, objbean.getNUMRIESGO());
               pt.setInt(4, objbean.getNumero());
             i=pt.executeUpdate();
             pt.close();
             cn.close();
        } catch (Exception e) {
             i=0;
         }
         return i;
         
     }
     
     public SolucionBean CapturarSolucion(SolucionBean obj)
    {
        SolucionBean objeto=null;
        try 
        {
            cn  =  ConexionBD.getConexionBD();
            pt  = cn.prepareStatement("select s.numero,s.nivel,s.descripcion,s.numriesgo,r.descripcion from solucion s inner join riesgos r on s.numriesgo=r.numero inner join proy pr on pr.numero=r.numproy  where s.numero=?;");
            pt.setInt(1, obj.getNumero());
            rs=pt.executeQuery();
            while(rs.next())
            {
                objeto=new SolucionBean();
                objeto.setNumero(rs.getInt(1));
                objeto.setNivel(rs.getString(2));
                objeto.setDescripción(rs.getString(3));
                objeto.setNUMRIESGO(rs.getInt(4));
                objeto.setNOMBRIESGO(rs.getString(5));
        
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
              pt  = cn.prepareStatement("SELECT MAX(NUMERO) FROM solucion ");  
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

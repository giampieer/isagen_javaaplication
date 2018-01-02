
package DAO;

import BEAN.ControldeCalidadBean;
import BEAN.RiesgosBean;
import UTIL.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ControldeCalidadDAO {
         Connection cn=null;
         ControldeCalidadBean objbean=null;
         ArrayList lista=null;
         PreparedStatement pt =null;
         ResultSet rs=null;
        
         
         
         
         public ArrayList<ControldeCalidadBean> cargartablacontrolcalidadPersonal(ControldeCalidadBean  obj){
        try {
         
            ConexionBD objc=new ConexionBD();
            cn=objc.getConexionBD();
            pt=cn.prepareStatement("select c.numero,c.plandegestion,c.plandemejoradecalidad,c.metricasdecalidad,c.actualizacionesdeladocumentacion,c.numproy,pr.titulo  from controldecalidad c inner join proy pr on pr.numero=c.numproy where c.numproy='"+obj.getNUMPROY()+"'");
            rs=pt.executeQuery();
            lista=new ArrayList<ControldeCalidadBean>();
            while(rs.next()){
                objbean=new ControldeCalidadBean();
                objbean.setNumero(rs.getInt(1));
                objbean.setPlandegestion(rs.getString(2));
                objbean.setPlandemejoradecalidad(rs.getString(3));
                objbean.setMetricasdecalidad(rs.getString(4));
                objbean.setActualizacionesdeladocumentacion(rs.getString(5));
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
        public ArrayList<ControldeCalidadBean> cargartablacontrolcalidad1(){
        try {
         
            ConexionBD objc=new ConexionBD();
            cn=objc.getConexionBD();
            pt=cn.prepareStatement("select c.numero,c.plandegestion,c.plandemejoradecalidad,c.metricasdecalidad,c.actualizacionesdeladocumentacion,c.numproy,pr.titulo from controldecalidad c inner join proy pr on pr.numero=c.numproy");
            rs=pt.executeQuery();
            lista=new ArrayList<ControldeCalidadBean>();
            while(rs.next()){
                objbean=new ControldeCalidadBean();
                objbean.setNumero(rs.getInt(1));
                objbean.setPlandegestion(rs.getString(2));
                objbean.setPlandemejoradecalidad(rs.getString(3));
                objbean.setMetricasdecalidad(rs.getString(4));
                objbean.setActualizacionesdeladocumentacion(rs.getString(5));
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
     
     
     public int eliminarcontrolcalidad(ControldeCalidadBean objbean){
        int i=0;
        try {
        
            ConexionBD objc=new ConexionBD();
            cn=objc.getConexionBD();
            pt=cn.prepareStatement("delete from controldecalidad where numero=?");
            pt.setInt(1,objbean.getNumero());
            
            i=pt.executeUpdate();
            pt.close();
            cn.close();
        } catch (Exception e) {
             i=0;
         }
         return  i;
         
     }
     
     public int grabarcontrolcalidad(ControldeCalidadBean objbean){
         int i=0;
         try {
             ConexionBD objc=new ConexionBD();
             cn=objc.getConexionBD();
             pt=cn.prepareStatement("insert into controldecalidad values (?,?,?,?,?,?)");
             pt.setInt(1, objbean.getNumero());
             pt.setString(2, objbean.getPlandegestion());
             pt.setString(3, objbean.getPlandemejoradecalidad());
             pt.setString(4, objbean.getMetricasdecalidad());
             pt.setString(5, objbean.getActualizacionesdeladocumentacion());
             pt.setInt(6, objbean.getNUMPROY());
       
      i=pt.executeUpdate();
      pt.close();
      cn.close();
         } catch (Exception e) {
             i=0;
         }
         return i;
     }
     
     public int modificarcontrolcalidad(ControldeCalidadBean objbean){
         int i=0;
         try {
             ConexionBD objc=new ConexionBD();
             cn=objc.getConexionBD();
             pt=cn.prepareStatement("update controldecalidad set plandegestion=? ,plandemejoradecalidad=?, metricasdecalidad=?, actualizacionesdeladocumentacion=?,numproy=? where numero=?;");

             pt.setString(1,objbean.getPlandegestion());
             pt.setString(2, objbean.getPlandemejoradecalidad());
             pt.setString(3, objbean.getMetricasdecalidad());
             pt.setString(4, objbean.getActualizacionesdeladocumentacion());
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
     
     public ControldeCalidadBean CapturarControlCalidad(ControldeCalidadBean obj)
    {
        ControldeCalidadBean objeto=null;
        try 
        {
            cn  =  ConexionBD.getConexionBD();
            pt  = cn.prepareStatement("select c.numero,c.plandegestion,c.plandemejoradecalidad,c.metricasdecalidad,c.actualizacionesdeladocumentacion,c.numproy,pr.titulo from controldecalidad c inner join proy pr on pr.numero=c.numproy where c.numero=?;");
            pt.setInt(1, obj.getNumero());
            rs=pt.executeQuery();
            while(rs.next())
            {
                objeto=new ControldeCalidadBean();
                objeto.setNumero(rs.getInt(1));
                objeto.setPlandegestion(rs.getString(2));
                objeto.setPlandemejoradecalidad(rs.getString(3));
                objeto.setMetricasdecalidad(rs.getString(4));
                objeto.setActualizacionesdeladocumentacion(rs.getString(5));
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
              pt  = cn.prepareStatement("SELECT MAX(NUMERO) FROM controldecalidad ;");  
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

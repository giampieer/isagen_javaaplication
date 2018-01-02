
package DAO;
import UTIL.ConexionBD;
import BEAN.InteresadosBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class InteresadosDAO {
    Connection          cn = null;
    PreparedStatement   pt = null;
    ResultSet           rs = null;
    ArrayList<InteresadosBean> lista = null;
    InteresadosBean objBean = null;
    
  
        
    public ArrayList<InteresadosBean> ListarInteresados1Personal(InteresadosBean obj) {
        try {
             cn=ConexionBD.getConexionBD();// conexion BD
             pt=cn.prepareStatement("select i.numero,i.nombre,i.importe,i.necesidades,i.interes,i.numproy,pr.titulo from interesados i inner join proy pr on pr.numero=i.numproy where i.numproy='"+obj.getNUMPROY()+"'");
             rs=pt.executeQuery();
             lista = new ArrayList<InteresadosBean>();
             while(rs.next()){
                 objBean = new InteresadosBean();
                 objBean.setNUMERO(rs.getInt("numero"));
                 objBean.setNOMBRE(rs.getString("nombre"));
                 objBean.setIMPORTE(rs.getString("importe"));
                 objBean.setNECESIDADES(rs.getString("necesidades"));
                 objBean.setINTERES(rs.getString("interes"));
                 objBean.setNUMPROY(rs.getInt("numproy"));
                 objBean.setNOMBPROY(rs.getString("titulo"));
                 lista.add(objBean);
             }
             pt.close();
             rs.close();
             cn.close();
        } catch (Exception e) {
             
        }
        return lista;
    }
     
    public ArrayList<InteresadosBean> ListarInteresados1() {
        try {
             cn=ConexionBD.getConexionBD();// conexion BD
             pt=cn.prepareStatement("select i.numero,i.nombre,i.importe,i.necesidades,i.interes,i.numproy,pr.titulo from interesados i inner join proy pr on pr.numero=i.numproy");
             rs=pt.executeQuery();
             lista = new ArrayList<InteresadosBean>();
             while(rs.next()){
                 objBean = new InteresadosBean();
                 objBean.setNUMERO(rs.getInt("numero"));
                 objBean.setNOMBRE(rs.getString("nombre"));
                 objBean.setIMPORTE(rs.getString("importe"));
                 objBean.setNECESIDADES(rs.getString("necesidades"));
                 objBean.setINTERES(rs.getString("interes"));
                 objBean.setNUMPROY(rs.getInt("numproy"));
                 objBean.setNOMBPROY(rs.getString("titulo"));
                 lista.add(objBean);
             }
             pt.close();
             rs.close();
             cn.close();
        } catch (Exception e) {
             
        }
        return lista;
    }
    
    public int InsertarInteresado(InteresadosBean obj){
        int estado = 0;
        try {
             cn=ConexionBD.getConexionBD(); 
             pt=cn.prepareStatement("insert into interesados values(?,?,?,?,?,?)");
             pt.setInt(1,obj.getNUMERO());
             pt.setString(2,obj.getNOMBRE());
             pt.setString(3,obj.getIMPORTE()); 
             pt.setString(4,obj.getNECESIDADES());
             pt.setString(5,obj.getINTERES());
             pt.setInt(6, obj.getNUMPROY());
             estado=pt.executeUpdate();
             pt.close();
             cn.close();
        } catch (Exception e) {
             estado=0;
        }
        return estado;
    }
    
    public int EliminarInteresados(InteresadosBean obj){
        int estado = 0;
        try {
             cn=ConexionBD.getConexionBD();
             pt=cn.prepareStatement("delete from interesados where numero=?");
             pt.setInt(1,obj.getNUMERO());
             estado=pt.executeUpdate();
             pt.close();
             cn.close();
        } catch (Exception e) {
             estado=0;
        }
        return estado;
    }
    
    public int ModificarInteresados(InteresadosBean obj){
        int estado = 0;
        try {
             cn=ConexionBD.getConexionBD();
             pt=cn.prepareStatement("update interesados set nombre=?,importe=?,necesidades=?,interes=?,numproy=? where numero=?");
             pt.setString(1,obj.getNOMBRE());
             pt.setString(2,obj.getIMPORTE());
             pt.setString(3,obj.getNECESIDADES());
             pt.setString(4,obj.getINTERES());
             pt.setInt(5,obj.getNUMPROY());
             pt.setInt(6,obj.getNUMERO());
             estado=pt.executeUpdate();
             pt.close();
             cn.close();
        } catch (Exception e) {
             estado=0;
        }
        return estado;
    }
    
     public InteresadosBean CapturarCodigo(InteresadosBean obj)
    {
        InteresadosBean objeto=null;
        try 
        {
            cn  =  ConexionBD.getConexionBD();
            pt  = cn.prepareStatement("select i.numero,i.nombre,i.importe,i.necesidades,i.interes,i.numproy,pr.titulo from interesados i inner join proy pr on pr.numero=i.numproy where i.numero=?;");
            pt.setInt(1, obj.getNUMERO());
            rs=pt.executeQuery();
            while(rs.next())
            {
                objeto=new InteresadosBean();
                objeto.setNUMERO(rs.getInt(1));
                objeto.setNOMBRE(rs.getString(2));
                objeto.setIMPORTE(rs.getString(3));
                objeto.setNECESIDADES(rs.getString(4));
                objeto.setINTERES(rs.getString(5));
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
              pt  = cn.prepareStatement("SELECT MAX(NUMERO) FROM interesados ;");  
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


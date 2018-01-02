
package DAO;
import UTIL.ConexionBD;
import BEAN.ReunionesBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ReunionesDAO {
    Connection          cn = null;
    PreparedStatement   pt = null;
    ResultSet           rs = null;
    ArrayList<ReunionesBean> lista = null;
    ReunionesBean objReunionesBean = null;

    //mostra tabla personal
    public ArrayList<ReunionesBean> ListarReuniones1Personal(ReunionesBean obj) {
        try {
             cn=ConexionBD.getConexionBD();// conexion BD
             pt=cn.prepareStatement("select r.numero,r.personal,r.fecha,r.acuerdos,r.reunion,r.duracion,r.numproy,pr.titulo from reuniones r inner join proy pr on pr.numero=r.numproy where r.numproy='"+obj.getNUMPROY()+"'");
             rs=pt.executeQuery();
             lista = new ArrayList<ReunionesBean>();
             while(rs.next()){
                 objReunionesBean = new ReunionesBean();
                 objReunionesBean.setNUMERO(rs.getInt("numero"));
                 objReunionesBean.setPERSONAL(rs.getString("personal"));
                 objReunionesBean.setFECHA(rs.getString("fecha"));
                 objReunionesBean.setACUERDOS(rs.getString("acuerdos"));
                 objReunionesBean.setREUNION(rs.getString("reunion"));
                 objReunionesBean.setDURACION(rs.getString("duracion"));
                 objReunionesBean.setNUMPROY(rs.getInt("numproy"));
                 objReunionesBean.setNOMBPROY(rs.getString("titulo"));
                 lista.add(objReunionesBean);
             }
             pt.close();
             rs.close();
             cn.close();
        } catch (Exception e) {
             
        }
        return lista;
    }
    
   
     
     //general
      public ArrayList<ReunionesBean> ListarReuniones1() {
        try {
             cn=ConexionBD.getConexionBD();// conexion BD
             pt=cn.prepareStatement("select r.numero,r.personal,r.fecha,r.acuerdos,r.reunion,r.duracion,r.numproy,pr.titulo from reuniones r inner join proy pr on pr.numero=r.numproy");
             rs=pt.executeQuery();
             lista = new ArrayList<ReunionesBean>();
             while(rs.next()){
                 objReunionesBean = new ReunionesBean();
                 objReunionesBean.setNUMERO(rs.getInt("numero"));
                 objReunionesBean.setPERSONAL(rs.getString("personal"));
                 objReunionesBean.setFECHA(rs.getString("fecha"));
                 objReunionesBean.setACUERDOS(rs.getString("acuerdos"));
                 objReunionesBean.setREUNION(rs.getString("reunion"));
                 objReunionesBean.setDURACION(rs.getString("duracion"));
                 objReunionesBean.setNUMPROY(rs.getInt("numproy"));
                 objReunionesBean.setNOMBPROY(rs.getString("titulo"));
                 lista.add(objReunionesBean);
             }
             pt.close();
             rs.close();
             cn.close();
        } catch (Exception e) {
             
        }
        return lista;
    }
    
    
    public int InsertarReuniones(ReunionesBean obj){
        int estado = 0;
        try {
             cn=ConexionBD.getConexionBD(); 
             pt=cn.prepareStatement("insert into reuniones values(?,?,?,?,?,?,?)");
             pt.setInt(1,obj.getNUMERO());
             pt.setString(2,obj.getPERSONAL());
             pt.setString(3,obj.getFECHA()); 
             pt.setString(4,obj.getACUERDOS());
             pt.setString(5,obj.getREUNION());
             pt.setString(6,obj.getDURACION());
             pt.setInt(7, obj.getNUMPROY());
             estado=pt.executeUpdate();
             pt.close();
             cn.close();
        } catch (Exception e) {
             estado=0;
        }
        return estado;
    }
    
    public int EliminarReuniones(ReunionesBean obj){
        int estado = 0;
        try {
             cn=ConexionBD.getConexionBD();
             pt=cn.prepareStatement("delete from reuniones where numero=?");
             pt.setInt(1,obj.getNUMERO());
             estado=pt.executeUpdate();
             pt.close();
             cn.close();
        } catch (Exception e) {
             estado=0;
        }
        return estado;
    }
    
    public int ModificarReuniones(ReunionesBean obj){
        int estado = 0;
        try {
             cn=ConexionBD.getConexionBD();
             pt=cn.prepareStatement("update reuniones set personal=?,fecha=?,acuerdos=?,reunion=?,duracion=?,numproy=? where numero=?");
             pt.setString(1,obj.getPERSONAL());
             pt.setString(2,obj.getFECHA());
             pt.setString(3,obj.getACUERDOS());
             pt.setString(4,obj.getREUNION());
             pt.setString(5,obj.getDURACION());
             pt.setInt(6,obj.getNUMPROY());
             pt.setInt(7,obj.getNUMERO());
             estado=pt.executeUpdate();
             pt.close();
             cn.close();
        } catch (Exception e) {
             estado=0;
        }
        return estado;
    }
   
    
     public ReunionesBean CapturarCodigo(ReunionesBean obj)
    {
        ReunionesBean objeto=null;
        try 
        {
            cn  =  ConexionBD.getConexionBD();
            pt  = cn.prepareStatement("select r.numero,r.personal,r.fecha,r.acuerdos,r.reunion,r.duracion,r.numproy,pr.titulo from reuniones r inner join proy pr on pr.numero=r.numproy where r.numero=?;");
            pt.setInt(1, obj.getNUMERO());
            rs=pt.executeQuery();
            while(rs.next())
            {
                objeto=new ReunionesBean();
                objeto.setNUMERO(rs.getInt(1));
                objeto.setPERSONAL(rs.getString(2));
                objeto.setFECHA(rs.getString(3));
                objeto.setACUERDOS(rs.getString(4));
                objeto.setREUNION(rs.getString(5));
                objeto.setDURACION(rs.getString(6)); 
                objeto.setNUMPROY(rs.getInt(7));
                objeto.setNOMBPROY(rs.getString(8));
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
              pt  = cn.prepareStatement("SELECT MAX(NUMERO) FROM reuniones ");  
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


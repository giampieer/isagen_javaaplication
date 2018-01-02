
package DAO;
import UTIL.ConexionBD;
import BEAN.PersonalBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class PersonalDAO {
    Connection          cn = null;
    PreparedStatement   pt = null;
    ResultSet           rs = null;
    ArrayList<PersonalBean> lista = null;
    PersonalBean objPersoBean = null;
    
 public ArrayList<PersonalBean> ListarPersonal1() {
        try {
             cn=ConexionBD.getConexionBD();// conexion BD
             pt=cn.prepareStatement("select p.codpersonal,p.nombpersonal,p.emapersonal,p.telfpersonal,p.horas,p.dias,p.ID,p.PASS,p.numproy,pr.titulo from personal p inner join proy pr on pr.numero=p.numproy");
             rs=pt.executeQuery();
             lista = new ArrayList<PersonalBean>();
             while(rs.next()){
                 objPersoBean = new PersonalBean();
                 objPersoBean.setCODPERSONAL(rs.getInt("codpersonal"));
                 objPersoBean.setNOMBPERSONAL(rs.getString("nombpersonal"));
                 objPersoBean.setEMAPERSONAL(rs.getString("emapersonal"));
                 objPersoBean.setTELFPERSONAL(rs.getString("telfpersonal"));
                 objPersoBean.setHORAS(rs.getString("horas"));
                 objPersoBean.setDIAS(rs.getString("dias"));
                 objPersoBean.setID(rs.getString("ID"));
                 objPersoBean.setPASS(rs.getString("PASS"));
                 objPersoBean.setNUMPROY(rs.getInt("numproy"));
                 objPersoBean.setNOMBPROY(rs.getString("titulo"));
                 
                 lista.add(objPersoBean);
             }
             pt.close();
             rs.close();
             cn.close();
        } catch (Exception e) {
             
        }
        return lista;
    }
      
      
      
    
    public int InsertarPersonal(PersonalBean obj){
        int estado = 0;
        try {
             cn=ConexionBD.getConexionBD(); 
             pt=cn.prepareStatement("insert into personal values(?,?,?,?,?,?,?,?,?)");
             pt.setInt(1,obj.getCODPERSONAL());
             pt.setString(2,obj.getNOMBPERSONAL());
             pt.setString(3,obj.getEMAPERSONAL()); 
             pt.setString(4,obj.getTELFPERSONAL());
             pt.setString(5,obj.getHORAS());
             pt.setString(6,obj.getDIAS());
             pt.setString(7, obj.getID());
             pt.setString(8, obj.getPASS());
             pt.setInt(9,obj.getNUMPROY());
             
             estado=pt.executeUpdate();
             pt.close();
             cn.close();
        } catch (Exception e) {
             estado=0;
        }
        return estado;
    }
    
    public int EliminarPersonal(PersonalBean obj){
        int estado = 0;
        try {
             cn=ConexionBD.getConexionBD();
             pt=cn.prepareStatement("delete from personal where codpersonal=?");
             pt.setInt(1,obj.getCODPERSONAL());
             estado=pt.executeUpdate();
             pt.close();
             cn.close();
        } catch (Exception e) {
             estado=0;
        }
        return estado;
    }
    
    public int ModificarPersonal(PersonalBean obj){
        int estado = 0;
        try {
             cn=ConexionBD.getConexionBD();
             pt=cn.prepareStatement("update personal set nombpersonal=?,emapersonal=?,telfpersonal=?,horas=?,dias=?,ID=?,PASS=?,numproy=? where codpersonal=?");
             pt.setString(1,obj.getNOMBPERSONAL());
             pt.setString(2,obj.getEMAPERSONAL());
             pt.setString(3,obj.getTELFPERSONAL());
             pt.setString(4,obj.getHORAS());
             pt.setString(5,obj.getDIAS());
             pt.setString(6, obj.getID());
             pt.setString(7, obj.getPASS());
             pt.setInt(8,obj.getNUMPROY());
             pt.setInt(9,obj.getCODPERSONAL());
             
             
             estado=pt.executeUpdate();
             pt.close();
             cn.close();
        } catch (Exception e) {
             estado=0;
        }
        return estado;
    }
    
     public PersonalBean CapturarCodigo(PersonalBean obj)
    {
        PersonalBean objeto=null;
        try 
        {
            cn  =  ConexionBD.getConexionBD();
            pt  = cn.prepareStatement("select p.codpersonal,p.nombpersonal,p.emapersonal,p.telfpersonal,p.horas,p.dias,p.ID,p.PASS,p.numproy,pr.titulo from personal p inner join proy pr on pr.numero=p.numproy where p.codpersonal=?;");
            pt.setInt(1, obj.getCODPERSONAL());
            rs=pt.executeQuery();
            while(rs.next())
            {
                objeto=new PersonalBean();
                objeto.setCODPERSONAL(rs.getInt(1));
                objeto.setNOMBPERSONAL(rs.getString(2));
                objeto.setEMAPERSONAL(rs.getString(3));
                objeto.setTELFPERSONAL(rs.getString(4));
                objeto.setHORAS(rs.getString(5));
                objeto.setDIAS(rs.getString(6)); 
                objeto.setID(rs.getString(7));
                objeto.setPASS(rs.getString(8));
                objeto.setNUMPROY(rs.getInt(9));
                objeto.setNOMBPROY(rs.getString(10));
                
                
            }
            pt.close();
            rs.close();
            cn.close();
        } catch (Exception e) 
        {
        }
        return objeto;
    }
     
      public int ValidarAcceso(PersonalBean obj)
    {
        int estado=0;
        try 
        {
            ConexionBD objc=new ConexionBD();
            cn=objc.getConexionBD();
            pt=cn.prepareStatement("select COUNT(*) from personal where ID=? and PASS=? ");
            pt.setString(1, obj.getID());
            pt.setString(2, obj.getPASS());
            
            rs=pt.executeQuery();
            while(rs.next()){
                estado=rs.getInt(1);
            }
            pt.close();
            rs.close();
            cn.close();
        } catch (Exception e) 
        {
            estado=0;
        }
        return estado;
    }
     
 
          public PersonalBean capturarDatosUsuario1(PersonalBean objUsuBean){
        PersonalBean  objeto=null;
        try {
            cn=ConexionBD.getConexionBD();
            pt=cn.prepareStatement("select p.codpersonal,p.nombpersonal,p.emapersonal,p.telfpersonal,p.horas,p.dias,p.ID,p.PASS,p.numproy,pr.titulo  from personal p inner join proy pr on pr.numero=p.numproy where p.ID=? and p.PASS=?;");
            pt.setString(1, objUsuBean.getID());
            pt.setString(2, objUsuBean.getPASS());
           
            rs=pt.executeQuery();
            while(rs.next()){
               
                objeto=new PersonalBean();
                objeto = new PersonalBean();
                 objeto .setCODPERSONAL(rs.getInt("codpersonal"));
                 objeto .setNOMBPERSONAL(rs.getString("nombpersonal"));
                 objeto .setEMAPERSONAL(rs.getString("emapersonal"));
                 objeto .setTELFPERSONAL(rs.getString("telfpersonal"));
                 objeto .setHORAS(rs.getString("horas"));
                 objeto .setDIAS(rs.getString("dias"));
                 objeto .setID(rs.getString("ID"));
                 objeto .setPASS(rs.getString("PASS"));
                 objeto.setNUMPROY(rs.getInt("numproy"));
                 objeto.setNOMBPROY(rs.getString("titulo"));
                         
            }
            pt.close();
            rs.close();
            cn.close();
        } catch (Exception e) {
        }
        return objeto;
    } 
           public PersonalBean InformacionProyecto(PersonalBean objUsuBean){
        PersonalBean  objeto=null;
        try {
            cn=ConexionBD.getConexionBD();
            pt=cn.prepareStatement("select  p.numero,p.titulo,p.duracion,p.descripcion,p.tipo,p.fases,p.inicio,p.fin,p.gastos,p.codjefe,"
                    + "r.numero,r.alcance,r.personal,r.reuniones,r.descripcion, pro.numero,pro.nivel,pro.descripcion,pro.perjudicado,o.numero,o.nivel,o.descripcion,o.finalidad,ad.nombjefe"
                    + "  from personal per inner join  requisito r on per.numproy=r.numproy inner join problema pro on r.numproy=pro.numproy inner join     objetivo o on o.numproy=pro.numproy inner join    proy p on p.numero=pro.numproy inner join     jefe ad on ad.codjefe=p.codjefe where per.numproy='"+objUsuBean.getNUMPROY()+"'");
           
            rs=pt.executeQuery();
            while(rs.next()){
               
                
                objeto = new PersonalBean();
                
                objeto.setNumero(rs.getInt(1));
                objeto.setTitulo(rs.getString(2));
                objeto.setDuracion(rs.getString(3));
                objeto.setDescripcion(rs.getString(4));
                objeto.setTipo(rs.getString(5));
                objeto.setFases(rs.getString(6));
                objeto.setInicio(rs.getString(7));
                objeto.setFin(rs.getString(8));
                objeto.setGastos(rs.getString(9));
                objeto.setCODJEFE(rs.getInt(10));
                objeto.setNumeror(rs.getInt(11));
                objeto.setAlcance(rs.getString(12));
                objeto.setPersonal(rs.getInt(13));
                objeto.setReunion(rs.getInt(14));
                objeto.setDescripcionr(rs.getString(15));
                
                objeto.setNumeropro(rs.getInt(6));
                objeto.setNivel(rs.getString(17));
                objeto.setDescripcionpro(rs.getString(18));
                objeto.setPerjudicado(rs.getString(19));
                
                objeto.setNumeroobj(rs.getInt(20));
                objeto.setNivelobj(rs.getString(21));
                objeto.setDescripcionobj(rs.getString(22));
                objeto.setFinalidad(rs.getString(23));
                objeto.setNOMBJEFE(rs.getString(24));
            }
            pt.close();
            rs.close();
            cn.close();
        } catch (Exception e) {
        }
        return objeto;
    } 
          
         public int generarCodigo() {
        int CODIGO = 0;
        try {
              cn  =  ConexionBD.getConexionBD();
              pt  = cn.prepareStatement("SELECT MAX(CODPERSONAL) FROM personal ");  
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

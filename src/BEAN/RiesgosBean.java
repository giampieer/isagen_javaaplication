package BEAN;

public class RiesgosBean {
    private int numero;
    private String nivel;
    private String descripción;
       private int NUMPROY;
    private String NOMBPROY;
    
    
//contructor para solucion
    public RiesgosBean(int numero,String descripcion){
        this.numero=numero;
        this.descripción=descripcion;
    }
    public  RiesgosBean(){
        
    }
    
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getDescripción() {
        return descripción;
    }

    public void setDescripción(String descripción) {
        this.descripción = descripción;
    }

    public int getNUMPROY() {
        return NUMPROY;
    }

    public void setNUMPROY(int NUMPROY) {
        this.NUMPROY = NUMPROY;
    }

    public String getNOMBPROY() {
        return NOMBPROY;
    }

    public void setNOMBPROY(String NOMBPROY) {
        this.NOMBPROY = NOMBPROY;
    }
//combobox para solucion
    @Override
    public String toString() {
        return descripción;
    }

    
}

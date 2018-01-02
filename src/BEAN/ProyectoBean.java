
package BEAN;

public class ProyectoBean {
    private int numero;
    private String titulo;
    private String duracion;
    private String descripcion;
    private String tipo;
    private String fases;
    private String inicio;
    private String fin;
    private String gastos;
    private int CODJEFE;
    private String NOMBJEFE;

    public static ProyectoBean proy;
    
    public ProyectoBean(int num,String tit){
        this.numero=num;
        this.titulo=tit;
        
    }

    public static ProyectoBean get(){
        return proy;
    }
    public ProyectoBean() {
    }
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getFases() {
        return fases;
    }

    public void setFases(String fases) {
        this.fases = fases;
    }

    public String getInicio() {
        return inicio;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    public String getFin() {
        return fin;
    }

    public void setFin(String fin) {
        this.fin = fin;
    }

    public String getGastos() {
        return gastos;
    }

    public void setGastos(String gastos) {
        this.gastos = gastos;
    }

    public int getCODJEFE() {
        return CODJEFE;
    }

    public void setCODJEFE(int CODJEFE) {
        this.CODJEFE = CODJEFE;
    }

    public String getNOMBJEFE() {
        return NOMBJEFE;
    }

    public void setNOMBJEFE(String NOMBJEFE) {
        this.NOMBJEFE = NOMBJEFE;
    }

    @Override
    public String toString() {
        return titulo; 
    }
    
    
    
    
}

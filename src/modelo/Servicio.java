//Clase Servicio, ultima revision: 29-08-2025
package modelo;

public class Servicio {
    private String tipoServicio;
    private String fecha;
    private String hora;
    private String descripcion;
    private int precio;
    private String estado;

//============================ CONSTRUCTORES ===================================
    public Servicio(){
    }
    
    public Servicio(String tipo, String fecha, String hora, String descripcion, int precio, String estado) {
        this.tipoServicio = tipo;
        this.fecha = fecha;
        this.hora = hora;
        this.descripcion = descripcion;
        this.precio = precio;
        this.estado = estado;
    }

//=========================== GETTER Y SETTER ==================================
    public String getTipoServicio() {return tipoServicio;}
    
    public void setTipoServicio(String tipo) {this.tipoServicio = tipo;}
    
    public String getFecha() {return fecha;}
    
    public void setFecha(String fecha) {this.fecha = fecha;}
    
    public String getHora() {return hora;};
    
    public void setHora(String hora) {this.hora = hora;};
    
    public String getDescripcion() {return descripcion;}
    
    public void setDescripcion(String descripcion) {this.descripcion = descripcion;}
    
    public int getPrecio() {return precio;}
    
    public void setPrecio(int precio) {this.precio = precio;}
    
    public String getEstado() {return estado;}
    
    public void setEstado(String estado) {this.estado = estado;}   
}

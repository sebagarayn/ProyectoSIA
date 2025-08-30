//Clase Servicio, ultima revision: 29-08-2025
package proyectosia;

public class Servicio {
    private String tipoServicio;
    private String fechaHora;
    private String descripcion;
    private int precio;
    private String estado;

//============================ CONSTRUCTORES ===================================
    public Servicio(){
    }
    
    public Servicio(String tipo, String fechaHora, String descripcion, int precio, String estado) {
        this.tipoServicio = tipo;
        this.fechaHora = fechaHora;
        this.descripcion = descripcion;
        this.precio = precio;
        this.estado = estado;
    }

//=========================== GETTER Y SETTER ==================================
    public String getTipoServicio() {
        return tipoServicio; 
    }
    
    public void setTipoServicio(String tipo) {
        this.tipoServicio = tipo;
    }
    
    public String getFechaHora() {
        return fechaHora;
    }
    
    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public int getPrecio() {
        return precio;
    }
    
    public void setPrecio(int precio) {
        this.precio = precio;
    }
    
    public String getEstado() {
        return estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }   
}

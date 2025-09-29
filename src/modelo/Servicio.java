//Rev.24-09
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
    
//==============================================================================
    public String obtenerTipoServicio(){ //Se obtiene el tipo de servicio para regulares
        return tipoServicio + " (Servicio Regular)";
    }
    
    public int calcularPrecioFinal(){ //Se calcula el precio final, en este caso sin cambios
        return precio;
    }
    
    public String obtenerInstruccionesEspeciales(){ //Se obtienen las instrucciones, en este caso estandar
        return "Seguir protocolo estándar";
    }
}
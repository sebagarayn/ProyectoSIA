//Rev.24-09
package modelo;
import java.util.ArrayList;

public class Mascota {
    private String nombre;
    private String tipo;
    private String raza;
    private int edad;
    private ArrayList<Servicio> servicios = new ArrayList<>(); //Para la lista de servicios de una mascota

//============================ CONSTRUCTORES ===================================
    public Mascota(){
    }
    
    public Mascota(String nombre, String tipo, String raza, int edad) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.raza = raza;
        this.edad = edad;
    }

//=========================== GETTER Y SETTER ==================================
    public String getNombre() {return nombre;}    
    public void setNombre(String nombre) {this.nombre = nombre;}   
    public String getTipo() {return tipo;}    
    public void setTipo(String tipo) {this.tipo = tipo;}    
    public String getRaza() {return raza;}    
    public void setRaza(String raza) {this.raza = raza;}    
    public int getEdad() {return edad;}
    public void setEdad(int edad) {this.edad = edad;}
    public ArrayList<Servicio> getServicios() {return servicios; }
    
//================================ METODOS =====================================   
    public void agregarServicio(Servicio servicio) { //Para agregar un servicio a la mascota ya creado
        servicios.add(servicio); 
    }
    
    public void agregarServicio(String tipoServicio, String fecha, String hora, String descripcion, int precio, String estado){ //Para agregar un servicio a la mascota a partir de parametros ingresados
        servicios.add(new Servicio(tipoServicio, fecha, hora, descripcion, precio, estado));
    }
    
    public boolean editarServicio(Servicio servicioOriginal, Servicio servicioNuevo){
        for(int i = 0 ; i < servicios.size() ; i ++){
            Servicio servicio = servicios.get(i);
            if(servicio.equals(servicioOriginal)){
                servicios.set(i, servicioNuevo);
                return true;
            }
        }
        return false;
    }
    
    public boolean editarServicio(int index, Servicio servicioNuevo){ //Por indice en vez de equals
        if(index >= 0 && index < servicios.size()){
            servicios.set(index, servicioNuevo);
            return true;
        }
        return false;
    }
    
    public int obtenerIndiceServicio(Servicio servicio){
        for(int i = 0 ; i < servicios.size() ; i ++){
            Servicio s = servicios.get(i);
            if (s.getTipoServicio().equals(servicio.getTipoServicio()) &&
                s.getFecha().equals(servicio.getFecha()) &&
                s.getHora().equals(servicio.getHora()) &&
                s.getDescripcion().equals(servicio.getDescripcion()) &&
                s.getPrecio() == servicio.getPrecio() &&
                s.getEstado().equals(servicio.getEstado())) {
                return i;
            }
        }
        return -1;
    }
    
    public boolean eliminarServicio(int indice) {
        if(indice >= 0 && indice < servicios.size()){
            servicios.remove(indice);
            return true;
        }
        return false;
    }
}

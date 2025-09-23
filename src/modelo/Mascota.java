//Clase Mascota, ultima revisión: 29-08-2025
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
}

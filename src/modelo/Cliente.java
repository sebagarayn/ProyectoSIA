//Rev.24-09
package modelo;
import java.util.ArrayList;

public class Cliente {
    private String nombre;
    private String rut;
    private String telefono;
    private String direccion;
    private ArrayList<Mascota> mascotas = new ArrayList<>(); //Para la lista de mascotas del cliente
    
//==============================  CONSTRUCTORES  ==============================
    public Cliente(){      
    } 
    
    public Cliente(String nombre, String rut, String telefono, String direccion) {
        this.nombre = nombre;
        this.rut = rut;
        this.telefono = telefono;
        this.direccion = direccion;
    }
    
//=============================  GETTER Y SETTER  ==============================
    public String getNombre() {return nombre;}  
    public void setNombre(String nombre) {this.nombre = nombre;}    
    public String getRut() {return rut;}
    public void setRut(String rut) {this.rut = rut;}  
    public String getTelefono() {return telefono;}   
    public void setTelefono(String telefono) {this.telefono = telefono;}   
    public String getDireccion() {return direccion;}    
    public void setDireccion(String direccion) {this.direccion = direccion;}   
    public ArrayList<Mascota> getMascotas() {return mascotas;}
    
//=======================  METODOS SOBREESCRITURA  =============================
    public String obtenerTipoCliente(){ //Obtener el tipo de cliente
        return "Cliente Regular";
    }
    
    public double calcularDescuento(){ //Obtener descuento
        return 0.0; //Sin descuento por defecto para los clientes regulares
    }
    
    public String obtenerBeneficios(){ //Obtener beneficios
        return "Beneficios básicos: Atención estándar";
    }
    
//=================================  METODOS  ==================================
    public void agregarMascota(Mascota mascota) { //Para agregar a la lista un objeto mascota ya creado
        mascotas.add(mascota); 
    }
    
    public void agregarMascota(String nombre, String tipo, String raza, int edad) { //Para agregar a la lista una mascota a partir de parametros
        mascotas.add(new Mascota(nombre, tipo, raza, edad));
    }
    
    public Mascota buscarMascotaPorNombre(String nombre) { //Para buscar entre las mascotas de un cliente, retornando a la primera coincidencia, si no encuentra es null
        for (Mascota mascota : mascotas) {
            if (mascota.getNombre().equalsIgnoreCase(nombre)){
                return mascota;
            }
        }
        return null;
    }
    
    public boolean eliminarMascota(String nombreMascota){
        for(Mascota mascota : mascotas){
            if(mascota.getNombre().equalsIgnoreCase(nombreMascota)){
                mascotas.remove(mascota);
                return true;
            }
        }
        return false;
    }
    
    public boolean editarMascota(String nombre, String tipo, String raza, int edad){
        for(Mascota mascota : mascotas){
            if(mascota.getNombre().equalsIgnoreCase(nombre)){
                mascota.setTipo(tipo);
                mascota.setRaza(raza);
                mascota.setEdad(edad);
                return true;
            }
        }
        return false;
    }
}
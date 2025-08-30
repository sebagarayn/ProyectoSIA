//Clase Veterinaria, ultima revision 29-08-2025
package proyectosia;
import java.util.ArrayList;
import java.util.HashMap;

public class Veterinaria {
    private ArrayList<Cliente> listaClientes; //Lista para almacenar clientes
    private HashMap<String, Cliente> mapaClientes; //Mapa para asociar rut con clientes, para buscar rapido

//============================ CONSTRUCTORES ===================================    
    public Veterinaria(){
        this.listaClientes = new ArrayList<>();
        this.mapaClientes = new HashMap<>();
    }
    
//=========================== GETTER Y SETTER ==================================
    public ArrayList<Cliente> getClientes(){
        return listaClientes;
    }
    
    public HashMap<String, Cliente> getMapaCliente(){
        return mapaClientes;
    }
    
//=============================== METODOS ======================================
    public boolean agregarCliente(Cliente cliente){ //Para agregar clientes, verificando si ya existe el rut
        if(!mapaClientes.containsKey(cliente.getRut())){
            listaClientes.add(cliente);
            mapaClientes.put(cliente.getRut(), cliente);
            return true;
        }
        else{
            System.out.println("Error, el cliente con RUT " + cliente.getRut() + " ya existe");
            return false;
        }
    }
    
    public boolean agregarCliente(String nombre, String rut, String telefono, String direccion){ //Para agregar clientes, pero recibiendo parametros
        if(!mapaClientes.containsKey(rut)){
            Cliente nuevo = new Cliente(nombre, rut, telefono, direccion);
            listaClientes.add(nuevo);
            mapaClientes.put(rut, nuevo);
            return true;
        }
        else{
            System.out.println("Error, el cliente con RUT "+ rut + " ya existe");
            return false;
        }
    }
    
    public Cliente buscarClientePorRut(String rut){ //Para buscar clientes por rut usando el hashmap
        return mapaClientes.get(rut);
    }
    
    public void mostrarClientesMascotas(){ //Para mostrar todos los clientes, sus mascotas y sus respectivos servicios
        for(Cliente cliente : listaClientes){
            System.out.println("Cliente: " + cliente.getNombre() + " (" + cliente.getRut() + ")");
            for(Mascota mascota : cliente.getMascotas()){
                System.out.println("  Mascota: " + mascota.getNombre() + " (" + mascota.getTipo() + ", " + mascota.getRaza() + ", " + mascota.getEdad() + " anios)");
                for(Servicio servicio : mascota.getServicios()){
                    System.out.println("    Servicio: " + servicio.getTipoServicio() + " - " + servicio.getFechaHora() + " - " + servicio.getDescripcion() + " ($" + servicio.getPrecio() + ") - Estado: " + servicio.getEstado());              
                }
            }
        }
    }
    
    public void cargarDatosIniciales(){ //Para cargar datos iniciales ya prestablecidos
        Cliente cliente1 = new Cliente("Sebastian Garay", "20911143-8", "967423262", "Enrique Budge");
        Mascota mascota1 = new Mascota("Ahri", "Perro", "Pug", 9);
        Mascota mascota2 = new Mascota("Mia", "Perro", "Mezcla", 1);
        Servicio servicio1 = new Servicio("Peluqueria", "2025-08-22 (10:00)", "Bano y corte", 15000, "Realizado");
        Servicio servicio2 = new Servicio("Veterinaria", "2025-08-23 (12:00)", "Vacunacion", 20000, "Pendiente");
        
        mascota1.agregarServicio(servicio1);
        mascota2.agregarServicio(servicio2);   
        
        cliente1.agregarMascota(mascota1);
        cliente1.agregarMascota(mascota2);
        
        agregarCliente(cliente1);
    }
}
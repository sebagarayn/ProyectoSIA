//Clase Veterinaria, ultima revision 29-08-2025
package modelo;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.*;

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
    
    public boolean editarCliente(String rut, String nuevoNombre, String nuevoTelefono, String nuevaDireccion){
        Cliente cliente = buscarClientePorRut(rut);
        if(cliente != null){
            cliente.setNombre(nuevoNombre);
            cliente.setTelefono(nuevoTelefono);
            cliente.setDireccion(nuevaDireccion);
            return true;
        }
        return false;
    }
    
    public boolean eliminarCliente(String rut){
        Cliente cliente = buscarClientePorRut(rut);
        if(cliente != null){
            if(!cliente.getMascotas().isEmpty()){
                System.out.println("No es posible eliminar un cliente con mascotas registradas");
                return false;
            }
            listaClientes.remove(cliente);
            mapaClientes.remove(rut);
            return true;
        }
        return false;
    }
    
    public boolean editarMascota(String rutCliente, String nombre, String tipo, String raza, int edad){
        Cliente cliente = buscarClientePorRut(rutCliente);
        if(cliente != null){
            return cliente.editarMascota(nombre, tipo, raza, edad);
        }
        return false;
    }
    
    public boolean eliminarMascota(String rutCliente, String nombreMascota){
        Cliente cliente = buscarClientePorRut(rutCliente);
        if(cliente != null){
            return cliente.eliminarMascota(nombreMascota);
        }
        return false;
    }
    
    public List<Servicio> buscarServiciosPorMascota(String rutCliente, String nombreMascota){
        Cliente cliente = buscarClientePorRut(rutCliente);
        if(cliente != null){
            Mascota mascota = cliente.buscarMascotaPorNombre(nombreMascota);
            if(mascota != null){
                return mascota.getServicios();
            }
        }
        return new ArrayList<>(); //Lista vacias
    }
    
//==============================================================================
    
    public void mostrarClientesMascotas(){ //Para mostrar todos los clientes, sus mascotas y sus respectivos servicios
        for(Cliente cliente : listaClientes){
            System.out.println("Cliente: " + cliente.getNombre() + " (" + cliente.getRut() + ")");
            for(Mascota mascota : cliente.getMascotas()){
                System.out.println("  Mascota: " + mascota.getNombre() + " (" + mascota.getTipo() + ", " + mascota.getRaza() + ", " + mascota.getEdad() + " anios)");
                for(Servicio servicio : mascota.getServicios()){
                    System.out.println("    Servicio: " + servicio.getTipoServicio() + " - " + servicio.getFecha() + " - " + servicio.getHora() + " - " + servicio.getDescripcion() + " ($" + servicio.getPrecio() + ") - Estado: " + servicio.getEstado());              
                }
            }
        }
    }
//=========================METODOS CSV =========================================
//=========================GUARDADOS CSV========================================
    public void guardarClientesCSV() {
        try(PrintWriter escritor = new PrintWriter(new FileWriter("csv/clientes.csv"))){ //Para escribir en el archivo en la carpeta csv
            for(Cliente cliente : listaClientes){ //Sobre la lista de los clientes
                escritor.println( //Se escriben los datos separador por coma en una linea
                        cliente.getRut() + "," + 
                        cliente.getNombre() + "," + 
                        cliente.getTelefono() + "," + 
                        cliente.getDireccion());
            }
        } catch (IOException e){ //En caso de haber un error en entrada o salida, muestra un mensaje de error
            System.out.println("Error al guardar clientes: " + e.getMessage());
        }
    }
    
    public void guardarMascotasCSV() {
        try(PrintWriter escritor = new PrintWriter(new FileWriter("csv/mascotas.csv"))){ //Para escribir en el archivo en la carpeta csv
            for(Cliente cliente : listaClientes){ //Sobre la lista de los clientes
                for(Mascota mascota : cliente.getMascotas()){ //Sobre la lista las mascotas de los clientes
                    escritor.println( //Se escriben los datos separador por coma en una linea
                            cliente.getRut() + "," + 
                            mascota.getNombre() + "," + 
                            mascota.getTipo() + "," + 
                            mascota.getRaza() + "," + 
                            mascota.getEdad());
                }
            }
        } catch (IOException e){ //En caso de haber un error en entrada o salida, muestra un mensaje de error
            System.out.println("Error al guardar mascotas: " + e.getMessage());
        }
    }
    
    public void guardarServiciosCSV() {
        try(PrintWriter escritor = new PrintWriter(new FileWriter("csv/servicios.csv"))){ //Para escribir en el archivo en la carpeta csv
            for(Cliente cliente : listaClientes){ //Sobre la lista de los clientes
                for(Mascota mascota : cliente.getMascotas()){ //Sobre la lista las mascotas de los clientes
                    for(Servicio servicio : mascota.getServicios()){ //Sobre la lista de los servicios de las mascotas de los clientes
                        escritor.println(
                                cliente.getRut() + "," + 
                                mascota.getNombre() + "," + 
                                servicio.getTipoServicio() + "," + 
                                servicio.getFecha() + "," + 
                                servicio.getHora() + "," +
                                servicio.getDescripcion() + "," + 
                                servicio.getPrecio() + "," + 
                                servicio.getEstado());
                    }
                }
            }
        } catch (IOException e){ //En caso de haber un error en entrada o salida, muestra un mensaje de error
            System.out.println("Error al guardar servicios: " + e.getMessage());
        }
    }
    
//============================CARGAS CSV========================================
    public void cargarClientesCSV() {
        try(BufferedReader lector = new BufferedReader(new FileReader("csv/clientes.csv"))){ //Para leer el archivo en la carpeta csv
            String linea; //Para almacenar cada linea del archivo leido
            while((linea = lector.readLine()) != null) { //Mientras la linea leida no sea null, es decir, exista
                String[] partes = linea.split(","); //Aca se divide cada linea del csv que esta separada por ,
                if(partes.length == 4){ //Para asegurar que la cantidad de partes (atributos) sea correcta
                    Cliente cliente = new Cliente(
                            partes[1], //nombre
                            partes[0], //rut
                            partes[2], //telefono
                            partes[3]); //direccion
                    agregarCliente(cliente); //Se agrega el cliente
                }
            }
        } catch (IOException e){ //En caso de haber un error al leer el archivo, muestra un mensaje de error
            System.out.println("No hay datos de clientes o error en lectura de CSV de clientes. " + e.getMessage());
        }
    }
    
    public void cargarMascotasCSV() {
        try(BufferedReader lector = new BufferedReader(new FileReader("csv/mascotas.csv"))){ //Para leer el archivo en la carpeta csv
            String linea; //Para almacenar cada linea del archivo leido
            while((linea = lector.readLine()) != null) { //Mientras la linea leida no sea null, es decir, exista
                String[] partes = linea.split(","); //Aca se divide cada linea del csv que esta separada por ,
                if(partes.length == 5){ //Para asegurar que la cantidad de partes (atributos) sea correcta
                    String rutDueno = partes[0]; //rut del dueno
                    Cliente dueno = buscarClientePorRut(rutDueno); //Se busca al cliente
                    if(dueno != null){ //En caso de existir se agrega la mascota al cliente
                        dueno.agregarMascota(new Mascota(
                                partes[1], //nombre
                                partes[2], //tipo
                                partes[3], //raza
                                Integer.parseInt(partes[4]))); //edad
                    }
                }
            }
        } catch (IOException e){ //En caso de haber un error al leer el archivo, muestra un mensaje de error
            System.out.println("No hay datos de mascotas o error en lectura de CSV de mascotas. " + e.getMessage());
        }
    }
    
    public void cargarServiciosCSV() {
        try(BufferedReader lector = new BufferedReader(new FileReader("csv/servicios.csv"))){ //Para leer el archivo en la carpeta csv
            String linea; //Para almacenar cada linea del archivo leido
            while((linea = lector.readLine()) != null) { //Mientras la linea leida no sea null, es decir, exista
                String[] partes = linea.split(","); //Aca se divide cada linea del csv que esta separada por ,
                if(partes.length == 8){ //Para asegurar que la cantidad de partes (atributos) sea correcta
                    String rutDueno = partes[0]; //rut del dueno
                    String nombreMascota = partes[1]; //nombre de la mascota
                    Cliente dueno = buscarClientePorRut(rutDueno); //Se busca al cliente
                    if(dueno != null){ //En caso de existir se busca la mascota en el cliente por su nombre
                        Mascota mascota = dueno.buscarMascotaPorNombre(nombreMascota);
                        if(mascota != null){ //En caso de existirse agrega el servicio a la masctoa
                            mascota.agregarServicio(new Servicio(
                                    partes[2], //tipoServicio
                                    partes[3], //fecha
                                    partes[4], //Hora
                                    partes[5], //descripcion
                                    Integer.parseInt(partes[6]), //precio
                                    partes[7])); //estado
                        }
                    }
                }
            }
        } catch (IOException e){ //En caso de haber un error al leer el archivo, muestra un mensaje de error
            System.out.println("No hay datos de servicios o error en lectura de CSV de servicios. " + e.getMessage());
        }
    }
}
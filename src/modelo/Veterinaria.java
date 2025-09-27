//Rev.24-09
package modelo;
import exception.UmbralNegativoException;
import exception.ListaClientesVaciaException;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.*;
import proyectosia.*;
import persistencia.GestorCSV;

public class Veterinaria {
    private ArrayList<Cliente> listaClientes; //Lista para almacenar clientes
    private HashMap<String, Cliente> mapaClientes; //Mapa para asociar rut con clientes, para buscar rapido
    private GestorCSV gestorCSV; 

//============================ CONSTRUCTORES ===================================    
    public Veterinaria(){
        this.listaClientes = new ArrayList<>();
        this.mapaClientes = new HashMap<>();
        this.gestorCSV = new GestorCSV(); 
    }
    
//=========================== GETTER Y SETTER ==================================
    public ArrayList<Cliente> getClientes(){return listaClientes;}
    
    public HashMap<String, Cliente> getMapaCliente(){return mapaClientes;}
    
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

//=========================  CLIENTES FRECUENTES  ==============================
    
    //Para verificar promoción automatica, 7 servicios ahora.
    public boolean verificarPromocionAutomatica(String rut) {
        Cliente cliente = buscarClientePorRut(rut);
        if (cliente != null && !(cliente instanceof ClienteFrecuente)) {
            int totalServicios = contarTotalServicios(cliente);
            if (totalServicios >= 7) {
                String fechaActual = obtenerFechaActual();
                boolean promovido = promoverAClienteFrecuente(rut, totalServicios, fechaActual);
                return promovido;
            }
        }
        return false;
    }
    
    //Para promover a cliente regular a frecuente
    public boolean promoverAClienteFrecuente(String rut, int numeroVisitasAnuales, String fechaUltimaVisita){
        Cliente cliente = buscarClientePorRut(rut);
        if (cliente != null && !(cliente instanceof ClienteFrecuente)) { //Guardar los datos del cliente original
            String nombre = cliente.getNombre();
            String telefono = cliente.getTelefono();
            String direccion = cliente.getDireccion();
            ArrayList<Mascota> mascotasOriginales = new ArrayList<>(cliente.getMascotas());
            listaClientes.remove(cliente); //Remover a cliente regular
            mapaClientes.remove(rut); 
            ClienteFrecuente clienteFrecuente = new ClienteFrecuente(nombre, rut, telefono, direccion, numeroVisitasAnuales, fechaUltimaVisita); //Para crear cliente frecuente
            for (Mascota mascota : mascotasOriginales) { //Transferir las mascotas
                clienteFrecuente.agregarMascota(mascota);
            }
            listaClientes.add(clienteFrecuente); //Agregar nuevo cliente frecuente
            mapaClientes.put(rut, clienteFrecuente);
            return true;
        }
        return false;
    }
    
    //Calcular precio con descuento automatico
    public int calcularPrecioConDescuento(String rutCliente, int precioOriginal) {
        Cliente cliente = buscarClientePorRut(rutCliente);
        if (cliente != null) {
            double descuento = cliente.calcularDescuento(); // Polimorfismo
            return (int)(precioOriginal * (1 - descuento));
        }
        return precioOriginal;
    }
    
    //Metodo para contar servicios totales
    private int contarTotalServicios(Cliente cliente) {
        int total = 0;
        for (Mascota mascota : cliente.getMascotas()) {
            total += mascota.getServicios().size();
        }
        return total;
    }

    //Obtener fecha actual
    private String obtenerFechaActual(){
        java.time.LocalDate hoy = java.time.LocalDate.now();
        return hoy.getDayOfMonth() + "/" + hoy.getMonthValue() + "/" + hoy.getYear();
    }
    
    //Obtener clientes frecuentes
    public ArrayList<ClienteFrecuente> obtenerClientesFrecuentes() {
        ArrayList<ClienteFrecuente> clientesFrecuentes = new ArrayList<>();
        for (Cliente cliente : listaClientes) {
            if (cliente instanceof ClienteFrecuente) {
                clientesFrecuentes.add((ClienteFrecuente) cliente);
            }
        }
        return clientesFrecuentes;
    }
    
    public void verificarPromocionesPendientes(){
        ArrayList<Cliente> clientesAPromover = new ArrayList<>();
        ArrayList<Cliente> clientesARevertir = new ArrayList<>();
        for (Cliente cliente : listaClientes) {
            int totalServicios = contarTotalServicios(cliente);
            if (!(cliente instanceof ClienteFrecuente)) {
                if (totalServicios >= 7) {
                    clientesAPromover.add(cliente);
                }
            }
            else{
                if (totalServicios < 7) {
                    clientesARevertir.add(cliente);
                }
            }
        }
        for (Cliente cliente : clientesAPromover) {
            String fechaActual = obtenerFechaActual();
            int servicios = contarTotalServicios(cliente);
            promoverAClienteFrecuente(cliente.getRut(), servicios, fechaActual);
        }
        for (Cliente cliente : clientesARevertir) {
            revertirAClienteRegular(cliente.getRut());
        }
    }
    
    public boolean revertirAClienteRegular(String rut){
        Cliente cliente = buscarClientePorRut(rut);
        if (cliente instanceof ClienteFrecuente) {
            String nombre = cliente.getNombre();
            String telefono = cliente.getTelefono();
            String direccion = cliente.getDireccion();
            ArrayList<Mascota> mascotas = new ArrayList<>(cliente.getMascotas());
            listaClientes.remove(cliente);
            mapaClientes.remove(rut);
            
            Cliente clienteRegular = new Cliente(nombre, rut, telefono, direccion);
            for (Mascota mascota : mascotas) {
                clienteRegular.agregarMascota(mascota);
            }
            
            listaClientes.add(clienteRegular);
            mapaClientes.put(rut, clienteRegular);
            return true;
        }
        return false;
    }

//=============================  METODOS MASCOTAS  =============================
    
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
    
    //MASCOTAS - GERIATRICAS
    
    public boolean agregarMascotaGeriatrica(String rutDueno, String nombre, String tipo, String raza, int edad, String fechaInicioGeriatria){
        Cliente dueno = buscarClientePorRut(rutDueno);
        if(dueno != null){
            MascotaGeriatrica mascotaGeriatrica = new MascotaGeriatrica(nombre, tipo, raza, edad, fechaInicioGeriatria);
            dueno.agregarMascota(mascotaGeriatrica);
            return true;
        }
        return false;
    }
    
    public int obtenerCantidadMascotasGeriatricas(){
        int contador = 0;
        for(Cliente cliente : listaClientes){
            for(Mascota mascota : cliente.getMascotas()){
                if(mascota instanceof MascotaGeriatrica){
                    contador ++;
                }
            }
        }
        return contador;
    }
    
    public List<Mascota> obtenerMascotasGeriatricas(){
        List<Mascota> geriatricas = new ArrayList<>();
        for(Cliente cliente : listaClientes){
            for(Mascota mascota : cliente.getMascotas()){
                if(mascota instanceof MascotaGeriatrica){
                    geriatricas.add(mascota);
                }
            }
        }
        return geriatricas;
    }
    
    //========================  METODOS PARA SERVICIOS =========================
    
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
    
    public boolean editarServicio(String rutCliente, String nombreMascota, int indexServicio, Servicio servicioNuevo){
        Cliente cliente = buscarClientePorRut(rutCliente);
        if(cliente != null){
            Mascota mascota = cliente.buscarMascotaPorNombre(nombreMascota);
            if(mascota != null && indexServicio >= 0 && indexServicio < mascota.getServicios().size()){
                return mascota.editarServicio(indexServicio, servicioNuevo);
            }
        }
        return false;
    }
    
    public boolean eliminarServicio(String rutCliente, String nombreMascota, int indiceServicio){
        Cliente cliente = buscarClientePorRut(rutCliente);
        if(cliente != null){
            Mascota mascota = cliente.buscarMascotaPorNombre(nombreMascota);
            if(mascota != null && indiceServicio >= 0 && indiceServicio < mascota.getServicios().size()){
                return mascota.eliminarServicio(indiceServicio);
            }
        }
        return false;
    }
    
    public List<Servicio> serviciosConCostoMayorA(int umbral) 
        throws UmbralNegativoException, ListaClientesVaciaException {

        if (umbral < 0) {
            throw new UmbralNegativoException("El umbral no puede ser negativo.");
        }
        if (listaClientes.isEmpty()) {
            throw new ListaClientesVaciaException("No hay clientes registrados.");
        }

        List<Servicio> resultado = new ArrayList<>();

    // Recorrer clientes por Ã­ndice
        for (int i = 0; i < listaClientes.size(); i++) {
            Cliente c = listaClientes.get(i);

        // Recorrer mascotas del cliente
            for (int j = 0; j < c.getMascotas().size(); j++) {
                Mascota m = c.getMascotas().get(j);

            // Recorrer servicios de la mascota
                for (int k = 0; k < m.getServicios().size(); k++) {
                    Servicio s = m.getServicios().get(k);

                    if (s.getPrecio() > umbral) {
                        resultado.add(s);
                    }
                }
            }
        }

        return resultado;
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
//===============================  METODOS CSV  ================================

    /*Carga de los datos csv*/
    public void cargarDatos(){
        gestorCSV.cargarDatos(this);
    }
    
    /*Guardar los datos en csv*/
    public void guardarDatos(){
        gestorCSV.guardarDatos(this);
    }
    
    /*Verificar si existen datos csv*/
    public boolean existenDatos(){
        return gestorCSV.existenDatos();
    }

//==============================================================================
    public String obtenerInformacionCompleta(String rut){
        Cliente cliente = buscarClientePorRut(rut);
        if(cliente != null){
            StringBuilder info = new StringBuilder();
            info.append("INFORMACIÓN DEL CLIENTE\n");
            info.append("Nombre: ").append(cliente.getNombre()).append("\n");
            info.append("Tipo: ").append(cliente.obtenerTipoCliente()).append("\n"); // Método sobreescrito
            info.append("Descuento: ").append((int)(cliente.calcularDescuento() * 100)).append("%\n"); // Método sobreescrito
            info.append("Beneficios: ").append(cliente.obtenerBeneficios()).append("\n"); // Método sobreescrito
            info.append("\n=== MASCOTAS ===\n");
            for (Mascota mascota : cliente.getMascotas()) {
                info.append("- ").append(mascota.getNombre()).append(" (").append(mascota.getTipo()).append(")\n");
                info.append("  Cuidados: ").append(mascota.obtenerCuidadosEspeciales()).append("\n"); // Método sobreescrito
                info.append("  Visitas cada: ").append(mascota.obtenerFrecuenciaVisitas()).append(" meses\n"); // Método sobreescrito
                info.append("  Factor precio: ").append((int)(mascota.calcularFactorPrecio() * 100)).append("%\n"); // Método sobreescrito                
            }
            return info.toString();
        }
        return "Cliente no encontrado";
    }
    
//===============METODOS NO UTILIZADOS, pero podrian ser util ==================
    public List<ServicioUrgencia> obtenerServiciosUrgenciaCritica() {
        List<ServicioUrgencia> serviciosCriticos = new ArrayList<>();
        for(Cliente cliente : listaClientes) {
            for(Mascota mascota : cliente.getMascotas()) {
                for(Servicio servicio : mascota.getServicios()) {
                    if(servicio instanceof ServicioUrgencia) {
                        ServicioUrgencia urgencia = (ServicioUrgencia) servicio;
                        if(urgencia.esEmergenciaCritica()) {
                            serviciosCriticos.add(urgencia);
                        }
                    }
                }    
            }         
        }
        return serviciosCriticos;
    }
    
    public int contarServiciosUrgencia(){
        int contador = 0;
        for(Cliente cliente : listaClientes){
            for(Mascota mascota : cliente.getMascotas()) {
                for(Servicio servicio : mascota.getServicios()) {
                    if(servicio instanceof ServicioUrgencia) {
                        contador++;
                    }
                }
            }
        }
        return contador;
    }    
}
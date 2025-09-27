//Rev.24-09
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
public void guardarClientesCSV(){
        try(PrintWriter escritor = new PrintWriter(new FileWriter("csv/clientes.csv"))) {
            for(Cliente cliente : listaClientes) {
                String linea = cliente.getRut() + "," + 
                        cliente.getNombre() + "," + 
                        cliente.getTelefono() + "," + 
                        cliente.getDireccion();
                if (cliente instanceof ClienteFrecuente) {
                    ClienteFrecuente cf = (ClienteFrecuente) cliente;
                    linea += ",FRECUENTE," + cf.getNumeroVisitasAnuales() + "," + cf.getFechaUltimaVisita();
                }
                else{
                    linea += ",REGULAR,,";
                }
                escritor.println(linea);
            }
        } catch (IOException e) {
            System.out.println("Error al guardar clientes: " + e.getMessage());
        }
    }
    
    public void guardarMascotasCSV() {
        try {PrintWriter escritor = new PrintWriter(new FileWriter("csv/mascotas.csv"));
            escritor.println("rut_dueno,nombre,tipo,raza,edad,es_geriatrica,fecha_inicio_geriatria");
            for(Cliente cliente : listaClientes){
                for(Mascota mascota : cliente.getMascotas()){
                    String esGeriatrica = "false";
                    String fechaInicio = "";

                    if(mascota instanceof MascotaGeriatrica){
                        esGeriatrica = "true";
                        fechaInicio = ((MascotaGeriatrica) mascota).getFechaInicioGeriatria();
                    }
                    String linea = cliente.getRut() + "," + 
                                  mascota.getNombre() + "," + 
                                  mascota.getTipo() + "," + 
                                  mascota.getRaza() + "," + 
                                  mascota.getEdad() + "," +
                                  esGeriatrica + "," +
                                  fechaInicio;
                    escritor.println(linea);
                }
            }
            escritor.close();
        } catch (IOException e){
            System.out.println("Error al guardar mascotas: " + e.getMessage());
        }
    }
    
    /*public void guardarServiciosCSV() {
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
    }*/
    
    public void guardarServiciosCSV() {
        try(PrintWriter escritor = new PrintWriter(new FileWriter("csv/servicios.csv"))){ //Para escribir en el archivo en la carpeta csv
            for(Cliente cliente : listaClientes){ //Sobre la lista de los clientes
                for(Mascota mascota : cliente.getMascotas()){ //Sobre la lista las mascotas de los clientes
                    for(Servicio servicio : mascota.getServicios()){ //Sobre la lista de los servicios de las mascotas de los clientes
                        String linea =
                                cliente.getRut() + "," + 
                                mascota.getNombre() + "," + 
                                servicio.getTipoServicio() + "," + 
                                servicio.getFecha() + "," + 
                                servicio.getHora() + "," +
                                servicio.getDescripcion() + "," + 
                                servicio.getPrecio() + "," + 
                                servicio.getEstado();
                        if (servicio instanceof ServicioUrgencia) {
                            ServicioUrgencia urgencia = (ServicioUrgencia) servicio;
                            linea += ",true," + 
                                urgencia.getNivelUrgencia() + "," + 
                                urgencia.getMotivoUrgencia() + "," + 
                                urgencia.isRequiereAtencionInmediata();
                        }
                        else{
                            linea += ",false,,,";
                        }
                        escritor.println(linea);
                    }
                }
            }
        } catch (IOException e){ //En caso de haber un error en entrada o salida, muestra un mensaje de error
            System.out.println("Error al guardar servicios: " + e.getMessage());
        }
    }  
    
//============================CARGAS CSV========================================
    public void cargarClientesCSV() {
        try(BufferedReader lector = new BufferedReader(new FileReader("csv/clientes.csv"))) {
            String linea;
            while((linea = lector.readLine()) != null) {
                String[] partes = linea.split(",");
                if(partes.length >= 4) {
                    String rut = partes[0];
                    String nombre = partes[1];
                    String telefono = partes[2];
                    String direccion = partes[3];       
                    if (partes.length >= 7 && "FRECUENTE".equals(partes[4])) {
                        try {
                            int visitas = Integer.parseInt(partes[5]);
                            String fechaUltima = partes[6];
                            ClienteFrecuente clienteFrecuente = new ClienteFrecuente(
                                nombre, rut, telefono, direccion, visitas, fechaUltima);
                            listaClientes.add(clienteFrecuente);
                            mapaClientes.put(rut, clienteFrecuente);
                        } catch (NumberFormatException e) {
                            Cliente cliente = new Cliente(nombre, rut, telefono, direccion);
                            listaClientes.add(cliente);
                            mapaClientes.put(rut, cliente);
                        }
                    } else {
                        Cliente cliente = new Cliente(nombre, rut, telefono, direccion);
                        listaClientes.add(cliente);
                        mapaClientes.put(rut, cliente);
                    }
                }              
            }
            verificarPromocionesPendientes();
        } catch (IOException e) {
            System.out.println("No hay datos de clientes o error en lectura de CSV de clientes. " + e.getMessage());
        }
    }
    
    public void cargarMascotasCSV() {
        try(BufferedReader lector = new BufferedReader(new FileReader("csv/mascotas.csv"))){
            String linea;
            boolean primeraLinea = true;      
        
            while((linea = lector.readLine()) != null) {
                if(primeraLinea) {
                primeraLinea = false;
                    continue;
                }
            
                String[] partes = linea.split(",");         
                if(partes.length >= 5){
                    String rutDueno = partes[0];               
                    Cliente dueno = buscarClientePorRut(rutDueno);

                    if(dueno != null){
                        String nombre = partes[1];
                        String tipo = partes[2];
                        String raza = partes[3];
                        int edad = Integer.parseInt(partes[4]);

                        if(partes.length >= 7 && "true".equals(partes[5])) {
                            String fechaInicio = partes[6];
                            MascotaGeriatrica mascota = new MascotaGeriatrica(nombre, tipo, raza, edad, fechaInicio);
                            dueno.agregarMascota(mascota);
                        }
                        else{
                            dueno.agregarMascota(new Mascota(nombre, tipo, raza, edad));
                        }
                    }
                }
            }
        } catch (Exception e){
            System.out.println("Error al cargar mascotas: " + e.getMessage());
        }
    }

    
    /*public void cargarServiciosCSV() {
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
    }*/
    
    public void cargarServiciosCSV() {
        try(BufferedReader lector = new BufferedReader(new FileReader("csv/servicios.csv"))){ //Para leer el archivo en la carpeta csv
            String linea; //Para almacenar cada linea del archivo leido
            while((linea = lector.readLine()) != null) { //Mientras la linea leida no sea null, es decir, exista
                String[] partes = linea.split(","); //Aca se divide cada linea del csv que esta separada por ,
                if(partes.length >= 8){ //Para asegurar que la cantidad de partes (atributos) sea correcta
                    String rutDueno = partes[0]; //rut del dueno
                    String nombreMascota = partes[1]; //nombre de la mascota
                    Cliente dueno = buscarClientePorRut(rutDueno); //Se busca al cliente
                    if(dueno != null){ //En caso de existir se busca la mascota en el cliente por su nombre
                        Mascota mascota = dueno.buscarMascotaPorNombre(nombreMascota);
                        if(mascota != null){ //En caso de existirse agrega el servicio a la masctoa
                            String tipoServicio = partes[2];
                            String fecha = partes[3];
                            String hora = partes[4];
                            String descripcion = partes[5];
                            int precio = Integer.parseInt(partes[6]);
                            String estado = partes[7];
                            
                            if(partes.length >= 12 && "true".equals(partes[8])) {
                                try {
                                    int nivelUrgencia = Integer.parseInt(partes[9]);
                                    String motivoUrgencia = partes[10];
                                    boolean requiereInmediata = Boolean.parseBoolean(partes[11]);
                                    ServicioUrgencia servicioUrgencia = new ServicioUrgencia(
                                            tipoServicio, fecha, hora, descripcion, precio, estado,
                                            nivelUrgencia, motivoUrgencia, requiereInmediata);
                                    mascota.agregarServicio(servicioUrgencia);
                                } catch (NumberFormatException e) {
                                    mascota.agregarServicio(new Servicio(tipoServicio, fecha, hora, descripcion, precio, estado));
                                }
                            }
                            else{
                                mascota.agregarServicio(new Servicio(tipoServicio, fecha, hora, descripcion, precio, estado));
                            }
                        }
                    }
                }
            }
        } catch (IOException e){ //En caso de haber un error al leer el archivo, muestra un mensaje de error
            System.out.println("No hay datos de servicios o error en lectura de CSV de servicios. " + e.getMessage());
        }
    }
    
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
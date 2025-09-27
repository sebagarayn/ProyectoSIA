//Revisión: 27-09-2025

package persistencia;

import modelo.*; //Se importan todas las clases de modelo
import java.io.*; //Se importan clases para manejar archivos

/*Esta clase esta dedicada a la carga y guardado de los datos de la veterinaria
en formato csv. Manejando la persistencia de datos y usando un sistema batch*/

public class GestorCSV {
    private static final String RUTA_CLIENTES = "csv/clientes.csv"; //Archivo de datos de clientes
    private static final String RUTA_MASCOTAS = "csv/mascotas.csv"; //Archivos de datos de mascotas
    private static final String RUTA_SERVICIOS = "csv/servicios.csv"; //Archivos de datos de servicios
   
    public boolean existenDatos(){ //Se verifica si exiten todos los archivos csv necesesarios para la carga
        return new File(RUTA_CLIENTES).exists() && new File(RUTA_MASCOTAS).exists() && new File(RUTA_SERVICIOS).exists();
    }
    
    private void crearDirectorios(){ //Crea la carpeta/directorio csv si es que no existe
        new File("csv").mkdirs();
    }
    
    public void cargarDatos(Veterinaria veterinaria){ //Carga todos los datos desde archivos csv al iniciar la aplicación
        cargarClientes(veterinaria); //Se cargan los clientes
        cargarMascotas(veterinaria); //Se cargan las mascotas de los clientes
        cargarServicios(veterinaria); //Se cargan los servicios de las mascotas
        veterinaria.verificarPromocionesPendientes(); //Actualiza los estados de los clientes (Frecuentes)
    }
    
    /*Todos los archivos se guardan en un archivo csv*/
    
    public void guardarDatos(Veterinaria veterinaria){
        crearDirectorios(); //Me aseguro de que exista el directorio antes de guardar datos
        guardarClientes(veterinaria); //Se guardan los datos de los clientes
        guardarMascotas(veterinaria); //Se guardan los datos de las mascotas
        guardarServicios(veterinaria); //Se guardan los datos de servicios
    }
    
    /*Métodos de carga privados, solo usables por la clase*/
    
//==============================  CARGA CLIENTES  ==============================
    
    private void cargarClientes(Veterinaria veterinaria){ //Cargar clientes desde archivo csv
        try(BufferedReader lector = new BufferedReader(new FileReader(RUTA_CLIENTES))){
            String linea; //Para almacenar cada linea
            boolean primeraLinea = true;  //Para saltar header del csv
            while((linea = lector.readLine()) != null){ //Se lee linea por linea hasta que no queden
                if(primeraLinea){ //Si es primera linea
                    primeraLinea = false; //Se marca que ya se proceso el header
                    continue; //Salta a la siguiente iteracion
                }
                procesarLineaCliente(linea, veterinaria); //Se procesa cada una de ellas
            }
        }catch(IOException e){ //Para capturar errores de lectura de archivo
            System.out.println("No hay datos de clientes o error en la lectura: " + e.getMessage()); //Linea de control (Debugging/CONSOLA)
        }
    }
    
    private void procesarLineaCliente(String linea, Veterinaria veterinaria){ //Procesar linea con datos de cliente
        String[] partes = linea.split(","); //Se divide la linea usando la como separador, que es el formato del csv
        if(partes.length >= 4){ //Se verifica que existan el minimo de campos
            String rut = partes[0]; //Rut
            String nombre = partes[1]; //Nombre
            String telefono = partes[2]; //Teléfono
            String direccion = partes[3]; //Dirección
            
            if(partes.length >= 7 && "FRECUENTE".equals(partes[4])){ //Se verifica si el cliente es frecuente, que tiene mas campos y un tipo especifico
                crearClienteFrecuente(rut, nombre, telefono, direccion, partes, veterinaria); //Se agregar el cliente frecuente
            }
            else{
                veterinaria.agregarCliente(nombre, rut, telefono, direccion); //Si no es frecuente, significa que es regular, y se agrega con los datos basicos
            }                  
        }
    }
    
    private void crearClienteFrecuente(String rut, String nombre, String telefono, String direccion, String[] partes, Veterinaria veterinaria) { //Para crear un cliente frecuente
        try{ //Se extraen datos especificos de los clientes frecuentes
            int visitas = Integer.parseInt(partes[5]); //Número de visitas
            String fechaUltima = partes[6]; //Fecha de la última visita
            
            if(veterinaria.agregarCliente(nombre, rut, telefono, direccion)){ //Verifica y crea al cliente regular
                veterinaria.promoverAClienteFrecuente(rut, visitas, fechaUltima); //Promueve al cliente regular a frecuente
            }
        }catch(NumberFormatException e){ //Si hay una falla de conversión númerica
            veterinaria.agregarCliente(nombre, rut, telefono, direccion); //Se crea un cliente regular como respaldo
        }
    }

//==============================  CARGA MASCOTAS  ============================== 
    
    private void cargarMascotas(Veterinaria veterinaria){ //Cargar masctoas desde archivo csv
        try(BufferedReader lector = new BufferedReader(new FileReader(RUTA_MASCOTAS))){
            String linea; //Para almacenar cada linea
            boolean primeraLinea = true; //Para saltar header del csv     
            while ((linea = lector.readLine()) != null) { //Se lee linea por linea hasta que no queden
                if(primeraLinea){ //Si es primera linea
                    primeraLinea = false; //Se marca que ya se proceso el header
                    continue; //Salta a la siguiente iteracion
                }
                procesarLineaMascota(linea, veterinaria); //Se procesa cada una de ellas
            }
        }catch(Exception e){ //Para capturar errores de lectura de archivo
            System.out.println("Error al cargar mascotas: " + e.getMessage()); //Linea de control (Debugging/CONSOLA)
        }
    }
    
    private void procesarLineaMascota(String linea, Veterinaria veterinaria){
        String[] partes = linea.split(","); //Se divide la linea usando la como separador, que es el formato del csv
        if(partes.length >= 5){ //Se verifica que existan el minimo de campos
            String rutDueno = partes[0]; //Rut del dueño
            Cliente dueno = veterinaria.buscarClientePorRut(rutDueno); //Busca al cliente dueño           
            if(dueno != null){ //Si dueño existe, es decir, no es null
                String nombre = partes[1]; //Nombre mascota
                String tipo = partes[2]; //Tipo (Perro, Gato, etc).
                String raza = partes[3]; //Raza (Mezcla, Pug, Labrador, etc).
                int edad = Integer.parseInt(partes[4]); //Edad
                
                if(partes.length >= 7 && "true".equals(partes[5])){ //Se verifica si la mascota es geriatrica, que tiene mas campos y un tipo especifico
                    String fechaInicio = partes[6]; //Fecha de inicio de cuidados
                    veterinaria.agregarMascotaGeriatrica(rutDueno, nombre, tipo, raza, edad, fechaInicio); //Se agrega la mascota geriatrica              
                }
                else{
                    dueno.agregarMascota(new Mascota(nombre, tipo, raza, edad)); //Si no es geriatrica, significa que es regular, y se agrega con los datos basicos
                }
            }
        }
    }

//=============================  CARGA SERVICIOS  ============================== 
  
    private void cargarServicios(Veterinaria veterinaria) { //Cargar servicios desde archivo csv
        try(BufferedReader lector = new BufferedReader(new FileReader(RUTA_SERVICIOS))) {
            String linea; //Para almacenar cada linea
            boolean primeraLinea = true; //Para saltar header del csv          
            while ((linea = lector.readLine()) != null) { //Se lee linea por linea hasta que no queden
                if(primeraLinea){ //Si es primera linea
                    primeraLinea = false; //Se marca que ya se proceso el header
                    continue; //Salata a la siguiente iteración
                }
                procesarLineaServicio(linea, veterinaria); //Se procesa cada una de ellas
            }
        }catch(IOException e){ //Para capturar errores de lectura de archivo
            System.out.println("No hay datos de servicios o error en lectura: " + e.getMessage()); //Linea de control (Debugging/CONSOLA)
        }
    }
    
    private void procesarLineaServicio(String linea, Veterinaria veterinaria) {
        String[] partes = linea.split(","); //Se divide la linea usando la como separador, que es el formato del csv
        if (partes.length >= 8) { //Se verifica que existan el minimo de campos
            String rutDueno = partes[0]; //Rut del dueño
            String nombreMascota = partes[1]; //NombreMascota            
            Cliente dueno = veterinaria.buscarClientePorRut(rutDueno); //Busca al cliente dueño         
            if (dueno != null) { //Si dueño existe, es decir, no es null
                Mascota mascota = dueno.buscarMascotaPorNombre(nombreMascota); //Buscar masctoa del cliente
                if (mascota != null) { //Si mascota existe, es decir, no es null
                    crearServicio(partes, mascota); //Se crea el servicio
                }
            }
        }
    }
    
    private void crearServicio(String[] partes, Mascota mascota) {
        String tipoServicio = partes[2]; //Tipo de servicio
        String fecha = partes[3]; //Fecha del servicio
        String hora = partes[4]; //Hora del servicio
        String descripcion = partes[5]; //Descripción del servicio
        int precio = Integer.parseInt(partes[6]); //Precio del servicio
        String estado = partes[7]; //Estado del servicio (Pendiente/Realizado)
        
        if (partes.length >= 12 && "true".equals(partes[8])) { //Se verifica si el servicio es de urgencia, que tiene mas campos y un tipo especifico
            crearServicioUrgencia(partes, mascota, tipoServicio, fecha, hora, descripcion, precio, estado); //Se invoca metodo para crear servicio de urgencia
        }
        else{
            mascota.agregarServicio(new Servicio(tipoServicio, fecha, hora, descripcion, precio, estado)); //Se agrega servicio regular
        }
    }
    
    private void crearServicioUrgencia(String[] partes, Mascota mascota, String tipoServicio, String fecha, String hora, String descripcion, int precio, String estado) { 
        try{
            int nivelUrgencia = Integer.parseInt(partes[9]); //Nivel de urgencia
            String motivoUrgencia = partes[10]; //Motivo de la urgencia
            boolean requiereInmediata = Boolean.parseBoolean(partes[11]); //Si es que requiere atención inmediata
            
            ServicioUrgencia servicioUrgencia = new ServicioUrgencia(tipoServicio, fecha, hora, descripcion, precio, estado, nivelUrgencia, motivoUrgencia, requiereInmediata); //Se crea el servicio urgencia
            mascota.agregarServicio(servicioUrgencia); //Se agrega el servicio de urgencia a la mascota        
        } catch (NumberFormatException e) { //Si hay una falla de conversión númerica
            mascota.agregarServicio(new Servicio(tipoServicio, fecha, hora, descripcion, precio, estado)); //Se crea un servicio regular como respaldo
        }
    }
    
    /*Métodos de guardado privados, solo usables por la clase*/
    
//============================  GUARDADO CLIENTES  =============================
    
    private void guardarClientes(Veterinaria veterinaria) {
        try (PrintWriter escritor = new PrintWriter(new FileWriter(RUTA_CLIENTES))) {
            escritor.println("rut, nombre, telefono, direccion,tipoCliente,numeroVisitas,ultimaVisita"); //Para escribir el header
            
            for (Cliente cliente : veterinaria.getClientes()) { //Se recorren todos los clientes
                String linea = construirLineaCliente(cliente); //Se construye linea csv
                escritor.println(linea); //Se escribe la linea en el archivo
            }
        } catch (IOException e) { //Error al escribir el archivo 
            System.out.println("Error al guardar clientes: " + e.getMessage()); //Linea de control (Debugging/CONSOLA)
        }
    }
    
    private String construirLineaCliente(Cliente cliente) { //Se separan los datos por comas
        String linea = cliente.getRut() + "," + //Rut
                      cliente.getNombre() + "," + //Nombre
                      cliente.getTelefono() + "," + //Telefono
                      cliente.getDireccion(); //Dirección
        
        if (cliente instanceof ClienteFrecuente) { //Para ver si es cliente frecuente
            ClienteFrecuente cf = (ClienteFrecuente) cliente; //Casting para seguridad
            linea += ",FRECUENTE," + cf.getNumeroVisitasAnuales() + "," + cf.getFechaUltimaVisita(); //Numero de visitas + Fecha ultima visita
        } else {
            linea += ",REGULAR,,"; //Tipo regular, los otros campos quedan vacios
        }       
        return linea; //Retorna linea completa
    }
    
//============================  GUARDADO MASCOTAS  =============================
    
    private void guardarMascotas(Veterinaria veterinaria) {
        try (PrintWriter escritor = new PrintWriter(new FileWriter(RUTA_MASCOTAS))) {
            escritor.println("rut_dueno,nombre,tipo,raza,edad,es_geriatrica,fecha_inicio_geriatria"); //Para escribir el header
            
            for (Cliente cliente : veterinaria.getClientes()) { //Se recorren todos los clientes
                for (Mascota mascota : cliente.getMascotas()) { //Se recorren las mascota de cada cliente
                    String linea = construirLineaMascota(cliente, mascota); //Se construye linea
                    escritor.println(linea); //Se escribe linea en el archivo
                }
            }
        } catch (IOException e) { //Error al escribir el archivo
            System.out.println("Error al guardar mascotas: " + e.getMessage()); //Linea de control (Debugging/CONSOLA)
        }
    }
    
    private String construirLineaMascota(Cliente cliente, Mascota mascota) {
        String esGeriatrica = "false"; //Por defecto la masctoa no es geriatrica
        String fechaInicio = ""; //Campo vacio por defecto
        
        if (mascota instanceof MascotaGeriatrica) { //Verifica si mascota es geriatrica
            esGeriatrica = "true"; //De serlo la marca como geriatrica
            fechaInicio = ((MascotaGeriatrica) mascota).getFechaInicioGeriatria(); //Obtiene la fecha de inicio de los cuidados
        }
        //Consturye linea con los campos, separadaos por comas
        return cliente.getRut() + "," + //Rut del dueño
               mascota.getNombre() + "," + //Nombre de la mascota
               mascota.getTipo() + "," + //Tipo de la mascota
               mascota.getRaza() + "," + //Raza de la mascota
               mascota.getEdad() + "," + //Edad de la mascota
               esGeriatrica + "," + //Para indicar si es geriatrica
               fechaInicio; //Fecha de inicio, que puede quedar vacio
    }

//============================  GUARDADO SERVICIOS  ============================
    
    private void guardarServicios(Veterinaria veterinaria) {
        try (PrintWriter escritor = new PrintWriter(new FileWriter(RUTA_SERVICIOS))) {
            escritor.println("rutDueno,nombreMascota,servicio,fechaServicio,horaServicio,descripcion,precio,estado,esUrgencia,nivelUrgencia,motivoUrgencia,requiereInmediata"); //Para escribir el header
            
            for (Cliente cliente : veterinaria.getClientes()) { //Se recorren todos los clientes
                for (Mascota mascota : cliente.getMascotas()) { //Se recorren las mascotas de cada cliente
                    for (Servicio servicio : mascota.getServicios()) { //Se recorren los servicios de las mascotas
                        String linea = construirLineaServicio(cliente, mascota, servicio); //Se construye linea
                        escritor.println(linea); //Se escribe linea en el archivo
                    }
                }
            }
        } catch (IOException e) { //Eror al escribir el archivo
            System.out.println("Error al guardar servicios: " + e.getMessage()); //Linea de control (Debugging/CONSOLA)
        }
    }
    
    private String construirLineaServicio(Cliente cliente, Mascota mascota, Servicio servicio) { //Se separan los datos por coma
        String linea = cliente.getRut() + "," + //Rut del dueño
                      mascota.getNombre() + "," + //Nombre mascota
                      servicio.getTipoServicio() + "," + //Tipo servicio
                      servicio.getFecha() + "," + ///Fecha
                      servicio.getHora() + "," + //Hora
                      servicio.getDescripcion() + "," + //Descripción 
                      servicio.getPrecio() + "," + //Precio
                      servicio.getEstado(); //Estado
        
        if (servicio instanceof ServicioUrgencia) {
            ServicioUrgencia urgencia = (ServicioUrgencia) servicio; //Casting para asegurarse
            linea += ",true," +  //En caso de ser de urgencia
                    urgencia.getNivelUrgencia() + "," + //Nivel de urgencia
                    urgencia.getMotivoUrgencia() + "," + //Motivo
                    urgencia.isRequiereAtencionInmediata(); //Si es atención inmediata
        } else {
            linea += ",false,,,"; //En caso de no ser de urgencia, se dejan el resto de campos vacios
        }       
        return linea; //Retornar linea completa
    }
}
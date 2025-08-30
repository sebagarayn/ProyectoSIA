//Ultima revision: 29-08-2025
package proyectosia;
import java.io.*;

public class ProyectoSIA {
    
    public static void main(String[] args) throws IOException {
        Veterinaria veterinaria = new Veterinaria(); //Para iniciar la veterinaria, con su lista de clientes y mapa.
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in)); //Para la lectura de datos.

        veterinaria.cargarDatosIniciales(); //Para cargar los datos inciales.
        
        int opcion = 0; //Opcion por defecto para entrar al bucle del menu.
        do {
            System.out.println("===== MENU PRINCIPAL =====");
            System.out.println("1. Agregar cliente");
            System.out.println("2. Agregar mascota a cliente");
            System.out.println("3. Agregar servicio a mascota");
            System.out.println("4. Listar clientes y mascotas");
            System.out.println("0. Salir");
            System.out.println("");
            System.out.println("Ingrese opcion: ");
            System.out.println("");
            try { //Para el manejo de excepciones utilizando try-catch.
                opcion = Integer.parseInt(lector.readLine());
            } catch (Exception exception) {
                System.out.println("");
                System.out.println("Error, por favor ingrese una de las opciones");
                System.out.println("");
                opcion = -1;
                continue; //Para volver al inicio
            }
            switch(opcion) {
                case 1: //Agregar cliente
                    System.out.print("Nombre: ");
                    String nombre = lector.readLine();
                    System.out.print("RUT: ");
                    String rut = lector.readLine();
                    System.out.print("Telefono: ");
                    String telefono = lector.readLine();
                    System.out.print("Direccion: ");
                    String direccion = lector.readLine();                   
                    if(veterinaria.agregarCliente(nombre, rut, telefono, direccion)){ //Si no existe el cliente se agrega
                        System.out.println("Cliente agregado.");
                    }
                    break;                  
                case 2: //Agregar mascota a cliente
                    System.out.print("RUT del dueno: ");
                    rut = lector.readLine();
                    Cliente dueno = veterinaria.buscarClientePorRut(rut);
                    if (dueno != null) {
                        System.out.print("Nombre mascota: ");
                        String nombreM = lector.readLine();
                        System.out.print("Tipo: ");
                        String tipo = lector.readLine();
                        System.out.print("Raza: ");
                        String raza = lector.readLine();
                        System.out.print("Edad: ");
                        int edad = Integer.parseInt(lector.readLine());                       
                        dueno.agregarMascota(nombreM, tipo, raza, edad);
                        System.out.println("Mascota agregada.");
                    } else {
                        System.out.println("Cliente no encontrado.");
                    }
                    break;
                case 3: //Agregar servicio a mascota
                    System.out.print("RUT del dueno: ");
                    rut = lector.readLine();
                    dueno = veterinaria.buscarClientePorRut(rut);
                    if (dueno != null) {
                        System.out.print("Nombre de la mascota: ");
                        String nombreM = lector.readLine();
                        Mascota encontrada = dueno.buscarMascotaPorNombre(nombreM);
                        if (encontrada != null) {
                            System.out.print("Tipo de servicio: ");
                            String tipoServ = lector.readLine();
                            System.out.print("Fecha-hora: ");
                            String fecha = lector.readLine();
                            System.out.print("Descripcion: ");
                            String desc = lector.readLine();
                            System.out.print("Precio: ");
                            int precio = Integer.parseInt(lector.readLine());
                            System.out.print("Estado (Pendiente/Realizado): ");
                            String estado = lector.readLine();
                            encontrada.agregarServicio(tipoServ, fecha, desc, precio, estado);
                            System.out.println("Servicio agregado.");
                        } else {
                            System.out.println("Mascota no encontrada.");
                        }
                    } else {
                        System.out.println("Cliente no encontrado.");
                    }
                    break;
                case 4: //Listar clientes y mascotas, tambien los servicios de cada una
                    veterinaria.mostrarClientesMascotas();
                    break;
                case 0: //Para los casos de default y opciones invalidad
                    System.out.println("Cerrando el pograma...");
                    break;
                default:
                    System.out.println("Opcion invalida.");
            }
            System.out.println();
        } while (opcion != 0);       
        lector.close(); //Para cerrar el lector
    }
}
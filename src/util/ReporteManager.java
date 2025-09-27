package util;

import java.io.*;
import modelo.*;
import java.util.HashMap;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class ReporteManager {

    private Veterinaria veterinaria;

    public ReporteManager(Veterinaria veterinaria){
        this.veterinaria = veterinaria;
    }
    
    private String generarNombreConFechaHora(String nombrePersonalizado) {
        LocalDateTime ahora = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        String timestamp = ahora.format(formatter);
        
        if (nombrePersonalizado.endsWith(".txt")) {
            nombrePersonalizado = nombrePersonalizado.substring(0, nombrePersonalizado.lastIndexOf("."));
        }
        return nombrePersonalizado + "_" + timestamp + ".txt";
    }

    public String generarReporte(){
    StringBuilder reporte = new StringBuilder();

    reporte.append("==== Reporte Veterinaria ====\n");
    for(Cliente cliente: veterinaria.getClientes()){
        reporte.append("Cliente: ").append(cliente.getNombre()).append("\n");
        reporte.append("RUT: ").append(cliente.getRut()).append("\n");
        reporte.append("Telefono: ").append(cliente.getTelefono()).append("\n");
        reporte.append("Direccion: ").append(cliente.getDireccion()).append("\n\n");

        if(cliente.getMascotas().isEmpty()){
            reporte.append("No tiene mascotas.\n");
        }else{
            for(Mascota mascota: cliente.getMascotas()){
                reporte.append("Mascota: ").append(mascota.getNombre()).append("\n");
                reporte.append("Tipo: ").append(mascota.getTipo()).append("\n");
                reporte.append("Raza: ").append(mascota.getRaza()).append("\n");
                reporte.append("Edad: ").append(mascota.getEdad()).append(" años\n");

                if(mascota.getServicios().isEmpty()){
                    reporte.append("Servicios: Ninguno\n");
                }else{
                    reporte.append("Servicios:\n");
                    for(Servicio servicio: mascota.getServicios()){
                            reporte.append("Tipo: ").append(servicio.getTipoServicio()).append("\n");
                            reporte.append("Fecha: ").append(servicio.getFecha()).append("\n");
                            reporte.append("Hora: ").append(servicio.getHora()).append("\n");
                            reporte.append("Descripcion: ").append(servicio.getDescripcion()).append("\n");
                            reporte.append("Precio: ").append(servicio.getPrecio()).append("\n");
                            reporte.append("Estado: ").append(servicio.getEstado()).append("\n");
                            reporte.append("------------------------\n");
                    }
                }
                reporte.append("\n");
            }
        }
        reporte.append("\n");
    }
    reporte.append(generarEstadisticas());
    return reporte.toString();
    }

    public void guardarReportePersonalizado(String nombrePersonalizado){

        String nombreArchivo = generarNombreConFechaHora(nombrePersonalizado);
        File carpeta = new File("Reportes");
        if(!carpeta.exists()){
            carpeta.mkdir();
        }
        String ruta = "Reportes/" + nombreArchivo;  
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(ruta))){
            writer.write(generarReporte());
        } catch(IOException e){
        }
    }

    private String generarEstadisticas() {
        int totalClientes = veterinaria.getClientes().size();
        int totalMascotas = 0;
        int totalServicios = 0;
        int totalIngresos = 0;
        HashMap<String, Integer> conteoServicios = new HashMap<>();

        for (Cliente cliente : veterinaria.getClientes()) {
            for (Mascota mascota : cliente.getMascotas()) {
                totalMascotas++;
                for (Servicio servicio : mascota.getServicios()) {
                    totalServicios++;
                    totalIngresos += servicio.getPrecio();

                // contar por tipo
                    conteoServicios.put(
                        servicio.getTipoServicio(),
                        conteoServicios.getOrDefault(servicio.getTipoServicio(), 0) + 1
                    );
                }
            }
        }

        StringBuilder resumen = new StringBuilder();
        resumen.append("\n==== ESTADÍSTICAS ====\n");
        resumen.append("Total Clientes: ").append(totalClientes).append("\n");
        resumen.append("Total Mascotas: ").append(totalMascotas).append("\n");
        resumen.append("Total Servicios: ").append(totalServicios).append("\n");
        resumen.append("Ingresos Totales: $").append(totalIngresos).append("\n");

        resumen.append("Servicios más solicitados:\n");
        for (String tipo : conteoServicios.keySet()) {
            resumen.append("  - ").append(tipo).append(": ")
                .append(conteoServicios.get(tipo)).append("\n");
        }

        return resumen.toString();
    }
}

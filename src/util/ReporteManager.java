package util;

import java.io.*;
import modelo.*;
import java.util.HashMap;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/*Clase encargada de generar reportes completos de la veterinaria
Incluye información de clientes, mascotas, servicios y estadísticas*/

public class ReporteManager {

    private Veterinaria veterinaria; //Constructor que recibe la instancia de veterinaria

    public ReporteManager(Veterinaria veterinaria){
        this.veterinaria = veterinaria;
    }
    
    private String generarNombreConFechaHora(String nombrePersonalizado) {  //Genera nombre de archivo con timestamp para evitar duplicados
        LocalDateTime ahora = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        String timestamp = ahora.format(formatter);
        
        if (nombrePersonalizado.endsWith(".txt")) {
            nombrePersonalizado = nombrePersonalizado.substring(0, nombrePersonalizado.lastIndexOf("."));
        }
        return nombrePersonalizado + "_" + timestamp + ".txt";
    }

    public String generarReporte(){ // Genera el contenido completo del reporte
    StringBuilder reporte = new StringBuilder();

    reporte.append("====  REPORTE COMPLETO VETERINARIA  ====\n");
    reporte.append("Fecha generación: ").append(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))).append("\n\n");
    for(Cliente cliente: veterinaria.getClientes()){
        reporte.append("====  CLIENTE  ====\n");
        reporte.append("Nombre: ").append(cliente.getNombre()).append("\n");
        reporte.append("RUT: ").append(cliente.getRut()).append("\n");
        reporte.append("Telefono: ").append(cliente.getTelefono()).append("\n");
        reporte.append("Direccion: ").append(cliente.getDireccion()).append("\n\n");
        reporte.append("Tipo Cliente: ").append(cliente.obtenerTipoCliente()).append("\n");
        if(cliente instanceof ClienteFrecuente){  //Mostrar información adicional si es cliente frecuente
            ClienteFrecuente frecuente = (ClienteFrecuente) cliente;
            reporte.append("Descuento aplicable: ").append((int)(frecuente.calcularDescuento() * 100)).append("%\n");
            reporte.append("Beneficios: ").append(frecuente.obtenerBeneficios()).append("\n");
        }
        reporte.append("\n");

        if(cliente.getMascotas().isEmpty()){ //Verificar si el cliente tiene mascotas
            reporte.append("No tiene mascotas.\n");
        }else{
            reporte.append("  MASCOTAS REGISTRADAS: ").append(cliente.getMascotas().size()).append("\n");
            reporte.append("----------------------------------------\n");
            for(Mascota mascota: cliente.getMascotas()){
                
                reporte.append("   ┌─ Nombre: ").append(mascota.getNombre()).append("\n");
                reporte.append("   ├─ Tipo: ").append(mascota.getTipo()).append("\n");
                reporte.append("   ├─ Raza: ").append(mascota.getRaza()).append("\n");
                reporte.append("   ├─ Edad: ").append(mascota.getEdad()).append(" años\n");
                
                if(mascota instanceof MascotaGeriatrica){ //Información adicional para mascotas geriátricas
                    MascotaGeriatrica geriatrica = (MascotaGeriatrica) mascota;
                    reporte.append("   ├─  ️ MASCOTA GERIÁTRICA  ️\n");
                    reporte.append("   ├─ Fecha inicio cuidados: ").append(geriatrica.getFechaInicioGeriatria()).append("\n");
                    reporte.append("   ├─ Cuidados especiales: ").append(geriatrica.obtenerCuidadosEspeciales()).append("\n");
                    reporte.append("   ├─ Frecuencia visitas: cada ").append(geriatrica.obtenerFrecuenciaVisitas()).append(" meses\n");
                    reporte.append("   ├─ Medicamentos habituales: ").append(geriatrica.getMedicamentosHabituales()).append("\n");
                    reporte.append("   ├─ Factor precio: ").append(String.format("%.2f", geriatrica.calcularFactorPrecio())).append("\n");
                }
                else{
                    reporte.append("   ├─ Categoría: Normal\n");
                    reporte.append("   ├─ Cuidados básicos: ").append(mascota.obtenerCuidadosEspeciales()).append("\n");
                    reporte.append("   ├─ Frecuencia visitas: cada ").append(mascota.obtenerFrecuenciaVisitas()).append(" meses\n");
                }

                if(mascota.getServicios().isEmpty()){ // Mostrar servicios recibidos por la mascota
                    reporte.append("   └─ Servicios: Ninguno registrado\n\n");
                }
                else{
                    reporte.append("\"   └─ SERVICIOS RECIBIDOS (").append(mascota.getServicios().size()).append("):\n");
                    for(Servicio servicio: mascota.getServicios()){
                            reporte.append("      ┌─ Tipo: ").append(servicio.getTipoServicio()).append("\n");
                            reporte.append("      ├─ Fecha: ").append(servicio.getFecha()).append(" - Hora: ").append(servicio.getHora()).append("\n");;
                            reporte.append("      ├─ Estado: ").append(servicio.getEstado()).append("\n");
                            reporte.append("      ├─ Descripción: ").append(servicio.getDescripcion()).append("\n");
                            reporte.append("      ├─ Precio: $").append(servicio.getPrecio()).append("\n");
                            reporte.append("      ├─ Estado: ").append(servicio.getEstado()).append("\n");
                            
                            if(servicio instanceof ServicioUrgencia){ //Información adicional para servicios de urgencia
                                ServicioUrgencia urgencia = (ServicioUrgencia) servicio;
                                reporte.append("      ├─   SERVICIO DE URGENCIA  \n");
                                reporte.append("      ├─ Nivel urgencia: ").append(urgencia.getNivelUrgencia()).append("/5\n");
                                reporte.append("      ├─ Motivo urgencia: ").append(urgencia.getMotivoUrgencia()).append("\n");
                                reporte.append("      ├─ Atención inmediata: ").append(urgencia.isRequiereAtencionInmediata() ? "SÍ" : "NO").append("\n");
                                reporte.append("      ├─ Precio base: $").append(servicio.getPrecio()).append("\n");
                                reporte.append("      ├─ Precio final con recargo: $").append(urgencia.calcularPrecioFinal()).append("\n");
                                reporte.append("      ├─ Instrucciones especiales: ").append(urgencia.obtenerInstruccionesEspeciales()).append("\n");
                                if(urgencia.esEmergenciaCritica()){
                                    reporte.append("      ├─   EMERGENCIA CRÍTICA  \n");
                                }
                                else{
                                    reporte.append("    ├─ Precio: $").append(servicio.getPrecio()).append("\n");
                                }
                                reporte.append("      └─────────────────────────────────────────\n");
                            }
                        }
                    }
                    reporte.append("\n");
                }
            }
            reporte.append("═══════════════════════════════════════════════════════════════\n\n");
        }
        reporte.append(generarEstadisticasAvanzadas());
        return reporte.toString();
    }

    public void guardarReportePersonalizado(String nombrePersonalizado){ //Guarda el reporte en un archivo con nombre personalizado

        String nombreArchivo = generarNombreConFechaHora(nombrePersonalizado);
        File carpeta = new File("Reportes");
        if(!carpeta.exists()){
            carpeta.mkdir();
        }
        String ruta = "Reportes/" + nombreArchivo;  
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(ruta))){
            writer.write(generarReporte());
            writer.flush(); 
            abrirArchivo(new File(ruta));
        } catch(IOException e){
            System.err.println("Error al guardar reporte: " + e.getMessage());
        }
    }
    
    private void abrirArchivo(File archivo) { //Intenta abrir el archivo generado automáticamente
        try {
            if (java.awt.Desktop.isDesktopSupported()) {
                java.awt.Desktop desktop = java.awt.Desktop.getDesktop();
                if (desktop.isSupported(java.awt.Desktop.Action.OPEN)) {
                    desktop.open(archivo);
                }
            }
        } catch (Exception e) {
            System.err.println("No se pudo abrir el archivo automáticamente: " + e.getMessage());
            System.out.println("El reporte se guardó en: " + archivo.getAbsolutePath());
        }
    }


    private String generarEstadisticasAvanzadas() { //Genera estadísticas detalladas de toda la veterinaria
        int totalClientes = veterinaria.getClientes().size();
        int clientesFrecuentes = 0;
        int clientesRegulares = 0;
        int totalMascotas = 0;
        int mascotasGeriatricas = 0;
        int mascotasNormales = 0;
        int totalServicios = 0;
        int serviciosUrgencia = 0;
        int serviciosNormales = 0;
        int totalIngresos = 0;
        int ingresosConDescuento = 0;
        int ingresosUrgencia = 0;

        HashMap<String, Integer> conteoServicios = new HashMap<>();
        HashMap<String, Integer> conteoTiposMascotas = new HashMap<>(); 
        HashMap<String, Integer> conteoRazas = new HashMap<>();

        for (Cliente cliente : veterinaria.getClientes()) {
            if(cliente instanceof ClienteFrecuente){
                clientesFrecuentes++;
            }
            else{
                clientesRegulares++;
            }
            for (Mascota mascota : cliente.getMascotas()) {
                totalMascotas++;
                if(mascota instanceof MascotaGeriatrica){
                    mascotasGeriatricas++;
                }
                else{
                    mascotasNormales++;
                }
                conteoTiposMascotas.put(
                        mascota.getTipo(),
                        conteoTiposMascotas.getOrDefault(mascota.getTipo(), 0) + 1
                );
                conteoRazas.put(
                        mascota.getRaza(),
                        conteoRazas.getOrDefault(mascota.getRaza(), 0) + 1
                );
                for (Servicio servicio : mascota.getServicios()) {
                    totalServicios++;
                    if(servicio instanceof ServicioUrgencia){
                        serviciosUrgencia++;
                        ServicioUrgencia urgencia = (ServicioUrgencia) servicio;
                        ingresosUrgencia += urgencia.calcularPrecioFinal();
                        totalIngresos += urgencia.calcularPrecioFinal();
                    }
                    else{
                        serviciosNormales++;
                        totalIngresos += servicio.getPrecio();
                    }
                    
                    if(cliente instanceof ClienteFrecuente){
                        ClienteFrecuente frecuente = (ClienteFrecuente) cliente;
                        ingresosConDescuento += (int)(servicio.getPrecio() * frecuente.calcularDescuento());
                    }
                // contar por tipo
                    conteoServicios.put(
                        servicio.getTipoServicio(),
                        conteoServicios.getOrDefault(servicio.getTipoServicio(), 0) + 1
                    );
                }
            }
        }

        StringBuilder resumen = new StringBuilder();
        resumen.append("\n=================================================\n");
        resumen.append("\n==== ESTADÍSTICAS COMPLETAS ====\n");
        resumen.append("\n=================================================\n");
        
        resumen.append("  CLIENTES:\n");
        resumen.append("   Total: ").append(totalClientes).append("\n");
        resumen.append("   • Regulares: ").append(clientesRegulares).append(" (").append(String.format("%.1f", (clientesRegulares * 100.0 / totalClientes))).append("%)\n");
        resumen.append("   • Frecuentes: ").append(clientesFrecuentes).append(" (").append(String.format("%.1f", (clientesFrecuentes * 100.0 / totalClientes))).append("%)\n\n");
        
        resumen.append("  MASCOTAS:\n");
        resumen.append("   Total: ").append(totalMascotas).append("\n");
        resumen.append("   • Normales: ").append(mascotasNormales).append(" (").append(String.format("%.1f", (mascotasNormales * 100.0 / totalMascotas))).append("%)\n");
        resumen.append("   • Geriátricas: ").append(mascotasGeriatricas).append(" (").append(String.format("%.1f", (mascotasGeriatricas * 100.0 / totalMascotas))).append("%)\n");
        resumen.append("   Promedio por cliente: ").append(String.format("%.1f", (totalMascotas * 1.0 / totalClientes))).append("\n\n");

        resumen.append(" ️ SERVICIOS:\n");
        resumen.append("   Total: ").append(totalServicios).append("\n");
        resumen.append("   • Normales: ").append(serviciosNormales).append(" (").append(String.format("%.1f", (serviciosNormales * 100.0 / totalServicios))).append("%)\n");
        resumen.append("   • Urgencias: ").append(serviciosUrgencia).append(" (").append(String.format("%.1f", (serviciosUrgencia * 100.0 / totalServicios))).append("%)\n");
        resumen.append("   Promedio por mascota: ").append(String.format("%.1f", (totalServicios * 1.0 / totalMascotas))).append("\n\n");
        
        resumen.append("  INGRESOS:\n");
        resumen.append("   Total: $").append(String.format("%,d", totalIngresos)).append("\n");
        resumen.append("   • Servicios normales: $").append(String.format("%,d", (totalIngresos - ingresosUrgencia))).append("\n");
        resumen.append("   • Servicios urgencia: $").append(String.format("%,d", ingresosUrgencia)).append("\n");
        resumen.append("   Descuentos otorgados: $").append(String.format("%,d", ingresosConDescuento)).append("\n");        
        resumen.append("   Ingreso promedio por servicio: $").append(String.format("%,d", (totalIngresos / totalServicios))).append("\n\n");
        resumen.append("                         FIN DEL REPORTE                       \n");
        return resumen.toString();
    }
}

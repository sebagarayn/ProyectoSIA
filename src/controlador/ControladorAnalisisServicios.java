package controlador;

import modelo.Veterinaria;
import modelo.Servicio;
import modelo.Cliente;
import modelo.Mascota;
import modelo.ClienteFrecuente;
import modelo.MascotaGeriatrica;
import modelo.ServicioUrgencia;
import vista.VentanaAnalisisServicios;
import exception.RangoInvalidoException;
import exception.ListaClientesVaciaException;
import javax.swing.*;
import java.util.List;


public class ControladorAnalisisServicios {
    private Veterinaria modelo;
    private VentanaAnalisisServicios vista;
    
    public ControladorAnalisisServicios(Veterinaria modelo, VentanaAnalisisServicios vista) {
        this.modelo = modelo;
        this.vista = vista;
        configurarEventos();
    }
    
    private void configurarEventos() {
        vista.setBuscarListener(e -> buscarServicios());
        vista.setLimpiarListener(e -> limpiarFiltros());
        vista.setEstadisticasListener(e -> mostrarEstadisticas());
        vista.setCerrarListener(e -> cerrarVentana());
    }
    
    private void buscarServicios() {
        try {
            double precioMin = vista.getPrecioMinimo();
            double precioMax = vista.getPrecioMaximo();
            boolean soloFrecuentes = vista.isSoloClientesFrecuentes();
            boolean soloGeriatricas = vista.isSoloMascotasGeriatricas();
            boolean soloUrgencia = vista.isSoloServiciosUrgencia();
            
            // Validar rango
            if (precioMin > precioMax) {
                vista.mostrarError("El precio mínimo no puede ser mayor al máximo");
                return;
            }
            
            List<Servicio> servicios = modelo.filtroAvanzadoServicios(
                precioMin, precioMax, soloFrecuentes, soloGeriatricas, soloUrgencia
            );
            
            vista.mostrarResultados(servicios, modelo);
            
        } catch (NumberFormatException ex) {
            vista.mostrarError("Por favor ingrese valores numéricos válidos");
        } catch (RangoInvalidoException | ListaClientesVaciaException ex) {
            vista.mostrarError(ex.getMessage());
        }
    }
    
    private void limpiarFiltros() {
        vista.limpiarCampos();
    }
    
    private void mostrarEstadisticas() {
        List<Servicio> servicios = vista.getServiciosMostrados();
        if (servicios == null || servicios.isEmpty()) {
            vista.mostrarError("No hay datos para mostrar estadísticas");
            return;
        }
        
        double[] stats = modelo.calcularEstadisticas(servicios);
        int[] tipos = modelo.contarTipos(servicios);
        List<Cliente> clientesUnicos = modelo.obtenerClientesUnicos(servicios);
        List<Mascota> mascotasUnicas = modelo.obtenerMascotasUnicas(servicios);
        
        StringBuilder mensaje = new StringBuilder();
        mensaje.append("=== ESTADÍSTICAS ===\n\n");
        mensaje.append(String.format("Total servicios: %d\n", servicios.size()));
        mensaje.append(String.format("Precio promedio: $%.2f\n", stats[0]));
        mensaje.append(String.format("Ingreso total: $%.2f\n\n", stats[1]));
        mensaje.append("Distribución:\n");
        mensaje.append(String.format("• Clientes únicos: %d\n", clientesUnicos.size()));
        mensaje.append(String.format("• Mascotas únicas: %d\n", mascotasUnicas.size()));
        mensaje.append(String.format("• Clientes frecuentes: %d\n", tipos[0]));
        mensaje.append(String.format("• Mascotas geriátricas: %d\n", tipos[1]));
        mensaje.append(String.format("• Servicios urgencia: %d\n", tipos[2]));
        
        vista.mostrarEstadisticas(mensaje.toString());
    }   
    private void cerrarVentana() {
    vista.dispose(); // Cierra la ventana
    }
}



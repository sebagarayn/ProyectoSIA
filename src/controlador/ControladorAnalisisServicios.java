package controlador;

import modelo.*;
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
            validarListaClientes();
            double precioMin = vista.getPrecioMinimo();
            double precioMax = vista.getPrecioMaximo();
            boolean soloFrecuentes = vista.isSoloClientesFrecuentes();
            boolean soloGeriatricas = vista.isSoloMascotasGeriatricas();
            boolean soloUrgencia = vista.isSoloServiciosUrgencia();
            
            // Validar rango
            validarRangoPrecio(precioMin, precioMax);
            
            List<Servicio> servicios = modelo.filtroAvanzadoServicios(
                precioMin, precioMax, soloFrecuentes, soloGeriatricas, soloUrgencia
            );
            
            vista.mostrarResultados(servicios, modelo);
            
        } catch (NumberFormatException ex) {
            vista.mostrarError("Por favor ingrese valores numéricos válidos");
        } catch (RangoInvalidoException ex) {
            vista.mostrarError(ex.getMessage());
        } catch (ListaClientesVaciaException ex){
            vista.mostrarError(ex.getMessage());
        } catch (Exception ex){
            vista.mostrarError("Error inesperado durante el análisis: " + ex.getMessage());
        }
    }
    
    private void limpiarFiltros() {
        vista.limpiarCampos();
    }
    
    private void mostrarEstadisticas() {
        
        try {
            List<Servicio> servicios = vista.getServiciosMostrados();
            if (servicios == null || servicios.isEmpty()) {
                vista.mostrarError("No hay datos para mostrar estadísticas.\nRealice una búsqueda primero.");
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
        }catch(Exception ex){
            vista.mostrarError("Error al calcular estadísticas: " + ex.getMessage());
        }
    }   
    
    private void validarRangoPrecio(double precioMin, double precioMax) throws RangoInvalidoException {
        if (precioMin < 0) {
            throw new RangoInvalidoException("El precio mínimo no puede ser negativo");
        }
        if (precioMax < 0) {
            throw new RangoInvalidoException("El precio máximo no puede ser negativo");
        }
        if (precioMin > precioMax) {
            throw new RangoInvalidoException("El precio mínimo no puede ser mayor al precio máximo");
        }
        if (precioMax > 2000000) { // Límite máximo razonable para análisis
            throw new RangoInvalidoException("El precio máximo parece demasiado alto para análisis");
        }
    }

    private void validarListaClientes() throws ListaClientesVaciaException {
        if (modelo.getClientes() == null || modelo.getClientes().isEmpty()) {
            throw new ListaClientesVaciaException("No hay clientes registrados para realizar el análisis");
        }
    }
    
    private void cerrarVentana() {
    vista.dispose(); // Cierra la ventana
    }
}



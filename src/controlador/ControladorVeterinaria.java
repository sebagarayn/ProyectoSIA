//Rev.24-09
package controlador;

import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.*;
import vista.*;
import java.util.List;
import java.util.ArrayList;
import util.ReporteManager;
import persistencia.GestorCSV;
import exception.*;

/*Controlador principal de la aplicación Veterinaria.
Maneja todos los eventos de menú y coordina las operaciones
entre la vista (ventanas) y el modelo (clases de negocio).*/

public class ControladorVeterinaria implements ActionListener {
    private Veterinaria veterinaria; //Modelo principal que gestiona clientes, mascotas y servicios
    private GestorCSV gestorCSV; //Clase para cargar/guardar datos en archivos CSV
    private VentanaMain main; //Ventana principal
    
    //VENTANAS CLIENTES
    private VentanaAgregarCliente agregarCliente;
    private VentanaBuscarCliente buscarCliente;
    private VentanaEditarCliente editarCliente;
    private VentanaEliminarCliente eliminarCliente;
    
    //VENTANAS MASCOTAS
    private VentanaAgregarMascota agregarMascota;
    private VentanaBuscarMascota buscarMascota;
    private VentanaEditarMascota editarMascota;
    private VentanaEliminarMascota eliminarMascota;
    
    //VENTANAS SERVICIOS
    private VentanaAgregarServicio agregarServicio;
    private VentanaBuscarServicio buscarServicio;
    private VentanaEditarServicio editarServicio;
    private VentanaEliminarServicio eliminarServicio;
    
    //VENTANAS REPORTES Y ANALISIS
    private VentanaListarClientes listarClientes;
    private VentanaListarMascotas listarMascotas;
    private VentanaListarServicios listarServicios;
    private VentanaListarClientesFrecuentes listarClientesFrecuentes;
    
    /*Inicializa la aplicación:
    - Crea el modelo y carga datos desde CSV.
    - Configura la ventana principal y registra los ActionListener
    en cada opción de menú.*/    

    public void iniciar() {
        veterinaria = new Veterinaria(); //Iniciar veterinaria
        gestorCSV = new GestorCSV(); //Inicio del gestor de datos
        
        gestorCSV.cargarDatos(veterinaria); //Se cargan los clientes, mascotas y servicios
        veterinaria.verificarPromocionesPendientes(); //Se actualizan los estados
        
        main = new VentanaMain();
        //Asociar listeners a cada ítem de menú
        //CLIENTES
        main.getjMenuItemAgregarCliente().addActionListener(this);
        main.getjMenuItemBuscarCliente().addActionListener(this);
        main.getjMenuItemEditarCliente().addActionListener(this);
        main.getjMenuItemEliminarCliente().addActionListener(this);
        
        //MASCOTAS
        main.getjMenuItemAgregarMascota().addActionListener(this);
        main.getjMenuItemBuscarMascota().addActionListener(this);
        main.getjMenuItemEditarMascota().addActionListener(this);
        main.getjMenuItemEliminarMascota().addActionListener(this);
        
        //SERVICIOS
        main.getjMenuItemAgregarServicio().addActionListener(this);
        main.getjMenuItemBuscarServicio().addActionListener(this);
        main.getjMenuItemEditarServicio().addActionListener(this);
        main.getjMenuItemEliminarServicio().addActionListener(this);
        
        //REPORTES
        main.getjMenuItemListarClientes().addActionListener(this);
        main.getjMenuItemListarMascotas().addActionListener(this);
        main.getjMenuItemListarServicios().addActionListener(this);
        main.getjMenuItemListarClientesFrecuentes().addActionListener(this);
        main.getJMenuItemGenerarReporte().addActionListener(this);
        main.getjMenuItemAnalisisServicios().addActionListener(this);
        
        //SALIR
        main.getjMenuItemSalir().addActionListener(this);
        
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main.setVisible(true);
        
        main.setSize(800,600);
        main.setLocationRelativeTo(null);
        main.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
             
//==============================  MENU CLIENTES  ===============================

        //=========================  AGREGAR CLIENTE  ==========================      
        if (ae.getSource() == main.getjMenuItemAgregarCliente()) {
            agregarCliente = new VentanaAgregarCliente();
            agregarCliente.getjButtonAgregar().addActionListener(this);
            agregarCliente.getjButtonCancelar().addActionListener(this);
            agregarCliente.setAlwaysOnTop(true);
            agregarCliente.setVisible(true);
            return;
        }        
     
        //AGREGAR CLIENTE - BOTÓN AGREGAR        
        if (agregarCliente != null && ae.getSource() == agregarCliente.getjButtonAgregar()) {
            String nombre = agregarCliente.getjTextFieldNombre().getText();
            String rut = agregarCliente.getjTextFieldRut().getText();
            String telefono = agregarCliente.getjTextFieldTelefono().getText();
            String direccion = agregarCliente.getjTextFieldDireccion().getText();

            try {
                //Validar formatos antes de enviar al modelo
                validarFormatoRUT(rut);
                validarFormatoTelefono(telefono);

                if (veterinaria.agregarCliente(nombre, rut, telefono, direccion)) {
                    JOptionPane.showMessageDialog(agregarCliente, "Cliente agregado exitosamente.");
                }
                agregarCliente.dispose();

            } catch (FormatoRUTInvalidoException e) {
                JOptionPane.showMessageDialog(agregarCliente, e.getMessage(), "Error de formato de RUT", JOptionPane.ERROR_MESSAGE);
            } catch (FormatoTelefonoInvalidoException e) {
                JOptionPane.showMessageDialog(agregarCliente, e.getMessage(), "Error de formato de teléfono", JOptionPane.ERROR_MESSAGE);
            }
            return;
        }
        
        //AGREGAR CLIENTE - BOTÓN CANCELAR
        if (agregarCliente != null && ae.getSource() == agregarCliente.getjButtonCancelar()) { //Cancelar agregar cliente
            agregarCliente.dispose();
            return;
        }
        
        //==========================  BUSCAR CLIENTE  ==========================
        if (ae.getSource() == main.getjMenuItemBuscarCliente()) {
            buscarCliente = new VentanaBuscarCliente();
            buscarCliente.getjButtonBuscar().addActionListener(this);
            buscarCliente.getjButtonCancelar().addActionListener(this);
            buscarCliente.setAlwaysOnTop(true);
            buscarCliente.setVisible(true);
            return;
        }
        
        //BUSCAR CLIENTE - BOTÓN BUSCAR
        if (buscarCliente != null && ae.getSource() == buscarCliente.getjButtonBuscar()) {
            String rut = buscarCliente.getjTextFieldRut().getText();

            try { // Validar formato del RUT antes de realizar la búsqueda
                validarFormatoRUT(rut);
                Cliente cliente = veterinaria.buscarClientePorRut(rut); // Si el formato es válido, proceder con la búsqueda en la base de datos
                if (cliente != null) { //Si el cliente se encuentra se muestra la información
                    String info = "Cliente: " + cliente.getNombre() + "\n" +
                                 "RUT: " + cliente.getRut() + "\n" +
                                 "Teléfono: " + cliente.getTelefono() + "\n" +
                                 "Dirección: " + cliente.getDireccion() + "\n" +
                                 "Mascotas: " + cliente.getMascotas().size();
                    JOptionPane.showMessageDialog(buscarCliente, info);
                } else {
                    JOptionPane.showMessageDialog(buscarCliente, "Cliente no encontrado."); // RUT válido pero cliente no existe en el sistema
                }

            } catch (FormatoRUTInvalidoException e) { // Capturar y mostrar error de formato de RUT sin realizar búsqueda
                JOptionPane.showMessageDialog(buscarCliente, e.getMessage(), "Error de formato de RUT", JOptionPane.ERROR_MESSAGE);
            }
            return;
        }
            

        //BUSCAR CLIENTE - BOTÓN CANCELAR
        if (buscarCliente != null && ae.getSource() == buscarCliente.getjButtonCancelar()) { //Cancelar buscar
            buscarCliente.dispose();
            return;
        }
        
        //==========================  EDITAR CLIENTE  ==========================        
        if(ae.getSource() == main.getjMenuItemEditarCliente()) {
            editarCliente = new VentanaEditarCliente();
            editarCliente.getBtnBuscar().addActionListener(this);
            editarCliente.getBtnGuardar().addActionListener(this);
            editarCliente.getBtnCancelar().addActionListener(this);
            editarCliente.setVisible(true);
            return;
        }      
        
        //EDITAR CLIENTE - BOTÓN BUSCAR
        if(editarCliente != null && ae.getSource() == editarCliente.getBtnBuscar()){
            String rut = editarCliente.getTxtRut().getText();

            try { // Validar formato del RUT antes de proceder con la búsqueda
                validarFormatoRUT(rut);
                Cliente cliente = veterinaria.buscarClientePorRut(rut); // Buscar cliente con RUT válido en el sistema

                if(cliente != null){ // Cliente encontrado - cargar datos en los campos de edición
                    editarCliente.getTxtNombre().setText(cliente.getNombre());
                    editarCliente.getTxtTelefono().setText(cliente.getTelefono());
                    editarCliente.getTxtDireccion().setText(cliente.getDireccion());
                    editarCliente.habilitarEdicion(true); // Habilitar campos para permitir edición
                }
                else{
                    JOptionPane.showMessageDialog(editarCliente, "Cliente no encontrado"); // RUT válido pero cliente no existe
                    editarCliente.limpiarCampos(); // Limpiar campos para evitar confusión
                }

            } catch (FormatoRUTInvalidoException e) { // Error de formato
                JOptionPane.showMessageDialog(editarCliente, e.getMessage(), "Error de formato de RUT", JOptionPane.ERROR_MESSAGE);
                // Limpiar campos
                editarCliente.limpiarCampos();
            }
            return;
        }
        
        //EDITAR CLIENTE - BOTÓN GUARDAR
        if(editarCliente != null && ae.getSource() == editarCliente.getBtnGuardar()){
            String rut = editarCliente.getTxtRut().getText();
            String nombre = editarCliente.getTxtNombre().getText();
            String telefono = editarCliente.getTxtTelefono().getText();
            String direccion = editarCliente.getTxtDireccion().getText();

            try { // Validar formato del teléfono modificado (RUT ya fue validado en búsqueda)
                validarFormatoTelefono(telefono);

                if(veterinaria.editarCliente(rut, nombre, telefono, direccion)){  // Intentar guardar los cambios en el sistema
                    JOptionPane.showMessageDialog(editarCliente, "Cliente editado exitosamente"); // Edición exitosa
                    editarCliente.dispose();
                }
                else{
                    JOptionPane.showMessageDialog(editarCliente, "Error al editar cliente"); // Error en la edición
                }

            } catch (FormatoTelefonoInvalidoException e) { // Error de formato en teléfono
                JOptionPane.showMessageDialog(editarCliente, e.getMessage(), "Error de formato de teléfono", JOptionPane.ERROR_MESSAGE);
                editarCliente.getTxtTelefono().requestFocus(); // Enfocar el campo de teléfono para facilitar corrección
            }
            return;
        }
        
        //EDITAR CLIENTE - BOTÓN CANCELAR
        if(editarCliente != null && ae.getSource() == editarCliente.getBtnCancelar()){
            editarCliente.dispose();
            return;
        }
        
        //=========================  ELIMINAR CLIENTE  =========================        
        if(ae.getSource() == main.getjMenuItemEliminarCliente()) {
            eliminarCliente = new VentanaEliminarCliente();
            eliminarCliente.getBtnBuscar().addActionListener(this);
            eliminarCliente.getBtnEliminar().addActionListener(this);
            eliminarCliente.getBtnCancelar().addActionListener(this);
            eliminarCliente.setVisible(true);
            return;
        }     
        
        //ELIMINAR CLIENTE - BOTÓN BUSCAR
         if(eliminarCliente != null && ae.getSource() == eliminarCliente.getBtnBuscar()){
            String rut = eliminarCliente.getTxtRut().getText();

            try { // Validar formato del RUT antes de realizar búsqueda
                validarFormatoRUT(rut);
                Cliente cliente = veterinaria.buscarClientePorRut(rut); // Buscar cliente en el sistema con RUT válido

                if(cliente != null){ // Cliente encontrado, mostrar informacion
                    eliminarCliente.getTxtNombre().setText(cliente.getNombre());
                    eliminarCliente.getTxtTelefono().setText(cliente.getTelefono());
                    eliminarCliente.getTxtDireccion().setText(cliente.getDireccion());
                    eliminarCliente.getTxtNumMascotas().setText(String.valueOf(cliente.getMascotas().size())); // Mostrar número de mascotas asociadas

                    eliminarCliente.habilitarEliminacion(true); // Habilitar el botón eliminar después de cargar datos
                }
                else{ // RUT válido pero cliente no existe en el sistema
                    JOptionPane.showMessageDialog(eliminarCliente, "Cliente no encontrado");
                    eliminarCliente.limpiarCampos(); // Limpiar campos
                }

            } catch (FormatoRUTInvalidoException e) { // Error de formato
                JOptionPane.showMessageDialog(eliminarCliente, e.getMessage(), "Error de formato de RUT", JOptionPane.ERROR_MESSAGE);
                eliminarCliente.limpiarCampos(); // Limpiar campos
            }
            return;
        }
        
        //ELIMINAR CLIENTE - BOTÓN ELIMINAR
        if(eliminarCliente != null && ae.getSource() == eliminarCliente.getBtnEliminar()){
            String rut = eliminarCliente.getTxtRut().getText();
            int numMascotas = Integer.parseInt(eliminarCliente.getTxtNumMascotas().getText());
            if(numMascotas > 0){
                JOptionPane.showMessageDialog(eliminarCliente, "No se puede eliminar a un cliente con mascotas registradas");
                return;
            }
            int confirmacion = JOptionPane.showConfirmDialog(eliminarCliente, "Está seguro de querer elminar este cliente?", "Confirmar la eliminación", JOptionPane.YES_NO_OPTION);
            if(confirmacion == JOptionPane.YES_OPTION){
                if(veterinaria.eliminarCliente(rut)){
                    JOptionPane.showMessageDialog(eliminarCliente, "Cliente eliminado exitosamente");
                    eliminarCliente.dispose();
                }
                else{
                    JOptionPane.showConfirmDialog(eliminarCliente, "Error al eliminar el cliente");
                }
            }
            return;
        } 
        
        //ELIMINAR CLIENTE - BOTÓN CANCELAR
        if(eliminarCliente != null && ae.getSource() == eliminarCliente.getBtnCancelar()){
            eliminarCliente.dispose();
            return;
        }
        
//==============================  MENU MASCOTAS  ===============================   
        
        //=========================  AGREGAR MASCOTA  ==========================
        if (ae.getSource() == main.getjMenuItemAgregarMascota()) {
            agregarMascota = new VentanaAgregarMascota();
            agregarMascota.getjButtonAgregar().addActionListener(this);
            agregarMascota.getjButtonCancelar().addActionListener(this);
            agregarMascota.setAlwaysOnTop(true);
            agregarMascota.setVisible(true);
            return;
        }
        
        //AGREGAR MASCOTA - BOTÓN AGREGAR
        if (agregarMascota != null && ae.getSource() == agregarMascota.getjButtonAgregar()) {
            String rutDueno = agregarMascota.getjTextFieldRutDueno().getText();
            String nombre = agregarMascota.getjTextFieldNombre().getText();
            String tipo = agregarMascota.getjTextFieldTipo().getText();
            String raza = agregarMascota.getjTextFieldRaza().getText();
            String edadTexto = agregarMascota.getjTextFieldEdad().getText();

            try { // Validar formato del RUT
                validarFormatoRUT(rutDueno);
                if (edadTexto.isEmpty()) { // Validar que la edad no esté vacía
                    JOptionPane.showMessageDialog(agregarMascota, "La edad no puede estar vacía");
                    return;
                }

                int edad; // Convertir y validar rango de edad
                try {
                    edad = Integer.parseInt(edadTexto); // Aplicar validación de rango para edad de mascota
                    validarEdad(edad);

                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(agregarMascota, "La edad debe ser un número válido"); 
                    return;
                } catch (RangoInvalidoException e) { // Error de rango de edad
                    JOptionPane.showMessageDialog(agregarMascota, e.getMessage(), "Error de rango de edad", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                Cliente dueno = veterinaria.buscarClientePorRut(rutDueno); // Buscar dueño con RUT válido
                if (dueno != null) { // Determinar tipo de mascota (normal o geriátrica)
                    int opcion = JOptionPane.showConfirmDialog(agregarMascota, "¿Es una mascota geriátrica (mayor o con cuidados especiales)?", "Tipo de Mascota", JOptionPane.YES_NO_OPTION);

                    if (opcion == JOptionPane.YES_OPTION) { // Proceso para mascota geriátrica
                        String fechaInicio = JOptionPane.showInputDialog("Fecha inicio cuidados geriátricos (dd/mm/yyyy):");
                        if (fechaInicio != null && !fechaInicio.trim().isEmpty()) {
                            if (veterinaria.agregarMascotaGeriatrica(rutDueno, nombre, tipo, raza, edad, fechaInicio)) {
                                JOptionPane.showMessageDialog(agregarMascota,
                                    "Mascota geriátrica agregada exitosamente.\n" +
                                    "Cuidados especiales: " + new MascotaGeriatrica(nombre, tipo, raza, edad, fechaInicio).obtenerCuidadosEspeciales());
                            } else {
                                JOptionPane.showMessageDialog(agregarMascota, "Error al agregar mascota geriátrica");
                            }
                        } else {
                            JOptionPane.showMessageDialog(agregarMascota, "La fecha de inicio es requerida para mascotas geriátricas");
                            return;
                        }
                    } else { // Agregar como mascota normal
                        dueno.agregarMascota(nombre, tipo, raza, edad);
                        JOptionPane.showMessageDialog(agregarMascota, "Mascota normal agregada exitosamente.");
                    }
                } else { // RUT válido pero dueño no existe
                    JOptionPane.showMessageDialog(agregarMascota, "Cliente no encontrado.");
                }
                agregarMascota.dispose();

            } catch (FormatoRUTInvalidoException e) { // Error de formato de RUT
                JOptionPane.showMessageDialog(agregarMascota, e.getMessage(), "Error de formato de RUT", JOptionPane.ERROR_MESSAGE);
            }
            return;
        }            
        
        //AGREGAR MASCOTA - BOTÓN CANCELAR       
        if (agregarMascota != null && ae.getSource() == agregarMascota.getjButtonCancelar()) {
            agregarMascota.dispose();
            return;
        }
        
        //==========================  BUSCAR MASCOTA  ==========================      
        if (ae.getSource() == main.getjMenuItemBuscarMascota()) {
            buscarMascota = new VentanaBuscarMascota();
            buscarMascota.getBtnBuscar().addActionListener(this);
            buscarMascota.getBtnCancelar().addActionListener(this);
            buscarMascota.setAlwaysOnTop(true);
            buscarMascota.setVisible(true);
            return;
        }
        
        //BUSCAR MASCOTA - BOTON BUSCAR      
        if (buscarMascota != null && ae.getSource() == buscarMascota.getBtnBuscar()) {
            String rutDueno = buscarMascota.getTxtRutDueno().getText();
            String nombreMascota = buscarMascota.getTxtNombreMascota().getText();

            try { // Validar formato del RUT
                validarFormatoRUT(rutDueno); // Buscar cliente con RUT válido en el sistema

                Cliente cliente = veterinaria.buscarClientePorRut(rutDueno);

                if(cliente != null){ // Cliente encontrado
                    Mascota mascota = cliente.buscarMascotaPorNombre(nombreMascota);

                    if (mascota != null) { //Mostrar información
                        String info = "Nombre: " + mascota.getNombre() + "\n" +
                                     "Dueño:" + cliente.getNombre () + " (RUT:" + cliente.getRut() + ")" + "\n" +
                                     "Tipo: " + mascota.getTipo() + "\n" +
                                     "Raza: " + mascota.getRaza() + "\n" +
                                     "Edad: " + mascota.getEdad() + " años\n" +
                                     "Categoría: ";
                        if(mascota instanceof MascotaGeriatrica){ // Verificar si es mascota geriátrica para mostrar información adicional
                            MascotaGeriatrica geriatrica = (MascotaGeriatrica) mascota;
                            info += "GERIÁTRICA\n";
                            info += "Fecha inicio cuidados: " + geriatrica.getFechaInicioGeriatria() + "\n";
                            info += "Cuidados especiales: " + geriatrica.obtenerCuidadosEspeciales() + "\n";
                            info += "Frecuencia visitas: " + geriatrica.obtenerFrecuenciaVisitas() + " meses\n";
                            info += "Medicamentos: " + geriatrica.getMedicamentosHabituales();
                        }
                        else{ // Información para mascota normal
                            info += "Normal\n";
                            info += "Cuidados: " + mascota.obtenerCuidadosEspeciales() + "\n";
                            info += "Frecuencia visitas: " + mascota.obtenerFrecuenciaVisitas() + " meses";
                        }

                        JOptionPane.showMessageDialog(buscarMascota, info); // Mostrar información completa
                    } else { // Cliente existe pero mascota con ese nombre no encontrada
                        JOptionPane.showMessageDialog(buscarMascota, "Mascota no encontrada.");
                    }
                } else { // RUT válido pero cliente no existe en el sistema
                    JOptionPane.showMessageDialog(buscarMascota, "Cliente no encontrado.");
                }

            } catch (FormatoRUTInvalidoException e) { // Error de formato de RUT
                JOptionPane.showMessageDialog(buscarMascota, e.getMessage(), "Error de formato de RUT", JOptionPane.ERROR_MESSAGE);
            }
            return;
        }
        
        //BUSCAR MASCOTA - BOTÓN CANCELAR

        if (buscarMascota != null && ae.getSource() == buscarMascota.getBtnCancelar()) {
            buscarMascota.dispose();
            return;
        }       
        
        //==========================  EDITAR MASCOTA  ==========================   
        if(ae.getSource() == main.getjMenuItemEditarMascota()) {
            editarMascota = new VentanaEditarMascota();
            editarMascota.getBtnBuscar().addActionListener(this);
            editarMascota.getBtnGuardar().addActionListener(this);
            editarMascota.getBtnCancelar().addActionListener(this);
            editarMascota.setVisible(true);
            return;
        } 
        
        //EDITAR MASCOTA - BOTÓN BUSCAR
        if(editarMascota != null && ae.getSource() == editarMascota.getBtnBuscar()){
            String rutDueno = editarMascota.getTxtRutDueno().getText();
            String nombreMascota = editarMascota.getTxtNombreMascota().getText();

            try { // Validar formato del RUT
                validarFormatoRUT(rutDueno); // Buscar cliente con RUT
                Cliente cliente = veterinaria.buscarClientePorRut(rutDueno);

                if(cliente != null){ // Cliente encontrado
                    Mascota mascota = cliente.buscarMascotaPorNombre(nombreMascota);

                    if(mascota != null){ // Mascota encontrada
                        editarMascota.getTxtTipo().setText(mascota.getTipo());
                        editarMascota.getTxtRaza().setText(mascota.getRaza());
                        editarMascota.getTxtEdad().setText(String.valueOf(mascota.getEdad()));
                        editarMascota.habilitarEdicion(true); // Habilitar campos de edición
                    }
                    else{ // Cliente existe pero no tiene mascota con ese nombre
                        JOptionPane.showMessageDialog(editarMascota, "Mascota no encontrada");
                        editarMascota.limpiarCampos(); // Limpiar campos
                    }
                }
                else{
                    JOptionPane.showMessageDialog(editarMascota, "Cliente no encontrado"); // RUT válido pero cliente no existe en el sistema
                    editarMascota.limpiarCampos(); // Limpiar campos
                }

            } catch (FormatoRUTInvalidoException e) { // Error de formato de RUT
                JOptionPane.showMessageDialog(editarMascota, e.getMessage(), "Error de formato de RUT", JOptionPane.ERROR_MESSAGE);
                editarMascota.limpiarCampos(); // Limpiar campos
            }
            return;
        }
        
        //EDITAR MASCOTA - BOTÓN GUARDAR        
        if(editarMascota != null && ae.getSource() == editarMascota.getBtnGuardar()){
            String rutDueno = editarMascota.getTxtRutDueno().getText();
            String nombreMascota = editarMascota.getTxtNombreMascota().getText();
            String tipo = editarMascota.getTxtTipo().getText();
            String raza = editarMascota.getTxtRaza().getText();
            String edadTexto = editarMascota.getTxtEdad().getText();

            try { // Convertir y validar la nueva edad ingresada
                int edad = Integer.parseInt(edadTexto);
                validarEdad(edad); // Aplicar validación de rango para la edad modificada
                if(veterinaria.editarMascota(rutDueno, nombreMascota, tipo, raza, edad)){ // Intentar guardar los cambios en el sistema
                    JOptionPane.showMessageDialog(editarMascota, "Mascota editada exitosamente"); // Edición exitosa
                    editarMascota.dispose();
                }
                else{
                    JOptionPane.showMessageDialog(editarMascota, "Error al editar mascota"); // Error en la edición
                }

            } catch (NumberFormatException e) { // Error de formato numérico en edad
                JOptionPane.showMessageDialog(editarMascota, "La edad debe ser un número válido", "Error de formato", JOptionPane.ERROR_MESSAGE);
                editarMascota.getTxtEdad().requestFocus();

            } catch (RangoInvalidoException e) { // Error de rango en edad
                JOptionPane.showMessageDialog(editarMascota, e.getMessage(), "Error de rango de edad", JOptionPane.ERROR_MESSAGE);
                editarMascota.getTxtEdad().setText("");
                editarMascota.getTxtEdad().requestFocus();
            }
            return;
        }
        
        //EDITAR MASCOTA - BOTON CANCELAR       
        if(editarMascota != null && ae.getSource() == editarMascota.getBtnCancelar()){
            editarMascota.dispose();
            return;
        }
        
        //=========================  ELIMINAR MASCOTA  =========================          
        if(ae.getSource() == main.getjMenuItemEliminarMascota()) {
            eliminarMascota = new VentanaEliminarMascota();
            eliminarMascota.getBtnBuscar().addActionListener(this);
            eliminarMascota.getBtnEliminar().addActionListener(this);
            eliminarMascota.getBtnCancelar().addActionListener(this);
            eliminarMascota.setVisible(true);
            return;
        }
        
        //ELIMINAR MASCOTA - BOTÓN BUSCAR
        if(eliminarMascota != null && ae.getSource() == eliminarMascota.getBtnBuscar()){
            String rutDueno = eliminarMascota.getTxtRutDueno().getText();
            String nombreMascota = eliminarMascota.getTxtNombre().getText();

            try { // Validar formato del RUT 
                validarFormatoRUT(rutDueno);
                Cliente dueno = veterinaria.buscarClientePorRut(rutDueno); // Buscar cliente con RUT

                if(dueno != null){ // Cliente encontrado
                    Mascota mascota = dueno.buscarMascotaPorNombre(nombreMascota);

                    if(mascota != null){ // Mascota encontrada
                        eliminarMascota.getTxtTipo().setText(mascota.getTipo());
                        eliminarMascota.getTxtRaza().setText(mascota.getRaza());
                        eliminarMascota.getTxtEdad().setText(String.valueOf(mascota.getEdad()));
                        eliminarMascota.habilitarEliminacion(true); // Habilitar botón eliminar
                    }
                    else{
                        JOptionPane.showMessageDialog(eliminarMascota, "Mascota no encontrada"); // Cliente existe pero no tiene mascota con ese nombre
                        eliminarMascota.limpiarCampos(); // Limpiar campos
                    }
                }
                else{
                    JOptionPane.showMessageDialog(eliminarMascota, "Cliente no encontrado"); // RUT válido pero cliente no existe en el sistema
                    eliminarMascota.limpiarCampos(); // Limpiar campos
                }

            } catch (FormatoRUTInvalidoException e) { // Error de formato de RUT 
                JOptionPane.showMessageDialog(eliminarMascota, e.getMessage(), "Error de formato de RUT", JOptionPane.ERROR_MESSAGE);
                eliminarMascota.limpiarCampos(); // Limpiar campos
            }
            return;
        }
        
        //ELIMINAR MASCOTA - BOTÓN ELIMINAR
        if(eliminarMascota != null && ae.getSource() == eliminarMascota.getBtnEliminar()){
            String rutDueno = eliminarMascota.getTxtRutDueno().getText();
            String nombreMascota = eliminarMascota.getTxtNombre().getText();

            try { // Validar formato del RUT 
                validarFormatoRUT(rutDueno); // Solicitar confirmación
                int confirmacion = JOptionPane.showConfirmDialog(eliminarMascota,
                        "Estas seguro de querer eliminar esta mascota?",
                        "Confirmar eliminación", JOptionPane.YES_NO_OPTION);

                if(confirmacion == JOptionPane.YES_OPTION){ // Usuario confirmó
                    if(veterinaria.eliminarMascota(rutDueno, nombreMascota)){ // Eliminación exitosa
                        JOptionPane.showMessageDialog(eliminarMascota, "Mascota eliminada exitosamente");
                        eliminarMascota.dispose();
                    }
                    else{ // Error en eliminación
                        JOptionPane.showMessageDialog(eliminarMascota, "Error al eliminar mascota");
                    }
                }
            } catch (FormatoRUTInvalidoException e) { // Si el usuario seleccionó "NO"
                JOptionPane.showMessageDialog(eliminarMascota, e.getMessage(), "Error de formato de RUT", JOptionPane.ERROR_MESSAGE); // Error de formato de RUT 
            }
            return;
        }
        
        //ELIMINAR MASCOTA - BOTÓN CANCELAR
        if(eliminarMascota != null && ae.getSource() == eliminarMascota.getBtnCancelar()){
            eliminarMascota.dispose();
            return;
        }
           
//=============================  MENU SERVICIOS  ===============================
        
        //=========================  AGREGAR SERVICIO  =========================     
        if (ae.getSource() == main.getjMenuItemAgregarServicio()) {
            agregarServicio = new VentanaAgregarServicio();
            agregarServicio.getBtnBuscar().addActionListener(this);
            agregarServicio.getBtnAgregar().addActionListener(this);
            agregarServicio.getBtnCancelar().addActionListener(this);
            agregarServicio.setAlwaysOnTop(true);
            agregarServicio.setVisible(true);
            return;
        }
        
        //AGREGAR SERVICIO - BOTÓN BUSCAR      
        if (agregarServicio != null && ae.getSource() == agregarServicio.getBtnBuscar()) {
            String rutDueno = agregarServicio.getTxtRutDueno().getText();

            try { // Validar formato del RUT
                validarFormatoRUT(rutDueno);
                Cliente dueno = veterinaria.buscarClientePorRut(rutDueno); // Buscar cliente con RUT

                if(dueno != null){ // Cliente encontrado
                    agregarServicio.cargarMascotasDelCliente(dueno);
                    JOptionPane.showMessageDialog(agregarServicio, "Cliente encontrado, seleccione una o más mascotas");
                }
                else{
                    JOptionPane.showMessageDialog(agregarServicio, "Cliente no encontrado"); // RUT válido pero cliente no existe en el sistema
                    agregarServicio.limpiarMascotas(); // Limpiar lista de mascotas
                }

            } catch (FormatoRUTInvalidoException e) { // Error de formato de RUT
                JOptionPane.showMessageDialog(agregarServicio, e.getMessage(), "Error de formato de RUT", JOptionPane.ERROR_MESSAGE);

                agregarServicio.limpiarMascotas(); // Limpiar mascotas
            }
            return;
        }
        
        //AGREGAR SERVICIO - BOTÓN AGREGAR
        if(agregarServicio != null && ae.getSource() == agregarServicio.getBtnAgregar()){
            String rutDueno = agregarServicio.getTxtRutDueno().getText();
            String nombreMascota = (String)agregarServicio.getComboMascotas().getSelectedItem();
            String tipoServicio = agregarServicio.getTxtTipoServicio().getText();
            String fecha = agregarServicio.getTxtFecha().getText();
            String hora = agregarServicio.getTxtHora().getText();
            String descripcion = agregarServicio.getTxtDescripcion().getText();
            String precioTexto = agregarServicio.getTxtPrecio().getText().trim();
            String estado = (String)agregarServicio.getComboEstado().getSelectedItem();
            boolean esUrgencia = agregarServicio.getChkEsUrgencia().isSelected();

            try { // Validar formato del RUT
                validarFormatoRUT(rutDueno);
                if(rutDueno.isEmpty() || nombreMascota == null || tipoServicio.isEmpty() ||
                    fecha.isEmpty() || hora.isEmpty() || descripcion.isEmpty() || precioTexto.isEmpty()){
                    JOptionPane.showMessageDialog(agregarServicio, "Por favor, completa todos los campos"); // Validar que todos los campos obligatorios estén completos
                    return;
                }

                if(esUrgencia){ // Validar información adicional si es urgencia
                    String motivoUrgencia = agregarServicio.getTxtMotivoUrgencia().getText().trim();
                    if(motivoUrgencia.isEmpty()){
                        JOptionPane.showMessageDialog(agregarServicio, "El motivo de urgencia es requerido para servicios de urgencia");
                        return;
                   } 
                }
                int precio = 0; //Convertir y validar precio
                try{
                    precio = Integer.parseInt(precioTexto); // Aplicar validación de rango para precio del servicio
                    validarPrecio(precio);

                } catch(NumberFormatException e){
                    JOptionPane.showMessageDialog(agregarServicio, "El precio debe ser un número válido", "Error de formato", JOptionPane.ERROR_MESSAGE);
                    agregarServicio.getTxtPrecio().setText("");
                    agregarServicio.getTxtPrecio().requestFocus();
                    return;
                } catch(RangoInvalidoException e){
                    JOptionPane.showMessageDialog(agregarServicio, e.getMessage(), "Error de rango de precio", JOptionPane.ERROR_MESSAGE);
                    agregarServicio.getTxtPrecio().setText("");
                    agregarServicio.getTxtPrecio().requestFocus();
                    return;
                }
                Cliente dueno = veterinaria.buscarClientePorRut(rutDueno); // Buscar cliente y mascota para procesar servicio
                if(dueno != null){
                    Mascota mascota = dueno.buscarMascotaPorNombre(nombreMascota);
                    if(mascota != null){
                        int precioConDescuento = veterinaria.calcularPrecioConDescuento(rutDueno, precio); // Calcular precios con descuentos aplicables
                        double factorMascota = mascota.calcularFactorPrecio();
                        int precioFinal = (int)(precioConDescuento * factorMascota);

                        if(esUrgencia){ // Procesar servicio de urgencia con información adicional
                            int nivelUrgencia = Integer.parseInt((String)agregarServicio.getComboNivelUrgencia().getSelectedItem());
                            String motivoUrgencia = agregarServicio.getTxtMotivoUrgencia().getText();
                            boolean requiereAtencionInmediata = agregarServicio.getChkAtencionInmediata().isSelected();

                            ServicioUrgencia servicioUrgencia = new ServicioUrgencia(
                                    tipoServicio, fecha, hora, descripcion, precioFinal, estado,
                                    nivelUrgencia, motivoUrgencia, requiereAtencionInmediata);
                            mascota.agregarServicio(servicioUrgencia); //Se crea el servicio de urgencia
       
                            String mensaje = "Servicio de urgencia agregado correctamente\n\n"; //Se prepara el mensaje con detalles de urgencia
                            mensaje += "=== DETALLES DE URGENCIA ===\n";
                            mensaje += "Nivel: " + nivelUrgencia + "/5\n";
                            mensaje += "Motivo: " + motivoUrgencia + "\n";
                            mensaje += "Atención inmediata: " + (requiereAtencionInmediata ? "SÍ" : "NO") + "\n";

                            int precioConRecargo = servicioUrgencia.calcularPrecioFinal(); //Se calcula el recargo por urgencia
                            double porcentajeRecargo = ((double)precioConRecargo / precioFinal - 1) * 100;
                            mensaje += "Recargo aplicado: " + (int)porcentajeRecargo + "%\n";

                            if(precioFinal < precio){ //Mostrar información de descuento si es que aplica
                                mensaje += "\nPrecio original: $" + precio;
                                mensaje += "\nPrecio con descuentos: $" + precioFinal; 
                            }
                            mensaje += "\nPrecio final con urgencia: $" + precioConRecargo;
                            mensaje += "\n\n=== INSTRUCCIONES ===\n";
                            mensaje += servicioUrgencia.obtenerInstruccionesEspeciales();

                            if (servicioUrgencia.esEmergenciaCritica()) { //Se muestra un mensaje segun el nivel de lo critico
                                mensaje += "\n\nEMERGENCIA CRÍTICA - PRIORIDAD MÁXIMA️";
                                JOptionPane.showMessageDialog(agregarServicio, mensaje, "Servicio de Urgencia Crítica", JOptionPane.ERROR_MESSAGE);
                            } else{
                                JOptionPane.showMessageDialog(agregarServicio, mensaje, "Servicio de Urgencia", JOptionPane.WARNING_MESSAGE);
                            }
                        } else{ // Procesar servicio normal
                            mascota.agregarServicio(tipoServicio, fecha, hora, descripcion, precioFinal, estado);
                            String mensaje = "Servicio agregado correctamente";

                            if(precioFinal < precio){
                                mensaje += "\n\n=== DESCUENTOS APLICADOS ===";
                                mensaje += "\nTipo Cliente: " + dueno.obtenerTipoCliente();
                                mensaje += "\nPrecio Original: $" + precio;
                                if(precioConDescuento < precio){
                                    mensaje += "\nDescuento cliente: $" + precioConDescuento + " (" + (int)(dueno.calcularDescuento() * 100) + "%)";                             
                                }
                                if (factorMascota < 1.0) {
                                    mensaje += "\nDescuento mascota: " + (int)((1 - factorMascota) * 100) + "%"; 
                                }
                            }
                            JOptionPane.showMessageDialog(agregarServicio, mensaje);
                        }
                        boolean promocionado = veterinaria.verificarPromocionAutomatica(rutDueno); // Verificar promoción automática a cliente frecuente
                        if (promocionado) {
                            Cliente clientePromovido = veterinaria.buscarClientePorRut(rutDueno);
                            JOptionPane.showMessageDialog(agregarServicio, 
                            "¡PROMOCION AUTOMATICA!\n\n" +
                            clientePromovido.getNombre() + "ahora es CLIENTE FRECUENTE\n\n" +
                            "Beneficios obtenidos:\n" +
                            "Descuento automatco:" + (int)(clientePromovido.calcularDescuento() * 100) + "%\n" +
                            clientePromovido.obtenerBeneficios() + "\n\n" +
                            "¡Los próximos servicios tendrán descuento automático!",
                            "Cliente Frecuente", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } else{
                        JOptionPane.showMessageDialog(agregarServicio, "Error: La mascota seleccionada no existe"); //Error, mascota no existe          
                    }
                } else{
                    JOptionPane.showMessageDialog(agregarServicio, "Error: El cliente no existe"); //Error, cliente no existe
                }
                agregarServicio.dispose(); //Se cierra la ventana

            } catch (FormatoRUTInvalidoException e) { // Error de formato de RUT
                JOptionPane.showMessageDialog(agregarServicio, e.getMessage(), "Error de formato de RUT", JOptionPane.ERROR_MESSAGE);
            }
            return;
        }
        
        //AGREGAR SERVICIO - BOTÓN CANCELAR       
        if (agregarServicio != null && ae.getSource() == agregarServicio.getBtnCancelar()) { //Cancelar agregar servicio
            agregarServicio.dispose();
            return;
        }
        
        //========================== BUSCAR SERVICIO ===========================
        if(ae.getSource() == main.getjMenuItemBuscarServicio()){
            buscarServicio = new VentanaBuscarServicio();
            buscarServicio.getBtnBuscarCliente().addActionListener(this);
            buscarServicio.getBtnBuscarMascota().addActionListener(this);
            buscarServicio.getBtnCancelar().addActionListener(this);
            buscarServicio.setVisible(true);
            return;
        }
        
        //BUSCAR SERVICIO - BOTÓN BUSCAR CLIENTE
        if(buscarServicio != null && ae.getSource() == buscarServicio.getBtnBuscarCliente()){
            String rutCliente = buscarServicio.getTxtRutCliente().getText().trim();

            try {
                if(rutCliente.isEmpty()){ // Validar que el campo RUT no esté vacío
                    JOptionPane.showMessageDialog(buscarServicio, "Por favor ingresa un RUT");
                    return;
                }
                validarFormatoRUT(rutCliente); //Validar formato del RUT
                Cliente cliente = veterinaria.buscarClientePorRut(rutCliente); // Buscar cliente con RUT 

                if(cliente != null){ // Cliente encontrado
                    List<String> nombreMascotas = new ArrayList<>();
                    for(Mascota mascota : cliente.getMascotas()){
                        nombreMascotas.add(mascota.getNombre());
                    }
                    buscarServicio.cargarMascotasDelCliente(nombreMascotas); // Cargar mascotas en el combo

                } else{ // RUT válido
                    JOptionPane.showMessageDialog(buscarServicio, "Cliente no encontrado");
                    buscarServicio.limpiarResultados(); // Limpiar resultados
                }

            } catch (FormatoRUTInvalidoException e) { // Error de formato de RUT
                JOptionPane.showMessageDialog(buscarServicio, e.getMessage(), "Error de formato de RUT", JOptionPane.ERROR_MESSAGE);
                buscarServicio.limpiarResultados(); // Limpiar resultados
            }
            return;
        }
        
        //BUSCAR SERVICIO - BOTÓN BUSCAR MASCOTA
        if (buscarServicio != null && ae.getSource() == buscarServicio.getBtnBuscarMascota()) {
            String rutCliente = buscarServicio.getTxtRutCliente().getText().trim();
            String nombreMascota = "";

            try { // Validar formato del RUT
                validarFormatoRUT(rutCliente);
                if (buscarServicio.getComboMascotas().getSelectedItem() != null) { // Verificar que se haya seleccionado una mascota del combo
                    nombreMascota = buscarServicio.getComboMascotas().getSelectedItem().toString();
                }
                else{
                    JOptionPane.showMessageDialog(buscarServicio, "Por favor, seleccione una mascota de la lista.");
                    return;
                }
                if (rutCliente.isEmpty() || nombreMascota.isEmpty()) { // Validar que ambos campos estén completos
                    JOptionPane.showMessageDialog(buscarServicio, "Por favor, complete RUT y seleccione una mascota.");
                    return;
                }
                List<Servicio> servicios = veterinaria.buscarServiciosPorMascota(rutCliente, nombreMascota); // Buscar servicios asociados a la mascota específica

                if(!servicios.isEmpty()){ // Servicios encontrados
                    buscarServicio.mostrarServicios(servicios);
                    JOptionPane.showMessageDialog(buscarServicio, "Se encontraron " + servicios.size() + " servicio(s) para la mascota");
                }
                else{
                    JOptionPane.showMessageDialog(buscarServicio, "No se encontraron servicios para la mascota seleccionada"); // No hay servicios registrados para esta mascota
                    buscarServicio.limpiarResultados(); // Limpiar tabla
                }

            } catch (FormatoRUTInvalidoException e) { // Error de formato de RUT
                JOptionPane.showMessageDialog(buscarServicio, e.getMessage(), "Error de formato de RUT", JOptionPane.ERROR_MESSAGE);
                buscarServicio.limpiarResultados(); // Limpiar resultados
            }
            return;
        }
        
        //BUSCAR SERVICIO - BOTÓN CANCELAR
        if(buscarServicio != null && ae.getSource() == buscarServicio.getBtnCancelar()){
            buscarServicio.dispose();
            return;
        }
        
        //=====================  EDITAR SERVICIO  ==============================
        if(ae.getSource() == main.getjMenuItemEditarServicio()){
            editarServicio = new VentanaEditarServicio();
            editarServicio.getBtnBuscarCliente().addActionListener(this);
            editarServicio.getBtnBuscarMascota().addActionListener(this);
            editarServicio.getBtnGuardar().addActionListener(this);
            editarServicio.getBtnCancelar().addActionListener(this);
            //Listener para tabla
            editarServicio.getTblServicios().getSelectionModel().addListSelectionListener(e->{
                if(!e.getValueIsAdjusting()){
                    manejarSeleccionServicio();
                }
            });
            editarServicio.setVisible(true);
            return;
        }
        
        //EDITAR SERVICIO - BOTÓN BUSCAR CLIENTE
        if(editarServicio != null && ae.getSource() == editarServicio.getBtnBuscarCliente()){
            String rutCliente = editarServicio.getTxtRutCliente().getText().trim();

            try {
                if(rutCliente.isEmpty()){ // Validar que el campo RUT no esté vacío
                    JOptionPane.showMessageDialog(editarServicio, "Por favor ingresa un RUT");
                    return;
                }
                validarFormatoRUT(rutCliente); // Validar formato del RUT 

                Cliente cliente = veterinaria.buscarClientePorRut(rutCliente); //Buscar cliente con RUT

                if(cliente != null){ // Cliente encontrado
                    editarServicio.cargarMascotasDelCliente(cliente);
                    JOptionPane.showMessageDialog(editarServicio, "Cliente encontrado, selecciona una mascota");
                }
                else{
                    JOptionPane.showMessageDialog(editarServicio, "Cliente no encontrado"); // RUT válido pero cliente no existe en el sistema
                    editarServicio.limpiarCampos(); // Limpiar campos
                }

            } catch (FormatoRUTInvalidoException e) { // Error de formato de RUT
                JOptionPane.showMessageDialog(editarServicio, e.getMessage(), "Error de formato de RUT", JOptionPane.ERROR_MESSAGE);
                editarServicio.limpiarCampos(); // Limpiar campos
            }
            return;
        }
        
        //EDITAR SERVICIO - BOTÓN BUSCAR MASCOTA
        if(editarServicio != null && ae.getSource() == editarServicio.getBtnBuscarMascota()){
            String rutCliente = editarServicio.getTxtRutCliente().getText().trim();
            String nombreMascota = "";

            try {
                validarFormatoRUT(rutCliente); // Validar formato del RUT
                if(editarServicio.getComboMascotas().getSelectedItem() != null){ // Verificar que se haya seleccionado una mascota del combo
                    nombreMascota = editarServicio.getComboMascotas().getSelectedItem().toString();
                }
                else{
                    JOptionPane.showMessageDialog(editarServicio, "Selecciona una mascota");
                    return;
                }
                if(rutCliente.isEmpty() || nombreMascota.isEmpty()){ //Validar que ambos campos estén completos
                    JOptionPane.showMessageDialog(editarServicio, "Completar RUT y seleccionar mascota");
                    return;
                }  
                List<Servicio> servicios = veterinaria.buscarServiciosPorMascota(rutCliente, nombreMascota); // Buscar servicios de la mascota para cargar en tabla

                if(!servicios.isEmpty()){
                    editarServicio.cargarServiciosDeMascota(servicios); // Servicios encontrados
                    JOptionPane.showMessageDialog(editarServicio, "Se encontraron " + servicios.size() + " servicios");
                }
                else{
                    JOptionPane.showMessageDialog(editarServicio, "No se encontraron servicios");
                    editarServicio.limpiarCampos(); // Limpiar campos
                }

            } catch (FormatoRUTInvalidoException e) { // Error de formato de RUT
                JOptionPane.showMessageDialog(editarServicio, e.getMessage(), "Error de formato de RUT", JOptionPane.ERROR_MESSAGE);
                editarServicio.limpiarCampos(); // Limpiar campos
            }
            return;
        }
        
        //EDITAR SERVICIO - BOTÓN GUARDAR
        if(editarServicio != null && ae.getSource() == editarServicio.getBtnGuardar()){
            int indiceSeleccionado = editarServicio.getIndiceServicioSeleccionado();
            if(indiceSeleccionado < 0){
                JOptionPane.showMessageDialog(editarServicio, "Selecciona un servicio de la tabla");
                return;
            }

            String rutCliente = editarServicio.getTxtRutCliente().getText().trim();
            String nombreMascota = editarServicio.getComboMascotas().getSelectedItem().toString();
            String tipoServicio = editarServicio.getTxtTipoServicio().getText();
            String fecha = editarServicio.getTxtFecha().getText();
            String hora = editarServicio.getTxtHora().getText();
            String descripcion = editarServicio.getTxtDescripcion().getText();
            String precioTexto = editarServicio.getTxtPrecio().getText().trim();
            String estado = (String)editarServicio.getComboEstado().getSelectedItem();

            try {
                if(tipoServicio.isEmpty() || fecha.isEmpty() || hora.isEmpty() || descripcion.isEmpty() || precioTexto.isEmpty()){ // Validar que todos los campos estén completos
                    JOptionPane.showMessageDialog(editarServicio, "Completa todos los campos");
                    return;
                }

                int precio = 0; // Convertir y validar precio modificado
                try{
                    precio = Integer.parseInt(precioTexto);
                    validarPrecio(precio); // Aplicar validación de rango para el nuevo precio

                } catch(NumberFormatException e){
                    JOptionPane.showMessageDialog(editarServicio, "El precio debe ser un número válido", "Error de formato", JOptionPane.ERROR_MESSAGE);
                    editarServicio.getTxtPrecio().requestFocus();
                    return;
                } catch(RangoInvalidoException e){
                    JOptionPane.showMessageDialog(editarServicio, e.getMessage(), "Error de rango de precio", JOptionPane.ERROR_MESSAGE);
                    editarServicio.getTxtPrecio().setText("");
                    editarServicio.getTxtPrecio().requestFocus();
                    return;
                }
                Servicio servicioActualizado = new Servicio(tipoServicio, fecha, hora, descripcion, precio, estado); // Crear servicio actualizado con datos validados

                if(veterinaria.editarServicio(rutCliente, nombreMascota, indiceSeleccionado, servicioActualizado)){ // Intentar guardar cambios en el sistema
                    JOptionPane.showMessageDialog(editarServicio, "Servicio actualizado correctamente"); // Edición exitosa
                    List<Servicio> servicios = veterinaria.buscarServiciosPorMascota(rutCliente, nombreMascota); 
                    editarServicio.cargarServiciosDeMascota(servicios); // Recargar servicios actualizados en la tabla
                    editarServicio.limpiarCampos(); // Limpiar campos
                }
                else{
                    JOptionPane.showMessageDialog(editarServicio, "Error al editar el servicio");
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(editarServicio, "Error inesperado: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
            return;
        }
        
        //EDTIAR SERVICIO - BOTÓN CANCELAR
        
        if(editarServicio != null && ae.getSource() == editarServicio.getBtnCancelar()){
            editarServicio.dispose();
            return;
        }   
        
        //====================  ELIMINAR SERVICIO  =============================    
        if(ae.getSource() == main.getjMenuItemEliminarServicio()){
            eliminarServicio = new VentanaEliminarServicio();
            eliminarServicio.getBtnBuscarCliente().addActionListener(this);
            eliminarServicio.getBtnBuscarMascota().addActionListener(this);
            eliminarServicio.getBtnEliminar().addActionListener(this);
            eliminarServicio.getBtnCancelar().addActionListener(this);
            
            eliminarServicio.getTblServicios().getSelectionModel().addListSelectionListener(e->{
                if(!e.getValueIsAdjusting()){
                    manejarSeleccionServicioEliminar();
                }
            });
            eliminarServicio.setVisible(true);
            return;
        }
        
        //ELIMINAR SERVICIO - BOTÓN BUSCAR CLIENTE
        if(eliminarServicio != null && ae.getSource() == eliminarServicio.getBtnBuscarCliente()){
            String rutCliente = eliminarServicio.getTxtRutCliente().getText().trim();

            try { 
                if(rutCliente.isEmpty()){ // Validar que el campo RUT no esté vacío
                    JOptionPane.showMessageDialog(eliminarServicio, "Por favor ingresa RUT");
                    return;
                }
                validarFormatoRUT(rutCliente); // Validar formato del RUT
                Cliente cliente = veterinaria.buscarClientePorRut(rutCliente); // Buscar cliente con RUT válido en el sistema

                if(cliente != null){ // Cliente encontrado
                    eliminarServicio.cargarMascotasDelCliente(cliente);
                    JOptionPane.showMessageDialog(eliminarServicio, "Cliente encontrado");
                }
                else{
                    JOptionPane.showMessageDialog(eliminarServicio, "Cliente no encontrado"); // RUT válido pero cliente no existe en el sistema
                    eliminarServicio.limpiarCampos(); // Limpiar campos
                }

            } catch (FormatoRUTInvalidoException e) {
                JOptionPane.showMessageDialog(eliminarServicio, e.getMessage(), "Error de formato de RUT", JOptionPane.ERROR_MESSAGE); // Error de formato de RUT
                eliminarServicio.limpiarCampos(); // Limpiar campos
            }
            return;
        }
        
        //ELIMINAR SERVICIO - BOTÓN BUSCAR MASCOTA
        if(eliminarServicio != null && ae.getSource() == eliminarServicio.getBtnBuscarMascota()){
            String rutCliente = eliminarServicio.getTxtRutCliente().getText().trim();
            String nombreMascota = "";

            try {
                validarFormatoRUT(rutCliente); // Validar formato del RUT
                if(eliminarServicio.getComboMascotas().getSelectedItem() != null){ // Verificar que se haya seleccionado una mascota del combo
                    nombreMascota = eliminarServicio.getComboMascotas().getSelectedItem().toString();
                }
                else{
                    JOptionPane.showMessageDialog(eliminarServicio, "Seleccione una mascota");
                    return;
                }
                List<Servicio> servicios = veterinaria.buscarServiciosPorMascota(rutCliente, nombreMascota); // Buscar servicios de la mascota

                if(!servicios.isEmpty()){ // Servicios encontrados 
                    eliminarServicio.cargarServiciosDeMascota(servicios);
                    JOptionPane.showMessageDialog(eliminarServicio, "Se encontraron " + servicios.size() + " servicios");
                }
                else{
                    JOptionPane.showMessageDialog(eliminarServicio, "No se encontraron servicios"); // No hay servicios para eliminar en esta mascota
                    eliminarServicio.limpiarCampos(); // Limpiar campos
                }

            } catch (FormatoRUTInvalidoException e) { // Error de formato de RUT
                JOptionPane.showMessageDialog(eliminarServicio, e.getMessage(), "Error de formato de RUT", JOptionPane.ERROR_MESSAGE);
                eliminarServicio.limpiarCampos(); // Limpiar campos
            }
            return;
        }
        
        //ELIMINAR SERVICIO - BOTÓN ELIMINAR
        if(eliminarServicio != null && ae.getSource() == eliminarServicio.getBtnEliminar()){
            int indiceSeleccionado = eliminarServicio.getIndiceServicioSeleccionado();
            if(indiceSeleccionado < 0){
                JOptionPane.showMessageDialog(eliminarServicio, "Seleccione un servicio");
                return;
            }

            String rutCliente = eliminarServicio.getTxtRutCliente().getText().trim();
            String nombreMascota = eliminarServicio.getComboMascotas().getSelectedItem().toString();

            try { 
                validarFormatoRUT(rutCliente); // Validar formato del RUT
                DefaultTableModel modelo = (DefaultTableModel) eliminarServicio.getTblServicios().getModel();
                String tipoServicio = modelo.getValueAt(indiceSeleccionado, 0).toString();
                String fecha = modelo.getValueAt(indiceSeleccionado, 1).toString();
                int confirmacion = JOptionPane.showConfirmDialog(
                    eliminarServicio,
                    "¿Está seguro de que desea eliminar el servicio?\n" +     
                    "Tipo: " + tipoServicio + "\n" +
                    "Fecha: " + fecha + "\n" +
                    "Esta acción no se puede deshacer.",
                    "Confirmar eliminación",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
                );

                if(confirmacion == JOptionPane.YES_OPTION){
                    if(veterinaria.eliminarServicio(rutCliente, nombreMascota, indiceSeleccionado)){
                        JOptionPane.showMessageDialog(eliminarServicio, "Servicio eliminado exitosamente");
                        Cliente cliente = veterinaria.buscarClientePorRut(rutCliente);
                        if(cliente != null){
                            int serviciosRestantes = 0;
                            for(Mascota mascota : cliente.getMascotas()){
                                serviciosRestantes += mascota.getServicios().size();
                            }
                            if(cliente instanceof ClienteFrecuente && serviciosRestantes < 7){
                                veterinaria.revertirAClienteRegular(rutCliente);
                                JOptionPane.showMessageDialog(eliminarServicio, 
                                    "Advertencia: Cliente frecuente ahora tiene solo " + serviciosRestantes + " servicios");
                            }
                        }

                        List<Servicio> serviciosActualizados = veterinaria.buscarServiciosPorMascota(rutCliente, nombreMascota);
                        eliminarServicio.cargarServiciosDeMascota(serviciosActualizados);
                        eliminarServicio.limpiarCampos(); // Limpiar campos

                        if(listarClientesFrecuentes != null && listarClientesFrecuentes.isVisible()){
                            listarClientesFrecuentes.cargarDatos();
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(eliminarServicio, "Error al eliminar el servicio");
                    }
                }

            } catch (FormatoRUTInvalidoException e) {
                JOptionPane.showMessageDialog(eliminarServicio, e.getMessage(), "Error de formato de RUT", JOptionPane.ERROR_MESSAGE);
            }
            return;
        }
        
        //ELIMINAR SERVICIO - BOTÓN CANCELAR
        if(eliminarServicio != null && ae.getSource() == eliminarServicio.getBtnCancelar()){
            eliminarServicio.dispose();
            return;
        }
    
//==============================  MENU REPORTES  ===============================
        
        //LISTAR CLIENTES        
        if(ae.getSource() == main.getjMenuItemListarClientes()){ //Abrir ventana para mostrar lista de clientes
            listarClientes = new VentanaListarClientes(veterinaria);
            listarClientes.setVisible(true);
            return;
        }
        
        //LISTAR MASCOTAS        
        if(ae.getSource() == main.getjMenuItemListarMascotas()){ //Abrir ventana para mostrar lista de mascotas
            listarMascotas = new VentanaListarMascotas(veterinaria);
            listarMascotas.setVisible(true);
            return;
        }    
        
        //LISTAR SERVICIOS
        if(ae.getSource() == main.getjMenuItemListarServicios()){ //Abrir ventana para mostrar lista de servicios
            listarServicios = new VentanaListarServicios(veterinaria);
            listarServicios.setVisible(true);
            return;
        }
        
        //LISTAR CLIENTES FRECUENTES
        if(ae.getSource() == main.getjMenuItemListarClientesFrecuentes()){ // Crear ventana de clientes frecuentes con sus botones
            listarClientesFrecuentes = new VentanaListarClientesFrecuentes(veterinaria);
            listarClientesFrecuentes.getBtnActualizar().addActionListener(this);
            listarClientesFrecuentes.getBtnCerrar().addActionListener(this);
            listarClientesFrecuentes.setVisible(true);
            return;
        }
        
        //LISTAR CLIENTES FRECUENTES - BOTÓN ACTUALIZAR
        if(listarClientesFrecuentes != null && ae.getSource() == listarClientesFrecuentes.getBtnActualizar()){ //Verificar promociones pendientes y actualizar datos
            veterinaria.verificarPromocionesPendientes();
            listarClientesFrecuentes.cargarDatos();
            int cantidadClientes = veterinaria.obtenerClientesFrecuentes().size();
            JOptionPane.showMessageDialog(listarClientesFrecuentes, "Lista actualizada\n" + "Clientes frecuentes: " + cantidadClientes);            
        }
        
        //LISTAR CLIENTES FRECUENTES - BOTÓN CERRAR
        if(listarClientesFrecuentes != null && ae.getSource() == listarClientesFrecuentes.getBtnCerrar()){
            listarClientesFrecuentes.dispose(); //Cerrar ventana de clientes frecuentes
            return;
        }
        
        //REPORTES TXT
        if(ae.getSource() == main.getJMenuItemGenerarReporte()){ //Reporte txt
            String nombrePersonalizado = JOptionPane.showInputDialog(main, "Ingrese el nombre para el reporte:", "Nombre del Reporte", JOptionPane.QUESTION_MESSAGE); //Pedir nombre personalizado para el reporte

            if(nombrePersonalizado != null && !nombrePersonalizado.trim().isEmpty()){
                ReporteManager reporte = new ReporteManager(veterinaria); //Generar reporte con el nombre dado
                reporte.guardarReportePersonalizado(nombrePersonalizado.trim());
                JOptionPane.showMessageDialog(main, "Reporte generado exitosamente\nRevisa la carpeta 'Reportes'", "Reporte Generado", JOptionPane.INFORMATION_MESSAGE);
            }
            else{ //Si se cancela, o no se ingresa nombre
                JOptionPane.showMessageDialog(main, "Generación de reporte cancelada", "Cancelado", JOptionPane.WARNING_MESSAGE);
            }
        }
        
        //FUNCION ANALISIS SERVICIOS 
        if (ae.getSource() == main.getjMenuItemAnalisisServicios()) { //Crear ventana de análisis con su controlador
            VentanaAnalisisServicios ventanaAnalisis = new VentanaAnalisisServicios();
            ControladorAnalisisServicios controladorAnalisis = new ControladorAnalisisServicios(veterinaria, ventanaAnalisis);
            ventanaAnalisis.setVisible(true);
        }


//===============================  MENU SALIR  =================================
        
        //SALIR        
        if (ae.getSource() == main.getjMenuItemSalir()) {
            gestorCSV.guardarDatos(veterinaria); //Se guardan los clientes, mascotas y servicios
            System.exit(0); //Se cierra la aplicación
            return;
        }     
    }  

//=======================  METODOS AUXILIARES  =================================
    
    private void manejarSeleccionServicio() { //Maneja la selección de servicios en la tabla de editar
        if(editarServicio != null){
            int filaSeleccionada = editarServicio.getTblServicios().getSelectedRow();
            if(filaSeleccionada >= 0){ //Obtener datos de la fila seleccionada
                javax.swing.table.DefaultTableModel modelo = (javax.swing.table.DefaultTableModel)editarServicio.getTblServicios().getModel();
                String tipo = modelo.getValueAt(filaSeleccionada, 0).toString();
                String fecha = modelo.getValueAt(filaSeleccionada, 1).toString();
                String hora = modelo.getValueAt(filaSeleccionada, 2).toString();
                String descripcion = modelo.getValueAt(filaSeleccionada, 3).toString();
                int precio = Integer.parseInt(modelo.getValueAt(filaSeleccionada, 4).toString());
                String estado = modelo.getValueAt(filaSeleccionada, 5).toString();
                //Crear objeto servicio y mostrarlo en los campos
                Servicio servicio = new Servicio(tipo, fecha, hora, descripcion, precio, estado);
                editarServicio.mostrarServicioEnCampos(servicio);
                editarServicio.setIndiceServicioSeleccionado(filaSeleccionada);
            }
        }
    }
    
    private void manejarSeleccionServicioEliminar() { //Maneja la selección de servicios en la tabla de eliminar
        if(eliminarServicio != null){
            int filaSeleccionada = eliminarServicio.getTblServicios().getSelectedRow();
            if(filaSeleccionada >= 0){ //Mostrar el servicio seleccionado para confirmar eliminación
                eliminarServicio.mostrarServicioSeleccionado(filaSeleccionada);
            }
        }
    }
    
//============================EXCEPTIONS========================================
    
    private void validarFormatoRUT(String rut) throws FormatoRUTInvalidoException { //Valida que el RUT tenga el formato correcto
        if (rut == null || rut.trim().isEmpty()) {
            throw new FormatoRUTInvalidoException("El RUT no puede estar vacío");
        }
    
        //Verificar formato: XX.XXX.XXX-X o XXXXXXXX-X
        if (!rut.matches("\\d{1,2}\\.\\d{3}\\.\\d{3}-[\\dkK]") && 
            !rut.matches("\\d{7,8}-[\\dkK]")) {
            throw new FormatoRUTInvalidoException("El formato del RUT debe ser XX.XXX.XXX-X o XXXXXXXX-X");
        }
    }
    
    private void validarEdad(int edad) throws RangoInvalidoException { //Valida que la edad esté en un rango válido
        if (edad <= 0) {
            throw new RangoInvalidoException("La edad debe ser mayor a 0");
        }
        if (edad > 50) {
            throw new RangoInvalidoException("La edad ingresada parece demasiado alta para una mascota");
        }
    }
    
    private void validarPrecio(int precio) throws RangoInvalidoException { //Valida que el precio esté en un rango válido
        if (precio <= 0) {
            throw new RangoInvalidoException("El precio debe ser mayor a 0");
        }
        if (precio > 1000000) {
            throw new RangoInvalidoException("El precio ingresado parece demasiado alto");
        }
    }

    private void validarFormatoTelefono(String telefono) throws FormatoTelefonoInvalidoException { //Valida que el teléfono tenga el formato correcto
        if (telefono == null || telefono.trim().isEmpty()) {
            throw new FormatoTelefonoInvalidoException("El teléfono no puede estar vacío");
        }
        //Verificar formato: +569XXXXXXXX o 9XXXXXXXX
        if (!telefono.matches("\\+569\\d{8}") && !telefono.matches("9\\d{8}")) {
            throw new FormatoTelefonoInvalidoException("El formato del teléfono debe ser +569XXXXXXXX o 9XXXXXXXX");
        }
    }
}
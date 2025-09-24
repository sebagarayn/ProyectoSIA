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

public class ControladorVeterinaria implements ActionListener {
    private Veterinaria veterinaria;
    private VentanaMain main;
    
    //CLIENTES
    private VentanaAgregarCliente agregarCliente;
    private VentanaBuscarCliente buscarCliente;
    private VentanaEditarCliente editarCliente;
    private VentanaEliminarCliente eliminarCliente;
    
    //MASCOTAS
    private VentanaAgregarMascota agregarMascota;
    private VentanaBuscarMascota buscarMascota;
    private VentanaEditarMascota editarMascota;
    private VentanaEliminarMascota eliminarMascota;
    
    //SERVICIOS
    private VentanaAgregarServicio agregarServicio;
    private VentanaBuscarServicio buscarServicio;
    private VentanaEditarServicio editarServicio;
    private VentanaEliminarServicio eliminarServicio;
    
    //REPORTES
    private VentanaListarClientes listarClientes;
    private VentanaListarMascotas listarMascotas;
    private VentanaListarServicios listarServicios;
    
    public void iniciar() {
        veterinaria = new Veterinaria(); //Iniciar veterinaria
        
        //CARGAR DATOS
        veterinaria.cargarClientesCSV(); //Cargar los datos de los clientes del csv
        veterinaria.cargarMascotasCSV(); //Cargar los datos de las mascotas del csv
        veterinaria.cargarServiciosCSV(); //Cargar los datos de los servicios del csv
        
        main = new VentanaMain();
        
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
            
            if (veterinaria.agregarCliente(nombre, rut, telefono, direccion)) {
                JOptionPane.showMessageDialog(agregarCliente, "Cliente agregado exitosamente.");
            }
            agregarCliente.dispose();
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
            Cliente cliente = veterinaria.buscarClientePorRut(rut);
            if (cliente != null) {
                String info = "Cliente: " + cliente.getNombre() + "\n" +
                             "RUT: " + cliente.getRut() + "\n" +
                             "Teléfono: " + cliente.getTelefono() + "\n" +
                             "Dirección: " + cliente.getDireccion() + "\n" +
                             "Mascotas: " + cliente.getMascotas().size();
                JOptionPane.showMessageDialog(buscarCliente, info);
            } else {
                JOptionPane.showMessageDialog(buscarCliente, "Cliente no encontrado.");
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
            Cliente cliente = veterinaria.buscarClientePorRut(rut);
            if(cliente != null){
                editarCliente.getTxtNombre().setText(cliente.getNombre());
                editarCliente.getTxtTelefono().setText(cliente.getTelefono());
                editarCliente.getTxtDireccion().setText(cliente.getDireccion());
                editarCliente.habilitarEdicion(true);
            }
            else{
                JOptionPane.showMessageDialog(editarCliente, "Cliento no encontrado");
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
            
            if(veterinaria.editarCliente(rut, nombre, telefono, direccion)){
                JOptionPane.showMessageDialog(editarCliente, "Cliente editado exitosamente");
                editarCliente.dispose();
            }
            else{
                JOptionPane.showMessageDialog(editarCliente, "Error al editar cliente");
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
            Cliente cliente = veterinaria.buscarClientePorRut(rut);
            if(cliente != null){
                eliminarCliente.getTxtNombre().setText(cliente.getNombre());
                eliminarCliente.getTxtTelefono().setText(cliente.getTelefono());
                eliminarCliente.getTxtDireccion().setText(cliente.getDireccion());
                eliminarCliente.getTxtNumMascotas().setText(String.valueOf(cliente.getMascotas().size()));
                eliminarCliente.habilitarEliminacion(true);
            }
            else{
                JOptionPane.showMessageDialog(eliminarCliente, "Cliente no encontrado");
                eliminarCliente.limpiarCampos();
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
            int edad = Integer.parseInt(agregarMascota.getjTextFieldEdad().getText());
            
            Cliente dueno = veterinaria.buscarClientePorRut(rutDueno);
            if (dueno != null) {
                dueno.agregarMascota(nombre, tipo, raza, edad);
                JOptionPane.showMessageDialog(agregarMascota, "Mascota agregada exitosamente.");
            } else {
                JOptionPane.showMessageDialog(agregarMascota, "Cliente no encontrado.");
            }
            agregarMascota.dispose();
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
            Cliente cliente = veterinaria.buscarClientePorRut(rutDueno);
            if(cliente != null){
                Mascota mascota = cliente.buscarMascotaPorNombre(nombreMascota);
                if (mascota != null) {
                    String info = "Nombre: " + mascota.getNombre() + "\n" +
                                 "Dueño:" + cliente.getNombre () + " (RUT:" + cliente.getRut() + ")" + "\n" +
                                 "Tipo: " + mascota.getTipo() + "\n" +
                                 "Raza: " + mascota.getRaza() + "\n" +
                                 "Edad: " + mascota.getEdad();
                    JOptionPane.showMessageDialog(buscarMascota, info);
                } else {
                    JOptionPane.showMessageDialog(buscarMascota, "Mascota no encontrada.");
                }
                return;
            }
            JOptionPane.showMessageDialog(buscarMascota, "Cliente no encontrado.");
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
            Cliente cliente = veterinaria.buscarClientePorRut(rutDueno);
            if(cliente != null){
                Mascota mascota = cliente.buscarMascotaPorNombre(nombreMascota);
                if(mascota != null){
                    editarMascota.getTxtTipo().setText(mascota.getTipo());
                    editarMascota.getTxtRaza().setText(mascota.getRaza());
                    editarMascota.getTxtEdad().setText(String.valueOf(mascota.getEdad()));
                    editarMascota.habilitarEdicion(true);
                }
                else{
                    JOptionPane.showMessageDialog(editarMascota, "Mascota no encontrado");
                editarMascota.limpiarCampos();
                }
            }
            else{
                JOptionPane.showMessageDialog(editarMascota, "Cleinte no encontrado");
                editarMascota.limpiarCampos();
            }
            return;
        }
        
        //EDITAR MASCOTA - BOTÓN GUARDAR        
        if(editarMascota != null && ae.getSource() == editarMascota.getBtnGuardar()){
            String rutDueno = editarMascota.getTxtRutDueno().getText();
            String nombreMascota = editarMascota.getTxtNombreMascota().getText();
            String tipo = editarMascota.getTxtTipo().getText();
            String raza = editarMascota.getTxtRaza().getText();
            int edad = Integer.parseInt(editarMascota.getTxtEdad().getText());
            
            if(veterinaria.editarMascota(rutDueno, nombreMascota, tipo, raza, edad)){
                JOptionPane.showMessageDialog(editarMascota, "Mascota editada exitosamente");
                editarMascota.dispose();
            }
            else{
                JOptionPane.showMessageDialog(editarMascota, "Errora al editar mascota");
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
            
            Cliente dueno = veterinaria.buscarClientePorRut(rutDueno);
            if(dueno != null){
                Mascota mascota = dueno.buscarMascotaPorNombre(nombreMascota);
                if(mascota != null){
                    eliminarMascota.getTxtTipo().setText(mascota.getTipo());
                    eliminarMascota.getTxtRaza().setText(mascota.getRaza());
                    eliminarMascota.getTxtEdad().setText(String.valueOf(mascota.getEdad()));
                    eliminarMascota.habilitarEliminacion(true);
                }
                else{
                    JOptionPane.showMessageDialog(eliminarMascota, "Mascota no encontrada");
                    eliminarMascota.limpiarCampos();
                }
            }
            else{
                JOptionPane.showMessageDialog(eliminarMascota, "Cliente no encontrado");
                eliminarMascota.limpiarCampos();
            }
            return;
        }
        
        //ELIMINAR MASCOTA - BOTÓN ELIMINAR
        if(eliminarMascota != null && ae.getSource() == eliminarMascota.getBtnEliminar()){
            String rutDueno = eliminarMascota.getTxtRutDueno().getText();
            String nombreMascota = eliminarMascota.getTxtNombre().getText();
            
            int confirmacion = JOptionPane.showConfirmDialog(eliminarMascota,
                    "Estas seguro de querer eliminar esta mascota?",
                    "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
            if(confirmacion == JOptionPane.YES_OPTION){
                if(veterinaria.eliminarMascota(rutDueno, nombreMascota)){
                    JOptionPane.showMessageDialog(eliminarMascota, "Mascota eliminada exitosamente");
                    eliminarMascota.dispose();
                }
                else{
                    JOptionPane.showMessageDialog(eliminarMascota, "Error al eliminar mascota");
                }
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
            Cliente dueno = veterinaria.buscarClientePorRut(rutDueno);
            if(dueno != null){
                agregarServicio.cargarMascotasDelCliente(dueno);
                JOptionPane.showMessageDialog(agregarServicio, "Cliente encontrado, seleccione una o más mascotas");
            }
            else{
                JOptionPane.showMessageDialog(agregarServicio, "Cliente no encontrado");
                agregarServicio.limpiarMascotas();
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
            
            if(rutDueno.isEmpty() || nombreMascota == null || tipoServicio.isEmpty() ||
                fecha.isEmpty() || hora.isEmpty() || descripcion.isEmpty() || precioTexto.isEmpty()){
                JOptionPane.showMessageDialog(agregarServicio, "Por favor, completa todos los campos");
                return;
            }
            int precio = 0 ;
            try{
                precio = Integer.parseInt(precioTexto);
                if(precio <= 0){
                    JOptionPane.showMessageDialog(agregarServicio, "El precio debe ser mayor a 0");
                    agregarServicio.getTxtPrecio().setText(""); //Limpiar el campo incorrecto
                    agregarServicio.getTxtPrecio().requestFocus(); //Resaltar el campo a corregir
                    return;
                }
            }catch(NumberFormatException e){
                JOptionPane.showMessageDialog(agregarServicio, "El precio debe ser un número válido");
                agregarServicio.getTxtPrecio().setText(""); //Limpiar el campo incorrecto
                agregarServicio.getTxtPrecio().requestFocus(); //Resaltar el campo a corregir
                return;
            }
            Cliente dueno = veterinaria.buscarClientePorRut(rutDueno);
            if(dueno != null){
                Mascota mascota = dueno.buscarMascotaPorNombre(nombreMascota);
                if(mascota != null){
                    mascota.agregarServicio(tipoServicio, fecha, hora, descripcion, precio, estado);
                    JOptionPane.showMessageDialog(agregarServicio, "Servicio agregado correctamente");
                    agregarServicio.dispose();
                }else{
                    JOptionPane.showMessageDialog(agregarServicio, "Error: La mascota seleccionada no existe");
                }
            }else{
                JOptionPane.showMessageDialog(agregarServicio, "Error: El cliente no existe");
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
            if(rutCliente.isEmpty()){
                JOptionPane.showMessageDialog(buscarServicio, "Por favor ingresa un rut");
                return;
            }
            Cliente cliente = veterinaria.buscarClientePorRut(rutCliente);
            if(cliente != null){
                List<String> nombreMascotas = new ArrayList<>();
                for(Mascota mascota : cliente.getMascotas()){
                    nombreMascotas.add(mascota.getNombre());
                }
                buscarServicio.cargarMascotasDelCliente(nombreMascotas);
            } else{
                JOptionPane.showMessageDialog(buscarServicio, "Cliente no encontrado");
                buscarServicio.limpiarResultados();
            }
            return;
        }
        
        //BUSCAR SERVICIO - BOTÓN BUSCAR MASCOTA
        if (buscarServicio != null && ae.getSource() == buscarServicio.getBtnBuscarMascota()) {
            String rutCliente = buscarServicio.getTxtRutCliente().getText().trim();
            String nombreMascota = "";
            
            if (buscarServicio.getComboMascotas().getSelectedItem() != null) {
                nombreMascota = buscarServicio.getComboMascotas().getSelectedItem().toString();
            }
            else{
                JOptionPane.showMessageDialog(buscarServicio, "Por favor, seleccione una mascota de la lista.");
                return;
            }
            if (rutCliente.isEmpty() || nombreMascota.isEmpty()) {
                JOptionPane.showMessageDialog(buscarServicio, "Por favor, complete RUT y seleccione una mascota.");
                return;
            }
            List<Servicio> servicios = veterinaria.buscarServiciosPorMascota(rutCliente, nombreMascota);
            if(!servicios.isEmpty()){
                buscarServicio.mostrarServicios(servicios);
                JOptionPane.showMessageDialog(buscarServicio, "Se encontraron " + servicios.size() + " servicio(s) para la mascota");
            }
            else{
                JOptionPane.showMessageDialog(buscarServicio, "No se encontraron servicios para la mascota seleccionada");
                buscarServicio.limpiarResultados();
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
            if(rutCliente.isEmpty()){
                JOptionPane.showMessageDialog(editarServicio, "Por favor ingresa un RUT");
                return;
            }
            Cliente cliente = veterinaria.buscarClientePorRut(rutCliente);
            if(cliente != null){
                editarServicio.cargarMascotasDelCliente(cliente);
                JOptionPane.showMessageDialog(editarServicio, "Cliente encontrado, selecciona una mascota");
            }
            else{
                JOptionPane.showMessageDialog(editarServicio, "Cliente no encontrado");
                editarServicio.limpiarCampos();
            }
            return;
        }
        
        //EDITAR SERVICIO - BOTÓN BUSCAR MASCOTA
        if(editarServicio != null && ae.getSource() == editarServicio.getBtnBuscarMascota()){
            String rutCliente = editarServicio.getTxtRutCliente().getText().trim();
            String nombreMascota = "";
            
            if(editarServicio.getComboMascotas().getSelectedItem() != null){
                nombreMascota = editarServicio.getComboMascotas().getSelectedItem().toString();
            }
            else{
                JOptionPane.showMessageDialog(editarServicio, "Selecciona una mascota");
                return;
            }
            
            if(rutCliente.isEmpty() || nombreMascota.isEmpty()){
                JOptionPane.showMessageDialog(editarServicio, "Completar RUT y seleccionar mascota");
                return;
            }  
            List<Servicio> servicios = veterinaria.buscarServiciosPorMascota(rutCliente, nombreMascota);
            if(!servicios.isEmpty()){
                editarServicio.cargarServiciosDeMascota(servicios);
                JOptionPane.showMessageDialog(editarServicio, "Se encontraron " + servicios.size() + " servicios");
            }
            else{
                JOptionPane.showMessageDialog(editarServicio, "No se encontraron servicios");
                editarServicio.limpiarCampos();
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
            
            if(tipoServicio.isEmpty() || fecha.isEmpty() || hora.isEmpty() || descripcion.isEmpty() || precioTexto.isEmpty()){
                JOptionPane.showMessageDialog(editarServicio, "Completa todos los campos");
                return;
            }
            int precio = 0;
            try{
                precio = Integer.parseInt(precioTexto);
                if(precio <= 0){
                    JOptionPane.showMessageDialog(editarServicio, "El precio debe ser mayor a 0");
                    return;
                }
            } catch(NumberFormatException e){
                JOptionPane.showMessageDialog(editarServicio, "El precio debe ser un número valido");
                return;
            }
            
            Servicio servicioActualizado = new Servicio(tipoServicio, fecha, hora, descripcion, precio, estado);
            if(veterinaria.editarServicio(rutCliente, nombreMascota, indiceSeleccionado, servicioActualizado)){
                JOptionPane.showMessageDialog(editarServicio, "Servicio actualizado correctamente");
                List<Servicio> servicios = veterinaria.buscarServiciosPorMascota(rutCliente, nombreMascota);
                editarServicio.cargarServiciosDeMascota(servicios);
                editarServicio.limpiarCampos();
            }
            else{
                JOptionPane.showMessageDialog(editarServicio, "Error al editar el servicio");
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
            if(rutCliente.isEmpty()){
                JOptionPane.showMessageDialog(eliminarServicio, "Por favor ingresa RUT");
                return;
            }
            Cliente cliente = veterinaria.buscarClientePorRut(rutCliente);
            if(cliente != null){
                eliminarServicio.cargarMascotasDelCliente(cliente);
                JOptionPane.showMessageDialog(eliminarServicio, "Cliente encontrado");
            }
            else{
                JOptionPane.showMessageDialog(eliminarServicio, "Cliente no encontrado");
                eliminarServicio.limpiarCampos();
            }
            return;
        }
        
        //ELIMINAR SERVICIO - BOTÓN BUSCAR MASCOTA
        if(eliminarServicio != null && ae.getSource() == eliminarServicio.getBtnBuscarMascota()){
            String rutCliente = eliminarServicio.getTxtRutCliente().getText().trim();
            String nombreMascota = "";
            
            if(eliminarServicio.getComboMascotas().getSelectedItem() != null){
                nombreMascota = eliminarServicio.getComboMascotas().getSelectedItem().toString();
            }
            else{
                JOptionPane.showMessageDialog(eliminarServicio, "Seleccione una mascota");
                return;
            }
            List<Servicio> servicios = veterinaria.buscarServiciosPorMascota(rutCliente, nombreMascota);
            if(!servicios.isEmpty()){
                eliminarServicio.cargarServiciosDeMascota(servicios);
                JOptionPane.showMessageDialog(eliminarServicio, "Se encontraron " + servicios.size() + " servicios");
            }
            else{
                JOptionPane.showMessageDialog(eliminarServicio, "No se encontraron servicios");
                eliminarServicio.limpiarCampos();
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
                    List<Servicio> serviciosActualizados = veterinaria.buscarServiciosPorMascota(rutCliente, nombreMascota);
                    eliminarServicio.cargarServiciosDeMascota(serviciosActualizados);
                    eliminarServicio.limpiarCampos();
                }
                else{
                    JOptionPane.showMessageDialog(eliminarServicio, "Errora al eliminar el servicio");
                }
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
        if(ae.getSource() == main.getjMenuItemListarClientes()){
            listarClientes = new VentanaListarClientes(veterinaria);
            listarClientes.setVisible(true);
            return;
        }
        
        //LISTAR MASCOTAS        
        if(ae.getSource() == main.getjMenuItemListarMascotas()){
            listarMascotas = new VentanaListarMascotas(veterinaria);
            listarMascotas.setVisible(true);
            return;
        }    
        
        //LISTAR SERVICIOS
        if(ae.getSource() == main.getjMenuItemListarServicios()){
            listarServicios = new VentanaListarServicios(veterinaria);
            listarServicios.setVisible(true);
            return;
        }

//===============================  MENU SALIR  =================================
        
        //SALIR        
        if (ae.getSource() == main.getjMenuItemSalir()) {
            veterinaria.guardarClientesCSV();
            veterinaria.guardarMascotasCSV();
            veterinaria.guardarServiciosCSV();
            System.exit(0);
            return;
        }
    }  

//=======================  METODOS AUXILIARES  =================================
    private void manejarSeleccionServicio() {
        if(editarServicio != null){
            int filaSeleccionada = editarServicio.getTblServicios().getSelectedRow();
            if(filaSeleccionada >= 0){
                javax.swing.table.DefaultTableModel modelo = (javax.swing.table.DefaultTableModel)editarServicio.getTblServicios().getModel();
                String tipo = modelo.getValueAt(filaSeleccionada, 0).toString();
                String fecha = modelo.getValueAt(filaSeleccionada, 1).toString();
                String hora = modelo.getValueAt(filaSeleccionada, 2).toString();
                String descripcion = modelo.getValueAt(filaSeleccionada, 3).toString();
                int precio = Integer.parseInt(modelo.getValueAt(filaSeleccionada, 4).toString());
                String estado = modelo.getValueAt(filaSeleccionada, 5).toString();
                
                Servicio servicio = new Servicio(tipo, fecha, hora, descripcion, precio, estado);
                editarServicio.mostrarServicioEnCampos(servicio);
                editarServicio.setIndiceServicioSeleccionado(filaSeleccionada);
            }
        }
    }
    
    private void manejarSeleccionServicioEliminar() {
        if(eliminarServicio != null){
            int filaSeleccionada = eliminarServicio.getTblServicios().getSelectedRow();
            if(filaSeleccionada >= 0){
                eliminarServicio.mostrarServicioSeleccionado(filaSeleccionada);
            }
        }
    }
}
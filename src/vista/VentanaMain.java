//Rev.24-09
package vista;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VentanaMain extends javax.swing.JFrame {

    public VentanaMain() {
        initComponents();
        configurarImagenFondo();
    }
    
    //MENU CLIENTES
    public javax.swing.JMenuItem getjMenuItemAgregarCliente() {return jMenuItemAgregarCliente;} //Submenu->Agregar Cliente
    public javax.swing.JMenuItem getjMenuItemBuscarCliente() {return jMenuItemBuscarCliente;} //Submenu->Buscar Cliente
    public javax.swing.JMenuItem getjMenuItemEditarCliente() {return jMenuItemEditarCliente;} //Submenu->Editar Cliente   
    public javax.swing.JMenuItem getjMenuItemEliminarCliente() {return jMenuItemEliminarCliente;} //Submenu->Eliminar Cliente     
    
    //MENU MASCOTAS
    public javax.swing.JMenuItem getjMenuItemAgregarMascota() { //Submenu->Agregar Mascota
        return jMenuItemAgregarMascota;
    }
    public javax.swing.JMenuItem getjMenuItemBuscarMascota() { //Submenu->Buscar Mascota
        return jMenuItemBuscarMascota;
    }    
    public javax.swing.JMenuItem getjMenuItemEditarMascota() { //Submenu->Editar Mascota
        return jMenuItemEditarMascota;
    }  
    public javax.swing.JMenuItem getjMenuItemEliminarMascota() { //Submenu->Eliminar Mascota
        return jMenuItemEliminarMascota;
    }  
    
    //MENU SERVICIOS
    public javax.swing.JMenuItem getjMenuItemAgregarServicio() { //Submenu->Agregar Servicio
        return jMenuItemAgregarServicio;
    }
    public javax.swing.JMenuItem getjMenuItemBuscarServicio() { //Submenu->Buscar Servicio
        return jMenuItemBuscarServicio;
    }
    public javax.swing.JMenuItem getjMenuItemEditarServicio() { //Submenu->Editar Servicio
        return jMenuItemEditarServicio;
    }   
    public javax.swing.JMenuItem getjMenuItemEliminarServicio() { //Submenu->Eliminar Servicio
        return jMenuItemEliminarServicio;
    }      
    
    //MENU REPORTES
    public javax.swing.JMenuItem getjMenuItemListarClientes() { //Submenu->Listar Clientes
        return jMenuItemListarClientes;
    }   
    public javax.swing.JMenuItem getjMenuItemListarMascotas() { //Submenu->Listar Mascotas
        return jMenuItemListarMascotas;
    }    
    public javax.swing.JMenuItem getjMenuItemListarServicios() { //Submenu->Listar Servicios
        return jMenuItemListarServicios;
    }  
    public javax.swing.JMenuItem getjMenuItemListarClientesFrecuentes() { //Submenu->Listar ClientesFrecuentes
        return jMenuItemListarClientesFrecuentes;
    } 
    public javax.swing.JMenuItem getJMenuItemGenerarReporte() { //Para el reporte txt y html
        return jMenuItemGenerarReporte;
    }
    public javax.swing.JMenuItem getjMenuItemAnalisisServicios() {
        return jMenuItemAnalisisServicios;
    }
         
    //MENU SALIR
    public javax.swing.JMenuItem getjMenuItemSalir() { //Submenu->Salir del Programa
        return jMenuItemSalir;
    }
    
    private void configurarImagenFondo(){
        try{
            ImageIcon imagenFondo = new ImageIcon(getClass().getResource("/imagenes/fondo_veterinaria.jpg"));
            JLabel labelFondo = new JLabel(imagenFondo);
            labelFondo.setSize(getSize());
            getLayeredPane().add(labelFondo, Integer.valueOf(Integer.MIN_VALUE));
            ((JPanel) getContentPane()).setOpaque(false);
        } catch (Exception e) {
            System.err.println("No se pudo cargar la imagen de fondo");
        }
    }

//============================  CODIGO AUTOMATICO  =============================
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItemAgregarCliente = new javax.swing.JMenuItem();
        jMenuItemBuscarCliente = new javax.swing.JMenuItem();
        jMenuItemEditarCliente = new javax.swing.JMenuItem();
        jMenuItemEliminarCliente = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItemAgregarMascota = new javax.swing.JMenuItem();
        jMenuItemBuscarMascota = new javax.swing.JMenuItem();
        jMenuItemEditarMascota = new javax.swing.JMenuItem();
        jMenuItemEliminarMascota = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItemAgregarServicio = new javax.swing.JMenuItem();
        jMenuItemBuscarServicio = new javax.swing.JMenuItem();
        jMenuItemEditarServicio = new javax.swing.JMenuItem();
        jMenuItemEliminarServicio = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItemListarClientes = new javax.swing.JMenuItem();
        jMenuItemListarMascotas = new javax.swing.JMenuItem();
        jMenuItemListarServicios = new javax.swing.JMenuItem();
        jMenuItemListarClientesFrecuentes = new javax.swing.JMenuItem();
        jMenuItemGenerarReporte = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jMenuItemAnalisisServicios = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItemSalir = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema de Veterinaria");
        setBackground(new java.awt.Color(0, 153, 153));
        setMaximumSize(new java.awt.Dimension(1000, 700));
        setMinimumSize(new java.awt.Dimension(1000, 700));
        setPreferredSize(new java.awt.Dimension(1000, 700));

        jMenu1.setText("Clientes");

        jMenuItemAgregarCliente.setText("Agregar Cliente");
        jMenu1.add(jMenuItemAgregarCliente);

        jMenuItemBuscarCliente.setText("Buscar Cliente");
        jMenu1.add(jMenuItemBuscarCliente);

        jMenuItemEditarCliente.setText("Editar Cliente");
        jMenu1.add(jMenuItemEditarCliente);

        jMenuItemEliminarCliente.setText("Eliminar Cliente");
        jMenu1.add(jMenuItemEliminarCliente);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Mascotas");

        jMenuItemAgregarMascota.setText("Agregar Mascota");
        jMenu2.add(jMenuItemAgregarMascota);

        jMenuItemBuscarMascota.setText("Buscar Mascota");
        jMenu2.add(jMenuItemBuscarMascota);

        jMenuItemEditarMascota.setText("Editar Mascota");
        jMenu2.add(jMenuItemEditarMascota);

        jMenuItemEliminarMascota.setText("Eliminar Mascota");
        jMenu2.add(jMenuItemEliminarMascota);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Servicios");

        jMenuItemAgregarServicio.setText("Agregar Servicio");
        jMenu3.add(jMenuItemAgregarServicio);

        jMenuItemBuscarServicio.setText("Buscar Servicio");
        jMenu3.add(jMenuItemBuscarServicio);

        jMenuItemEditarServicio.setText("Editar Servicio");
        jMenu3.add(jMenuItemEditarServicio);

        jMenuItemEliminarServicio.setText("Eliminar Servicio");
        jMenu3.add(jMenuItemEliminarServicio);

        jMenuBar1.add(jMenu3);

        jMenu5.setText("Reportes");

        jMenuItemListarClientes.setText("Listar Clientes");
        jMenu5.add(jMenuItemListarClientes);

        jMenuItemListarMascotas.setText("Listar Mascotas");
        jMenu5.add(jMenuItemListarMascotas);

        jMenuItemListarServicios.setText("Listar Servicios");
        jMenu5.add(jMenuItemListarServicios);

        jMenuItemListarClientesFrecuentes.setText("Listar Clientes Frecuentes");
        jMenu5.add(jMenuItemListarClientesFrecuentes);

        jMenuItemGenerarReporte.setText("Generar Reporte");
        jMenu5.add(jMenuItemGenerarReporte);

        jMenuBar1.add(jMenu5);

        jMenu6.setText("Extras");

        jMenuItemAnalisisServicios.setText("Analisis Servicios");
        jMenu6.add(jMenuItemAnalisisServicios);

        jMenuBar1.add(jMenu6);

        jMenu4.setText("Salir");

        jMenuItemSalir.setText("Salir del Progama");
        jMenu4.add(jMenuItemSalir);

        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 278, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItemAgregarCliente;
    private javax.swing.JMenuItem jMenuItemAgregarMascota;
    private javax.swing.JMenuItem jMenuItemAgregarServicio;
    private javax.swing.JMenuItem jMenuItemAnalisisServicios;
    private javax.swing.JMenuItem jMenuItemBuscarCliente;
    private javax.swing.JMenuItem jMenuItemBuscarMascota;
    private javax.swing.JMenuItem jMenuItemBuscarServicio;
    private javax.swing.JMenuItem jMenuItemEditarCliente;
    private javax.swing.JMenuItem jMenuItemEditarMascota;
    private javax.swing.JMenuItem jMenuItemEditarServicio;
    private javax.swing.JMenuItem jMenuItemEliminarCliente;
    private javax.swing.JMenuItem jMenuItemEliminarMascota;
    private javax.swing.JMenuItem jMenuItemEliminarServicio;
    private javax.swing.JMenuItem jMenuItemGenerarReporte;
    private javax.swing.JMenuItem jMenuItemListarClientes;
    private javax.swing.JMenuItem jMenuItemListarClientesFrecuentes;
    private javax.swing.JMenuItem jMenuItemListarMascotas;
    private javax.swing.JMenuItem jMenuItemListarServicios;
    private javax.swing.JMenuItem jMenuItemSalir;
    // End of variables declaration//GEN-END:variables
}

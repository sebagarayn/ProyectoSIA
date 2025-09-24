//Rev.24-09
package vista;

import modelo.Cliente;
import modelo.Mascota;
import modelo.Servicio;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class VentanaEliminarServicio extends javax.swing.JFrame {
    private DefaultTableModel modeloTabla;
    private int indiceServicioSeleccionado = -1;

    public VentanaEliminarServicio() {
        initComponents();
        setLocationRelativeTo(null);
        configurarComponentes();
        habilitarEliminacion(false);
    }
    
    private void configurarComponentes(){
        modeloTabla = (DefaultTableModel) tblServicios.getModel();
        modeloTabla.setColumnIdentifiers(new String[]{
           "Tipo Servicio", "Fecha", "Hora", "Descripción", "Precio", "Estado" 
        });
        habilitarBusquedaMascota(false);
        habilitarEliminacion(false);
    }
    
    public void cargarMascotasDelCliente(Cliente cliente){
        comboMascotas.removeAllItems();
        if(cliente != null && !cliente.getMascotas().isEmpty()){
            for(Mascota mascota : cliente.getMascotas()){
                comboMascotas.addItem(mascota.getNombre());
            }
            habilitarBusquedaMascota(true);
        }
        else{
            habilitarBusquedaMascota(false);
            if(cliente != null){
                JOptionPane.showMessageDialog(this, "El cliente no tiene mascotas registradas");
            }
        }
    }
    
    public void cargarServiciosDeMascota(List<Servicio> servicios){
        modeloTabla.setRowCount(0);
        if(servicios != null && !servicios.isEmpty()){
            for(Servicio servicio : servicios){
                Object[] fila = {
                    servicio.getTipoServicio(),
                    servicio.getFecha(),
                    servicio.getHora(),
                    servicio.getDescripcion(),
                    servicio.getPrecio(),
                    servicio.getEstado()
                };
                modeloTabla.addRow(fila);
            }
            habilitarTabla(true);
        }
        else{
            habilitarTabla(false);
            limpiarCampos();
        }
    }
    
    public void mostrarServicioSeleccionado(int filaSeleccionada){
        if(filaSeleccionada >= 0){
            indiceServicioSeleccionado = filaSeleccionada;
            habilitarEliminacion(true);
        }
    }
    
    public void habilitarBusquedaMascota(boolean habilitar) {
        comboMascotas.setEnabled(habilitar);
        btnBuscarMascota.setEnabled(habilitar);
    }
    
    public void habilitarTabla(boolean habilitar){
        tblServicios.setEnabled(habilitar);
    }
    
    public void habilitarEliminacion(boolean habilitar){
        btnEliminar.setEnabled(habilitar);
    }
    
    public void limpiarCampos(){
        modeloTabla.setRowCount(0);
        habilitarEliminacion(false);
        indiceServicioSeleccionado = -1;
    }

//==============================================================================
    
    public JTextField getTxtRutCliente() {return txtRutCliente;}
    public JComboBox<String> getComboMascotas() {return comboMascotas;}
    public JTable getTblServicios() {return tblServicios;}
    public JButton getBtnBuscarCliente() {return btnBuscarCliente;}
    public JButton getBtnBuscarMascota() {return btnBuscarMascota;}
    public JButton getBtnEliminar() {return btnEliminar;}
    public JButton getBtnCancelar() {return btnCancelar;}
    public int getIndiceServicioSeleccionado() {return indiceServicioSeleccionado;}

//==============================================================================
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtRutCliente = new javax.swing.JTextField();
        comboMascotas = new javax.swing.JComboBox<>();
        btnBuscarCliente = new javax.swing.JButton();
        btnBuscarMascota = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblServicios = new javax.swing.JTable();
        btnEliminar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("RUT Cliente:");

        jLabel2.setText("Nombre Mascota:");

        txtRutCliente.setMaximumSize(new java.awt.Dimension(100, 20));
        txtRutCliente.setMinimumSize(new java.awt.Dimension(100, 20));
        txtRutCliente.setPreferredSize(new java.awt.Dimension(100, 20));

        comboMascotas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnBuscarCliente.setText("Buscar Cliente");

        btnBuscarMascota.setText("Buscar Mascota");
        btnBuscarMascota.setMaximumSize(new java.awt.Dimension(100, 20));
        btnBuscarMascota.setMinimumSize(new java.awt.Dimension(100, 20));
        btnBuscarMascota.setPreferredSize(new java.awt.Dimension(100, 20));

        tblServicios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Tipo Servicio", "Fecha", "Hora", "Descripción", "Precio", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblServicios.setPreferredSize(new java.awt.Dimension(600, 150));
        jScrollPane1.setViewportView(tblServicios);

        btnEliminar.setText("Eliminar");

        btnCancelar.setText("Cancelar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtRutCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnBuscarCliente))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(comboMascotas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(btnBuscarMascota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(btnEliminar)
                        .addGap(53, 53, 53)
                        .addComponent(btnCancelar)))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtRutCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarCliente))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(comboMascotas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarMascota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEliminar)
                    .addComponent(btnCancelar))
                .addContainerGap(107, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarCliente;
    private javax.swing.JButton btnBuscarMascota;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JComboBox<String> comboMascotas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblServicios;
    private javax.swing.JTextField txtRutCliente;
    // End of variables declaration//GEN-END:variables
}

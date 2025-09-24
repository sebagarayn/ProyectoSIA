//Rev.24-09
package vista;

import modelo.Cliente;
import modelo.Mascota;
import modelo.Servicio;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class VentanaEditarServicio extends javax.swing.JFrame {
    private DefaultTableModel modeloTabla;

    public VentanaEditarServicio() {
        initComponents();
        setLocationRelativeTo(null);
        configurarComponentes();
        habilitarEdicion(false);
    }
    
    private void configurarComponentes() {
        comboEstado.removeAllItems();
        comboEstado.addItem("Pendiente");
        comboEstado.addItem("Realizado");
        comboEstado.setEnabled(false);
        
        modeloTabla = (DefaultTableModel) tblServicios.getModel();
        modeloTabla.setColumnIdentifiers(new String[]{
            "Tipo Servicio", "Fecha", "Hora", "Descripción", "Precio", "Estado"
        });
        habilitarBusquedaMascota(false);
        habilitarEdicion(false);
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
    
    public void mostrarServicioEnCampos(Servicio servicio){
        if(servicio != null){
            txtTipoServicio.setText(servicio.getTipoServicio());
            txtFecha.setText(servicio.getFecha());
            txtHora.setText(servicio.getHora());
            txtDescripcion.setText(servicio.getDescripcion());
            txtPrecio.setText(String.valueOf(servicio.getPrecio()));
            comboEstado.setSelectedItem(servicio.getEstado());
            habilitarEdicion(true);          
        }
    }
    
    public void habilitarBusquedaMascota(boolean habilitar){
        comboMascotas.setEnabled(habilitar);
        btnBuscarMascota.setEnabled(habilitar);
    }
    
    public void habilitarTabla(boolean habilitar){
        tblServicios.setEnabled(habilitar);
    }
    
    public void habilitarEdicion(boolean habilitar){
        txtTipoServicio.setEnabled(habilitar);
        txtFecha.setEnabled(habilitar);
        txtHora.setEnabled(habilitar);
        txtDescripcion.setEnabled(habilitar);
        txtPrecio.setEnabled(habilitar);
        comboEstado.setEnabled(habilitar);
        btnGuardar.setEnabled(habilitar);
    }
    
    public void limpiarCampos(){
        txtTipoServicio.setText("");
        txtFecha.setText("");
        txtHora.setText("");
        txtDescripcion.setText("");
        txtPrecio.setText("");
        comboEstado.setSelectedIndex(0);
        modeloTabla.setRowCount(0);
        habilitarEdicion(false);
    }
    
    private int indiceServicioSeleccionado = -1;
    
    public void setIndiceServicioSeleccionado(int indice) {
        this.indiceServicioSeleccionado = indice;
    }
    
    public int getIndiceServicioSeleccionado() {
        return indiceServicioSeleccionado;
    }
    
//==============================================================================
    
    public JTextField getTxtRutCliente() {return txtRutCliente;}
    public JComboBox<String> getComboMascotas() {return comboMascotas;}
    public JTable getTblServicios() {return tblServicios;}
    public JTextField getTxtTipoServicio() {return txtTipoServicio;}
    public JTextField getTxtFecha() {return txtFecha;}
    public JTextField getTxtHora() {return txtHora;}
    public JTextField getTxtDescripcion() {return txtDescripcion;}
    public JTextField getTxtPrecio() {return txtPrecio;}
    public JComboBox<String> getComboEstado() {return comboEstado;}
    public JButton getBtnBuscarCliente() {return btnBuscarCliente;}
    public JButton getBtnBuscarMascota() {return btnBuscarMascota;}
    public JButton getBtnGuardar() {return btnGuardar;}
    public JButton getBtnCancelar() {return btnCancelar;}

//======================  AUTOMATICOS  =========================================
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtRutCliente = new javax.swing.JTextField();
        comboMascotas = new javax.swing.JComboBox<>();
        btnBuscarCliente = new javax.swing.JButton();
        btnBuscarMascota = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblServicios = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        txtTipoServicio = new javax.swing.JTextField();
        txtFecha = new javax.swing.JTextField();
        txtDescripcion = new javax.swing.JTextField();
        txtPrecio = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        comboEstado = new javax.swing.JComboBox<>();
        txtHora = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("RUT Cliente:");

        jLabel2.setText("Mascota:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("EDITAR SERVICIO");

        txtRutCliente.setText("jTextField1");

        comboMascotas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnBuscarCliente.setText("Buscar Cliente");

        btnBuscarMascota.setText("Buscar Mascota");

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
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblServicios.setPreferredSize(new java.awt.Dimension(600, 150));
        jScrollPane1.setViewportView(tblServicios);

        jLabel4.setText("Tipo Servicio:");

        jLabel5.setText("Fecha");

        jLabel6.setText("Descripción:");

        jLabel7.setText("Precio:");

        btnGuardar.setBackground(new java.awt.Color(204, 204, 255));
        btnGuardar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.setMaximumSize(new java.awt.Dimension(100, 20));
        btnGuardar.setMinimumSize(new java.awt.Dimension(100, 20));
        btnGuardar.setPreferredSize(new java.awt.Dimension(100, 20));

        btnCancelar.setBackground(new java.awt.Color(204, 204, 255));
        btnCancelar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.setMaximumSize(new java.awt.Dimension(100, 20));
        btnCancelar.setMinimumSize(new java.awt.Dimension(100, 20));
        btnCancelar.setPreferredSize(new java.awt.Dimension(100, 20));

        txtTipoServicio.setMaximumSize(new java.awt.Dimension(100, 20));
        txtTipoServicio.setMinimumSize(new java.awt.Dimension(100, 20));
        txtTipoServicio.setPreferredSize(new java.awt.Dimension(100, 20));
        txtTipoServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTipoServicioActionPerformed(evt);
            }
        });

        txtFecha.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtFecha.setMaximumSize(new java.awt.Dimension(100, 20));
        txtFecha.setMinimumSize(new java.awt.Dimension(100, 20));
        txtFecha.setPreferredSize(new java.awt.Dimension(100, 20));

        txtDescripcion.setMaximumSize(new java.awt.Dimension(100, 20));
        txtDescripcion.setMinimumSize(new java.awt.Dimension(100, 20));
        txtDescripcion.setPreferredSize(new java.awt.Dimension(100, 20));

        txtPrecio.setMaximumSize(new java.awt.Dimension(100, 20));
        txtPrecio.setMinimumSize(new java.awt.Dimension(100, 20));
        txtPrecio.setPreferredSize(new java.awt.Dimension(100, 20));

        jLabel8.setText("Hora:");

        jLabel9.setText("Estado:");

        comboEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboEstado.setMaximumSize(new java.awt.Dimension(100, 20));
        comboEstado.setMinimumSize(new java.awt.Dimension(100, 20));
        comboEstado.setPreferredSize(new java.awt.Dimension(100, 20));

        txtHora.setMaximumSize(new java.awt.Dimension(100, 20));
        txtHora.setMinimumSize(new java.awt.Dimension(100, 20));
        txtHora.setPreferredSize(new java.awt.Dimension(100, 20));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(comboMascotas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnBuscarMascota))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(txtRutCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnBuscarCliente))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtTipoServicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(102, 102, 102)
                                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel9)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(comboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 38, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(119, 119, 119)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtRutCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarCliente))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(comboMascotas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarMascota))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtTipoServicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(txtHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(comboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtTipoServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTipoServicioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTipoServicioActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarCliente;
    private javax.swing.JButton btnBuscarMascota;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox<String> comboEstado;
    private javax.swing.JComboBox<String> comboMascotas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblServicios;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtHora;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JTextField txtRutCliente;
    private javax.swing.JTextField txtTipoServicio;
    // End of variables declaration//GEN-END:variables
}

//Rev.24-09
package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import modelo.Servicio;
import java.util.List;

public class VentanaBuscarServicio extends javax.swing.JFrame {

    public VentanaBuscarServicio() {
        initComponents();
        setLocationRelativeTo(null);
        configurarComponentes();
        habilitarBusquedaMascota(false);
        limpiarResultados();
    }
    
    private void configurarComponentes(){
        habilitarBusquedaMascota(false);
        habilitarResultados(false);
    }
    
    public void habilitarBusquedaMascota(boolean habilitar){
        comboMascotas.setEnabled(habilitar);
        btnBuscarMascota.setEnabled(habilitar);
    }
   
    public void habilitarResultados(boolean habilitar){
        tblServicios.setEnabled(habilitar);
    }
    
    public void cargarMascotasDelCliente(java.util.List<String> mascotas){
        comboMascotas.removeAllItems();
        if(mascotas != null && !mascotas.isEmpty()){
            for(String nombreMascota : mascotas){
                comboMascotas.addItem(nombreMascota);
            }
            habilitarBusquedaMascota(true);
        }
        else{
            habilitarBusquedaMascota(false);
            JOptionPane.showMessageDialog(this, "El cliente no tiene mascotas registradas");
        }
    }
    
    public void mostrarServicios(List<Servicio> servicios){
        DefaultTableModel modelo = (DefaultTableModel) tblServicios.getModel();
        modelo.setRowCount(0); //Limpiar la tabla
        for(Servicio servicio : servicios){
            Object[] fila = {
                servicio.getTipoServicio(),
                servicio.getFecha(),
                servicio.getHora(),
                servicio.getDescripcion(),
                servicio.getPrecio(),
                servicio.getEstado(),
            };
            modelo.addRow(fila);
        }

        tblServicios.getColumnModel().getColumn(0).setPreferredWidth(100);
        tblServicios.getColumnModel().getColumn(1).setPreferredWidth(80);
        tblServicios.getColumnModel().getColumn(2).setPreferredWidth(60);
        tblServicios.getColumnModel().getColumn(3).setPreferredWidth(200);
        tblServicios.getColumnModel().getColumn(4).setPreferredWidth(60);
        tblServicios.getColumnModel().getColumn(5).setPreferredWidth(80);
        
        habilitarResultados(true);
    }

    public void limpiarResultados() {
        DefaultTableModel modelo = (DefaultTableModel) tblServicios.getModel();
        modelo.setRowCount(0);
        habilitarResultados(false);
    }

    public javax.swing.JTextField getTxtRutCliente() {return txtRutCliente;}
    public javax.swing.JComboBox<String> getComboMascotas() {return comboMascotas;}
    public javax.swing.JButton getBtnBuscarCliente() {return btnBuscarCliente;}
    public javax.swing.JButton getBtnBuscarMascota() {return btnBuscarMascota;}
    public javax.swing.JButton getBtnCancelar() {return btnCancelar;}
    public javax.swing.JTable getTblServicios() {return tblServicios;}

//============================  CODIGO AUTOMATICO  =============================
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        lblRutCliente = new javax.swing.JLabel();
        txtRutCliente = new javax.swing.JTextField();
        btnBuscarCliente = new javax.swing.JButton();
        lblNombreMascota = new javax.swing.JLabel();
        comboMascotas = new javax.swing.JComboBox<>();
        btnBuscarMascota = new javax.swing.JButton();
        scrollServicios = new javax.swing.JScrollPane();
        tblServicios = new javax.swing.JTable();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Buscar Servicios");
        setPreferredSize(new java.awt.Dimension(750, 500));
        setResizable(false);

        lblTitulo.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        lblTitulo.setText("BUSCAR SERVICIOS");

        lblRutCliente.setText("RUT Cliente");

        txtRutCliente.setMaximumSize(new java.awt.Dimension(120, 20));
        txtRutCliente.setMinimumSize(new java.awt.Dimension(120, 20));
        txtRutCliente.setPreferredSize(new java.awt.Dimension(120, 20));

        btnBuscarCliente.setText("Buscar Cliente");

        lblNombreMascota.setText("Mascota");

        comboMascotas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboMascotas.setEnabled(false);
        comboMascotas.setMinimumSize(new java.awt.Dimension(120, 25));
        comboMascotas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboMascotasActionPerformed(evt);
            }
        });

        btnBuscarMascota.setText("Buscar Mascota");
        btnBuscarMascota.setEnabled(false);
        btnBuscarMascota.setPreferredSize(new java.awt.Dimension(105, 22));

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
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scrollServicios.setViewportView(tblServicios);

        btnCancelar.setText("Cancelar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblRutCliente)
                                .addGap(18, 18, 18)
                                .addComponent(txtRutCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnBuscarCliente))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(182, 182, 182)
                                .addComponent(lblTitulo))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblNombreMascota)
                                .addGap(18, 18, 18)
                                .addComponent(comboMascotas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnBuscarMascota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 188, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(scrollServicios))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnCancelar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo)
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRutCliente)
                    .addComponent(txtRutCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarCliente))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombreMascota)
                    .addComponent(comboMascotas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarMascota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(scrollServicios, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCancelar)
                .addContainerGap(47, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void comboMascotasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboMascotasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboMascotasActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarCliente;
    private javax.swing.JButton btnBuscarMascota;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JComboBox<String> comboMascotas;
    private javax.swing.JLabel lblNombreMascota;
    private javax.swing.JLabel lblRutCliente;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JScrollPane scrollServicios;
    private javax.swing.JTable tblServicios;
    private javax.swing.JTextField txtRutCliente;
    // End of variables declaration//GEN-END:variables
}

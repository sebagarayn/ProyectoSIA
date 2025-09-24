//Rev-24-09
package vista;

public class VentanaEditarMascota extends javax.swing.JFrame {

    public VentanaEditarMascota() {
        initComponents();
        setLocationRelativeTo(null); // Centrar la ventana
        habilitarEdicion(false); // Deshabilitar campos inicialmente
    }

    public void habilitarEdicion(boolean habilitar) {
        txtTipo.setEnabled(habilitar);
        txtRaza.setEnabled(habilitar);
        txtEdad.setEnabled(habilitar);
        btnGuardar.setEnabled(habilitar);
    }

    // Método para limpiar campos (excepto RUT)
    public void limpiarCampos() {
        txtNombreMascota.setText("");
        txtTipo.setText("");
        txtRaza.setText("");
        txtEdad.setText("");
        habilitarEdicion(false);
    }
   
    public javax.swing.JButton getBtnBuscar() { return btnBuscar; }
    public javax.swing.JButton getBtnGuardar() { return btnGuardar; }
    public javax.swing.JButton getBtnCancelar() { return btnCancelar; }
    public javax.swing.JTextField getTxtRutDueno() { return txtRutDueno; }
    public javax.swing.JTextField getTxtNombreMascota() { return txtNombreMascota; }
    public javax.swing.JTextField getTxtTipo() { return txtTipo; }
    public javax.swing.JTextField getTxtRaza() { return txtRaza; }  
    public javax.swing.JTextField getTxtEdad() { return txtEdad; }  
    
//==============================================================================
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtRutDueno = new javax.swing.JTextField();
        txtNombreMascota = new javax.swing.JTextField();
        txtTipo = new javax.swing.JTextField();
        txtRaza = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtEdad = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Editar Mascota");

        jLabel3.setText("Tipo");

        jLabel4.setText("Raza");

        txtRutDueno.setText("jTextField1");
        txtRutDueno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRutDuenoActionPerformed(evt);
            }
        });

        txtNombreMascota.setText("jTextField1");
        txtNombreMascota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreMascotaActionPerformed(evt);
            }
        });

        txtTipo.setText("jTextField1");
        txtTipo.setEnabled(false);
        txtTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTipoActionPerformed(evt);
            }
        });

        txtRaza.setText("jTextField1");
        txtRaza.setEnabled(false);
        txtRaza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRazaActionPerformed(evt);
            }
        });

        btnBuscar.setText("Buscar");

        btnGuardar.setText("Guardar");

        btnCancelar.setText("Cancelar");

        jLabel1.setText("RUT");

        jLabel2.setText("Nombre Mascota");

        jLabel5.setText("Edad");

        txtEdad.setText("jTextField1");
        txtEdad.setEnabled(false);
        txtEdad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEdadActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(btnGuardar)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancelar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(txtTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel5)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtEdad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtRaza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(txtRutDueno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(79, 79, 79)
                                .addComponent(btnBuscar))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(txtNombreMascota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(121, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(47, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtRutDueno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNombreMascota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtRaza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtEdad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnCancelar))
                .addGap(27, 27, 27))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtRutDuenoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRutDuenoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRutDuenoActionPerformed

    private void txtNombreMascotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreMascotaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreMascotaActionPerformed

    private void txtTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTipoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTipoActionPerformed

    private void txtRazaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRazaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRazaActionPerformed

    private void txtEdadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEdadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEdadActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField txtEdad;
    private javax.swing.JTextField txtNombreMascota;
    private javax.swing.JTextField txtRaza;
    private javax.swing.JTextField txtRutDueno;
    private javax.swing.JTextField txtTipo;
    // End of variables declaration//GEN-END:variables
}

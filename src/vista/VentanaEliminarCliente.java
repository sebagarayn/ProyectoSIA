//Rev.24-09
package vista;

public class VentanaEliminarCliente extends javax.swing.JFrame {

    public VentanaEliminarCliente() {
        initComponents();
        setLocationRelativeTo(null); 
        habilitarEliminacion(false);
        
        txtNombre.setEditable(false);
        txtTelefono.setEditable(false);
        txtDireccion.setEditable(false);
        txtNumMascotas.setEditable(false);
    }
    
    public void habilitarEliminacion(boolean habilitar) {
        btnEliminar.setEnabled(habilitar);
    }

    // Método para limpiar campos (excepto RUT)
    public void limpiarCampos() {
        txtNombre.setText("");
        txtTelefono.setText("");
        txtDireccion.setText("");
        txtNumMascotas.setText("");
        habilitarEliminacion(false);
    }

    // Getters para los componentes (necesarios para el controlador)
    public javax.swing.JButton getBtnBuscar() { return btnBuscar; }
    public javax.swing.JButton getBtnEliminar() { return btnEliminar; }
    public javax.swing.JButton getBtnCancelar() { return btnCancelar; }
    public javax.swing.JTextField getTxtRut() { return txtRut; }
    public javax.swing.JTextField getTxtNombre() { return txtNombre; }
    public javax.swing.JTextField getTxtTelefono() { return txtTelefono; }
    public javax.swing.JTextField getTxtDireccion() { return txtDireccion; }
    public javax.swing.JTextField getTxtNumMascotas() { return txtNumMascotas; }
//==============================================================================
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtRut = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        txtNumMascotas = new javax.swing.JTextField();
        txtDireccion = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Eliminar Cliente");
        setResizable(false);

        jLabel1.setText("RUT");

        jLabel2.setText("Nombre");

        jLabel3.setText("Teléfono");

        jLabel4.setText("Dirección");

        jLabel5.setText("Mascotas");

        txtRut.setText("jTextField1");

        txtNombre.setEditable(false);
        txtNombre.setText("jTextField1");

        txtTelefono.setEditable(false);
        txtTelefono.setText("jTextField1");

        txtNumMascotas.setEditable(false);
        txtNumMascotas.setText("jTextField1");

        txtDireccion.setEditable(false);
        txtDireccion.setText("jTextField1");

        btnBuscar.setText("Buscar");

        btnCancelar.setText("Cancelar");

        btnEliminar.setText("Eliminar");
        btnEliminar.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(txtNumMascotas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnEliminar)
                                .addGap(18, 18, 18)
                                .addComponent(btnCancelar))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(txtRut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 121, Short.MAX_VALUE)
                                .addComponent(btnBuscar)))
                        .addGap(56, 56, 56))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtRut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtNumMascotas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnEliminar))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNumMascotas;
    private javax.swing.JTextField txtRut;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}

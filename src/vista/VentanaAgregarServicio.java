//Rev.24-09
package vista;

import modelo.Cliente;
import modelo.Mascota;
import javax.swing.*;

public class VentanaAgregarServicio extends javax.swing.JFrame {

    public VentanaAgregarServicio() {
        initComponents();
        setLocationRelativeTo(null);
        configurarComponentes();
    }
    
    private void configurarComponentes(){
        comboEstado.removeAllItems();
        comboEstado.addItem("Pendiente");
        comboEstado.addItem("Realizado");
        
        comboNivelUrgencia.removeAllItems();
        comboNivelUrgencia.addItem("1");
        comboNivelUrgencia.addItem("2");
        comboNivelUrgencia.addItem("3");
        comboNivelUrgencia.addItem("4");
        comboNivelUrgencia.addItem("5");
        
        habilitarCamposServicio(false);
        habilitarCamposUrgencia(false);
        comboMascotas.setEnabled(false);  
        
        //Ojo aca, revisar esto
        chkEsUrgencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkEsUrgenciaActionPerformed(evt);
            }
        });     
    }
    
    //Este igual revisar despues
    private void chkEsUrgenciaActionPerformed(java.awt.event.ActionEvent evt) {
        habilitarCamposUrgencia(chkEsUrgencia.isSelected());
    }
    
    public void cargarMascotasDelCliente(Cliente cliente){
        comboMascotas.removeAllItems();
        if(cliente != null && !cliente.getMascotas().isEmpty()){
            for(Mascota mascota : cliente.getMascotas()){
                comboMascotas.addItem(mascota.getNombre());
            }
            habilitarCamposServicio(true);
            habilitarCamposUrgencia(chkEsUrgencia.isSelected());
        }
        else{
            habilitarCamposServicio(false);
            comboMascotas.setEnabled(false);
            if(cliente != null){
                JOptionPane.showMessageDialog(this, "El cliente no tiene mascotas registradas");
            }
        }
    }
    
    public void limpiarMascotas() {
        comboMascotas.removeAllItems();
        comboMascotas.setEnabled(false);  
    }
    
    public void habilitarCamposServicio(boolean habilitar){
        comboMascotas.setEnabled(habilitar);
        txtTipoServicio.setEnabled(habilitar);
        txtFecha.setEnabled(habilitar);
        txtHora.setEnabled(habilitar);
        txtDescripcion.setEnabled(habilitar);
        txtPrecio.setEnabled(habilitar);
        comboEstado.setEnabled(habilitar);
        chkEsUrgencia.setEnabled(habilitar);
        btnAgregar.setEnabled(habilitar);
    }
    
    public void habilitarCamposUrgencia(boolean habilitar){
        comboNivelUrgencia.setEnabled(habilitar);
        txtMotivoUrgencia.setEnabled(habilitar);
        chkAtencionInmediata.setEnabled(habilitar);
    }
    
    public void limpiarCamposServicio(){
        comboMascotas.removeAllItems();
        txtTipoServicio.setText("");
        txtFecha.setText("");
        txtHora.setText("");
        txtDescripcion.setText("");
        txtPrecio.setText("");
        comboEstado.setSelectedIndex(0); //Se vuelve a "Pendiente"
        comboNivelUrgencia.setSelectedIndex(0);
        chkAtencionInmediata.setSelected(false);
        habilitarCamposUrgencia(false);
    }
    
    public javax.swing.JTextField getTxtRutDueno() {return txtRutDueno;}
    public JComboBox<String> getComboMascotas() {return comboMascotas;}
    public javax.swing.JTextField getTxtTipoServicio() {return txtTipoServicio;}
    public javax.swing.JTextField getTxtFecha() {return txtFecha;}
    public javax.swing.JTextField getTxtHora() {return txtHora;}    
    public javax.swing.JTextField getTxtDescripcion() {return txtDescripcion;} 
    public javax.swing.JTextField getTxtPrecio() {return txtPrecio;}    
    public JComboBox<String> getComboEstado() {return comboEstado;}  
    public javax.swing.JButton getBtnBuscar() {return btnBuscar;}
    public javax.swing.JButton getBtnAgregar() {return btnAgregar;}
    public javax.swing.JButton getBtnCancelar() {return btnCancelar;}   
    public JCheckBox getChkEsUrgencia() {return chkEsUrgencia;}
    public JComboBox<String> getComboNivelUrgencia() {return comboNivelUrgencia;}
    public JTextField getTxtMotivoUrgencia() {return txtMotivoUrgencia;}
    public JCheckBox getChkAtencionInmediata() {return chkAtencionInmediata;}
    

//=========================  CODIGO AUTOMATICO  ================================
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtRutDueno = new javax.swing.JTextField();
        txtTipoServicio = new javax.swing.JTextField();
        txtFecha = new javax.swing.JTextField();
        btnAgregar = new javax.swing.JButton();
        txtDescripcion = new javax.swing.JTextField();
        btnCancelar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtHora = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        comboEstado = new javax.swing.JComboBox<>();
        comboMascotas = new javax.swing.JComboBox<>();
        comboNivelUrgencia = new javax.swing.JComboBox<>();
        chkEsUrgencia = new javax.swing.JCheckBox();
        txtMotivoUrgencia = new javax.swing.JTextField();
        chkAtencionInmediata = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtRutDueno.setText("jTextField1");

        txtTipoServicio.setText("jTextField3");

        txtFecha.setText("jTextField4");

        btnAgregar.setText("Agregar");

        txtDescripcion.setText("jTextField5");

        btnCancelar.setText("Cancelar");

        jLabel1.setText("Rut del dueño");

        jLabel2.setText("Nombre de la mascota");

        jLabel3.setText("Tipo de servicio");

        jLabel4.setText("Fecha");

        jLabel5.setText("Descripción");

        jLabel6.setText("Precio");

        jLabel7.setText("Estado");

        txtPrecio.setText("jTextField5");

        jLabel8.setText("Hora");

        txtHora.setText("jTextField1");

        btnBuscar.setText("Buscar Cliente");

        comboEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboEstado.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboEstadoItemStateChanged(evt);
            }
        });

        comboMascotas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboMascotas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboMascotasActionPerformed(evt);
            }
        });

        comboNivelUrgencia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        chkEsUrgencia.setText("Es Urgencia");

        txtMotivoUrgencia.setText("Motivo Urgencia");

        chkAtencionInmediata.setText("Atencion Inmediata");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel7)
                                    .addGap(18, 18, 18)
                                    .addComponent(comboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(btnAgregar))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnCancelar)
                                .addGap(53, 53, 53)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtMotivoUrgencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(chkAtencionInmediata))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(chkEsUrgencia)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(comboNivelUrgencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(txtRutDueno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnBuscar)
                                .addGap(29, 29, 29))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(18, 18, 18)
                                        .addComponent(comboMascotas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtTipoServicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel4)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel8)
                                        .addGap(12, 12, 12)
                                        .addComponent(txtHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBuscar)
                    .addComponent(jLabel1)
                    .addComponent(txtRutDueno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(comboMascotas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtTipoServicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(txtHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(comboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(chkEsUrgencia)
                            .addComponent(comboNivelUrgencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregar)
                    .addComponent(txtMotivoUrgencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkAtencionInmediata))
                .addGap(5, 5, 5)
                .addComponent(btnCancelar)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void comboEstadoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboEstadoItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_comboEstadoItemStateChanged

    private void comboMascotasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboMascotasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboMascotasActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JCheckBox chkAtencionInmediata;
    private javax.swing.JCheckBox chkEsUrgencia;
    private javax.swing.JComboBox<String> comboEstado;
    private javax.swing.JComboBox<String> comboMascotas;
    private javax.swing.JComboBox<String> comboNivelUrgencia;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtHora;
    private javax.swing.JTextField txtMotivoUrgencia;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JTextField txtRutDueno;
    private javax.swing.JTextField txtTipoServicio;
    // End of variables declaration//GEN-END:variables
}

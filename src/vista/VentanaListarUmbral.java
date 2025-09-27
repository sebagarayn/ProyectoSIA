package vista;

import exception.UmbralNegativoException;
import exception.ListaClientesVaciaException;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.*;
import exception.*;


public class VentanaListarUmbral extends javax.swing.JFrame {
    private javax.swing.table.DefaultTableModel modeloTabla;
    private Veterinaria veterinaria;
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(VentanaListarUmbral.class.getName());
    

    
    public VentanaListarUmbral(Veterinaria vet) {
        this.veterinaria = vet;
        initComponents();
        inicializarTabla();
        btnBuscar.addActionListener(e -> cargarDatos());
    }
    private void inicializarTabla() {
        String[] columnas = {"Cliente", "Mascota", "Servicio", "Costo"};
        modeloTabla = new javax.swing.table.DefaultTableModel(columnas, 0);
        jTable1.setModel(modeloTabla);
    }
    private void cargarDatos() {
        try {
            double umbral = Double.parseDouble(txtUmbral.getText().trim());
            modeloTabla.setRowCount(0); // limpiar tabla
            List<Servicio> listaServicios = veterinaria.serviciosConCostoMayorA((int)umbral);
            for (Cliente c : veterinaria.getClientes()) {
                for (Mascota m : c.getMascotas()) {
                    for (Servicio s : m.getServicios()) {
                        if (listaServicios.contains(s)) {
                            Object[] fila = {
                                c.getNombre(),
                                m.getNombre(),
                                s.getTipoServicio(),
                                s.getPrecio()
                            };
                            modeloTabla.addRow(fila);
                        }
                    }
                }
            }
            
        } 
        catch (NumberFormatException ex) {
            javax.swing.JOptionPane.showMessageDialog(
                this,
                "Ingrese un número válido para el umbral",
                "Error",
                javax.swing.JOptionPane.ERROR_MESSAGE
            );
        }catch (ListaClientesVaciaException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Advertencia", JOptionPane.WARNING_MESSAGE);   
        }catch (UmbralNegativoException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }      
    }
    public VentanaListarUmbral() {
        
        this(new Veterinaria()); // inicializa con veterinaria vacía

    }

//==============================================================================
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtUmbral = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Umbral:");

        txtUmbral.setText("jTextField1");

        btnBuscar.setText("Buscar");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Cliente", "Mascota", "Servicio", "Costo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtUmbral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(btnBuscar)
                .addContainerGap(91, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtUmbral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar))
                .addGap(19, 19, 19))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtUmbral;
    // End of variables declaration//GEN-END:variables
}

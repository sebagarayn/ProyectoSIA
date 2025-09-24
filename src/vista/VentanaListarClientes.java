//Rev.24-09
package vista;

import modelo.Veterinaria;
import modelo.Cliente;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class VentanaListarClientes extends javax.swing.JFrame {
    private Veterinaria veterinaria; // Agregar este atributo

    // Constructor modificado para recibir Veterinaria
    public VentanaListarClientes(Veterinaria veterinaria) {
        this.veterinaria = veterinaria;
        initComponents();
        cargarDatos(); // Llamar al método después de inicializar componentes
        setLocationRelativeTo(null); // Centrar la ventana
    }

    // Método para cargar los datos en la tabla
    private void cargarDatos() {
        DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
        modelo.setRowCount(0); // Limpiar la tabla
        
        List<Cliente> clientes = veterinaria.getClientes();
        for (Cliente cliente : clientes) {
            Object[] fila = {
                cliente.getNombre(),
                cliente.getRut(),
                cliente.getTelefono(),
                cliente.getDireccion(),
                cliente.getMascotas().size()
            };
            modelo.addRow(fila);
        }
        
        // Ajustar el ancho de las columnas
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(150);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(100);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(100);
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(200);
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(80);
    }
    
//========================AUTOMATICO============================================
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Lista de Clientes");
        setPreferredSize(new java.awt.Dimension(800, 400));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Nombre", "RUT", "Teléfono", "Dirección", "Mascotas"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Byte.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}

//Rev.24-09
package vista;

import modelo.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class VentanaListarMascotas extends javax.swing.JFrame {
    private Veterinaria veterinaria;
    
    public VentanaListarMascotas(Veterinaria veterinaria) {
        this.veterinaria = veterinaria;
        initComponents();
        cargarDatos();
        setLocationRelativeTo(null);
    }
    
    private void cargarDatos() {
        DefaultTableModel modelo = (DefaultTableModel) tblMascotas.getModel();
        modelo.setRowCount(0); // Limpiar la tabla
        
        List<Cliente> clientes = veterinaria.getClientes();
        for (Cliente cliente : clientes) {
            for (Mascota mascota : cliente.getMascotas()) {
                String tipoMascota = "Normal";
                if(mascota instanceof MascotaGeriatrica){
                    tipoMascota = "Geriatrica";
                }
                Object[] fila = {
                    mascota.getNombre(),
                    mascota.getTipo(),
                    mascota.getRaza(),
                    mascota.getEdad(),
                    cliente.getNombre() + " (" + cliente.getRut() + ")",
                    tipoMascota
                };
                modelo.addRow(fila);
            }
        }
        
        // Ajustar el ancho de las columnas
        tblMascotas.getColumnModel().getColumn(0).setPreferredWidth(120);
        tblMascotas.getColumnModel().getColumn(1).setPreferredWidth(80);
        tblMascotas.getColumnModel().getColumn(2).setPreferredWidth(100);
        tblMascotas.getColumnModel().getColumn(3).setPreferredWidth(50);
        tblMascotas.getColumnModel().getColumn(4).setPreferredWidth(200);
        tblMascotas.getColumnModel().getColumn(5).setPreferredWidth(80);
    }


//==============================================================================
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollMascotas = new javax.swing.JScrollPane();
        tblMascotas = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Lista de Mascotas");
        setResizable(false);

        tblMascotas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Nombre", "Tipo", "Raza", "Edad", "Dueño", "Tipo Mascota"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblMascotas.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        scrollMascotas.setViewportView(tblMascotas);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollMascotas, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollMascotas, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane scrollMascotas;
    private javax.swing.JTable tblMascotas;
    // End of variables declaration//GEN-END:variables
}

//Rev.24-09
package vista;

import modelo.Veterinaria;
import modelo.Cliente;
import modelo.Mascota;
import modelo.Servicio;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class VentanaListarServicios extends javax.swing.JFrame {
    private Veterinaria veterinaria;

    public VentanaListarServicios(Veterinaria veterinaria) {
        this.veterinaria = veterinaria;
        initComponents();
        cargarDatos();
        setLocationRelativeTo(null);
    }
    
    private void cargarDatos(){
        DefaultTableModel modelo = (DefaultTableModel)tblServicios.getModel();
        modelo.setRowCount(0);
        
        List<Cliente> clientes = veterinaria.getClientes();
        for(Cliente cliente : clientes){
            for(Mascota mascota : cliente.getMascotas()){
                for(Servicio servicio : mascota.getServicios()){
                    Object[] fila = {
                        servicio.getTipoServicio(),
                        servicio.getFecha(),
                        servicio.getHora(),
                        servicio.getDescripcion(),
                        servicio.getPrecio(),
                        servicio.getEstado(),
                        mascota.getNombre(),
                        cliente.getNombre() + " (" + cliente.getRut() + ")"
                    };
                    modelo.addRow(fila);
                }
            }
        }
    
        tblServicios.getColumnModel().getColumn(0).setPreferredWidth(120);
        tblServicios.getColumnModel().getColumn(1).setPreferredWidth(80);
        tblServicios.getColumnModel().getColumn(2).setPreferredWidth(60); 
        tblServicios.getColumnModel().getColumn(3).setPreferredWidth(200);
        tblServicios.getColumnModel().getColumn(4).setPreferredWidth(70);
        tblServicios.getColumnModel().getColumn(5).setPreferredWidth(80);
        tblServicios.getColumnModel().getColumn(6).setPreferredWidth(100);
        tblServicios.getColumnModel().getColumn(7).setPreferredWidth(180);
}

//=============================AUTOMATICO===========================    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollServicios = new javax.swing.JScrollPane();
        tblServicios = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Lista de Servicios");
        setPreferredSize(new java.awt.Dimension(900, 500));
        setResizable(false);

        tblServicios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Tipo Servicio", "Fecha", "Hora", "Descripción", "Precio", "Estado", "Mascota", "Cliente"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblServicios.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        scrollServicios.setViewportView(tblServicios);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollServicios, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollServicios, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane scrollServicios;
    private javax.swing.JTable tblServicios;
    // End of variables declaration//GEN-END:variables
}

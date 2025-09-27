package vista;

import modelo.Veterinaria;
import modelo.ClienteFrecuente;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import javax.swing.JOptionPane;

public class VentanaListarClientesFrecuentes extends javax.swing.JFrame {
    private Veterinaria veterinaria;
    
    public VentanaListarClientesFrecuentes(Veterinaria veterinaria) {
        this.veterinaria = veterinaria;
        initComponents();
        cargarDatos();
        setLocationRelativeTo(null);
    }
    
    public void cargarDatos() {
        DefaultTableModel modelo = (DefaultTableModel)tblListaClientesFrecuentes.getModel();
        modelo.setRowCount(0);
        List<ClienteFrecuente> clientesFrecuentes = veterinaria.obtenerClientesFrecuentes();
        if (clientesFrecuentes.isEmpty()) {
            Object[] fila = {"No hay clientes frecuentes", "", "", "", "", "", ""};
            modelo.addRow(fila);
        }
        else{
            for (ClienteFrecuente cliente : clientesFrecuentes) {
                Object[] fila = {
                    cliente.getNombre(),
                    cliente.getRut(),
                    cliente.getTelefono(),
                    cliente.getNumeroVisitasAnuales(),
                    (int)(cliente.calcularDescuento() * 100) + "%",
                    cliente.isTienePlanFidelidad() ? "Activo" : "Inactivo",
                    cliente.getFechaUltimaVisita()
                };
                modelo.addRow(fila);
            }
        }
        if (!clientesFrecuentes.isEmpty()) {
            tblListaClientesFrecuentes.getColumnModel().getColumn(0).setPreferredWidth(150);
            tblListaClientesFrecuentes.getColumnModel().getColumn(1).setPreferredWidth(100);
            tblListaClientesFrecuentes.getColumnModel().getColumn(2).setPreferredWidth(100);
            tblListaClientesFrecuentes.getColumnModel().getColumn(3).setPreferredWidth(80);
            tblListaClientesFrecuentes.getColumnModel().getColumn(4).setPreferredWidth(80);
            tblListaClientesFrecuentes.getColumnModel().getColumn(5).setPreferredWidth(80);
            tblListaClientesFrecuentes.getColumnModel().getColumn(6).setPreferredWidth(100);
        }
    }
    
    public javax.swing.JButton getBtnActualizar() {return btnActualizar;}
    public javax.swing.JButton getBtnCerrar() {return btnCerrar;}
    
//==============================================================================
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        scrollClientesFrecuentes = new javax.swing.JScrollPane();
        tblListaClientesFrecuentes = new javax.swing.JTable();
        lblInfo = new javax.swing.JLabel();
        btnCerrar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Listar Clientes Frecuentes");
        setPreferredSize(new java.awt.Dimension(800, 500));

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("CLIENTES FRECUENTES (ACTIVOS)");

        tblListaClientesFrecuentes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Nombre", "RUT", "Télefono", "Visitas Anuales", "Descuento", "Plan Fidelidad", "Última Visita"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblListaClientesFrecuentes.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        scrollClientesFrecuentes.setViewportView(tblListaClientesFrecuentes);

        lblInfo.setText("Promoción automática: 7 servicios -> Cliente Frecuente");

        btnCerrar.setText("Cerrar");

        btnActualizar.setText("Actualizar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollClientesFrecuentes)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(lblTitulo))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnActualizar)
                                .addGap(156, 156, 156)
                                .addComponent(btnCerrar))
                            .addComponent(lblInfo))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollClientesFrecuentes, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblInfo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCerrar)
                    .addComponent(btnActualizar))
                .addGap(19, 19, 19))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JLabel lblInfo;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JScrollPane scrollClientesFrecuentes;
    private javax.swing.JTable tblListaClientesFrecuentes;
    // End of variables declaration//GEN-END:variables

}

package vista;

import modelo.Veterinaria;
import modelo.Servicio;
import modelo.Cliente;
import modelo.Mascota;
import modelo.ClienteFrecuente;
import modelo.MascotaGeriatrica;
import modelo.ServicioUrgencia;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;

public class VentanaAnalisisServicios extends javax.swing.JFrame {
    private List<Servicio> serviciosMostrados;
    
    public VentanaAnalisisServicios() {
        initComponents();
        serviciosMostrados = new ArrayList<>();
        configurarTabla();
    }
    
    private void configurarTabla() {
        String[] columnas = {
            "Cliente", "Tipo Cliente", "Mascota", "Tipo Mascota", 
            "Servicio", "Tipo Servicio", "Precio", "Fecha", "Estado"
        };
        
        DefaultTableModel model = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblAnalisisServicios.setModel(model);
    }

    // MÉTODOS PARA EL CONTROLADOR
    public double getPrecioMinimo() {
        try {
            return Double.parseDouble(txtPrecioMinimo.getText());
        } catch (NumberFormatException e) {
            return 0;
        }
    }
    
    public double getPrecioMaximo() {
        try {
            return Double.parseDouble(txtPrecioMaximo.getText());
        } catch (NumberFormatException e) {
            return Double.MAX_VALUE;
        }
    }
    
    public boolean isSoloClientesFrecuentes() {
        return chkClientesFrecuentes.isSelected();
    }
    
    public boolean isSoloMascotasGeriatricas() {
        return chkMascotasGeriatricas.isSelected();
    }
    
    public boolean isSoloServiciosUrgencia() {
        return chkServiciosUrgencia.isSelected();
    }
    
    public List<Servicio> getServiciosMostrados() {
        return new ArrayList<>(serviciosMostrados);
    }
    
    public void mostrarResultados(List<Servicio> servicios, Veterinaria veterinaria) {
        this.serviciosMostrados = new ArrayList<>(servicios);
        DefaultTableModel model = (DefaultTableModel) tblAnalisisServicios.getModel();
        model.setRowCount(0);
        
        for (Servicio servicio : servicios) {
            Object[] fila = obtenerFilaServicio(servicio, veterinaria);
            if (fila != null) {
                model.addRow(fila);
            }
        }
        
        if (servicios.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No se encontraron servicios", "Resultados", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private Object[] obtenerFilaServicio(Servicio servicio, Veterinaria veterinaria) {
        try {
            Cliente cliente = null;
            Mascota mascota = null;
            
            // Buscar cliente y mascota del servicio
            for (Cliente c : veterinaria.getClientes()) {
                for (Mascota m : c.getMascotas()) {
                    if (m.getServicios().contains(servicio)) {
                        cliente = c;
                        mascota = m;
                        break;
                    }
                }
                if (cliente != null) break;
            }
            
            if (cliente == null || mascota == null) return null;
            
            return new Object[]{
                cliente.getNombre(),
                cliente instanceof ClienteFrecuente ? "Frecuente" : "Regular",
                mascota.getNombre(),
                mascota instanceof MascotaGeriatrica ? "Geriatrica" : "Normal",
                servicio.getTipoServicio(),
                servicio instanceof ServicioUrgencia ? "Urgencia" : "Normal",
                "$" + servicio.getPrecio(),
                servicio.getFecha(),
                servicio.getEstado()
            };
            
        } catch (Exception e) {
            return null;
        }
    }
    
    public void mostrarEstadisticas(String estadisticas) {
        JOptionPane.showMessageDialog(this, estadisticas, "Estadísticas Detalladas", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    public void limpiarCampos() {
        txtPrecioMinimo.setText("0");
        txtPrecioMaximo.setText("999999");
        chkClientesFrecuentes.setSelected(false);
        chkMascotasGeriatricas.setSelected(false);
        chkServiciosUrgencia.setSelected(false);
        
        DefaultTableModel model = (DefaultTableModel) tblAnalisisServicios.getModel();
        model.setRowCount(0);
        serviciosMostrados.clear();
    }
    
    // LISTENERS para el Controlador
    public void setBuscarListener(ActionListener listener) {
        btnBuscar.addActionListener(listener);
    }
    
    public void setLimpiarListener(ActionListener listener) {
        btnLimpiar.addActionListener(listener);
    }
    
    public void setEstadisticasListener(ActionListener listener) {
        btnEstadisticas.addActionListener(listener);
    }
    
    public void setCerrarListener(java.awt.event.ActionListener listener) {
    btnCerrar.addActionListener(listener);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtPrecioMinimo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtPrecioMaximo = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        chkClientesFrecuentes = new javax.swing.JCheckBox();
        chkMascotasGeriatricas = new javax.swing.JCheckBox();
        chkServiciosUrgencia = new javax.swing.JCheckBox();
        btnEstadisticas = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAnalisisServicios = new javax.swing.JTable();
        btnCerrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Analisis Servicios");

        jLabel1.setText("Precio mínimo:");

        txtPrecioMinimo.setText("0");
        txtPrecioMinimo.setMaximumSize(new java.awt.Dimension(60, 20));
        txtPrecioMinimo.setMinimumSize(new java.awt.Dimension(60, 20));
        txtPrecioMinimo.setPreferredSize(new java.awt.Dimension(60, 20));

        jLabel2.setText("Precio máximo:");

        txtPrecioMaximo.setText("999999");
        txtPrecioMaximo.setMaximumSize(new java.awt.Dimension(60, 20));
        txtPrecioMaximo.setMinimumSize(new java.awt.Dimension(60, 20));
        txtPrecioMaximo.setPreferredSize(new java.awt.Dimension(60, 20));

        btnBuscar.setText("Buscar");

        chkClientesFrecuentes.setText("Solo Clientes Frecuentes");

        chkMascotasGeriatricas.setText("Solo Mascotas Geriatricas");

        chkServiciosUrgencia.setText("Solo Servicios de Urgencia");

        btnEstadisticas.setText("Estadisticas");

        btnLimpiar.setText("Limpiar");

        tblAnalisisServicios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Cliente", "Tipo Cliente", "Mascota", "Tipo Mascota", "Servicio", "Tipo Servicio", "Precio"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblAnalisisServicios);

        btnCerrar.setText("Cerrar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtPrecioMinimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPrecioMaximo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnBuscar))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(chkServiciosUrgencia)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEstadisticas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLimpiar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCerrar))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(chkClientesFrecuentes)
                        .addGap(18, 18, 18)
                        .addComponent(chkMascotasGeriatricas)))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtPrecioMinimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtPrecioMaximo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkClientesFrecuentes)
                    .addComponent(chkMascotasGeriatricas))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkServiciosUrgencia)
                    .addComponent(btnEstadisticas)
                    .addComponent(btnLimpiar)
                    .addComponent(btnCerrar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnEstadisticas;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JCheckBox chkClientesFrecuentes;
    private javax.swing.JCheckBox chkMascotasGeriatricas;
    private javax.swing.JCheckBox chkServiciosUrgencia;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblAnalisisServicios;
    private javax.swing.JTextField txtPrecioMaximo;
    private javax.swing.JTextField txtPrecioMinimo;
    // End of variables declaration//GEN-END:variables
}

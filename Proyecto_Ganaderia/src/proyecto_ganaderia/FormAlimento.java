/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_ganaderia;

import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import modelos.Alimentos;
import modelos.Tipoganaderia;
import modelos.TipoganaderiaJpaController;


/**
 *
 * @author jvifdz-GRANDE
 */
public class FormAlimento extends javax.swing.JDialog {

    /**
     * Creates new form FormAlimento
     */
    
    public Alimentos alimento;
    public boolean creado = false;
    
    public FormAlimento(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        alimento= new Alimentos();
        getCombo();
        this.setResizable(false);
        this.jDateFechaCompra.setDate(new Date());
    }
    
     private void getCombo() {
        List<Tipoganaderia> lista;
        TipoganaderiaJpaController controlador = new TipoganaderiaJpaController();
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        
        try {
            lista = controlador.findTipoganaderiaEntities();
            for (int i = 0; i < lista.size(); i++) {
                modelo.addElement(lista.get(i));

            }

            this.CbTipoGanaderia.setFocusable(false);
            this.CbTipoGanaderia.setModel(modelo);
        } catch (Exception e) {

            JOptionPane.showMessageDialog(this, "Error al cargar los datos: " + e.toString());

        }

    }
    
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        TxNombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        TxPrecio = new javax.swing.JFormattedTextField();
        jLabel4 = new javax.swing.JLabel();
        TxCantidad = new javax.swing.JFormattedTextField();
        jDateFechaCompra = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        BtAceptar = new javax.swing.JButton();
        BtCancelar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        CbTipoGanaderia = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Alimentos");

        jLabel2.setText("Nombre");

        jLabel3.setText("Precio");

        TxPrecio.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter()));

        jLabel4.setText("Cantidad");

        TxCantidad.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("###########"))));

        jLabel5.setText("Fecha compra");

        BtAceptar.setText("Aceptar");
        BtAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtAceptarActionPerformed(evt);
            }
        });

        BtCancelar.setText("Cancelar");
        BtCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtCancelarActionPerformed(evt);
            }
        });

        jLabel6.setText("Ganaderia destinada");

        CbTipoGanaderia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel6))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addComponent(BtAceptar)
                                .addGap(116, 116, 116)
                                .addComponent(BtCancelar))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(TxNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(TxPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(36, 36, 36)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel5))
                                        .addGap(26, 26, 26)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jDateFechaCompra, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                                            .addComponent(TxCantidad)))
                                    .addComponent(CbTipoGanaderia, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(226, 226, 226)
                        .addComponent(jLabel1)))
                .addContainerGap(55, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1)
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(jLabel3))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(6, 6, 6)
                            .addComponent(jLabel2))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(TxCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4)
                                .addComponent(TxNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(30, 30, 30)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jDateFechaCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(TxPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(CbTipoGanaderia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtAceptar)
                    .addComponent(BtCancelar))
                .addGap(22, 22, 22))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtCancelarActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_BtCancelarActionPerformed

    private void BtAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtAceptarActionPerformed
        // TODO add your handling code here:
        
        if (this.TxNombre.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Inserte nombre del alimento", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (this.TxCantidad.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Inserte la cantidad", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (this.TxPrecio.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Inserte el precio", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (this.jDateFechaCompra.getDate()==null) {
            JOptionPane.showMessageDialog(null, "Inserte la fecha de compra", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        Date fechaCompra;
        if (this.jDateFechaCompra.getDate() == null) {
            fechaCompra = new Date();
        } else {
            fechaCompra = jDateFechaCompra.getDate();
        }
        
        
        
        
        String nombre=this.TxNombre.getText();
        int cantidad=Integer.parseInt(this.TxCantidad.getText());
        
        float precio=Float.parseFloat(this.TxPrecio.getText());
        
        
        
        alimento.setNombre(nombre);
        alimento.setPrecio(precio);
        alimento.setCantidad(cantidad);
        alimento.setFechaCompra(fechaCompra);
        alimento.setFkAlimentos((Tipoganaderia) CbTipoGanaderia.getSelectedItem());
        creado=true;
        this.dispose();
        
    }//GEN-LAST:event_BtAceptarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormAlimento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormAlimento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormAlimento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormAlimento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FormAlimento dialog = new FormAlimento(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtAceptar;
    private javax.swing.JButton BtCancelar;
    private javax.swing.JComboBox<String> CbTipoGanaderia;
    private javax.swing.JFormattedTextField TxCantidad;
    private javax.swing.JTextField TxNombre;
    private javax.swing.JFormattedTextField TxPrecio;
    private com.toedter.calendar.JDateChooser jDateFechaCompra;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    // End of variables declaration//GEN-END:variables
}

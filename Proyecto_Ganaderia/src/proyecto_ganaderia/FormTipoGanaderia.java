/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_ganaderia;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelos.Cliente;
import modelos.ClienteJpaController;
import modelos.Tipoganaderia;
import modelos.TipoganaderiaJpaController;
import modelos.exceptions.IllegalOrphanException;
import modelos.exceptions.NonexistentEntityException;

/**
 *
 * @author jvifdz-GRANDE
 */
public class FormTipoGanaderia extends javax.swing.JDialog {

    /**
     * Creates new form FormTipoGanaderia
     */
    
    Tipoganaderia tipoGanaderia;
    TipoganaderiaJpaController controlador = new TipoganaderiaJpaController();
    List<Tipoganaderia> listaTipoganaderia = new ArrayList<>();
    String[] columnasTipoganaderia = {"Nombre","Tiempo de gestación"};
    DefaultTableModel modeloTipoganaderia = new DefaultTableModel(columnasTipoganaderia, 0);
    
    public FormTipoGanaderia(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        getTablaTotal();
    }
    
    private void getTablaTotal() {
        listaTipoganaderia.clear();
        List<Tipoganaderia> lista;

        try {
            lista = controlador.findTipoganaderiaEntities();
            //lista = controlador.findAnimalEntities();
            for (int i = 0; i < lista.size(); i++) {
                
                TipoGanaderiaAModelo(lista.get(i));
            }
            this.jTableTipoGanaderia.setFocusable(false);
            this.jTableTipoGanaderia.setModel(modeloTipoganaderia);
           
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar los datos: " + e.toString());
        }

    }
    
    public void TipoGanaderiaAModelo(Tipoganaderia tipoGanaderia) {
        Object[] objeto = new Object[2];
        objeto[0] = tipoGanaderia.getNombre();
        objeto[1] = tipoGanaderia.getTiempoGestacion()+" meses";
        modeloTipoganaderia.addRow(objeto);
        listaTipoganaderia.add(tipoGanaderia);
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        MnBorrar = new javax.swing.JMenuItem();
        BtAceptar = new javax.swing.JButton();
        BtCancelar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableTipoGanaderia = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        TxNombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        TxTiempoGestacion = new javax.swing.JFormattedTextField();
        jLabel5 = new javax.swing.JLabel();

        MnBorrar.setText("Borrar");
        MnBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnBorrarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnBorrar);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

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

        jTableTipoGanaderia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTableTipoGanaderia.setComponentPopupMenu(jPopupMenu1);
        jScrollPane1.setViewportView(jTableTipoGanaderia);

        jLabel1.setText("Añadir tipo de ganaderia");

        jLabel2.setText("Nombre");

        jLabel3.setText("Tiempo de gestacion");

        jLabel4.setText("meses");

        TxTiempoGestacion.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("###"))));

        jLabel5.setText("Ganaderias actuales");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(141, 141, 141)
                        .addComponent(BtAceptar)
                        .addGap(83, 83, 83)
                        .addComponent(BtCancelar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addGap(41, 41, 41)
                                    .addComponent(TxNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(80, 80, 80)
                                    .addComponent(jLabel3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(TxTiempoGestacion)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jLabel4))
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(39, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(200, 200, 200))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(TxNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(TxTiempoGestacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtAceptar)
                    .addComponent(BtCancelar))
                .addGap(31, 31, 31))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtAceptarActionPerformed
        // TODO add your handling code here:
        if(this.TxNombre.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Introduzca el nombre", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if(this.TxTiempoGestacion.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Introduzca el tiempo de gestación", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        tipoGanaderia=new Tipoganaderia();
        String nombre = this.TxNombre.getText();
        int tiempoGestacion= Integer.parseInt(this.TxTiempoGestacion.getText());
        
        tipoGanaderia.setNombre(nombre);
        tipoGanaderia.setTiempoGestacion(tiempoGestacion);
        controlador.create(tipoGanaderia);
        modeloTipoganaderia.setRowCount(0);
        getTablaTotal();
        
        
    }//GEN-LAST:event_BtAceptarActionPerformed

    private void BtCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtCancelarActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_BtCancelarActionPerformed

    private void MnBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnBorrarActionPerformed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
 
            tipoGanaderia = listaTipoganaderia.get(this.jTableTipoGanaderia.getSelectedRow());
            controlador.destroy(tipoGanaderia.getIdtipoGanaderia());
            modeloTipoganaderia.setRowCount(0);
            getTablaTotal();
        } catch (IllegalOrphanException ex) {
            Logger.getLogger(FormCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(FormCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_MnBorrarActionPerformed

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
            java.util.logging.Logger.getLogger(FormTipoGanaderia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormTipoGanaderia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormTipoGanaderia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormTipoGanaderia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FormTipoGanaderia dialog = new FormTipoGanaderia(new javax.swing.JFrame(), true);
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
    private javax.swing.JMenuItem MnBorrar;
    private javax.swing.JTextField TxNombre;
    private javax.swing.JFormattedTextField TxTiempoGestacion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableTipoGanaderia;
    // End of variables declaration//GEN-END:variables
}

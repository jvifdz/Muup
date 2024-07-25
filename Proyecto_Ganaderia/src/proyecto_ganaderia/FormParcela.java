/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_ganaderia;

import javax.swing.JOptionPane;
import modelos.Parcela;

/**
 *
 * @author jvifdz-GRANDE
 */
public class FormParcela extends javax.swing.JDialog {

    public Parcela parcela;
    public boolean creado = false;
    /**
     * Creates new form NewJDialog
     */
    public FormParcela(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        parcela= new Parcela();
        this.setResizable(false);
    }
    
    public FormParcela(java.awt.Frame parent, boolean modal,Parcela parcela) {
        super(parent, modal);
        initComponents();
        this.BtGuardar.setText("Editar");
        this.setLocationRelativeTo(null);
        this.parcela=parcela;
        this.TxNombre.setText(parcela.getNombre());
        this.spinerTamano.setValue(parcela.getTamano());
        this.TxLugar.setText(parcela.getLugarSituada());
        this.setResizable(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TxLugar = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        spinerTamano = new javax.swing.JSpinner();
        BtGuardar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        TxNombre = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        BtCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel4.setText("Tamaño, en hectareas");

        BtGuardar.setText("Guardar");
        BtGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtGuardarActionPerformed(evt);
            }
        });

        jLabel1.setText("Insertar Parcela");

        jLabel2.setText("Nombre");

        jLabel3.setText("Lugar Situada");

        BtCancelar.setText("Cancelar");
        BtCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(TxNombre)
                            .addComponent(TxLugar)
                            .addComponent(spinerTamano, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(BtGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BtCancelar)))
                .addContainerGap(44, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(117, 117, 117))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel1)
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TxNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(TxLugar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(spinerTamano, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtGuardar)
                    .addComponent(BtCancelar))
                .addContainerGap(43, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtGuardarActionPerformed
        // TODO add your handling code here:
        if(this.TxNombre.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Introduzca el nombre", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if(this.TxLugar.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Introduzca el lugar donde esta situada", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String nombre=this.TxNombre.getText();
        String lugarSituada=this.TxLugar.getText();
        int tamano=(int) this.spinerTamano.getValue();
        if (tamano == 0) {
            JOptionPane.showMessageDialog(this,
                    "Debes introducir un numero de dosis",
                    "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        parcela.setNombre(nombre);
        parcela.setLugarSituada(lugarSituada);
        parcela.setTamano(tamano);
        creado=true;
        this.dispose();
       
    }//GEN-LAST:event_BtGuardarActionPerformed

    private void BtCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtCancelarActionPerformed
        // TODO add your handling code here:
        
        this.dispose();
    }//GEN-LAST:event_BtCancelarActionPerformed

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
            java.util.logging.Logger.getLogger(FormParcela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormParcela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormParcela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormParcela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FormParcela dialog = new FormParcela(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton BtCancelar;
    private javax.swing.JButton BtGuardar;
    private javax.swing.JTextField TxLugar;
    private javax.swing.JTextField TxNombre;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JSpinner spinerTamano;
    // End of variables declaration//GEN-END:variables
}

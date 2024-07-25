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
import modelos.Enfermedad;
import modelos.EnfermedadJpaController;
import modelos.exceptions.IllegalOrphanException;
import modelos.exceptions.NonexistentEntityException;

/**
 *
 * @author jvifdz-GRANDE
 */
public class FormEnfermedad extends javax.swing.JDialog {

    public Enfermedad enfermedad;
    
    EnfermedadJpaController controlador = new EnfermedadJpaController();
    List<Enfermedad> listaEnfermedad = new ArrayList<>();
    String[] columnasEnfermedad = {"Nombre"};

    DefaultTableModel modeloEnfermedad = new DefaultTableModel(columnasEnfermedad, 0);

    /**
     * Creates new form FormEnfermedad
     */
    public FormEnfermedad(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        getTablaTotalEnfermedad();
        this.setResizable(false);
        this.jTableEnfermedad.setDefaultEditor(Object.class, null);
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        TxNombre = new javax.swing.JTextField();
        BtGuardar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableEnfermedad = new javax.swing.JTable();
        lblEnfermedad = new javax.swing.JLabel();

        MnBorrar.setText("Borrar");
        MnBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnBorrarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnBorrar);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Insertar Enfermedad");

        jLabel2.setText("Nombre de la enfermedad");

        BtGuardar.setText("Guardar");
        BtGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtGuardarActionPerformed(evt);
            }
        });

        jTableEnfermedad.setModel(new javax.swing.table.DefaultTableModel(
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
        jTableEnfermedad.setComponentPopupMenu(jPopupMenu1);
        jScrollPane1.setViewportView(jTableEnfermedad);

        lblEnfermedad.setText("Total de enfermedades");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 429, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(102, 102, 102)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(27, 27, 27)
                                .addComponent(TxNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(124, 124, 124)
                                .addComponent(BtGuardar))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(216, 216, 216)
                        .addComponent(jLabel1)))
                .addContainerGap(84, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblEnfermedad)
                .addGap(130, 130, 130))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(TxNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(BtGuardar)
                .addGap(38, 38, 38)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblEnfermedad)
                .addGap(48, 48, 48))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtGuardarActionPerformed
        // TODO add your handling code here:
        if(this.TxNombre.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Introduzca el nombre de la enfermedad", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        enfermedad = new Enfermedad();
        String nombre = this.TxNombre.getText();
        enfermedad.setNombre(nombre);
        controlador.create(enfermedad);
        modeloEnfermedad.setRowCount(0);
        getTablaTotalEnfermedad();
        this.TxNombre.setText("");
        

    }//GEN-LAST:event_BtGuardarActionPerformed

    private void MnBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnBorrarActionPerformed
        try {
            enfermedad = listaEnfermedad.get(this.jTableEnfermedad.getSelectedRow());
            controlador.destroy(enfermedad.getIdEnfermedad());
            modeloEnfermedad.setRowCount(0);
            getTablaTotalEnfermedad();
        } catch (IllegalOrphanException ex) {
            Logger.getLogger(FormEnfermedad.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(FormEnfermedad.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_MnBorrarActionPerformed

    private void getTablaTotalEnfermedad() {
        listaEnfermedad.clear();
        List lista;

        try {
            lista = controlador.findEnfermedadEntities();
            //lista = controlador.findAnimalEntities();
            for (int i = 0; i < lista.size(); i++) {
                enfermedad = (Enfermedad) lista.get(i);
                EnfermedadAModelo(enfermedad);
            }
            this.jTableEnfermedad.setFocusable(false);
            this.jTableEnfermedad.setModel(modeloEnfermedad);
            this.lblEnfermedad.setText("Total de enfermedades " + this.jTableEnfermedad.getRowCount());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar los datos: " + e.toString());
        }

    }

    public void EnfermedadAModelo(Enfermedad enfermedad) {
        Object[] objeto = new Object[1];
        objeto[0] = enfermedad.getNombre();
        modeloEnfermedad.addRow(objeto);
        listaEnfermedad.add(enfermedad);
    }

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
            java.util.logging.Logger.getLogger(FormEnfermedad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormEnfermedad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormEnfermedad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormEnfermedad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FormEnfermedad dialog = new FormEnfermedad(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton BtGuardar;
    private javax.swing.JMenuItem MnBorrar;
    private javax.swing.JTextField TxNombre;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableEnfermedad;
    private javax.swing.JLabel lblEnfermedad;
    // End of variables declaration//GEN-END:variables
}

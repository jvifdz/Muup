/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_ganaderia;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelos.Animal;
import modelos.AnimalJpaController;
import modelos.Animalmedicamento;
import modelos.AnimalmedicamentoJpaController;
import modelos.Enfermedad;
import modelos.EnfermedadJpaController;
import modelos.HistorialenfJpaController;
import modelos.Historialparcela;
import modelos.HistorialparcelaJpaController;
import modelos.Medicamento;
import modelos.MedicamentoJpaController;
import modelos.Parcela;
import modelos.ParcelaJpaController;
import modelos.exceptions.NonexistentEntityException;

/**
 *
 * @author jvifdz-GRANDE
 */
public class FormAsignarMedicamento extends javax.swing.JDialog {

    /**
     * Creates new form FormAsignarMedicamento
     */
    public Animal[] animal;
    AnimalJpaController controladorAnimal = new AnimalJpaController();
    Medicamento medicamento;
    List<Medicamento> listaMedicamento = new ArrayList<>();
    String[] columnasMedicamento = {"Nombre", "Fecha Compra", "Volumen", "Número de dosis", "precio"};
    DefaultTableModel modeloMedicamento = new DefaultTableModel(columnasMedicamento, 0);
    MedicamentoJpaController medicamentoController = new MedicamentoJpaController();
    AnimalmedicamentoJpaController historialController = new AnimalmedicamentoJpaController();

    boolean creado = false;

    private void getTablaTotalMedicamento() {
        listaMedicamento.clear();
        List<Medicamento> lista;
        MedicamentoJpaController controlador = new MedicamentoJpaController();
        try {
            lista = controlador.findMedicamentoEntities();
            //lista = controlador.findAnimalEntities();
            for (int i = 0; i < lista.size(); i++) {

                MedicamentoAModelo(lista.get(i));
            }
            this.jTableAsignarMedicamento.setFocusable(false);
            this.jTableAsignarMedicamento.setModel(modeloMedicamento);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar los datos: " + e.toString());
        }

    }

    public void MedicamentoAModelo(Medicamento medicamento) {
        Object[] objeto = new Object[5];
        objeto[0] = medicamento.getNombre();
        objeto[1] = new SimpleDateFormat("dd MMM yyyy").format(new Date(medicamento.getFechaCompra().getTime()));
        objeto[2] = medicamento.getVolumen();
        objeto[3] = medicamento.getVolumenDosis();
        objeto[4] = medicamento.getPrecio();
        modeloMedicamento.addRow(objeto);
        listaMedicamento.add(medicamento);
    }

    public FormAsignarMedicamento(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.jTableAsignarMedicamento.setDefaultEditor(Object.class, null);
        this.setResizable(false);
    }

    public FormAsignarMedicamento(java.awt.Frame parent, boolean modal, Animal[] animal, java.util.Date fechaMaxima) {
        super(parent, modal);
        initComponents();
        this.jTableAsignarMedicamento.setDefaultEditor(Object.class, null);
        this.jTableAsignarMedicamento.setDefaultEditor(Object.class, null);
        this.animal = animal;
        getTablaTotalMedicamento();
        this.setLocationRelativeTo(null);
        this.jDateFechaVacuna.setMaxSelectableDate(fechaMaxima);
        this.setResizable(false);
        this.jDateFechaVacuna.setDate(fechaMaxima);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BtCancelar = new javax.swing.JButton();
        TxBuscar = new javax.swing.JTextField();
        BtBuscarMedicamento = new javax.swing.JButton();
        jDateFechaVacuna = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableAsignarMedicamento = new javax.swing.JTable();
        BtConfirmar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jSpinnerNumDosis = new javax.swing.JSpinner();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        BtCancelar.setText("Cancelar");
        BtCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtCancelarActionPerformed(evt);
            }
        });

        BtBuscarMedicamento.setText("Buscar");
        BtBuscarMedicamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtBuscarMedicamentoActionPerformed(evt);
            }
        });

        jTableAsignarMedicamento.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTableAsignarMedicamento);

        BtConfirmar.setText("Confirmar");
        BtConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtConfirmarActionPerformed(evt);
            }
        });

        jLabel1.setText("Asignar medicamento");

        jLabel2.setText("Fecha");

        jLabel3.setText("Numero de dosis");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addComponent(jLabel3)
                .addGap(27, 27, 27)
                .addComponent(jSpinnerNumDosis, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(28, 28, 28)
                .addComponent(jDateFechaVacuna, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(125, 125, 125))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(37, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 691, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
            .addGroup(layout.createSequentialGroup()
                .addGap(154, 154, 154)
                .addComponent(BtConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BtCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(148, 148, 148))
            .addGroup(layout.createSequentialGroup()
                .addGap(226, 226, 226)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(TxBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(36, 36, 36)
                .addComponent(BtBuscarMedicamento, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(jLabel3)
                        .addComponent(jSpinnerNumDosis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(34, 34, 34)
                        .addComponent(jDateFechaVacuna, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TxBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtBuscarMedicamento))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtConfirmar)
                    .addComponent(BtCancelar))
                .addGap(37, 37, 37))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtCancelarActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_BtCancelarActionPerformed

    private void BtBuscarMedicamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtBuscarMedicamentoActionPerformed
        // TODO add your handling code here:

        modeloMedicamento.setRowCount(0);
        listaMedicamento.clear();
        List <Medicamento> lista;
        MedicamentoJpaController medicamentoController = new MedicamentoJpaController();
        try {
            lista = medicamentoController.MedicamentoNombre(TxBuscar.getText());
            //lista = controlador.findAnimalEntities();
            for (int i = 0; i < lista.size(); i++) {
                
                
                MedicamentoAModelo(lista.get(i));
            }
            this.jTableAsignarMedicamento.setFocusable(false);
            this.jTableAsignarMedicamento.setModel(modeloMedicamento);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar los datos: " + e.toString());
        }

    }//GEN-LAST:event_BtBuscarMedicamentoActionPerformed

    private void BtConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtConfirmarActionPerformed
        // TODO add your handling code here:
        if (this.jTableAsignarMedicamento.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione un medicamento", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Date fechaVacunacion;
        if (this.jDateFechaVacuna.getDate() == null) {
            fechaVacunacion = new Date();
        } else {
            fechaVacunacion = jDateFechaVacuna.getDate();
        }

        int numDosis = (int) this.jSpinnerNumDosis.getValue();

        medicamento = this.listaMedicamento.get(this.jTableAsignarMedicamento.getSelectedRow());

        if (numDosis == 0) {
            JOptionPane.showMessageDialog(this,
                    "Debes introducir un numero de dosis",
                    "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (medicamento.getVolumenDosis() - (numDosis * animal.length) >= 0) {

            try {
                medicamento.setVolumenDosis(medicamento.getVolumenDosis() - (numDosis * animal.length));
                medicamentoController.edit(medicamento);

                for (int i = 0; i < this.animal.length; i++) {

                    crearHistorialMedicamento(animal[i], medicamento, fechaVacunacion, numDosis);
                }
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(FormAsignarMedicamento.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(FormAsignarMedicamento.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            JOptionPane.showMessageDialog(this,
                    "No tienes suficientes dosis, te faltan " + ((numDosis * animal.length) - medicamento.getVolumenDosis()) + " dosis",
                    "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
            return;

        }

        creado = true;
        this.dispose();
    }//GEN-LAST:event_BtConfirmarActionPerformed

    private void crearHistorialMedicamento(Animal animal, Medicamento medicamento, Date fechaVacunacion, int numDosis) {

        Animalmedicamento historial = new Animalmedicamento();
        historial.setFkAnimal(animal);
        historial.setFkMedicamento(medicamento);
        historial.setFechaVacunacion(fechaVacunacion);
        historial.setNumDosis(numDosis);
        historialController.create(historial);
        animal.getAnimalmedicamentoList().add(historial);

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
            java.util.logging.Logger.getLogger(FormAsignarMedicamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormAsignarMedicamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormAsignarMedicamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormAsignarMedicamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FormAsignarMedicamento dialog = new FormAsignarMedicamento(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton BtBuscarMedicamento;
    private javax.swing.JButton BtCancelar;
    private javax.swing.JButton BtConfirmar;
    private javax.swing.JTextField TxBuscar;
    private com.toedter.calendar.JDateChooser jDateFechaVacuna;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner jSpinnerNumDosis;
    private javax.swing.JTable jTableAsignarMedicamento;
    // End of variables declaration//GEN-END:variables
}
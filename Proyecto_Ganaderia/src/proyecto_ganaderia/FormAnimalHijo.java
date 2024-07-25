/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_ganaderia;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelos.Animal;
import modelos.AnimalJpaController;
import modelos.exceptions.NonexistentEntityException;

/**
 *
 * @author jvifdz-GRANDE
 */
public class FormAnimalHijo extends javax.swing.JDialog {

    /**
     * Creates new form FormAnimalHijo
     */
    Animal animal;

    List<Animal> listaAnimal = new ArrayList<>();
    String[] columnasAnimal = {"DAI", "Nombre", "Fecha de nacimiento", "Tipo de animal", "Sexo"};
    DefaultTableModel modeloAnimal = new DefaultTableModel(columnasAnimal, 0);

    public FormAnimalHijo(javax.swing.JDialog parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setTitle("Hijos");
        this.setResizable(false);
    }

    public FormAnimalHijo(javax.swing.JDialog parent, boolean modal, Animal animal) {
        super(parent, modal);
        initComponents();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setTitle("Hijos");
        this.animal = animal;
        this.LblTitulo.setText("Hijos de " + this.animal.getNombre());
        this.animal = animal;
        getTablaAnimalHijo();
        this.jTableAnimalHijo.setDefaultEditor(Object.class, null);

        this.jTableAnimalHijo.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent) {
                JTable table = (JTable) mouseEvent.getSource();

                if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
                    // your valueChanged overridden method 

                    if (mouseEvent.getClickCount() == 2 && jTableAnimalHijo.getSelectedRow() != -1) {
                        // your valueChanged overridden method 

                        Animal animalHijo;
                        
                        animalHijo = listaAnimal.get(jTableAnimalHijo.getSelectedRow());
                        FormAnimal vacaForm = new FormAnimal(new Formulario(), true, animalHijo, new Date());
                        vacaForm.setVisible(true);
                        if (vacaForm.creado == true) {
                            try {
                                AnimalJpaController animalController = new AnimalJpaController();
                                animalController.edit(vacaForm.animal);
                                modeloAnimal.setRowCount(0);
                                getTablaAnimalHijo();
                            } catch (NonexistentEntityException ex) {
                                Logger.getLogger(Formulario.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (Exception ex) {
                                Logger.getLogger(Formulario.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        }
                    }

                }
            }
        });
    }

    private void getTablaAnimalHijo() {
        listaAnimal.clear();
        List<Animal> lista;
        AnimalJpaController controlador = new AnimalJpaController();
        try {
            lista = controlador.AnimalesHijos(this.animal.getDai());

            for (int i = 0; i < lista.size(); i++) {

                AnimalAModelo(lista.get(i));
            }
            this.jTableAnimalHijo.setFocusable(false);
            this.jTableAnimalHijo.setModel(modeloAnimal);
            this.LblTotal.setText("Total " + this.jTableAnimalHijo.getRowCount());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar los datos: " + e.toString());
        }

    }

    public void AnimalAModelo(Animal animal) {
        String dai = animal.getDai();
        String dai4 = "";
        if (dai.length() > 4) {
            dai4 = dai.substring(dai.length() - 4);
        } else {
            dai4 = dai;
        }
        Object[] objeto = new Object[5];
        objeto[0] = dai4;
        objeto[1] = animal.getNombre();
        objeto[2] = new SimpleDateFormat("dd MMM yyyy").format(new Date(animal.getFechaNacimiento().getTime()));
        objeto[3] = animal.getFktipoGanaderia();
        objeto[4] = animal.getSexo();
        modeloAnimal.addRow(objeto);
        listaAnimal.add(animal);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        LblTitulo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableAnimalHijo = new javax.swing.JTable();
        LblTotal = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        LblTitulo.setText("Hijos de");

        jTableAnimalHijo.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTableAnimalHijo);

        LblTotal.setText("Total");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(179, 179, 179)
                        .addComponent(LblTitulo))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(55, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(LblTotal)
                .addGap(98, 98, 98))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(LblTitulo)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(LblTotal)
                .addContainerGap(51, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(FormAnimalHijo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormAnimalHijo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormAnimalHijo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormAnimalHijo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FormAnimalHijo dialog = new FormAnimalHijo(new javax.swing.JDialog(), true);
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
    private javax.swing.JLabel LblTitulo;
    private javax.swing.JLabel LblTotal;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableAnimalHijo;
    // End of variables declaration//GEN-END:variables
}

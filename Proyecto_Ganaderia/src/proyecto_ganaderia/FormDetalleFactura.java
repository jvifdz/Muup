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
import modelos.Detallefactura;
import modelos.DetallefacturaJpaController;
import modelos.Factura;
import modelos.Historialenf;
import modelos.HistorialenfJpaController;
import modelos.Historialparcela;
import modelos.exceptions.NonexistentEntityException;

/**
 *
 * @author jvifdz-GRANDE
 */
public class FormDetalleFactura extends javax.swing.JDialog {

    /**
     * Creates new form FormDetalleFactura
     */
    Factura factura;

    List<Detallefactura> listaDetallefactura = new ArrayList<>();
    String[] columnasDetallefactura = {"DAI", "Nombre", "Tipo de ganaderia", "Precio"};
    DefaultTableModel modeloDetallefactura = new DefaultTableModel(columnasDetallefactura, 0);

    public FormDetalleFactura(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
     this.setResizable(false);
        initComponents();
    }

    public FormDetalleFactura(java.awt.Frame parent, boolean modal, Factura factura) {
        super(parent, modal);
        initComponents();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.factura = factura;
        getTablaHistorialEnfermedadDesc(this.factura.getIdFactura());
        this.jTableDetalleFactura.setDefaultEditor(Object.class, null);

        this.setTitle("Factura " + new SimpleDateFormat("dd MMM yyyy").format(new Date(factura.getFechaVenta().getTime())));
        this.lblDetalle.setText("Detalle de la factura del dia: " + new SimpleDateFormat("dd MMM yyyy").format(new Date(factura.getFechaVenta().getTime())));

        this.jTableDetalleFactura.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent) {
                JTable table = (JTable) mouseEvent.getSource();

                if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
                    // your valueChanged overridden method 

                    if (mouseEvent.getClickCount() == 2 && jTableDetalleFactura.getSelectedRow() != -1) {
                        // your valueChanged overridden method 

                        Detallefactura detalle;
                        
                        detalle = listaDetallefactura.get(jTableDetalleFactura.getSelectedRow());
                        
                        AnimalJpaController animalController=new AnimalJpaController();
                        Animal animal;
                        animal=animalController.Animales(detalle.getIdDetalle());
                        
                        FormAnimal vacaForm = new FormAnimal(new Formulario(), true, animal, new Date());
                        vacaForm.setVisible(true);
                        if (vacaForm.creado == true) {
                            try {
                                
                                animalController.edit(vacaForm.animal);
                                
                                
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

    private void getTablaHistorialEnfermedadDesc(int id) {

        List<Detallefactura> lista;
        DetallefacturaJpaController controlador = new DetallefacturaJpaController();
        try {
            lista = controlador.detalleFactura(id);

            for (int i = 0; i < lista.size(); i++) {

                HistorialEnfermedadAModelo(lista.get(i));
            }
            this.jTableDetalleFactura.setFocusable(false);
            this.jTableDetalleFactura.setModel(modeloDetallefactura);
            //this.LblTotal.setText("Número total de animales " + this.jTableVaca.getRowCount());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar los datos: " + e.toString());
        }

    }

    public void HistorialEnfermedadAModelo(Detallefactura detallefactura) {
        Object[] objeto = new Object[4];
        objeto[0] = detallefactura.getFkAnimal().getDai();
        objeto[1] = detallefactura.getFkAnimal().getNombre();
        objeto[2] = detallefactura.getFkAnimal().getFktipoGanaderia();
        objeto[3] = detallefactura.getPrecio();

        modeloDetallefactura.addRow(objeto);
        listaDetallefactura.add(detallefactura);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblDetalle = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableDetalleFactura = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblDetalle.setText("Detalle de la factura del dia:");

        jTableDetalleFactura.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTableDetalleFactura);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 648, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(198, 198, 198)
                .addComponent(lblDetalle)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblDetalle)
                .addGap(30, 30, 30)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE)
                .addContainerGap())
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
            java.util.logging.Logger.getLogger(FormDetalleFactura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormDetalleFactura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormDetalleFactura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormDetalleFactura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FormDetalleFactura dialog = new FormDetalleFactura(new javax.swing.JFrame(), true);
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableDetalleFactura;
    private javax.swing.JLabel lblDetalle;
    // End of variables declaration//GEN-END:variables
}

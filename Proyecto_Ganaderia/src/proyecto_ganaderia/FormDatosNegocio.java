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
import modelos.Datosnegocio;
import modelos.DatosnegocioJpaController;
import modelos.exceptions.IllegalOrphanException;
import modelos.exceptions.NonexistentEntityException;

/**
 *
 * @author jvifdz-GRANDE
 */
public class FormDatosNegocio extends javax.swing.JDialog {

    /**
     * Creates new form FormDatosNegocio
     */
    Datosnegocio datosnegocio;

    DatosnegocioJpaController controlador = new DatosnegocioJpaController();
    List<Datosnegocio> listaDatosnegocio = new ArrayList<>();
    String[] columnasDatosnegocio = {"Nombre","Codigo de explotación"};
    DefaultTableModel modeloDatosNegocio = new DefaultTableModel(columnasDatosnegocio, 0);

    public FormDatosNegocio(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("Datos negocio");
        getTablaTotal();
        this.jTableDatosNegocio.setDefaultEditor(Object.class, null);
        this.setResizable(false);
        this.jTableDatosNegocio.setDefaultEditor(Object.class, null);
        
        

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
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        TxNumeroTelf = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        TxCodExplotacion = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        TxDireccion = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableDatosNegocio = new javax.swing.JTable();
        Cancelar = new javax.swing.JButton();
        Confirmar = new javax.swing.JButton();
        TxCorreo = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        TxDni = new javax.swing.JTextField();
        TxCodPostal = new javax.swing.JFormattedTextField();
        jLabel9 = new javax.swing.JLabel();

        MnBorrar.setText("Borrar");
        MnBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnBorrarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnBorrar);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Introducir datos del negocio");

        jLabel2.setText("Nombre");

        jLabel3.setText("DNI/NIF");

        jLabel4.setText("Numero de explotación");

        jLabel5.setText("Código postal");

        TxNumeroTelf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#########"))));

        jLabel6.setText("Número de telefono");

        jLabel7.setText("Dirección");

        jTableDatosNegocio.setModel(new javax.swing.table.DefaultTableModel(
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
        jTableDatosNegocio.setComponentPopupMenu(jPopupMenu1);
        jScrollPane1.setViewportView(jTableDatosNegocio);

        Cancelar.setText("Cancelar");
        Cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelarActionPerformed(evt);
            }
        });

        Confirmar.setText("Confirmar");
        Confirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConfirmarActionPerformed(evt);
            }
        });

        jLabel8.setText("Correo");

        TxCodPostal.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#####"))));

        jLabel9.setText("Ganaderias actuales");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(Confirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(108, 108, 108)
                .addComponent(Cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(121, 121, 121))
            .addGroup(layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 505, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel6)
                            .addComponent(jLabel8))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(48, 48, 48)
                                .addComponent(jLabel1))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(TxCorreo)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(TxNombre, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                                            .addComponent(TxCodExplotacion, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(TxNumeroTelf, javax.swing.GroupLayout.Alignment.LEADING))
                                        .addGap(38, 38, 38)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel7)
                                            .addComponent(jLabel3))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(TxDireccion, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                                            .addComponent(TxDni)
                                            .addComponent(TxCodPostal))))))))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(TxNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(TxDni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TxCodExplotacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(TxCodPostal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(TxNumeroTelf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7)
                        .addComponent(TxDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(TxCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Confirmar)
                    .addComponent(Cancelar))
                .addGap(20, 20, 20))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConfirmarActionPerformed
        // TODO add your handling code here:
        
        if(this.TxNombre.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Introduzca el nombre", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if(this.TxDni.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Introduzca el dni", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if(this.TxCodExplotacion.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Introduzca el código de explotacion", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if(this.TxCodPostal.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Introduzca el código postal", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if(this.TxNumeroTelf.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Introduzca el número de teléfono", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if(this.TxDireccion.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Introduzca la dirección", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if(this.TxCorreo.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Introduzca el correo", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        datosnegocio = new Datosnegocio();

        String nombre = this.TxNombre.getText();
        String DNI = this.TxDni.getText();
        String codExplotacion = this.TxCodExplotacion.getText();
        int codPostal = Integer.parseInt(this.TxCodPostal.getText());
        int numTelf = Integer.parseInt(this.TxNumeroTelf.getText());
        String direccion = this.TxDireccion.getText();
        String correo = this.TxCorreo.getText();

        datosnegocio.setNombre(nombre);
        datosnegocio.setDni(DNI);
        datosnegocio.setCodExplotacion(codExplotacion);
        datosnegocio.setCodPostal(codPostal);
        datosnegocio.setDireccion(direccion);
        datosnegocio.setTelefono(numTelf);
        datosnegocio.setCorreo(correo);
        controlador.create(datosnegocio);
        modeloDatosNegocio.setRowCount(0);
        getTablaTotal();

    }//GEN-LAST:event_ConfirmarActionPerformed

    private void MnBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnBorrarActionPerformed

         try {
            datosnegocio = listaDatosnegocio.get(this.jTableDatosNegocio.getSelectedRow()); 
            controlador.destroy(datosnegocio.getIddatosNegocio());
            modeloDatosNegocio.setRowCount(0);
            getTablaTotal();
        } catch (IllegalOrphanException ex) {
            Logger.getLogger(FormEnfermedad.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(FormEnfermedad.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }//GEN-LAST:event_MnBorrarActionPerformed

    private void CancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelarActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_CancelarActionPerformed

    private void getTablaTotal() {
        listaDatosnegocio.clear();
        List<Datosnegocio> lista;

        try {
            lista = controlador.findDatosnegocioEntities();
            //lista = controlador.findAnimalEntities();
            for (int i = 0; i < lista.size(); i++) {
                
                DatosnegocioAModelo(lista.get(i));
            }
            this.jTableDatosNegocio.setFocusable(false);
            this.jTableDatosNegocio.setModel(modeloDatosNegocio);
           
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar los datos: " + e.toString());
        }

    }

    public void DatosnegocioAModelo(Datosnegocio datosnegocio) {
        Object[] objeto = new Object[2];
        objeto[0] = datosnegocio.getNombre();
        objeto[1] = datosnegocio.getCodExplotacion();
        modeloDatosNegocio.addRow(objeto);
        listaDatosnegocio.add(datosnegocio);
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
            java.util.logging.Logger.getLogger(FormDatosNegocio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormDatosNegocio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormDatosNegocio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormDatosNegocio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FormDatosNegocio dialog = new FormDatosNegocio(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton Cancelar;
    private javax.swing.JButton Confirmar;
    private javax.swing.JMenuItem MnBorrar;
    private javax.swing.JTextField TxCodExplotacion;
    private javax.swing.JFormattedTextField TxCodPostal;
    private javax.swing.JTextField TxCorreo;
    private javax.swing.JTextField TxDireccion;
    private javax.swing.JTextField TxDni;
    private javax.swing.JTextField TxNombre;
    private javax.swing.JFormattedTextField TxNumeroTelf;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableDatosNegocio;
    // End of variables declaration//GEN-END:variables
}

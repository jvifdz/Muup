/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_ganaderia;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelos.Animal;
import modelos.AnimalJpaController;
import modelos.Cliente;
import modelos.ClienteJpaController;
import modelos.Datosnegocio;
import modelos.DatosnegocioJpaController;
import modelos.Detallefactura;
import modelos.DetallefacturaJpaController;
import modelos.Factura;
import modelos.FacturaJpaController;
import modelos.HistorialenfJpaController;
import modelos.HistorialparcelaJpaController;
import modelos.exceptions.NonexistentEntityException;

/**
 *
 * @author jvifdz-GRANDE
 */
public class FormVenta extends javax.swing.JDialog {

    /**
     * Creates new form FormVenta
     */
    boolean creado = false;
    public Animal[] animal;
    AnimalJpaController controladorAnimal = new AnimalJpaController();
    List<Animal> listaAnimal;
    
   
    
    String[] columnasAnimal = {"DAI", "Nombre", "Fecha de nacimiento", "Tipo de animal", "Sexo"};
    DefaultTableModel modeloAnimal = new DefaultTableModel(columnasAnimal, 0);
    
    
    Cliente cliente;
    List<Cliente> listaCliente;
    List<Datosnegocio> listaNegocio;
    ClienteJpaController controladorCliente = new ClienteJpaController();
    String[] columnasCliente = {"Nombre","Codigo de explotación"};
    DefaultTableModel modeloCliente = new DefaultTableModel(columnasCliente, 0);
    
    
    
    Datosnegocio datosnegocio;

    DatosnegocioJpaController controladorDatosNegocio = new DatosnegocioJpaController();
    String[] columnasDatosnegocio = {"Nombre","Codigo de explotación"};
    DefaultTableModel modeloDatosNegocio = new DefaultTableModel(columnasDatosnegocio, 0);

           
    
    
    public FormVenta(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setResizable(false);
    }
    
    
    public FormVenta(java.awt.Frame parent, boolean modal,Animal[] animal, java.util.Date fechaMaxima) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        this.animal=animal;
        getTablaActualAnimal();
        getTablaTotalClientes();
        this.jTableCliente.setDefaultEditor(Object.class, null);
        this.jTableAnimalEscogido.setDefaultEditor(Object.class, null);
        getTablaTotalDatosNegocio();
        this.jTableDatosNegocio.setDefaultEditor(Object.class, null);
        this.jDateFechaVenta.setDate(fechaMaxima);
        this.setResizable(false);
        
    }
    
    private void getTablaActualAnimal() {
        try {
            
           listaAnimal = new LinkedList<>(Arrays.asList(animal));
            for (int i = 0; i < listaAnimal.size(); i++) {
                
                AnimalAModelo(listaAnimal.get(i));
            }
            this.jTableAnimalEscogido.setFocusable(false);
            this.jTableAnimalEscogido.setModel(modeloAnimal);
            //this.LblTotal.setText("Total de animales " + this.jTableVaca.getRowCount());
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
    }
    
    
    
    private void getTablaTotalClientes() {

        

        try {
            listaCliente = controladorCliente.findClienteEntities();
            //lista = controlador.findAnimalEntities();
            for (int i = 0; i < listaCliente.size(); i++) {
                
                ClienteAModelo(listaCliente.get(i));
            }
            this.jTableCliente.setFocusable(false);
            this.jTableCliente.setModel(modeloCliente);
           
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar los datos: " + e.toString());
        }

    }
    
    public void ClienteAModelo(Cliente cliente) {
        Object[] objeto = new Object[2];
        objeto[0] = cliente.getNombre();
        objeto[1] = cliente.getCodExplotacion();
        modeloCliente.addRow(objeto);

    }
    
    
    
    
    private void getTablaTotalDatosNegocio() {
        
        

        try {
            listaNegocio = controladorDatosNegocio.findDatosnegocioEntities();
            //lista = controlador.findAnimalEntities();
            for (int i = 0; i < listaNegocio.size(); i++) {
                
                DatosnegocioAModelo(listaNegocio.get(i));
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableAnimalEscogido = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        BtVender = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableCliente = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTableDatosNegocio = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jDateFechaVenta = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        TxPrecio = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();

        MnBorrar.setText("Borrar");
        MnBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnBorrarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnBorrar);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jTableAnimalEscogido.setModel(new javax.swing.table.DefaultTableModel(
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
        jTableAnimalEscogido.setComponentPopupMenu(jPopupMenu1);
        jScrollPane1.setViewportView(jTableAnimalEscogido);

        jLabel1.setText("Animales escogidos");

        jLabel2.setText("Cliente");

        BtVender.setText("Vender");
        BtVender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtVenderActionPerformed(evt);
            }
        });

        jTableCliente.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(jTableCliente);

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
        jScrollPane4.setViewportView(jTableDatosNegocio);

        jLabel3.setText("Ganadería emisora");

        jLabel4.setText("Fecha de venta");

        jLabel5.setText("Venta");

        jLabel6.setText("Precio");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(341, 341, 341)
                        .addComponent(BtVender, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel2)
                                .addComponent(jScrollPane3)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 748, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addGap(312, 312, 312)
                                                    .addComponent(jLabel5))
                                                .addGroup(layout.createSequentialGroup()
                                                    .addGap(108, 108, 108)
                                                    .addComponent(jLabel6)
                                                    .addGap(39, 39, 39)
                                                    .addComponent(TxPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel4)
                                            .addGap(39, 39, 39)))
                                    .addComponent(jDateFechaVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(59, 59, 59))))))
                .addContainerGap(47, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jDateFechaVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(36, 36, 36))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(TxPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BtVender, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void MnBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnBorrarActionPerformed
        // TODO add your handling code here:
        listaAnimal.remove(this.jTableAnimalEscogido.getSelectedRow());
        modeloAnimal.removeRow(this.jTableAnimalEscogido.getSelectedRow());
    }//GEN-LAST:event_MnBorrarActionPerformed

    private void BtVenderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtVenderActionPerformed
        // TODO add your handling code here:
        if(this.TxPrecio.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Introduzca el precio", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (this.jTableCliente.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione un cliente", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (this.jTableDatosNegocio.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione una ganadería emisora", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        
        FacturaJpaController facturaController = new FacturaJpaController();
        DetallefacturaJpaController lineaController=new DetallefacturaJpaController();
        HistorialparcelaJpaController historialController=new HistorialparcelaJpaController();
        
        Factura factura = new Factura();
        
         Date fechaVenta;
        if (this.jDateFechaVenta.getDate() == null) {
            fechaVenta = new Date();
        } else {
            fechaVenta = jDateFechaVenta.getDate();
        }
        float precio= Float.parseFloat(this.TxPrecio.getText());
        cliente=listaCliente.get(this.jTableCliente.getSelectedRow());
        datosnegocio=listaNegocio.get(this.jTableDatosNegocio.getSelectedRow());
        float precioTotal=0;
        
        
        factura.setFechaVenta(fechaVenta);
        factura.setFkCliente(cliente);
        factura.setFkdatosNegocio(datosnegocio);
        facturaController.create(factura);
        
        
        
        for (int i = 0; i < listaAnimal.size(); i++) {
            Detallefactura linea= new Detallefactura();
            linea.setFkAnimal(listaAnimal.get(i));
            linea.setFkFactura(factura);
            linea.setPrecio(precio);
            lineaController.create(linea);
            factura.getDetallefacturaList().add(linea);
            precioTotal=precioTotal+precio;
            
            
            
            listaAnimal.get(i).setRazonBaja("Vendido a "+cliente.getNombre());
            listaAnimal.get(i).setFechaBaja(fechaVenta);
            listaAnimal.get(i).getDetallefacturaList().add(linea);
            
            try {
             int numeroParcela = listaAnimal.get(i).getHistorialparcelaList().size() - 1;
                listaAnimal.get(i).getHistorialparcelaList().get(numeroParcela).setFechaSalida(fechaVenta);
                historialController.edit(listaAnimal.get(i).getHistorialparcelaList().get(numeroParcela));
            
            
            
                controladorAnimal.edit(listaAnimal.get(i));
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(FormVenta.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(FormVenta.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        factura.setPrecioTotal(precioTotal);
        factura.setIva(precio*0.21f);
        
        try {
            facturaController.edit(factura);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(FormVenta.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(FormVenta.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.dispose();
        
        
    }//GEN-LAST:event_BtVenderActionPerformed

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
            java.util.logging.Logger.getLogger(FormVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FormVenta dialog = new FormVenta(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton BtVender;
    private javax.swing.JMenuItem MnBorrar;
    private javax.swing.JTextField TxPrecio;
    private com.toedter.calendar.JDateChooser jDateFechaVenta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTableAnimalEscogido;
    private javax.swing.JTable jTableCliente;
    private javax.swing.JTable jTableDatosNegocio;
    // End of variables declaration//GEN-END:variables
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_ganaderia;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import modelos.Alimentos;
import modelos.AlimentosJpaController;
import modelos.Animal;
import modelos.AnimalJpaController;
import modelos.Factura;
import modelos.FacturaJpaController;
import modelos.Medicamento;
import modelos.MedicamentoJpaController;
import modelos.Parcela;
import modelos.ParcelaJpaController;
import modelos.Tipoganaderia;
import modelos.TipoganaderiaJpaController;
import modelos.exceptions.IllegalOrphanException;
import modelos.exceptions.NonexistentEntityException;

/**
 *
 * @author jvifdz-GRANDE
 */
public class Formulario extends javax.swing.JFrame {

    /**
     * Creates new form Formulario
     */
    Animal animal;
    Parcela parcela;
    Medicamento medicamento;
    Alimentos alimento;
    Factura factura;
    List<Animal> listaAnimal = new ArrayList<>();
    List<Parcela> listaParcela = new ArrayList<>();
    List<Medicamento> listaMedicamento = new ArrayList<>();
    List<Alimentos> listaAlimentos = new ArrayList<>();
    List<Factura> listaFactura = new ArrayList<>();
    String[] columnasAnimal = {"DAI", "Nombre", "Fecha de nacimiento", "Tipo de animal", "Sexo"};
    String[] columnasParcela = {"Nombre", "Tamaño", "Lugar Situada"};
    String[] columnasMedicamento = {"Nombre", "Fecha Compra", "Volumen", "Número de dosis", "precio"};
    String[] columnasAlimentos = {"Nombre", "Cantidad", "Fecha Compra", "precio", "Ganaderia destinada"};
    String[] columnasFactura = {"Número factura","Cliente", "Precio", "IVA", "Fecha venta", "Ganaderia emisora"};
    DefaultTableModel modeloAnimal = new DefaultTableModel(columnasAnimal, 0);
    DefaultTableModel modeloParcela = new DefaultTableModel(columnasParcela, 0);
    DefaultTableModel modeloMedicamento = new DefaultTableModel(columnasMedicamento, 0);
    DefaultTableModel modeloAlimentos = new DefaultTableModel(columnasAlimentos, 0);
    DefaultTableModel modeloFactura = new DefaultTableModel(columnasFactura, 0);

    public Formulario() {
        initComponents();
        getTablaActualAnimal();
        getTablaTotalParcela();
        getTablaTotalMedicamento();
        getTablaTotalAlimento();
        getTablaTotalFactura();
        getCombo();
        this.setResizable(false);
        this.setTitle("MUUP");
        this.setLocationRelativeTo(null);
        this.jTableVaca.setDefaultEditor(Object.class, null);
        this.jTableParcela.setDefaultEditor(Object.class, null);
        this.jTableMedicamento.setDefaultEditor(Object.class, null);
        this.jTableAlimento.setDefaultEditor(Object.class, null);
        this.jTableFacturas.setDefaultEditor(Object.class, null);

        this.CbTipoGanaderia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modeloAnimal.setRowCount(0);
                listaAnimal.clear();
                if (jCheckTotales.isSelected()) {
                    if (CbTipoGanaderia.getSelectedItem().toString().equals("Todas")) {
                        getTablaBajaAnimal();
                    } else {
                        getTablaTipoAnimal(CbTipoGanaderia.getSelectedItem().toString());
                    }
                } else if (!jCheckTotales.isSelected()) {
                    if (CbTipoGanaderia.getSelectedItem().toString().equals("Todas")) {
                        getTablaActualAnimal();
                    } else {

                        getTablaTipoActualAnimal(CbTipoGanaderia.getSelectedItem().toString());
                    }
                }
            }
        });

        this.jTableVaca.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent) {
                JTable table = (JTable) mouseEvent.getSource();

                if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
                    // your valueChanged overridden method 

                    if (mouseEvent.getClickCount() == 2 && jTableVaca.getSelectedRow() != -1) {
                        // your valueChanged overridden method 

                        animal = listaAnimal.get(jTableVaca.getSelectedRow());
                        FormAnimal vacaForm = new FormAnimal(new Formulario(), true, animal, new Date());
                        vacaForm.setVisible(true);
                        if (vacaForm.creado == true) {
                            try {
                                AnimalJpaController animalController = new AnimalJpaController();
                                animalController.edit(vacaForm.animal);

                            } catch (NonexistentEntityException ex) {
                                Logger.getLogger(Formulario.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (Exception ex) {
                                Logger.getLogger(Formulario.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        }
                        modeloAnimal.setRowCount(0);
                        getTablaActualAnimal();
                    }

                }
            }
        });
        this.jTableParcela.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent) {
                JTable table = (JTable) mouseEvent.getSource();
                Point point = mouseEvent.getPoint();

                if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
                    // your valueChanged overridden method 

                    // your valueChanged overridden method 
                    parcela = listaParcela.get(jTableParcela.getSelectedRow());

                    FormParcela formParcela = new FormParcela(new Formulario(), true, parcela);
                    formParcela.setVisible(true);
                    if (formParcela.creado == true) {
                        try {
                            ParcelaJpaController parcelaController = new ParcelaJpaController();
                            parcelaController.edit(formParcela.parcela);
                            modeloParcela.setRowCount(0);
                            getTablaTotalParcela();
                        } catch (NonexistentEntityException ex) {
                            Logger.getLogger(Formulario.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (Exception ex) {
                            Logger.getLogger(Formulario.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }

                }
            }
        });
        this.jTableFacturas.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent) {
                JTable table = (JTable) mouseEvent.getSource();
                Point point = mouseEvent.getPoint();

                if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
                    // your valueChanged overridden method 

                    // your valueChanged overridden method 
                    factura = listaFactura.get(jTableFacturas.getSelectedRow());

                    FormDetalleFactura formParcela = new FormDetalleFactura(new Formulario(), true, factura);
                    formParcela.setVisible(true);

                }
            }
        });
    }

    private void getTablaActualAnimal() {
        listaAnimal.clear();
        List lista;
        AnimalJpaController controlador = new AnimalJpaController();
        try {
            lista = controlador.AnimalesActuales();
            //lista = controlador.findAnimalEntities();
            for (int i = 0; i < lista.size(); i++) {
                animal = (Animal) lista.get(i);
                AnimalAModelo(animal);
            }
            this.jTableVaca.setFocusable(false);
            this.jTableVaca.setModel(modeloAnimal);
            this.LblTotal.setText("Total de animales " + this.jTableVaca.getRowCount());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar los datos: " + e.toString());
        }

    }

    private void getTablaBajaAnimal() {
        listaAnimal.clear();
        List lista;
        AnimalJpaController controlador = new AnimalJpaController();
        try {
            lista = controlador.AnimalesBaja();
            //lista = controlador.findAnimalEntities();
            for (int i = 0; i < lista.size(); i++) {
                animal = (Animal) lista.get(i);
                AnimalAModelo(animal);
            }
            this.jTableVaca.setFocusable(false);
            this.jTableVaca.setModel(modeloAnimal);
            this.LblTotal.setText("Total de animales " + this.jTableVaca.getRowCount());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar los datos: " + e.toString());
        }

    }

    private void getTablaTotalParcela() {
        listaParcela.clear();
        List lista;
        ParcelaJpaController controlador = new ParcelaJpaController();
        try {
            lista = controlador.findParcelaEntities();
            //lista = controlador.findAnimalEntities();
            for (int i = 0; i < lista.size(); i++) {
                parcela = (Parcela) lista.get(i);
                ParcelaAModelo(parcela);
            }
            this.jTableParcela.setFocusable(false);
            this.jTableParcela.setModel(modeloParcela);
            this.lblParcelas.setText("Total de parcelas  " + this.jTableParcela.getRowCount());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar los datos: " + e.toString());
        }

    }

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
            this.jTableMedicamento.setFocusable(false);
            this.jTableMedicamento.setModel(modeloMedicamento);
            this.LblMedicamento.setText("Total " + this.jTableMedicamento.getRowCount());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar los datos: " + e.toString());
        }

    }

    private void getTablaTotalAlimento() {
        listaAlimentos.clear();
        List<Alimentos> lista;
        AlimentosJpaController controlador = new AlimentosJpaController();
        try {
            lista = controlador.findAlimentosEntities();
            //lista = controlador.findAnimalEntities();
            for (int i = 0; i < lista.size(); i++) {

                AlimentoAModelo(lista.get(i));
            }
            this.jTableAlimento.setFocusable(false);
            this.jTableAlimento.setModel(modeloAlimentos);
            this.LblAlimento.setText("Total " + this.jTableAlimento.getRowCount());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar los datos: " + e.toString());
        }

    }

    private void getTablaTotalFactura() {
        listaFactura.clear();
        List<Factura> lista;
        FacturaJpaController controlador = new FacturaJpaController();
        try {
            lista = controlador.findFacturasDesc();
            //lista = controlador.findAnimalEntities();
            for (int i = 0; i < lista.size(); i++) {

                FacturaAModelo(lista.get(i));
            }
            this.jTableFacturas.setFocusable(false);
            this.jTableFacturas.setModel(modeloFactura);
            this.LblFacturas.setText("Total " + this.jTableFacturas.getRowCount());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar los datos: " + e.toString());
        }

    }

    private void getTablaTipoAnimal(String nombre) {
        listaAnimal.clear();
        List lista;
        AnimalJpaController controlador = new AnimalJpaController();
        try {
            lista = controlador.AnimalesTipo(nombre);

            for (int i = 0; i < lista.size(); i++) {
                animal = (Animal) lista.get(i);
                AnimalAModelo(animal);
            }
            this.jTableVaca.setFocusable(false);
            this.jTableVaca.setModel(modeloAnimal);
            this.LblTotal.setText("Total de animales " + this.jTableVaca.getRowCount());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar los datos: " + e.toString());
        }

    }

    private void getTablaTipoActualAnimal(String nombre) {
        listaAnimal.clear();
        List lista;
        AnimalJpaController controlador = new AnimalJpaController();
        try {
            lista = controlador.AnimalesTipoActuales(nombre);

            for (int i = 0; i < lista.size(); i++) {
                animal = (Animal) lista.get(i);
                AnimalAModelo(animal);
            }
            this.jTableVaca.setFocusable(false);
            this.jTableVaca.setModel(modeloAnimal);
            this.LblTotal.setText("Total de animales " + this.jTableVaca.getRowCount());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar los datos: " + e.toString());
        }

    }

    private void getCombo() {
        List<Tipoganaderia> lista;
        TipoganaderiaJpaController controlador = new TipoganaderiaJpaController();
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        modelo.addElement("Todas");
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

    public void ParcelaAModelo(Parcela parcela) {
        Object[] objeto = new Object[3];
        objeto[0] = parcela.getNombre();
        objeto[1] = parcela.getTamano();
        objeto[2] = parcela.getLugarSituada();
        modeloParcela.addRow(objeto);
        listaParcela.add(parcela);
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

    public void AlimentoAModelo(Alimentos alimentos) {
        Object[] objeto = new Object[5];
        objeto[0] = alimentos.getNombre();
        objeto[1] = alimentos.getCantidad();
        objeto[2] = new SimpleDateFormat("dd MMM yyyy").format(new Date(alimentos.getFechaCompra().getTime()));
        objeto[3] = alimentos.getPrecio();
        objeto[4] = alimentos.getFkAlimentos();

        modeloAlimentos.addRow(objeto);
        listaAlimentos.add(alimentos);
    }

    public void FacturaAModelo(Factura factura) {
        Object[] objeto = new Object[6];
        objeto[0] = factura.getIdFactura();
        objeto[1] = factura.getFkCliente().getNombre();
        objeto[2] = factura.getPrecioTotal();
        objeto[3] = factura.getIva();
        objeto[4] = new SimpleDateFormat("dd MMM yyyy").format(new Date(factura.getFechaVenta().getTime()));
        objeto[5] = factura.getFkdatosNegocio().getNombre();
        modeloFactura.addRow(objeto);
        listaFactura.add(factura);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popMenuTablaAnimal = new javax.swing.JPopupMenu();
        MnParcela = new javax.swing.JMenuItem();
        MnAsignarEnfermedad = new javax.swing.JMenuItem();
        MnVacunar = new javax.swing.JMenuItem();
        MnVenta = new javax.swing.JMenuItem();
        popMenuTablaParcela = new javax.swing.JPopupMenu();
        MnBorrarParcela = new javax.swing.JMenuItem();
        popMenuTablaMedicamento = new javax.swing.JPopupMenu();
        MnBorrarMedicamento = new javax.swing.JMenuItem();
        jPopupMenuTablaAlimento = new javax.swing.JPopupMenu();
        MnBorrarAlimento = new javax.swing.JMenuItem();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        lblListado = new javax.swing.JLabel();
        BtAnadir = new javax.swing.JButton();
        LblTotal = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        TxNombre = new javax.swing.JTextField();
        BtBusqueda = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTableVaca = new javax.swing.JTable();
        jLabel17 = new javax.swing.JLabel();
        CbTipoGanaderia = new javax.swing.JComboBox<>();
        jCheckTotales = new javax.swing.JCheckBox();
        jLabel10 = new javax.swing.JLabel();
        TxParcelaAnimal = new javax.swing.JTextField();
        BtBusquedaParcelaAnimal = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableFacturas = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        LblFacturas = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        LblAlimento = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        LblMedicamento = new javax.swing.JLabel();
        BtAlimento = new javax.swing.JButton();
        BtMedicamento = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTableMedicamento = new javax.swing.JTable();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTableAlimento = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        BtParcela = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableParcela = new javax.swing.JTable();
        lblParcelas = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtParcela = new javax.swing.JTextField();
        BtBusquedaParcela = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        MnDatosNegocio = new javax.swing.JMenuItem();
        MnEnfermedades = new javax.swing.JMenuItem();
        MnClientes = new javax.swing.JMenuItem();
        MnTipoGanaderia = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();

        MnParcela.setText("Mover de parcela");
        MnParcela.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnParcelaActionPerformed(evt);
            }
        });
        popMenuTablaAnimal.add(MnParcela);

        MnAsignarEnfermedad.setText("Asignar Enfermedad");
        MnAsignarEnfermedad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnAsignarEnfermedadActionPerformed(evt);
            }
        });
        popMenuTablaAnimal.add(MnAsignarEnfermedad);

        MnVacunar.setText("Vacunar");
        MnVacunar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnVacunarActionPerformed(evt);
            }
        });
        popMenuTablaAnimal.add(MnVacunar);

        MnVenta.setText("Vender");
        MnVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnVentaActionPerformed(evt);
            }
        });
        popMenuTablaAnimal.add(MnVenta);

        MnBorrarParcela.setText("Borrar");
        MnBorrarParcela.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnBorrarParcelaActionPerformed(evt);
            }
        });
        popMenuTablaParcela.add(MnBorrarParcela);

        MnBorrarMedicamento.setText("Borrar");
        MnBorrarMedicamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnBorrarMedicamentoActionPerformed(evt);
            }
        });
        popMenuTablaMedicamento.add(MnBorrarMedicamento);

        MnBorrarAlimento.setText("Borrar");
        MnBorrarAlimento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnBorrarAlimentoActionPerformed(evt);
            }
        });
        jPopupMenuTablaAlimento.add(MnBorrarAlimento);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTabbedPane1.setToolTipText("");

        lblListado.setText("Listado de animales actuales");

        BtAnadir.setText("Añadir Animal");
        BtAnadir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtAnadirMouseClicked(evt);
            }
        });
        BtAnadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtAnadirActionPerformed(evt);
            }
        });

        LblTotal.setText("Total de animales");

        jLabel11.setText("Ingrese el nombre del animal");

        BtBusqueda.setText("Buscar");
        BtBusqueda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtBusquedaActionPerformed(evt);
            }
        });

        jTableVaca.setModel(new javax.swing.table.DefaultTableModel(
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
        jTableVaca.setComponentPopupMenu(popMenuTablaAnimal);
        jTableVaca.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableVacaMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(jTableVaca);

        jLabel17.setText("Tipo de ganaderia");

        CbTipoGanaderia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jCheckTotales.setText("Animales dados de baja");
        jCheckTotales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckTotalesActionPerformed(evt);
            }
        });

        jLabel10.setText("Animales en parcela");

        BtBusquedaParcelaAnimal.setText("Buscar");
        BtBusquedaParcelaAnimal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtBusquedaParcelaAnimalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 1057, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(jLabel17)
                    .addComponent(lblListado)
                    .addComponent(jLabel10))
                .addGap(42, 42, 42)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TxNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(TxParcelaAnimal, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(CbTipoGanaderia, javax.swing.GroupLayout.Alignment.LEADING, 0, 130, Short.MAX_VALUE)))
                .addGap(64, 64, 64)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckTotales)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(BtBusquedaParcelaAnimal, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
                        .addComponent(BtBusqueda, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BtAnadir, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(229, 229, 229)
                .addComponent(LblTotal)
                .addGap(93, 93, 93))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(TxNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtBusqueda))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtBusquedaParcelaAnimal)
                    .addComponent(TxParcelaAnimal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jCheckTotales)
                    .addComponent(CbTipoGanaderia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(lblListado)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(LblTotal)
                        .addGap(139, 139, 139))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(BtAnadir, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(101, 101, 101))))
        );

        jTabbedPane1.addTab("Animales", jPanel1);

        jLabel3.setText("Ventas realizadas ultimo año");

        jLabel4.setText("Numero total de ingresos recibidos");

        jLabel5.setText("Beneficios Totales");

        jLabel6.setText("Clientes con mas compras");

        jLabel7.setText("Cantidad impuestos ultimo año");

        jLabel8.setText("Beneficios por vaca");

        jLabel9.setText("Cantidad de becerros para vender");

        jTableFacturas.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jTableFacturas);

        jLabel13.setText("Historial de facturas");

        LblFacturas.setText("Total");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(103, 103, 103)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel4))
                                .addGap(309, 309, 309)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8)))
                            .addComponent(jLabel6)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1028, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(30, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(LblFacturas)
                .addGap(73, 73, 73))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel7))
                .addGap(36, 36, 36)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel9))
                .addGap(47, 47, 47)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel8))
                .addGap(42, 42, 42)
                .addComponent(jLabel6)
                .addGap(35, 35, 35)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LblFacturas)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Ventas", jPanel3);

        jLabel12.setText("Alimento en los almacenes");

        LblAlimento.setText("Total");

        jLabel14.setText("Medicamento actual");

        LblMedicamento.setText("Total");

        BtAlimento.setText("Añadir alimento");
        BtAlimento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtAlimentoActionPerformed(evt);
            }
        });

        BtMedicamento.setText("Añadir medicamento");
        BtMedicamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtMedicamentoActionPerformed(evt);
            }
        });

        jTableMedicamento.setModel(new javax.swing.table.DefaultTableModel(
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
        jTableMedicamento.setComponentPopupMenu(popMenuTablaMedicamento);
        jScrollPane4.setViewportView(jTableMedicamento);

        jTableAlimento.setModel(new javax.swing.table.DefaultTableModel(
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
        jTableAlimento.setComponentPopupMenu(jPopupMenuTablaAlimento);
        jScrollPane6.setViewportView(jTableAlimento);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(jLabel14)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 973, Short.MAX_VALUE)
                        .addComponent(jScrollPane6)))
                .addContainerGap(57, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(180, 180, 180)
                .addComponent(BtAlimento, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 244, Short.MAX_VALUE)
                .addComponent(BtMedicamento, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(175, 175, 175))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(LblMedicamento)
                        .addGap(101, 101, 101))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(LblAlimento)
                        .addGap(73, 73, 73))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel12)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(LblAlimento)
                .addGap(35, 35, 35)
                .addComponent(jLabel14)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(LblMedicamento)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtAlimento, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtMedicamento, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(67, 67, 67))
        );

        jTabbedPane1.addTab("Gastos", jPanel2);

        jLabel16.setText("Parcelas disponibles");

        BtParcela.setText("Añadir una nueva parcela");
        BtParcela.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtParcelaActionPerformed(evt);
            }
        });

        jTableParcela.setModel(new javax.swing.table.DefaultTableModel(
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
        jTableParcela.setComponentPopupMenu(popMenuTablaParcela);
        jScrollPane1.setViewportView(jTableParcela);

        lblParcelas.setText("Total de parcelas ");

        jLabel2.setText("Lugar donde esta situada");

        BtBusquedaParcela.setText("Buscar");
        BtBusquedaParcela.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtBusquedaParcelaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1007, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(68, 68, 68)
                        .addComponent(txtParcela, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70)
                        .addComponent(BtBusquedaParcela, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(44, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(BtParcela, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(397, 397, 397))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(lblParcelas)
                        .addGap(86, 86, 86))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtParcela, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtBusquedaParcela))
                .addGap(37, 37, 37)
                .addComponent(jLabel16)
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblParcelas)
                .addGap(14, 14, 14)
                .addComponent(BtParcela, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Parcelas", jPanel4);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("MUUP");

        jMenu1.setText("Añadir");

        MnDatosNegocio.setText("Datos negocio");
        MnDatosNegocio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDatosNegocioActionPerformed(evt);
            }
        });
        jMenu1.add(MnDatosNegocio);

        MnEnfermedades.setText("Enfermedades");
        MnEnfermedades.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnEnfermedadesActionPerformed(evt);
            }
        });
        jMenu1.add(MnEnfermedades);

        MnClientes.setText("Clientes");
        MnClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnClientesActionPerformed(evt);
            }
        });
        jMenu1.add(MnClientes);

        MnTipoGanaderia.setText("Tipo de ganaderia");
        MnTipoGanaderia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnTipoGanaderiaActionPerformed(evt);
            }
        });
        jMenu1.add(MnTipoGanaderia);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Ayuda");

        jMenuItem1.setText("Guía rapida");
        jMenu2.add(jMenuItem1);

        jMenuItem2.setText("Manual");
        jMenu2.add(jMenuItem2);

        jMenuItem3.setText("Informacion aplicacón");
        jMenu2.add(jMenuItem3);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addGap(19, 19, 19))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(526, 526, 526))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(43, 43, 43)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 642, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtBusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtBusquedaActionPerformed
        // TODO add your handling code here:
        modeloAnimal.setRowCount(0);
        listaAnimal.clear();
        List lista;
        AnimalJpaController controlador = new AnimalJpaController();
        try {
            lista = controlador.AnimalesNombre(TxNombre.getText());
            //lista = controlador.findAnimalEntities();
            for (int i = 0; i < lista.size(); i++) {
                animal = (Animal) lista.get(i);
                AnimalAModelo(animal);
            }
            this.jTableVaca.setFocusable(false);
            this.jTableVaca.setModel(modeloAnimal);
            this.LblTotal.setText("Número total de animales " + this.jTableVaca.getRowCount());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar los datos: " + e.toString());
        }
        this.lblListado.setText("Resultado de la busqueda");

    }//GEN-LAST:event_BtBusquedaActionPerformed

    private void BtAnadirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtAnadirMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_BtAnadirMouseClicked

    private void BtAnadirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtAnadirActionPerformed
        // TODO add your handling code here:

        FormAnimal vacaForm = new FormAnimal(this, true, new Date());
        vacaForm.setVisible(true);
        if (vacaForm.creado == true) {
            AnimalJpaController animalController = new AnimalJpaController();
            animalController.create(vacaForm.animal);
            modeloAnimal.setRowCount(0);
            getTablaActualAnimal();
            this.jCheckTotales.setSelected(false);
        }

    }//GEN-LAST:event_BtAnadirActionPerformed


    private void jTableVacaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableVacaMouseClicked

    }//GEN-LAST:event_jTableVacaMouseClicked

    private void jCheckTotalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckTotalesActionPerformed
        // TODO add your handling code here:
        if (jCheckTotales.isSelected()) {

            modeloAnimal.setRowCount(0);
            if (CbTipoGanaderia.getSelectedItem().toString().equals("Todas")) {
                getTablaBajaAnimal();
            } else {
                getTablaTipoAnimal(CbTipoGanaderia.getSelectedItem().toString());
            }
            this.lblListado.setText("Listado de animales de baja");
        } else if (jCheckTotales.isSelected() == false) {

            modeloAnimal.setRowCount(0);
            if (CbTipoGanaderia.getSelectedItem().toString().equals("Todas")) {
                getTablaActualAnimal();
            } else {

                getTablaTipoActualAnimal(CbTipoGanaderia.getSelectedItem().toString());
            }
            this.lblListado.setText("Listado de animales actuales");
        }
    }//GEN-LAST:event_jCheckTotalesActionPerformed

    private void BtParcelaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtParcelaActionPerformed
        // TODO add your handling code here:
        FormParcela formParcela = new FormParcela(this, true);
        formParcela.setVisible(true);
        if (formParcela.creado == true) {
            ParcelaJpaController parcelaController = new ParcelaJpaController();
            parcelaController.create(formParcela.parcela);
            modeloParcela.setRowCount(0);
            getTablaTotalParcela();

        }


    }//GEN-LAST:event_BtParcelaActionPerformed

    private void MnBorrarParcelaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnBorrarParcelaActionPerformed
        // TODO add your handling code here:
        parcela = listaParcela.get(this.jTableParcela.getSelectedRow());
        try {
            ParcelaJpaController parcelaController = new ParcelaJpaController();
            parcelaController.destroy(parcela.getIdParcela());
            modeloParcela.setRowCount(0);
            getTablaTotalParcela();
        } catch (IllegalOrphanException ex) {
            Logger.getLogger(Formulario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(Formulario.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_MnBorrarParcelaActionPerformed

    private void BtBusquedaParcelaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtBusquedaParcelaActionPerformed
        // TODO add your handling code here:
        modeloParcela.setRowCount(0);
        listaParcela.clear();
        List lista;
        ParcelaJpaController parcelaController = new ParcelaJpaController();
        try {
            lista = parcelaController.ParcelasNombre(txtParcela.getText());
            //lista = controlador.findAnimalEntities();
            for (int i = 0; i < lista.size(); i++) {
                parcela = (Parcela) lista.get(i);
                ParcelaAModelo(parcela);
            }
            this.jTableParcela.setFocusable(false);
            this.jTableParcela.setModel(modeloParcela);
            this.lblParcelas.setText("Total de parcelas  " + this.jTableParcela.getRowCount());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar los datos: " + e.toString());
        }


    }//GEN-LAST:event_BtBusquedaParcelaActionPerformed

    private void MnEnfermedadesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnEnfermedadesActionPerformed
        // TODO add your handling code here:
        FormEnfermedad formEnfermedad = new FormEnfermedad(this, true);
        formEnfermedad.setVisible(true);


    }//GEN-LAST:event_MnEnfermedadesActionPerformed

    private void MnAsignarEnfermedadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnAsignarEnfermedadActionPerformed
        // TODO add your handling code here:

        Animal[] animalesEscogidos = recogerAnimalesRows();
        FormAsignarEnfermedad formAsignarEnfermedad = new FormAsignarEnfermedad(this, true, animalesEscogidos, new Date());
        formAsignarEnfermedad.setVisible(true);

        if (formAsignarEnfermedad.creado == true) {
            try {
                AnimalJpaController animalController = new AnimalJpaController();
                for (int i = 0; i < animalesEscogidos.length; i++) {
                    animalController.edit(formAsignarEnfermedad.animal[i]);
                }

                modeloAnimal.setRowCount(0);
                getTablaActualAnimal();
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(Formulario.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(Formulario.class.getName()).log(Level.SEVERE, null, ex);
            }

        }


    }//GEN-LAST:event_MnAsignarEnfermedadActionPerformed

    private void MnParcelaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnParcelaActionPerformed
        // TODO add your handling code here:

        Animal[] animalesEscogidos = recogerAnimalesRows();
        FormAsignarParcela formAsignarParcela = new FormAsignarParcela(this, true, animalesEscogidos);
        formAsignarParcela.setVisible(true);

        if (formAsignarParcela.creado == true) {
            try {
                AnimalJpaController animalController = new AnimalJpaController();
                for (int i = 0; i < animalesEscogidos.length; i++) {
                    animalController.edit(formAsignarParcela.animal[i]);
                }

                modeloAnimal.setRowCount(0);
                getTablaActualAnimal();
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(Formulario.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(Formulario.class.getName()).log(Level.SEVERE, null, ex);
            }

        }


    }//GEN-LAST:event_MnParcelaActionPerformed

    private void BtBusquedaParcelaAnimalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtBusquedaParcelaAnimalActionPerformed
        // TODO add your handling code here:

        modeloAnimal.setRowCount(0);
        listaAnimal.clear();
        List lista;
        AnimalJpaController controlador = new AnimalJpaController();
        try {
            lista = controlador.AnimalesActualesParcelas(TxParcelaAnimal.getText());
            //lista = controlador.findAnimalEntities();
            for (int i = 0; i < lista.size(); i++) {
                animal = (Animal) lista.get(i);
                AnimalAModelo(animal);
            }
            this.jTableVaca.setFocusable(false);
            this.jTableVaca.setModel(modeloAnimal);
            this.LblTotal.setText("Número total de animales " + this.jTableVaca.getRowCount());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar los datos: " + e.toString());
        }
        this.lblListado.setText("Resultado de la busqueda");


    }//GEN-LAST:event_BtBusquedaParcelaAnimalActionPerformed

    private void BtMedicamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtMedicamentoActionPerformed
        // TODO add your handling code here:
        FormMedicamento formMedicamento = new FormMedicamento(this, true);
        formMedicamento.setVisible(true);
        if (formMedicamento.creado == true) {
            MedicamentoJpaController medicamentoController = new MedicamentoJpaController();
            medicamentoController.create(formMedicamento.medicamento);
            modeloMedicamento.setRowCount(0);
            getTablaTotalMedicamento();

        }


    }//GEN-LAST:event_BtMedicamentoActionPerformed

    private void MnBorrarMedicamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnBorrarMedicamentoActionPerformed
        // TODO add your handling code here:

        medicamento = listaMedicamento.get(this.jTableMedicamento.getSelectedRow());
        try {
            MedicamentoJpaController medicamentoController = new MedicamentoJpaController();
            medicamentoController.destroy(medicamento.getIdMedicamento());
            modeloMedicamento.setRowCount(0);
            getTablaTotalMedicamento();
        } catch (IllegalOrphanException ex) {
            Logger.getLogger(Formulario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(Formulario.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_MnBorrarMedicamentoActionPerformed

    private void MnVacunarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnVacunarActionPerformed
        // TODO add your handling code here:
        Animal[] animalesEscogidos = recogerAnimalesRows();
        FormAsignarMedicamento formAsignarMedicamento = new FormAsignarMedicamento(this, true, animalesEscogidos, new Date());
        formAsignarMedicamento.setVisible(true);

        if (formAsignarMedicamento.creado == true) {
            try {
                AnimalJpaController animalController = new AnimalJpaController();
                for (int i = 0; i < animalesEscogidos.length; i++) {
                    animalController.edit(formAsignarMedicamento.animal[i]);
                }

                modeloMedicamento.setRowCount(0);
                getTablaTotalMedicamento();
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(Formulario.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(Formulario.class.getName()).log(Level.SEVERE, null, ex);
            }

        }


    }//GEN-LAST:event_MnVacunarActionPerformed

    private void MnDatosNegocioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDatosNegocioActionPerformed
        // TODO add your handling code here:
        FormDatosNegocio formDatosNegocio = new FormDatosNegocio(this, true);
        formDatosNegocio.setVisible(true);


    }//GEN-LAST:event_MnDatosNegocioActionPerformed

    private void MnClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnClientesActionPerformed
        // TODO add your handling code here:
        FormCliente formCliente = new FormCliente(this, true);
        formCliente.setVisible(true);
    }//GEN-LAST:event_MnClientesActionPerformed

    private void BtAlimentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtAlimentoActionPerformed
        // TODO add your handling code here:

        FormAlimento formAlimento = new FormAlimento(this, true);
        formAlimento.setVisible(true);
        if (formAlimento.creado == true) {
            AlimentosJpaController alimentoController = new AlimentosJpaController();
            alimentoController.create(formAlimento.alimento);
            modeloAlimentos.setRowCount(0);
            getTablaTotalAlimento();

        }


    }//GEN-LAST:event_BtAlimentoActionPerformed

    private void MnBorrarAlimentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnBorrarAlimentoActionPerformed
        try {
            // TODO add your handling code here:
            alimento = listaAlimentos.get(this.jTableAlimento.getSelectedRow());

            AlimentosJpaController alimentoController = new AlimentosJpaController();
            alimentoController.destroy(alimento.getIdAlimentos());
            modeloAlimentos.setRowCount(0);
            getTablaTotalAlimento();
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(Formulario.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_MnBorrarAlimentoActionPerformed

    private void MnVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnVentaActionPerformed
        // TODO add your handling code here:

        Animal[] animalesEscogidos = recogerAnimalesRows();
        FormVenta formVenta = new FormVenta(this, true, animalesEscogidos, new Date());
        formVenta.setVisible(true);

        modeloFactura.setRowCount(0);
        getTablaTotalFactura();


    }//GEN-LAST:event_MnVentaActionPerformed

    private void MnTipoGanaderiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnTipoGanaderiaActionPerformed
        // TODO add your handling code here:
        FormTipoGanaderia formTipoGanaderia = new FormTipoGanaderia(this, true);
        formTipoGanaderia.setVisible(true);
        getCombo();
    }//GEN-LAST:event_MnTipoGanaderiaActionPerformed

    public Animal[] recogerAnimalesRows() {
        int[] numIndices = this.jTableVaca.getSelectedRows();
        Animal[] animalesEscogidos = new Animal[numIndices.length];
        for (int i = 0; i < numIndices.length; i++) {
            animalesEscogidos[i] = listaAnimal.get(numIndices[i]);
        }
        return animalesEscogidos;
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
            java.util.logging.Logger.getLogger(Formulario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Formulario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Formulario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Formulario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Formulario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtAlimento;
    private javax.swing.JButton BtAnadir;
    private javax.swing.JButton BtBusqueda;
    private javax.swing.JButton BtBusquedaParcela;
    private javax.swing.JButton BtBusquedaParcelaAnimal;
    private javax.swing.JButton BtMedicamento;
    private javax.swing.JButton BtParcela;
    private javax.swing.JComboBox<String> CbTipoGanaderia;
    private javax.swing.JLabel LblAlimento;
    private javax.swing.JLabel LblFacturas;
    private javax.swing.JLabel LblMedicamento;
    private javax.swing.JLabel LblTotal;
    private javax.swing.JMenuItem MnAsignarEnfermedad;
    private javax.swing.JMenuItem MnBorrarAlimento;
    private javax.swing.JMenuItem MnBorrarMedicamento;
    private javax.swing.JMenuItem MnBorrarParcela;
    private javax.swing.JMenuItem MnClientes;
    private javax.swing.JMenuItem MnDatosNegocio;
    private javax.swing.JMenuItem MnEnfermedades;
    private javax.swing.JMenuItem MnParcela;
    private javax.swing.JMenuItem MnTipoGanaderia;
    private javax.swing.JMenuItem MnVacunar;
    private javax.swing.JMenuItem MnVenta;
    private javax.swing.JTextField TxNombre;
    private javax.swing.JTextField TxParcelaAnimal;
    private javax.swing.JCheckBox jCheckTotales;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPopupMenu jPopupMenuTablaAlimento;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTableAlimento;
    private javax.swing.JTable jTableFacturas;
    private javax.swing.JTable jTableMedicamento;
    private javax.swing.JTable jTableParcela;
    private javax.swing.JTable jTableVaca;
    private javax.swing.JLabel lblListado;
    private javax.swing.JLabel lblParcelas;
    private javax.swing.JPopupMenu popMenuTablaAnimal;
    private javax.swing.JPopupMenu popMenuTablaMedicamento;
    private javax.swing.JPopupMenu popMenuTablaParcela;
    private javax.swing.JTextField txtParcela;
    // End of variables declaration//GEN-END:variables
}

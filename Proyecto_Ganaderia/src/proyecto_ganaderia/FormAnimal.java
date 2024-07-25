/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_ganaderia;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelos.Animal;
import modelos.Animalmedicamento;
import modelos.AnimalmedicamentoJpaController;
import modelos.Enfermedad;
import modelos.Historialenf;
import modelos.HistorialenfJpaController;
import modelos.Historialparcela;
import modelos.HistorialparcelaJpaController;
import modelos.Tipoganaderia;
import modelos.TipoganaderiaJpaController;

/**
 *
 * @author jvifdz-GRANDE
 */
public class FormAnimal extends javax.swing.JDialog {

    /**
     * Creates new form VacaForm
     */
    public Animal animal;
    Tipoganaderia tipoGanaderia;
    List<Tipoganaderia> lista;
    public boolean creado = false;

    List<Historialenf> listaHistorial = new ArrayList<>();
    String[] columnasHistorialEnfermedad = {"Enfermedad", "Fecha"};
    DefaultTableModel modeloHistorialEnfermedad = new DefaultTableModel(columnasHistorialEnfermedad, 0);

    List<Historialparcela> listahistorialParcela = new ArrayList<>();
    String[] columnasHistorialParcela = {"Parcela", "Lugar situada", "Fecha Entrada", "Fecha Salida"};
    DefaultTableModel modeloHistorialParcela = new DefaultTableModel(columnasHistorialParcela, 0);

    List<Animalmedicamento> listaAnimalMedicamento = new ArrayList<>();
    String[] columnasAnimalMedicamento = {"Medicamento", "Fecha Suministrado", "Numero de dosis"};
    DefaultTableModel modeloAnimalMedicamento = new DefaultTableModel(columnasAnimalMedicamento, 0);

    public FormAnimal(java.awt.Frame parent, boolean modal, java.util.Date fechaMaxima) {
        super(parent, modal);
        initComponents();

        this.jDatefechaNacimiento.setMaxSelectableDate(fechaMaxima);
        this.jDateBaja.setMaxSelectableDate(fechaMaxima);
        this.jDateApareamiento.setEnabled(false);
        this.TxFechaParto.setEditable(false);
        this.setLocationRelativeTo(null);
        this.TxFechaParto.setEnabled(false);
        this.MnGrande.setEnabled(false);
        this.MnOperacones.setEnabled(false);
        animal = new Animal();
        getCombo();
        this.setResizable(false);

    }

    public FormAnimal(java.awt.Frame parent, boolean modal, Animal animal, java.util.Date fechaMaxima) {
        super(parent, modal);
        initComponents();
        this.setResizable(false);
        this.TxDai.setEditable(false);
        this.TxMadre.setEditable(false);
        this.TxNombre.setEditable(false);
        this.TxRazonBaja.setEditable(false);


        this.btConfirmar.setText("Editar");
        this.jDatefechaNacimiento.setMaxSelectableDate(fechaMaxima);
        this.jDateBaja.setMaxSelectableDate(fechaMaxima);
        this.jDateApareamiento.setMaxSelectableDate(fechaMaxima);
        this.jTableHistorialEnf.setDefaultEditor(Object.class, null);
        this.jTableHistorialParcela.setDefaultEditor(Object.class, null);

        this.animal = animal;
        this.TxNombre.setText(animal.getNombre());
        this.TxDai.setText(animal.getDai());
        this.jDatefechaNacimiento.setDate(this.animal.getFechaNacimiento());
        this.TxFechaParto.setEditable(false);
        this.TxMadre.setText(animal.getDaiMadre());

        getComboEspecifico();
        getTablaHistorialEnfermedadDesc(this.animal.getIdAnimal());
        getTablaHistorialParcelaDesc(this.animal.getIdAnimal());
        getTablaHistorialMedicamento(this.animal.getIdAnimal());
        this.CbTipoGanaderia.getEditor().setItem(animal.getFktipoGanaderia());

        if (this.animal.getFechaApareamiento() != null) {
            String fecha = new SimpleDateFormat("YYYY-MM-dd").format(new java.util.Date(this.animal.getFechaApareamiento().getTime()));
            LocalDate date = LocalDate.parse(fecha);
            date = date.plusMonths(this.animal.getFktipoGanaderia().getTiempoGestacion());
            this.jDateApareamiento.setDate(this.animal.getFechaApareamiento());
            this.TxFechaParto.setText(date.format(DateTimeFormatter.ofPattern("dd MMM yyyy")));

        } else {
            this.TxFechaParto.setText("Sin fecha de apareamiento");
        }

        this.TxRazonBaja.setText(this.animal.getRazonBaja());
        this.jDateBaja.setDate(this.animal.getFechaBaja());

        if (this.animal.getRazonBaja() == null && this.animal.getSexo().equals("Macho") == false) {
            this.jDateApareamiento.setEnabled(true);
        } else {
            this.jDateApareamiento.setEnabled(false);
        }

        this.MnEditar.setSelected(false);

        this.setLocationRelativeTo(null);
    }

    private void getCombo() {
        TipoganaderiaJpaController controlador = new TipoganaderiaJpaController();
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();

        try {
            lista = controlador.findTipoganaderiaEntities();
            for (int i = 0; i < lista.size(); i++) {
                modelo.addElement(lista.get(i));

            }
            this.CbTipoGanaderia.setFocusable(false);
            this.CbTipoGanaderia.setModel(modelo);
            cargaComboSexo();
        } catch (Exception e) {

            JOptionPane.showMessageDialog(this, "Error al cargar los datos: " + e.toString());

        }

    }

    private void getTablaHistorialEnfermedadDesc(int id) {
        listaHistorial.clear();
        List<Historialenf> lista;
        HistorialenfJpaController controlador = new HistorialenfJpaController();
        try {
            lista = controlador.historialEnfermedadAnimalDesc(id);

            for (int i = 0; i < lista.size(); i++) {

                HistorialEnfermedadAModelo(lista.get(i));
            }
            this.jTableHistorialEnf.setFocusable(false);
            this.jTableHistorialEnf.setModel(modeloHistorialEnfermedad);
            //this.LblTotal.setText("Número total de animales " + this.jTableVaca.getRowCount());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar los datos: " + e.toString());
        }

    }

    public void HistorialEnfermedadAModelo(Historialenf historialEnf) {
        Object[] objeto = new Object[2];
        objeto[0] = historialEnf.getFkEnfermedad().getNombre();
        objeto[1] = new SimpleDateFormat("dd MMM yyyy").format(new java.util.Date(historialEnf.getFechaEnfermedad().getTime()));

        modeloHistorialEnfermedad.addRow(objeto);
        listaHistorial.add(historialEnf);
    }

    private void getTablaHistorialParcelaDesc(int id) {
        listahistorialParcela.clear();
        List<Historialparcela> lista;
        HistorialparcelaJpaController controlador = new HistorialparcelaJpaController();
        try {
            lista = controlador.historialParcelaAnimalDesc(id);

            for (int i = 0; i < lista.size(); i++) {
                HistorialParcelaAModelo(lista.get(i));
            }
            this.jTableHistorialParcela.setFocusable(false);
            this.jTableHistorialParcela.setModel(modeloHistorialParcela);
            //this.LblTotal.setText("Número total de animales " + this.jTableVaca.getRowCount());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar los datos: " + e.toString());
        }

    }

    private void getTablaHistorialMedicamento(int id) {
        listaAnimalMedicamento.clear();
        List<Animalmedicamento> lista;
        AnimalmedicamentoJpaController controlador = new AnimalmedicamentoJpaController();
        try {
            lista = controlador.AnimalMedicamentoDesc(id);

            for (int i = 0; i < lista.size(); i++) {
                HistorialMedicamentoAModelo(lista.get(i));
            }
            this.jTableHistorialMedicamento.setFocusable(false);
            this.jTableHistorialMedicamento.setModel(modeloAnimalMedicamento);
            //this.LblTotal.setText("Número total de animales " + this.jTableVaca.getRowCount());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar los datos: " + e.toString());
        }

    }

    public void HistorialMedicamentoAModelo(Animalmedicamento historialMedicamento) {

        Object[] objeto = new Object[3];
        objeto[0] = historialMedicamento.getFkMedicamento().getNombre();
        objeto[1] = new SimpleDateFormat("dd MMM yyyy").format(new java.util.Date(historialMedicamento.getFechaVacunacion().getTime()));
        objeto[2] = historialMedicamento.getNumDosis();

        modeloAnimalMedicamento.addRow(objeto);
        listaAnimalMedicamento.add(historialMedicamento);
    }

    public void HistorialParcelaAModelo(Historialparcela historialParcela) {

        Object[] objeto = new Object[4];
        objeto[0] = historialParcela.getFkParcela().getNombre();
        objeto[1] = historialParcela.getFkParcela().getLugarSituada();
        objeto[2] = new SimpleDateFormat("dd MMM yyyy").format(new java.util.Date(historialParcela.getFechaEntrada().getTime()));
        if (historialParcela.getFechaSalida() != null) {
            objeto[3] = new SimpleDateFormat("dd MMM yyyy").format(new java.util.Date(historialParcela.getFechaSalida().getTime()));
        } else {
            objeto[3] = "Actualmente en esta parcela";
        }
        modeloHistorialParcela.addRow(objeto);
        listahistorialParcela.add(historialParcela);
    }

    private void cargaComboSexo() {
        this.CbSexo.removeAllItems();
        this.CbSexo.addItem("Macho");
        this.CbSexo.addItem("Hembra");
    }

    private void getComboEspecifico() {
        TipoganaderiaJpaController controlador = new TipoganaderiaJpaController();
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();

        cargaComboSexo();
        try {
            lista = controlador.findTipoganaderiaEntities();
            for (int i = 0; i < lista.size(); i++) {
                modelo.addElement(lista.get(i));
            }

            this.CbTipoGanaderia.setFocusable(false);
            this.CbTipoGanaderia.setModel(modelo);

            for (int i = 0; i < lista.size(); i++) {
                if (lista.get(i).getIdtipoGanaderia().equals(this.animal.getFktipoGanaderia().getIdtipoGanaderia())) {
                    this.CbTipoGanaderia.setSelectedIndex(i);
                }
            }
            for (int i = 0; i < CbSexo.getModel().getSize(); i++) {
                if (CbSexo.getModel().getElementAt(i).equals(this.animal.getSexo())) {
                    this.CbSexo.setSelectedIndex(i);
                }

            }

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

        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        TxMadre = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        TxFechaParto = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        TxRazonBaja = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        TxNombre = new javax.swing.JTextField();
        CbTipoGanaderia = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        btConfirmar = new javax.swing.JButton();
        lblNombre = new javax.swing.JLabel();
        TxDai = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jDatefechaNacimiento = new com.toedter.calendar.JDateChooser();
        jDateApareamiento = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        CbSexo = new javax.swing.JComboBox<>();
        jDateBaja = new com.toedter.calendar.JDateChooser();
        btCancelar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableHistorialEnf = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableHistorialParcela = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableHistorialMedicamento = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        MnGrande = new javax.swing.JMenuBar();
        MnOperacones = new javax.swing.JMenu();
        MnEditar = new javax.swing.JCheckBoxMenuItem();
        MnVerHijos = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel4.setText("Fecha Apareamiento");

        jLabel5.setText("Posible parto ");

        jLabel6.setText("Pendiente de la madre");

        jLabel7.setText("Pendiente");

        jLabel1.setText("Tipo de ganaderia");

        jLabel8.setText("Fecha baja animal");

        CbTipoGanaderia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel9.setText("Razón baja");

        btConfirmar.setText("Confirmar");
        btConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btConfirmarActionPerformed(evt);
            }
        });

        lblNombre.setText("Nombre Animal");

        jLabel3.setText("Fecha de nacimiento");

        jLabel2.setText("Sexo");

        CbSexo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btCancelar.setText("Cancelar");
        btCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelarActionPerformed(evt);
            }
        });

        jTableHistorialEnf.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTableHistorialEnf);

        jTableHistorialParcela.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jTableHistorialParcela);

        jLabel11.setText("Parcelas en las que esta el animal");

        jLabel12.setText("Enfermedades del animal");

        jTableHistorialMedicamento.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(jTableHistorialMedicamento);

        jLabel10.setText("Medicamentos");

        MnOperacones.setText("Operaciones");

        MnEditar.setSelected(true);
        MnEditar.setText("Editar");
        MnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnEditarActionPerformed(evt);
            }
        });
        MnOperacones.add(MnEditar);

        MnVerHijos.setText("Ver hijos");
        MnVerHijos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnVerHijosActionPerformed(evt);
            }
        });
        MnOperacones.add(MnVerHijos);

        MnGrande.add(MnOperacones);

        setJMenuBar(MnGrande);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(96, 96, 96)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(702, 705, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel8)
                                            .addComponent(lblNombre))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(TxNombre)
                                            .addComponent(jDateApareamiento, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                                            .addComponent(jDateBaja, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel7)
                                            .addComponent(jLabel2))
                                        .addGap(92, 92, 92)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(CbSexo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(TxDai))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel3)
                                                    .addComponent(jLabel1))
                                                .addGap(9, 9, 9))
                                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel9))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(19, 19, 19)
                                                .addComponent(CbTipoGanaderia, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(19, 19, 19)
                                                .addComponent(jDatefechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(TxFechaParto, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(TxMadre, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(TxRazonBaja, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                    .addComponent(jLabel5)))
                            .addComponent(jScrollPane3))
                        .addGap(99, 99, 99))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel11))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGap(255, 255, 255)
                .addComponent(btConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(159, 159, 159)
                .addComponent(btCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(CbTipoGanaderia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jDatefechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TxMadre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(jLabel5))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(TxFechaParto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(TxRazonBaja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(TxDai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(CbSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TxNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNombre))
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jDateApareamiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8)
                            .addComponent(jDateBaja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jLabel11)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel10)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel12)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btConfirmar)
                    .addComponent(btCancelar))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btConfirmarActionPerformed
        // TODO add your handling code here:

        if (this.TxDai.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Inserte DAI del animal", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (jDatefechaNacimiento.getDate() == null) {
            JOptionPane.showMessageDialog(null, "Inserte fecha de nacimiento del animal", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (this.TxNombre.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Inserte nombre del animal", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (this.TxMadre.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Inserte DAI de la madre", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        Date fechaNacimiento = new java.sql.Date(jDatefechaNacimiento.getDate().getTime());
        
        Date fechaApareamiento;
        Date fechaBaja;
        
        
        String nombre = this.TxNombre.getText();
        String dai = this.TxDai.getText().toUpperCase();
        String daiMadre = this.TxMadre.getText().toUpperCase();
        String razonBaja;
        if (jDateApareamiento.getDate() == null) {
            fechaApareamiento = null;
        } else {
            fechaApareamiento = new java.sql.Date(jDateApareamiento.getDate().getTime());
        }
        if (jDateBaja.getDate() == null) {
            fechaBaja = null;
        } else {

            fechaBaja = new java.sql.Date(jDateBaja.getDate().getTime());
            try {
                HistorialparcelaJpaController historialController = new HistorialparcelaJpaController();

                //cuando damos una fecha de baja al animal en su ultimo historial parcela le ponemos fechasalida
                int numeroParcela = animal.getHistorialparcelaList().size() - 1;
                animal.getHistorialparcelaList().get(numeroParcela).setFechaSalida(fechaBaja);
                historialController.edit(animal.getHistorialparcelaList().get(numeroParcela));
            } catch (Exception ex) {
                Logger.getLogger(FormAnimal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (TxRazonBaja.getText().equals("")) {
            razonBaja = null;
        } else {
            razonBaja = TxRazonBaja.getText();

        }

        animal.setDai(dai);
        animal.setNombre(nombre);
        animal.setFechaApareamiento(fechaApareamiento);
        animal.setFechaBaja(fechaBaja);
        animal.setFechaNacimiento(fechaNacimiento);
        animal.setFktipoGanaderia((Tipoganaderia) CbTipoGanaderia.getSelectedItem());
        animal.setDaiMadre(daiMadre);
        animal.setRazonBaja(razonBaja);
        animal.setSexo(this.CbSexo.getSelectedItem().toString());

        creado = true;
        this.dispose();

    }//GEN-LAST:event_btConfirmarActionPerformed

    private void btCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelarActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btCancelarActionPerformed

    private void MnVerHijosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnVerHijosActionPerformed
        // TODO add your handling code here:

        FormAnimalHijo formAnimalHijo = new FormAnimalHijo(new javax.swing.JDialog(), true, animal);
        formAnimalHijo.setVisible(true);


    }//GEN-LAST:event_MnVerHijosActionPerformed

    private void MnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnEditarActionPerformed
        // TODO add your handling code here:

        if (this.MnEditar.isSelected()) {
            this.TxDai.setEditable(true);
            this.TxMadre.setEditable(true);
            this.TxNombre.setEditable(true);
            this.TxRazonBaja.setEditable(true);
            

        } else {
            this.TxDai.setEditable(false);
            this.TxMadre.setEditable(false);
            this.TxNombre.setEditable(false);
            this.TxRazonBaja.setEditable(false);
            

        }
    }//GEN-LAST:event_MnEditarActionPerformed

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
            java.util.logging.Logger.getLogger(FormAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FormAnimal dialog = new FormAnimal(new javax.swing.JFrame(), true, new java.util.Date());
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
    private javax.swing.JComboBox<String> CbSexo;
    private javax.swing.JComboBox<String> CbTipoGanaderia;
    private javax.swing.JCheckBoxMenuItem MnEditar;
    private javax.swing.JMenuBar MnGrande;
    private javax.swing.JMenu MnOperacones;
    private javax.swing.JMenuItem MnVerHijos;
    private javax.swing.JTextField TxDai;
    private javax.swing.JTextField TxFechaParto;
    private javax.swing.JTextField TxMadre;
    private javax.swing.JTextField TxNombre;
    private javax.swing.JTextField TxRazonBaja;
    private javax.swing.JButton btCancelar;
    private javax.swing.JButton btConfirmar;
    private com.toedter.calendar.JDateChooser jDateApareamiento;
    private com.toedter.calendar.JDateChooser jDateBaja;
    private com.toedter.calendar.JDateChooser jDatefechaNacimiento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTableHistorialEnf;
    private javax.swing.JTable jTableHistorialMedicamento;
    private javax.swing.JTable jTableHistorialParcela;
    private javax.swing.JLabel lblNombre;
    // End of variables declaration//GEN-END:variables
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author jvifdz-GRANDE
 */
@Entity
@Table(name = "animal")
@NamedQueries({
    @NamedQuery(name = "Animal.findAll", query = "SELECT a FROM Animal a"),
    @NamedQuery(name = "Animal.findByIdAnimal", query = "SELECT a FROM Animal a WHERE a.idAnimal = :idAnimal"),
    @NamedQuery(name = "Animal.findByNombre", query = "SELECT a FROM Animal a WHERE a.nombre = :nombre"),
    @NamedQuery(name = "Animal.findByFechaNacimiento", query = "SELECT a FROM Animal a WHERE a.fechaNacimiento = :fechaNacimiento"),
    @NamedQuery(name = "Animal.findByFechaApareamiento", query = "SELECT a FROM Animal a WHERE a.fechaApareamiento = :fechaApareamiento"),
    @NamedQuery(name = "Animal.findByDaiMadre", query = "SELECT a FROM Animal a WHERE a.daiMadre = :daiMadre"),
    @NamedQuery(name = "Animal.findByDai", query = "SELECT a FROM Animal a WHERE a.dai = :dai"),
    @NamedQuery(name = "Animal.findByFechaBaja", query = "SELECT a FROM Animal a WHERE a.fechaBaja = :fechaBaja"),
    @NamedQuery(name = "Animal.findByRazonBaja", query = "SELECT a FROM Animal a WHERE a.razonBaja = :razonBaja"),
    @NamedQuery(name = "Animal.findBySexo", query = "SELECT a FROM Animal a WHERE a.sexo = :sexo")})
public class Animal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_animal")
    private Integer idAnimal;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "fechaNacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    @Column(name = "fechaApareamiento")
    @Temporal(TemporalType.DATE)
    private Date fechaApareamiento;
    @Column(name = "dai_madre")
    private String daiMadre;
    @Column(name = "dai")
    private String dai;
    @Column(name = "fechaBaja")
    @Temporal(TemporalType.DATE)
    private Date fechaBaja;
    @Column(name = "razonBaja")
    private String razonBaja;
    @Basic(optional = false)
    @Column(name = "sexo")
    private String sexo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkAnimal")
    private List<Animalmedicamento> animalmedicamentoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkAnimal")
    private List<Historialparcela> historialparcelaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkAnimal")
    private List<Detallefactura> detallefacturaList;
    @JoinColumn(name = "fk_tipoGanaderia", referencedColumnName = "id_tipoGanaderia")
    @ManyToOne(optional = false)
    private Tipoganaderia fktipoGanaderia;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkAnimal")
    private List<Historialenf> historialenfList;

    public Animal() {
    }

    public Animal(Integer idAnimal) {
        this.idAnimal = idAnimal;
    }

    public Animal(Integer idAnimal, String sexo) {
        this.idAnimal = idAnimal;
        this.sexo = sexo;
    }

    public Integer getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(Integer idAnimal) {
        this.idAnimal = idAnimal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Date getFechaApareamiento() {
        return fechaApareamiento;
    }

    public void setFechaApareamiento(Date fechaApareamiento) {
        this.fechaApareamiento = fechaApareamiento;
    }

    public String getDaiMadre() {
        return daiMadre;
    }

    public void setDaiMadre(String daiMadre) {
        this.daiMadre = daiMadre;
    }

    public String getDai() {
        return dai;
    }

    public void setDai(String dai) {
        this.dai = dai;
    }

    public Date getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    public String getRazonBaja() {
        return razonBaja;
    }

    public void setRazonBaja(String razonBaja) {
        this.razonBaja = razonBaja;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public List<Animalmedicamento> getAnimalmedicamentoList() {
        return animalmedicamentoList;
    }

    public void setAnimalmedicamentoList(List<Animalmedicamento> animalmedicamentoList) {
        this.animalmedicamentoList = animalmedicamentoList;
    }

    public List<Historialparcela> getHistorialparcelaList() {
        return historialparcelaList;
    }

    public void setHistorialparcelaList(List<Historialparcela> historialparcelaList) {
        this.historialparcelaList = historialparcelaList;
    }

    public List<Detallefactura> getDetallefacturaList() {
        return detallefacturaList;
    }

    public void setDetallefacturaList(List<Detallefactura> detallefacturaList) {
        this.detallefacturaList = detallefacturaList;
    }

    public Tipoganaderia getFktipoGanaderia() {
        return fktipoGanaderia;
    }

    public void setFktipoGanaderia(Tipoganaderia fktipoGanaderia) {
        this.fktipoGanaderia = fktipoGanaderia;
    }

    public List<Historialenf> getHistorialenfList() {
        return historialenfList;
    }

    public void setHistorialenfList(List<Historialenf> historialenfList) {
        this.historialenfList = historialenfList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAnimal != null ? idAnimal.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Animal)) {
            return false;
        }
        Animal other = (Animal) object;
        if ((this.idAnimal == null && other.idAnimal != null) || (this.idAnimal != null && !this.idAnimal.equals(other.idAnimal))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelos.Animal[ idAnimal=" + idAnimal + " ]";
    }
    
}

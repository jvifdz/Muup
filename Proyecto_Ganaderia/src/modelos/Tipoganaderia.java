/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author jvifdz-GRANDE
 */
@Entity
@Table(name = "tipoganaderia")
@NamedQueries({
    @NamedQuery(name = "Tipoganaderia.findAll", query = "SELECT t FROM Tipoganaderia t"),
    @NamedQuery(name = "Tipoganaderia.findByIdtipoGanaderia", query = "SELECT t FROM Tipoganaderia t WHERE t.idtipoGanaderia = :idtipoGanaderia"),
    @NamedQuery(name = "Tipoganaderia.findByNombre", query = "SELECT t FROM Tipoganaderia t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "Tipoganaderia.findByTiempoGestacion", query = "SELECT t FROM Tipoganaderia t WHERE t.tiempoGestacion = :tiempoGestacion")})
public class Tipoganaderia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipoGanaderia")
    private Integer idtipoGanaderia;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "tiempoGestacion")
    private Integer tiempoGestacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fktipoGanaderia")
    private List<Animal> animalList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkAlimentos")
    private List<Alimentos> alimentosList;

    public Tipoganaderia() {
    }

    public Tipoganaderia(Integer idtipoGanaderia) {
        this.idtipoGanaderia = idtipoGanaderia;
    }

    public Integer getIdtipoGanaderia() {
        return idtipoGanaderia;
    }

    public void setIdtipoGanaderia(Integer idtipoGanaderia) {
        this.idtipoGanaderia = idtipoGanaderia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getTiempoGestacion() {
        return tiempoGestacion;
    }

    public void setTiempoGestacion(Integer tiempoGestacion) {
        this.tiempoGestacion = tiempoGestacion;
    }

    public List<Animal> getAnimalList() {
        return animalList;
    }

    public void setAnimalList(List<Animal> animalList) {
        this.animalList = animalList;
    }

    public List<Alimentos> getAlimentosList() {
        return alimentosList;
    }

    public void setAlimentosList(List<Alimentos> alimentosList) {
        this.alimentosList = alimentosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtipoGanaderia != null ? idtipoGanaderia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipoganaderia)) {
            return false;
        }
        Tipoganaderia other = (Tipoganaderia) object;
        if ((this.idtipoGanaderia == null && other.idtipoGanaderia != null) || (this.idtipoGanaderia != null && !this.idtipoGanaderia.equals(other.idtipoGanaderia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombre;
    }
    
}
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
@Table(name = "parcela")
@NamedQueries({
    @NamedQuery(name = "Parcela.findAll", query = "SELECT p FROM Parcela p"),
    @NamedQuery(name = "Parcela.findByIdParcela", query = "SELECT p FROM Parcela p WHERE p.idParcela = :idParcela"),
    @NamedQuery(name = "Parcela.findByNombre", query = "SELECT p FROM Parcela p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "Parcela.findByLugarSituada", query = "SELECT p FROM Parcela p WHERE p.lugarSituada = :lugarSituada"),
    @NamedQuery(name = "Parcela.findByTamano", query = "SELECT p FROM Parcela p WHERE p.tamano = :tamano")})
public class Parcela implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_parcela")
    private Integer idParcela;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "lugarSituada")
    private String lugarSituada;
    @Column(name = "tamano")
    private Integer tamano;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkParcela")
    private List<Historialparcela> historialparcelaList;

    public Parcela() {
    }

    public Parcela(Integer idParcela) {
        this.idParcela = idParcela;
    }

    public Integer getIdParcela() {
        return idParcela;
    }

    public void setIdParcela(Integer idParcela) {
        this.idParcela = idParcela;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLugarSituada() {
        return lugarSituada;
    }

    public void setLugarSituada(String lugarSituada) {
        this.lugarSituada = lugarSituada;
    }

    public Integer getTamano() {
        return tamano;
    }

    public void setTamano(Integer tamano) {
        this.tamano = tamano;
    }

    public List<Historialparcela> getHistorialparcelaList() {
        return historialparcelaList;
    }

    public void setHistorialparcelaList(List<Historialparcela> historialparcelaList) {
        this.historialparcelaList = historialparcelaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idParcela != null ? idParcela.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Parcela)) {
            return false;
        }
        Parcela other = (Parcela) object;
        if ((this.idParcela == null && other.idParcela != null) || (this.idParcela != null && !this.idParcela.equals(other.idParcela))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelos.Parcela[ idParcela=" + idParcela + " ]";
    }
    
}

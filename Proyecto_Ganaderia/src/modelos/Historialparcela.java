/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author jvifdz-GRANDE
 */
@Entity
@Table(name = "historialparcela")
@NamedQueries({
    @NamedQuery(name = "Historialparcela.findAll", query = "SELECT h FROM Historialparcela h"),
    @NamedQuery(name = "Historialparcela.findByIdhistorialParcela", query = "SELECT h FROM Historialparcela h WHERE h.idhistorialParcela = :idhistorialParcela"),
    @NamedQuery(name = "Historialparcela.findByFechaEntrada", query = "SELECT h FROM Historialparcela h WHERE h.fechaEntrada = :fechaEntrada"),
    @NamedQuery(name = "Historialparcela.findByFechaSalida", query = "SELECT h FROM Historialparcela h WHERE h.fechaSalida = :fechaSalida")})
public class Historialparcela implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_historialParcela")
    private Integer idhistorialParcela;
    @Column(name = "fechaEntrada")
    @Temporal(TemporalType.DATE)
    private Date fechaEntrada;
    @Column(name = "fechaSalida")
    @Temporal(TemporalType.DATE)
    private Date fechaSalida;
    @JoinColumn(name = "fk_animal", referencedColumnName = "id_animal")
    @ManyToOne(optional = false)
    private Animal fkAnimal;
    @JoinColumn(name = "fk_parcela", referencedColumnName = "id_parcela")
    @ManyToOne(optional = false)
    private Parcela fkParcela;

    public Historialparcela() {
    }

    public Historialparcela(Integer idhistorialParcela) {
        this.idhistorialParcela = idhistorialParcela;
    }

    public Integer getIdhistorialParcela() {
        return idhistorialParcela;
    }

    public void setIdhistorialParcela(Integer idhistorialParcela) {
        this.idhistorialParcela = idhistorialParcela;
    }

    public Date getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(Date fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public Animal getFkAnimal() {
        return fkAnimal;
    }

    public void setFkAnimal(Animal fkAnimal) {
        this.fkAnimal = fkAnimal;
    }

    public Parcela getFkParcela() {
        return fkParcela;
    }

    public void setFkParcela(Parcela fkParcela) {
        this.fkParcela = fkParcela;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idhistorialParcela != null ? idhistorialParcela.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Historialparcela)) {
            return false;
        }
        Historialparcela other = (Historialparcela) object;
        if ((this.idhistorialParcela == null && other.idhistorialParcela != null) || (this.idhistorialParcela != null && !this.idhistorialParcela.equals(other.idhistorialParcela))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelos.Historialparcela[ idhistorialParcela=" + idhistorialParcela + " ]";
    }
    
}

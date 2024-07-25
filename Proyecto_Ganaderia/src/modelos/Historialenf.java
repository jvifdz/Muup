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
@Table(name = "historialenf")
@NamedQueries({
    @NamedQuery(name = "Historialenf.findAll", query = "SELECT h FROM Historialenf h"),
    @NamedQuery(name = "Historialenf.findByIdhistorialEnf", query = "SELECT h FROM Historialenf h WHERE h.idhistorialEnf = :idhistorialEnf"),
    @NamedQuery(name = "Historialenf.findByFechaEnfermedad", query = "SELECT h FROM Historialenf h WHERE h.fechaEnfermedad = :fechaEnfermedad")})
public class Historialenf implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_historialEnf")
    private Integer idhistorialEnf;
    @Column(name = "fechaEnfermedad")
    @Temporal(TemporalType.DATE)
    private Date fechaEnfermedad;
    @JoinColumn(name = "fk_animal", referencedColumnName = "id_animal")
    @ManyToOne(optional = false)
    private Animal fkAnimal;
    @JoinColumn(name = "fk_enfermedad", referencedColumnName = "id_enfermedad")
    @ManyToOne(optional = false)
    private Enfermedad fkEnfermedad;

    public Historialenf() {
    }

    public Historialenf(Integer idhistorialEnf) {
        this.idhistorialEnf = idhistorialEnf;
    }

    public Integer getIdhistorialEnf() {
        return idhistorialEnf;
    }

    public void setIdhistorialEnf(Integer idhistorialEnf) {
        this.idhistorialEnf = idhistorialEnf;
    }

    public Date getFechaEnfermedad() {
        return fechaEnfermedad;
    }

    public void setFechaEnfermedad(Date fechaEnfermedad) {
        this.fechaEnfermedad = fechaEnfermedad;
    }

    public Animal getFkAnimal() {
        return fkAnimal;
    }

    public void setFkAnimal(Animal fkAnimal) {
        this.fkAnimal = fkAnimal;
    }

    public Enfermedad getFkEnfermedad() {
        return fkEnfermedad;
    }

    public void setFkEnfermedad(Enfermedad fkEnfermedad) {
        this.fkEnfermedad = fkEnfermedad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idhistorialEnf != null ? idhistorialEnf.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Historialenf)) {
            return false;
        }
        Historialenf other = (Historialenf) object;
        if ((this.idhistorialEnf == null && other.idhistorialEnf != null) || (this.idhistorialEnf != null && !this.idhistorialEnf.equals(other.idhistorialEnf))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelos.Historialenf[ idhistorialEnf=" + idhistorialEnf + " ]";
    }
    
}

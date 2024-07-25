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
@Table(name = "animalmedicamento")
@NamedQueries({
    @NamedQuery(name = "Animalmedicamento.findAll", query = "SELECT a FROM Animalmedicamento a"),
    @NamedQuery(name = "Animalmedicamento.findByIdanimalMedicamento", query = "SELECT a FROM Animalmedicamento a WHERE a.idanimalMedicamento = :idanimalMedicamento"),
    @NamedQuery(name = "Animalmedicamento.findByFechaVacunacion", query = "SELECT a FROM Animalmedicamento a WHERE a.fechaVacunacion = :fechaVacunacion"),
    @NamedQuery(name = "Animalmedicamento.findByNumDosis", query = "SELECT a FROM Animalmedicamento a WHERE a.numDosis = :numDosis")})
public class Animalmedicamento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_animalMedicamento")
    private Integer idanimalMedicamento;
    @Column(name = "fechaVacunacion")
    @Temporal(TemporalType.DATE)
    private Date fechaVacunacion;
    @Column(name = "numDosis")
    private Integer numDosis;
    @JoinColumn(name = "fk_animal", referencedColumnName = "id_animal")
    @ManyToOne(optional = false)
    private Animal fkAnimal;
    @JoinColumn(name = "fk_medicamento", referencedColumnName = "id_medicamento")
    @ManyToOne(optional = false)
    private Medicamento fkMedicamento;

    public Animalmedicamento() {
    }

    public Animalmedicamento(Integer idanimalMedicamento) {
        this.idanimalMedicamento = idanimalMedicamento;
    }

    public Integer getIdanimalMedicamento() {
        return idanimalMedicamento;
    }

    public void setIdanimalMedicamento(Integer idanimalMedicamento) {
        this.idanimalMedicamento = idanimalMedicamento;
    }

    public Date getFechaVacunacion() {
        return fechaVacunacion;
    }

    public void setFechaVacunacion(Date fechaVacunacion) {
        this.fechaVacunacion = fechaVacunacion;
    }

    public Integer getNumDosis() {
        return numDosis;
    }

    public void setNumDosis(Integer numDosis) {
        this.numDosis = numDosis;
    }

    public Animal getFkAnimal() {
        return fkAnimal;
    }

    public void setFkAnimal(Animal fkAnimal) {
        this.fkAnimal = fkAnimal;
    }

    public Medicamento getFkMedicamento() {
        return fkMedicamento;
    }

    public void setFkMedicamento(Medicamento fkMedicamento) {
        this.fkMedicamento = fkMedicamento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idanimalMedicamento != null ? idanimalMedicamento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Animalmedicamento)) {
            return false;
        }
        Animalmedicamento other = (Animalmedicamento) object;
        if ((this.idanimalMedicamento == null && other.idanimalMedicamento != null) || (this.idanimalMedicamento != null && !this.idanimalMedicamento.equals(other.idanimalMedicamento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelos.Animalmedicamento[ idanimalMedicamento=" + idanimalMedicamento + " ]";
    }
    
}

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
@Table(name = "alimentos")
@NamedQueries({
    @NamedQuery(name = "Alimentos.findAll", query = "SELECT a FROM Alimentos a"),
    @NamedQuery(name = "Alimentos.findByIdAlimentos", query = "SELECT a FROM Alimentos a WHERE a.idAlimentos = :idAlimentos"),
    @NamedQuery(name = "Alimentos.findByNombre", query = "SELECT a FROM Alimentos a WHERE a.nombre = :nombre"),
    @NamedQuery(name = "Alimentos.findByPrecio", query = "SELECT a FROM Alimentos a WHERE a.precio = :precio"),
    @NamedQuery(name = "Alimentos.findByCantidad", query = "SELECT a FROM Alimentos a WHERE a.cantidad = :cantidad"),
    @NamedQuery(name = "Alimentos.findByFechaCompra", query = "SELECT a FROM Alimentos a WHERE a.fechaCompra = :fechaCompra")})
public class Alimentos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_alimentos")
    private Integer idAlimentos;
    @Column(name = "nombre")
    private String nombre;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "precio")
    private Float precio;
    @Column(name = "cantidad")
    private Integer cantidad;
    @Column(name = "fechaCompra")
    @Temporal(TemporalType.DATE)
    private Date fechaCompra;
    @JoinColumn(name = "fk_alimentos", referencedColumnName = "id_tipoGanaderia")
    @ManyToOne(optional = false)
    private Tipoganaderia fkAlimentos;

    public Alimentos() {
    }

    public Alimentos(Integer idAlimentos) {
        this.idAlimentos = idAlimentos;
    }

    public Integer getIdAlimentos() {
        return idAlimentos;
    }

    public void setIdAlimentos(Integer idAlimentos) {
        this.idAlimentos = idAlimentos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public Tipoganaderia getFkAlimentos() {
        return fkAlimentos;
    }

    public void setFkAlimentos(Tipoganaderia fkAlimentos) {
        this.fkAlimentos = fkAlimentos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAlimentos != null ? idAlimentos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Alimentos)) {
            return false;
        }
        Alimentos other = (Alimentos) object;
        if ((this.idAlimentos == null && other.idAlimentos != null) || (this.idAlimentos != null && !this.idAlimentos.equals(other.idAlimentos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "alimentosbien.Alimentos[ idAlimentos=" + idAlimentos + " ]";
    }
    
}
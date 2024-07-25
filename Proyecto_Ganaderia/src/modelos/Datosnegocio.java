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
@Table(name = "datosnegocio")
@NamedQueries({
    @NamedQuery(name = "Datosnegocio.findAll", query = "SELECT d FROM Datosnegocio d"),
    @NamedQuery(name = "Datosnegocio.findByIddatosNegocio", query = "SELECT d FROM Datosnegocio d WHERE d.iddatosNegocio = :iddatosNegocio"),
    @NamedQuery(name = "Datosnegocio.findByDni", query = "SELECT d FROM Datosnegocio d WHERE d.dni = :dni"),
    @NamedQuery(name = "Datosnegocio.findByDireccion", query = "SELECT d FROM Datosnegocio d WHERE d.direccion = :direccion"),
    @NamedQuery(name = "Datosnegocio.findByCodPostal", query = "SELECT d FROM Datosnegocio d WHERE d.codPostal = :codPostal"),
    @NamedQuery(name = "Datosnegocio.findByNombre", query = "SELECT d FROM Datosnegocio d WHERE d.nombre = :nombre"),
    @NamedQuery(name = "Datosnegocio.findByTelefono", query = "SELECT d FROM Datosnegocio d WHERE d.telefono = :telefono"),
    @NamedQuery(name = "Datosnegocio.findByCorreo", query = "SELECT d FROM Datosnegocio d WHERE d.correo = :correo"),
    @NamedQuery(name = "Datosnegocio.findByCodExplotacion", query = "SELECT d FROM Datosnegocio d WHERE d.codExplotacion = :codExplotacion")})
public class Datosnegocio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_datosNegocio")
    private Integer iddatosNegocio;
    @Basic(optional = false)
    @Column(name = "dni")
    private String dni;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "codPostal")
    private Integer codPostal;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "telefono")
    private Integer telefono;
    @Column(name = "correo")
    private String correo;
    @Basic(optional = false)
    @Column(name = "codExplotacion")
    private String codExplotacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkdatosNegocio")
    private List<Factura> facturaList;

    public Datosnegocio() {
    }

    public Datosnegocio(Integer iddatosNegocio) {
        this.iddatosNegocio = iddatosNegocio;
    }

    public Datosnegocio(Integer iddatosNegocio, String dni, String codExplotacion) {
        this.iddatosNegocio = iddatosNegocio;
        this.dni = dni;
        this.codExplotacion = codExplotacion;
    }

    public Integer getIddatosNegocio() {
        return iddatosNegocio;
    }

    public void setIddatosNegocio(Integer iddatosNegocio) {
        this.iddatosNegocio = iddatosNegocio;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Integer getCodPostal() {
        return codPostal;
    }

    public void setCodPostal(Integer codPostal) {
        this.codPostal = codPostal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCodExplotacion() {
        return codExplotacion;
    }

    public void setCodExplotacion(String codExplotacion) {
        this.codExplotacion = codExplotacion;
    }

    public List<Factura> getFacturaList() {
        return facturaList;
    }

    public void setFacturaList(List<Factura> facturaList) {
        this.facturaList = facturaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddatosNegocio != null ? iddatosNegocio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Datosnegocio)) {
            return false;
        }
        Datosnegocio other = (Datosnegocio) object;
        if ((this.iddatosNegocio == null && other.iddatosNegocio != null) || (this.iddatosNegocio != null && !this.iddatosNegocio.equals(other.iddatosNegocio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelos.Datosnegocio[ iddatosNegocio=" + iddatosNegocio + " ]";
    }
    
}

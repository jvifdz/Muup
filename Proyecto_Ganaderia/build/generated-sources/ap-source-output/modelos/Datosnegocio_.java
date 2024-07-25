package modelos;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelos.Factura;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-06-17T23:12:00")
@StaticMetamodel(Datosnegocio.class)
public class Datosnegocio_ { 

    public static volatile SingularAttribute<Datosnegocio, Integer> iddatosNegocio;
    public static volatile SingularAttribute<Datosnegocio, String> correo;
    public static volatile SingularAttribute<Datosnegocio, String> direccion;
    public static volatile ListAttribute<Datosnegocio, Factura> facturaList;
    public static volatile SingularAttribute<Datosnegocio, Integer> telefono;
    public static volatile SingularAttribute<Datosnegocio, Integer> codPostal;
    public static volatile SingularAttribute<Datosnegocio, String> nombre;
    public static volatile SingularAttribute<Datosnegocio, String> dni;
    public static volatile SingularAttribute<Datosnegocio, String> codExplotacion;

}
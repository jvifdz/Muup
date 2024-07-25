package modelos;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelos.Animal;
import modelos.Factura;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-06-17T23:12:00")
@StaticMetamodel(Detallefactura.class)
public class Detallefactura_ { 

    public static volatile SingularAttribute<Detallefactura, Float> precio;
    public static volatile SingularAttribute<Detallefactura, Integer> idDetalle;
    public static volatile SingularAttribute<Detallefactura, Factura> fkFactura;
    public static volatile SingularAttribute<Detallefactura, Animal> fkAnimal;

}
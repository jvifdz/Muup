package modelos;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelos.Tipoganaderia;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-06-17T23:12:00")
@StaticMetamodel(Alimentos.class)
public class Alimentos_ { 

    public static volatile SingularAttribute<Alimentos, Date> fechaCompra;
    public static volatile SingularAttribute<Alimentos, Float> precio;
    public static volatile SingularAttribute<Alimentos, Tipoganaderia> fkAlimentos;
    public static volatile SingularAttribute<Alimentos, Integer> idAlimentos;
    public static volatile SingularAttribute<Alimentos, Integer> cantidad;
    public static volatile SingularAttribute<Alimentos, String> nombre;

}
package modelos;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelos.Alimentos;
import modelos.Animal;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-06-17T23:12:00")
@StaticMetamodel(Tipoganaderia.class)
public class Tipoganaderia_ { 

    public static volatile ListAttribute<Tipoganaderia, Animal> animalList;
    public static volatile SingularAttribute<Tipoganaderia, Integer> idtipoGanaderia;
    public static volatile SingularAttribute<Tipoganaderia, String> nombre;
    public static volatile ListAttribute<Tipoganaderia, Alimentos> alimentosList;
    public static volatile SingularAttribute<Tipoganaderia, Integer> tiempoGestacion;

}
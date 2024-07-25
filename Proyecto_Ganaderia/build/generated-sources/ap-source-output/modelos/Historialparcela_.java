package modelos;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelos.Animal;
import modelos.Parcela;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-06-17T23:12:00")
@StaticMetamodel(Historialparcela.class)
public class Historialparcela_ { 

    public static volatile SingularAttribute<Historialparcela, Date> fechaSalida;
    public static volatile SingularAttribute<Historialparcela, Date> fechaEntrada;
    public static volatile SingularAttribute<Historialparcela, Animal> fkAnimal;
    public static volatile SingularAttribute<Historialparcela, Parcela> fkParcela;
    public static volatile SingularAttribute<Historialparcela, Integer> idhistorialParcela;

}
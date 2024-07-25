package modelos;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelos.Historialparcela;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-06-17T23:12:00")
@StaticMetamodel(Parcela.class)
public class Parcela_ { 

    public static volatile ListAttribute<Parcela, Historialparcela> historialparcelaList;
    public static volatile SingularAttribute<Parcela, Integer> idParcela;
    public static volatile SingularAttribute<Parcela, String> lugarSituada;
    public static volatile SingularAttribute<Parcela, String> nombre;
    public static volatile SingularAttribute<Parcela, Integer> tamano;

}
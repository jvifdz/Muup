package modelos;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelos.Historialenf;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-06-17T23:12:00")
@StaticMetamodel(Enfermedad.class)
public class Enfermedad_ { 

    public static volatile SingularAttribute<Enfermedad, Integer> idEnfermedad;
    public static volatile ListAttribute<Enfermedad, Historialenf> historialenfList;
    public static volatile SingularAttribute<Enfermedad, String> nombre;

}
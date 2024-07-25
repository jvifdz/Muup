package modelos;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelos.Animal;
import modelos.Medicamento;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-06-17T23:12:00")
@StaticMetamodel(Animalmedicamento.class)
public class Animalmedicamento_ { 

    public static volatile SingularAttribute<Animalmedicamento, Integer> numDosis;
    public static volatile SingularAttribute<Animalmedicamento, Integer> idanimalMedicamento;
    public static volatile SingularAttribute<Animalmedicamento, Animal> fkAnimal;
    public static volatile SingularAttribute<Animalmedicamento, Medicamento> fkMedicamento;
    public static volatile SingularAttribute<Animalmedicamento, Date> fechaVacunacion;

}
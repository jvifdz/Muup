package modelos;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelos.Animalmedicamento;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-06-17T23:12:00")
@StaticMetamodel(Medicamento.class)
public class Medicamento_ { 

    public static volatile SingularAttribute<Medicamento, Integer> idMedicamento;
    public static volatile SingularAttribute<Medicamento, Date> fechaCompra;
    public static volatile SingularAttribute<Medicamento, Float> precio;
    public static volatile ListAttribute<Medicamento, Animalmedicamento> animalmedicamentoList;
    public static volatile SingularAttribute<Medicamento, Integer> volumen;
    public static volatile SingularAttribute<Medicamento, Integer> volumenDosis;
    public static volatile SingularAttribute<Medicamento, String> nombre;

}
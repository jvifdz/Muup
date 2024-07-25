package modelos;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelos.Animalmedicamento;
import modelos.Detallefactura;
import modelos.Historialenf;
import modelos.Historialparcela;
import modelos.Tipoganaderia;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-06-17T23:12:00")
@StaticMetamodel(Animal.class)
public class Animal_ { 

    public static volatile SingularAttribute<Animal, Date> fechaBaja;
    public static volatile SingularAttribute<Animal, Integer> idAnimal;
    public static volatile ListAttribute<Animal, Animalmedicamento> animalmedicamentoList;
    public static volatile SingularAttribute<Animal, Date> fechaNacimiento;
    public static volatile ListAttribute<Animal, Detallefactura> detallefacturaList;
    public static volatile SingularAttribute<Animal, Date> fechaApareamiento;
    public static volatile SingularAttribute<Animal, String> nombre;
    public static volatile SingularAttribute<Animal, String> dai;
    public static volatile SingularAttribute<Animal, Tipoganaderia> fktipoGanaderia;
    public static volatile ListAttribute<Animal, Historialparcela> historialparcelaList;
    public static volatile SingularAttribute<Animal, String> razonBaja;
    public static volatile ListAttribute<Animal, Historialenf> historialenfList;
    public static volatile SingularAttribute<Animal, String> sexo;
    public static volatile SingularAttribute<Animal, String> daiMadre;

}
package entities;

import entities.Angyusers;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "EclipseLink-2.5.2.v20140319-rNA", date = "2016-12-15T16:35:51")
@StaticMetamodel(Pointreg.class)
public class Pointreg_ {

    public static volatile SingularAttribute<Pointreg, Angyusers> idUser;
    public static volatile SingularAttribute<Pointreg, Integer> idPointreg;
    public static volatile SingularAttribute<Pointreg, Integer> puntuacion;
    public static volatile SingularAttribute<Pointreg, Date> fechaIni;
    public static volatile SingularAttribute<Pointreg, Date> fechaFin;

}

package entities;

import entities.Pointreg;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "EclipseLink-2.5.2.v20140319-rNA", date = "2016-12-15T16:35:51")
@StaticMetamodel(Angyusers.class)
public class Angyusers_ {

    public static volatile SingularAttribute<Angyusers, Integer> idUser;
    public static volatile SingularAttribute<Angyusers, String> contras;
    public static volatile SingularAttribute<Angyusers, String> usuario;
    public static volatile CollectionAttribute<Angyusers, Pointreg> pointregCollection;

}

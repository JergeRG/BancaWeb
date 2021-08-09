package persistencia;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-12-03T14:54:50")
@StaticMetamodel(Movimientos.class)
public class Movimientos_ { 

    public static volatile SingularAttribute<Movimientos, String> descripcion;
    public static volatile SingularAttribute<Movimientos, Date> fecha;
    public static volatile SingularAttribute<Movimientos, Float> monto;
    public static volatile SingularAttribute<Movimientos, Integer> id;
    public static volatile SingularAttribute<Movimientos, Integer> numerodecuenta;

}
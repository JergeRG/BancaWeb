/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Jorge
 */
public abstract class insertaMovimiento {
    private EntityManager entityManager;

    /**
     * @param args
     */
    public void insertTopic(int numero, Date date, String descrip, float mon){
    EntityManagerFactory factory = Persistence
            .createEntityManagerFactory("LoginPU");
    entityManager = factory.createEntityManager();
    entityManager.getTransaction().begin();
    insertTopicquery(numero,date,descrip, mon);
    entityManager.getTransaction().commit();
    entityManager.close();
    factory.close();

    }
 
    /**
     * @param id
     *   id of the new topic.
     * @param fecha
     *   title of the new topic.
     * @param descripcion
     *   creation date of the topic.
     * @param monto
     *   creation date of the topic.
     * @param numerodecuenta
     *   creation date of the topic.
     */
    public void insertTopicquery(int numerodecuenta, Date fecha, String descripcion, float monto){
    Query query = entityManager.createNativeQuery("INSERT INTO movimientos (id, numerodecuenta, fecha, descripcion, monto) " +
            " VALUES(?,?,?,?,?)");
        query.setParameter(2, numerodecuenta);
        query.setParameter(3, fecha);
        query.setParameter(4, descripcion);
        query.setParameter(5, monto);
        query.executeUpdate();
    }
    
}

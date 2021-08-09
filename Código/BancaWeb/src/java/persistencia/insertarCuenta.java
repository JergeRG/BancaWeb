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
public abstract class insertarCuenta {
    private EntityManager entityManager;
    public void insertTopic(int cuenta, String usuario, String nip, float sal){
    EntityManagerFactory factory = Persistence
            .createEntityManagerFactory("LoginPU");
    entityManager = factory.createEntityManager();
    entityManager.getTransaction().begin();
    insertTopicquery(cuenta,usuario,nip, sal);
    entityManager.getTransaction().commit();
    entityManager.close();
    factory.close();

    }

    public void insertTopicquery(int numerodecuenta, String usuario, String nip, float saldo){
    Query query = entityManager.createNativeQuery("INSERT INTO cuenta (numerodecuenta, username, nip, saldo) " +
            " VALUES(?,?,?,?)");
        query.setParameter(1, numerodecuenta);
        query.setParameter(2, usuario);
        query.setParameter(3, nip);
        query.setParameter(4, saldo);
        query.executeUpdate();
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Jorge
 */
public abstract class JPAInsert {
    private EntityManager entityManager;

    /**
     * @param args
     */
    public void insertTopic(String user, String pass, String nombre, String apellidos, String tel,String correo, String dir){
    EntityManagerFactory factory = Persistence
            .createEntityManagerFactory("LoginPU");
    entityManager = factory.createEntityManager();
    entityManager.getTransaction().begin();
    insertTopicquery(user,pass,nombre,apellidos,tel,correo,dir);
    entityManager.getTransaction().commit();
    entityManager.close();
    factory.close();

    }
 
    /**
     * @param username
     *   title of the new topic.
     * @param password
     *   creation date of the topic.
     * @param nombre
     *   creation date of the topic.
     * @param apellidos
     *   creation date of the topic.
     * @param telefono
     *   creation date of the topic.

     * @param email
     *   creation date of the topic.
     * @param direccion
     *   creation date of the topic.
     */
    public void insertTopicquery(String username,String password, String nombre, String apellidos, String telefono, String email,String direccion){
    Query query = entityManager.createNativeQuery("INSERT INTO usuario (username,password,nombre,apellidos,telefono,cuentas,email,direccion,sesion,block) " +
            " VALUES(?,?,?,?,?,?,?,?,?,?)");
        query.setParameter(1, username);
        query.setParameter(2, password);
        query.setParameter(3, nombre);
        query.setParameter(4, apellidos);
        query.setParameter(5, telefono);
        query.setParameter(6, 0);
        query.setParameter(7, email);
        query.setParameter(8, direccion);
        query.setParameter(9, false);
        query.setParameter(10, false);
        query.executeUpdate();
    }
}

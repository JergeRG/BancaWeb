/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import persistencia.exceptions.NonexistentEntityException;
import persistencia.exceptions.PreexistingEntityException;
import persistencia.exceptions.RollbackFailureException;

/**
 *
 * @author Jorge
 */
public class CuentaJpaController implements Serializable {

    public CuentaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cuenta cuenta) throws PreexistingEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(cuenta);
            em.getTransaction().commit();
        } catch (Exception ex) {
            try {
                em.getTransaction().rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findCuenta(cuenta.getNumerodecuenta()) != null) {
                throw new PreexistingEntityException("Cuenta " + cuenta + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cuenta cuenta) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        em = getEntityManager();
        try {
            em.getTransaction().begin();
            cuenta = em.merge(cuenta);
            em.getTransaction().commit();
        } catch (Exception ex) {
            try {
                em.getTransaction().rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = cuenta.getNumerodecuenta();
                if (findCuenta(id) == null) {
                    throw new NonexistentEntityException("The cuenta with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        em = getEntityManager();
        try {
            em.getTransaction().begin();
            Cuenta cuenta;
            try {
                cuenta = em.getReference(Cuenta.class, id);
                cuenta.getNumerodecuenta();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cuenta with id " + id + " no longer exists.", enfe);
            }
            em.remove(cuenta);
            em.getTransaction().commit();
        } catch (Exception ex) {
            try {
                em.getTransaction().rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cuenta> findCuentaEntities() {
        return findCuentaEntities(true, -1, -1);
    }

    public List<Cuenta> findCuentaEntities(int maxResults, int firstResult) {
        return findCuentaEntities(false, maxResults, firstResult);
    }

    private List<Cuenta> findCuentaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cuenta.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Cuenta findCuenta(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cuenta.class, id);
        } finally {
            em.close();
        }
    }

    public int getCuentaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cuenta> rt = cq.from(Cuenta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List<Cuenta> findAll() {
        EntityManager em = getEntityManager();
        List<Cuenta> result;  
        TypedQuery<Cuenta> query = em.createNamedQuery("Cuenta.findAll", Cuenta.class);
        result = query.getResultList();
        return result;
    }
    
    public List<Cuenta> findByUsername(String usuario) {
        EntityManager em = getEntityManager();
        TypedQuery<Cuenta>query=em.createNamedQuery("Cuenta.findByUsername", Cuenta.class);
        query.setParameter("username",usuario);
        List<Cuenta> result = null;
        try {
         result = query.getResultList();
        } catch (NoResultException error){
            System.out.println(error);
        }
        return result;
    }
    public boolean findByNumerodecuenta(int numerodecuenta) {
        EntityManager em = getEntityManager();
        TypedQuery<Cuenta>query=em.createNamedQuery("Cuenta.findByNumerodecuenta", Cuenta.class);
        query.setParameter("numerodecuenta",numerodecuenta);
        Cuenta result;
        try {
            result = query.getSingleResult();
            return true;
        } catch (NoResultException error){
            System.out.println(error);
            return false;
        }
    }
    public Cuenta findByNumerodecuenta1(int numerodecuenta) {
        EntityManager em = getEntityManager();
        TypedQuery<Cuenta>query=em.createNamedQuery("Cuenta.findByNumerodecuenta", Cuenta.class);
        query.setParameter("numerodecuenta",numerodecuenta);
        Cuenta result;
        try {
            result = query.getSingleResult();
            return result;
        } catch (NoResultException error){
            System.out.println(error);
            return null;
        }
    }
    public boolean findByNipNumerodecuenta(int numerodecuenta, String nip) {
        EntityManager em = getEntityManager();
        TypedQuery<Cuenta>query=em.createNamedQuery("Cuenta.findByNipNumerodecuenta", Cuenta.class);
        query.setParameter("numerodecuenta",numerodecuenta);
        query.setParameter("nip",nip);
        Cuenta result = null;
        try {
            result = query.getSingleResult();
            return true;
        } catch (NoResultException error){
            return false;
        }
    }
     public void updateSaldo(int cuenta,float saldo){
        EntityManager em = getEntityManager();
        TypedQuery<Cuenta>query=em.createNamedQuery("Cuenta.updateSaldo", Cuenta.class);
        query.setParameter("numerodecuenta",cuenta);
        query.setParameter("saldo",saldo);
        em.getTransaction().begin();
        query.executeUpdate();
        em.getTransaction().commit();
        em.close();
    }
}

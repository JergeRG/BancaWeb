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
public class RegistroJpaController implements Serializable {

    public RegistroJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Registro registro) throws PreexistingEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(registro);
            em.getTransaction().commit();
        } catch (Exception ex) {
            try {
                em.getTransaction().rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findRegistro(registro.getNumerodecuenta()) != null) {
                throw new PreexistingEntityException("Registro " + registro + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Registro registro) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        em = getEntityManager();
        try {
            em.getTransaction().begin();
            registro = em.merge(registro);
            em.getTransaction().commit();
        } catch (Exception ex) {
            try {
                em.getTransaction().rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = registro.getNumerodecuenta();
                if (findRegistro(id) == null) {
                    throw new NonexistentEntityException("The registro with id " + id + " no longer exists.");
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
            Registro registro;
            try {
                registro = em.getReference(Registro.class, id);
                registro.getNumerodecuenta();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The registro with id " + id + " no longer exists.", enfe);
            }
            em.remove(registro);
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

    public List<Registro> findRegistroEntities() {
        return findRegistroEntities(true, -1, -1);
    }

    public List<Registro> findRegistroEntities(int maxResults, int firstResult) {
        return findRegistroEntities(false, maxResults, firstResult);
    }

    private List<Registro> findRegistroEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Registro.class));
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

    public Registro findRegistro(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Registro.class, id);
        } finally {
            em.close();
        }
    }

    public int getRegistroCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Registro> rt = cq.from(Registro.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    public boolean findByNumerodecuenta(int numerodecuenta) {
        EntityManager em = getEntityManager();
        TypedQuery<Registro>query=em.createNamedQuery("Registro.findByNumerodecuenta", Registro.class);
        query.setParameter("numerodecuenta",numerodecuenta);
        Registro result;
        try {
            result = query.getSingleResult();
            return true;
        } catch (NoResultException error){
            System.out.println(error);
            return false;
        }
    }
    public Registro findByNumerodecuenta1(int numerodecuenta) {
        EntityManager em = getEntityManager();
        TypedQuery<Registro>query=em.createNamedQuery("Registro.findByNumerodecuenta", Registro.class);
        query.setParameter("numerodecuenta",numerodecuenta);
        Registro result;
        try {
            result = (Registro)query.getSingleResult();
            return result;
        } catch (NoResultException error){
            System.out.println(error);
            return null;
        }
    }
    public boolean findByNipNumerodecuenta(int numerodecuenta, String nip) {
        EntityManager em = getEntityManager();
        TypedQuery<Registro>query=em.createNamedQuery("Registro.findByNipNumerodecuenta", Registro.class);
        query.setParameter("numerodecuenta",numerodecuenta);
        query.setParameter("nip",nip);
        Registro result = null;
        try {
            result = query.getSingleResult();
            return true;
        } catch (NoResultException error){
            return false;
        }
    }
    
    public void updateSaldo(int cuenta,float saldo){
        EntityManager em = getEntityManager();
        TypedQuery<Registro>query=em.createNamedQuery("Registro.updateSaldo", Registro.class);
        query.setParameter("numerodecuenta",cuenta);
        query.setParameter("saldo",saldo);
        em.getTransaction().begin();
        query.executeUpdate();
        em.getTransaction().commit();
        em.close();
    }
    public void updateUso(int cuenta){
        EntityManager em = getEntityManager();
        TypedQuery<Registro>query=em.createNamedQuery("Registro.updateUso", Registro.class);
        query.setParameter("numerodecuenta",cuenta);
        em.getTransaction().begin();
        query.executeUpdate();
        em.getTransaction().commit();
        em.close();
    }
}

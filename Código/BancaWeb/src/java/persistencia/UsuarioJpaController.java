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
import javax.persistence.Persistence;
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
public class UsuarioJpaController implements Serializable {

    public UsuarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuario usuario) throws PreexistingEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(usuario);
            em.getTransaction().commit();
        } catch (Exception ex) {
            try {
                em.getTransaction().rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findUsuario(usuario.getUsername()) != null) {
                throw new PreexistingEntityException("Usuario " + usuario + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuario usuario) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        em = getEntityManager();
        try {
            em.getTransaction().begin();
            usuario = em.merge(usuario);
            em.getTransaction().commit();
        } catch (Exception ex) {
            try {
                em.getTransaction().rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = usuario.getUsername();
                if (findUsuario(id) == null) {
                    throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        em = getEntityManager();
        try {
            em.getTransaction().begin();
            Usuario usuario;
            try {
                usuario = em.getReference(Usuario.class, id);
                usuario.getUsername();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.", enfe);
            }
            em.remove(usuario);
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

    public List<Usuario> findUsuarioEntities() {
        return findUsuarioEntities(true, -1, -1);
    }

    public List<Usuario> findUsuarioEntities(int maxResults, int firstResult) {
        return findUsuarioEntities(false, maxResults, firstResult);
    }

    private List<Usuario> findUsuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuario.class));
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

    public Usuario findUsuario(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuario> rt = cq.from(Usuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    public List<Usuario> findAll(){
        EntityManager em = getEntityManager();
        List<Usuario> result;  
        TypedQuery<Usuario> query = em.createNamedQuery("Usuario.findAll", Usuario.class);
        result = query.getResultList();
        return result;
    }
    public Usuario findByUsername(String usuario) {
        EntityManager em = getEntityManager();
        TypedQuery<Usuario>query=em.createNamedQuery("Usuario.findByUsername", Usuario.class);
        query.setParameter("username",usuario);
        Usuario result = null;
        try {
        result = (Usuario)query.getSingleResult();
        } catch (NoResultException error){
            System.out.println(error);
        }
        return result;
    }
    public Usuario findByPasswordUsername(String usuario, String pass) {
        EntityManager em = getEntityManager();
        TypedQuery<Usuario>query=em.createNamedQuery("Usuario.findByPasswordUsername", Usuario.class);
        query.setParameter("username",usuario);
        query.setParameter("password",pass);
        Usuario result = null;
        try {
        result = (Usuario)query.getSingleResult();
        } catch (NoResultException error){
            System.out.println(error);
        }
        return result;
    }
    public void updateSesion(String usuario,boolean val){
        EntityManager em = getEntityManager();
        TypedQuery<Usuario>query=em.createNamedQuery("Usuario.updateSesion", Usuario.class);
        query.setParameter("username",usuario);
        query.setParameter("sesion",val);
        em.getTransaction().begin();
        query.executeUpdate();
        em.getTransaction().commit();
        em.close();
    }
    
    public void updateCuentas(String usuario,int cuentas){
        EntityManager em = getEntityManager();
        TypedQuery<Usuario>query=em.createNamedQuery("Usuario.updateCuentas", Usuario.class);
        query.setParameter("username",usuario);
        query.setParameter("cuentas",cuentas);
        em.getTransaction().begin();
        query.executeUpdate();
        em.getTransaction().commit();
        em.close();
    }
    public void bloquearUsuario(String usuario){
        EntityManager em = getEntityManager();
        TypedQuery<Usuario>query=em.createNamedQuery("Usuario.block", Usuario.class);
        query.setParameter("username",usuario);
        em.getTransaction().begin();
        query.executeUpdate();
        em.getTransaction().commit();
        em.close();
    }
     public Usuario findByEmail(String email) {
        EntityManager em = getEntityManager();
        TypedQuery<Usuario>query=em.createNamedQuery("Usuario.findByEmail", Usuario.class);
        query.setParameter("username", email);
        Usuario result = null;
        try {
        result = (Usuario)query.getSingleResult();
        } catch (NoResultException error){
            System.out.println(error);
        }
        return result;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ces4.paspagcontrollers;

import co.com.ces4.paspagcontrollers.exceptions.NonexistentEntityException;
import co.com.ces4.paspagcontrollers.exceptions.PreexistingEntityException;
import co.com.ces4.paspagentities.EntidadFinanciera;
import co.com.ces4.paspagentities.PersonaPK;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author cristian
 */
public class EntidadFinancieraJpaController implements Serializable {

    public EntidadFinancieraJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(EntidadFinanciera entidadFinanciera) throws PreexistingEntityException, Exception {
        if (entidadFinanciera.getPersonaPK() == null) {
            entidadFinanciera.setPersonaPK(new PersonaPK());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(entidadFinanciera);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEntidadFinanciera(entidadFinanciera.getPersonaPK()) != null) {
                throw new PreexistingEntityException("EntidadFinanciera " + entidadFinanciera + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EntidadFinanciera entidadFinanciera) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            entidadFinanciera = em.merge(entidadFinanciera);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                PersonaPK id = entidadFinanciera.getPersonaPK();
                if (findEntidadFinanciera(id) == null) {
                    throw new NonexistentEntityException("The entidadFinanciera with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(PersonaPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EntidadFinanciera entidadFinanciera;
            try {
                entidadFinanciera = em.getReference(EntidadFinanciera.class, id);
                entidadFinanciera.getPersonaPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The entidadFinanciera with id " + id + " no longer exists.", enfe);
            }
            em.remove(entidadFinanciera);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EntidadFinanciera> findEntidadFinancieraEntities() {
        return findEntidadFinancieraEntities(true, -1, -1);
    }

    public List<EntidadFinanciera> findEntidadFinancieraEntities(int maxResults, int firstResult) {
        return findEntidadFinancieraEntities(false, maxResults, firstResult);
    }

    private List<EntidadFinanciera> findEntidadFinancieraEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EntidadFinanciera.class));
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

    public EntidadFinanciera findEntidadFinanciera(PersonaPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EntidadFinanciera.class, id);
        } finally {
            em.close();
        }
    }

    public int getEntidadFinancieraCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EntidadFinanciera> rt = cq.from(EntidadFinanciera.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

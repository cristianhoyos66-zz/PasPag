/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ces4.paspagcontrollers;

import co.com.ces4.paspagcontrollers.exceptions.NonexistentEntityException;
import co.com.ces4.paspagcontrollers.exceptions.PreexistingEntityException;
import co.com.ces4.paspagentities.PersonaNatural;
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
public class PersonaNaturalJpaController implements Serializable {

    public PersonaNaturalJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PersonaNatural personaNatural) throws PreexistingEntityException, Exception {
        if (personaNatural.getPersonaPK() == null) {
            personaNatural.setPersonaPK(new PersonaPK());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(personaNatural);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPersonaNatural(personaNatural.getPersonaPK()) != null) {
                throw new PreexistingEntityException("PersonaNatural " + personaNatural + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PersonaNatural personaNatural) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            personaNatural = em.merge(personaNatural);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                PersonaPK id = personaNatural.getPersonaPK();
                if (findPersonaNatural(id) == null) {
                    throw new NonexistentEntityException("The personaNatural with id " + id + " no longer exists.");
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
            PersonaNatural personaNatural;
            try {
                personaNatural = em.getReference(PersonaNatural.class, id);
                personaNatural.getPersonaPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The personaNatural with id " + id + " no longer exists.", enfe);
            }
            em.remove(personaNatural);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PersonaNatural> findPersonaNaturalEntities() {
        return findPersonaNaturalEntities(true, -1, -1);
    }

    public List<PersonaNatural> findPersonaNaturalEntities(int maxResults, int firstResult) {
        return findPersonaNaturalEntities(false, maxResults, firstResult);
    }

    private List<PersonaNatural> findPersonaNaturalEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PersonaNatural.class));
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

    public PersonaNatural findPersonaNatural(PersonaPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PersonaNatural.class, id);
        } finally {
            em.close();
        }
    }

    public int getPersonaNaturalCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PersonaNatural> rt = cq.from(PersonaNatural.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

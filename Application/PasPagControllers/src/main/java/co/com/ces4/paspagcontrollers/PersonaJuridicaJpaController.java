/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ces4.paspagcontrollers;

import co.com.ces4.paspagcontrollers.exceptions.NonexistentEntityException;
import co.com.ces4.paspagcontrollers.exceptions.PreexistingEntityException;
import co.com.ces4.paspagentities.PersonaJuridica;
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
public class PersonaJuridicaJpaController implements Serializable {

    public PersonaJuridicaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PersonaJuridica personaJuridica) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(personaJuridica);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPersonaJuridica(personaJuridica.getPersonaPK()) != null) {
                throw new PreexistingEntityException("PersonaJuridica " + personaJuridica + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PersonaJuridica personaJuridica) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            personaJuridica = em.merge(personaJuridica);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                PersonaPK id = personaJuridica.getPersonaPK();
                if (findPersonaJuridica(id) == null) {
                    throw new NonexistentEntityException("The personaJuridica with id " + id + " no longer exists.");
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
            PersonaJuridica personaJuridica;
            try {
                personaJuridica = em.getReference(PersonaJuridica.class, id);
                personaJuridica.getPersonaPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The personaJuridica with id " + id + " no longer exists.", enfe);
            }
            em.remove(personaJuridica);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PersonaJuridica> findPersonaJuridicaEntities() {
        return findPersonaJuridicaEntities(true, -1, -1);
    }

    public List<PersonaJuridica> findPersonaJuridicaEntities(int maxResults, int firstResult) {
        return findPersonaJuridicaEntities(false, maxResults, firstResult);
    }

    private List<PersonaJuridica> findPersonaJuridicaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PersonaJuridica.class));
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

    public PersonaJuridica findPersonaJuridica(PersonaPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PersonaJuridica.class, id);
        } finally {
            em.close();
        }
    }

    public int getPersonaJuridicaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PersonaJuridica> rt = cq.from(PersonaJuridica.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

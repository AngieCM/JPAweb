/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Angyusers;
import entities.Pointreg;
import services.exceptions.IllegalOrphanException;
import services.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Angie_
 */
public class AngyusersJpaController implements Serializable {

    protected EntityManager em;

    public AngyusersJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Angyusers angyusers) {
        if (angyusers.getPointregCollection() == null) {
            angyusers.setPointregCollection(new ArrayList<Pointreg>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Pointreg> attachedPointregCollection = new ArrayList<Pointreg>();
            for (Pointreg pointregCollectionPointregToAttach : angyusers.getPointregCollection()) {
                pointregCollectionPointregToAttach = em.getReference(pointregCollectionPointregToAttach.getClass(), pointregCollectionPointregToAttach.getIdPointreg());
                attachedPointregCollection.add(pointregCollectionPointregToAttach);
            }
            angyusers.setPointregCollection(attachedPointregCollection);
            em.persist(angyusers);
            for (Pointreg pointregCollectionPointreg : angyusers.getPointregCollection()) {
                Angyusers oldIdUserOfPointregCollectionPointreg = pointregCollectionPointreg.getIdUser();
                pointregCollectionPointreg.setIdUser(angyusers);
                pointregCollectionPointreg = em.merge(pointregCollectionPointreg);
                if (oldIdUserOfPointregCollectionPointreg != null) {
                    oldIdUserOfPointregCollectionPointreg.getPointregCollection().remove(pointregCollectionPointreg);
                    oldIdUserOfPointregCollectionPointreg = em.merge(oldIdUserOfPointregCollectionPointreg);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Angyusers angyusers) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Angyusers persistentAngyusers = em.find(Angyusers.class, angyusers.getIdUser());
            Collection<Pointreg> pointregCollectionOld = persistentAngyusers.getPointregCollection();
            Collection<Pointreg> pointregCollectionNew = angyusers.getPointregCollection();
            List<String> illegalOrphanMessages = null;
            for (Pointreg pointregCollectionOldPointreg : pointregCollectionOld) {
                if (!pointregCollectionNew.contains(pointregCollectionOldPointreg)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Pointreg " + pointregCollectionOldPointreg + " since its idUser field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Pointreg> attachedPointregCollectionNew = new ArrayList<Pointreg>();
            for (Pointreg pointregCollectionNewPointregToAttach : pointregCollectionNew) {
                pointregCollectionNewPointregToAttach = em.getReference(pointregCollectionNewPointregToAttach.getClass(), pointregCollectionNewPointregToAttach.getIdPointreg());
                attachedPointregCollectionNew.add(pointregCollectionNewPointregToAttach);
            }
            pointregCollectionNew = attachedPointregCollectionNew;
            angyusers.setPointregCollection(pointregCollectionNew);
            angyusers = em.merge(angyusers);
            for (Pointreg pointregCollectionNewPointreg : pointregCollectionNew) {
                if (!pointregCollectionOld.contains(pointregCollectionNewPointreg)) {
                    Angyusers oldIdUserOfPointregCollectionNewPointreg = pointregCollectionNewPointreg.getIdUser();
                    pointregCollectionNewPointreg.setIdUser(angyusers);
                    pointregCollectionNewPointreg = em.merge(pointregCollectionNewPointreg);
                    if (oldIdUserOfPointregCollectionNewPointreg != null && !oldIdUserOfPointregCollectionNewPointreg.equals(angyusers)) {
                        oldIdUserOfPointregCollectionNewPointreg.getPointregCollection().remove(pointregCollectionNewPointreg);
                        oldIdUserOfPointregCollectionNewPointreg = em.merge(oldIdUserOfPointregCollectionNewPointreg);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = angyusers.getIdUser();
                if (findAngyusers(id) == null) {
                    throw new NonexistentEntityException("The angyusers with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Angyusers angyusers;
            try {
                angyusers = em.getReference(Angyusers.class, id);
                angyusers.getIdUser();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The angyusers with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Pointreg> pointregCollectionOrphanCheck = angyusers.getPointregCollection();
            for (Pointreg pointregCollectionOrphanCheckPointreg : pointregCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Angyusers (" + angyusers + ") cannot be destroyed since the Pointreg " + pointregCollectionOrphanCheckPointreg + " in its pointregCollection field has a non-nullable idUser field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(angyusers);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @SuppressWarnings("unchecked")

    public List<Angyusers> findAllusers() {
        Query query = em.createQuery("SELECT a FROM angyusers a");
        return (List<Angyusers>) query.getResultList();
    }

    public List<Angyusers> findAngyusersEntities() {
        return findAngyusersEntities(true, -1, -1);
    }

    public List<Angyusers> findAngyusersEntities(int maxResults, int firstResult) {
        return findAngyusersEntities(false, maxResults, firstResult);
    }

    private List<Angyusers> findAngyusersEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Angyusers.class));
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

    public Angyusers findAngyusers(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Angyusers.class, id);
        } finally {
            em.close();
        }
    }

    public int getAngyusersCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Angyusers> rt = cq.from(Angyusers.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Angyusers;
import entities.Pointreg;
import services.exceptions.NonexistentEntityException;
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
 * @author Angie_
 */
public class PointregJpaController implements Serializable {

    public PointregJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Pointreg pointreg) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Angyusers idUser = pointreg.getIdUser();
            if (idUser != null) {
                idUser = em.getReference(idUser.getClass(), idUser.getIdUser());
                pointreg.setIdUser(idUser);
            }
            em.persist(pointreg);
            if (idUser != null) {
                idUser.getPointregCollection().add(pointreg);
                idUser = em.merge(idUser);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Pointreg pointreg) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pointreg persistentPointreg = em.find(Pointreg.class, pointreg.getIdPointreg());
            Angyusers idUserOld = persistentPointreg.getIdUser();
            Angyusers idUserNew = pointreg.getIdUser();
            if (idUserNew != null) {
                idUserNew = em.getReference(idUserNew.getClass(), idUserNew.getIdUser());
                pointreg.setIdUser(idUserNew);
            }
            pointreg = em.merge(pointreg);
            if (idUserOld != null && !idUserOld.equals(idUserNew)) {
                idUserOld.getPointregCollection().remove(pointreg);
                idUserOld = em.merge(idUserOld);
            }
            if (idUserNew != null && !idUserNew.equals(idUserOld)) {
                idUserNew.getPointregCollection().add(pointreg);
                idUserNew = em.merge(idUserNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = pointreg.getIdPointreg();
                if (findPointreg(id) == null) {
                    throw new NonexistentEntityException("The pointreg with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pointreg pointreg;
            try {
                pointreg = em.getReference(Pointreg.class, id);
                pointreg.getIdPointreg();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pointreg with id " + id + " no longer exists.", enfe);
            }
            Angyusers idUser = pointreg.getIdUser();
            if (idUser != null) {
                idUser.getPointregCollection().remove(pointreg);
                idUser = em.merge(idUser);
            }
            em.remove(pointreg);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Pointreg> findPointregEntities() {
        return findPointregEntities(true, -1, -1);
    }

    public List<Pointreg> findPointregEntities(int maxResults, int firstResult) {
        return findPointregEntities(false, maxResults, firstResult);
    }

    private List<Pointreg> findPointregEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Pointreg.class));
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

    public Pointreg findPointreg(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pointreg.class, id);
        } finally {
            em.close();
        }
    }

    public int getPointregCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Pointreg> rt = cq.from(Pointreg.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}

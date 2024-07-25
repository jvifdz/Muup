/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import modelos.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import static javax.persistence.Persistence.createEntityManagerFactory;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author jvifdz-GRANDE
 */
public class AlimentosJpaController implements Serializable {

    public AlimentosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    public AlimentosJpaController() {
        this.emf = createEntityManagerFactory("Proyecto_GanaderiaPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Alimentos alimentos) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tipoganaderia fkAlimentos = alimentos.getFkAlimentos();
            if (fkAlimentos != null) {
                fkAlimentos = em.getReference(fkAlimentos.getClass(), fkAlimentos.getIdtipoGanaderia());
                alimentos.setFkAlimentos(fkAlimentos);
            }
            em.persist(alimentos);
            if (fkAlimentos != null) {
                fkAlimentos.getAlimentosList().add(alimentos);
                fkAlimentos = em.merge(fkAlimentos);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Alimentos alimentos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Alimentos persistentAlimentos = em.find(Alimentos.class, alimentos.getIdAlimentos());
            Tipoganaderia fkAlimentosOld = persistentAlimentos.getFkAlimentos();
            Tipoganaderia fkAlimentosNew = alimentos.getFkAlimentos();
            if (fkAlimentosNew != null) {
                fkAlimentosNew = em.getReference(fkAlimentosNew.getClass(), fkAlimentosNew.getIdtipoGanaderia());
                alimentos.setFkAlimentos(fkAlimentosNew);
            }
            alimentos = em.merge(alimentos);
            if (fkAlimentosOld != null && !fkAlimentosOld.equals(fkAlimentosNew)) {
                fkAlimentosOld.getAlimentosList().remove(alimentos);
                fkAlimentosOld = em.merge(fkAlimentosOld);
            }
            if (fkAlimentosNew != null && !fkAlimentosNew.equals(fkAlimentosOld)) {
                fkAlimentosNew.getAlimentosList().add(alimentos);
                fkAlimentosNew = em.merge(fkAlimentosNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = alimentos.getIdAlimentos();
                if (findAlimentos(id) == null) {
                    throw new NonexistentEntityException("The alimentos with id " + id + " no longer exists.");
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
            Alimentos alimentos;
            try {
                alimentos = em.getReference(Alimentos.class, id);
                alimentos.getIdAlimentos();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The alimentos with id " + id + " no longer exists.", enfe);
            }
            Tipoganaderia fkAlimentos = alimentos.getFkAlimentos();
            if (fkAlimentos != null) {
                fkAlimentos.getAlimentosList().remove(alimentos);
                fkAlimentos = em.merge(fkAlimentos);
            }
            em.remove(alimentos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Alimentos> findAlimentosEntities() {
        return findAlimentosEntities(true, -1, -1);
    }

    public List<Alimentos> findAlimentosEntities(int maxResults, int firstResult) {
        return findAlimentosEntities(false, maxResults, firstResult);
    }

    private List<Alimentos> findAlimentosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Alimentos.class));
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

    public Alimentos findAlimentos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Alimentos.class, id);
        } finally {
            em.close();
        }
    }

    public int getAlimentosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Alimentos> rt = cq.from(Alimentos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
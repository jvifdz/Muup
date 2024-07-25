/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import static javax.persistence.Persistence.createEntityManagerFactory;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelos.exceptions.NonexistentEntityException;

/**
 *
 * @author jvifdz-GRANDE
 */
public class HistorialparcelaJpaController implements Serializable {

    public HistorialparcelaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    public HistorialparcelaJpaController() {
        this.emf = createEntityManagerFactory("Proyecto_GanaderiaPU");
    }
    
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Historialparcela historialparcela) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Animal fkAnimal = historialparcela.getFkAnimal();
            if (fkAnimal != null) {
                fkAnimal = em.getReference(fkAnimal.getClass(), fkAnimal.getIdAnimal());
                historialparcela.setFkAnimal(fkAnimal);
            }
            Parcela fkParcela = historialparcela.getFkParcela();
            if (fkParcela != null) {
                fkParcela = em.getReference(fkParcela.getClass(), fkParcela.getIdParcela());
                historialparcela.setFkParcela(fkParcela);
            }
            em.persist(historialparcela);
            if (fkAnimal != null) {
                fkAnimal.getHistorialparcelaList().add(historialparcela);
                fkAnimal = em.merge(fkAnimal);
            }
            if (fkParcela != null) {
                fkParcela.getHistorialparcelaList().add(historialparcela);
                fkParcela = em.merge(fkParcela);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Historialparcela historialparcela) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Historialparcela persistentHistorialparcela = em.find(Historialparcela.class, historialparcela.getIdhistorialParcela());
            Animal fkAnimalOld = persistentHistorialparcela.getFkAnimal();
            Animal fkAnimalNew = historialparcela.getFkAnimal();
            Parcela fkParcelaOld = persistentHistorialparcela.getFkParcela();
            Parcela fkParcelaNew = historialparcela.getFkParcela();
            if (fkAnimalNew != null) {
                fkAnimalNew = em.getReference(fkAnimalNew.getClass(), fkAnimalNew.getIdAnimal());
                historialparcela.setFkAnimal(fkAnimalNew);
            }
            if (fkParcelaNew != null) {
                fkParcelaNew = em.getReference(fkParcelaNew.getClass(), fkParcelaNew.getIdParcela());
                historialparcela.setFkParcela(fkParcelaNew);
            }
            historialparcela = em.merge(historialparcela);
            if (fkAnimalOld != null && !fkAnimalOld.equals(fkAnimalNew)) {
                fkAnimalOld.getHistorialparcelaList().remove(historialparcela);
                fkAnimalOld = em.merge(fkAnimalOld);
            }
            if (fkAnimalNew != null && !fkAnimalNew.equals(fkAnimalOld)) {
                fkAnimalNew.getHistorialparcelaList().add(historialparcela);
                fkAnimalNew = em.merge(fkAnimalNew);
            }
            if (fkParcelaOld != null && !fkParcelaOld.equals(fkParcelaNew)) {
                fkParcelaOld.getHistorialparcelaList().remove(historialparcela);
                fkParcelaOld = em.merge(fkParcelaOld);
            }
            if (fkParcelaNew != null && !fkParcelaNew.equals(fkParcelaOld)) {
                fkParcelaNew.getHistorialparcelaList().add(historialparcela);
                fkParcelaNew = em.merge(fkParcelaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = historialparcela.getIdhistorialParcela();
                if (findHistorialparcela(id) == null) {
                    throw new NonexistentEntityException("The historialparcela with id " + id + " no longer exists.");
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
            Historialparcela historialparcela;
            try {
                historialparcela = em.getReference(Historialparcela.class, id);
                historialparcela.getIdhistorialParcela();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The historialparcela with id " + id + " no longer exists.", enfe);
            }
            Animal fkAnimal = historialparcela.getFkAnimal();
            if (fkAnimal != null) {
                fkAnimal.getHistorialparcelaList().remove(historialparcela);
                fkAnimal = em.merge(fkAnimal);
            }
            Parcela fkParcela = historialparcela.getFkParcela();
            if (fkParcela != null) {
                fkParcela.getHistorialparcelaList().remove(historialparcela);
                fkParcela = em.merge(fkParcela);
            }
            em.remove(historialparcela);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Historialparcela> findHistorialparcelaEntities() {
        return findHistorialparcelaEntities(true, -1, -1);
    }

    public List<Historialparcela> findHistorialparcelaEntities(int maxResults, int firstResult) {
        return findHistorialparcelaEntities(false, maxResults, firstResult);
    }
    
    public List<Historialparcela> historialParcelaAnimalDesc(int id) {
        EntityManager em = emf.createEntityManager();
        Query consulta = em.createQuery("select h from Historialparcela h where h.fkAnimal.idAnimal=:id order by h.fechaEntrada desc");
        consulta.setParameter("id", id);
        List<Historialparcela> lista = consulta.getResultList();
        return lista;
    }

    private List<Historialparcela> findHistorialparcelaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Historialparcela.class));
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

    public Historialparcela findHistorialparcela(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Historialparcela.class, id);
        } finally {
            em.close();
        }
    }

    public int getHistorialparcelaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Historialparcela> rt = cq.from(Historialparcela.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

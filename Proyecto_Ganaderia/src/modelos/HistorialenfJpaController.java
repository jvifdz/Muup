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
public class HistorialenfJpaController implements Serializable {

    public HistorialenfJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    public HistorialenfJpaController() {
        this.emf = createEntityManagerFactory("Proyecto_GanaderiaPU");;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Historialenf historialenf) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Animal fkAnimal = historialenf.getFkAnimal();
            if (fkAnimal != null) {
                fkAnimal = em.getReference(fkAnimal.getClass(), fkAnimal.getIdAnimal());
                historialenf.setFkAnimal(fkAnimal);
            }
            Enfermedad fkEnfermedad = historialenf.getFkEnfermedad();
            if (fkEnfermedad != null) {
                fkEnfermedad = em.getReference(fkEnfermedad.getClass(), fkEnfermedad.getIdEnfermedad());
                historialenf.setFkEnfermedad(fkEnfermedad);
            }
            em.persist(historialenf);
            if (fkAnimal != null) {
                fkAnimal.getHistorialenfList().add(historialenf);
                fkAnimal = em.merge(fkAnimal);
            }
            if (fkEnfermedad != null) {
                fkEnfermedad.getHistorialenfList().add(historialenf);
                fkEnfermedad = em.merge(fkEnfermedad);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Historialenf historialenf) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Historialenf persistentHistorialenf = em.find(Historialenf.class, historialenf.getIdhistorialEnf());
            Animal fkAnimalOld = persistentHistorialenf.getFkAnimal();
            Animal fkAnimalNew = historialenf.getFkAnimal();
            Enfermedad fkEnfermedadOld = persistentHistorialenf.getFkEnfermedad();
            Enfermedad fkEnfermedadNew = historialenf.getFkEnfermedad();
            if (fkAnimalNew != null) {
                fkAnimalNew = em.getReference(fkAnimalNew.getClass(), fkAnimalNew.getIdAnimal());
                historialenf.setFkAnimal(fkAnimalNew);
            }
            if (fkEnfermedadNew != null) {
                fkEnfermedadNew = em.getReference(fkEnfermedadNew.getClass(), fkEnfermedadNew.getIdEnfermedad());
                historialenf.setFkEnfermedad(fkEnfermedadNew);
            }
            historialenf = em.merge(historialenf);
            if (fkAnimalOld != null && !fkAnimalOld.equals(fkAnimalNew)) {
                fkAnimalOld.getHistorialenfList().remove(historialenf);
                fkAnimalOld = em.merge(fkAnimalOld);
            }
            if (fkAnimalNew != null && !fkAnimalNew.equals(fkAnimalOld)) {
                fkAnimalNew.getHistorialenfList().add(historialenf);
                fkAnimalNew = em.merge(fkAnimalNew);
            }
            if (fkEnfermedadOld != null && !fkEnfermedadOld.equals(fkEnfermedadNew)) {
                fkEnfermedadOld.getHistorialenfList().remove(historialenf);
                fkEnfermedadOld = em.merge(fkEnfermedadOld);
            }
            if (fkEnfermedadNew != null && !fkEnfermedadNew.equals(fkEnfermedadOld)) {
                fkEnfermedadNew.getHistorialenfList().add(historialenf);
                fkEnfermedadNew = em.merge(fkEnfermedadNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = historialenf.getIdhistorialEnf();
                if (findHistorialenf(id) == null) {
                    throw new NonexistentEntityException("The historialenf with id " + id + " no longer exists.");
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
            Historialenf historialenf;
            try {
                historialenf = em.getReference(Historialenf.class, id);
                historialenf.getIdhistorialEnf();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The historialenf with id " + id + " no longer exists.", enfe);
            }
            Animal fkAnimal = historialenf.getFkAnimal();
            if (fkAnimal != null) {
                fkAnimal.getHistorialenfList().remove(historialenf);
                fkAnimal = em.merge(fkAnimal);
            }
            Enfermedad fkEnfermedad = historialenf.getFkEnfermedad();
            if (fkEnfermedad != null) {
                fkEnfermedad.getHistorialenfList().remove(historialenf);
                fkEnfermedad = em.merge(fkEnfermedad);
            }
            em.remove(historialenf);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Historialenf> findHistorialenfEntities() {
        return findHistorialenfEntities(true, -1, -1);
    }

    public List<Historialenf> findHistorialenfEntities(int maxResults, int firstResult) {
        return findHistorialenfEntities(false, maxResults, firstResult);
    }
    
    
    
    public List<Historialenf> historialEnfermedadAnimalDesc(int id) {
        EntityManager em = emf.createEntityManager();
        Query consulta = em.createQuery("select h from Historialenf h where h.fkAnimal.idAnimal=:id order by h.fechaEnfermedad desc");
        consulta.setParameter("id", id);
        List<Historialenf> lista = consulta.getResultList();
        return lista;
    }

    private List<Historialenf> findHistorialenfEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Historialenf.class));
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

    public Historialenf findHistorialenf(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Historialenf.class, id);
        } finally {
            em.close();
        }
    }

    public int getHistorialenfCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Historialenf> rt = cq.from(Historialenf.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

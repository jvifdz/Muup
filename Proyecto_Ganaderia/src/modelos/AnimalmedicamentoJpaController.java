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
public class AnimalmedicamentoJpaController implements Serializable {

    public AnimalmedicamentoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public AnimalmedicamentoJpaController() {
        this.emf = createEntityManagerFactory("Proyecto_GanaderiaPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Animalmedicamento animalmedicamento) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Animal fkAnimal = animalmedicamento.getFkAnimal();
            if (fkAnimal != null) {
                fkAnimal = em.getReference(fkAnimal.getClass(), fkAnimal.getIdAnimal());
                animalmedicamento.setFkAnimal(fkAnimal);
            }
            Medicamento fkMedicamento = animalmedicamento.getFkMedicamento();
            if (fkMedicamento != null) {
                fkMedicamento = em.getReference(fkMedicamento.getClass(), fkMedicamento.getIdMedicamento());
                animalmedicamento.setFkMedicamento(fkMedicamento);
            }
            em.persist(animalmedicamento);
            if (fkAnimal != null) {
                fkAnimal.getAnimalmedicamentoList().add(animalmedicamento);
                fkAnimal = em.merge(fkAnimal);
            }
            if (fkMedicamento != null) {
                fkMedicamento.getAnimalmedicamentoList().add(animalmedicamento);
                fkMedicamento = em.merge(fkMedicamento);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Animalmedicamento animalmedicamento) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Animalmedicamento persistentAnimalmedicamento = em.find(Animalmedicamento.class, animalmedicamento.getIdanimalMedicamento());
            Animal fkAnimalOld = persistentAnimalmedicamento.getFkAnimal();
            Animal fkAnimalNew = animalmedicamento.getFkAnimal();
            Medicamento fkMedicamentoOld = persistentAnimalmedicamento.getFkMedicamento();
            Medicamento fkMedicamentoNew = animalmedicamento.getFkMedicamento();
            if (fkAnimalNew != null) {
                fkAnimalNew = em.getReference(fkAnimalNew.getClass(), fkAnimalNew.getIdAnimal());
                animalmedicamento.setFkAnimal(fkAnimalNew);
            }
            if (fkMedicamentoNew != null) {
                fkMedicamentoNew = em.getReference(fkMedicamentoNew.getClass(), fkMedicamentoNew.getIdMedicamento());
                animalmedicamento.setFkMedicamento(fkMedicamentoNew);
            }
            animalmedicamento = em.merge(animalmedicamento);
            if (fkAnimalOld != null && !fkAnimalOld.equals(fkAnimalNew)) {
                fkAnimalOld.getAnimalmedicamentoList().remove(animalmedicamento);
                fkAnimalOld = em.merge(fkAnimalOld);
            }
            if (fkAnimalNew != null && !fkAnimalNew.equals(fkAnimalOld)) {
                fkAnimalNew.getAnimalmedicamentoList().add(animalmedicamento);
                fkAnimalNew = em.merge(fkAnimalNew);
            }
            if (fkMedicamentoOld != null && !fkMedicamentoOld.equals(fkMedicamentoNew)) {
                fkMedicamentoOld.getAnimalmedicamentoList().remove(animalmedicamento);
                fkMedicamentoOld = em.merge(fkMedicamentoOld);
            }
            if (fkMedicamentoNew != null && !fkMedicamentoNew.equals(fkMedicamentoOld)) {
                fkMedicamentoNew.getAnimalmedicamentoList().add(animalmedicamento);
                fkMedicamentoNew = em.merge(fkMedicamentoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = animalmedicamento.getIdanimalMedicamento();
                if (findAnimalmedicamento(id) == null) {
                    throw new NonexistentEntityException("The animalmedicamento with id " + id + " no longer exists.");
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
            Animalmedicamento animalmedicamento;
            try {
                animalmedicamento = em.getReference(Animalmedicamento.class, id);
                animalmedicamento.getIdanimalMedicamento();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The animalmedicamento with id " + id + " no longer exists.", enfe);
            }
            Animal fkAnimal = animalmedicamento.getFkAnimal();
            if (fkAnimal != null) {
                fkAnimal.getAnimalmedicamentoList().remove(animalmedicamento);
                fkAnimal = em.merge(fkAnimal);
            }
            Medicamento fkMedicamento = animalmedicamento.getFkMedicamento();
            if (fkMedicamento != null) {
                fkMedicamento.getAnimalmedicamentoList().remove(animalmedicamento);
                fkMedicamento = em.merge(fkMedicamento);
            }
            em.remove(animalmedicamento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Animalmedicamento> findAnimalmedicamentoEntities() {
        return findAnimalmedicamentoEntities(true, -1, -1);
    }

    public List<Animalmedicamento> findAnimalmedicamentoEntities(int maxResults, int firstResult) {
        return findAnimalmedicamentoEntities(false, maxResults, firstResult);
    }
    
    
    public List<Animalmedicamento> AnimalMedicamentoDesc(int id) {
        EntityManager em = emf.createEntityManager();
        Query consulta = em.createQuery("select h from Animalmedicamento h where h.fkAnimal.idAnimal=:id order by h.fechaVacunacion desc");
        consulta.setParameter("id", id);
        List<Animalmedicamento> lista = consulta.getResultList();
        return lista;
    }
    

    private List<Animalmedicamento> findAnimalmedicamentoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Animalmedicamento.class));
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

    public Animalmedicamento findAnimalmedicamento(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Animalmedicamento.class, id);
        } finally {
            em.close();
        }
    }

    public int getAnimalmedicamentoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Animalmedicamento> rt = cq.from(Animalmedicamento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

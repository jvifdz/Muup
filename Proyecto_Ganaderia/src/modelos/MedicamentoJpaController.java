/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import modelos.exceptions.IllegalOrphanException;
import modelos.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import static javax.persistence.Persistence.createEntityManagerFactory;

/**
 *
 * @author jvifdz-GRANDE
 */
public class MedicamentoJpaController implements Serializable {

    public MedicamentoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    public MedicamentoJpaController() {
        this.emf = createEntityManagerFactory("Proyecto_GanaderiaPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Medicamento medicamento) {
        if (medicamento.getAnimalmedicamentoList() == null) {
            medicamento.setAnimalmedicamentoList(new ArrayList<Animalmedicamento>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Animalmedicamento> attachedAnimalmedicamentoList = new ArrayList<Animalmedicamento>();
            for (Animalmedicamento animalmedicamentoListAnimalmedicamentoToAttach : medicamento.getAnimalmedicamentoList()) {
                animalmedicamentoListAnimalmedicamentoToAttach = em.getReference(animalmedicamentoListAnimalmedicamentoToAttach.getClass(), animalmedicamentoListAnimalmedicamentoToAttach.getIdanimalMedicamento());
                attachedAnimalmedicamentoList.add(animalmedicamentoListAnimalmedicamentoToAttach);
            }
            medicamento.setAnimalmedicamentoList(attachedAnimalmedicamentoList);
            em.persist(medicamento);
            for (Animalmedicamento animalmedicamentoListAnimalmedicamento : medicamento.getAnimalmedicamentoList()) {
                Medicamento oldFkMedicamentoOfAnimalmedicamentoListAnimalmedicamento = animalmedicamentoListAnimalmedicamento.getFkMedicamento();
                animalmedicamentoListAnimalmedicamento.setFkMedicamento(medicamento);
                animalmedicamentoListAnimalmedicamento = em.merge(animalmedicamentoListAnimalmedicamento);
                if (oldFkMedicamentoOfAnimalmedicamentoListAnimalmedicamento != null) {
                    oldFkMedicamentoOfAnimalmedicamentoListAnimalmedicamento.getAnimalmedicamentoList().remove(animalmedicamentoListAnimalmedicamento);
                    oldFkMedicamentoOfAnimalmedicamentoListAnimalmedicamento = em.merge(oldFkMedicamentoOfAnimalmedicamentoListAnimalmedicamento);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Medicamento medicamento) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Medicamento persistentMedicamento = em.find(Medicamento.class, medicamento.getIdMedicamento());
            List<Animalmedicamento> animalmedicamentoListOld = persistentMedicamento.getAnimalmedicamentoList();
            List<Animalmedicamento> animalmedicamentoListNew = medicamento.getAnimalmedicamentoList();
            List<String> illegalOrphanMessages = null;
            for (Animalmedicamento animalmedicamentoListOldAnimalmedicamento : animalmedicamentoListOld) {
                if (!animalmedicamentoListNew.contains(animalmedicamentoListOldAnimalmedicamento)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Animalmedicamento " + animalmedicamentoListOldAnimalmedicamento + " since its fkMedicamento field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Animalmedicamento> attachedAnimalmedicamentoListNew = new ArrayList<Animalmedicamento>();
            for (Animalmedicamento animalmedicamentoListNewAnimalmedicamentoToAttach : animalmedicamentoListNew) {
                animalmedicamentoListNewAnimalmedicamentoToAttach = em.getReference(animalmedicamentoListNewAnimalmedicamentoToAttach.getClass(), animalmedicamentoListNewAnimalmedicamentoToAttach.getIdanimalMedicamento());
                attachedAnimalmedicamentoListNew.add(animalmedicamentoListNewAnimalmedicamentoToAttach);
            }
            animalmedicamentoListNew = attachedAnimalmedicamentoListNew;
            medicamento.setAnimalmedicamentoList(animalmedicamentoListNew);
            medicamento = em.merge(medicamento);
            for (Animalmedicamento animalmedicamentoListNewAnimalmedicamento : animalmedicamentoListNew) {
                if (!animalmedicamentoListOld.contains(animalmedicamentoListNewAnimalmedicamento)) {
                    Medicamento oldFkMedicamentoOfAnimalmedicamentoListNewAnimalmedicamento = animalmedicamentoListNewAnimalmedicamento.getFkMedicamento();
                    animalmedicamentoListNewAnimalmedicamento.setFkMedicamento(medicamento);
                    animalmedicamentoListNewAnimalmedicamento = em.merge(animalmedicamentoListNewAnimalmedicamento);
                    if (oldFkMedicamentoOfAnimalmedicamentoListNewAnimalmedicamento != null && !oldFkMedicamentoOfAnimalmedicamentoListNewAnimalmedicamento.equals(medicamento)) {
                        oldFkMedicamentoOfAnimalmedicamentoListNewAnimalmedicamento.getAnimalmedicamentoList().remove(animalmedicamentoListNewAnimalmedicamento);
                        oldFkMedicamentoOfAnimalmedicamentoListNewAnimalmedicamento = em.merge(oldFkMedicamentoOfAnimalmedicamentoListNewAnimalmedicamento);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = medicamento.getIdMedicamento();
                if (findMedicamento(id) == null) {
                    throw new NonexistentEntityException("The medicamento with id " + id + " no longer exists.");
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
            Medicamento medicamento;
            try {
                medicamento = em.getReference(Medicamento.class, id);
                medicamento.getIdMedicamento();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The medicamento with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Animalmedicamento> animalmedicamentoListOrphanCheck = medicamento.getAnimalmedicamentoList();
            for (Animalmedicamento animalmedicamentoListOrphanCheckAnimalmedicamento : animalmedicamentoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Medicamento (" + medicamento + ") cannot be destroyed since the Animalmedicamento " + animalmedicamentoListOrphanCheckAnimalmedicamento + " in its animalmedicamentoList field has a non-nullable fkMedicamento field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(medicamento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Medicamento> findMedicamentoEntities() {
        return findMedicamentoEntities(true, -1, -1);
    }

    public List<Medicamento> findMedicamentoEntities(int maxResults, int firstResult) {
        return findMedicamentoEntities(false, maxResults, firstResult);
    }
    public List<Medicamento> MedicamentoNombre(String nombre) {
        EntityManager em = emf.createEntityManager();
        Query consulta = em.createQuery("SELECT m FROM Medicamento m where m.nombre like :nombre");
        consulta.setParameter("nombre", "%"+nombre+"%");
        List<Medicamento> lista = consulta.getResultList();
        return lista;
    }

    private List<Medicamento> findMedicamentoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Medicamento.class));
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

    public Medicamento findMedicamento(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Medicamento.class, id);
        } finally {
            em.close();
        }
    }

    public int getMedicamentoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Medicamento> rt = cq.from(Medicamento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
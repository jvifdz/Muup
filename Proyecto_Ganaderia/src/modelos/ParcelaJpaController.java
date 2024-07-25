/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

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
import modelos.exceptions.IllegalOrphanException;
import modelos.exceptions.NonexistentEntityException;

/**
 *
 * @author jvifdz-GRANDE
 */
public class ParcelaJpaController implements Serializable {

    public ParcelaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    public ParcelaJpaController() {
        this.emf = createEntityManagerFactory("Proyecto_GanaderiaPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Parcela parcela) {
        if (parcela.getHistorialparcelaList() == null) {
            parcela.setHistorialparcelaList(new ArrayList<Historialparcela>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Historialparcela> attachedHistorialparcelaList = new ArrayList<Historialparcela>();
            for (Historialparcela historialparcelaListHistorialparcelaToAttach : parcela.getHistorialparcelaList()) {
                historialparcelaListHistorialparcelaToAttach = em.getReference(historialparcelaListHistorialparcelaToAttach.getClass(), historialparcelaListHistorialparcelaToAttach.getIdhistorialParcela());
                attachedHistorialparcelaList.add(historialparcelaListHistorialparcelaToAttach);
            }
            parcela.setHistorialparcelaList(attachedHistorialparcelaList);
            em.persist(parcela);
            for (Historialparcela historialparcelaListHistorialparcela : parcela.getHistorialparcelaList()) {
                Parcela oldFkParcelaOfHistorialparcelaListHistorialparcela = historialparcelaListHistorialparcela.getFkParcela();
                historialparcelaListHistorialparcela.setFkParcela(parcela);
                historialparcelaListHistorialparcela = em.merge(historialparcelaListHistorialparcela);
                if (oldFkParcelaOfHistorialparcelaListHistorialparcela != null) {
                    oldFkParcelaOfHistorialparcelaListHistorialparcela.getHistorialparcelaList().remove(historialparcelaListHistorialparcela);
                    oldFkParcelaOfHistorialparcelaListHistorialparcela = em.merge(oldFkParcelaOfHistorialparcelaListHistorialparcela);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Parcela parcela) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Parcela persistentParcela = em.find(Parcela.class, parcela.getIdParcela());
            List<Historialparcela> historialparcelaListOld = persistentParcela.getHistorialparcelaList();
            List<Historialparcela> historialparcelaListNew = parcela.getHistorialparcelaList();
            List<String> illegalOrphanMessages = null;
            for (Historialparcela historialparcelaListOldHistorialparcela : historialparcelaListOld) {
                if (!historialparcelaListNew.contains(historialparcelaListOldHistorialparcela)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Historialparcela " + historialparcelaListOldHistorialparcela + " since its fkParcela field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Historialparcela> attachedHistorialparcelaListNew = new ArrayList<Historialparcela>();
            for (Historialparcela historialparcelaListNewHistorialparcelaToAttach : historialparcelaListNew) {
                historialparcelaListNewHistorialparcelaToAttach = em.getReference(historialparcelaListNewHistorialparcelaToAttach.getClass(), historialparcelaListNewHistorialparcelaToAttach.getIdhistorialParcela());
                attachedHistorialparcelaListNew.add(historialparcelaListNewHistorialparcelaToAttach);
            }
            historialparcelaListNew = attachedHistorialparcelaListNew;
            parcela.setHistorialparcelaList(historialparcelaListNew);
            parcela = em.merge(parcela);
            for (Historialparcela historialparcelaListNewHistorialparcela : historialparcelaListNew) {
                if (!historialparcelaListOld.contains(historialparcelaListNewHistorialparcela)) {
                    Parcela oldFkParcelaOfHistorialparcelaListNewHistorialparcela = historialparcelaListNewHistorialparcela.getFkParcela();
                    historialparcelaListNewHistorialparcela.setFkParcela(parcela);
                    historialparcelaListNewHistorialparcela = em.merge(historialparcelaListNewHistorialparcela);
                    if (oldFkParcelaOfHistorialparcelaListNewHistorialparcela != null && !oldFkParcelaOfHistorialparcelaListNewHistorialparcela.equals(parcela)) {
                        oldFkParcelaOfHistorialparcelaListNewHistorialparcela.getHistorialparcelaList().remove(historialparcelaListNewHistorialparcela);
                        oldFkParcelaOfHistorialparcelaListNewHistorialparcela = em.merge(oldFkParcelaOfHistorialparcelaListNewHistorialparcela);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = parcela.getIdParcela();
                if (findParcela(id) == null) {
                    throw new NonexistentEntityException("The parcela with id " + id + " no longer exists.");
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
            Parcela parcela;
            try {
                parcela = em.getReference(Parcela.class, id);
                parcela.getIdParcela();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The parcela with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Historialparcela> historialparcelaListOrphanCheck = parcela.getHistorialparcelaList();
            for (Historialparcela historialparcelaListOrphanCheckHistorialparcela : historialparcelaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Parcela (" + parcela + ") cannot be destroyed since the Historialparcela " + historialparcelaListOrphanCheckHistorialparcela + " in its historialparcelaList field has a non-nullable fkParcela field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(parcela);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Parcela> findParcelaEntities() {
        return findParcelaEntities(true, -1, -1);
    }

    public List<Parcela> findParcelaEntities(int maxResults, int firstResult) {
        return findParcelaEntities(false, maxResults, firstResult);
    }
    
    public List<Parcela> ParcelasNombre(String nombre) {
        EntityManager em = emf.createEntityManager();
        Query consulta = em.createQuery("SELECT p FROM Parcela p where p.lugarSituada like :nombre");
        consulta.setParameter("nombre", "%"+nombre+"%");
        List<Parcela> lista = consulta.getResultList();
        return lista;
    }
    

    private List<Parcela> findParcelaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Parcela.class));
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

    public Parcela findParcela(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Parcela.class, id);
        } finally {
            em.close();
        }
    }

    public int getParcelaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Parcela> rt = cq.from(Parcela.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

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
public class EnfermedadJpaController implements Serializable {

    public EnfermedadJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    public EnfermedadJpaController() {
        this.emf = createEntityManagerFactory("Proyecto_GanaderiaPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Enfermedad enfermedad) {
        if (enfermedad.getHistorialenfList() == null) {
            enfermedad.setHistorialenfList(new ArrayList<Historialenf>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Historialenf> attachedHistorialenfList = new ArrayList<Historialenf>();
            for (Historialenf historialenfListHistorialenfToAttach : enfermedad.getHistorialenfList()) {
                historialenfListHistorialenfToAttach = em.getReference(historialenfListHistorialenfToAttach.getClass(), historialenfListHistorialenfToAttach.getIdhistorialEnf());
                attachedHistorialenfList.add(historialenfListHistorialenfToAttach);
            }
            enfermedad.setHistorialenfList(attachedHistorialenfList);
            em.persist(enfermedad);
            for (Historialenf historialenfListHistorialenf : enfermedad.getHistorialenfList()) {
                Enfermedad oldFkEnfermedadOfHistorialenfListHistorialenf = historialenfListHistorialenf.getFkEnfermedad();
                historialenfListHistorialenf.setFkEnfermedad(enfermedad);
                historialenfListHistorialenf = em.merge(historialenfListHistorialenf);
                if (oldFkEnfermedadOfHistorialenfListHistorialenf != null) {
                    oldFkEnfermedadOfHistorialenfListHistorialenf.getHistorialenfList().remove(historialenfListHistorialenf);
                    oldFkEnfermedadOfHistorialenfListHistorialenf = em.merge(oldFkEnfermedadOfHistorialenfListHistorialenf);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Enfermedad enfermedad) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Enfermedad persistentEnfermedad = em.find(Enfermedad.class, enfermedad.getIdEnfermedad());
            List<Historialenf> historialenfListOld = persistentEnfermedad.getHistorialenfList();
            List<Historialenf> historialenfListNew = enfermedad.getHistorialenfList();
            List<String> illegalOrphanMessages = null;
            for (Historialenf historialenfListOldHistorialenf : historialenfListOld) {
                if (!historialenfListNew.contains(historialenfListOldHistorialenf)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Historialenf " + historialenfListOldHistorialenf + " since its fkEnfermedad field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Historialenf> attachedHistorialenfListNew = new ArrayList<Historialenf>();
            for (Historialenf historialenfListNewHistorialenfToAttach : historialenfListNew) {
                historialenfListNewHistorialenfToAttach = em.getReference(historialenfListNewHistorialenfToAttach.getClass(), historialenfListNewHistorialenfToAttach.getIdhistorialEnf());
                attachedHistorialenfListNew.add(historialenfListNewHistorialenfToAttach);
            }
            historialenfListNew = attachedHistorialenfListNew;
            enfermedad.setHistorialenfList(historialenfListNew);
            enfermedad = em.merge(enfermedad);
            for (Historialenf historialenfListNewHistorialenf : historialenfListNew) {
                if (!historialenfListOld.contains(historialenfListNewHistorialenf)) {
                    Enfermedad oldFkEnfermedadOfHistorialenfListNewHistorialenf = historialenfListNewHistorialenf.getFkEnfermedad();
                    historialenfListNewHistorialenf.setFkEnfermedad(enfermedad);
                    historialenfListNewHistorialenf = em.merge(historialenfListNewHistorialenf);
                    if (oldFkEnfermedadOfHistorialenfListNewHistorialenf != null && !oldFkEnfermedadOfHistorialenfListNewHistorialenf.equals(enfermedad)) {
                        oldFkEnfermedadOfHistorialenfListNewHistorialenf.getHistorialenfList().remove(historialenfListNewHistorialenf);
                        oldFkEnfermedadOfHistorialenfListNewHistorialenf = em.merge(oldFkEnfermedadOfHistorialenfListNewHistorialenf);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = enfermedad.getIdEnfermedad();
                if (findEnfermedad(id) == null) {
                    throw new NonexistentEntityException("The enfermedad with id " + id + " no longer exists.");
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
            Enfermedad enfermedad;
            try {
                enfermedad = em.getReference(Enfermedad.class, id);
                enfermedad.getIdEnfermedad();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The enfermedad with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Historialenf> historialenfListOrphanCheck = enfermedad.getHistorialenfList();
            for (Historialenf historialenfListOrphanCheckHistorialenf : historialenfListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Enfermedad (" + enfermedad + ") cannot be destroyed since the Historialenf " + historialenfListOrphanCheckHistorialenf + " in its historialenfList field has a non-nullable fkEnfermedad field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(enfermedad);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Enfermedad> findEnfermedadEntities() {
        return findEnfermedadEntities(true, -1, -1);
    }

    public List<Enfermedad> findEnfermedadEntities(int maxResults, int firstResult) {
        return findEnfermedadEntities(false, maxResults, firstResult);
    }
    public List<Enfermedad> EnfermedadNombre(String nombre) {
        EntityManager em = emf.createEntityManager();
        Query consulta = em.createQuery("SELECT e FROM Enfermedad e where e.nombre like :nombre");
        consulta.setParameter("nombre", "%"+nombre+"%");
        List<Enfermedad> lista = consulta.getResultList();
        return lista;
    }

    private List<Enfermedad> findEnfermedadEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Enfermedad.class));
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

    public Enfermedad findEnfermedad(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Enfermedad.class, id);
        } finally {
            em.close();
        }
    }

    public int getEnfermedadCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Enfermedad> rt = cq.from(Enfermedad.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

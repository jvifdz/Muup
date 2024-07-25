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
public class DatosnegocioJpaController implements Serializable {

    public DatosnegocioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    public DatosnegocioJpaController() {
        this.emf = createEntityManagerFactory("Proyecto_GanaderiaPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Datosnegocio datosnegocio) {
        if (datosnegocio.getFacturaList() == null) {
            datosnegocio.setFacturaList(new ArrayList<Factura>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Factura> attachedFacturaList = new ArrayList<Factura>();
            for (Factura facturaListFacturaToAttach : datosnegocio.getFacturaList()) {
                facturaListFacturaToAttach = em.getReference(facturaListFacturaToAttach.getClass(), facturaListFacturaToAttach.getIdFactura());
                attachedFacturaList.add(facturaListFacturaToAttach);
            }
            datosnegocio.setFacturaList(attachedFacturaList);
            em.persist(datosnegocio);
            for (Factura facturaListFactura : datosnegocio.getFacturaList()) {
                Datosnegocio oldFkdatosNegocioOfFacturaListFactura = facturaListFactura.getFkdatosNegocio();
                facturaListFactura.setFkdatosNegocio(datosnegocio);
                facturaListFactura = em.merge(facturaListFactura);
                if (oldFkdatosNegocioOfFacturaListFactura != null) {
                    oldFkdatosNegocioOfFacturaListFactura.getFacturaList().remove(facturaListFactura);
                    oldFkdatosNegocioOfFacturaListFactura = em.merge(oldFkdatosNegocioOfFacturaListFactura);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Datosnegocio datosnegocio) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Datosnegocio persistentDatosnegocio = em.find(Datosnegocio.class, datosnegocio.getIddatosNegocio());
            List<Factura> facturaListOld = persistentDatosnegocio.getFacturaList();
            List<Factura> facturaListNew = datosnegocio.getFacturaList();
            List<String> illegalOrphanMessages = null;
            for (Factura facturaListOldFactura : facturaListOld) {
                if (!facturaListNew.contains(facturaListOldFactura)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Factura " + facturaListOldFactura + " since its fkdatosNegocio field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Factura> attachedFacturaListNew = new ArrayList<Factura>();
            for (Factura facturaListNewFacturaToAttach : facturaListNew) {
                facturaListNewFacturaToAttach = em.getReference(facturaListNewFacturaToAttach.getClass(), facturaListNewFacturaToAttach.getIdFactura());
                attachedFacturaListNew.add(facturaListNewFacturaToAttach);
            }
            facturaListNew = attachedFacturaListNew;
            datosnegocio.setFacturaList(facturaListNew);
            datosnegocio = em.merge(datosnegocio);
            for (Factura facturaListNewFactura : facturaListNew) {
                if (!facturaListOld.contains(facturaListNewFactura)) {
                    Datosnegocio oldFkdatosNegocioOfFacturaListNewFactura = facturaListNewFactura.getFkdatosNegocio();
                    facturaListNewFactura.setFkdatosNegocio(datosnegocio);
                    facturaListNewFactura = em.merge(facturaListNewFactura);
                    if (oldFkdatosNegocioOfFacturaListNewFactura != null && !oldFkdatosNegocioOfFacturaListNewFactura.equals(datosnegocio)) {
                        oldFkdatosNegocioOfFacturaListNewFactura.getFacturaList().remove(facturaListNewFactura);
                        oldFkdatosNegocioOfFacturaListNewFactura = em.merge(oldFkdatosNegocioOfFacturaListNewFactura);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = datosnegocio.getIddatosNegocio();
                if (findDatosnegocio(id) == null) {
                    throw new NonexistentEntityException("The datosnegocio with id " + id + " no longer exists.");
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
            Datosnegocio datosnegocio;
            try {
                datosnegocio = em.getReference(Datosnegocio.class, id);
                datosnegocio.getIddatosNegocio();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The datosnegocio with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Factura> facturaListOrphanCheck = datosnegocio.getFacturaList();
            for (Factura facturaListOrphanCheckFactura : facturaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Datosnegocio (" + datosnegocio + ") cannot be destroyed since the Factura " + facturaListOrphanCheckFactura + " in its facturaList field has a non-nullable fkdatosNegocio field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(datosnegocio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Datosnegocio> findDatosnegocioEntities() {
        return findDatosnegocioEntities(true, -1, -1);
    }

    public List<Datosnegocio> findDatosnegocioEntities(int maxResults, int firstResult) {
        return findDatosnegocioEntities(false, maxResults, firstResult);
    }

    private List<Datosnegocio> findDatosnegocioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Datosnegocio.class));
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

    public Datosnegocio findDatosnegocio(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Datosnegocio.class, id);
        } finally {
            em.close();
        }
    }

    public int getDatosnegocioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Datosnegocio> rt = cq.from(Datosnegocio.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

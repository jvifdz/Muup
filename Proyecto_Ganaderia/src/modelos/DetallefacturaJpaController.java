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
public class DetallefacturaJpaController implements Serializable {

    public DetallefacturaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    public DetallefacturaJpaController() {
        this.emf = createEntityManagerFactory("Proyecto_GanaderiaPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Detallefactura detallefactura) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Factura fkFactura = detallefactura.getFkFactura();
            if (fkFactura != null) {
                fkFactura = em.getReference(fkFactura.getClass(), fkFactura.getIdFactura());
                detallefactura.setFkFactura(fkFactura);
            }
            Animal fkAnimal = detallefactura.getFkAnimal();
            if (fkAnimal != null) {
                fkAnimal = em.getReference(fkAnimal.getClass(), fkAnimal.getIdAnimal());
                detallefactura.setFkAnimal(fkAnimal);
            }
            em.persist(detallefactura);
            if (fkFactura != null) {
                fkFactura.getDetallefacturaList().add(detallefactura);
                fkFactura = em.merge(fkFactura);
            }
            if (fkAnimal != null) {
                fkAnimal.getDetallefacturaList().add(detallefactura);
                fkAnimal = em.merge(fkAnimal);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Detallefactura detallefactura) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detallefactura persistentDetallefactura = em.find(Detallefactura.class, detallefactura.getIdDetalle());
            Factura fkFacturaOld = persistentDetallefactura.getFkFactura();
            Factura fkFacturaNew = detallefactura.getFkFactura();
            Animal fkAnimalOld = persistentDetallefactura.getFkAnimal();
            Animal fkAnimalNew = detallefactura.getFkAnimal();
            if (fkFacturaNew != null) {
                fkFacturaNew = em.getReference(fkFacturaNew.getClass(), fkFacturaNew.getIdFactura());
                detallefactura.setFkFactura(fkFacturaNew);
            }
            if (fkAnimalNew != null) {
                fkAnimalNew = em.getReference(fkAnimalNew.getClass(), fkAnimalNew.getIdAnimal());
                detallefactura.setFkAnimal(fkAnimalNew);
            }
            detallefactura = em.merge(detallefactura);
            if (fkFacturaOld != null && !fkFacturaOld.equals(fkFacturaNew)) {
                fkFacturaOld.getDetallefacturaList().remove(detallefactura);
                fkFacturaOld = em.merge(fkFacturaOld);
            }
            if (fkFacturaNew != null && !fkFacturaNew.equals(fkFacturaOld)) {
                fkFacturaNew.getDetallefacturaList().add(detallefactura);
                fkFacturaNew = em.merge(fkFacturaNew);
            }
            if (fkAnimalOld != null && !fkAnimalOld.equals(fkAnimalNew)) {
                fkAnimalOld.getDetallefacturaList().remove(detallefactura);
                fkAnimalOld = em.merge(fkAnimalOld);
            }
            if (fkAnimalNew != null && !fkAnimalNew.equals(fkAnimalOld)) {
                fkAnimalNew.getDetallefacturaList().add(detallefactura);
                fkAnimalNew = em.merge(fkAnimalNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = detallefactura.getIdDetalle();
                if (findDetallefactura(id) == null) {
                    throw new NonexistentEntityException("The detallefactura with id " + id + " no longer exists.");
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
            Detallefactura detallefactura;
            try {
                detallefactura = em.getReference(Detallefactura.class, id);
                detallefactura.getIdDetalle();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detallefactura with id " + id + " no longer exists.", enfe);
            }
            Factura fkFactura = detallefactura.getFkFactura();
            if (fkFactura != null) {
                fkFactura.getDetallefacturaList().remove(detallefactura);
                fkFactura = em.merge(fkFactura);
            }
            Animal fkAnimal = detallefactura.getFkAnimal();
            if (fkAnimal != null) {
                fkAnimal.getDetallefacturaList().remove(detallefactura);
                fkAnimal = em.merge(fkAnimal);
            }
            em.remove(detallefactura);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Detallefactura> findDetallefacturaEntities() {
        return findDetallefacturaEntities(true, -1, -1);
    }

    public List<Detallefactura> findDetallefacturaEntities(int maxResults, int firstResult) {
        return findDetallefacturaEntities(false, maxResults, firstResult);
    }
    
    public List<Detallefactura> detalleFactura(int id) {
        EntityManager em = emf.createEntityManager();
        Query consulta = em.createQuery("select d from Detallefactura d where d.fkFactura.idFactura=:id");
        consulta.setParameter("id", id);
        List<Detallefactura> lista = consulta.getResultList();
        return lista;
    }

    private List<Detallefactura> findDetallefacturaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Detallefactura.class));
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

    public Detallefactura findDetallefactura(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Detallefactura.class, id);
        } finally {
            em.close();
        }
    }

    public int getDetallefacturaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Detallefactura> rt = cq.from(Detallefactura.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

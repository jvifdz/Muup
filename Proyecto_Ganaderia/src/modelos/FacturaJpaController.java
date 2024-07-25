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
public class FacturaJpaController implements Serializable {

    public FacturaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    public FacturaJpaController() {
        this.emf = createEntityManagerFactory("Proyecto_GanaderiaPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Factura factura) {
        if (factura.getDetallefacturaList() == null) {
            factura.setDetallefacturaList(new ArrayList<Detallefactura>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente fkCliente = factura.getFkCliente();
            if (fkCliente != null) {
                fkCliente = em.getReference(fkCliente.getClass(), fkCliente.getIdCliente());
                factura.setFkCliente(fkCliente);
            }
            Datosnegocio fkdatosNegocio = factura.getFkdatosNegocio();
            if (fkdatosNegocio != null) {
                fkdatosNegocio = em.getReference(fkdatosNegocio.getClass(), fkdatosNegocio.getIddatosNegocio());
                factura.setFkdatosNegocio(fkdatosNegocio);
            }
            List<Detallefactura> attachedDetallefacturaList = new ArrayList<Detallefactura>();
            for (Detallefactura detallefacturaListDetallefacturaToAttach : factura.getDetallefacturaList()) {
                detallefacturaListDetallefacturaToAttach = em.getReference(detallefacturaListDetallefacturaToAttach.getClass(), detallefacturaListDetallefacturaToAttach.getIdDetalle());
                attachedDetallefacturaList.add(detallefacturaListDetallefacturaToAttach);
            }
            factura.setDetallefacturaList(attachedDetallefacturaList);
            em.persist(factura);
            if (fkCliente != null) {
                fkCliente.getFacturaList().add(factura);
                fkCliente = em.merge(fkCliente);
            }
            if (fkdatosNegocio != null) {
                fkdatosNegocio.getFacturaList().add(factura);
                fkdatosNegocio = em.merge(fkdatosNegocio);
            }
            for (Detallefactura detallefacturaListDetallefactura : factura.getDetallefacturaList()) {
                Factura oldFkFacturaOfDetallefacturaListDetallefactura = detallefacturaListDetallefactura.getFkFactura();
                detallefacturaListDetallefactura.setFkFactura(factura);
                detallefacturaListDetallefactura = em.merge(detallefacturaListDetallefactura);
                if (oldFkFacturaOfDetallefacturaListDetallefactura != null) {
                    oldFkFacturaOfDetallefacturaListDetallefactura.getDetallefacturaList().remove(detallefacturaListDetallefactura);
                    oldFkFacturaOfDetallefacturaListDetallefactura = em.merge(oldFkFacturaOfDetallefacturaListDetallefactura);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Factura factura) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Factura persistentFactura = em.find(Factura.class, factura.getIdFactura());
            Cliente fkClienteOld = persistentFactura.getFkCliente();
            Cliente fkClienteNew = factura.getFkCliente();
            Datosnegocio fkdatosNegocioOld = persistentFactura.getFkdatosNegocio();
            Datosnegocio fkdatosNegocioNew = factura.getFkdatosNegocio();
            List<Detallefactura> detallefacturaListOld = persistentFactura.getDetallefacturaList();
            List<Detallefactura> detallefacturaListNew = factura.getDetallefacturaList();
            List<String> illegalOrphanMessages = null;
            for (Detallefactura detallefacturaListOldDetallefactura : detallefacturaListOld) {
                if (!detallefacturaListNew.contains(detallefacturaListOldDetallefactura)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detallefactura " + detallefacturaListOldDetallefactura + " since its fkFactura field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (fkClienteNew != null) {
                fkClienteNew = em.getReference(fkClienteNew.getClass(), fkClienteNew.getIdCliente());
                factura.setFkCliente(fkClienteNew);
            }
            if (fkdatosNegocioNew != null) {
                fkdatosNegocioNew = em.getReference(fkdatosNegocioNew.getClass(), fkdatosNegocioNew.getIddatosNegocio());
                factura.setFkdatosNegocio(fkdatosNegocioNew);
            }
            List<Detallefactura> attachedDetallefacturaListNew = new ArrayList<Detallefactura>();
            for (Detallefactura detallefacturaListNewDetallefacturaToAttach : detallefacturaListNew) {
                detallefacturaListNewDetallefacturaToAttach = em.getReference(detallefacturaListNewDetallefacturaToAttach.getClass(), detallefacturaListNewDetallefacturaToAttach.getIdDetalle());
                attachedDetallefacturaListNew.add(detallefacturaListNewDetallefacturaToAttach);
            }
            detallefacturaListNew = attachedDetallefacturaListNew;
            factura.setDetallefacturaList(detallefacturaListNew);
            factura = em.merge(factura);
            if (fkClienteOld != null && !fkClienteOld.equals(fkClienteNew)) {
                fkClienteOld.getFacturaList().remove(factura);
                fkClienteOld = em.merge(fkClienteOld);
            }
            if (fkClienteNew != null && !fkClienteNew.equals(fkClienteOld)) {
                fkClienteNew.getFacturaList().add(factura);
                fkClienteNew = em.merge(fkClienteNew);
            }
            if (fkdatosNegocioOld != null && !fkdatosNegocioOld.equals(fkdatosNegocioNew)) {
                fkdatosNegocioOld.getFacturaList().remove(factura);
                fkdatosNegocioOld = em.merge(fkdatosNegocioOld);
            }
            if (fkdatosNegocioNew != null && !fkdatosNegocioNew.equals(fkdatosNegocioOld)) {
                fkdatosNegocioNew.getFacturaList().add(factura);
                fkdatosNegocioNew = em.merge(fkdatosNegocioNew);
            }
            for (Detallefactura detallefacturaListNewDetallefactura : detallefacturaListNew) {
                if (!detallefacturaListOld.contains(detallefacturaListNewDetallefactura)) {
                    Factura oldFkFacturaOfDetallefacturaListNewDetallefactura = detallefacturaListNewDetallefactura.getFkFactura();
                    detallefacturaListNewDetallefactura.setFkFactura(factura);
                    detallefacturaListNewDetallefactura = em.merge(detallefacturaListNewDetallefactura);
                    if (oldFkFacturaOfDetallefacturaListNewDetallefactura != null && !oldFkFacturaOfDetallefacturaListNewDetallefactura.equals(factura)) {
                        oldFkFacturaOfDetallefacturaListNewDetallefactura.getDetallefacturaList().remove(detallefacturaListNewDetallefactura);
                        oldFkFacturaOfDetallefacturaListNewDetallefactura = em.merge(oldFkFacturaOfDetallefacturaListNewDetallefactura);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = factura.getIdFactura();
                if (findFactura(id) == null) {
                    throw new NonexistentEntityException("The factura with id " + id + " no longer exists.");
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
            Factura factura;
            try {
                factura = em.getReference(Factura.class, id);
                factura.getIdFactura();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The factura with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Detallefactura> detallefacturaListOrphanCheck = factura.getDetallefacturaList();
            for (Detallefactura detallefacturaListOrphanCheckDetallefactura : detallefacturaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Factura (" + factura + ") cannot be destroyed since the Detallefactura " + detallefacturaListOrphanCheckDetallefactura + " in its detallefacturaList field has a non-nullable fkFactura field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Cliente fkCliente = factura.getFkCliente();
            if (fkCliente != null) {
                fkCliente.getFacturaList().remove(factura);
                fkCliente = em.merge(fkCliente);
            }
            Datosnegocio fkdatosNegocio = factura.getFkdatosNegocio();
            if (fkdatosNegocio != null) {
                fkdatosNegocio.getFacturaList().remove(factura);
                fkdatosNegocio = em.merge(fkdatosNegocio);
            }
            em.remove(factura);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Factura> findFacturaEntities() {
        return findFacturaEntities(true, -1, -1);
    }

    public List<Factura> findFacturaEntities(int maxResults, int firstResult) {
        return findFacturaEntities(false, maxResults, firstResult);
    }
    public List<Factura> findFacturasDesc() {
        EntityManager em = emf.createEntityManager();
        Query consulta = em.createQuery("select f from Factura f order by f.fechaVenta desc ");
        List<Factura> lista = consulta.getResultList();
        return lista;
    }
    public List<Factura> FacturasCliente(String nombre) {
        EntityManager em = emf.createEntityManager();
        Query consulta = em.createQuery("select f from Factura f where f.fkCliente.nombre like :nombre order by f.fechaVenta desc");
        consulta.setParameter("nombre", nombre);
        List<Factura> lista = consulta.getResultList();
        return lista;
    }


    private List<Factura> findFacturaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Factura.class));
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

    public Factura findFactura(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Factura.class, id);
        } finally {
            em.close();
        }
    }

    public int getFacturaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Factura> rt = cq.from(Factura.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

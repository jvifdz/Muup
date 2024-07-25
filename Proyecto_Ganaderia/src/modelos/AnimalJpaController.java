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
public class AnimalJpaController implements Serializable {

    public AnimalJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    public AnimalJpaController() {
        this.emf = createEntityManagerFactory("Proyecto_GanaderiaPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Animal animal) {
        if (animal.getAnimalmedicamentoList() == null) {
            animal.setAnimalmedicamentoList(new ArrayList<Animalmedicamento>());
        }
        if (animal.getHistorialparcelaList() == null) {
            animal.setHistorialparcelaList(new ArrayList<Historialparcela>());
        }
        if (animal.getDetallefacturaList() == null) {
            animal.setDetallefacturaList(new ArrayList<Detallefactura>());
        }
        if (animal.getHistorialenfList() == null) {
            animal.setHistorialenfList(new ArrayList<Historialenf>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tipoganaderia fktipoGanaderia = animal.getFktipoGanaderia();
            if (fktipoGanaderia != null) {
                fktipoGanaderia = em.getReference(fktipoGanaderia.getClass(), fktipoGanaderia.getIdtipoGanaderia());
                animal.setFktipoGanaderia(fktipoGanaderia);
            }
            List<Animalmedicamento> attachedAnimalmedicamentoList = new ArrayList<Animalmedicamento>();
            for (Animalmedicamento animalmedicamentoListAnimalmedicamentoToAttach : animal.getAnimalmedicamentoList()) {
                animalmedicamentoListAnimalmedicamentoToAttach = em.getReference(animalmedicamentoListAnimalmedicamentoToAttach.getClass(), animalmedicamentoListAnimalmedicamentoToAttach.getIdanimalMedicamento());
                attachedAnimalmedicamentoList.add(animalmedicamentoListAnimalmedicamentoToAttach);
            }
            animal.setAnimalmedicamentoList(attachedAnimalmedicamentoList);
            List<Historialparcela> attachedHistorialparcelaList = new ArrayList<Historialparcela>();
            for (Historialparcela historialparcelaListHistorialparcelaToAttach : animal.getHistorialparcelaList()) {
                historialparcelaListHistorialparcelaToAttach = em.getReference(historialparcelaListHistorialparcelaToAttach.getClass(), historialparcelaListHistorialparcelaToAttach.getIdhistorialParcela());
                attachedHistorialparcelaList.add(historialparcelaListHistorialparcelaToAttach);
            }
            animal.setHistorialparcelaList(attachedHistorialparcelaList);
            List<Detallefactura> attachedDetallefacturaList = new ArrayList<Detallefactura>();
            for (Detallefactura detallefacturaListDetallefacturaToAttach : animal.getDetallefacturaList()) {
                detallefacturaListDetallefacturaToAttach = em.getReference(detallefacturaListDetallefacturaToAttach.getClass(), detallefacturaListDetallefacturaToAttach.getIdDetalle());
                attachedDetallefacturaList.add(detallefacturaListDetallefacturaToAttach);
            }
            animal.setDetallefacturaList(attachedDetallefacturaList);
            List<Historialenf> attachedHistorialenfList = new ArrayList<Historialenf>();
            for (Historialenf historialenfListHistorialenfToAttach : animal.getHistorialenfList()) {
                historialenfListHistorialenfToAttach = em.getReference(historialenfListHistorialenfToAttach.getClass(), historialenfListHistorialenfToAttach.getIdhistorialEnf());
                attachedHistorialenfList.add(historialenfListHistorialenfToAttach);
            }
            animal.setHistorialenfList(attachedHistorialenfList);
            em.persist(animal);
            if (fktipoGanaderia != null) {
                fktipoGanaderia.getAnimalList().add(animal);
                fktipoGanaderia = em.merge(fktipoGanaderia);
            }
            for (Animalmedicamento animalmedicamentoListAnimalmedicamento : animal.getAnimalmedicamentoList()) {
                Animal oldFkAnimalOfAnimalmedicamentoListAnimalmedicamento = animalmedicamentoListAnimalmedicamento.getFkAnimal();
                animalmedicamentoListAnimalmedicamento.setFkAnimal(animal);
                animalmedicamentoListAnimalmedicamento = em.merge(animalmedicamentoListAnimalmedicamento);
                if (oldFkAnimalOfAnimalmedicamentoListAnimalmedicamento != null) {
                    oldFkAnimalOfAnimalmedicamentoListAnimalmedicamento.getAnimalmedicamentoList().remove(animalmedicamentoListAnimalmedicamento);
                    oldFkAnimalOfAnimalmedicamentoListAnimalmedicamento = em.merge(oldFkAnimalOfAnimalmedicamentoListAnimalmedicamento);
                }
            }
            for (Historialparcela historialparcelaListHistorialparcela : animal.getHistorialparcelaList()) {
                Animal oldFkAnimalOfHistorialparcelaListHistorialparcela = historialparcelaListHistorialparcela.getFkAnimal();
                historialparcelaListHistorialparcela.setFkAnimal(animal);
                historialparcelaListHistorialparcela = em.merge(historialparcelaListHistorialparcela);
                if (oldFkAnimalOfHistorialparcelaListHistorialparcela != null) {
                    oldFkAnimalOfHistorialparcelaListHistorialparcela.getHistorialparcelaList().remove(historialparcelaListHistorialparcela);
                    oldFkAnimalOfHistorialparcelaListHistorialparcela = em.merge(oldFkAnimalOfHistorialparcelaListHistorialparcela);
                }
            }
            for (Detallefactura detallefacturaListDetallefactura : animal.getDetallefacturaList()) {
                Animal oldFkAnimalOfDetallefacturaListDetallefactura = detallefacturaListDetallefactura.getFkAnimal();
                detallefacturaListDetallefactura.setFkAnimal(animal);
                detallefacturaListDetallefactura = em.merge(detallefacturaListDetallefactura);
                if (oldFkAnimalOfDetallefacturaListDetallefactura != null) {
                    oldFkAnimalOfDetallefacturaListDetallefactura.getDetallefacturaList().remove(detallefacturaListDetallefactura);
                    oldFkAnimalOfDetallefacturaListDetallefactura = em.merge(oldFkAnimalOfDetallefacturaListDetallefactura);
                }
            }
            for (Historialenf historialenfListHistorialenf : animal.getHistorialenfList()) {
                Animal oldFkAnimalOfHistorialenfListHistorialenf = historialenfListHistorialenf.getFkAnimal();
                historialenfListHistorialenf.setFkAnimal(animal);
                historialenfListHistorialenf = em.merge(historialenfListHistorialenf);
                if (oldFkAnimalOfHistorialenfListHistorialenf != null) {
                    oldFkAnimalOfHistorialenfListHistorialenf.getHistorialenfList().remove(historialenfListHistorialenf);
                    oldFkAnimalOfHistorialenfListHistorialenf = em.merge(oldFkAnimalOfHistorialenfListHistorialenf);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Animal animal) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Animal persistentAnimal = em.find(Animal.class, animal.getIdAnimal());
            Tipoganaderia fktipoGanaderiaOld = persistentAnimal.getFktipoGanaderia();
            Tipoganaderia fktipoGanaderiaNew = animal.getFktipoGanaderia();
            List<Animalmedicamento> animalmedicamentoListOld = persistentAnimal.getAnimalmedicamentoList();
            List<Animalmedicamento> animalmedicamentoListNew = animal.getAnimalmedicamentoList();
            List<Historialparcela> historialparcelaListOld = persistentAnimal.getHistorialparcelaList();
            List<Historialparcela> historialparcelaListNew = animal.getHistorialparcelaList();
            List<Detallefactura> detallefacturaListOld = persistentAnimal.getDetallefacturaList();
            List<Detallefactura> detallefacturaListNew = animal.getDetallefacturaList();
            List<Historialenf> historialenfListOld = persistentAnimal.getHistorialenfList();
            List<Historialenf> historialenfListNew = animal.getHistorialenfList();
            List<String> illegalOrphanMessages = null;
            for (Animalmedicamento animalmedicamentoListOldAnimalmedicamento : animalmedicamentoListOld) {
                if (!animalmedicamentoListNew.contains(animalmedicamentoListOldAnimalmedicamento)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Animalmedicamento " + animalmedicamentoListOldAnimalmedicamento + " since its fkAnimal field is not nullable.");
                }
            }
            for (Historialparcela historialparcelaListOldHistorialparcela : historialparcelaListOld) {
                if (!historialparcelaListNew.contains(historialparcelaListOldHistorialparcela)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Historialparcela " + historialparcelaListOldHistorialparcela + " since its fkAnimal field is not nullable.");
                }
            }
            for (Detallefactura detallefacturaListOldDetallefactura : detallefacturaListOld) {
                if (!detallefacturaListNew.contains(detallefacturaListOldDetallefactura)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detallefactura " + detallefacturaListOldDetallefactura + " since its fkAnimal field is not nullable.");
                }
            }
            for (Historialenf historialenfListOldHistorialenf : historialenfListOld) {
                if (!historialenfListNew.contains(historialenfListOldHistorialenf)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Historialenf " + historialenfListOldHistorialenf + " since its fkAnimal field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (fktipoGanaderiaNew != null) {
                fktipoGanaderiaNew = em.getReference(fktipoGanaderiaNew.getClass(), fktipoGanaderiaNew.getIdtipoGanaderia());
                animal.setFktipoGanaderia(fktipoGanaderiaNew);
            }
            List<Animalmedicamento> attachedAnimalmedicamentoListNew = new ArrayList<Animalmedicamento>();
            for (Animalmedicamento animalmedicamentoListNewAnimalmedicamentoToAttach : animalmedicamentoListNew) {
                animalmedicamentoListNewAnimalmedicamentoToAttach = em.getReference(animalmedicamentoListNewAnimalmedicamentoToAttach.getClass(), animalmedicamentoListNewAnimalmedicamentoToAttach.getIdanimalMedicamento());
                attachedAnimalmedicamentoListNew.add(animalmedicamentoListNewAnimalmedicamentoToAttach);
            }
            animalmedicamentoListNew = attachedAnimalmedicamentoListNew;
            animal.setAnimalmedicamentoList(animalmedicamentoListNew);
            List<Historialparcela> attachedHistorialparcelaListNew = new ArrayList<Historialparcela>();
            for (Historialparcela historialparcelaListNewHistorialparcelaToAttach : historialparcelaListNew) {
                historialparcelaListNewHistorialparcelaToAttach = em.getReference(historialparcelaListNewHistorialparcelaToAttach.getClass(), historialparcelaListNewHistorialparcelaToAttach.getIdhistorialParcela());
                attachedHistorialparcelaListNew.add(historialparcelaListNewHistorialparcelaToAttach);
            }
            historialparcelaListNew = attachedHistorialparcelaListNew;
            animal.setHistorialparcelaList(historialparcelaListNew);
            List<Detallefactura> attachedDetallefacturaListNew = new ArrayList<Detallefactura>();
            for (Detallefactura detallefacturaListNewDetallefacturaToAttach : detallefacturaListNew) {
                detallefacturaListNewDetallefacturaToAttach = em.getReference(detallefacturaListNewDetallefacturaToAttach.getClass(), detallefacturaListNewDetallefacturaToAttach.getIdDetalle());
                attachedDetallefacturaListNew.add(detallefacturaListNewDetallefacturaToAttach);
            }
            detallefacturaListNew = attachedDetallefacturaListNew;
            animal.setDetallefacturaList(detallefacturaListNew);
            List<Historialenf> attachedHistorialenfListNew = new ArrayList<Historialenf>();
            for (Historialenf historialenfListNewHistorialenfToAttach : historialenfListNew) {
                historialenfListNewHistorialenfToAttach = em.getReference(historialenfListNewHistorialenfToAttach.getClass(), historialenfListNewHistorialenfToAttach.getIdhistorialEnf());
                attachedHistorialenfListNew.add(historialenfListNewHistorialenfToAttach);
            }
            historialenfListNew = attachedHistorialenfListNew;
            animal.setHistorialenfList(historialenfListNew);
            animal = em.merge(animal);
            if (fktipoGanaderiaOld != null && !fktipoGanaderiaOld.equals(fktipoGanaderiaNew)) {
                fktipoGanaderiaOld.getAnimalList().remove(animal);
                fktipoGanaderiaOld = em.merge(fktipoGanaderiaOld);
            }
            if (fktipoGanaderiaNew != null && !fktipoGanaderiaNew.equals(fktipoGanaderiaOld)) {
                fktipoGanaderiaNew.getAnimalList().add(animal);
                fktipoGanaderiaNew = em.merge(fktipoGanaderiaNew);
            }
            for (Animalmedicamento animalmedicamentoListNewAnimalmedicamento : animalmedicamentoListNew) {
                if (!animalmedicamentoListOld.contains(animalmedicamentoListNewAnimalmedicamento)) {
                    Animal oldFkAnimalOfAnimalmedicamentoListNewAnimalmedicamento = animalmedicamentoListNewAnimalmedicamento.getFkAnimal();
                    animalmedicamentoListNewAnimalmedicamento.setFkAnimal(animal);
                    animalmedicamentoListNewAnimalmedicamento = em.merge(animalmedicamentoListNewAnimalmedicamento);
                    if (oldFkAnimalOfAnimalmedicamentoListNewAnimalmedicamento != null && !oldFkAnimalOfAnimalmedicamentoListNewAnimalmedicamento.equals(animal)) {
                        oldFkAnimalOfAnimalmedicamentoListNewAnimalmedicamento.getAnimalmedicamentoList().remove(animalmedicamentoListNewAnimalmedicamento);
                        oldFkAnimalOfAnimalmedicamentoListNewAnimalmedicamento = em.merge(oldFkAnimalOfAnimalmedicamentoListNewAnimalmedicamento);
                    }
                }
            }
            for (Historialparcela historialparcelaListNewHistorialparcela : historialparcelaListNew) {
                if (!historialparcelaListOld.contains(historialparcelaListNewHistorialparcela)) {
                    Animal oldFkAnimalOfHistorialparcelaListNewHistorialparcela = historialparcelaListNewHistorialparcela.getFkAnimal();
                    historialparcelaListNewHistorialparcela.setFkAnimal(animal);
                    historialparcelaListNewHistorialparcela = em.merge(historialparcelaListNewHistorialparcela);
                    if (oldFkAnimalOfHistorialparcelaListNewHistorialparcela != null && !oldFkAnimalOfHistorialparcelaListNewHistorialparcela.equals(animal)) {
                        oldFkAnimalOfHistorialparcelaListNewHistorialparcela.getHistorialparcelaList().remove(historialparcelaListNewHistorialparcela);
                        oldFkAnimalOfHistorialparcelaListNewHistorialparcela = em.merge(oldFkAnimalOfHistorialparcelaListNewHistorialparcela);
                    }
                }
            }
            for (Detallefactura detallefacturaListNewDetallefactura : detallefacturaListNew) {
                if (!detallefacturaListOld.contains(detallefacturaListNewDetallefactura)) {
                    Animal oldFkAnimalOfDetallefacturaListNewDetallefactura = detallefacturaListNewDetallefactura.getFkAnimal();
                    detallefacturaListNewDetallefactura.setFkAnimal(animal);
                    detallefacturaListNewDetallefactura = em.merge(detallefacturaListNewDetallefactura);
                    if (oldFkAnimalOfDetallefacturaListNewDetallefactura != null && !oldFkAnimalOfDetallefacturaListNewDetallefactura.equals(animal)) {
                        oldFkAnimalOfDetallefacturaListNewDetallefactura.getDetallefacturaList().remove(detallefacturaListNewDetallefactura);
                        oldFkAnimalOfDetallefacturaListNewDetallefactura = em.merge(oldFkAnimalOfDetallefacturaListNewDetallefactura);
                    }
                }
            }
            for (Historialenf historialenfListNewHistorialenf : historialenfListNew) {
                if (!historialenfListOld.contains(historialenfListNewHistorialenf)) {
                    Animal oldFkAnimalOfHistorialenfListNewHistorialenf = historialenfListNewHistorialenf.getFkAnimal();
                    historialenfListNewHistorialenf.setFkAnimal(animal);
                    historialenfListNewHistorialenf = em.merge(historialenfListNewHistorialenf);
                    if (oldFkAnimalOfHistorialenfListNewHistorialenf != null && !oldFkAnimalOfHistorialenfListNewHistorialenf.equals(animal)) {
                        oldFkAnimalOfHistorialenfListNewHistorialenf.getHistorialenfList().remove(historialenfListNewHistorialenf);
                        oldFkAnimalOfHistorialenfListNewHistorialenf = em.merge(oldFkAnimalOfHistorialenfListNewHistorialenf);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = animal.getIdAnimal();
                if (findAnimal(id) == null) {
                    throw new NonexistentEntityException("The animal with id " + id + " no longer exists.");
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
            Animal animal;
            try {
                animal = em.getReference(Animal.class, id);
                animal.getIdAnimal();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The animal with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Animalmedicamento> animalmedicamentoListOrphanCheck = animal.getAnimalmedicamentoList();
            for (Animalmedicamento animalmedicamentoListOrphanCheckAnimalmedicamento : animalmedicamentoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Animal (" + animal + ") cannot be destroyed since the Animalmedicamento " + animalmedicamentoListOrphanCheckAnimalmedicamento + " in its animalmedicamentoList field has a non-nullable fkAnimal field.");
            }
            List<Historialparcela> historialparcelaListOrphanCheck = animal.getHistorialparcelaList();
            for (Historialparcela historialparcelaListOrphanCheckHistorialparcela : historialparcelaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Animal (" + animal + ") cannot be destroyed since the Historialparcela " + historialparcelaListOrphanCheckHistorialparcela + " in its historialparcelaList field has a non-nullable fkAnimal field.");
            }
            List<Detallefactura> detallefacturaListOrphanCheck = animal.getDetallefacturaList();
            for (Detallefactura detallefacturaListOrphanCheckDetallefactura : detallefacturaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Animal (" + animal + ") cannot be destroyed since the Detallefactura " + detallefacturaListOrphanCheckDetallefactura + " in its detallefacturaList field has a non-nullable fkAnimal field.");
            }
            List<Historialenf> historialenfListOrphanCheck = animal.getHistorialenfList();
            for (Historialenf historialenfListOrphanCheckHistorialenf : historialenfListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Animal (" + animal + ") cannot be destroyed since the Historialenf " + historialenfListOrphanCheckHistorialenf + " in its historialenfList field has a non-nullable fkAnimal field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Tipoganaderia fktipoGanaderia = animal.getFktipoGanaderia();
            if (fktipoGanaderia != null) {
                fktipoGanaderia.getAnimalList().remove(animal);
                fktipoGanaderia = em.merge(fktipoGanaderia);
            }
            em.remove(animal);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Animal> findAnimalEntities() {
        return findAnimalEntities(true, -1, -1);
    }

    public List<Animal> findAnimalEntities(int maxResults, int firstResult) {
        return findAnimalEntities(false, maxResults, firstResult);
    }
    public List<Animal> AnimalesActuales() {
        EntityManager em = emf.createEntityManager();
        Query consulta = em.createQuery("SELECT a FROM Animal a where a.razonBaja is null");
        List<Animal> lista = consulta.getResultList();
        return lista;
    }
    
    public List<Animal> AnimalesBaja() {
        EntityManager em = emf.createEntityManager();
        Query consulta = em.createQuery("SELECT a FROM Animal a where a.razonBaja is not null");
        List<Animal> lista = consulta.getResultList();
        return lista;
    }
    
    
    public List<Animal> AnimalesNombre(String nombre) {
        EntityManager em = emf.createEntityManager();
        Query consulta = em.createQuery("SELECT a FROM Animal a where a.nombre like :nombre");
        consulta.setParameter("nombre", "%"+nombre+"%");
        List<Animal> lista = consulta.getResultList();
        return lista;
    }
    public List<Animal> AnimalesTipo(String tipo) {
        EntityManager em = emf.createEntityManager();
        Query consulta = em.createQuery("SELECT a FROM Animal a where a.fktipoGanaderia.nombre like :tipo and a.razonBaja is not null");
        consulta.setParameter("tipo", tipo);
        List<Animal> lista = consulta.getResultList();
        return lista;
    }
    
    public List<Animal> AnimalesTipoActuales(String tipo) {
        EntityManager em = emf.createEntityManager();
        Query consulta = em.createQuery("SELECT a FROM Animal a where a.fktipoGanaderia.nombre like :tipo and a.razonBaja is null");
        consulta.setParameter("tipo", tipo);
        List<Animal> lista = consulta.getResultList();
        return lista;
    }
    
     public List<Animal> AnimalesActualesParcelas(String parcela) {
        EntityManager em = emf.createEntityManager();
        Query consulta = em.createQuery("select h.fkAnimal from Historialparcela h where h.fkParcela.nombre like :parcela and h.fkAnimal.razonBaja is null and h.fechaSalida is null");
        consulta.setParameter("parcela", "%"+parcela+"%");
        List<Animal> lista = consulta.getResultList();
        return lista;
    }
     public Animal Animales(int id) {
        EntityManager em = emf.createEntityManager();
        Query consulta = em.createQuery("select d.fkAnimal from Detallefactura d where d.idDetalle=:id");
        consulta.setParameter("id", id);
        Animal animal = (Animal) consulta.getSingleResult();
        return animal;
    }
     
     public List<Animal> AnimalesHijos(String daiMadre) {
        EntityManager em = emf.createEntityManager();
        Query consulta = em.createQuery("select a from Animal a where a.daiMadre like :daiMadre");
        consulta.setParameter("daiMadre", daiMadre);
        List<Animal> lista = consulta.getResultList();
        return lista;
    }

    private List<Animal> findAnimalEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Animal.class));
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

    public Animal findAnimal(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Animal.class, id);
        } finally {
            em.close();
        }
    }

    public int getAnimalCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Animal> rt = cq.from(Animal.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

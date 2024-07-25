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
public class TipoganaderiaJpaController implements Serializable {

    public TipoganaderiaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    public TipoganaderiaJpaController() {
        this.emf = createEntityManagerFactory("Proyecto_GanaderiaPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tipoganaderia tipoganaderia) {
        if (tipoganaderia.getAnimalList() == null) {
            tipoganaderia.setAnimalList(new ArrayList<Animal>());
        }
        if (tipoganaderia.getAlimentosList() == null) {
            tipoganaderia.setAlimentosList(new ArrayList<Alimentos>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Animal> attachedAnimalList = new ArrayList<Animal>();
            for (Animal animalListAnimalToAttach : tipoganaderia.getAnimalList()) {
                animalListAnimalToAttach = em.getReference(animalListAnimalToAttach.getClass(), animalListAnimalToAttach.getIdAnimal());
                attachedAnimalList.add(animalListAnimalToAttach);
            }
            tipoganaderia.setAnimalList(attachedAnimalList);
            List<Alimentos> attachedAlimentosList = new ArrayList<Alimentos>();
            for (Alimentos alimentosListAlimentosToAttach : tipoganaderia.getAlimentosList()) {
                alimentosListAlimentosToAttach = em.getReference(alimentosListAlimentosToAttach.getClass(), alimentosListAlimentosToAttach.getIdAlimentos());
                attachedAlimentosList.add(alimentosListAlimentosToAttach);
            }
            tipoganaderia.setAlimentosList(attachedAlimentosList);
            em.persist(tipoganaderia);
            for (Animal animalListAnimal : tipoganaderia.getAnimalList()) {
                Tipoganaderia oldFktipoGanaderiaOfAnimalListAnimal = animalListAnimal.getFktipoGanaderia();
                animalListAnimal.setFktipoGanaderia(tipoganaderia);
                animalListAnimal = em.merge(animalListAnimal);
                if (oldFktipoGanaderiaOfAnimalListAnimal != null) {
                    oldFktipoGanaderiaOfAnimalListAnimal.getAnimalList().remove(animalListAnimal);
                    oldFktipoGanaderiaOfAnimalListAnimal = em.merge(oldFktipoGanaderiaOfAnimalListAnimal);
                }
            }
            for (Alimentos alimentosListAlimentos : tipoganaderia.getAlimentosList()) {
                Tipoganaderia oldFkAlimentosOfAlimentosListAlimentos = alimentosListAlimentos.getFkAlimentos();
                alimentosListAlimentos.setFkAlimentos(tipoganaderia);
                alimentosListAlimentos = em.merge(alimentosListAlimentos);
                if (oldFkAlimentosOfAlimentosListAlimentos != null) {
                    oldFkAlimentosOfAlimentosListAlimentos.getAlimentosList().remove(alimentosListAlimentos);
                    oldFkAlimentosOfAlimentosListAlimentos = em.merge(oldFkAlimentosOfAlimentosListAlimentos);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tipoganaderia tipoganaderia) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tipoganaderia persistentTipoganaderia = em.find(Tipoganaderia.class, tipoganaderia.getIdtipoGanaderia());
            List<Animal> animalListOld = persistentTipoganaderia.getAnimalList();
            List<Animal> animalListNew = tipoganaderia.getAnimalList();
            List<Alimentos> alimentosListOld = persistentTipoganaderia.getAlimentosList();
            List<Alimentos> alimentosListNew = tipoganaderia.getAlimentosList();
            List<String> illegalOrphanMessages = null;
            for (Animal animalListOldAnimal : animalListOld) {
                if (!animalListNew.contains(animalListOldAnimal)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Animal " + animalListOldAnimal + " since its fktipoGanaderia field is not nullable.");
                }
            }
            for (Alimentos alimentosListOldAlimentos : alimentosListOld) {
                if (!alimentosListNew.contains(alimentosListOldAlimentos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Alimentos " + alimentosListOldAlimentos + " since its fkAlimentos field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Animal> attachedAnimalListNew = new ArrayList<Animal>();
            for (Animal animalListNewAnimalToAttach : animalListNew) {
                animalListNewAnimalToAttach = em.getReference(animalListNewAnimalToAttach.getClass(), animalListNewAnimalToAttach.getIdAnimal());
                attachedAnimalListNew.add(animalListNewAnimalToAttach);
            }
            animalListNew = attachedAnimalListNew;
            tipoganaderia.setAnimalList(animalListNew);
            List<Alimentos> attachedAlimentosListNew = new ArrayList<Alimentos>();
            for (Alimentos alimentosListNewAlimentosToAttach : alimentosListNew) {
                alimentosListNewAlimentosToAttach = em.getReference(alimentosListNewAlimentosToAttach.getClass(), alimentosListNewAlimentosToAttach.getIdAlimentos());
                attachedAlimentosListNew.add(alimentosListNewAlimentosToAttach);
            }
            alimentosListNew = attachedAlimentosListNew;
            tipoganaderia.setAlimentosList(alimentosListNew);
            tipoganaderia = em.merge(tipoganaderia);
            for (Animal animalListNewAnimal : animalListNew) {
                if (!animalListOld.contains(animalListNewAnimal)) {
                    Tipoganaderia oldFktipoGanaderiaOfAnimalListNewAnimal = animalListNewAnimal.getFktipoGanaderia();
                    animalListNewAnimal.setFktipoGanaderia(tipoganaderia);
                    animalListNewAnimal = em.merge(animalListNewAnimal);
                    if (oldFktipoGanaderiaOfAnimalListNewAnimal != null && !oldFktipoGanaderiaOfAnimalListNewAnimal.equals(tipoganaderia)) {
                        oldFktipoGanaderiaOfAnimalListNewAnimal.getAnimalList().remove(animalListNewAnimal);
                        oldFktipoGanaderiaOfAnimalListNewAnimal = em.merge(oldFktipoGanaderiaOfAnimalListNewAnimal);
                    }
                }
            }
            for (Alimentos alimentosListNewAlimentos : alimentosListNew) {
                if (!alimentosListOld.contains(alimentosListNewAlimentos)) {
                    Tipoganaderia oldFkAlimentosOfAlimentosListNewAlimentos = alimentosListNewAlimentos.getFkAlimentos();
                    alimentosListNewAlimentos.setFkAlimentos(tipoganaderia);
                    alimentosListNewAlimentos = em.merge(alimentosListNewAlimentos);
                    if (oldFkAlimentosOfAlimentosListNewAlimentos != null && !oldFkAlimentosOfAlimentosListNewAlimentos.equals(tipoganaderia)) {
                        oldFkAlimentosOfAlimentosListNewAlimentos.getAlimentosList().remove(alimentosListNewAlimentos);
                        oldFkAlimentosOfAlimentosListNewAlimentos = em.merge(oldFkAlimentosOfAlimentosListNewAlimentos);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tipoganaderia.getIdtipoGanaderia();
                if (findTipoganaderia(id) == null) {
                    throw new NonexistentEntityException("The tipoganaderia with id " + id + " no longer exists.");
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
            Tipoganaderia tipoganaderia;
            try {
                tipoganaderia = em.getReference(Tipoganaderia.class, id);
                tipoganaderia.getIdtipoGanaderia();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoganaderia with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Animal> animalListOrphanCheck = tipoganaderia.getAnimalList();
            for (Animal animalListOrphanCheckAnimal : animalListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tipoganaderia (" + tipoganaderia + ") cannot be destroyed since the Animal " + animalListOrphanCheckAnimal + " in its animalList field has a non-nullable fktipoGanaderia field.");
            }
            List<Alimentos> alimentosListOrphanCheck = tipoganaderia.getAlimentosList();
            for (Alimentos alimentosListOrphanCheckAlimentos : alimentosListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tipoganaderia (" + tipoganaderia + ") cannot be destroyed since the Alimentos " + alimentosListOrphanCheckAlimentos + " in its alimentosList field has a non-nullable fkAlimentos field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(tipoganaderia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tipoganaderia> findTipoganaderiaEntities() {
        return findTipoganaderiaEntities(true, -1, -1);
    }

    public List<Tipoganaderia> findTipoganaderiaEntities(int maxResults, int firstResult) {
        return findTipoganaderiaEntities(false, maxResults, firstResult);
    }

    private List<Tipoganaderia> findTipoganaderiaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tipoganaderia.class));
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

    public Tipoganaderia findTipoganaderia(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tipoganaderia.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoganaderiaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tipoganaderia> rt = cq.from(Tipoganaderia.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
package com.ejbserver;

import com.ejbserver.cdserver.model.CD;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Stateful
public class AdminServiceBean implements AdminService {

    @PersistenceContext(unitName = "CDServerPU")
    private EntityManager em;

    @Override
    public void ajouterCd(String titre) {
        CD cd = new CD(titre, true);
        em.persist(cd);
        System.out.println("CD ajouté: " + titre);
    }

    @Override
    public void supprimerCd(String titre) {
        CD cd = em.createQuery("SELECT c FROM CD c WHERE c.titre = :titre", CD.class)
                .setParameter("titre", titre)
                .getSingleResult();
        if (cd != null) {
            em.remove(cd);
            System.out.println("CD supprimé: " + titre);
        } else {
            System.out.println("CD non trouvé pour suppression.");
        }
    }

    @Override
    public List<CD> listerCds() {
        return em.createQuery("SELECT c FROM CD c", CD.class).getResultList();
    }
}
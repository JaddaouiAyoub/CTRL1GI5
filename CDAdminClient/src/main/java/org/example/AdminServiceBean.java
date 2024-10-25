package org.example;


import jakarta.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Stateless
public class AdminServiceBean implements AdminService {

    @PersistenceContext(unitName = "CDServerPU")
    private EntityManager em;

    @Override
    public void ajouterCd(CD cd) {
        em.persist(cd);
    }

    @Override
    public void modifierCd(CD cd) {
        em.merge(cd);
    }

    @Override
    public void supprimerCd(Long cdId) {
        CD cd = em.find(CD.class, cdId);
        if (cd != null) {
            em.remove(cd);
        }
    }
}
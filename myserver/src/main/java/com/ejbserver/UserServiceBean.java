package com.ejbserver;

import com.ejbserver.cdserver.model.CD;
import com.ejbserver.cdserver.model.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class UserServiceBean implements UserService {

    @PersistenceContext(unitName = "CDServerPU")
    private EntityManager em;

    @Override
    public void preterCd(String titre, String utilisateur) {
        CD cd = em.createQuery("SELECT c FROM CD c WHERE c.titre = :titre AND c.estDisponible = true", CD.class)
                .setParameter("titre", titre)
                .getSingleResult();

        if (cd != null) {
            User user = em.createQuery("SELECT u FROM User u WHERE u.nom = :nom", User.class)
                    .setParameter("nom", utilisateur)
                    .getSingleResult();
            cd.setEstDisponible(false);
            em.merge(cd);
            user.emprunterCd(cd);
            em.merge(user);
            System.out.println("CD prêté à l'utilisateur " + utilisateur);
        }
    }

    @Override
    public void retournerCd(String titre) {
        CD cd = em.createQuery("SELECT c FROM CD c WHERE c.titre = :titre AND c.estDisponible = false", CD.class)
                .setParameter("titre", titre)
                .getSingleResult();
        if (cd != null) {
            cd.setEstDisponible(true);
            em.merge(cd);
            System.out.println("CD retourné: " + titre);
        } else {
            System.out.println("CD non trouvé ou déjà disponible.");
        }
    }

}
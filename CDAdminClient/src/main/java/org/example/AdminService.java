package org.example;

import jakarta.ejb.Remote;

@Remote
public interface AdminService {
    void ajouterCd(CD cd);
    void modifierCd(CD cd);
    void supprimerCd(Long cdId);
}
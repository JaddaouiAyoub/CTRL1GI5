package com.ejbserver;

import com.ejbserver.cdserver.model.CD;

import java.util.List;

public interface AdminService {
    void ajouterCd(String titre);

    void supprimerCd(String titre);

    List<CD> listerCds();
}

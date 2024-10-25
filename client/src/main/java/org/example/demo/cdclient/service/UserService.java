package org.example.demo.cdclient.service;


import jakarta.ejb.Remote;

import java.util.List;

@Remote
public interface UserService {
    void preterCd(String titre, String utilisateur);
    void retournerCd(String titre);
    List<CD> listerCdsDisponibles();
    List<CD> listerCdsEmpruntes(String utilisateur);
}
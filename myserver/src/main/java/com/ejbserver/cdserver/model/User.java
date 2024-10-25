package com.ejbserver.cdserver.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;

    @OneToMany
    private List<CD> cdsEmpruntes = new ArrayList<>();

    // Constructeurs, Getters et Setters
    public User() {}

    public User(String nom) {
        this.nom = nom;
    }

    public List<CD> getCdsEmpruntes() {
        return cdsEmpruntes;
    }

    public void emprunterCd(CD cd) {
        cdsEmpruntes.add(cd);
    }

    public void retournerCd(CD cd) {
        cdsEmpruntes.remove(cd);
    }

    public String getNom() {
        return nom;
    }
}

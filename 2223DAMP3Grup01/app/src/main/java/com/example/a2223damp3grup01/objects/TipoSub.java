package com.example.a2223damp3grup01.objects;

public class TipoSub {
    String nom;
    String preu;

    public TipoSub(String nom, String preu) {
        this.nom = nom;
        this.preu = preu;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPreu() {
        return preu;
    }

    public void setPreu(String preu) {
        this.preu = preu;
    }
}

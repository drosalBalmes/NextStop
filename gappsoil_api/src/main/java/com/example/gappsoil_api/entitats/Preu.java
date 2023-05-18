package com.example.gappsoil_api.entitats;


import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PREUS")
public class Preu implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PREU_ID")
    private long id;



    String ultimaAct;
    double preuSP95;
    double preuSP98;
    double preuGasoil;

    double preuGLP;

    double preuGNC;

    double preuGNL;



    @ManyToOne
    @JoinColumn(name = "BENZINERA_ID")
    private Benzinera benzinera;

    public Preu(long id, String ultimaAct, double preuSP95, double preuSP98, double preuGasoil, double preuGLP, double preuGNC, double preuGNL, Benzinera benzinera) {
        this.id = id;
        this.ultimaAct = ultimaAct;
        this.preuSP95 = preuSP95;
        this.preuSP98 = preuSP98;
        this.preuGasoil = preuGasoil;
        this.preuGLP = preuGLP;
        this.preuGNC = preuGNC;
        this.preuGNL = preuGNL;
        this.benzinera = benzinera;
    }

    public Preu() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getPreuSP95() {
        return preuSP95;
    }

    public void setPreuSP95(double preuSP95) {
        this.preuSP95 = preuSP95;
    }

    public double getPreuSP98() {
        return preuSP98;
    }

    public void setPreuSP98(double preuSP98) {
        this.preuSP98 = preuSP98;
    }

    public double getPreuGasoil() {
        return preuGasoil;
    }

    public void setPreuGasoil(double preuGasoil) {
        this.preuGasoil = preuGasoil;
    }





    public Benzinera getBenzinera() {
        return benzinera;
    }

    public void setBenzinera(Benzinera benzinera) {
        this.benzinera = benzinera;
    }



    public String getUltimaAct() {
        return ultimaAct;
    }

    public void setUltimaAct(String ultimaAct) {
        this.ultimaAct = ultimaAct;
    }

    public double getPreuGLP() {
        return preuGLP;
    }

    public void setPreuGLP(double preuGLP) {
        this.preuGLP = preuGLP;
    }

    public double getPreuGNC() {
        return preuGNC;
    }

    public void setPreuGNC(double preuGNC) {
        this.preuGNC = preuGNC;
    }

    public double getPreuGNL() {
        return preuGNL;
    }

    public void setPreuGNL(double preuGNL) {
        this.preuGNL = preuGNL;
    }
}

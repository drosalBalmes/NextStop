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

    private String dia;

    private String ultimaAct;
    double preuSP95;
    double preuSP98;
    double preuGasoil;
    double preuAdblue;
    double preuElectricitat;
    double preuHidrogen;

    @ManyToOne
    @JoinColumn(name = "BENZINERA_ID")
    private Benzinera benzinera;

    public Preu(long id, String dia, String ultimaAct, double preuSP95, double preuSP98, double preuGasoil, double preuAdblue, double preuElectricitat, double preuHidrogen, Benzinera benzinera) {
        this.id = id;
        this.dia = dia;
        this.ultimaAct = ultimaAct;
        this.preuSP95 = preuSP95;
        this.preuSP98 = preuSP98;
        this.preuGasoil = preuGasoil;
        this.preuAdblue = preuAdblue;
        this.preuElectricitat = preuElectricitat;
        this.preuHidrogen = preuHidrogen;
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

    public double getPreuAdblue() {
        return preuAdblue;
    }

    public void setPreuAdblue(double preuAdblue) {
        this.preuAdblue = preuAdblue;
    }

    public double getPreuElectricitat() {
        return preuElectricitat;
    }

    public void setPreuElectricitat(double preuElectricitat) {
        this.preuElectricitat = preuElectricitat;
    }

    public double getPreuHidrogen() {
        return preuHidrogen;
    }

    public void setPreuHidrogen(double preuHidrogen) {
        this.preuHidrogen = preuHidrogen;
    }

    public Benzinera getBenzinera() {
        return benzinera;
    }

    public void setBenzinera(Benzinera benzinera) {
        this.benzinera = benzinera;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getUltimaAct() {
        return ultimaAct;
    }

    public void setUltimaAct(String ultimaAct) {
        this.ultimaAct = ultimaAct;
    }
}

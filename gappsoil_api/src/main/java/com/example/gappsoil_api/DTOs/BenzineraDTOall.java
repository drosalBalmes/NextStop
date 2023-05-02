package com.example.gappsoil_api.DTOs;

import com.example.gappsoil_api.entitats.Preu;
import com.example.gappsoil_api.entitats.Valoracio;

import java.time.LocalTime;
import java.util.List;

public class BenzineraDTOall {
    private long id;

    private String nom;

    private double latitude;
    private double longitude;

    boolean gasolina;

    boolean SP95;

    boolean SP98;


    boolean gasoil;

    boolean Adblue;


    boolean hidrogen;


    String horari;
    private List<Valoracio> valoracions;

    private List<Preu> preus;


    public BenzineraDTOall(long id, String nom, double latitude, double longitude, boolean gasolina, boolean SP95, boolean SP98, boolean gasoil, boolean adblue, boolean hidrogen, String horari, List<Valoracio> valoracions, List<Preu> preus) {
        this.id = id;
        this.nom = nom;
        this.latitude = latitude;
        this.longitude = longitude;
        this.gasolina = gasolina;
        this.SP95 = SP95;
        this.SP98 = SP98;
        this.gasoil = gasoil;
        this.Adblue = adblue;
        this.hidrogen = hidrogen;
        this.horari = horari;
        this.valoracions = valoracions;
        this.preus = preus;
    }

    public BenzineraDTOall() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public boolean isGasolina() {
        return gasolina;
    }

    public void setGasolina(boolean gasolina) {
        this.gasolina = gasolina;
    }

    public boolean isSP95() {
        return SP95;
    }

    public void setSP95(boolean SP95) {
        this.SP95 = SP95;
    }

    public boolean isSP98() {
        return SP98;
    }

    public void setSP98(boolean SP98) {
        this.SP98 = SP98;
    }

    public boolean isGasoil() {
        return gasoil;
    }

    public void setGasoil(boolean gasoil) {
        this.gasoil = gasoil;
    }

    public boolean isAdblue() {
        return Adblue;
    }

    public void setAdblue(boolean adblue) {
        Adblue = adblue;
    }

    public boolean isHidrogen() {
        return hidrogen;
    }

    public void setHidrogen(boolean hidrogen) {
        this.hidrogen = hidrogen;
    }

    public String getHorari() {
        return horari;
    }

    public void setHorari(String horari) {
        this.horari = horari;
    }

    public List<Valoracio> getValoracions() {
        return valoracions;
    }

    public void setValoracions(List<Valoracio> valoracions) {
        this.valoracions = valoracions;
    }

    public List<Preu> getPreus() {
        return preus;
    }

    public void setPreus(List<Preu> preus) {
        this.preus = preus;
    }
}

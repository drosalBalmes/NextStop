package com.example.gappsoil_api.entitats;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "PUNTS_DE_RECARREGA")
public class puntRecarrega {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PUNT_RECARREGA_ID")
    private long id;


    private String tipusConexio;

    private double latitude;

    private double longitude;

    private String nom;

    private String tipusCorrent;

    private String numPlaces;

    private String TipusVehicles;


    @OneToMany(mappedBy = "puntRecarrega")
    private List<ValoracioElec> valoracionsElec;

    public puntRecarrega(long id, String tipusConexio, double latitude, double longitude, String nom, String tipusCorrent, String numPlaces, String tipusVehicles, List<ValoracioElec> valoracionsElec) {
        this.id = id;
        this.tipusConexio = tipusConexio;
        this.latitude = latitude;
        this.longitude = longitude;
        this.nom = nom;
        this.tipusCorrent = tipusCorrent;
        this.numPlaces = numPlaces;
        TipusVehicles = tipusVehicles;
        this.valoracionsElec = valoracionsElec;
    }

    public puntRecarrega() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTipusConexio() {
        return tipusConexio;
    }

    public void setTipusConexio(String tipusConexio) {
        this.tipusConexio = tipusConexio;
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

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getTipusCorrent() {
        return tipusCorrent;
    }

    public void setTipusCorrent(String tipusCorrent) {
        this.tipusCorrent = tipusCorrent;
    }

    public String getNumPlaces() {
        return numPlaces;
    }

    public void setNumPlaces(String numPlaces) {
        this.numPlaces = numPlaces;
    }

    public String getTipusVehicles() {
        return TipusVehicles;
    }

    public void setTipusVehicles(String tipusVehicles) {
        TipusVehicles = tipusVehicles;
    }

    public List<ValoracioElec> getValoracionsElec() {
        return valoracionsElec;
    }

    public void setValoracionsElec(List<ValoracioElec> valoracionsElec) {
        this.valoracionsElec = valoracionsElec;
    }
}

package com.example.gappsoil_api.DTOs;

public class PuntRecargaDTOwVals {

    private long id;

    String tipusConexio;

    private double latitude;

    private double longitude;

    private String nom;

    private String tipusCorrent;

    private String numPlaces;

    private String TipusVehicles;

    int numReviews;

    Double mitjaReviews;

    public PuntRecargaDTOwVals(long id, String tipusConexio, double latitude, double longitude, String nom, String tipusCorrent, String numPlaces, String tipusVehicles) {
        this.id = id;
        this.tipusConexio = tipusConexio;
        this.latitude = latitude;
        this.longitude = longitude;
        this.nom = nom;
        this.tipusCorrent = tipusCorrent;
        this.numPlaces = numPlaces;
        TipusVehicles = tipusVehicles;
    }

    public PuntRecargaDTOwVals(long id, String tipusConexio, double latitude, double longitude, String nom, String tipusCorrent, String numPlaces, String tipusVehicles, int numReviews, Double mitjaReviews) {
        this.id = id;
        this.tipusConexio = tipusConexio;
        this.latitude = latitude;
        this.longitude = longitude;
        this.nom = nom;
        this.tipusCorrent = tipusCorrent;
        this.numPlaces = numPlaces;
        TipusVehicles = tipusVehicles;
        this.numReviews = numReviews;
        this.mitjaReviews = mitjaReviews;
    }

    public PuntRecargaDTOwVals() {
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

    public int getNumReviews() {
        return numReviews;
    }

    public void setNumReviews(int numReviews) {
        this.numReviews = numReviews;
    }

    public Double getMitjaReviews() {
        return mitjaReviews;
    }

    public void setMitjaReviews(Double mitjaReviews) {
        this.mitjaReviews = mitjaReviews;
    }
}

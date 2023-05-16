package com.example.a2223damp3grup01.objects;



public class PuntRecarrega {

    private Integer id;
    private Integer latitude;
    private Integer longitude;
    private String nom;
    private String numPlaces;
    private String tipusConexio;
    private String tipusCorrent;
    private String tipusVehicles;

    public PuntRecarrega() {
    }


    public PuntRecarrega(Integer id, Integer latitude, Integer longitude, String nom, String numPlaces, String tipusConexio, String tipusCorrent, String tipusVehicles) {
        super();
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.nom = nom;
        this.numPlaces = numPlaces;
        this.tipusConexio = tipusConexio;
        this.tipusCorrent = tipusCorrent;
        this.tipusVehicles = tipusVehicles;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLatitude() {
        return latitude;
    }

    public void setLatitude(Integer latitude) {
        this.latitude = latitude;
    }

    public Integer getLongitude() {
        return longitude;
    }

    public void setLongitude(Integer longitude) {
        this.longitude = longitude;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNumPlaces() {
        return numPlaces;
    }

    public void setNumPlaces(String numPlaces) {
        this.numPlaces = numPlaces;
    }

    public String getTipusConexio() {
        return tipusConexio;
    }

    public void setTipusConexio(String tipusConexio) {
        this.tipusConexio = tipusConexio;
    }

    public String getTipusCorrent() {
        return tipusCorrent;
    }

    public void setTipusCorrent(String tipusCorrent) {
        this.tipusCorrent = tipusCorrent;
    }

    public String getTipusVehicles() {
        return tipusVehicles;
    }

    public void setTipusVehicles(String tipusVehicles) {
        this.tipusVehicles = tipusVehicles;
    }

}
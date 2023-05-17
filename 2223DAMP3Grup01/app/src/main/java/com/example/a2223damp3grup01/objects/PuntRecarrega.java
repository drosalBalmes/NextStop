package com.example.a2223damp3grup01.objects;



public class PuntRecarrega {

    private Integer id;
    private double latitude;
    private double longitude;
    private String nom;

    private Double mitjaReviews;
    private int numReviews;
    private String numPlaces;
    private String tipusConexio;
    private String tipusCorrent;
    private String tipusVehicles;
    private long distFromActual;


    public PuntRecarrega() {
    }


    public PuntRecarrega(Integer id, double latitude, double longitude, String nom, String numPlaces, String tipusConexio, String tipusCorrent, String tipusVehicles,long distFromActual,Double mitjaReviews,int numReviews) {
        super();
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.nom = nom;
        this.numPlaces = numPlaces;
        this.tipusConexio = tipusConexio;
        this.tipusCorrent = tipusCorrent;
        this.tipusVehicles = tipusVehicles;
        this.distFromActual = distFromActual;
        this.mitjaReviews = mitjaReviews;
        this.numReviews = numReviews;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public long getDistFromActual() {
        return distFromActual;
    }

    public void setDistFromActual(long distFromActual) {
        this.distFromActual = distFromActual;
    }

    public Double getMitjaReviews() {
        return mitjaReviews;
    }

    public void setMitjaReviews(Double mitjaReviews) {
        this.mitjaReviews = mitjaReviews;
    }

    public int getNumReviews() {
        return numReviews;
    }

    public void setNumReviews(int numReviews) {
        this.numReviews = numReviews;
    }
}
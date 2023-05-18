package com.example.a2223damp3grup01.objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Benzinera {

    @SerializedName("adblue")
    @Expose
    private Boolean adblue;
    @SerializedName("gasoil")
    @Expose
    private Boolean gasoil;
    @SerializedName("gasolina")
    @Expose
    private Boolean gasolina;
    @SerializedName("glp")
    @Expose
    private Boolean glp;
    @SerializedName("gnc")
    @Expose
    private Boolean gnc;
    @SerializedName("gnl")
    @Expose
    private Boolean gnl;
    @SerializedName("hidrogen")
    @Expose
    private Boolean hidrogen;
    @SerializedName("horari")
    @Expose
    private String horari;
    @SerializedName("id")
    @Expose
    private long id;
    @SerializedName("latitude")
    @Expose
    private double latitude;
    @SerializedName("longitude")
    @Expose
    private double longitude;
    @SerializedName("nom")
    @Expose
    private String nom;

    @SerializedName("mitjaReviews")
    @Expose
    private Double mitjaReviews;

    @SerializedName("numReviews")
    @Expose
    private int numReviews;
    @SerializedName("sp95")
    @Expose
    private Boolean sp95;
    @SerializedName("sp98")
    @Expose
    private Boolean sp98;

    private long distFromActual;

    private String typeGAS;

    private long preuid;



    String ultimaAct;
    double preuSP95;
    double preuSP98;
    double preuGasoil;

    double preuGLP;

    double preuGNC;

    double preuGNL;
    public Benzinera() {
    }

    public Benzinera(String nom) {
        this.nom = nom;
    }

    /**
     *
     * @param gnl
     * @param horari
     * @param gasolina
     * @param latitude
     * @param adblue
     * @param gasoil
     * @param sp98
     * @param glp
     * @param sp95
     * @param nom
     * @param hidrogen
     * @param id
     * @param gnc
     * @param longitude
     */
    public Benzinera(Boolean adblue, Boolean gasoil, Boolean gasolina, Boolean glp, Boolean gnc, Boolean gnl, Boolean hidrogen, String horari, Integer id, Integer latitude, Integer longitude, String nom, Boolean sp95, Boolean sp98,long distFromActual,Double mitjaReviews, int numReviews) {
        super();
        this.adblue = adblue;
        this.gasoil = gasoil;
        this.gasolina = gasolina;
        this.glp = glp;
        this.gnc = gnc;
        this.gnl = gnl;
        this.hidrogen = hidrogen;
        this.horari = horari;
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.nom = nom;
        this.sp95 = sp95;
        this.sp98 = sp98;
        this.distFromActual = distFromActual;
        this.mitjaReviews = mitjaReviews;
        this.numReviews = numReviews;
    }

    public Benzinera(Boolean adblue, Boolean gasoil, Boolean gasolina, Boolean glp, Boolean gnc, Boolean gnl, Boolean hidrogen, String horari, long id, double latitude, double longitude, String nom, Double mitjaReviews, int numReviews, Boolean sp95, Boolean sp98, long distFromActual, String typeGAS, long preuid, String ultimaAct, double preuSP95, double preuSP98, double preuGasoil, double preuGLP, double preuGNC, double preuGNL) {
        this.adblue = adblue;
        this.gasoil = gasoil;
        this.gasolina = gasolina;
        this.glp = glp;
        this.gnc = gnc;
        this.gnl = gnl;
        this.hidrogen = hidrogen;
        this.horari = horari;
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.nom = nom;
        this.mitjaReviews = mitjaReviews;
        this.numReviews = numReviews;
        this.sp95 = sp95;
        this.sp98 = sp98;
        this.distFromActual = distFromActual;
        this.typeGAS = typeGAS;
        this.preuid = preuid;
        this.ultimaAct = ultimaAct;
        this.preuSP95 = preuSP95;
        this.preuSP98 = preuSP98;
        this.preuGasoil = preuGasoil;
        this.preuGLP = preuGLP;
        this.preuGNC = preuGNC;
        this.preuGNL = preuGNL;
    }

    public Boolean getAdblue() {
        return adblue;
    }

    public void setAdblue(Boolean adblue) {
        this.adblue = adblue;
    }

    public Boolean getGasoil() {
        return gasoil;
    }

    public void setGasoil(Boolean gasoil) {
        this.gasoil = gasoil;
    }

    public Boolean getGasolina() {
        return gasolina;
    }

    public void setGasolina(Boolean gasolina) {
        this.gasolina = gasolina;
    }

    public Boolean getGlp() {
        return glp;
    }

    public void setGlp(Boolean glp) {
        this.glp = glp;
    }

    public Boolean getGnc() {
        return gnc;
    }

    public void setGnc(Boolean gnc) {
        this.gnc = gnc;
    }

    public Boolean getGnl() {
        return gnl;
    }

    public void setGnl(Boolean gnl) {
        this.gnl = gnl;
    }

    public Boolean getHidrogen() {
        return hidrogen;
    }

    public void setHidrogen(Boolean hidrogen) {
        this.hidrogen = hidrogen;
    }

    public String getHorari() {
        return horari;
    }

    public void setHorari(String horari) {
        this.horari = horari;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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


    public long getPreuid() {
        return preuid;
    }

    public void setPreuid(long preuid) {
        this.preuid = preuid;
    }

    public String getUltimaAct() {
        return ultimaAct;
    }

    public void setUltimaAct(String ultimaAct) {
        this.ultimaAct = ultimaAct;
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

    public double getPreuGasoil() {
        return preuGasoil;
    }

    public void setPreuGasoil(double preuGasoil) {
        this.preuGasoil = preuGasoil;
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

    public Boolean getSp95() {
        return sp95;
    }

    public void setSp95(Boolean sp95) {
        this.sp95 = sp95;
    }

    public Boolean getSp98() {
        return sp98;
    }

    public void setSp98(Boolean sp98) {
        this.sp98 = sp98;
    }

    public long getDistFromActual() {
        return distFromActual;
    }

    public void setDistFromActual(long distFromActual) {
        this.distFromActual = distFromActual;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getTypeGAS() {
        return typeGAS;
    }

    public void setTypeGAS(String typeGAS) {
        this.typeGAS = typeGAS;
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

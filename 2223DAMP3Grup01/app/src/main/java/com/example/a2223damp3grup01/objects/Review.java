package com.example.a2223damp3grup01.objects;

public class Review {
    private int puntuacio;
    private String comentari;
    private String gasolinera;

    public Review(int puntuacio, String comment, String gasolinera) {
        this.puntuacio = puntuacio;
        this.comentari = comment;
        this.gasolinera = gasolinera;
    }

    public Review(int puntuacio, String review) {
        this.puntuacio = puntuacio;
        this.comentari = review;
    }

    public Review(int puntuacio) {
        this.puntuacio = puntuacio;
    }

    public int getPuntuacio() {
        return puntuacio;
    }

    public void setPuntuacio(int puntuacio) {
        this.puntuacio = puntuacio;
    }

    public String getComentari() {
        return comentari;
    }

    public void setComentari(String review) {
        this.comentari = review;
    }

    public String getGasolinera() {
        return gasolinera;
    }

    public void setGasolinera(String gasolinera) {
        this.gasolinera = gasolinera;
    }
}

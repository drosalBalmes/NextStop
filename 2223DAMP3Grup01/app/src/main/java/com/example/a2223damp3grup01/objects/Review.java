package com.example.a2223damp3grup01.objects;

public class Review {
    private float rating;
    private String comment;
    private String gasolinera;

    public Review(float rating, String comment, String gasolinera) {
        this.rating = rating;
        this.comment = comment;
        this.gasolinera = gasolinera;
    }

    public Review(float rating, String review) {
        this.rating = rating;
        this.comment = review;
    }

    public Review(float rating) {
        this.rating = rating;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String review) {
        this.comment = review;
    }

    public String getGasolinera() {
        return gasolinera;
    }

    public void setGasolinera(String gasolinera) {
        this.gasolinera = gasolinera;
    }
}

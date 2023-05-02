package com.example.gappsoil_api.DTOs;

import com.example.gappsoil_api.entitats.Usuario;

import java.io.Serializable;

public class ValoracioDTONoid implements Serializable {

    private long id;


    private String comentari;

    private int puntuacio;

    private String image_url;

    public ValoracioDTONoid(long id, String comentari, int puntuacio, String image_url) {
        this.id = id;
        this.comentari = comentari;
        this.puntuacio = puntuacio;
        this.image_url = image_url;
    }


    public String getComentari() {
        return comentari;
    }

    public void setComentari(String comentari) {
        this.comentari = comentari;
    }

    public int getPuntuacio() {
        return puntuacio;
    }

    public void setPuntuacio(int puntuacio) {
        this.puntuacio = puntuacio;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}



package com.example.gappsoil_api.DTOs;

import com.example.gappsoil_api.entitats.Benzinera;
import com.example.gappsoil_api.entitats.Usuario;

import java.io.Serializable;

public class ValoracioDTOall implements Serializable {

    private long id;

    private long id_benzinera;

    private long id_user;

    private String comentari;

    private int puntuacio;

    private String image_url;

    public ValoracioDTOall(long id, long id_benzinera, long id_user, String comentari, int puntuacio, String image_url) {
        this.id = id;
        this.id_benzinera = id_benzinera;
        this.id_user = id_user;
        this.comentari = comentari;
        this.puntuacio = puntuacio;
        this.image_url = image_url;
    }

    public ValoracioDTOall() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId_benzinera() {
        return id_benzinera;
    }

    public void setId_benzinera(long id_benzinera) {
        this.id_benzinera = id_benzinera;
    }

    public long getId_user() {
        return id_user;
    }

    public void setId_user(long id_user) {
        this.id_user = id_user;
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
}

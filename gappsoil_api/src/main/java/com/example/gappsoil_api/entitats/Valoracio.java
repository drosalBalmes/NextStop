package com.example.gappsoil_api.entitats;


import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "VALORACIONS")
public class Valoracio implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RESEÑA_ID")
    private long id;

    @ManyToOne
    @JoinColumn(name = "BENZINERA_ID")
    private Benzinera benzinera;

    @ManyToOne
    @JoinColumn(name = "USERR_ID")
    private Usuario user;

    private String comentari;

    private int puntuacio;

    private String image_url;


    public Valoracio() {
    }

    public Valoracio(long id, Benzinera benzinera, Usuario user, String comentari, int puntuacio, String image_url) {
        this.id = id;
        this.benzinera = benzinera;
        this.user = user;
        this.comentari = comentari;
        this.puntuacio = puntuacio;
        this.image_url = image_url;
    }



    public long getId() {
        return id;
    }

    public void setId(long idReview) {
        this.id = idReview;
    }

    public Benzinera getBenzinera() {
        return benzinera;
    }

    public void setBenzinera(Benzinera benzinera) {
        this.benzinera = benzinera;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
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

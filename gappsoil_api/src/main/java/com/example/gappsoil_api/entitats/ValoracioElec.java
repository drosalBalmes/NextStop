package com.example.gappsoil_api.entitats;

import javax.persistence.*;
@Entity
@Table(name = "VALORACIONS_ELEC")
public class ValoracioElec {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RESEÑA_ELEC_ID")
    private long id;

    @ManyToOne
    @JoinColumn(name = "USERR_ID")
    private Usuario user;

    @ManyToOne
    @JoinColumn(name = "PUNT_RECARREGA_ID")
    private puntRecarrega puntRecarrega;

    private String comentari;

    private int puntuacio;

    private String image_url;

    public ValoracioElec(long id, Usuario user, com.example.gappsoil_api.entitats.puntRecarrega puntRecarrega, String comentari, int puntuacio, String image_url) {
        this.id = id;
        this.user = user;
        this.puntRecarrega = puntRecarrega;
        this.comentari = comentari;
        this.puntuacio = puntuacio;
        this.image_url = image_url;
    }

    public ValoracioElec() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

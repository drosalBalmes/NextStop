package com.example.gappsoil_api.entitats;


import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "USUARIOS")
public class Usuario implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USERR_ID")
    private long id;

    private String username;

    private String password;

    private String email;

    @OneToMany(mappedBy = "user")
    private List<Valoracio> valoracions;

    @OneToMany(mappedBy = "user")
    private List<ValoracioElec> valoracionsElec;

    public Usuario() {
    }

    public Usuario(long id, String username, String password, String email, List<Valoracio> valoracions) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.valoracions = valoracions;
    }

    public long getId() {
        return id;
    }

    public void setId(long idUsuario) {
        this.id = idUsuario;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Valoracio> getValoracions() {
        return valoracions;
    }

    public void setValoracions(List<Valoracio> valoracions) {
        this.valoracions = valoracions;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<ValoracioElec> getValoracionsElec() {
        return valoracionsElec;
    }

    public void setValoracionsElec(List<ValoracioElec> valoracionsElec) {
        this.valoracionsElec = valoracionsElec;
    }
}

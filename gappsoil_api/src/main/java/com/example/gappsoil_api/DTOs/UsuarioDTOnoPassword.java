package com.example.gappsoil_api.DTOs;

import com.example.gappsoil_api.entitats.Valoracio;
import com.example.gappsoil_api.entitats.ValoracioElec;

import javax.persistence.*;
import java.util.List;

public class UsuarioDTOnoPassword {

    private long id;

    private String username;

    private String email;

    private List<Valoracio> valoracions;

    private List<ValoracioElec> valoracionsElec;

    public UsuarioDTOnoPassword(long id, String username, String email, List<Valoracio> valoracions, List<ValoracioElec> valoracionsElec) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.valoracions = valoracions;
        this.valoracionsElec = valoracionsElec;
    }

    public UsuarioDTOnoPassword() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Valoracio> getValoracions() {
        return valoracions;
    }

    public void setValoracions(List<Valoracio> valoracions) {
        this.valoracions = valoracions;
    }

    public List<ValoracioElec> getValoracionsElec() {
        return valoracionsElec;
    }

    public void setValoracionsElec(List<ValoracioElec> valoracionsElec) {
        this.valoracionsElec = valoracionsElec;
    }
}

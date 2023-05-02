package com.example.a2223damp3grup01.objects;


import java.util.List;

public class UsuarioDTOnoPassword {

    private long id;

    private String username;

    private String email;

    private List<ValoracioDTO> valoracions;

    //private List<ValoracioElec> valoracionsElec;

    public UsuarioDTOnoPassword(long id, String username, String email, List<ValoracioDTO> valoracions) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.valoracions = valoracions;
        //this.valoracionsElec = valoracionsElec;
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

    public List<ValoracioDTO> getValoracions() {
        return valoracions;
    }

    public void setValoracions(List<ValoracioDTO> valoracions) {
        this.valoracions = valoracions;
    }

    //public List<ValoracioElec> getValoracionsElec() {
        //return valoracionsElec;
    //}

    //public void setValoracionsElec(List<ValoracioElec> valoracionsElec) {
      //  this.valoracionsElec = valoracionsElec;
    //}
}

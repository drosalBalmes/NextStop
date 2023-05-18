package com.example.a2223damp3grup01.objects;

public class ResumRutaObject {

    String parada;

    String direccio;

    public ResumRutaObject(String parada, String direccio) {
        this.parada = parada;
        this.direccio = direccio;
    }

    public ResumRutaObject() {
    }


    public String getParada() {
        return parada;
    }

    public void setParada(String parada) {
        this.parada = parada;
    }

    public String getDireccio() {
        return direccio;
    }

    public void setDireccio(String direccio) {
        this.direccio = direccio;
    }
}

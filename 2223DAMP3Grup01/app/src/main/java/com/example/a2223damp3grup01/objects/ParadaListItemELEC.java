package com.example.a2223damp3grup01.objects;

import java.util.List;

public class ParadaListItemELEC {
    public String numeroParada;

    public List<PuntRecarrega> puntsList;

    public String type;

    public ParadaListItemELEC(String numeroParada, List<PuntRecarrega> puntsList, String type) {
        this.numeroParada = numeroParada;
        this.puntsList = puntsList;
        this.type = type;
    }


    public ParadaListItemELEC(String numeroParada, List<PuntRecarrega> puntsList) {
        this.numeroParada = numeroParada;
        this.puntsList = puntsList;
    }

    public ParadaListItemELEC() {
    }

    public String getNumeroParada() {
        return numeroParada;
    }

    public void setNumeroParada(String numeroParada) {
        this.numeroParada = numeroParada;
    }

    public List<PuntRecarrega> getPuntsList() {
        return puntsList;
    }

    public void setPuntsList(List<PuntRecarrega> puntsList) {
        this.puntsList = puntsList;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

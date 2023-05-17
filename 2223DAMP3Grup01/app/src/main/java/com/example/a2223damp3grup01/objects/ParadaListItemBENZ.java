package com.example.a2223damp3grup01.objects;

import java.util.List;

public class ParadaListItemBENZ {

    public String numeroParada;

    public List<Benzinera>  benzList;

    public int type;
/*////////////////////////////////////////////////////////////////////////////////////////////////////////*/

    public ParadaListItemBENZ(String numeroParada, List<Benzinera> benzList, int type) {
        this.numeroParada = numeroParada;
        this.benzList = benzList;
        this.type = type;
    }

    public ParadaListItemBENZ(String numeroParada, List<Benzinera> benzList) {
        this.numeroParada = numeroParada;
        this.benzList = benzList;
    }

    public ParadaListItemBENZ() {
    }
    /*////////////////////////////////////////////////////////////////////////////////////////////////////////*/

    public String getNumeroParada() {
        return numeroParada;
    }

    public void setNumeroParada(String numeroParada) {
        this.numeroParada = numeroParada;
    }

    public List<Benzinera> getBenzList() {
        return benzList;
    }

    public void setBenzList(List<Benzinera> benzList) {
        this.benzList = benzList;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}

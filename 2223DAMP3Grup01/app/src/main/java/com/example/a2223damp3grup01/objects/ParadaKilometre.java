package com.example.a2223damp3grup01.objects;

import android.widget.EditText;

public class ParadaKilometre {

    String numParadaString;

    int numParadaInt;

    EditText kmET;

    String kmString;

    public ParadaKilometre(String numParadaString, int numParadaInt, EditText kmET, String kmString) {
        this.numParadaString = numParadaString;
        this.numParadaInt = numParadaInt;
        this.kmET = kmET;
        this.kmString = kmString;
    }

    public ParadaKilometre() {
    }

    public String getNumParadaString() {
        return numParadaString;
    }

    public void setNumParadaString(String numParadaString) {
        this.numParadaString = numParadaString;
    }

    public int getNumParadaInt() {
        return numParadaInt;
    }

    public void setNumParadaInt(int numParadaInt) {
        this.numParadaInt = numParadaInt;
    }

    public EditText getKmET() {
        return kmET;
    }

    public void setKmET(EditText kmET) {
        this.kmET = kmET;
    }

    public String getKmString() {
        return kmString;
    }

    public void setKmString(String kmString) {
        this.kmString = kmString;
    }
}


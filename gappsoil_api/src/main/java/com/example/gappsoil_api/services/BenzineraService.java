package com.example.gappsoil_api.services;

import com.example.gappsoil_api.entitats.Benzinera;
import com.example.gappsoil_api.repositories.BenzineraRepositori;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BenzineraService {
    @Autowired
    private final BenzineraRepositori benzineraRepositori;


    public BenzineraService(BenzineraRepositori benzineraRepositori) {
        this.benzineraRepositori = benzineraRepositori;
    }

    public List<Benzinera> getAll(){
        return benzineraRepositori.findAll();
    }

    public Benzinera getBenzineraById(long id){
        return benzineraRepositori.findBenzineraById(id);
    }

    public List<Benzinera> getGASOILbenz(){
        return benzineraRepositori.findBenzinerasByGasoilIsTrue();
    }
    public List<Benzinera> getGASOLINAbenz(){
        return benzineraRepositori.findBenzinerasBySP95IsTrueOrSP98IsTrue();

    }

    public List<Benzinera> getGNLbenz(){
        return benzineraRepositori.findBenzinerasByGNLIsTrue();

    }
    public List<Benzinera> getGNCbenz(){
        return benzineraRepositori.findBenzinerasByGNCIsTrue();

    }
    public List<Benzinera> getGLPbenz(){
        return benzineraRepositori.findBenzinerasByGLPIsTrue();

    }
    public List<Benzinera> getSP98benz(){
        return benzineraRepositori.findBenzinerasBySP98IsTrue();

    }

    public List<Benzinera> getSP95benz(){
        return benzineraRepositori.findBenzinerasBySP95IsTrue();

    }


}

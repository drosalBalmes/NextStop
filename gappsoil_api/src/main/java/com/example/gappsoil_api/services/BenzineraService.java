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
}

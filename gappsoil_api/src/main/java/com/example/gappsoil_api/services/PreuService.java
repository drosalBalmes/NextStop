package com.example.gappsoil_api.services;

import com.example.gappsoil_api.entitats.Benzinera;
import com.example.gappsoil_api.entitats.Preu;
import com.example.gappsoil_api.repositories.PreuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PreuService {
    @Autowired
    private final PreuRepository preuRepository;




    public PreuService(PreuRepository preuRepository) {
        this.preuRepository = preuRepository;
    }

    public List<Preu> findpreusByBenz(Benzinera b){
        return preuRepository.findPreusByBenzinera(b);
    }

    public Preu findPreuByBenz(Benzinera b){
        List <Preu> aaa = preuRepository.findPreusByBenzinera(b);

        return aaa.get(aaa.size()-1);
    }

}

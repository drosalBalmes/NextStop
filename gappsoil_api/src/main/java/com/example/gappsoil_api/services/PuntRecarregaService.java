package com.example.gappsoil_api.services;

import com.example.gappsoil_api.entitats.puntRecarrega;
import com.example.gappsoil_api.repositories.PuntRecarregaRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PuntRecarregaService {
    
    @Autowired 
    final PuntRecarregaRepository puntRecarregaRepository;


    public PuntRecarregaService(PuntRecarregaRepository puntRecarregaRepository) {
        this.puntRecarregaRepository = puntRecarregaRepository;
    }
    
    public List<puntRecarrega> findAll(){
        return puntRecarregaRepository.findAll();
    }
}

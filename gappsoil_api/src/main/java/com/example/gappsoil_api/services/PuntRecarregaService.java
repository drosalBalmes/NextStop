package com.example.gappsoil_api.services;

import com.example.gappsoil_api.entitats.puntRecarrega;
import com.example.gappsoil_api.repositories.PuntRecarregaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PuntRecarregaService {
    
    @Autowired 
    final PuntRecarregaRepository puntRecarregaRepository;


    public PuntRecarregaService(PuntRecarregaRepository puntRecarregaRepository) {
        this.puntRecarregaRepository = puntRecarregaRepository;
    }
    
    public List<puntRecarrega> findAll(){
        return puntRecarregaRepository.findAll();
    }

    public puntRecarrega findById(long id){
        return puntRecarregaRepository.findById(id);
    }

    public List<puntRecarrega> findByCarregador ( String carregador){
        return puntRecarregaRepository.findByTipusConexioContainingIgnoreCase(carregador);
    }
}

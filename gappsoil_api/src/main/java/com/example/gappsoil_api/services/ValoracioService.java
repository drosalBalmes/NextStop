package com.example.gappsoil_api.services;

import com.example.gappsoil_api.entitats.Benzinera;
import com.example.gappsoil_api.entitats.Usuario;
import com.example.gappsoil_api.entitats.Valoracio;
import com.example.gappsoil_api.repositories.ValoracioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ValoracioService {
    @Autowired
    private final ValoracioRepository valoracioRepository;

    public ValoracioService(ValoracioRepository valoracioRepository) {
        this.valoracioRepository = valoracioRepository;
    }


    public int insertNewValoracio(String comentari, int puntuacio,long benzinera_id,long user_id){
        return valoracioRepository.insertNewValoracio(comentari, puntuacio, benzinera_id, user_id);
    }

    public int numValoracionsByBenzId(long benzId){
        return valoracioRepository.numValoracionsByBenzId(benzId);
    }

    public Double avgValoracionsByBenzId(long benzId){
        return valoracioRepository.avgValoracionsByBenzId(benzId);
    }

    public List<Valoracio> valoracionsByBenzinera(Benzinera b){
        return valoracioRepository.findValoraciosByBenzinera(b);
    }

    public List<Valoracio> valoracionsByUser(Usuario u){
        return valoracioRepository.findValoraciosByUser(u);

    }

    public Valoracio valoracioById(long id){
        return valoracioRepository.findValoracioById(id);
    }
}

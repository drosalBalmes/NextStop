package com.example.gappsoil_api.services;

import com.example.gappsoil_api.entitats.Usuario;
import com.example.gappsoil_api.entitats.ValoracioElec;
import com.example.gappsoil_api.entitats.puntRecarrega;
import com.example.gappsoil_api.repositories.ValoracioElecRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ValoracioElecService {

    @Autowired
    private final ValoracioElecRepository valoracioElecRepository;

    public ValoracioElecService(ValoracioElecRepository valoracioElecRepository) {
        this.valoracioElecRepository = valoracioElecRepository;
    }


    public int insertNewValoracioElec(String comentari,int puntuacio, long puntRecarrega_id,long user_id){
        return valoracioElecRepository.insertNewValoracioElec(comentari,puntuacio,puntRecarrega_id,user_id);

    }



    public List<ValoracioElec> valoracionsByPuntRecarrega(puntRecarrega pr){
        return valoracioElecRepository.findValoracioElecsByPuntRecarrega(pr);
    }

    public List<ValoracioElec> ValoracionsByUser(Usuario u){
        return valoracioElecRepository.findValoracioElecsByUser(u);
    }

    public ValoracioElec valoracioById(long id){
        return valoracioElecRepository.findValoracioElecById(id);
    }


    public int numValoracionsByBenzId(long puntId){
        return valoracioElecRepository.numValoracionsByPuntId(puntId);
    }

    public Double avgValoracionsByBenzId(long puntId){
        return valoracioElecRepository.avgValoracionsByPuntId(puntId);
    }





}

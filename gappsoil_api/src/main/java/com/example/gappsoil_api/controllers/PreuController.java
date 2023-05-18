package com.example.gappsoil_api.controllers;

import com.example.gappsoil_api.DTOs.PreuDTO;
import com.example.gappsoil_api.entitats.Benzinera;
import com.example.gappsoil_api.entitats.Preu;
import com.example.gappsoil_api.repositories.BenzineraRepositori;
import com.example.gappsoil_api.repositories.PreuRepository;
import com.example.gappsoil_api.repositories.UsuarioRepository;
import com.example.gappsoil_api.repositories.ValoracioRepository;
import com.example.gappsoil_api.services.BenzineraService;
import com.example.gappsoil_api.services.PreuService;
import com.example.gappsoil_api.services.UsuarioService;
import com.example.gappsoil_api.services.ValoracioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/preus")
public class PreuController {
    @Autowired
    BenzineraRepositori benzineraRepositori;

    @Autowired
    PreuRepository preuRepository;

    @Autowired
    ValoracioRepository valoracioRepository;

    @Autowired
    UsuarioRepository usuarioRepository;


    @Autowired
    BenzineraService benzineraService = new BenzineraService(benzineraRepositori);

    @Autowired
    PreuService preuService = new PreuService(preuRepository);

    @Autowired
    ValoracioService valoracioService = new ValoracioService(valoracioRepository);

    @Autowired
    UsuarioService usuarioService = new UsuarioService(usuarioRepository);

    @GetMapping("/preusByBenzId")
    public List<PreuDTO> preusBenz(@RequestParam("benzID")long benzID){
        List<PreuDTO> toreturn = new ArrayList<>();

        Benzinera b = benzineraService.getBenzineraById(benzID);
        List<Preu> preus = preuService.findpreusByBenz(b);

        for (Preu p :
                preus) {
            toreturn.add(new PreuDTO(p.getId(),
                    p.getUltimaAct(),
                    p.getPreuSP95(),
                    p.getPreuSP98(),
                    p.getPreuGasoil(),
                    p.getPreuGLP(),
                    p.getPreuGNC(),
                    p.getPreuGNL()
                    ));
        }

        return toreturn;
    }
    @GetMapping("/lastPriceBenzId")
    public PreuDTO preuBenz(@RequestParam("benzID")long benzID){

        Benzinera b = benzineraService.getBenzineraById(benzID);
        List<Preu> preus = preuService.findpreusByBenz(b);

        Preu p = preus.get(preus.size()-1);

        PreuDTO toreturn = new PreuDTO(p.getId(),
                    p.getUltimaAct(),
                    p.getPreuSP95(),
                    p.getPreuSP98(),
                    p.getPreuGasoil(),
                    p.getPreuGLP(),
                    p.getPreuGNC(),
                    p.getPreuGNL());

        return toreturn;

    }

    }



package com.example.gappsoil_api.controllers;

import com.example.gappsoil_api.DTOs.BenzineraDTOnoPriceNoVal;
import com.example.gappsoil_api.entitats.Benzinera;
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
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("benzineres")
public class BenzineraController {
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


    @GetMapping("/benzinerasNoPreusNoReviews")
    public List<BenzineraDTOnoPriceNoVal> llistatBenzineres(){
        List<Benzinera> benzineres =benzineraService.getAll();
        List<BenzineraDTOnoPriceNoVal> benzineresR = new ArrayList<>();

        for (Benzinera b: benzineres) {
            benzineresR.add( new BenzineraDTOnoPriceNoVal(
                    b.getId(),
                    b.getNom(),
                    b.getLatitude(),
                    b.getLongitude(),
                    b.isGasolina(),
                    b.isSP95(),
                    b.isSP98(),
                    b.isGasoil(),
                    b.isAdblue(),
                    b.isHidrogen(),
                    b.getHorari()
            ));
        }
        return benzineresR;
    }



}

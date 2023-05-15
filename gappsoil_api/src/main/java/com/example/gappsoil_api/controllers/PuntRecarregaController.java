package com.example.gappsoil_api.controllers;

import com.example.gappsoil_api.DTOs.BenzineraDTOnoPriceNoVal;
import com.example.gappsoil_api.DTOs.PuntRecargaDTOnoReviews;
import com.example.gappsoil_api.entitats.Benzinera;
import com.example.gappsoil_api.entitats.puntRecarrega;
import com.example.gappsoil_api.repositories.*;
import com.example.gappsoil_api.services.*;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("puntsRecarrega")
public class PuntRecarregaController {
    @Autowired
    BenzineraRepositori benzineraRepositori;

    @Autowired
    PreuRepository preuRepository;

    @Autowired
    ValoracioRepository valoracioRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    PuntRecarregaRepository puntRecarregaRepository;


    @Autowired
    BenzineraService benzineraService = new BenzineraService(benzineraRepositori);

    @Autowired
    PreuService preuService = new PreuService(preuRepository);

    @Autowired
    ValoracioService valoracioService = new ValoracioService(valoracioRepository);

    @Autowired
    UsuarioService usuarioService = new UsuarioService(usuarioRepository);

    @Autowired
    PuntRecarregaService puntRecarregaService = new PuntRecarregaService(puntRecarregaRepository);

    @GetMapping("puntsRecarregaNoPreusNoReviews")
    public List<PuntRecargaDTOnoReviews> llistatPunts(){
        List<puntRecarrega> punts= puntRecarregaService.findAll();
        List<PuntRecargaDTOnoReviews> puntsR = new ArrayList<>();

        for (puntRecarrega pr: punts){
            puntsR.add( new PuntRecargaDTOnoReviews(
                    pr.getId(),
                    pr.getTipusConexio(),
                    pr.getLatitude(),
                    pr.getLongitude(),
                    pr.getNom(),
                    pr.getTipusCorrent(),
                    pr.getNumPlaces(),
                    pr.getTipusVehicles()
            ));
        }

        return puntsR;
    }

    @GetMapping("puntsRecarregaFinder")
    public List<PuntRecargaDTOnoReviews> puntsFinder(@RequestParam("locationLONG")double locationLNG,
                                                     @RequestParam("locationLAT")double locationLAT,
                                                     @RequestParam("KMredonda")double KMredonda){

        List<puntRecarrega> punts= puntRecarregaService.findAll();
        List<PuntRecargaDTOnoReviews> puntsR = new ArrayList<>();

        final double radiTerra = 6371.01;

        double radius = KMredonda; // en kilómetros

        double latRad = Math.toRadians(locationLAT);
        double lonRad = Math.toRadians(locationLNG);

        double deltaLon = Math.asin(Math.sin(radius / (2 * radiTerra)) / Math.cos(latRad));

        double minLat = latRad - deltaLon;
        double minLon = lonRad - Math.atan2(Math.sin(2 * deltaLon), Math.cos(minLat) * Math.cos(latRad));

        double maxLat = latRad + deltaLon;
        double maxLon = lonRad + Math.atan2(Math.sin(2 * deltaLon), Math.cos(maxLat) * Math.cos(latRad));

        double minLatDeg = Math.toDegrees(minLat);
        double minLonDeg = Math.toDegrees(minLon);
        double maxLatDeg = Math.toDegrees(maxLat);
        double maxLonDeg = Math.toDegrees(maxLon);


        for (puntRecarrega pr: punts){
            puntsR.add( new PuntRecargaDTOnoReviews(
                    pr.getId(),
                    pr.getTipusConexio(),
                    pr.getLatitude(),
                    pr.getLongitude(),
                    pr.getNom(),
                    pr.getTipusCorrent(),
                    pr.getNumPlaces(),
                    pr.getTipusVehicles()
            ));
        }

        Iterator<PuntRecargaDTOnoReviews> iterator = puntsR.iterator();

        while (iterator.hasNext()) {
            PuntRecargaDTOnoReviews pr = iterator.next();
            if (pr.getLatitude()<minLatDeg || pr.getLatitude()>maxLatDeg || pr.getLongitude()<minLonDeg || pr.getLongitude()>maxLonDeg){
                iterator.remove();
            }
        }


        return puntsR;

    }


}

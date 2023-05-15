package com.example.gappsoil_api.controllers;

import com.example.gappsoil_api.DTOs.*;
import com.example.gappsoil_api.entitats.*;
import com.example.gappsoil_api.objects.distances;
import com.example.gappsoil_api.repositories.*;
import com.example.gappsoil_api.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("valoracioElec")
public class ValoracioElecController {
    @Autowired
    BenzineraRepositori benzineraRepositori;

    @Autowired
    PreuRepository preuRepository;

    @Autowired
    ValoracioRepository valoracioRepository;

    @Autowired
    ValoracioElecRepository valoracioElecRepository;
    @Autowired
    PuntRecarregaRepository puntRecarregaRepository;

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

    @Autowired
    ValoracioElecService valoracioElecService = new ValoracioElecService(valoracioElecRepository);

    @Autowired
    PuntRecarregaService puntRecarregaService = new PuntRecarregaService(puntRecarregaRepository);


    @PostMapping("newValoracio")
    public ResponseEntity newValoracio(@RequestParam("comentari") String comentari,
                                       @RequestParam("puntuacio") int puntuacio,
                                       @RequestParam("puntRecarrega_id") long puntRecarrega_id,
                                       @RequestParam("user_id") long user_id) {

        int result = valoracioElecService.insertNewValoracioElec(comentari, puntuacio, puntRecarrega_id, user_id);

        if (result != 1) {
            return new ResponseEntity<>("not succed", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("success", HttpStatus.OK);

    }

    @GetMapping("valoracionsByPuntRecarrega/idPuntRecarrega/{id}")
    public List<ValoracioElecDTOnoId> findValoracioByBenzinera(@PathVariable long id) {
        puntRecarrega pr = puntRecarregaService.findById(id);

        List<ValoracioElec> valoracioElecList= valoracioElecService.valoracionsByPuntRecarrega(pr);

        List<ValoracioElecDTOnoId> valoracionsReturn = new ArrayList<>();

        for (ValoracioElec v:valoracioElecList){
            valoracionsReturn.add(new ValoracioElecDTOnoId(
                    v.getId(),
                    v.getComentari(),
                    v.getPuntuacio(),
                    v.getImage_url()
            ));
        }

        return valoracionsReturn;

    }

    @GetMapping("valoracionsByUser/idUser/{id}")
    public List<ValoracioDTONoid> findValoracioByUser(@PathVariable long id){
        Usuario u = usuarioService.findUsuarioById(id);
        List<Valoracio> valoracioList = valoracioService.valoracionsByUser(u);
        List<ValoracioDTONoid> valoracionsReturn= new ArrayList<>();

        for (Valoracio v : valoracioList) {
            valoracionsReturn.add(new ValoracioDTONoid(
                    v.getId(),
                    v.getComentari(),
                    v.getPuntuacio(),
                    v.getImage_url()
            ));
        }

        return valoracionsReturn;


    }







}

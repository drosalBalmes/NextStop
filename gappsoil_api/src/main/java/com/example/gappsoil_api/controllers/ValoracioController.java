package com.example.gappsoil_api.controllers;

import com.example.gappsoil_api.DTOs.ValoracioDTO;
import com.example.gappsoil_api.DTOs.ValoracioDTONoid;
import com.example.gappsoil_api.DTOs.ValoracioDTOall;
import com.example.gappsoil_api.entitats.Benzinera;
import com.example.gappsoil_api.entitats.Usuario;
import com.example.gappsoil_api.entitats.Valoracio;
import com.example.gappsoil_api.repositories.BenzineraRepositori;
import com.example.gappsoil_api.repositories.PreuRepository;
import com.example.gappsoil_api.repositories.UsuarioRepository;
import com.example.gappsoil_api.repositories.ValoracioRepository;
import com.example.gappsoil_api.services.BenzineraService;
import com.example.gappsoil_api.services.PreuService;
import com.example.gappsoil_api.services.UsuarioService;
import com.example.gappsoil_api.services.ValoracioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("valoracio")
public class ValoracioController {

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


    @PostMapping("newValoracio")
    public ResponseEntity newValoracio(@RequestParam("comentari") String comentari,
                                       @RequestParam("puntuacio") int puntuacio,
                                       @RequestParam("benzinera_id") long benzinera_id,
                                       @RequestParam("user_id") long user_id) {

        int result = valoracioService.insertNewValoracio(comentari, puntuacio, benzinera_id, user_id);

        if (result != 1) {
            return new ResponseEntity<>("not succed", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("success", HttpStatus.OK);

    }

    @GetMapping("valoracionsByBenzinera/idBenzinera/{id}")
    public List<ValoracioDTONoid> findValoracioByBenzinera(@PathVariable long id) {
        Benzinera benzinera = benzineraService.getBenzineraById(id);
        List<Valoracio> valoracioList = valoracioService.valoracionsByBenzinera(benzinera);
        List<ValoracioDTONoid> valoracionsReturn = new ArrayList<>();

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

    @GetMapping("valoracioById/{id}")
    public ValoracioDTOall findValoracioById(@PathVariable long id){
        Valoracio val = valoracioService.valoracioById(id);
        ValoracioDTOall returnn = new ValoracioDTOall(
                val.getId(),
                val.getBenzinera().getId(),
                val.getUser().getId(),
                val.getComentari(),
                val.getPuntuacio(),
                val.getImage_url());



        return returnn;


    }


}

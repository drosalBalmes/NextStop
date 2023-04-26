package com.example.gappsoil_api.controllers;

import com.example.gappsoil_api.DTOs.UsuarioDTOnoPassword;
import com.example.gappsoil_api.entitats.Usuario;
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
@RequestMapping("usuaris")
public class UsuarioController {
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


    @GetMapping("/usuariosNoPassword")
    public List<UsuarioDTOnoPassword> llistaUsuarisNoPassword(){
        List<Usuario> usuarios = usuarioService.findAll();
        List<UsuarioDTOnoPassword> usuariosReturn= new ArrayList<>();


        for (Usuario u : usuarios) {
            usuariosReturn.add(new UsuarioDTOnoPassword(
               u.getId(),
               u.getUsername(),
               u.getEmail(),
               u.getValoracions(),
               u.getValoracionsElec()
            ));
        }

        return usuariosReturn;
    }


}

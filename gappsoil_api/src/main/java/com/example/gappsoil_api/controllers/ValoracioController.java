package com.example.gappsoil_api.controllers;

import com.example.gappsoil_api.repositories.BenzineraRepositori;
import com.example.gappsoil_api.repositories.PreuRepository;
import com.example.gappsoil_api.repositories.UsuarioRepository;
import com.example.gappsoil_api.repositories.ValoracioRepository;
import com.example.gappsoil_api.services.BenzineraService;
import com.example.gappsoil_api.services.PreuService;
import com.example.gappsoil_api.services.UsuarioService;
import com.example.gappsoil_api.services.ValoracioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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











}

package com.example.gappsoil_api.controllers;

import com.example.gappsoil_api.repositories.*;
import com.example.gappsoil_api.services.*;
import org.springframework.beans.factory.annotation.Autowired;

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




}

package com.example.gappsoil_api.services;

import com.example.gappsoil_api.repositories.PreuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PreuService {
    @Autowired
    private final PreuRepository preuRepository;


    public PreuService(PreuRepository preuRepository) {
        this.preuRepository = preuRepository;
    }



}

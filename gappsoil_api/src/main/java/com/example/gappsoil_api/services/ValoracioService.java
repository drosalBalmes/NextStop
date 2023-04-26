package com.example.gappsoil_api.services;

import com.example.gappsoil_api.repositories.ValoracioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValoracioService {
    @Autowired
    private final ValoracioRepository valoracioRepository;

    public ValoracioService(ValoracioRepository valoracioRepository) {
        this.valoracioRepository = valoracioRepository;
    }
}

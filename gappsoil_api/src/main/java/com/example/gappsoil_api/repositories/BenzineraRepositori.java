package com.example.gappsoil_api.repositories;

import com.example.gappsoil_api.entitats.Benzinera;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BenzineraRepositori extends JpaRepository<Benzinera,Long> {

    List<Benzinera> findAll();

    Benzinera findBenzineraById(long id);


}

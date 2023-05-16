package com.example.gappsoil_api.repositories;

import com.example.gappsoil_api.entitats.Benzinera;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BenzineraRepositori extends JpaRepository<Benzinera,Long> {

    List<Benzinera> findAll();

    Benzinera findBenzineraById(long id);


    List<Benzinera> findBenzinerasByGasoilIsTrue();
    List<Benzinera> findBenzinerasBySP98IsTrue();
    List<Benzinera> findBenzinerasBySP95IsTrue();
    List<Benzinera> findBenzinerasBySP95IsTrueAndSP98IsTrue();
    List<Benzinera> findBenzinerasBySP95IsTrueOrSP98IsTrue();



    List<Benzinera> findBenzinerasByGNLIsTrue();
    List<Benzinera> findBenzinerasByGNCIsTrue();
    List<Benzinera> findBenzinerasByGLPIsTrue();



}

package com.example.gappsoil_api.repositories;

import com.example.gappsoil_api.entitats.Usuario;
import com.example.gappsoil_api.entitats.puntRecarrega;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PuntRecarregaRepository extends JpaRepository<puntRecarrega,Long> {
    List<puntRecarrega> findAll();

}

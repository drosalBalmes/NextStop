package com.example.gappsoil_api.repositories;

import com.example.gappsoil_api.entitats.Benzinera;
import com.example.gappsoil_api.entitats.Preu;
import org.hibernate.metamodel.model.convert.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PreuRepository extends JpaRepository<Preu,Long> {

    List<Preu> findPreusByBenzinera(Benzinera b);


}

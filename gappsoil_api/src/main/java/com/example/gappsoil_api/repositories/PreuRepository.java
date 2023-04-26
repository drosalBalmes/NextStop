package com.example.gappsoil_api.repositories;

import com.example.gappsoil_api.entitats.Preu;
import org.hibernate.metamodel.model.convert.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PreuRepository extends JpaRepository<Preu,Long> {


}

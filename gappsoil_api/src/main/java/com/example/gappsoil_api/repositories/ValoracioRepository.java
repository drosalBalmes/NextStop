package com.example.gappsoil_api.repositories;

import com.example.gappsoil_api.entitats.Benzinera;
import com.example.gappsoil_api.entitats.Usuario;
import com.example.gappsoil_api.entitats.Valoracio;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface ValoracioRepository extends JpaRepository<Valoracio,Long> {


    List<Valoracio> findValoraciosByBenzinera(Benzinera b);

    List<Valoracio> findValoraciosByUser(Usuario u);

    Valoracio findValoracioById(long id);

    @Query(value = "SELECT COUNT(*) FROM VALORACIONS WHERE BENZINERA_ID = :benzId", nativeQuery = true)
    int numValoracionsByBenzId(@Param("benzId")long benzId);

    @Query(value = "SELECT AVG(PUNTUACIO) FROM VALORACIONS WHERE BENZINERA_ID = :benzId", nativeQuery = true)
    Double avgValoracionsByBenzId(@Param("benzId")long benzId);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO VALORACIONS(COMENTARI,PUNTUACIO,BENZINERA_ID,USERR_ID) VALUES (:comentari,:puntuacio,:benzinera_id,:user_id)",nativeQuery = true)
    int insertNewValoracio(@Param("comentari")String comentari,
                            @Param("puntuacio")int puntuacio,
                            @Param("benzinera_id")long benzinera_id,
                            @Param("user_id")long user_id);

}

package com.example.gappsoil_api.repositories;

import com.example.gappsoil_api.entitats.Usuario;
import com.example.gappsoil_api.entitats.Valoracio;
import com.example.gappsoil_api.entitats.ValoracioElec;
import com.example.gappsoil_api.entitats.puntRecarrega;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ValoracioElecRepository extends JpaRepository<ValoracioElec,Long> {
    List<ValoracioElec> findValoracioElecsByPuntRecarrega(puntRecarrega pr);

    List<ValoracioElec> findValoracioElecsByUser(Usuario u);

    ValoracioElec findValoracioElecById(long id);

    @Query(value = "SELECT COUNT(*) FROM VALORACIONS_ELEC WHERE PUNT_RECARREGA_ID = :puntId", nativeQuery = true)
    int numValoracionsByPuntId(@Param("puntId")long puntId);

    @Query(value = "SELECT AVG(PUNTUACIO) FROM VALORACIONS_ELEC WHERE PUNT_RECARREGA_ID = :puntId", nativeQuery = true)
    Double avgValoracionsByPuntId(@Param("puntId")long puntId);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO VALORACIONS_ELEC(COMENTARI,PUNTUACIO,PUNT_RECARREGA_ID,USERR_ID) VALUES (:comentari,:puntuacio,:puntRecarrega_id,:user_id)",nativeQuery = true)
    int insertNewValoracioElec(@Param("comentari")String comentari,
                           @Param("puntuacio")int puntuacio,
                           @Param("puntRecarrega_id")long puntRecarrega_id,
                           @Param("user_id")long user_id);

}

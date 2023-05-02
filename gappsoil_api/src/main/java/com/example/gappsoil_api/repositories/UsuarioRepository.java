package com.example.gappsoil_api.repositories;

import com.example.gappsoil_api.entitats.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    List<Usuario> findAll();

    Usuario findUsuarioByEmail(String email);

    Usuario findUsuarioByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);


    Usuario findUsuarioById(Long id);

    @Query(value = "SELECT email FROM USUARIOS WHERE email = :email", nativeQuery = true)
    List<String>  checkUserEmail(@Param("email")String email);

    @Query(value = "SELECT password FROM USUARIOS WHERE email = :email", nativeQuery = true)
    String checkUserPasswordByEmail(@Param("email")String email);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO USUARIOS (EMAIL,PASSWORD,USERNAME) VALUES (:email,:password,:username)", nativeQuery = true)
    int registerNewUser(@Param("email") String email,
                        @Param("password")String password,
                        @Param("username")String username);
}

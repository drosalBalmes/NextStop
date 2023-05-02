package com.example.gappsoil_api.services;

import com.example.gappsoil_api.entitats.Usuario;
import com.example.gappsoil_api.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    private final UsuarioRepository usuarioRepository;


    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> findAll(){
        return usuarioRepository.findAll();
    }

    public Usuario findUsuarioByEmail(String email){
        return usuarioRepository.findUsuarioByEmail(email);
    }

    public Usuario findUsuarioById(Long id){
        return usuarioRepository.findUsuarioById(id);
    }

    public Usuario findUsuarioByUsername(String username){
        return usuarioRepository.findUsuarioByUsername(username);
    }

    public boolean existsByUsername(String username){
        return usuarioRepository.existsByUsername(username);
    }

    public boolean existsByEmail(String email){
        return usuarioRepository.existsByEmail(email);
    }
    public List<String> checkUserEmail(String email){
        return usuarioRepository.checkUserEmail(email);
    }

    public String checkUserPasswordByEmail(String email){
        return usuarioRepository.checkUserPasswordByEmail(email);
    }

    public int registerNewUserServiceMethod(String email,String password,String username){
        return usuarioRepository.registerNewUser(email,password,username);

    }


}

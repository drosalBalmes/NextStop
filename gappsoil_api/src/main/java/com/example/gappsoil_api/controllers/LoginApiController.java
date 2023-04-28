package com.example.gappsoil_api.controllers;


import com.example.gappsoil_api.entitats.Login;
import com.example.gappsoil_api.entitats.Usuario;
import com.example.gappsoil_api.repositories.UsuarioRepository;
import com.example.gappsoil_api.services.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController()
public class LoginApiController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    UsuarioService usuarioService = new UsuarioService(usuarioRepository);



    @PostMapping("/login")
    public ResponseEntity authUser(@RequestBody Login login){

        List<String> userEmail = usuarioService.checkUserEmail(login.getEmail());

        if (userEmail.isEmpty()|| userEmail==null){
            return new ResponseEntity("Email error", HttpStatus.NOT_FOUND);

        }

        String password = usuarioService.checkUserPasswordByEmail(login.getEmail());
        String hashedPassword = usuarioService.checkUserPasswordByEmail(login.getEmail());


        if (!BCrypt.checkpw(login.getPassword(), hashedPassword)){
            return new ResponseEntity("o la contrasenya o lusuari estan malament",HttpStatus.BAD_REQUEST);

        }


        Usuario usuario = usuarioService.findUsuarioByEmail(login.getEmail());
        return  new ResponseEntity(usuario,HttpStatus.OK);
    }
}

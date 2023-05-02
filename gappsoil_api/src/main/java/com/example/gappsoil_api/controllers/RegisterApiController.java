package com.example.gappsoil_api.controllers;

import com.example.gappsoil_api.repositories.UsuarioRepository;
import com.example.gappsoil_api.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class RegisterApiController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    UsuarioService usuarioService = new UsuarioService(usuarioRepository);


    @PostMapping("/registration")
    public ResponseEntity registerNewUser(@RequestParam("email") String email,
                                          @RequestParam("password") String password,
                                          @RequestParam("username") String username){





            if (email.isEmpty() || password.isEmpty() || username.isEmpty()){
                return new ResponseEntity<>("completa tots els camps", HttpStatus.BAD_REQUEST);
            }

            if (usuarioService.existsByUsername(username)){
                return new ResponseEntity<>("Ja existeix un usuari amb aquest nom", HttpStatus.BAD_REQUEST);

            }

            if (usuarioService.existsByEmail(email)){
                return new ResponseEntity<>("Ja existeix un usuari registrat amb aquest email", HttpStatus.BAD_REQUEST);

            }

            String hashed_password = BCrypt.hashpw(password, BCrypt.gensalt());

            //rigistrar usuari
            int result = usuarioService.registerNewUserServiceMethod(email,hashed_password,username);

            if(result!=1){
                return new ResponseEntity<>("not succed",HttpStatus.BAD_REQUEST);

            }

            return new ResponseEntity<>("success",HttpStatus.OK);

    }





}

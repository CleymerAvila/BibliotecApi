package com.unicolombo.bibliotecApi.controller;

import com.unicolombo.bibliotecApi.dto.auth.JwtTokenDto;
import com.unicolombo.bibliotecApi.dto.auth.LoginDto;
import com.unicolombo.bibliotecApi.dto.auth.RegistroDto;
import com.unicolombo.bibliotecApi.service.AutenticacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class AutenticacionController {

    @Autowired
    private AutenticacionService autenticacionService;

    @PostMapping("/login")
    public ResponseEntity<JwtTokenDto> login(@RequestBody LoginDto datos){
        return ResponseEntity.ok(autenticacionService.autenticacionLogin(datos));
    }

    @PostMapping("/registro")
    public ResponseEntity<String> registro(@RequestBody RegistroDto datos){
        autenticacionService.registrarUsuario(datos);
        return ResponseEntity.ok("Usuario registrado con exito!");
    }


}

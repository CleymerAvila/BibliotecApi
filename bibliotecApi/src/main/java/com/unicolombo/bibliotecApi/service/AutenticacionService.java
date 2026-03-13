package com.unicolombo.bibliotecApi.service;

import com.unicolombo.bibliotecApi.domain.model.Usuario;
import com.unicolombo.bibliotecApi.dto.auth.JwtTokenDto;
import com.unicolombo.bibliotecApi.dto.auth.LoginDto;
import com.unicolombo.bibliotecApi.dto.auth.RegistroDto;
import com.unicolombo.bibliotecApi.dto.usuario.UsuarioDto;
import com.unicolombo.bibliotecApi.infrastructure.security.JwtTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AutenticacionService {

    @Autowired
    private AuthenticationManager gestorAutenticacion;

    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    private UsuarioService usuarioService;

    public JwtTokenDto autenticacionLogin(LoginDto datos){
        try {
            Authentication auth = gestorAutenticacion.authenticate(new UsernamePasswordAuthenticationToken(datos.correo(), datos.contrasena()));

            UserDetails ud =(UserDetails) auth.getPrincipal();

            String token = jwtTokenService.generarToken((Usuario) ud);

            return new JwtTokenDto(token);
        } catch (BadCredentialsException ex) {
            throw new BadCredentialsException("Credenciales invalidas");
        }
    }

    public UsuarioDto registrarUsuario(RegistroDto datos){
        return usuarioService.registroUsuario(datos);
    }
}

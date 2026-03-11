package com.unicolombo.bibliotecApi.dto.usuario;

import com.unicolombo.bibliotecApi.domain.model.Usuario;

public record UsuarioDto(long idUsuario, String nombre, String correo, String tipo, String contrasena) {

    public UsuarioDto(Usuario usuario){
        this(usuario.getIdUsuario(), usuario.getNombre(), usuario.getCorreo(), usuario.getTipo(), usuario.getContrasena());
    }
}

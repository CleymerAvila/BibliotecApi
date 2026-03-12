package com.unicolombo.bibliotecApi.dto.usuario;

import com.unicolombo.bibliotecApi.domain.model.TipoUsuario;

public record CrearUsuarioDto(String nombre, String correo, String contrasena, TipoUsuario tipo) {
}

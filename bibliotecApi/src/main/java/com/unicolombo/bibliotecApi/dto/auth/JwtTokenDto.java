package com.unicolombo.bibliotecApi.dto.auth;

import com.unicolombo.bibliotecApi.domain.model.TipoUsuario;

public record JwtTokenDto(
        String token, String tipo, long id, String username, String nombre, String apellido, String email, TipoUsuario rol) {
}

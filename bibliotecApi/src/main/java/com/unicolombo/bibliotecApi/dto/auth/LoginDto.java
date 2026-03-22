package com.unicolombo.bibliotecApi.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginDto(
        @NotBlank(message = "El correo es obligatorio")
        @Email
        String correo,
        @NotBlank(message = "La contraseña es obligaria")
//        @Size(min = 8, max = 12, message = "Debe tener entre 8 y 12 dígitos")
        String contrasena) {
}

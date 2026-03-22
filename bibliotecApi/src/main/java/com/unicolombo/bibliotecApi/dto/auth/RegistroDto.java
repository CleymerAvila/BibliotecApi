package com.unicolombo.bibliotecApi.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RegistroDto(
        @NotBlank(message = "El nombre es obligatorio")
        String nombre,
        @NotBlank(message = "El correo es obligatorio")
        @Email(message = "El correo debe tener un formato valido")
        String correo,
        @Size(min = 8, max = 12, message = "Debe tener entre 8 y 12 digitos")
        String contrasena) {
}

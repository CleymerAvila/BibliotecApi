package com.unicolombo.bibliotecApi.dto.libro;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.Date;

public record CrearLibroDto(
        String titulo,
        String autor,
        String editorial,
        @Pattern(regexp = "^[0-9]+$", message = "Debe contener solo número")
        @Size(min = 10, max = 12, message = "Debe tener entre 10 y 12 dígitos")
        String anoPublicacion,
        String categoria,
        String disponibilidad) {
}

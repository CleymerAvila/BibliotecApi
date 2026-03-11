package com.unicolombo.bibliotecApi.dto.libro;

import java.util.Date;

public record CrearLibroDto(String titulo, String autor, String editorial, String anoPublicacion, String categoria, String disponibilidad) {
}

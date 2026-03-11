package com.unicolombo.bibliotecApi.dto.prestamo;

import java.time.LocalDate;

public record CrearPrestamoDto(LocalDate fechaPrestamo,
                               LocalDate fechaDevolucion,
                               String estado, long idUsuario,
                               long idLibro) {
}

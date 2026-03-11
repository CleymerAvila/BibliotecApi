package com.unicolombo.bibliotecApi.dto.prestamo;

import java.time.LocalDate;

public record ActualizarPrestamoDto(LocalDate fechaPrestamo,
                                    LocalDate fechaDevolucion, String estado) {
}

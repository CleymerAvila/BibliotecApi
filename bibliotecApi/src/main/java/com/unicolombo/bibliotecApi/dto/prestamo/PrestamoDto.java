package com.unicolombo.bibliotecApi.dto.prestamo;

import com.unicolombo.bibliotecApi.domain.model.Prestamo;

import java.time.LocalDate;

public record PrestamoDto(long idPrestamo, LocalDate fechaPrestamo
                            , LocalDate fechaDevolucion, String estado,
                          long idUsuario, long idLibro ) {

    public PrestamoDto(Prestamo prestamo){
        this(prestamo.getIdPrestamo(), prestamo.getFechaPrestamo(),
                prestamo.getFechaDevolucion(), prestamo.getEstado(),
                prestamo.getUsuario().getIdUsuario(),
                prestamo.getLibro().getIdLibro());
    }
}

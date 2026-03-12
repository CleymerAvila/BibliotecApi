package com.unicolombo.bibliotecApi.dto.reserva;

import com.unicolombo.bibliotecApi.domain.model.Reserva;

import java.time.LocalDate;

public record ReservaDto(long idReserva, LocalDate fechaInicio, LocalDate fechaFin, String estado, long idUsuario, long idLibro) {

    public ReservaDto(Reserva reserva){
        this(reserva.getIdReserva(), reserva.getFechaInicio(),
                reserva.getFechaFin(), reserva.getEstado(),
                reserva.getUsuario().getIdUsuario(),
                reserva.getLibro().getIdLibro());
    }
}

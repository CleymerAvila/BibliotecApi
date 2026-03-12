package com.unicolombo.bibliotecApi.dto.reserva;

import java.time.LocalDate;

public record CrearReservaDto(LocalDate fechaInicio, LocalDate fechaFin, String estado, long idUsuario, long idLibro) {
}

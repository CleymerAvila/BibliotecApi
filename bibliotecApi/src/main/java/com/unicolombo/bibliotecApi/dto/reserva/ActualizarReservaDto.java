package com.unicolombo.bibliotecApi.dto.reserva;

import java.time.LocalDate;

public record ActualizarReservaDto(LocalDate fechaInicio, LocalDate fechaFin, String estado) {
}

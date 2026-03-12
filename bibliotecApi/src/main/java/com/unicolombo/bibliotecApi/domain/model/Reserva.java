package com.unicolombo.bibliotecApi.domain.model;

import com.unicolombo.bibliotecApi.dto.reserva.ActualizarReservaDto;
import com.unicolombo.bibliotecApi.dto.reserva.CrearReservaDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "reservas")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "idReserva")
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idReserva;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String estado;
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
    @ManyToOne
    @JoinColumn(name = "id_libro")
    private Libro libro;

    public Reserva(LocalDate fechaInicio, LocalDate fechaFin, String estado, Usuario usuario, Libro libro){
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estado = estado;
        this.usuario = usuario;
        this.libro = libro;
    }

    public void actualizar(ActualizarReservaDto datos){
        if (datos.fechaInicio() != null && !datos.fechaInicio().equals(this.fechaInicio)){
            this.fechaInicio = datos.fechaInicio();
        }

        if(datos.fechaFin() != null && !datos.fechaFin().equals(this.fechaFin)){
            this.fechaFin = datos.fechaFin();
        }

        if(!datos.estado().isBlank() && !datos.estado().equals(this.estado)){
            this.estado = datos.estado();
        }
    }
}

package com.unicolombo.bibliotecApi.domain.model;


import com.unicolombo.bibliotecApi.dto.prestamo.ActualizarPrestamoDto;
import com.unicolombo.bibliotecApi.dto.prestamo.CrearPrestamoDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "prestamos")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "idPrestamo")
public class Prestamo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idPrestamo;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;
    private String estado;
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
    @ManyToOne
    @JoinColumn(name = "id_libro")
    private Libro libro;

    public Prestamo(LocalDate fechaPrestamo, LocalDate fechaDevolucion, String estado, Usuario usuario, Libro libro){
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.estado = estado;
        this.usuario = usuario;
        this.libro = libro;
    }

    public void actualizar(ActualizarPrestamoDto datos) {
        if(datos.fechaPrestamo() != null && !datos.fechaPrestamo().equals(this.fechaPrestamo)){
            this.fechaPrestamo = datos.fechaPrestamo();
        }

        if(datos.fechaDevolucion() != null && !datos.fechaDevolucion().equals(this.fechaDevolucion)){
            this.fechaDevolucion = datos.fechaDevolucion();
        }

        if(!datos.estado().isBlank() && !datos.estado().equals(this.estado)){
            this.estado = datos.estado();
        }
    }
}

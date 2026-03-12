package com.unicolombo.bibliotecApi.domain.model;

import com.unicolombo.bibliotecApi.dto.libro.ActualizarLibroDto;
import com.unicolombo.bibliotecApi.dto.libro.CrearLibroDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Table(name = "libros")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "idLibro")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idLibro;
    private String titulo;
    private String autor;
    private String editorial;
    private String anoPublicacion;
    private String categoria;
    private String disponibilidad;
    @OneToMany(mappedBy = "libro")
    private List<Prestamo> prestamos;
    @OneToMany(mappedBy = "libro")
    private List<Reserva> reservas;


    public Libro(CrearLibroDto datos){
        this.titulo = datos.titulo();
        this.autor = datos.autor();
        this.editorial = datos.editorial();
        this.anoPublicacion = datos.anoPublicacion();
        this.categoria =  datos.categoria();
        this.disponibilidad  = datos.disponibilidad();
    }

    public void actualizar(ActualizarLibroDto datos){
        if(!datos.titulo().equals(this.titulo) && !datos.titulo().isBlank()){
            this.titulo  = datos.titulo();
        }

        if(!datos.autor().equals(this.autor) && !datos.autor().isBlank()){
            this.autor = datos.autor();
        }

        if(!datos.editorial().equals(this.editorial) && !datos.editorial().isBlank()){
            this.editorial = datos.editorial();
        }

        if(!datos.categoria().equals(this.categoria) && !datos.categoria().isBlank()){
            this.categoria = datos.categoria();
        }

        if(!datos.disponibilidad().equals(this.disponibilidad) && !datos.disponibilidad().isBlank()){
            this.disponibilidad = datos.disponibilidad();
        }
    }

}

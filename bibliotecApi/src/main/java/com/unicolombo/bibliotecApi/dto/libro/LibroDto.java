package com.unicolombo.bibliotecApi.dto.libro;

import com.unicolombo.bibliotecApi.domain.model.Libro;


public record LibroDto(Long idLibro, String titulo, String autor, String editorial, String anoPublicacion, String categoria, String disponibilidad) {

    public LibroDto(Libro libro){
        this(libro.getIdLibro(), libro.getTitulo(),
                libro.getAutor(), libro.getEditorial(),
                libro.getAnoPublicacion(), libro.getCategoria(),
                libro.getDisponibilidad());
    }
}

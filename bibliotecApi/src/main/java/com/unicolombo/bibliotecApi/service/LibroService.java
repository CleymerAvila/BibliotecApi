package com.unicolombo.bibliotecApi.service;

import com.unicolombo.bibliotecApi.domain.model.Libro;
import com.unicolombo.bibliotecApi.domain.repository.LibroRepository;
import com.unicolombo.bibliotecApi.dto.libro.ActualizarLibroDto;
import com.unicolombo.bibliotecApi.dto.libro.CrearLibroDto;
import com.unicolombo.bibliotecApi.dto.libro.LibroDto;
import com.unicolombo.bibliotecApi.infrastructure.errors.exceptions.ValidacionDeLogicaDeNegocioException;
import jakarta.persistence.EntityNotFoundException;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class LibroService {

    @Autowired
    private LibroRepository libroRepository;


    public Page<LibroDto> obtenerLibros(Pageable pageable){
        return libroRepository.findAll(pageable).map(LibroDto::new);
    }
    public LibroDto crearLibro(CrearLibroDto datos){
        Libro libroGuardado = libroRepository.save(new Libro(datos));
        return new LibroDto(libroRepository.save(libroGuardado));
    }

    public LibroDto actualizarLibro(long idLibro, ActualizarLibroDto datos){
        Libro libroEncontrado = libroRepository.getReferenceById(idLibro);

        libroEncontrado.actualizar(datos);
        return new LibroDto(libroRepository.save(libroEncontrado));
    }

    public void eliminarLibro(long idLibro){
        libroRepository.deleteById(idLibro);
    }

    public LibroDto obtenerLibroId(long idLibro) {
        Libro libro = this.libroRepository.findById(idLibro)
                .orElseThrow(() -> new EntityNotFoundException("El libro con el id ingresado no se encuentra"));

        return new LibroDto(libro);
    }
}

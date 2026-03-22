package com.unicolombo.bibliotecApi.controller;

import com.unicolombo.bibliotecApi.dto.libro.ActualizarLibroDto;
import com.unicolombo.bibliotecApi.dto.libro.CrearLibroDto;
import com.unicolombo.bibliotecApi.dto.libro.LibroDto;
import com.unicolombo.bibliotecApi.service.LibroService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/libros")
public class LibroController {

    @Autowired
    private LibroService libroService;

    @GetMapping
    public ResponseEntity<Page<LibroDto>> obtenerLibros(Pageable pageable){
        Page<LibroDto> libros = libroService.obtenerLibros(pageable);
        return ResponseEntity.ok(libros);
    }

    @GetMapping("{idLibro}")
    public ResponseEntity<LibroDto> obtenerLibroPorId(@PathVariable long idLibro){
        return ResponseEntity.ok(libroService.obtenerLibroId(idLibro));
    }

    @PostMapping
    public ResponseEntity<LibroDto> crearLibro(@Valid @RequestBody CrearLibroDto datos, UriComponentsBuilder uriCompBuilder){
        LibroDto libro = libroService.crearLibro(datos);

        URI url = uriCompBuilder.path("/libros/{idLibro}").buildAndExpand(libro.idLibro()).toUri();

        return ResponseEntity.created(url).body(libro);
    }

    @PutMapping("{idLibro}")
    @Transactional
    public ResponseEntity<LibroDto> actualizarLibro(@PathVariable long idLibro,@Valid @RequestBody ActualizarLibroDto datos){
        return ResponseEntity.ok(libroService.actualizarLibro(idLibro, datos));
    }

    @DeleteMapping("{idLibro}")
    public ResponseEntity<LibroDto> eliminarLibro(@PathVariable long idLibro ){
        libroService.eliminarLibro(idLibro);
        return ResponseEntity.noContent().build();
    }
}

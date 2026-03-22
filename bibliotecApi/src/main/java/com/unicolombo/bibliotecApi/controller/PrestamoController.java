package com.unicolombo.bibliotecApi.controller;

import com.unicolombo.bibliotecApi.dto.prestamo.ActualizarPrestamoDto;
import com.unicolombo.bibliotecApi.dto.prestamo.CrearPrestamoDto;
import com.unicolombo.bibliotecApi.dto.prestamo.PrestamoDto;
import com.unicolombo.bibliotecApi.service.PrestamoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/prestamos")
public class PrestamoController {

    @Autowired
    private PrestamoService prestamoService;


    @GetMapping
    public ResponseEntity<List<PrestamoDto>> obtenerTodosPrestamos(){
        return ResponseEntity.ok(prestamoService.obtenerTodosLosPrestamos());
    }

    @PostMapping
    public ResponseEntity<PrestamoDto> crearPrestamo(@Valid @RequestBody CrearPrestamoDto datos, UriComponentsBuilder uriCompBuilder){

        PrestamoDto prestamoDto = prestamoService.crearPrestamo(datos);
        URI url = uriCompBuilder.path("/prestamos/{idPrestamo}").buildAndExpand(prestamoDto.idPrestamo()).toUri();

        return ResponseEntity.created(url).body(prestamoDto);
    }

    @PutMapping("{idPrestamo}")
    @Transactional
    public ResponseEntity<PrestamoDto> actualizarPrestamo(@PathVariable long idPrestamo,@Valid @RequestBody ActualizarPrestamoDto datos){
        PrestamoDto prestamoDto = prestamoService.actualizarPrestamo(idPrestamo, datos);
        return ResponseEntity.ok(prestamoDto);
    }

    @GetMapping("{idPrestamo}")
    public ResponseEntity<PrestamoDto> obtenerPorId(@PathVariable long idPrestamo){
        return ResponseEntity.ok(prestamoService.obtenerPrestamoId(idPrestamo));
    }

    @DeleteMapping("{idPrestamo}")
    public ResponseEntity<Void> eliminarPrestamo(@PathVariable long idPrestamo){
        prestamoService.eliminarPrestamo(idPrestamo);
        return ResponseEntity.noContent().build();
    }
}

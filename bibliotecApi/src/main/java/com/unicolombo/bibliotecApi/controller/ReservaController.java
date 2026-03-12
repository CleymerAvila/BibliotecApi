package com.unicolombo.bibliotecApi.controller;


import com.unicolombo.bibliotecApi.dto.reserva.ActualizarReservaDto;
import com.unicolombo.bibliotecApi.dto.reserva.CrearReservaDto;
import com.unicolombo.bibliotecApi.dto.reserva.ReservaDto;
import com.unicolombo.bibliotecApi.service.ReservaService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @GetMapping
    public ResponseEntity<List<ReservaDto>> obtenerTodosLasReservas(){
        return ResponseEntity.ok(reservaService.obtenerReservas());
    }

    @PostMapping
    public ResponseEntity<ReservaDto> crearReserva(@RequestBody CrearReservaDto datos, UriComponentsBuilder uriCompBuilder){

        ReservaDto reservaDto = reservaService.crearReserva(datos);
        URI url = uriCompBuilder.path("/reservas/{idReserva}").buildAndExpand(reservaDto.idReserva()).toUri();

        return ResponseEntity.created(url).body(reservaDto);
    }

    @PutMapping("{idReserva}")
    @Transactional
    public ResponseEntity<ReservaDto> actualizarReserva(@PathVariable long idReserva, @RequestBody ActualizarReservaDto datos){
        ReservaDto reservaDto = reservaService.actualizarReserva(idReserva, datos);
        return ResponseEntity.ok(reservaDto);
    }

    @GetMapping("{idReserva}")
    public ResponseEntity<ReservaDto> obtenerPorId(@PathVariable long idReserva){
        return ResponseEntity.ok(reservaService.obtenerReservaId(idReserva));
    }

    @DeleteMapping("{idReserva}")
    public ResponseEntity<Void> eliminarReserva(@PathVariable long idReserva){
        reservaService.eliminarReserva(idReserva);
        return ResponseEntity.noContent().build();
    }
}

package com.unicolombo.bibliotecApi.service;

import com.unicolombo.bibliotecApi.domain.model.Libro;
import com.unicolombo.bibliotecApi.domain.model.Reserva;
import com.unicolombo.bibliotecApi.domain.model.Usuario;
import com.unicolombo.bibliotecApi.domain.repository.LibroRepository;
import com.unicolombo.bibliotecApi.domain.repository.ReservaRepository;
import com.unicolombo.bibliotecApi.domain.repository.UsuarioRepository;
import com.unicolombo.bibliotecApi.dto.reserva.ActualizarReservaDto;
import com.unicolombo.bibliotecApi.dto.reserva.CrearReservaDto;
import com.unicolombo.bibliotecApi.dto.reserva.ReservaDto;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private LibroRepository libroRepository;

    public ReservaDto obtenerReservaId(long idReserva){
        Reserva reserva = reservaRepository.findById(idReserva)
                .orElseThrow(() -> new EntityNotFoundException("La reserva con el id ingresado no ha sido encontrada"));

        return new ReservaDto(reserva);
    }

    public List<ReservaDto> obtenerReservas(){
        return reservaRepository.findAll().stream().map(ReservaDto::new).toList();
    }

    public ReservaDto crearReserva(CrearReservaDto datos){
        Usuario usuario = usuarioRepository.findById(datos.idUsuario())
                .orElseThrow(() -> new EntityNotFoundException("El usuario con el id ingreso no existe"));

        Libro libro = libroRepository.findById(datos.idLibro()).orElseThrow(() -> new EntityNotFoundException("El libro con el id ingresado no existe"));

        Reserva reserva = new Reserva(datos.fechaInicio(), datos.fechaFin(), datos.estado(), usuario, libro);

        return new ReservaDto(reservaRepository.save(reserva));
    }

    public ReservaDto actualizarReserva(long idReserva, ActualizarReservaDto datos){
        Reserva reserva = reservaRepository.findById(idReserva)
                .orElseThrow(() -> new EntityNotFoundException("La reserva con el id ingresado no existe"));

        reserva.actualizar(datos);

        return new ReservaDto(reservaRepository.save(reserva));
    }

    public void eliminarReserva(long idReserva){
        reservaRepository.deleteById(idReserva);
    }
}

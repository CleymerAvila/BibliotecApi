package com.unicolombo.bibliotecApi.service;

import com.unicolombo.bibliotecApi.domain.model.Libro;
import com.unicolombo.bibliotecApi.domain.model.Prestamo;
import com.unicolombo.bibliotecApi.domain.model.Usuario;
import com.unicolombo.bibliotecApi.domain.repository.LibroRepository;
import com.unicolombo.bibliotecApi.domain.repository.PrestamoRepository;
import com.unicolombo.bibliotecApi.domain.repository.UsuarioRepository;
import com.unicolombo.bibliotecApi.dto.prestamo.ActualizarPrestamoDto;
import com.unicolombo.bibliotecApi.dto.prestamo.CrearPrestamoDto;
import com.unicolombo.bibliotecApi.dto.prestamo.PrestamoDto;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrestamoService {

    @Autowired
    private PrestamoRepository prestamoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private LibroRepository libroRepository;

    public PrestamoDto crearPrestamo(CrearPrestamoDto datos){

        Usuario usuario = usuarioRepository.findById(datos.idUsuario())
                .orElseThrow(() -> new EntityNotFoundException("El usuario ingresado no fue encontrado"));

        Libro libro = libroRepository.findById(datos.idLibro())
                .orElseThrow(() -> new EntityNotFoundException("El libro ingresado no fue encontrado"));

        Prestamo nuevoPrestamo = new Prestamo(datos.fechaPrestamo(), datos.fechaDevolucion(), datos.estado(), usuario, libro);
        return new PrestamoDto(prestamoRepository.save(nuevoPrestamo));
    }


    public List<PrestamoDto> obtenerTodosLosPrestamos(){
        return prestamoRepository.findAll().stream().map(PrestamoDto::new).toList();
    }


    public PrestamoDto actualizarPrestamo(long idPrestamo, ActualizarPrestamoDto datos){
        Prestamo prestamo = prestamoRepository.findById(idPrestamo)
                .orElseThrow( () -> new EntityNotFoundException("Prestamo no encontrado para actualizar"));

        prestamo.actualizar(datos);

        return new PrestamoDto(prestamo);
    }

    public void eliminarPrestamo(long idPrestamo){
        prestamoRepository.deleteById(idPrestamo);
    }

    public PrestamoDto obtenerPrestamoId(long idPrestamo) {
        Prestamo prestamo = prestamoRepository.findById(idPrestamo)
                .orElseThrow(() -> new EntityNotFoundException("El prestamo con el id ingresado no se encuentra en la base de datos"));

        return new PrestamoDto(prestamo);
    }
}

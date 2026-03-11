package com.unicolombo.bibliotecApi.controller;


import com.unicolombo.bibliotecApi.dto.usuario.ActualizarUsuarioDto;
import com.unicolombo.bibliotecApi.dto.usuario.CrearUsuarioDto;
import com.unicolombo.bibliotecApi.dto.usuario.UsuarioDto;
import com.unicolombo.bibliotecApi.service.UsuarioService;
import jakarta.transaction.Transactional;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioDto> crearUsuario(@RequestBody CrearUsuarioDto datos, UriComponentsBuilder uriCompBuilder){
        UsuarioDto usuarioDto = usuarioService.crearUsuario(datos);

        URI url = uriCompBuilder.path("/usuarios/{idUsuario}")
                .buildAndExpand(usuarioDto.idUsuario()).toUri();

        return ResponseEntity.created(url).body(usuarioDto);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDto>> obtenerUsuarios(){
        return ResponseEntity.ok(usuarioService.obtenerUsuarios());
    }

    @GetMapping("{idUsuario}")
    public ResponseEntity<UsuarioDto> obtenerUsuarioPorId(@PathVariable long idUsuario){
        return ResponseEntity.ok(usuarioService.obtenerUsuarioId(idUsuario));
    }

    @PutMapping("{idUsuario}")
    @Transactional
    public ResponseEntity<UsuarioDto> actualizarUsuario(
            @PathVariable long idUsuario,
            @RequestBody ActualizarUsuarioDto datos
    ) throws BadRequestException {
        return ResponseEntity.ok(usuarioService.actualizarUsuario(idUsuario, datos));
    }

    @DeleteMapping("{idUsuario}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable long idUsuario) throws BadRequestException {
        usuarioService.eliminarUsuario(idUsuario);
        return ResponseEntity.noContent().build();
    }
}

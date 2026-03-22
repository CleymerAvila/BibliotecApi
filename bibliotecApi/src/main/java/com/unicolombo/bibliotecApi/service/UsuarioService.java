package com.unicolombo.bibliotecApi.service;

import com.unicolombo.bibliotecApi.domain.model.Usuario;
import com.unicolombo.bibliotecApi.domain.repository.UsuarioRepository;
import com.unicolombo.bibliotecApi.dto.auth.RegistroDto;
import com.unicolombo.bibliotecApi.dto.usuario.ActualizarUsuarioDto;
import com.unicolombo.bibliotecApi.dto.usuario.CrearUsuarioDto;
import com.unicolombo.bibliotecApi.dto.usuario.UsuarioDto;
import com.unicolombo.bibliotecApi.infrastructure.errors.exceptions.ValidacionDeLogicaDeNegocioException;
import jakarta.persistence.EntityNotFoundException;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public UsuarioDto crearUsuario(CrearUsuarioDto datos){
        Usuario usuario = new Usuario(datos);
        // cambiar la contraseña a encriptada
        String contrasenaEncriptada = passwordEncoder.encode(datos.contrasena());
        usuario.setContrasena(contrasenaEncriptada);

        // cambiar correo dependiendo del tipo
        if(datos.tipo().name().equals("ROLE_ADMIN")){
            String nuevoCorreo = formatEmail(datos.correo(), "admin");
            usuario.setCorreo(nuevoCorreo);
        } else if(datos.tipo().name().equals("ROLE_DOCENTE")){
            String nuevoCorreo = formatEmail(datos.correo(), "docente");
            usuario.setCorreo(nuevoCorreo);
        }
        Usuario usuarioGuardado = usuarioRepository.save(usuario);
        return new UsuarioDto(usuarioGuardado);
    }

    public UsuarioDto registroUsuario(RegistroDto datos){
        Usuario usuario = new Usuario(datos);
        // cambiar la contraseña a encriptada
        String contrasenaEncriptada = passwordEncoder.encode(datos.contrasena());
        usuario.setContrasena(contrasenaEncriptada);

        Usuario usuarioGuardado = usuarioRepository.save(usuario);
        return new UsuarioDto(usuarioGuardado);
    }

    public List<UsuarioDto> obtenerUsuarios(){
        return usuarioRepository.findAll().stream()
                .map(UsuarioDto::new).toList();
    }

    public void eliminarUsuario(long idUsuario) throws BadRequestException {
        usuarioRepository.deleteById(idUsuario);
    }

    public UsuarioDto actualizarUsuario(long idUsuario, ActualizarUsuarioDto datos) throws BadRequestException {
        Usuario usuarioEncontrado = usuarioRepository.getReferenceById(idUsuario);
        usuarioEncontrado.actualizarDatos(datos);

        return new UsuarioDto(usuarioRepository.save(usuarioEncontrado));
    }

    public UsuarioDto obtenerUsuarioId(long idUsuario) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new EntityNotFoundException("El usuario con el id ingresado no existe"));

        return new UsuarioDto(usuario);
    }



    private String formatEmail(String email, String userType) {

        if(userType.equalsIgnoreCase("estudiante")) {
            return email; // keep original
        }
        return email.replaceAll("(?=@)", "." + userType);
    }
}

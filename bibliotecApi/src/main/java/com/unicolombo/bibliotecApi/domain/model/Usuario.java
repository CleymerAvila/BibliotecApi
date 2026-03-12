package com.unicolombo.bibliotecApi.domain.model;

import com.unicolombo.bibliotecApi.dto.usuario.ActualizarUsuarioDto;
import com.unicolombo.bibliotecApi.dto.usuario.CrearUsuarioDto;
import jakarta.persistence.*;
import lombok.*;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "usuarios")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "idUsuario")
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idUsuario;
    private String nombre;
    private String correo;
    @Enumerated(EnumType.STRING)
    private TipoUsuario tipo;
    private String contrasena;
    @OneToMany(mappedBy = "usuario")
    private List<Prestamo> prestamos;
    @OneToMany(mappedBy = "usuario")
    private List<Reserva> reservas;

    public Usuario(CrearUsuarioDto datos){
        this.nombre = datos.nombre();
        this.correo = datos.correo();
        this.tipo = datos.tipo();
        this.contrasena = datos.contrasena();
    }

    public void actualizarDatos(ActualizarUsuarioDto datos){
        if(!datos.nombre().equals(this.nombre) && !datos.nombre().isBlank()){
            this.nombre = datos.nombre();
        }

        if(!datos.correo().equals(this.correo) && !datos.correo().isBlank()){
            this.correo = datos.correo();
        }

        if(!datos.contrasena().equals(this.contrasena) && !datos.contrasena().isBlank()){
            this.contrasena = datos.contrasena();
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(this.tipo.name()));
    }

    @Override
    public String getPassword() {
        return contrasena;
    }

    @Override
    public String getUsername() {
        return correo;
    }
}

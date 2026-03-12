package com.unicolombo.bibliotecApi.domain.model;

import com.unicolombo.bibliotecApi.dto.usuario.ActualizarUsuarioDto;
import com.unicolombo.bibliotecApi.dto.usuario.CrearUsuarioDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "usuarios")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "idUsuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idUsuario;
    private String nombre;
    private String correo;
    private String tipo;
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
}

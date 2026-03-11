package com.unicolombo.bibliotecApi.domain.repository;

import com.unicolombo.bibliotecApi.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}

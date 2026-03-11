package com.unicolombo.bibliotecApi.domain.repository;

import com.unicolombo.bibliotecApi.domain.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroRepository extends JpaRepository<Libro, Long> {
}

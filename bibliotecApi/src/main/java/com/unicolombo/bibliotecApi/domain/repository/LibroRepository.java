package com.unicolombo.bibliotecApi.domain.repository;

import com.unicolombo.bibliotecApi.domain.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {
}

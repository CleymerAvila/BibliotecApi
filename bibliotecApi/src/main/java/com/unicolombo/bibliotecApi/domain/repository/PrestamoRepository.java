package com.unicolombo.bibliotecApi.domain.repository;

import com.unicolombo.bibliotecApi.domain.model.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {

}

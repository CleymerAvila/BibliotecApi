package com.unicolombo.bibliotecApi.domain.repository;

import com.unicolombo.bibliotecApi.domain.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {
}

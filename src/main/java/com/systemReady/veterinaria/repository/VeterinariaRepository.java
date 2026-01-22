package com.systemReady.veterinaria.repository;

import com.systemReady.veterinaria.Domain.veterinaria.Veterinaria;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VeterinariaRepository extends JpaRepository<Veterinaria, Long> {
    Page<Veterinaria> findByActivo(Boolean activo, Pageable pageable);
}

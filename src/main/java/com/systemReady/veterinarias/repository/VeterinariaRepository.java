package com.systemReady.veterinarias.repository;

import com.systemReady.veterinarias.Domain.veterinaria.Veterinaria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VeterinariaRepository extends JpaRepository<Veterinaria, Long> {
}

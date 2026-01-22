package com.systemReady.veterinaria.repository;

import com.systemReady.veterinaria.Domain.veterinaria.Veterinaria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VeterinariaRepository extends JpaRepository<Veterinaria, Long> {
}

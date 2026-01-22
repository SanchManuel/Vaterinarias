package com.systemReady.veterinaria.repository;

import com.systemReady.veterinaria.Domain.mascota.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MascotaRepository extends JpaRepository<Mascota, Long> {
}

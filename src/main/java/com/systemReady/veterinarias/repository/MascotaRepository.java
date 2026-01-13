package com.systemReady.veterinarias.repository;

import com.systemReady.veterinarias.Domain.mascota.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MascotaRepository extends JpaRepository<Mascota, Long> {
}

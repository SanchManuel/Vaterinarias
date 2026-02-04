package com.systemReady.veterinaria.repository;

import com.systemReady.veterinaria.Domain.rol.Rol;
import com.systemReady.veterinaria.Domain.veterinaria.Veterinaria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepository extends JpaRepository<Rol, Long> {

    Page<Rol> findByVeterinariaId(Long veterinariaId, Pageable pageable);
    Page<Rol> findByVeterinariaIdAndActivo(Long veterinaria_id, Boolean activo, Pageable pageable);
}

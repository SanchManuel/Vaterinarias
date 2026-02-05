package com.systemReady.veterinaria.repository;

import com.systemReady.veterinaria.Domain.Usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}

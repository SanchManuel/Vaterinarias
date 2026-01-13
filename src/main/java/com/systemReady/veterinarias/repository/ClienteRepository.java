package com.systemReady.veterinarias.repository;

import com.systemReady.veterinarias.Domain.cliente.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository <Cliente, Long>{
}

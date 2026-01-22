package com.systemReady.veterinaria.dto.veterinaria;


import java.time.OffsetDateTime;

public record responseDTO(
        Long id,
        String nombre,
        String telefono,
        String email,
        Boolean activo,
        OffsetDateTime createdAt
){}
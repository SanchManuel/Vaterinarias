package com.systemReady.veterinaria.dto.rol;

import java.time.OffsetDateTime;

public record RoleResponse(
        Long id,
        String nombre,
        String description,
        Boolean activo,
        OffsetDateTime createdAt
) {
}

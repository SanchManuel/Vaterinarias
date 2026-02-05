package com.systemReady.veterinaria.dto.Usuario;

import java.time.OffsetDateTime;

public record UsuarioResponse(
        Long id,
        String nombre,
        String email,
        Boolean activo,
        OffsetDateTime createdAt
) {
}

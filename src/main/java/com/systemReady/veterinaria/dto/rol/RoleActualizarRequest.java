package com.systemReady.veterinaria.dto.rol;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RoleActualizarRequest(
        @NotBlank @Size(max = 50) String nombre,
        @NotBlank @Valid @Size(max = 100) String descripcion
) {
}

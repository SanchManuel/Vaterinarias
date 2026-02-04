package com.systemReady.veterinaria.dto.rol;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RoleCreateRequest(
        @NotBlank @Size(max = 50) String nombre,
        @NotBlank @Valid @Size(max = 100) String descripcion,
        @NotNull Long veterinaria_id
) { }

package com.systemReady.veterinaria.dto.veterinaria;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateRequest(
        @NotBlank @Size(max = 150) String nombre,
        @NotBlank @Size(max = 10) String telefono,
        @NotBlank @Email @Size(max = 200) String email,
        @NotBlank @Size(max = 500) String direccion
) {}


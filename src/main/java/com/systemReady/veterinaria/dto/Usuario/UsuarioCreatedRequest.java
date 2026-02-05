package com.systemReady.veterinaria.dto.Usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioCreatedRequest(
        @NotBlank @Size(max = 100) String nombre,
        @NotBlank @Size(max = 200) @Email String email,
        @NotBlank @Size(min = 10, max = 200) String password
) {
}

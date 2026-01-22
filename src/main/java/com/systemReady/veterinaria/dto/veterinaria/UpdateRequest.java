package com.systemReady.veterinaria.dto.veterinaria;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record UpdateRequest(
        @Size(max = 150) String nombre,
        @Size(max = 10) String telefono,
        @Email @Size(max = 200) String email,
        String direccion,
        Boolean activa
) { }


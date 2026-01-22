package com.systemReady.veterinaria.Domain.veterinaria;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@Entity
@Table(name = "veterinarias")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Veterinaria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String nombre;

    @Column(length = 50)
    private String telefono;

    @Column(columnDefinition = "text")
    private String direccion;

    @Column(nullable = false)
    private Boolean activo = true;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt = OffsetDateTime.now();
}

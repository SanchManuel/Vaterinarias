package com.systemReady.veterinaria.Domain.veterinaria;

import jakarta.persistence.*;
import lombok.*;

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
}

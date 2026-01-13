package com.systemReady.veterinarias.Domain.mascota;

import com.systemReady.veterinarias.Domain.cliente.Cliente;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "mascotas")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Mascota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @Column(nullable = false, length = 150)
    private String nombre;

    @Column(nullable = false, length = 50)
    private String especie;

    @Column(length = 80)
    private String raza;

    private LocalDate fechaNacimiento;

}

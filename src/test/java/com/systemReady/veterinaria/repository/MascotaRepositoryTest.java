package com.systemReady.veterinaria.repository;

import com.systemReady.veterinaria.Domain.cliente.Cliente;
import com.systemReady.veterinaria.Domain.mascota.Mascota;
import com.systemReady.veterinaria.Domain.veterinaria.Veterinaria;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class MascotaRepositoryTest {

    @Autowired
    private MascotaRepository mascotaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private VeterinariaRepository veterinariaRepository;

    @Test
    @DisplayName("MascotaRepository guarda y recupera Mascota correctamente")
    void saveAndFindMascota() {
        // given: una Veterinaria y un Cliente requeridos por la entidad Mascota
        Veterinaria veterinaria = Veterinaria.builder()
                .nombre("Veterinaria Sur")
                .telefono("987654321")
                .direccion("Avenida Siempre Viva 742")
                .build();
        veterinaria = veterinariaRepository.save(veterinaria);

        Cliente cliente = Cliente.builder()
                .veterinaria(veterinaria)
                .nombre("María López")
                .telefono("555-1111")
                .email("maria.lopez@example.com")
                .build();
        cliente = clienteRepository.save(cliente);

        LocalDate fechaNacimiento = LocalDate.of(2020, 5, 15);

        Mascota mascota = Mascota.builder()
                .cliente(cliente)
                .nombre("Firulais")
                .especie("Perro")
                .raza("Labrador")
                .fechaNacimiento(fechaNacimiento)
                .build();

        // when: se guarda la mascota
        Mascota saved = mascotaRepository.save(mascota);

        // then: se genera un id y se puede recuperar por id y via findAll
        assertThat(saved.getId()).isNotNull();

        Optional<Mascota> foundById = mascotaRepository.findById(saved.getId());
        assertThat(foundById)
                .isPresent()
                .get()
                .extracting(Mascota::getNombre, Mascota::getEspecie, Mascota::getRaza, Mascota::getFechaNacimiento)
                .containsExactly("Firulais", "Perro", "Labrador", fechaNacimiento);

        List<Mascota> all = mascotaRepository.findAll();
        assertThat(all)
                .hasSize(1)
                .first()
                .extracting(Mascota::getNombre)
                .isEqualTo("Firulais");
    }
}

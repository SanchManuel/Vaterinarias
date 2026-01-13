package com.systemReady.veterinarias.repository;

import com.systemReady.veterinarias.Domain.veterinaria.Veterinaria;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class VeterinariaRepositoryTest {

    @Autowired
    private VeterinariaRepository veterinariaRepository;

    @Test
    @DisplayName("VeterinariaRepository guarda y recupera Veterinaria correctamente")
    void saveAndFindVeterinaria() {
        // given: una entidad Veterinaria
        Veterinaria veterinaria = Veterinaria.builder()
                .nombre("Clínica Norte")
                .telefono("444-2222")
                .direccion("Boulevard Principal 99")
                .build();

        // when: se guarda la veterinaria
        Veterinaria saved = veterinariaRepository.save(veterinaria);

        // then: se genera un id y se puede recuperar por id y via findAll
        assertThat(saved.getId()).isNotNull();

        Optional<Veterinaria> foundById = veterinariaRepository.findById(saved.getId());
        assertThat(foundById)
                .isPresent()
                .get()
                .extracting(Veterinaria::getNombre, Veterinaria::getTelefono, Veterinaria::getDireccion)
                .containsExactly("Clínica Norte", "444-2222", "Boulevard Principal 99");

        List<Veterinaria> all = veterinariaRepository.findAll();
        assertThat(all)
                .hasSize(1)
                .first()
                .extracting(Veterinaria::getNombre)
                .isEqualTo("Clínica Norte");
    }
}

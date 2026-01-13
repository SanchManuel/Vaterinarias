package com.systemReady.veterinarias.repository;

import com.systemReady.veterinarias.Domain.cliente.Cliente;
import com.systemReady.veterinarias.Domain.veterinaria.Veterinaria;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ClienteRepositoryTest {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private VeterinariaRepository veterinariaRepository;

    @Test
    @DisplayName("ClienteRepository guarda y recupera Cliente correctamente")
    void saveAndFindCliente() {
        // given: una Veterinaria requerida por la entidad Cliente
        Veterinaria veterinaria = Veterinaria.builder()
                .nombre("Clínica Central")
                .telefono("123456789")
                .direccion("Calle Falsa 123")
                .build();
        veterinaria = veterinariaRepository.save(veterinaria);

        Cliente cliente = Cliente.builder()
                .veterinaria(veterinaria)
                .nombre("Juan Pérez")
                .telefono("555-0000")
                .email("juan.perez@example.com")
                .build();

        // when: se guarda el cliente
        Cliente saved = clienteRepository.save(cliente);

        // then: se genera un id y se puede recuperar por id y via findAll
        assertThat(saved.getId()).isNotNull();

        Optional<Cliente> foundById = clienteRepository.findById(saved.getId());
        assertThat(foundById)
                .isPresent()
                .get()
                .extracting(Cliente::getNombre, Cliente::getEmail)
                .containsExactly("Juan Pérez", "juan.perez@example.com");

        List<Cliente> all = clienteRepository.findAll();
        assertThat(all)
                .hasSize(1)
                .first()
                .extracting(Cliente::getNombre)
                .isEqualTo("Juan Pérez");
    }
}

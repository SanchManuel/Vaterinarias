package com.systemReady.veterinaria.Domain;

import com.systemReady.veterinaria.Domain.cliente.Cliente;
import com.systemReady.veterinaria.Domain.veterinaria.Veterinaria;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class ClienteTest {

    @Test
    void shouldCreateClienteWithAllFieldsProperlySet() {
        Veterinaria veterinaria = Veterinaria.builder()
                .id(1L)
                .nombre("Clínica Central")
                .telefono("123456789")
                .direccion("Calle Falsa 123")
                .build();

        Cliente cliente = Cliente.builder()
                .id(10L)
                .veterinaria(veterinaria)
                .nombre("Juan Pérez")
                .telefono("555-0000")
                .email("juan.perez@example.com")
                .build();

        assertNotNull(cliente);
        assertEquals(10L, cliente.getId());
        assertEquals("Juan Pérez", cliente.getNombre());
        assertEquals("555-0000", cliente.getTelefono());
        assertEquals("juan.perez@example.com", cliente.getEmail());
        assertNotNull(cliente.getVeterinaria());
        assertSame(veterinaria, cliente.getVeterinaria());
    }

    @Test
    void idFieldHasGeneratedValueAndIdAnnotations() throws NoSuchFieldException {
        Field idField = Cliente.class.getDeclaredField("id");

        Id idAnnotation = idField.getAnnotation(Id.class);
        assertNotNull(idAnnotation, "Cliente.id debe estar anotado con @Id");

        GeneratedValue generatedValue = idField.getAnnotation(GeneratedValue.class);
        assertNotNull(generatedValue, "Cliente.id debe estar anotado con @GeneratedValue");
        assertEquals(GenerationType.IDENTITY, generatedValue.strategy());
    }

    @Test
    void veterinariaRelationIsMandatoryLazyManyToOneWithNonNullableJoinColumn() throws NoSuchFieldException {
        Field veterinariaField = Cliente.class.getDeclaredField("veterinaria");

        ManyToOne manyToOne = veterinariaField.getAnnotation(ManyToOne.class);
        assertNotNull(manyToOne, "Cliente.veterinaria debe estar anotado con @ManyToOne");
        assertFalse(manyToOne.optional(), "La relación con Veterinaria debe ser obligatoria (optional = false)");
        assertEquals(FetchType.LAZY, manyToOne.fetch(), "La relación con Veterinaria debe ser LAZY");

        JoinColumn joinColumn = veterinariaField.getAnnotation(JoinColumn.class);
        assertNotNull(joinColumn, "Cliente.veterinaria debe tener @JoinColumn");
        assertEquals("veterinaria_id", joinColumn.name());
        assertFalse(joinColumn.nullable(), "La columna veterinaria_id debe ser NOT NULL");
    }

    @Test
    void nombreTelefonoEmailColumnsHaveExpectedConstraints() throws NoSuchFieldException {
        Field nombreField = Cliente.class.getDeclaredField("nombre");
        Column nombreColumn = nombreField.getAnnotation(Column.class);
        assertNotNull(nombreColumn, "Cliente.nombre debe tener @Column");
        assertFalse(nombreColumn.nullable(), "nombre debe ser NOT NULL");
        assertEquals(150, nombreColumn.length(), "nombre debe tener longitud máxima 150");

        Field telefonoField = Cliente.class.getDeclaredField("telefono");
        Column telefonoColumn = telefonoField.getAnnotation(Column.class);
        assertNotNull(telefonoColumn, "Cliente.telefono debe tener @Column");
        assertEquals(50, telefonoColumn.length(), "telefono debe tener longitud máxima 50");

        Field emailField = Cliente.class.getDeclaredField("email");
        Column emailColumn = emailField.getAnnotation(Column.class);
        assertNotNull(emailColumn, "Cliente.email debe tener @Column");
        assertEquals(150, emailColumn.length(), "email debe tener longitud máxima 150");
    }
}

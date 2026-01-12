package com.systemReady.veterinarias.Domain;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class MascotaTest {

    @Test
    void shouldCreateMascotaWithAllFieldsAndLocalDateHandledCorrectly() {
        Veterinaria veterinaria = Veterinaria.builder()
                .id(2L)
                .nombre("Veterinaria Sur")
                .telefono("987654321")
                .direccion("Avenida Siempre Viva 742")
                .build();

        Cliente cliente = Cliente.builder()
                .id(20L)
                .veterinaria(veterinaria)
                .nombre("María López")
                .telefono("555-1111")
                .email("maria.lopez@example.com")
                .build();

        LocalDate fechaNacimiento = LocalDate.of(2020, 5, 15);

        Mascota mascota = Mascota.builder()
                .id(100L)
                .cliente(cliente)
                .nombre("Firulais")
                .especie("Perro")
                .raza("Labrador")
                .fechaNacimiento(fechaNacimiento)
                .build();

        assertNotNull(mascota);
        assertEquals(100L, mascota.getId());
        assertEquals("Firulais", mascota.getNombre());
        assertEquals("Perro", mascota.getEspecie());
        assertEquals("Labrador", mascota.getRaza());
        assertEquals(fechaNacimiento, mascota.getFechaNacimiento());
        assertNotNull(mascota.getCliente());
        assertSame(cliente, mascota.getCliente());
    }

    @Test
    void idFieldHasGeneratedValueAndIdAnnotations() throws NoSuchFieldException {
        Field idField = Mascota.class.getDeclaredField("id");

        Id idAnnotation = idField.getAnnotation(Id.class);
        assertNotNull(idAnnotation, "Mascota.id debe estar anotado con @Id");

        GeneratedValue generatedValue = idField.getAnnotation(GeneratedValue.class);
        assertNotNull(generatedValue, "Mascota.id debe estar anotado con @GeneratedValue");
        assertEquals(GenerationType.IDENTITY, generatedValue.strategy());
    }

    @Test
    void clienteRelationIsMandatoryLazyManyToOneWithNonNullableJoinColumn() throws NoSuchFieldException {
        Field clienteField = Mascota.class.getDeclaredField("cliente");

        ManyToOne manyToOne = clienteField.getAnnotation(ManyToOne.class);
        assertNotNull(manyToOne, "Mascota.cliente debe estar anotado con @ManyToOne");
        assertFalse(manyToOne.optional(), "La relación con Cliente debe ser obligatoria (optional = false)");
        assertEquals(FetchType.LAZY, manyToOne.fetch(), "La relación con Cliente debe ser LAZY");

        JoinColumn joinColumn = clienteField.getAnnotation(JoinColumn.class);
        assertNotNull(joinColumn, "Mascota.cliente debe tener @JoinColumn");
        assertEquals("cliente_id", joinColumn.name());
        assertFalse(joinColumn.nullable(), "La columna cliente_id debe ser NOT NULL");
    }

    @Test
    void nombreEspecieRazaColumnsHaveExpectedConstraints() throws NoSuchFieldException {
        Field nombreField = Mascota.class.getDeclaredField("nombre");
        Column nombreColumn = nombreField.getAnnotation(Column.class);
        assertNotNull(nombreColumn, "Mascota.nombre debe tener @Column");
        assertFalse(nombreColumn.nullable(), "nombre debe ser NOT NULL");
        assertEquals(150, nombreColumn.length(), "nombre debe tener longitud máxima 150");

        Field especieField = Mascota.class.getDeclaredField("especie");
        Column especieColumn = especieField.getAnnotation(Column.class);
        assertNotNull(especieColumn, "Mascota.especie debe tener @Column");
        assertFalse(especieColumn.nullable(), "especie debe ser NOT NULL");
        assertEquals(50, especieColumn.length(), "especie debe tener longitud máxima 50");

        Field razaField = Mascota.class.getDeclaredField("raza");
        Column razaColumn = razaField.getAnnotation(Column.class);
        assertNotNull(razaColumn, "Mascota.raza debe tener @Column");
        assertEquals(80, razaColumn.length(), "raza debe tener longitud máxima 80");
    }
}

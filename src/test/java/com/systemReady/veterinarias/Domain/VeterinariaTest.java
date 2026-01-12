package com.systemReady.veterinarias.Domain;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class VeterinariaTest {

    @Test
    void shouldCreateVeterinariaWithAllFieldsProperlySet() {
        Veterinaria veterinaria = Veterinaria.builder()
                .id(5L)
                .nombre("Clínica Norte")
                .telefono("444-2222")
                .direccion("Boulevard Principal 99")
                .build();

        assertNotNull(veterinaria);
        assertEquals(5L, veterinaria.getId());
        assertEquals("Clínica Norte", veterinaria.getNombre());
        assertEquals("444-2222", veterinaria.getTelefono());
        assertEquals("Boulevard Principal 99", veterinaria.getDireccion());
    }

    @Test
    void idFieldHasGeneratedValueAndIdAnnotations() throws NoSuchFieldException {
        Field idField = Veterinaria.class.getDeclaredField("id");

        Id idAnnotation = idField.getAnnotation(Id.class);
        assertNotNull(idAnnotation, "Veterinaria.id debe estar anotado con @Id");

        GeneratedValue generatedValue = idField.getAnnotation(GeneratedValue.class);
        assertNotNull(generatedValue, "Veterinaria.id debe estar anotado con @GeneratedValue");
        assertEquals(GenerationType.IDENTITY, generatedValue.strategy());
    }

    @Test
    void nombreTelefonoDireccionColumnsHaveExpectedConstraints() throws NoSuchFieldException {
        Field nombreField = Veterinaria.class.getDeclaredField("nombre");
        Column nombreColumn = nombreField.getAnnotation(Column.class);
        assertNotNull(nombreColumn, "Veterinaria.nombre debe tener @Column");
        assertFalse(nombreColumn.nullable(), "nombre debe ser NOT NULL");
        assertEquals(150, nombreColumn.length(), "nombre debe tener longitud máxima 150");

        Field telefonoField = Veterinaria.class.getDeclaredField("telefono");
        Column telefonoColumn = telefonoField.getAnnotation(Column.class);
        assertNotNull(telefonoColumn, "Veterinaria.telefono debe tener @Column");
        assertEquals(50, telefonoColumn.length(), "telefono debe tener longitud máxima 50");
        // nullable por defecto es true si no se especifica, así que no se comprueba explícitamente

        Field direccionField = Veterinaria.class.getDeclaredField("direccion");
        Column direccionColumn = direccionField.getAnnotation(Column.class);
        assertNotNull(direccionColumn, "Veterinaria.direccion debe tener @Column");
        assertEquals("text", direccionColumn.columnDefinition(), "direccion debe tener columnDefinition = 'text'");
    }
}

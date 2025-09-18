package com.pe.insalud.backend.paciente.interfaces.rest.resources;

public record PacienteResource(
        Long id,
        String nombre,
        String email,
        String estado
) {}

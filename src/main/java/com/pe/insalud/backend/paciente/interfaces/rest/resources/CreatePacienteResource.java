package com.pe.insalud.backend.paciente.interfaces.rest.resources;

public record CreatePacienteResource(
        String nombre,
        String email,
        String contrasena
) {
    // Constructor with validation
    public CreatePacienteResource {
        if (nombre == null || nombre.isBlank()) throw new IllegalArgumentException("Nombre is required");
        if (email == null || email.isBlank()) throw new IllegalArgumentException("Email is required");
        if (contrasena == null || contrasena.isBlank()) throw new IllegalArgumentException("Contrasena is required");
    }
}

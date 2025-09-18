package com.pe.insalud.backend.paciente.domain.model.commands;

public record CreatePacienteCommand(
        String nombre,
        String email,
        String contrasena
) {}

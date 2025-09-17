package com.pe.insalud.backend.paciente.domain.services;

import com.pe.insalud.backend.paciente.domain.model.aggregates.Paciente;
import com.pe.insalud.backend.paciente.domain.model.commands.CreatePacienteCommand;

import java.util.Optional;

// Service interface to handle patient commands
public interface PacienteCommandService {
    Optional<Paciente> handle(CreatePacienteCommand command);
}

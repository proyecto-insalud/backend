package com.pe.insalud.backend.paciente.domain.services;

import com.pe.insalud.backend.paciente.domain.model.aggregates.Paciente;
import com.pe.insalud.backend.paciente.domain.model.queries.GetAllPacientesQuery;
import com.pe.insalud.backend.paciente.domain.model.queries.GetPacienteByEmailQuery;
import com.pe.insalud.backend.paciente.domain.model.queries.GetPacienteByIdQuery;

import java.util.List;
import java.util.Optional;

// Service interface to handle patient queries
public interface PacienteQueryService {
    Optional<Paciente> handle(GetPacienteByIdQuery query);
    Optional<Paciente> handle(GetPacienteByEmailQuery query);
    List<Paciente> handle(GetAllPacientesQuery query);
}

package com.pe.insalud.backend.paciente.application.internal.queryservices;

import com.pe.insalud.backend.paciente.domain.model.aggregates.Paciente;
import com.pe.insalud.backend.paciente.domain.model.queries.GetAllPacientesQuery;
import com.pe.insalud.backend.paciente.domain.model.queries.GetPacienteByEmailQuery;
import com.pe.insalud.backend.paciente.domain.model.queries.GetPacienteByIdQuery;
import com.pe.insalud.backend.paciente.domain.services.PacienteQueryService;
import com.pe.insalud.backend.paciente.infrastructure.persistence.jpa.repositories.PacienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
// Implementation of PacienteQueryService to handle patient queries
public class PacienteQueryServiceImpl implements PacienteQueryService {

    private final PacienteRepository pacienteRepository;

    public PacienteQueryServiceImpl(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    // Handles query to get patient by ID
    @Override
    public Optional<Paciente> handle(GetPacienteByIdQuery query) {
        return pacienteRepository.findById(query.pacienteId());
    }

    // Handles query to get patient by email
    @Override
    public Optional<Paciente> handle(GetPacienteByEmailQuery query) {
        return pacienteRepository.findByEmailAddress(query.emailAddress());
    }

    // Handles query to get all patients
    @Override
    public List<Paciente> handle(GetAllPacientesQuery query) {
        return pacienteRepository.findAll();
    }
}

package com.pe.insalud.backend.atencion.application.internal.queryservices;

import com.pe.insalud.backend.atencion.domain.model.aggregates.Atencion;
import com.pe.insalud.backend.atencion.domain.model.queries.GetAllAtencionesQuery;
import com.pe.insalud.backend.atencion.domain.model.queries.GetAtencionesByPacienteQuery;
import com.pe.insalud.backend.atencion.domain.services.AtencionQueryService;
import com.pe.insalud.backend.atencion.infrastructure.persistence.jpa.repositories.AtencionRepository;
import com.pe.insalud.backend.paciente.domain.model.valueobjects.EmailAddress;
import com.pe.insalud.backend.paciente.infrastructure.persistence.jpa.repositories.PacienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AtencionQueryServiceImpl implements AtencionQueryService {

    // Repositories to access Atencion and Paciente data
    private final AtencionRepository atencionRepository;
    private final PacienteRepository pacienteRepository;

    // Constructor to initialize the repositories
    public AtencionQueryServiceImpl(AtencionRepository atencionRepository, PacienteRepository pacienteRepository) {
        this.atencionRepository = atencionRepository;
        this.pacienteRepository = pacienteRepository;
    }

    // Handle retrieving all atenciones (appointments)
    @Override
    public List<Atencion> handle(GetAllAtencionesQuery query) {
        return atencionRepository.findAll(); // Return all atenciones from the repository
    }

    // Handle retrieving atenciones by paciente's ID
    @Override
    public List<Atencion> handle(GetAtencionesByPacienteQuery query) {
        // Retrieve the Paciente by ID and throw exception if not found
        var paciente = pacienteRepository.findById(query.pacienteId()).orElseThrow(() ->
                new RuntimeException("Paciente no encontrado"));

        // Return all atenciones associated with this paciente
        return atencionRepository.findByPaciente(paciente);
    }

    // Handle finding paciente ID by email address
    @Override
    public Long findPacienteIdByEmail(String email) {
        // Convert the email to a value object (EmailAddress)
        EmailAddress emailVO = new EmailAddress(email);

        // Find the paciente by email and return their ID
        return pacienteRepository.findByEmailAddress(emailVO)
                .map(p -> p.getId())
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado con email: " + email));
    }
}

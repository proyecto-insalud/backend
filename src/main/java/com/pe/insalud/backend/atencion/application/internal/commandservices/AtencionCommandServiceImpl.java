package com.pe.insalud.backend.atencion.application.internal.commandservices;

import com.pe.insalud.backend.atencion.domain.model.aggregates.Atencion;
import com.pe.insalud.backend.atencion.domain.model.commands.CreateAtencionCommand;
import com.pe.insalud.backend.atencion.domain.model.commands.UpdateAtencionCommand;
import com.pe.insalud.backend.atencion.domain.model.commands.DeleteAtencionCommand;
import com.pe.insalud.backend.atencion.domain.services.AtencionCommandService;
import com.pe.insalud.backend.atencion.infrastructure.persistence.jpa.repositories.AtencionRepository;
import com.pe.insalud.backend.medico.infrastructure.persistence.jpa.repositories.MedicoRepository;
import com.pe.insalud.backend.paciente.infrastructure.persistence.jpa.repositories.PacienteRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AtencionCommandServiceImpl implements AtencionCommandService {

    // Repositories to access Atencion, Paciente, and Medico data
    private final AtencionRepository atencionRepository;
    private final PacienteRepository pacienteRepository;
    private final MedicoRepository medicoRepository;

    // Constructor to initialize dependencies
    public AtencionCommandServiceImpl(AtencionRepository atencionRepository,
                                      PacienteRepository pacienteRepository,
                                      MedicoRepository medicoRepository) {
        this.atencionRepository = atencionRepository;
        this.pacienteRepository = pacienteRepository;
        this.medicoRepository = medicoRepository;
    }

    // Handle creating a new Atencion (appointment)
    @Override
    public Optional<Atencion> handle(CreateAtencionCommand command) {
        // Retrieve Paciente and Medico by their IDs
        var paciente = pacienteRepository.findById(command.pacienteId()).orElseThrow();
        var medico = medicoRepository.findById(command.medicoId()).orElseThrow();

        // Create a new Atencion and save it to the repository
        var atencion = new Atencion(command.fecha(), command.motivo(), paciente, medico);
        atencionRepository.save(atencion);
        return Optional.of(atencion);
    }

    // Handle updating an existing Atencion (appointment)
    @Override
    public Optional<Atencion> handle(UpdateAtencionCommand command) {
        // Retrieve the Atencion to be updated and the Medico by ID
        var atencion = atencionRepository.findById(command.atencionId()).orElseThrow();
        var medico = medicoRepository.findById(command.medicoId()).orElseThrow();

        // Update the Atencion's details
        atencion.update(command.motivo(), command.fecha(), medico);
        atencionRepository.save(atencion);
        return Optional.of(atencion);
    }

    // Handle deleting (removing) an Atencion (appointment) from DB
    @Override
    public void handle(DeleteAtencionCommand command) {
        if (!atencionRepository.existsById(command.atencionId())) {
            throw new IllegalArgumentException("La atención con ID " + command.atencionId() + " no existe");
        }
        atencionRepository.deleteById(command.atencionId());
    }
}

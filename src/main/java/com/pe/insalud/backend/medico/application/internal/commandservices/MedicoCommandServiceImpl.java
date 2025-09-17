package com.pe.insalud.backend.medico.application.internal.commandservices;

import com.pe.insalud.backend.medico.domain.model.aggregates.Medico;
import com.pe.insalud.backend.medico.domain.model.commands.CreateMedicoCommand;
import com.pe.insalud.backend.medico.domain.services.MedicoCommandService;
import com.pe.insalud.backend.medico.infrastructure.persistence.jpa.repositories.MedicoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MedicoCommandServiceImpl implements MedicoCommandService {

    private final MedicoRepository medicoRepository;

    public MedicoCommandServiceImpl(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    @Override
    public Optional<Medico> handle(CreateMedicoCommand command) {
        // You could add validation to check if a doctor with the same name exists, or other rules
        var medico = new Medico(command.nombre(), command.especialidades());
        medicoRepository.save(medico); // Save the doctor to the repository
        return Optional.of(medico); // Return the created doctor
    }
}

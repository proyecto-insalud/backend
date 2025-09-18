package com.pe.insalud.backend.paciente.application.internal.commandservices;

import com.pe.insalud.backend.paciente.domain.model.aggregates.Paciente;
import com.pe.insalud.backend.paciente.domain.model.commands.CreatePacienteCommand;
import com.pe.insalud.backend.paciente.domain.model.valueobjects.EmailAddress;
import com.pe.insalud.backend.paciente.domain.services.PacienteCommandService;
import com.pe.insalud.backend.paciente.infrastructure.persistence.jpa.repositories.PacienteRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
// Implementation of PacienteCommandService to handle commands
public class PacienteCommandServiceImpl implements PacienteCommandService {

    private final PacienteRepository pacienteRepository;
    private final PasswordEncoder passwordEncoder;

    public PacienteCommandServiceImpl(PacienteRepository pacienteRepository, PasswordEncoder passwordEncoder) {
        this.pacienteRepository = pacienteRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Handles the creation of a new patient
    @Override
    public Optional<Paciente> handle(CreatePacienteCommand command) {
        var email = new EmailAddress(command.email());

        // Check if the email already exists
        if (pacienteRepository.existsByEmailAddress(email)) {
            throw new IllegalArgumentException("Email already registered");
        }

        // Encrypt password
        String encoded = passwordEncoder.encode(command.contrasena());

        // Create and save the new patient
        var paciente = new Paciente(command.nombre(), command.email(), encoded);
        pacienteRepository.save(paciente);

        return Optional.of(paciente);
    }
}

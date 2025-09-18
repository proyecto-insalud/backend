package com.pe.insalud.backend.paciente.infrastructure.persistence.jpa.repositories;

import com.pe.insalud.backend.paciente.domain.model.aggregates.Paciente;
import com.pe.insalud.backend.paciente.domain.model.valueobjects.EmailAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
// Repository for Paciente aggregate
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    Optional<Paciente> findByEmailAddress(EmailAddress emailAddress);
    boolean existsByEmailAddress(EmailAddress emailAddress);
}

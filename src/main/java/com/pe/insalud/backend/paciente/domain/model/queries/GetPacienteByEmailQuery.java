package com.pe.insalud.backend.paciente.domain.model.queries;

import com.pe.insalud.backend.paciente.domain.model.valueobjects.EmailAddress;

// Query to get a patient by email address
public record GetPacienteByEmailQuery(EmailAddress emailAddress) {
}

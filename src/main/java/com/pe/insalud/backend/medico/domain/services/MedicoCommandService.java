package com.pe.insalud.backend.medico.domain.services;

import com.pe.insalud.backend.medico.domain.model.aggregates.Medico;
import com.pe.insalud.backend.medico.domain.model.commands.CreateMedicoCommand;

import java.util.Optional;

public interface MedicoCommandService {
    Optional<Medico> handle(CreateMedicoCommand command);
}

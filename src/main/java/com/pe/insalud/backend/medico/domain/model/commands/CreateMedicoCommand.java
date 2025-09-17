package com.pe.insalud.backend.medico.domain.model.commands;

import com.pe.insalud.backend.medico.domain.model.valueobjects.Especialidad;

import java.util.Set;

public record CreateMedicoCommand(
        String nombre,  // Doctor's name
        Set<Especialidad> especialidades  // Set of specialties assigned to the doctor
) {}

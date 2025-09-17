package com.pe.insalud.backend.medico.domain.model.queries;

import com.pe.insalud.backend.medico.domain.model.valueobjects.Especialidad;

public record GetMedicosByEspecialidadQuery(Especialidad especialidad) {}

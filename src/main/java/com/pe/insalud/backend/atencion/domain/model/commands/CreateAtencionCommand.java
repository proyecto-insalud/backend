package com.pe.insalud.backend.atencion.domain.model.commands;

import java.time.LocalDate;

public record CreateAtencionCommand(
        LocalDate fecha,
        String motivo,
        Long pacienteId,
        Long medicoId
) {}

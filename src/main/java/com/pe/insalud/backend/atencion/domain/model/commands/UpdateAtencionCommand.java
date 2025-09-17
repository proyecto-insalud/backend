package com.pe.insalud.backend.atencion.domain.model.commands;

import java.time.LocalDate;

public record UpdateAtencionCommand(
        Long atencionId,
        LocalDate fecha,
        String motivo,
        Long medicoId
) {}

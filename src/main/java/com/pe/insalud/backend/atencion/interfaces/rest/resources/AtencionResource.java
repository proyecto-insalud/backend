package com.pe.insalud.backend.atencion.interfaces.rest.resources;

import java.time.LocalDate;

public record AtencionResource(
        Long id,
        LocalDate fecha,
        String motivo,
        Long pacienteId,
        Long medicoId,
        String estado
) {}

package com.pe.insalud.backend.atencion.interfaces.rest.resources;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record CreateAtencionResource(
        @NotNull LocalDate fecha,
        @NotBlank String motivo,
        @NotNull Long pacienteId,
        @NotNull Long medicoId
) {}

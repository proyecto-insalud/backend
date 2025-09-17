package com.pe.insalud.backend.medico.interfaces.rest.resources;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;

public record CreateMedicoResource(
        @NotBlank String nombre,
        @NotEmpty List<String> especialidades
) {}

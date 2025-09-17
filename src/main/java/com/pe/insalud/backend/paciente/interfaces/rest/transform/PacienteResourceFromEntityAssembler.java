package com.pe.insalud.backend.paciente.interfaces.rest.transform;

import com.pe.insalud.backend.paciente.domain.model.aggregates.Paciente;
import com.pe.insalud.backend.paciente.interfaces.rest.resources.PacienteResource;

public class PacienteResourceFromEntityAssembler {
    // Converts Paciente entity to PacienteResource
    public static PacienteResource toResourceFromEntity(Paciente p) {
        return new PacienteResource(p.getId(), p.getNombre(), p.getEmail(), p.getEstado());
    }
}

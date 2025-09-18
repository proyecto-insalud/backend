package com.pe.insalud.backend.paciente.interfaces.rest.transform;

import com.pe.insalud.backend.paciente.domain.model.commands.CreatePacienteCommand;
import com.pe.insalud.backend.paciente.interfaces.rest.resources.CreatePacienteResource;

public class CreatePacienteCommandFromResourceAssembler {
    // Converts CreatePacienteResource to CreatePacienteCommand
    public static CreatePacienteCommand toCommandFromResource(CreatePacienteResource r) {
        return new CreatePacienteCommand(r.nombre(), r.email(), r.contrasena());
    }
}

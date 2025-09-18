package com.pe.insalud.backend.medico.interfaces.rest.transform;

import com.pe.insalud.backend.medico.domain.model.commands.CreateMedicoCommand;
import com.pe.insalud.backend.medico.domain.model.valueobjects.Especialidad;
import com.pe.insalud.backend.medico.interfaces.rest.resources.CreateMedicoResource;

import java.util.Set;
import java.util.stream.Collectors;

public class CreateMedicoCommandFromResourceAssembler {

    public static CreateMedicoCommand toCommandFromResource(CreateMedicoResource r) {
        Set<Especialidad> especialidades = r.especialidades().stream()
                .map(String::toUpperCase)
                .map(Especialidad::valueOf)
                .collect(Collectors.toSet());
        return new CreateMedicoCommand(r.nombre(), especialidades);
    }
}

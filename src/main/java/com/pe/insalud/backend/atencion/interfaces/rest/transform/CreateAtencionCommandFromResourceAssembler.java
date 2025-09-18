package com.pe.insalud.backend.atencion.interfaces.rest.transform;

import com.pe.insalud.backend.atencion.domain.model.commands.CreateAtencionCommand;
import com.pe.insalud.backend.atencion.interfaces.rest.resources.CreateAtencionResource;

public class CreateAtencionCommandFromResourceAssembler {
    public static CreateAtencionCommand toCommandFromResource(CreateAtencionResource r) {
        return new CreateAtencionCommand(r.fecha(), r.motivo(), r.pacienteId(), r.medicoId());
    }
}

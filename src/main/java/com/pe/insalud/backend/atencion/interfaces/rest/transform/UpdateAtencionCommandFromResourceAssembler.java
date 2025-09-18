package com.pe.insalud.backend.atencion.interfaces.rest.transform;

import com.pe.insalud.backend.atencion.domain.model.commands.UpdateAtencionCommand;
import com.pe.insalud.backend.atencion.interfaces.rest.resources.UpdateAtencionResource;

public class UpdateAtencionCommandFromResourceAssembler {
    public static UpdateAtencionCommand toCommandFromResource(Long id, UpdateAtencionResource r) {
        return new UpdateAtencionCommand(id, r.fecha(), r.motivo(), r.medicoId());
    }
}

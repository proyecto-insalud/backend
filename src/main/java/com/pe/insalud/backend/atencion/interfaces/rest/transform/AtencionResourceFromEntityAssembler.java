package com.pe.insalud.backend.atencion.interfaces.rest.transform;

import com.pe.insalud.backend.atencion.domain.model.aggregates.Atencion;
import com.pe.insalud.backend.atencion.interfaces.rest.resources.AtencionResource;

public class AtencionResourceFromEntityAssembler {
    public static AtencionResource toResourceFromEntity(Atencion a) {
        return new AtencionResource(
                a.getId(),
                a.getFecha(),
                a.getMotivo(),
                a.getPaciente().getId(),
                a.getMedico().getId(),
                a.getEstado()
        );
    }
}

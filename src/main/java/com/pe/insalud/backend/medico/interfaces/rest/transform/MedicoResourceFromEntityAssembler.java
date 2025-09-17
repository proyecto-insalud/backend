package com.pe.insalud.backend.medico.interfaces.rest.transform;

import com.pe.insalud.backend.medico.domain.model.aggregates.Medico;
import com.pe.insalud.backend.medico.interfaces.rest.resources.MedicoResource;

import java.util.List;
import java.util.stream.Collectors;

public class MedicoResourceFromEntityAssembler {

    public static MedicoResource toResourceFromEntity(Medico m) {
        List<String> especialidades = m.getEspecialidades().stream()
                .map(Enum::name)
                .collect(Collectors.toList());
        return new MedicoResource(m.getId(), m.getNombre(), especialidades, m.getEstado());
    }
}

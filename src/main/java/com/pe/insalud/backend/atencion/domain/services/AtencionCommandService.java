package com.pe.insalud.backend.atencion.domain.services;

import com.pe.insalud.backend.atencion.domain.model.aggregates.Atencion;
import com.pe.insalud.backend.atencion.domain.model.commands.CreateAtencionCommand;
import com.pe.insalud.backend.atencion.domain.model.commands.UpdateAtencionCommand;
import com.pe.insalud.backend.atencion.domain.model.commands.DeleteAtencionCommand;

import java.util.Optional;

public interface AtencionCommandService {

    // Handle creating a new appointment (Atencion)
    Optional<Atencion> handle(CreateAtencionCommand command);

    // Handle updating an existing appointment (Atencion)
    Optional<Atencion> handle(UpdateAtencionCommand command);

    // Handle deleting an existing appointment (Atencion)
    void handle(DeleteAtencionCommand command);
}

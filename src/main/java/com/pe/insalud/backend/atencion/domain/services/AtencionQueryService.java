package com.pe.insalud.backend.atencion.domain.services;

import com.pe.insalud.backend.atencion.domain.model.aggregates.Atencion;
import com.pe.insalud.backend.atencion.domain.model.queries.GetAllAtencionesQuery;
import com.pe.insalud.backend.atencion.domain.model.queries.GetAtencionesByPacienteQuery;

import java.util.List;

public interface AtencionQueryService {

    // Handle retrieving all atenciones (appointments)
    List<Atencion> handle(GetAllAtencionesQuery query);

    // Handle retrieving atenciones (appointments) for a specific paciente (patient)
    List<Atencion> handle(GetAtencionesByPacienteQuery query);

    /**
     * Retrieves the patient ID associated with an authenticated email (username).
     * This is required to list the atenciones (appointments) for the specific patient.
     *
     * @param email the username/email from the JWT
     * @return the patient ID corresponding to the email
     */
    Long findPacienteIdByEmail(String email);
}

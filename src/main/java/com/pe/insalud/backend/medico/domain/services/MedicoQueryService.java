package com.pe.insalud.backend.medico.domain.services;

import com.pe.insalud.backend.medico.domain.model.aggregates.Medico;
import com.pe.insalud.backend.medico.domain.model.queries.GetAllMedicosQuery;
import com.pe.insalud.backend.medico.domain.model.queries.GetMedicoByIdQuery;
import com.pe.insalud.backend.medico.domain.model.queries.GetMedicosByEspecialidadQuery;

import java.util.List;
import java.util.Optional;

public interface MedicoQueryService {
    // Handle query to fetch a doctor by ID
    Optional<Medico> handle(GetMedicoByIdQuery query);

    // Handle query to fetch all doctors
    List<Medico> handle(GetAllMedicosQuery query);

    // Handle query to fetch doctors by their specialty
    List<Medico> handle(GetMedicosByEspecialidadQuery query);

    // Fetch doctors by their state (e.g., "ACTIVO", "INACTIVO")
    List<Medico> getMedicosPorEstado(String estado);
}

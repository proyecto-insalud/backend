package com.pe.insalud.backend.medico.application.internal.queryservices;

import com.pe.insalud.backend.medico.domain.model.aggregates.Medico;
import com.pe.insalud.backend.medico.domain.model.queries.GetAllMedicosQuery;
import com.pe.insalud.backend.medico.domain.model.queries.GetMedicoByIdQuery;
import com.pe.insalud.backend.medico.domain.model.queries.GetMedicosByEspecialidadQuery;
import com.pe.insalud.backend.medico.domain.services.MedicoQueryService;
import com.pe.insalud.backend.medico.infrastructure.persistence.jpa.repositories.MedicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicoQueryServiceImpl implements MedicoQueryService {

    private final MedicoRepository medicoRepository;

    public MedicoQueryServiceImpl(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    @Override
    public Optional<Medico> handle(GetMedicoByIdQuery query) {
        // Fetch doctor by ID
        return medicoRepository.findById(query.medicoId());
    }

    @Override
    public List<Medico> handle(GetAllMedicosQuery query) {
        // Fetch all doctors
        return medicoRepository.findAll();
    }

    @Override
    public List<Medico> handle(GetMedicosByEspecialidadQuery query) {
        // Fetch doctors by specialty
        return medicoRepository.findByEspecialidad(query.especialidad());
    }

    @Override
    public List<Medico> getMedicosPorEstado(String estado) {
        // Fetch doctors by their state (e.g., "ACTIVO", "INACTIVO")
        return medicoRepository.findByEstado(estado);
    }
}

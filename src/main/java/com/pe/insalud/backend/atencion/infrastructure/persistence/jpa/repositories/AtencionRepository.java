package com.pe.insalud.backend.atencion.infrastructure.persistence.jpa.repositories;

import com.pe.insalud.backend.atencion.domain.model.aggregates.Atencion;
import com.pe.insalud.backend.medico.domain.model.aggregates.Medico;
import com.pe.insalud.backend.paciente.domain.model.aggregates.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AtencionRepository extends JpaRepository<Atencion, Long> {

    // Find all atenciones (appointments) for a specific paciente (patient)
    List<Atencion> findByPaciente(Paciente paciente);

    // Find all atenciones (appointments) for a specific medico (doctor) on a specific date
    List<Atencion> findByMedicoAndFecha(Medico medico, LocalDate fecha);
}

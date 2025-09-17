package com.pe.insalud.backend.medico.infrastructure.persistence.jpa.repositories;

import com.pe.insalud.backend.medico.domain.model.aggregates.Medico;
import com.pe.insalud.backend.medico.domain.model.valueobjects.Especialidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {

    // Search doctors by name
    List<Medico> findByNombreContainingIgnoreCase(String nombre);

    // Search doctors by their specialty
    @Query("select m from Medico m join m.especialidades e where e = :especialidad")
    List<Medico> findByEspecialidad(@Param("especialidad") Especialidad especialidad);

    // Custom query: search doctors by their state (e.g., "ACTIVO", "INACTIVO")
    @Query("SELECT m FROM Medico m WHERE m.estado = :estado")
    List<Medico> findByEstado(@Param("estado") String estado);
}

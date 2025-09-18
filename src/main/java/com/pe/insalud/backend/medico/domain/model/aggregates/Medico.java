package com.pe.insalud.backend.medico.domain.model.aggregates;

import com.pe.insalud.backend.medico.domain.model.valueobjects.Especialidad;
import com.pe.insalud.backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "medicos")
public class Medico extends AuditableAbstractAggregateRoot<Medico> {

    @NotBlank
    @Column(nullable = false)
    private String nombre;

    // Set of specialties assigned to the doctor
    @ElementCollection(targetClass = Especialidad.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "medico_especialidades", joinColumns = @JoinColumn(name = "medico_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "especialidad", nullable = false)
    private Set<Especialidad> especialidades = new HashSet<>();

    @Column(nullable = false)
    private String estado = "ACTIVO"; // Default state is ACTIVE

    // JPA constructor
    protected Medico() {}

    // Constructor for creating a new Medico
    public Medico(String nombre, Set<Especialidad> especialidades) {
        this.nombre = nombre;
        this.especialidades = especialidades == null ? new HashSet<>() : new HashSet<>(especialidades);
    }

    public String getNombre() { return nombre; }
    public Set<Especialidad> getEspecialidades() { return Set.copyOf(especialidades); }
    public String getEstado() { return estado; }

    // Method to update the name of the doctor
    public void updateNombre(String nombre) { this.nombre = nombre; }

    // Methods to manage specialties
    public void addEspecialidad(Especialidad e) { this.especialidades.add(e); }
    public void removeEspecialidad(Especialidad e) { this.especialidades.remove(e); }

    // Method to deactivate the doctor
    public void desactivar() { this.estado = "INACTIVO"; }
}

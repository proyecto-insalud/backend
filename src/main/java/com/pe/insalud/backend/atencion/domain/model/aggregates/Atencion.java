package com.pe.insalud.backend.atencion.domain.model.aggregates;

import com.pe.insalud.backend.medico.domain.model.aggregates.Medico;
import com.pe.insalud.backend.paciente.domain.model.aggregates.Paciente;
import com.pe.insalud.backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
@Table(name = "atenciones")
public class Atencion extends AuditableAbstractAggregateRoot<Atencion> {

    @NotNull // Ensures the 'fecha' field cannot be null
    @Column(nullable = false) // The 'fecha' field must not be null in the database
    private LocalDate fecha;

    @NotBlank // Ensures the 'motivo' field cannot be blank
    @Column(nullable = false) // The 'motivo' field must not be null or blank in the database
    private String motivo;

    @ManyToOne(fetch = FetchType.EAGER) // Many-to-one relationship with 'Paciente' (eager loading)
    @JoinColumn(name = "paciente_id", nullable = false) // Foreign key reference to 'Paciente'
    private Paciente paciente;

    @ManyToOne(fetch = FetchType.EAGER) // Many-to-one relationship with 'Medico' (eager loading)
    @JoinColumn(name = "medico_id", nullable = false) // Foreign key reference to 'Medico'
    private Medico medico;

    @Column(nullable = false) // Default state of the appointment is 'PROGRAMADA'
    private String estado = "PROGRAMADA";

    // Default constructor for JPA
    protected Atencion() {}

    // Constructor to initialize the 'Atencion' entity
    public Atencion(LocalDate fecha, String motivo, Paciente paciente, Medico medico) {
        this.fecha = fecha;
        this.motivo = motivo;
        this.paciente = paciente;
        this.medico = medico;
    }

    // Getter methods for each field
    public LocalDate getFecha() { return fecha; }
    public String getMotivo() { return motivo; }
    public Paciente getPaciente() { return paciente; }
    public Medico getMedico() { return medico; }
    public String getEstado() { return estado; }

    // Method to update the 'Atencion' details
    public void update(String motivo, LocalDate fecha, Medico medico) {
        this.motivo = motivo;
        this.fecha = fecha;
        this.medico = medico;
    }

    // Method to cancel the 'Atencion', changing its state to 'CANCELADA'
    public void cancelar() {
        this.estado = "CANCELADA";
    }
}

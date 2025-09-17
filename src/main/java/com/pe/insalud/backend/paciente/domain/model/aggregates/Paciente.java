package com.pe.insalud.backend.paciente.domain.model.aggregates;

import com.pe.insalud.backend.paciente.domain.model.commands.CreatePacienteCommand;
import com.pe.insalud.backend.paciente.domain.model.valueobjects.EmailAddress;
import com.pe.insalud.backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "pacientes")
public class Paciente extends AuditableAbstractAggregateRoot<Paciente> {

    @NotBlank
    @Column(nullable = false)
    private String nombre;

    @Embedded
    @AttributeOverride(name = "address", column = @Column(name = "email", nullable = false, unique = true))
    private EmailAddress emailAddress;

    @NotBlank
    @Column(nullable = false)
    private String contrasena;

    @Column(nullable = false)
    private String estado = "ACTIVO";

    // Default constructor for JPA
    public Paciente() { }

    // Constructor with fields
    public Paciente(String nombre, String email, String contrasena) {
        this.nombre = nombre;
        this.emailAddress = new EmailAddress(email);
        this.contrasena = contrasena;
    }

    // Constructor from command object
    public Paciente(CreatePacienteCommand command) {
        this(command.nombre(), command.email(), command.contrasena());
    }

    // Getters
    public String getNombre() { return nombre; }

    public EmailAddress getEmailAddress() { return emailAddress; }

    public String getEmail() { return emailAddress.address(); }

    public String getContrasena() { return contrasena; }

    public String getEstado() { return estado; }

    // Update patient name
    public void updateNombre(String nombre) { this.nombre = nombre; }

    // Update patient password
    public void updateContrasena(String contrasena) { this.contrasena = contrasena; }

    // Deactivate patient
    public void desactivar() { this.estado = "INACTIVO"; }

    // Static factory method
    public static Paciente create() {
        return new Paciente();
    }
}

package com.pe.insalud.backend.iam.domain.model.aggregates;

import com.pe.insalud.backend.iam.domain.model.entities.Role;
import com.pe.insalud.backend.paciente.domain.model.aggregates.Paciente;
import com.pe.insalud.backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Representa un usuario del sistema (entidad raíz del agregado).
 */
@Getter
@Setter
@Entity
public class User extends AuditableAbstractAggregateRoot<User> {

    @NotBlank
    @Size(max = 50)
    @Column(unique = true)
    private String username; // Nombre de usuario único

    @NotBlank
    @Size(max = 120)
    private String password; // Contraseña encriptada

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles; // Roles asignados al usuario

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "paciente_id")
    private Paciente paciente; // Asociación con paciente (si aplica)

    @NotBlank
    @Size(max = 100)
    @Column(unique = true, nullable = false)
    private String email; // Correo electrónico único

    // Constructor por defecto
    public User() {
        this.roles = new HashSet<>();
    }

    // Constructor sin roles
    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.roles = new HashSet<>();
    }

    // Constructor con roles
    public User(String username, String password, String email, List<Role> roles) {
        this(username, password, email);
        addRoles(roles);
    }

    // Agregar un rol individual
    public User addRole(Role role) {
        this.roles.add(role);
        return this;
    }

    // Agregar una lista de roles, validando si está vacía
    public User addRoles(List<Role> roles) {
        var validatedRoleSet = Role.validateRoleSet(roles);
        this.roles.addAll(validatedRoleSet);
        return this;
    }

}

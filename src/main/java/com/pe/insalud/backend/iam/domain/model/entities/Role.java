package com.pe.insalud.backend.iam.domain.model.entities;

import com.pe.insalud.backend.iam.domain.model.valueobjects.Roles;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

import java.util.List;

// Representa un rol en el sistema (ej: ROLE_ADMIN, ROLE_PACIENTE)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@With
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Enum que representa el nombre del rol (como texto)
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Roles name;

    public Role(Roles name) {
        this.name = name;
    }

    // Devuelve el nombre del rol como String
    public String getStringName() {
        return name.name();
    }

    // Retorna el rol por defecto del sistema
    public static Role getDefaultRole() {
        return new Role(Roles.ROLE_PACIENTE);
    }

    // Crea un rol a partir de su nombre como String
    public static Role toRoleFromName(String name) {
        return new Role(Roles.valueOf(name));
    }

    // Si la lista de roles es nula o vacía, retorna una con el rol por defecto
    public static List<Role> validateRoleSet(List<Role> roles) {
        if (roles == null || roles.isEmpty()) {
            return List.of(getDefaultRole());
        }
        return roles;
    }
}

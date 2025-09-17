package com.pe.insalud.backend.iam.domain.model.commands;

import com.pe.insalud.backend.iam.domain.model.entities.Role;
import lombok.Getter;

import java.util.List;

/**
 * Comando para registrar un nuevo usuario (sign-up)
 */
@Getter
public class SignUpCommand {

    // Nombre de usuario
    private final String username;

    // Contraseña del usuario
    private final String password;

    // Correo electrónico del usuario
    private final String email;

    // Lista de roles asignados al usuario
    private final List<Role> roles;

    // Constructor
    public SignUpCommand(String username, String password, String email, List<Role> roles) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.roles = roles;
    }
}

package com.pe.insalud.backend.iam.domain.model.commands;

/**
 * Comando para iniciar sesión
 * Contiene el nombre de usuario y la contraseña
 */
public record SignInCommand(String username, String password) {
}

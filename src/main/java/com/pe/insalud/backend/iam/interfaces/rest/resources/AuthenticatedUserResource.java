package com.pe.insalud.backend.iam.interfaces.rest.resources;

import java.util.Set;

/**
 * Resource representing an authenticated user with ID, email, token, and roles.
 */
public record AuthenticatedUserResource(Long id, String email, String token, Set<String> roles) {
}

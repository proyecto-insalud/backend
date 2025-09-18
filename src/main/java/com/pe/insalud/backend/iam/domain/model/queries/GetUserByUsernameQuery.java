package com.pe.insalud.backend.iam.domain.model.queries;

/**
 * Query to get a user by username.
 * @param username the username
 */
public record GetUserByUsernameQuery(String username) {
}

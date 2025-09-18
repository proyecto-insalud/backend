package com.pe.insalud.backend.iam.domain.model.queries;

/**
 * Query to get a user by ID.
 * @param userId the user ID
 */
public record GetUserByIdQuery(Long userId) {
}

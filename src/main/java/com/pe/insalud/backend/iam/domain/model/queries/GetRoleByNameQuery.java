package com.pe.insalud.backend.iam.domain.model.queries;

import com.pe.insalud.backend.iam.domain.model.valueobjects.Roles;

/**
 * Query to get a role by its name
 * <p>
 *     Represents a query to retrieve a role based on its name.
 * </p>
 * @param name the name of the role
 * @see Roles
 */
public record GetRoleByNameQuery(Roles name) {
}

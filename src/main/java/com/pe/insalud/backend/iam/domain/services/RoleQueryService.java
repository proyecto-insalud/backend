package com.pe.insalud.backend.iam.domain.services;

import com.pe.insalud.backend.iam.domain.model.entities.Role;
import com.pe.insalud.backend.iam.domain.model.queries.GetAllRolesQuery;
import com.pe.insalud.backend.iam.domain.model.queries.GetRoleByNameQuery;

import java.util.List;
import java.util.Optional;

/**
 * Service interface for handling role queries.
 */
public interface RoleQueryService {
    /**
     * Handle query to get all roles.
     * @param query the GetAllRolesQuery
     * @return list of Role entities
     */
    List<Role> handle(GetAllRolesQuery query);

    /**
     * Handle query to get role by name.
     * @param query the GetRoleByNameQuery
     * @return optional Role entity
     */
    Optional<Role> handle(GetRoleByNameQuery query);
}

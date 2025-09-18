package com.pe.insalud.backend.iam.application.internal.queryservices;

import com.pe.insalud.backend.iam.domain.model.entities.Role;
import com.pe.insalud.backend.iam.domain.model.queries.GetAllRolesQuery;
import com.pe.insalud.backend.iam.domain.model.queries.GetRoleByNameQuery;
import com.pe.insalud.backend.iam.domain.services.RoleQueryService;
import com.pe.insalud.backend.iam.infrastructure.persistence.jpa.repositories.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service implementation to handle role queries.
 */
@Service
public class RoleQueryServiceImpl implements RoleQueryService {
    private final RoleRepository roleRepository;

    /**
     * Constructor with RoleRepository.
     */
    public RoleQueryServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    /**
     * Get all roles.
     */
    @Override
    public List<Role> handle(GetAllRolesQuery query) {
        return roleRepository.findAll();
    }

    /**
     * Get role by name.
     */
    @Override
    public Optional<Role> handle(GetRoleByNameQuery query) {
        return roleRepository.findByName(query.name());
    }
}

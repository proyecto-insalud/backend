package com.pe.insalud.backend.iam.application.internal.commandservices;

import com.pe.insalud.backend.iam.domain.model.commands.SeedRolesCommand;
import com.pe.insalud.backend.iam.domain.model.entities.Role;
import com.pe.insalud.backend.iam.domain.model.valueobjects.Roles;
import com.pe.insalud.backend.iam.domain.services.RoleCommandService;
import com.pe.insalud.backend.iam.infrastructure.persistence.jpa.repositories.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * Service implementation for role commands.
 */
@Service
public class RoleCommandServiceImpl implements RoleCommandService {

    private final RoleRepository roleRepository;

    public RoleCommandServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    /**
     * Handle command to seed roles.
     * Creates roles if they do not exist.
     * @param command SeedRolesCommand
     */
    @Override
    public void handle(SeedRolesCommand command) {
        Arrays.stream(Roles.values()).forEach(role -> {
            if(!roleRepository.existsByName(role)) {
                roleRepository.save(new Role(Roles.valueOf(role.name())));
            }
        });
    }
}

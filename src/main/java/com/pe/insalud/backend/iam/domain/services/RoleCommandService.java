package com.pe.insalud.backend.iam.domain.services;

import com.pe.insalud.backend.iam.domain.model.commands.SeedRolesCommand;

/**
 * Service interface for handling role commands.
 */
public interface RoleCommandService {
    /**
     * Handle the seed roles command.
     * @param command the SeedRolesCommand
     */
    void handle(SeedRolesCommand command);
}

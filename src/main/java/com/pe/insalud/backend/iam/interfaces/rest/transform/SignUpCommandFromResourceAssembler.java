package com.pe.insalud.backend.iam.interfaces.rest.transform;

import com.pe.insalud.backend.iam.domain.model.commands.SignUpCommand;
import com.pe.insalud.backend.iam.domain.model.entities.Role;
import com.pe.insalud.backend.iam.interfaces.rest.resources.SignUpResource;

import java.util.List;

/**
 * Assembler class to convert SignUpResource into SignUpCommand.
 */
public class SignUpCommandFromResourceAssembler {

    /**
     * Converts SignUpResource to SignUpCommand.
     *
     * @param resource the sign-up resource
     * @return SignUpCommand instance with mapped roles
     */
    public static SignUpCommand toCommandFromResource(SignUpResource resource) {
        List<Role> roles = resource.getRoles().stream()
                .map(Role::toRoleFromName) // Convert string role names to Role objects
                .toList();

        return new SignUpCommand(
                resource.getUsername(),
                resource.getPassword(),
                resource.getEmail(),
                roles
        );
    }
}

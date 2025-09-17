package com.pe.insalud.backend.iam.interfaces.rest.transform;

import com.pe.insalud.backend.iam.domain.model.aggregates.User;
import com.pe.insalud.backend.iam.domain.model.entities.Role;
import com.pe.insalud.backend.iam.interfaces.rest.resources.AuthenticatedUserResource;

import java.util.Set;
import java.util.stream.Collectors;

public class AuthenticatedUserResourceFromEntityAssembler {
    public static AuthenticatedUserResource toResourceFromEntity(User user, String token) {
        Set<String> roleNames = user.getRoles()
                .stream()
                .map(Role::getStringName)
                .collect(Collectors.toSet());
        return new AuthenticatedUserResource(user.getId(), user.getEmail(), token, roleNames);
    }
}
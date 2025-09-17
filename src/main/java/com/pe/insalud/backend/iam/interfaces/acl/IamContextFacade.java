package com.pe.insalud.backend.iam.interfaces.acl;

import com.pe.insalud.backend.iam.domain.model.commands.SignUpCommand;
import com.pe.insalud.backend.iam.domain.model.entities.Role;
import com.pe.insalud.backend.iam.domain.model.queries.GetUserByIdQuery;
import com.pe.insalud.backend.iam.domain.model.queries.GetUserByUsernameQuery;
import com.pe.insalud.backend.iam.domain.services.UserCommandService;
import com.pe.insalud.backend.iam.domain.services.UserQueryService;
import org.apache.logging.log4j.util.Strings;

import java.util.ArrayList;
import java.util.List;

public class IamContextFacade {

    private final UserCommandService userCommandService;
    private final UserQueryService userQueryService;

    // Constructor with dependencies injected
    public IamContextFacade(UserCommandService userCommandService, UserQueryService userQueryService) {
        this.userCommandService = userCommandService;
        this.userQueryService = userQueryService;
    }

    // Create user with default role
    public Long createUser(String username, String password) {
        String email = username + "@example.com"; // temporary email
        var signUpCommand = new SignUpCommand(
                username,
                password,
                email,
                List.of(Role.getDefaultRole())
        );
        var result = userCommandService.handle(signUpCommand);
        return result.map(u -> u.getId()).orElse(0L);
    }

    // Create user with specified roles
    public Long createUser(String username, String password, List<String> roleNames) {
        String email = username + "@example.com"; // temporary email
        List<Role> roles = roleNames != null
                ? roleNames.stream().map(Role::toRoleFromName).toList()
                : new ArrayList<>();

        var signUpCommand = new SignUpCommand(
                username,
                password,
                email,
                roles
        );
        var result = userCommandService.handle(signUpCommand);
        return result.map(u -> u.getId()).orElse(0L);
    }

    // Fetch user ID by username
    public Long fetchUserIdByUsername(String username) {
        var getUserByUsernameQuery = new GetUserByUsernameQuery(username);
        var result = userQueryService.handle(getUserByUsernameQuery);
        return result.map(u -> u.getId()).orElse(0L);
    }

    // Fetch username by user ID
    public String fetchUsernameByUserId(Long userId) {
        var getUserByIdQuery = new GetUserByIdQuery(userId);
        var result = userQueryService.handle(getUserByIdQuery);
        return result.map(u -> u.getUsername()).orElse(Strings.EMPTY);
    }
}

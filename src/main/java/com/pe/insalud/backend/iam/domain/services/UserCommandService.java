package com.pe.insalud.backend.iam.domain.services;

import com.pe.insalud.backend.iam.domain.model.aggregates.User;
import com.pe.insalud.backend.iam.domain.model.commands.SignInCommand;
import com.pe.insalud.backend.iam.domain.model.commands.SignUpCommand;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.Optional;

/**
 * Service interface for handling user commands.
 */
public interface UserCommandService {
    /**
     * Handle sign in command.
     * @param command the SignInCommand
     * @return optional pair of User and token string
     */
    Optional<ImmutablePair<User, String>> handle(SignInCommand command);

    /**
     * Handle sign up command.
     * @param command the SignUpCommand
     * @return optional User entity
     */
    Optional<User> handle(SignUpCommand command);
}

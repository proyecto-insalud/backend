package com.pe.insalud.backend.iam.application.internal.commandservices;

import com.pe.insalud.backend.iam.application.internal.outboundservices.hashing.HashingService;
import com.pe.insalud.backend.iam.application.internal.outboundservices.tokens.TokenService;
import com.pe.insalud.backend.iam.domain.model.aggregates.User;
import com.pe.insalud.backend.iam.domain.model.commands.SignInCommand;
import com.pe.insalud.backend.iam.domain.model.commands.SignUpCommand;
import com.pe.insalud.backend.iam.domain.services.UserCommandService;
import com.pe.insalud.backend.iam.infrastructure.persistence.jpa.repositories.RoleRepository;
import com.pe.insalud.backend.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import com.pe.insalud.backend.paciente.domain.model.aggregates.Paciente;
import com.pe.insalud.backend.paciente.infrastructure.persistence.jpa.repositories.PacienteRepository;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * User command service implementation
 * Handles user sign in and sign up commands.
 */
@Service
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;
    private final HashingService hashingService;
    private final TokenService tokenService;
    private final RoleRepository roleRepository;
    private final PacienteRepository pacienteRepository;

    public UserCommandServiceImpl(UserRepository userRepository, HashingService hashingService,
                                  TokenService tokenService, RoleRepository roleRepository,
                                  PacienteRepository pacienteRepository) {
        this.userRepository = userRepository;
        this.hashingService = hashingService;
        this.tokenService = tokenService;
        this.roleRepository = roleRepository;
        this.pacienteRepository = pacienteRepository;
    }

    /**
     * Handle sign-in command
     * @param command SignInCommand with username and password
     * @return Optional of user and token pair
     */
    @Override
    public Optional<ImmutablePair<User, String>> handle(SignInCommand command) {
        var user = userRepository.findByUsername(command.username());
        if (user.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        if (!hashingService.matches(command.password(), user.get().getPassword())) {
            throw new RuntimeException("Invalid password");
        }
        var token = tokenService.generateToken(user.get().getUsername());
        return Optional.of(ImmutablePair.of(user.get(), token));
    }

    /**
     * Handle sign-up command
     * @param command SignUpCommand with user info and roles
     * @return Optional of created user
     */
    @Override
    public Optional<User> handle(SignUpCommand command) {
        // Check if username or email exists
        if (userRepository.existsByUsername(command.getUsername()) ||
                userRepository.existsByEmail(command.getEmail())) {
            throw new RuntimeException("Username or email already exists");
        }

        // Fetch roles from DB
        var roles = command.getRoles().stream()
                .map(role -> roleRepository.findByName(role.getName())
                        .orElseThrow(() -> new RuntimeException("Role name not found")))
                .toList();

        // Encode password
        String hashedPassword = hashingService.encode(command.getPassword());

        var user = new User(
                command.getUsername(),
                hashedPassword,
                command.getEmail(),
                roles
        );

        // If user has ROLE_PACIENTE, create associated Paciente
        boolean isPaciente = roles.stream()
                .anyMatch(r -> r.getStringName().equals("ROLE_PACIENTE"));

        if (isPaciente) {
            var encodedPassword = hashingService.encode(command.getPassword());
            var paciente = new Paciente(
                    command.getUsername(),
                    command.getEmail(),
                    encodedPassword
            );
            pacienteRepository.save(paciente);
            user.setPaciente(paciente);
        }

        // Save user
        userRepository.save(user);

        return Optional.of(user);
    }

}

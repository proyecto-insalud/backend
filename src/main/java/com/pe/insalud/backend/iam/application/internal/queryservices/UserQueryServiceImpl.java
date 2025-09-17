package com.pe.insalud.backend.iam.application.internal.queryservices;

import com.pe.insalud.backend.iam.domain.model.aggregates.User;
import com.pe.insalud.backend.iam.domain.model.queries.GetAllUsersQuery;
import com.pe.insalud.backend.iam.domain.model.queries.GetUserByIdQuery;
import com.pe.insalud.backend.iam.domain.model.queries.GetUserByUsernameQuery;
import com.pe.insalud.backend.iam.domain.services.UserQueryService;
import com.pe.insalud.backend.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of UserQueryService.
 */
@Service
public class UserQueryServiceImpl implements UserQueryService {
    private final UserRepository userRepository;

    /**
     * Constructor with UserRepository.
     */
    public UserQueryServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Get all users.
     */
    @Override
    public List<User> handle(GetAllUsersQuery query) {
        return userRepository.findAll();
    }

    /**
     * Get user by id.
     */
    @Override
    public Optional<User> handle(GetUserByIdQuery query) {
        return userRepository.findById(query.userId());
    }

    /**
     * Get user by username.
     */
    @Override
    public Optional<User> handle(GetUserByUsernameQuery query) {
        return userRepository.findByUsername(query.username());
    }
}

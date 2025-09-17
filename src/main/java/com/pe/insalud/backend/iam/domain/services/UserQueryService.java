package com.pe.insalud.backend.iam.domain.services;

import com.pe.insalud.backend.iam.domain.model.aggregates.User;
import com.pe.insalud.backend.iam.domain.model.queries.GetAllUsersQuery;
import com.pe.insalud.backend.iam.domain.model.queries.GetUserByIdQuery;
import com.pe.insalud.backend.iam.domain.model.queries.GetUserByUsernameQuery;

import java.util.List;
import java.util.Optional;

/**
 * Service interface for handling user queries.
 */
public interface UserQueryService {
    /**
     * Handle query to get all users.
     * @param query GetAllUsersQuery
     * @return list of User entities
     */
    List<User> handle(GetAllUsersQuery query);

    /**
     * Handle query to get a user by id.
     * @param query GetUserByIdQuery
     * @return optional User entity
     */
    Optional<User> handle(GetUserByIdQuery query);

    /**
     * Handle query to get a user by username.
     * @param query GetUserByUsernameQuery
     * @return optional User entity
     */
    Optional<User> handle(GetUserByUsernameQuery query);
}

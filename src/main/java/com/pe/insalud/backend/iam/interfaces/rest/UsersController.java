package com.pe.insalud.backend.iam.interfaces.rest;

import com.pe.insalud.backend.iam.domain.model.queries.GetAllUsersQuery;
import com.pe.insalud.backend.iam.domain.model.queries.GetUserByIdQuery;
import com.pe.insalud.backend.iam.domain.services.UserQueryService;
import com.pe.insalud.backend.iam.interfaces.rest.resources.UserResource;
import com.pe.insalud.backend.iam.interfaces.rest.transform.UserResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * UsersController
 *
 * REST controller to handle user-related queries.
 * Exposes endpoints to retrieve all users and get a user by ID.
 */
@RestController
@RequestMapping(value = "/api/v1/users", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Users", description = "Available User Endpoints")
public class UsersController {

    private final UserQueryService userQueryService;

    public UsersController(UserQueryService userQueryService) {
        this.userQueryService = userQueryService;
    }

    /**
     * GET /api/v1/users
     *
     * Returns a list of all users in the system.
     */
    @GetMapping
    @Operation(summary = "Get all users", description = "Get all the users available in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Users retrieved successfully."),
            @ApiResponse(responseCode = "401", description = "Unauthorized.")})
    public ResponseEntity<List<UserResource>> getAllUsers() {
        var getAllUsersQuery = new GetAllUsersQuery(); // Construir el query
        var users = userQueryService.handle(getAllUsersQuery); // Ejecutar query
        var userResources = users.stream()
                .map(UserResourceFromEntityAssembler::toResourceFromEntity) // Convertir entidades a DTO
                .toList();
        return ResponseEntity.ok(userResources); // Devolver respuesta con lista de usuarios
    }

    /**
     * GET /api/v1/users/{userId}
     *
     * Returns the user that matches the provided ID.
     */
    @GetMapping(value = "/{userId}")
    @Operation(summary = "Get user by id", description = "Get the user with the given id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User retrieved successfully."),
            @ApiResponse(responseCode = "404", description = "User not found."),
            @ApiResponse(responseCode = "401", description = "Unauthorized.")})
    public ResponseEntity<UserResource> getUserById(@PathVariable Long userId) {
        var getUserByIdQuery = new GetUserByIdQuery(userId); // Crear query con el ID
        var user = userQueryService.handle(getUserByIdQuery); // Ejecutar query
        if (user.isEmpty()) {
            return ResponseEntity.notFound().build(); // Retornar 404 si no se encuentra
        }
        var userResource = UserResourceFromEntityAssembler.toResourceFromEntity(user.get()); // Convertir a DTO
        return ResponseEntity.ok(userResource); // Retornar usuario encontrado
    }
}

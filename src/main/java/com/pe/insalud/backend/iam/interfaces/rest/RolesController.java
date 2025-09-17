package com.pe.insalud.backend.iam.interfaces.rest;

import com.pe.insalud.backend.iam.domain.model.queries.GetAllRolesQuery;
import com.pe.insalud.backend.iam.domain.services.RoleQueryService;
import com.pe.insalud.backend.iam.interfaces.rest.resources.RoleResource;
import com.pe.insalud.backend.iam.interfaces.rest.transform.RoleResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * RolesController
 *
 * Handles HTTP requests related to role queries (e.g., get all roles).
 */
@RestController
@RequestMapping(value = "/ap/v1/roles", produces = MediaType.APPLICATION_JSON_VALUE) // Nota: "ap" podría ser un typo de "api"
@Tag(name = "Roles", description = "Available Role Endpoints")
public class RolesController {

    private final RoleQueryService roleQueryService;

    public RolesController(RoleQueryService roleQueryService) {
        this.roleQueryService = roleQueryService;
    }

    /**
     * Endpoint to retrieve all available roles in the system.
     *
     * @return list of RoleResource
     */
    @GetMapping
    @Operation(summary = "Get all roles", description = "Get all the roles available in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Roles retrieved successfully."),
            @ApiResponse(responseCode = "401", description = "Unauthorized.")})
    public ResponseEntity<List<RoleResource>> getAllRoles() {
        var getAllRolesQuery = new GetAllRolesQuery(); // Crear el query
        var roles = roleQueryService.handle(getAllRolesQuery); // Ejecutar query en el servicio
        var roleResources = roles.stream()
                .map(RoleResourceFromEntityAssembler::toResourceFromEntity) // Convertir entidades a DTO
                .toList();
        return ResponseEntity.ok(roleResources); // Retornar lista de roles
    }
}

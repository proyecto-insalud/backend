package com.pe.insalud.backend.iam.interfaces.rest;

import com.pe.insalud.backend.iam.domain.services.UserCommandService;
import com.pe.insalud.backend.iam.interfaces.rest.resources.AuthenticatedUserResource;
import com.pe.insalud.backend.iam.interfaces.rest.resources.SignInResource;
import com.pe.insalud.backend.iam.interfaces.rest.resources.SignUpResource;
import com.pe.insalud.backend.iam.interfaces.rest.resources.UserResource;
import com.pe.insalud.backend.iam.interfaces.rest.transform.AuthenticatedUserResourceFromEntityAssembler;
import com.pe.insalud.backend.iam.interfaces.rest.transform.SignInCommandFromResourceAssembler;
import com.pe.insalud.backend.iam.interfaces.rest.transform.SignUpCommandFromResourceAssembler;
import com.pe.insalud.backend.iam.interfaces.rest.transform.UserResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * AuthenticationController
 *
 * Exposes authentication endpoints:
 * - Sign in (login)
 * - Sign up (register)
 */
@RestController
@RequestMapping(value = "/api/v1/authentication", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Authentication", description = "Available Authentication Endpoints")
public class AuthenticationController {

    private final UserCommandService userCommandService;

    public AuthenticationController(UserCommandService userCommandService) {
        this.userCommandService = userCommandService;
    }

    /**
     * Sign-in endpoint to authenticate a user and return a JWT token.
     *
     * @param signInResource request body with username and password
     * @return authenticated user data and token, or 404 if not found
     */
    @PostMapping("/sign-in")
    @Operation(summary = "Sign-in", description = "Sign-in with the provided credentials.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User authenticated successfully."),
            @ApiResponse(responseCode = "404", description = "User not found.")})
    public ResponseEntity<AuthenticatedUserResource> signIn(@RequestBody SignInResource signInResource) {
        var signInCommand = SignInCommandFromResourceAssembler.toCommandFromResource(signInResource);
        var authenticatedUser = userCommandService.handle(signInCommand);

        // Si el usuario no fue autenticado
        if (authenticatedUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // Convertir a recurso y retornar con el token
        var authenticatedUserResource = AuthenticatedUserResourceFromEntityAssembler.toResourceFromEntity(
                authenticatedUser.get().getLeft(),
                authenticatedUser.get().getRight()
        );
        return ResponseEntity.ok(authenticatedUserResource);
    }

    /**
     * Sign-up endpoint to register a new user.
     *
     * @param signUpResource request body with username, password, email and optional roles
     * @return user info if created successfully, or 400 if failed
     */
    @PostMapping("/sign-up")
    @Operation(summary = "Sign-up", description = "Sign-up with the provided credentials.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created successfully."),
            @ApiResponse(responseCode = "400", description = "Bad request.")})
    public ResponseEntity<UserResource> signUp(@RequestBody SignUpResource signUpResource) {
        var signUpCommand = SignUpCommandFromResourceAssembler.toCommandFromResource(signUpResource);
        var user = userCommandService.handle(signUpCommand);

        // Si la creación falla
        if (user.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        // Convertir y retornar el recurso del usuario creado
        var userResource = UserResourceFromEntityAssembler.toResourceFromEntity(user.get());
        return new ResponseEntity<>(userResource, HttpStatus.CREATED);
    }
}

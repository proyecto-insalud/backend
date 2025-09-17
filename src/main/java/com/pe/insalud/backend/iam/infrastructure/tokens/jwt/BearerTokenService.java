package com.pe.insalud.backend.iam.infrastructure.tokens.jwt;

import com.pe.insalud.backend.iam.application.internal.outboundservices.tokens.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;

/**
 * Interface for JWT token service.
 * Extends TokenService.
 */
public interface BearerTokenService extends TokenService {

    /**
     * Get JWT token from HTTP request.
     * @param request the HTTP request
     * @return the JWT token
     */
    String getBearerTokenFrom(HttpServletRequest request);

    /**
     * Generate JWT token from authentication.
     * @param authentication the authentication object
     * @return the JWT token
     */
    String generateToken(Authentication authentication);
}

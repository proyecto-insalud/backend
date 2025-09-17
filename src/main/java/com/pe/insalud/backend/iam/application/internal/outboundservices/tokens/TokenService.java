package com.insalud.backend.iam.application.internal.outboundservices.tokens;

/**
 * Service for generating and validating tokens.
 */
public interface TokenService {

    /**
     * Generate a token for a username.
     * @param username the username
     * @return generated token as String
     */
    String generateToken(String username);

    /**
     * Extract username from a token.
     * @param token the token
     * @return username as String
     */
    String getUsernameFromToken(String token);

    /**
     * Validate the token.
     * @param token the token
     * @return true if valid, false otherwise
     */
    boolean validateToken(String token);
}

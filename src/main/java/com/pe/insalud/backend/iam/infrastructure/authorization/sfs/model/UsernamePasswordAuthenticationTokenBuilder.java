package com.pe.insalud.backend.iam.infrastructure.authorization.sfs.model;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

/**
 * Builds UsernamePasswordAuthenticationToken for user authentication.
 */
public class UsernamePasswordAuthenticationTokenBuilder {

    /**
     * Build token using UserDetails and HttpServletRequest.
     */
    public static UsernamePasswordAuthenticationToken build(UserDetails principal, HttpServletRequest request) {
        var token = new UsernamePasswordAuthenticationToken(principal, null, principal.getAuthorities());
        token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        return token;
    }
}

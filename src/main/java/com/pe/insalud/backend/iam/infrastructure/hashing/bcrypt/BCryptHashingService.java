package com.pe.insalud.backend.iam.infrastructure.hashing.bcrypt;

import com.pe.insalud.backend.iam.application.internal.outboundservices.hashing.HashingService;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Marker interface for BCrypt hashing service.
 * Combines HashingService and PasswordEncoder interfaces.
 */
public interface BCryptHashingService extends HashingService, PasswordEncoder {
}

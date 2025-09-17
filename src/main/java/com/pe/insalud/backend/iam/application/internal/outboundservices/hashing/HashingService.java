package com.pe.insalud.backend.iam.application.internal.outboundservices.hashing;

/**
 * Service for encoding and matching passwords.
 */
public interface HashingService {
    /**
     * Encode a raw password.
     * @param rawPassword the password to encode
     * @return encoded password as String
     */
    String encode(CharSequence rawPassword);

    /**
     * Check if raw password matches encoded password.
     * @param rawPassword the raw password
     * @param encodedPassword the encoded password
     * @return true if matches, false otherwise
     */
    boolean matches(CharSequence rawPassword, String encodedPassword);
}

package com.pe.insalud.backend.paciente.domain.model.valueobjects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Email;
import java.util.Objects;

@Embeddable
public class EmailAddress {

    @Email
    @Column(name = "email", nullable = false, unique = true)
    private String address;

    // Empty constructor required by JPA
    protected EmailAddress() { this.address = ""; }

    // Constructor with basic validation
    public EmailAddress(String address) {
        if (address == null || address.isBlank()) {
            throw new IllegalArgumentException("Email address cannot be null or blank");
        }
        this.address = address;
    }

    // Getter for the email address
    public String address() {
        return address;
    }

    // Returns the email as string
    @Override
    public String toString() {
        return address;
    }

    // Checks if two email addresses are equal
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmailAddress that = (EmailAddress) o;
        return Objects.equals(address, that.address);
    }

    // Generates hash code based on email address
    @Override
    public int hashCode() {
        return Objects.hash(address);
    }
}

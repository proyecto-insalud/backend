package com.pe.insalud.backend.iam.infrastructure.persistence.jpa.repositories;

import com.pe.insalud.backend.iam.domain.model.entities.Role;
import com.pe.insalud.backend.iam.domain.model.valueobjects.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for Role entity.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    /**
     * Find role by its name.
     * @param name Role name
     * @return Optional Role
     */
    Optional<Role> findByName(Roles name);

    /**
     * Check if role exists by its name.
     * @param name Role name
     * @return true if exists, false otherwise
     */
    boolean existsByName(Roles name);

}

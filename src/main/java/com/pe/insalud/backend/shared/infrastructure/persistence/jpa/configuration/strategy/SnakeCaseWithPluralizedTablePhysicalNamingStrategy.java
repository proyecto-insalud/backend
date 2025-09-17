package com.pe.insalud.backend.shared.infrastructure.persistence.jpa.configuration.strategy;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategy;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

import static io.github.encryptorcode.pluralize.Pluralize.pluralize;

/**
 * Estrategia para convertir nombres a snake_case y pluralizar tablas.
 */
public class SnakeCaseWithPluralizedTablePhysicalNamingStrategy implements PhysicalNamingStrategy {

    @Override
    public Identifier toPhysicalCatalogName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
        // Convierte el nombre del catálogo a snake_case
        return this.toSnakeCase(identifier);
    }

    @Override
    public Identifier toPhysicalSchemaName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
        // Convierte el nombre del esquema a snake_case
        return this.toSnakeCase(identifier);
    }

    @Override
    public Identifier toPhysicalTableName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
        // Convierte el nombre de la tabla a snake_case y lo pluraliza
        return this.toSnakeCase(this.toPlural(identifier));
    }

    @Override
    public Identifier toPhysicalSequenceName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
        // Convierte el nombre de la secuencia a snake_case
        return this.toSnakeCase(identifier);
    }

    @Override
    public Identifier toPhysicalColumnName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
        // Convierte el nombre de la columna a snake_case
        return this.toSnakeCase(identifier);
    }

    /**
     * Convierte un nombre a snake_case.
     *
     * @param identifier nombre original
     * @return nombre en snake_case
     */
    private Identifier toSnakeCase(final Identifier identifier) {
        if (identifier == null) return null;

        final String regex = "([a-z])([A-Z])";
        final String replacement = "$1_$2";
        final String newName = identifier.getText()
                .replaceAll(regex, replacement)
                .toLowerCase();
        return Identifier.toIdentifier(newName);
    }

    /**
     * Pluraliza un nombre.
     *
     * @param identifier nombre original
     * @return nombre pluralizado
     */
    private Identifier toPlural(final Identifier identifier) {
        final String newName = pluralize(identifier.getText());
        return Identifier.toIdentifier(newName);
    }
}

package com.pe.insalud.backend.shared.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

/**
 * Clase base para entidades con capacidades de auditoría.
 * Extiende AbstractAggregateRoot para manejar eventos de dominio.
 *
 * @param <T> tipo de la raíz del agregado
 */
@Getter
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public class AuditableAbstractAggregateRoot<T extends AbstractAggregateRoot<T>> extends AbstractAggregateRoot<T> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Date createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private Date updatedAt;

    /**
     * Registra un evento de dominio para ser publicado.
     *
     * @param event el evento de dominio a registrar
     */
    public void addDomainEvent(Object event) {
        registerEvent(event);
    }
}

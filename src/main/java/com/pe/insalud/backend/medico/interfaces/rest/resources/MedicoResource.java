package com.pe.insalud.backend.medico.interfaces.rest.resources;

import java.util.List;

public record MedicoResource(Long id, String nombre, List<String> especialidades, String estado) {}

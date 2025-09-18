package com.pe.insalud.backend.paciente.interfaces.rest;

import com.pe.insalud.backend.paciente.domain.model.commands.CreatePacienteCommand;
import com.pe.insalud.backend.paciente.domain.model.queries.GetAllPacientesQuery;
import com.pe.insalud.backend.paciente.domain.model.queries.GetPacienteByIdQuery;
import com.pe.insalud.backend.paciente.domain.services.PacienteCommandService;
import com.pe.insalud.backend.paciente.domain.services.PacienteQueryService;
import com.pe.insalud.backend.paciente.interfaces.rest.resources.CreatePacienteResource;
import com.pe.insalud.backend.paciente.interfaces.rest.resources.PacienteResource;
import com.pe.insalud.backend.paciente.interfaces.rest.transform.CreatePacienteCommandFromResourceAssembler;
import com.pe.insalud.backend.paciente.interfaces.rest.transform.PacienteResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/pacientes", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Pacientes", description = "Endpoints for Pacientes")
public class PacientesController {

    private final PacienteCommandService commandService;
    private final PacienteQueryService queryService;

    public PacientesController(PacienteCommandService commandService, PacienteQueryService queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    // Endpoint to create a new paciente
    @PostMapping
    @Operation(summary = "Create a new paciente")
    public ResponseEntity<PacienteResource> createPaciente(@RequestBody CreatePacienteResource resource) {
        // Convert resource to command and execute service
        CreatePacienteCommand cmd = CreatePacienteCommandFromResourceAssembler.toCommandFromResource(resource);
        var opt = commandService.handle(cmd);
        if (opt.isEmpty()) return ResponseEntity.badRequest().build();

        // Convert created paciente to resource and return
        var paciente = opt.get();
        var pacienteResource = PacienteResourceFromEntityAssembler.toResourceFromEntity(paciente);
        return new ResponseEntity<>(pacienteResource, HttpStatus.CREATED);
    }

    // Endpoint to get paciente by ID
    @GetMapping("/{id}")
    public ResponseEntity<PacienteResource> getById(@PathVariable Long id) {
        var opt = queryService.handle(new GetPacienteByIdQuery(id));
        if (opt.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(PacienteResourceFromEntityAssembler.toResourceFromEntity(opt.get()));
    }

    // Endpoint to get all pacientes
    @GetMapping
    public ResponseEntity<List<PacienteResource>> getAll() {
        var list = queryService.handle(new GetAllPacientesQuery());
        var resources = list.stream()
                .map(PacienteResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources); // always returns 200
    }
}

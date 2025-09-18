package com.pe.insalud.backend.atencion.interfaces.rest;

import com.pe.insalud.backend.atencion.domain.model.commands.CreateAtencionCommand;
import com.pe.insalud.backend.atencion.domain.model.commands.DeleteAtencionCommand;
import com.pe.insalud.backend.atencion.domain.model.commands.UpdateAtencionCommand;
import com.pe.insalud.backend.atencion.domain.model.queries.GetAllAtencionesQuery;
import com.pe.insalud.backend.atencion.domain.model.queries.GetAtencionesByPacienteQuery;
import com.pe.insalud.backend.atencion.domain.services.AtencionCommandService;
import com.pe.insalud.backend.atencion.domain.services.AtencionQueryService;
import com.pe.insalud.backend.atencion.interfaces.rest.resources.AtencionResource;
import com.pe.insalud.backend.atencion.interfaces.rest.resources.CreateAtencionResource;
import com.pe.insalud.backend.atencion.interfaces.rest.resources.UpdateAtencionResource;
import com.pe.insalud.backend.atencion.interfaces.rest.transform.AtencionResourceFromEntityAssembler;
import com.pe.insalud.backend.atencion.interfaces.rest.transform.CreateAtencionCommandFromResourceAssembler;
import com.pe.insalud.backend.atencion.interfaces.rest.transform.UpdateAtencionCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/atenciones")
@Tag(name = "Atenciones", description = "Endpoints to manage atenciones")
public class AtencionesController {

    private final AtencionCommandService commandService;
    private final AtencionQueryService queryService;

    public AtencionesController(AtencionCommandService commandService, AtencionQueryService queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    // Create a new atención
    @PostMapping
    @Operation(summary = "Create a new atención (only ADMIN)")
    public ResponseEntity<AtencionResource> create(@RequestBody CreateAtencionResource resource) {
        CreateAtencionCommand command = CreateAtencionCommandFromResourceAssembler.toCommandFromResource(resource);
        var opt = commandService.handle(command);
        if (opt.isEmpty()) return ResponseEntity.badRequest().build();
        return new ResponseEntity<>(AtencionResourceFromEntityAssembler.toResourceFromEntity(opt.get()), HttpStatus.CREATED);
    }

    // Get all atenciones
    @GetMapping
    @Operation(summary = "Get all atenciones (only ADMIN)")
    public List<AtencionResource> getAll() {
        return queryService.handle(new GetAllAtencionesQuery())
                .stream().map(AtencionResourceFromEntityAssembler::toResourceFromEntity).toList();
    }

    // Get atenciones of the authenticated patient
    @GetMapping("/mias")
    @Operation(summary = "Get atenciones of the authenticated patient")
    public List<AtencionResource> getMias(Authentication authentication) {
        String email = authentication.getName();  // Get email from JWT
        Long pacienteId = queryService.findPacienteIdByEmail(email);  // Get patient by email
        return queryService.handle(new GetAtencionesByPacienteQuery(pacienteId))
                .stream().map(AtencionResourceFromEntityAssembler::toResourceFromEntity).toList();
    }

    // Update an existing atención
    @PutMapping("/{id}")
    @Operation(summary = "Update an existing atención (only ADMIN)")
    public ResponseEntity<AtencionResource> update(@PathVariable Long id, @RequestBody UpdateAtencionResource resource) {
        UpdateAtencionCommand command = UpdateAtencionCommandFromResourceAssembler.toCommandFromResource(id, resource);
        var opt = commandService.handle(command);
        if (opt.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(AtencionResourceFromEntityAssembler.toResourceFromEntity(opt.get()));
    }

    // Cancel an atención (delete)
    @DeleteMapping("/{id}")
    @Operation(summary = "Cancel an atención (only ADMIN)")
    public List<AtencionResource> delete(@PathVariable Long id) {
        commandService.handle(new DeleteAtencionCommand(id));
        return queryService.handle(new GetAllAtencionesQuery())
                .stream().map(AtencionResourceFromEntityAssembler::toResourceFromEntity).toList();
    }
}

package com.pe.insalud.backend.medico.interfaces.rest;

import com.pe.insalud.backend.medico.domain.model.commands.CreateMedicoCommand;
import com.pe.insalud.backend.medico.domain.model.queries.GetAllMedicosQuery;
import com.pe.insalud.backend.medico.domain.model.queries.GetMedicoByIdQuery;
import com.pe.insalud.backend.medico.domain.model.queries.GetMedicosByEspecialidadQuery;
import com.pe.insalud.backend.medico.domain.services.MedicoCommandService;
import com.pe.insalud.backend.medico.domain.services.MedicoQueryService;
import com.pe.insalud.backend.medico.interfaces.rest.resources.CreateMedicoResource;
import com.pe.insalud.backend.medico.interfaces.rest.resources.MedicoResource;
import com.pe.insalud.backend.medico.interfaces.rest.transform.CreateMedicoCommandFromResourceAssembler;
import com.pe.insalud.backend.medico.interfaces.rest.transform.MedicoResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/medicos", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Medicos", description = "Endpoints for Medicos")
public class MedicosController {

    private final MedicoCommandService commandService;
    private final MedicoQueryService queryService;

    // Constructor injecting the services
    public MedicosController(MedicoCommandService commandService, MedicoQueryService queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    // Endpoint to create a new medico (ADMIN only)
    @PostMapping
    @Operation(summary = "Create a new medico (ADMIN)")
    public ResponseEntity<MedicoResource> createMedico(@RequestBody CreateMedicoResource resource) {
        // Convert the resource to a command and handle it
        CreateMedicoCommand cmd = CreateMedicoCommandFromResourceAssembler.toCommandFromResource(resource);
        var opt = commandService.handle(cmd);
        if (opt.isEmpty()) return ResponseEntity.badRequest().build(); // If not created, return bad request
        var medico = opt.get();
        var res = MedicoResourceFromEntityAssembler.toResourceFromEntity(medico);
        return new ResponseEntity<>(res, HttpStatus.CREATED); // Return created response
    }

    // Endpoint to get a medico by its ID
    @GetMapping("/{id}")
    public ResponseEntity<MedicoResource> getById(@PathVariable Long id) {
        var opt = queryService.handle(new GetMedicoByIdQuery(id));
        if (opt.isEmpty()) return ResponseEntity.notFound().build(); // Return not found if no medico found
        return ResponseEntity.ok(MedicoResourceFromEntityAssembler.toResourceFromEntity(opt.get())); // Return medico resource
    }

    // Endpoint to get all medicos, with optional filtering by especialidad
    @GetMapping
    public ResponseEntity<List<MedicoResource>> getAll(@RequestParam(required = false) String especialidad) {
        if (especialidad != null && !especialidad.isBlank()) {
            // If a specialty is provided, filter medicos by that specialty
            var list = queryService.handle(new GetMedicosByEspecialidadQuery(
                    com.pe.insalud.backend.medico.domain.model.valueobjects.Especialidad.valueOf(especialidad.toUpperCase())
            ));
            if (list.isEmpty()) return ResponseEntity.notFound().build(); // Return not found if no medicos match
            var resources = list.stream().map(MedicoResourceFromEntityAssembler::toResourceFromEntity).toList();
            return ResponseEntity.ok(resources); // Return list of medicos matching the specialty
        }

        // If no specialty, return all medicos
        var list = queryService.handle(new GetAllMedicosQuery());
        if (list.isEmpty()) return ResponseEntity.notFound().build(); // Return not found if no medicos
        var resources = list.stream().map(MedicoResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(resources); // Return list of all medicos
    }

    // Endpoint to get medicos by their state (ACTIVE/INACTIVE)
    @GetMapping("/estado/{estado}")
    @Operation(summary = "Get medicos by estado (custom query using @Query)")
    public ResponseEntity<List<MedicoResource>> getByEstado(@PathVariable String estado) {
        var list = queryService.getMedicosPorEstado(estado.toUpperCase()); // Query medicos by state

        if (list.isEmpty()) return ResponseEntity.notFound().build(); // Return not found if no medicos

        var resources = list.stream()
                .map(MedicoResourceFromEntityAssembler::toResourceFromEntity)
                .toList();

        return ResponseEntity.ok(resources); // Return medicos matching the state
    }
}

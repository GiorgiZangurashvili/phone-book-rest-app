package dev.omedia.boot.controller;

import dev.omedia.boot.dto.JuridicalPersonDTO;
import dev.omedia.boot.service.JuridicalPersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/juriicalPersons")
@RequiredArgsConstructor
public class JuridicalPersonController {

    private final JuridicalPersonService service;

    @GetMapping
    @Operation(summary = "Retrieve all JuridicalPerson from database",
            responses = {@ApiResponse(responseCode = "204", description = "Not a single JuridicalPerson is stored in database"),
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved all JuridicalPersons")})
    public ResponseEntity<Collection<JuridicalPersonDTO>> findAll() {
        Collection<JuridicalPersonDTO> all = service.findALl();
        if (all.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(all);
        } else {
            return ResponseEntity.of(Optional.of(all));
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retrieve NaturalPerson with specified id",
            responses = {@ApiResponse(responseCode = "404", description = "JuridicalPerson with specified id was not found"),
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved JuridicalPerson with specified id")})
    public ResponseEntity<JuridicalPersonDTO> findById(
            @PathVariable(name = "id") long id
    ) {
        return ResponseEntity.of(service.findById(id));
    }

    @PostMapping
    @Operation(summary = "Save a new JuridicalPerson to database",
            responses = {@ApiResponse(responseCode = "400", description = "Some validations failed"),
                    @ApiResponse(responseCode = "201", description = "Successfully created a new JuridicalPerson (resource)")})
    public ResponseEntity<JuridicalPersonDTO> save(@Valid @RequestBody JuridicalPersonDTO juridicalPersonDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.save(juridicalPersonDTO));
    }

    @PutMapping("/{juridicalPersonId}/{naturalPersonId}")
    @Operation(summary = "Merges NaturalPerson as Contactor on JuridicalPerson",
        responses = {@ApiResponse(responseCode = "404", description = "Either NaturalPerson or JuridicalPerson with given id's was not found"),
                    @ApiResponse(responseCode = "400", description = "Some validations failed"),
                    @ApiResponse(responseCode = "200", description = "Successfylly merged NaturalPerson on JuridicalPerson")})
    public ResponseEntity<JuridicalPersonDTO> merge(
            @PathVariable(name = "juridicalPersonId") long juridicalPersonId,
            @PathVariable(name = "naturalPersonId") long naturalPersonId
    ) {
        return ResponseEntity.of(Optional.of(service.merge(juridicalPersonId, naturalPersonId)));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update already existing JuridicalPerson",
            responses = {@ApiResponse(responseCode = "404", description = "JuridicalPerson with specified id was not found"),
                    @ApiResponse(responseCode = "200", description = "Successfully updated JuridicalPerson with specified id"),
                    @ApiResponse(responseCode = "400", description = "Some validations failed")})
    public ResponseEntity<JuridicalPersonDTO> update(
            @PathVariable(name = "id") long id,
            @Valid @RequestBody JuridicalPersonDTO juridicalPersonDTO
    ) {
        return ResponseEntity.of(
                Optional.of(service.update(id, juridicalPersonDTO))
        );
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletes JuridicalPerson with specified id from database",
            responses = {@ApiResponse(responseCode = "404", description = "JuridicalPerson with specified id was not found"),
                    @ApiResponse(responseCode = "204", description = "JuridicalPerson was successfully deleted")})
    public void delete(
            @PathVariable(name = "id") long id
    ) {
        service.delete(id);
    }
}

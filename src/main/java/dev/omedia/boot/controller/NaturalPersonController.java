package dev.omedia.boot.controller;

import dev.omedia.boot.dto.NaturalPersonDTO;
import dev.omedia.boot.service.NaturalPersonService;
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
@RequestMapping("/naturalPersons")
@RequiredArgsConstructor
public class NaturalPersonController {

    private final NaturalPersonService service;

    @GetMapping
    @Operation(summary = "Retrieve all NaturalPersons from database",
    responses = {@ApiResponse(responseCode = "204", description = "Not a single NaturalPerson is stored in database"),
                @ApiResponse(responseCode = "200", description = "Successfully retrieved all NaturalPersons")})
    public ResponseEntity<Collection<NaturalPersonDTO>> findAll() {
        Collection<NaturalPersonDTO> all = service.findALl();
        if (!all.isEmpty()) {
            return ResponseEntity.of(Optional.of(all));
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(all);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retrieve NaturalPerson with specified id",
    responses = {@ApiResponse(responseCode = "404", description = "NaturalPerson with specified id was not found"),
                @ApiResponse(responseCode = "200", description = "Successfully retrieved NaturalPerson with specified id")})
    public ResponseEntity<NaturalPersonDTO> findById(
            @PathVariable(name = "id") long id
    ) {
        return ResponseEntity.of(service.findById(id));
    }

    @PostMapping
    @Operation(summary = "Save a new NaturalPerson to database",
    responses = {@ApiResponse(responseCode = "400", description = "Some validations failed"),
                @ApiResponse(responseCode = "201", description = "Successfully created a new NaturalPerson (resource)")})
    public ResponseEntity<NaturalPersonDTO> save(@Valid @RequestBody NaturalPersonDTO naturalPersonDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.save(naturalPersonDTO));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update already existing NaturalPerson",
    responses = {@ApiResponse(responseCode = "404", description = "NaturalPerson with specified id was not found"),
                @ApiResponse(responseCode = "200", description = "Successfully updated NaturalPerson with specified id"),
                @ApiResponse(responseCode = "400", description = "Some validations failed")})
    public ResponseEntity<NaturalPersonDTO> update(
            @PathVariable(name = "id") long id,
            @Valid @RequestBody NaturalPersonDTO naturalPersonDTO) {
        return ResponseEntity.of(
                Optional.of(service.update(id, naturalPersonDTO))
                );
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletes NaturalPerson with specified id from database",
    responses = {@ApiResponse(responseCode = "404", description = "NaturalPerson with specified id was not found"),
                @ApiResponse(responseCode = "204", description = "NaturalPerson was successfully deleted")})
    public void delete(
            @PathVariable(name = "id") long id
    ) {
        service.delete(id);
    }
}

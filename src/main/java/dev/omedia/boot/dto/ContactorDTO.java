package dev.omedia.boot.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Schema(title = "Contactor", description = "Contactor data in phone_book database")
public class ContactorDTO {
    @EqualsAndHashCode.Include
    @Schema(description = "Contactor id")
    private long id;

    @NotNull(message = "Contactor name should not be null")
    @NotEmpty(message = "Contactor name should not be empty")
    @Schema(description = "Contactor name")
    private String name;

    @Schema(description = "Contactor comment")
    private String comment;

    @Schema(description = "Contactor birth date")
    private LocalDate birthDate;

    @Schema(description = "Contactor contacts")
    private Collection<ContactDTO> contacts;
}

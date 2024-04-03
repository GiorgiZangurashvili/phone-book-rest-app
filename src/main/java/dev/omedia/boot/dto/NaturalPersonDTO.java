package dev.omedia.boot.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Schema(title = "Natural person", description = "Natural person data in phone_book database")
public class NaturalPersonDTO {
    @Schema(description = "Natural person id")
    private long id;

    @Schema(description = "Natural person name")
    @NotNull(message = "Natural person name should not be null")
    @NotEmpty(message = "Natural person name should not be empty")
    private String name;

    @Schema(description = "Natural person surname")
    @NotNull(message = "Natural person surname should not be null")
    @NotEmpty(message = "Natural person surname should not be empty")
    private String surname;

    @Schema(description = "Natural person comment")
    private String comment;

    @Schema(description = "Natural person birth date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    @Schema(description = "Natural person contacts")
    private Collection<ContactDTO> contacts;
}

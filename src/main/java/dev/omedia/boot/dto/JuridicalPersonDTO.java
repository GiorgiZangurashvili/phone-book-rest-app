package dev.omedia.boot.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Schema(title = "Juridical person", description = "Juridical person data in phone_book database")
public class JuridicalPersonDTO {
    @Schema(description = "Juridical person id")
    private long id;

    @NotNull(message = "Juridical person name should not be null")
    @NotEmpty(message = "Juridical person name should not be empty")
    @Schema(description = "Juridical person name")
    private String name;

    @Schema(description = "Juridical person comment")
    private String comment;

    @Schema(description = "Juridical person contactors")
    private Collection<ContactorDTO> contactors;
}

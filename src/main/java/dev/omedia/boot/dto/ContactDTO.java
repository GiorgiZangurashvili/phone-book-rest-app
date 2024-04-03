package dev.omedia.boot.dto;

import dev.omedia.boot.domain.Contact;
import dev.omedia.boot.domain.Label;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Schema(title = "Contact", description = "Contact data in phone_book database")
public class ContactDTO {
    @EqualsAndHashCode.Include
    @Schema(description = "Contact id")
    private long id;

    @NotNull(message = "Contact label should not be null")
    @NotEmpty(message = "Contact label should not be empty")
    @Schema(description = "Contact label")
    private Label label;

    @NotNull(message = "Contact value should not be null")
    @NotEmpty(message = "Contact value should not be empty")
    @Schema(description = "Contact value")
    private String value;
}

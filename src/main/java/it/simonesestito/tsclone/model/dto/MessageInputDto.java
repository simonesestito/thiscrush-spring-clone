package it.simonesestito.tsclone.model.dto;

import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class MessageInputDto {
    @NotBlank
    private String text;

    @NotBlank
    private String to;

    @NotNull
    private Boolean secret;
}

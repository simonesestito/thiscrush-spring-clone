package it.simonesestito.tsclone.model.dto;

import com.sun.istack.internal.NotNull;
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

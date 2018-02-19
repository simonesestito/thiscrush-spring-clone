package it.simonesestito.tsclone.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class SignUpUser {
    @NotBlank
    @Size(min = 5, max = 50)
    private String username;

    @NotBlank
    @Size(min = 8, max = 100)
    private String password;
}

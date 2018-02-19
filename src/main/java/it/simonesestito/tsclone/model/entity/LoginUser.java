package it.simonesestito.tsclone.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class LoginUser {
    @NotBlank
    private String username;

    @NotBlank
    private String password;
}

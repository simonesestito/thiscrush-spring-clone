package it.simonesestito.tsclone.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue
    @Column
    private Long id;

    @NotBlank
    @Column(unique = true)
    private String username;

    @NotBlank
    @Size(min = 60, max = 60) // BCrypt Size
    @Column
    private String password;
}

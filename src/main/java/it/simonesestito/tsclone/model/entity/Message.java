package it.simonesestito.tsclone.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@Table
@Entity
public class Message {
    @Id
    @GeneratedValue
    @Column
    private Long id;

    @NotBlank
    @Column
    private String text;

    @NotNull
    @Column
    private Long date;

    @ManyToOne
    @NotNull
    private User addressee;

    @ManyToOne
    @NotNull
    private User sender;

    @Column
    private Boolean secret;
}

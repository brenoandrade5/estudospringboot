package com.algaworks.algatransito.domain.model;

import com.algaworks.algatransito.domain.validation.ValidationGroups;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "proprietario")
public class Proprietario {

    @NotNull(groups = ValidationGroups.ProprietarioId.class)
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 60)
    private String nome;

    @NotBlank
    @Size(max = 60)
    @Email
    private String email;

    @NotBlank
    @Size(max = 20)
    private String telefone;


}

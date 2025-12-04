package br.com.carlos.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "usuario")
@Getter
@Setter
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotBlank(message = "O nome é obrigatório!")
    @Column(name = "nome", length = 200, nullable = false)
    private String nome;

    @NotBlank(message = "O username e obrigatório!")
    @Column(name = "username", length = 100, nullable = false)
    private String username;

    @NotBlank(message = "O email e obrigatorio")
    @Column(name = "email", length = 50, nullable = false)
    private String email;

    @NotBlank(message = "A senha e obrigatoria!")
    @Column(name = "senha", columnDefinition = "TEXT", nullable = false)
    private String senha;

    @NotBlank(message = "O telefone e obrigatorio!")
    @Column(name = "telefone", length = 15, nullable = false)
    private String telefone;
}

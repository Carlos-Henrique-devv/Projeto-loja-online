package br.com.carlos.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

    @NotBlank(message = "Nome e obrigatorio")
    private String name;

    private String surname;

    @NotBlank(message = "UserName e obrigatorio")
    private String username;

    @NotBlank(message = "Email e obrigatorio")
    @Pattern(
            regexp = "^[^@]*@[^@]*$",
            message = "O campo deve conter exatamente um '@'"
    )
    private String email;

    @NotBlank(message = "Senha e obrigatoria")
    private String password;

    @NotBlank(message = "Telefone e obrigatorio")
    private String phone;

    public UserDto(String name, String surname, String username, String email, String password, String phone) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }
}

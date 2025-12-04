package br.com.carlos.api.dto;

import jakarta.validation.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

public class LoginRequest {

    @NotBlank(message = "Email é obrigario")
    @Email(message = "Email inválido")
    private String email;

    @NotBlank(message = "senha é obrigatŕia")
    private String senha;

    public LoginRequest() {
    }

    public LoginRequest(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
